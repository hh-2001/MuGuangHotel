package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.UserDao;
import com.hhz.exception.ValidException;
import com.hhz.pojo.PageBean;
import com.hhz.pojo.PageParam;
import com.hhz.pojo.User;
import com.hhz.service.BaseService;
import com.hhz.service.UserService;
import com.hhz.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class UserServiceImpl
		extends BaseService
		implements UserService {

	private UserDao userDao = (UserDao) IocContainer
			.getBean("userDao");
	@Override
	public User doLogin(User user)
			throws SQLException, ValidException {
		DruidUtils.getConnection();
		User dbUser = null;
		try{
			dbUser = userDao.getUserByAccount(user.getAccount(), true );
			String ps = user.getPassword();
			String dPS = dbUser.getPassword();
			if( !dPS.equals(ps) ){
				throw new ValidException("用户登录失败。");
			}
		}finally{
			//这里暂时还是需要去关闭连接, 原因是 DruidUtils
			//里面使用 ThreadLocal, 除非把它去掉。
			DruidUtils.closeConnection();
		}
		return dbUser;
	}

	@Override
	public void saveUser(User user)
			throws SQLException, IllegalAccessException {
		Connection conn = DruidUtils.getConnection();
		String id = user.getId();
		conn.setAutoCommit(false);
		try{
			if(id!=null) {
				userDao.updateUser( user, user.getId() );
			}else {
				user.setId( uuid() );   //设置 uuid
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String now = simpleDateFormat.format(System.currentTimeMillis());
				user.setCreateDate(now.toString());   //id==null --> 添加,自动生成
				userDao.addUser(user);
			}
			conn.commit();
		}catch(SQLException e){
			e.printStackTrace();//这里可能要处理区分异常
		}
		DruidUtils.closeConnection();
	}

	@Override
	public PageBean<User> getUserList(
			PageParam param) throws SQLException {

		DruidUtils.getConnection();
		Map map = param.getKeywords();    //拿到搜索关键字
		List<User> list = new ArrayList<>();

		PageBean<User> pBean = new PageBean<>();
		try{
			//{1}获取 sql 搜索 sql 字符串(片段)
			String sqlPart = userDao.getSearchSQL( map, "u.");
			//{2}获取 用户的数量。
			int count = userDao.getUserCount( sqlPart );
			//{3}计算偏移量。
			int page = param.getPage();
			int offset = (page-1)* param.getLimit();
			list = userDao.getUserList( sqlPart, offset,
					param.getLimit() );
			pBean.setCount( count );
			pBean.setData( list );
		}finally{
			//这里暂时还是需要去关闭连接, 原因是 DruidUtils
			//里面使用 ThreadLocal, 除非把它去掉。
			DruidUtils.closeConnection();
		}
		return pBean;
	}

	@Override
	public User getUserById(String id)
			throws SQLException {
		DruidUtils.getConnection();
		User user = null;
		try{
			//{2}调用 dao 获取用户。
			user = userDao.getUserById(id);
		}finally{
			DruidUtils.closeConnection();
		}
		return user;
	}

	@Override
	public void delUserById(String id)
			throws SQLException {
		DruidUtils.getConnection();
		try{
			//{2}调用 dao 获取用户。
			userDao.delUser(id);
		}finally{
			DruidUtils.closeConnection();
		}
	}

	@Override
	public List viewList() {
		return null;
	}

}




