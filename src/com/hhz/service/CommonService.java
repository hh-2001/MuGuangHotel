package com.hhz.service;

import com.hhz.exception.LoginException;
import com.hhz.pojo.User;

import java.sql.SQLException;

public interface CommonService {
    User doLogin(User user) throws SQLException, LoginException;
}
