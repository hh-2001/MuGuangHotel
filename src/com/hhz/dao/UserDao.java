package com.hhz.dao;

import com.hhz.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //通过名字获取
    User getByAccount(String account, Connection conn) throws SQLException;

    //管理员获取user所有信息
    List<User> getAllUser() throws SQLException;
}
