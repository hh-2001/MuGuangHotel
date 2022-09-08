package com.hhz.dao.Impl;

import com.hhz.dao.UserDao;
import com.hhz.pojo.User;
import com.hhz.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getByAccount(String account, Connection conn) throws SQLException {
        String sql = "select * from user where account=?";
        return DruidUtils.query(sql, User.class, account);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sql = "select * from user";
        return DruidUtils.selectTableList(sql, User.class, null);
    }
}
