package com.hhz.dao;

import java.sql.SQLException;
import java.util.List;

import com.hhz.dao.BaseDao;
import com.hhz.pojo.Lodger;

//根据用户来使用，前台只能添加
public interface LodgerDao extends BaseDao {
	//获取用户的信息，通过id和idCard
	Lodger getLodgerById(String id, String idCard) throws SQLException;

	//添加用户信息
	void addLodger(Lodger lodger) throws SQLException, IllegalAccessException;
	//改用户信息
	void updateLodger(Lodger lodger) throws SQLException, IllegalAccessException;









	//删除用户信息(只有经理能操作)
	List<Lodger> getAllLodger() throws SQLException;
	void delLodger(String id, String idCard) throws SQLException;
}
