package com.hhz.dao;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Lodger;
import com.hhz.pojo.LodgingInfo;
import com.hhz.pojo.User;
import com.hhz.vo.UserVO;

import java.sql.SQLException;
import java.util.List;

//员工的操作
public interface UserDao {
    //登录使用的是账号
    User getByAccount(User user) throws SQLException;

    //可以操作自身的user:修改password,sex,nickName
    void updateUser(User user, String account) throws SQLException, UpdateExceptioin;

    //管理员获取user所有信息
    int getCount() throws SQLException;
    List<User> getAllUser() throws SQLException;
    List<UserVO> getLimitUser(Integer offset, Integer limit) throws SQLException;
    User getUser(User user) throws SQLException, UpdateExceptioin;

    //增加一个员工
    void addUser(User user) throws SQLException, UpdateExceptioin;

    //删除一个员工;;通过id，account,no三个都能删除
    void delUser(User user) throws SQLException, UpdateExceptioin;



}
