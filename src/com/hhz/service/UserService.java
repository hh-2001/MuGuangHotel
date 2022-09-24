package com.hhz.service;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.User;
import com.hhz.pojo.page.PageBean;
import com.hhz.pojo.page.PageParam;
import com.hhz.vo.RoomVo;
import com.hhz.vo.UserVO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    //用户列表
    int getCount() throws SQLException;
    List<User> viewList() throws SQLException;
    List<UserVO> viewLimitList(PageParam param) throws SQLException;

    //新增用户
    void addUser(User user) throws SQLException, UpdateExceptioin;

    //编辑用户信息，所有信息都可以
    void updateUser(User user) throws SQLException, UpdateExceptioin;

    //删除用户
    void delUser(User user) throws SQLException, UpdateExceptioin;

    //获取特定用户
    User getUser(User user) throws SQLException, UpdateExceptioin;


}
