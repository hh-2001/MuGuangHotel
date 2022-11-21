package com.hhz.service;

import com.hhz.pojo.Lodger;

import java.sql.SQLException;
import java.util.List;


public interface LodgerService {
	//获取用户的信息，通过id和idCard
	Lodger getLodgerById(String id, String idCard) throws SQLException;

	//添加用户信息
	void saveLodger(Lodger lodger) throws SQLException, IllegalAccessException;

	//删除用户信息(只有经理能操作)
	List<Lodger> getAllLodger() throws SQLException;
	void delLodger(String id, String idCard) throws SQLException;
}
