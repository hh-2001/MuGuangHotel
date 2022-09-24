package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.UserDao;
import com.hhz.exception.LoginException;
import com.hhz.pojo.User;
import com.hhz.service.CommonService;

import java.sql.SQLException;

public class CommonServiceImpl implements CommonService {

    private UserDao userDao = (UserDao) IocContainer.getBean("userDao");

    @Override
    public User doLogin(User user) throws SQLException, LoginException {
        User account = userDao.getByAccount(user);
        if(account == null){
            throw new LoginException("无此员工");
        }else if(!user.getPassword().equals(account.getPassword())){
            throw new LoginException(user.getAccount()+"密码错误");
        }
        return account;
    }

}
