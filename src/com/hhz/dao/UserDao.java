package com.hhz.dao;

import java.sql.SQLException;
import java.util.List;

import com.hhz.dao.BaseDao;
import com.hhz.pojo.User;

public interface UserDao extends BaseDao {
	//{1}获取用户
	User getUserByAccount(
			String account, boolean required)
			throws SQLException;
	//{2}新增用户
	void addUser(User user)
			throws SQLException, IllegalAccessException;
	//{3}修改用户
	void updateUser(User user, String id)
			throws SQLException, IllegalAccessException;
	//{4}删除用户
	void delUser(String id)
			throws SQLException;
	//{5}获取用户的数量(不包含管理员)
	int getUserCount(String sqlPart)
			throws SQLException;
	//{6}获取用户的列表(不包含管理员)
	List<User> getUserList(
			String sqlPart, Integer offset, Integer limit )
			throws SQLException;
	//{7}根据 id 获取用户
	User getUserById(String id)
			throws SQLException;

    User getByAccount(User user);
}
