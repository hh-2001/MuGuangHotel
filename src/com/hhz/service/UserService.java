package com.hhz.service;

import com.hhz.exception.ValidException;
import com.hhz.pojo.PageBean;
import com.hhz.pojo.PageParam;
import com.hhz.pojo.User;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

	User doLogin(User user)
			throws SQLException, ValidException;

	void saveUser(User user)
			throws SQLException, IllegalAccessException;

	//{3}编写 getUserList() 方法
	PageBean getUserList(PageParam param)
			throws SQLException;

	//{4}编写 getUserList() 方法
	User getUserById(String id)
			throws SQLException;

	//{4}编写 delUserList() 方法
	void delUserById(String id)
			throws SQLException;

	List viewList();
}
