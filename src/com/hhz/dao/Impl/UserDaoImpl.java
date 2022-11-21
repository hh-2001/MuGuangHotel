package com.hhz.dao.Impl;

import com.hhz.dao.UserDao;
import com.hhz.exception.ValidException;
import com.hhz.pojo.User;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class UserDaoImpl
		implements UserDao {
	@Override
	public User getUserByAccount(
			String account, boolean required)
			throws SQLException {
		//{1}编写 sql 语句。
		String sql = "select u.*, r.roleName from user u "+
				" left join role r on u.roleid=r.id"+
				" where u.account=?";
		User user = DruidUtils.queryObject(
				sql, User.class, account);
		//{6}required=true 表示我一定要得到该对象
		if( required && user==null ){
			//没有对象, 抛出异常。
			throw new ValidException(
					account +": 该用户不存在。");
		}
		return user;
	}

	@Override
	public void addUser(User user)
			throws SQLException, IllegalAccessException {
		List<Object> field = getField(user);
		String sql = "insert into user("+ field.get(0).toString().replace("=?", "") +")"+
				" values("+field.get(2)+")";
		int cnt = DruidUtils.update( sql, field.get(1).toString());
		if( cnt!=1 ){
			throw new SQLException("添加用户失败。");
		}
	}

	@Override
	public void updateUser(User user, String id)
			throws SQLException, IllegalAccessException {
		List<Object> field = getField(user);
		String sql = "update user set "+ field.get(0) +
				" where id=?";
		int cnt = DruidUtils.update( sql, field.get(1).toString()+id);
		if(cnt!=1){
			throw new SQLException("更新用户失败。");
		}
	}

	@Override
	public void delUser(String id)
			throws SQLException {
		String sql = "delete from user where id=?";
		int cnt = DruidUtils.update( sql, id );
		if(cnt!=1){
			throw new SQLException("删除用户失败。");
		}
	}

	//sqlPart 【SQL】的片段
	@Override
	public int getUserCount(String sqlPart)
			throws SQLException {
		String sql = "select count(u.id) from user u "+
				" left join role r on u.roleid=r.id "+
				" where r.roleName!='系统管理员' "+ sqlPart;
		//System.out.println("SQL:"+ sql);
		Long cnt = (Long)DruidUtils.getValue( sql,null );
		return cnt.intValue();
	}

	//sqlPart实现模糊搜索
	@Override
	public List<User> getUserList(
			String sqlPart, Integer offset,
			Integer limit )
			throws SQLException {
		String sql = "select u.*, r.roleName from user u "+
				" left join role r on u.roleId=r.id "+
				" where r.roleName!='系统管理员' "+ sqlPart +
				" limit "+offset+","+limit;
		List<User> list = DruidUtils.queryList(
				sql, User.class, null );
		return list;
	}

	@Override
	public User getUserById(String id)
			throws SQLException {
		String sql = "select u.*, r.roleName from user u "+
				" left join role r on u.roleId=r.id "+
				" where r.roleName!='系统管理员' "+
				" and u.id = ?";
		User user = DruidUtils.queryObject(
				sql, User.class, id);
		if( user==null ){
			throw new SQLException("此用户"+ id +"不存在。");
		}
		return user;
	}

	@Override
	public User getByAccount(User user) {
		return null;
	}

}




