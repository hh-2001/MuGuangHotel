package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.UserDao;
import com.hhz.exception.LoginException;
import com.hhz.pojo.User;
import com.hhz.service.UserService;
import com.hhz.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) IocContainer.getBean("userDao");

    @Override
    public User doLogin(User user) throws SQLException, LoginException {
        Connection conn = DruidUtils.getConnection();
        if(conn==null){
            throw new SQLException("Conn连接失败");
        }
        User account = userDao.getByAccount(user.getAccount(), conn);
        if(account == null){
            throw new LoginException("无此员工");
        }else if(!user.getPassword().equals(account.getPassword())){
            throw new LoginException(user.getAccount()+"密码错误");
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
