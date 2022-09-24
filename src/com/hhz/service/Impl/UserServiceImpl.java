package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.RoomDao;
import com.hhz.dao.UserDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.User;
import com.hhz.pojo.page.PageParam;
import com.hhz.service.UserService;
import com.hhz.vo.UserVO;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) IocContainer.getBean("userDao");
    private RoomDao roomDao = (RoomDao) IocContainer.getBean("roomDao");

    @Override
    public List<User> viewList() throws SQLException {
        return userDao.getAllUser();
    }

    @Override
    public int getCount() throws SQLException {
        return userDao.getCount();
    }
    @Override
    public List<UserVO> viewLimitList(PageParam param) throws SQLException {
        int offset = (param.getPage() - 1) * param.getLimit();
        return userDao.getLimitUser(offset, param.getLimit());
    }

    @Override
    public void addUser(User user) throws SQLException, UpdateExceptioin {
        if(user != null){
            userDao.addUser(user);
        }
    }

    @Override
    public void updateUser(User user) throws SQLException, UpdateExceptioin {
        if(user != null){
            userDao.updateUser(user, user.getId());
        }
    }

    @Override
    public void delUser(User user) throws SQLException, UpdateExceptioin {
        if(user != null){
            userDao.delUser(user);//只要三个
        }
    }

    @Override
    public User getUser(User user) throws SQLException, UpdateExceptioin {
        if(user != null){
            user = userDao.getUser(user);
        }
        return user;
    }


//    public static void main(String[] args) {
//        UserService userService = (UserService) IocContainer.getBean("userService");
//        User user = new User();
//        user.setAccount("bob15");
//        user.setPassword("123");
//        User allUser = userService.doLogin(user);
//        System.out.println(allUser.toString());
//    }
}
