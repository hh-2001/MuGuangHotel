package com.hhz.dao.Impl;

import com.hhz.dao.LodgerDao;
import com.hhz.pojo.Lodger;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;



public class LodgerDaoImpl implements LodgerDao {

	@Override
	public Lodger getLodgerById(String id, String idCard) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from lodger where id=? and idCard=?";
		Lodger lodger = DruidUtils.queryObject(sql, Lodger.class, id+","+idCard);
		if(lodger==null) {
			throw new SQLException("获取用户信息失败");
		}
		return lodger;
	}

	@Override
	public void addLodger(Lodger lodger) throws SQLException, IllegalAccessException {
		// TODO Auto-generated method stub
		int updateMethod = updateMethod("lodger", "add", lodger, null);
		if(updateMethod!=1) {
			throw new SQLException("登记新用户");
		}
	}

	@Override
	public void updateLodger(Lodger lodger) throws SQLException, IllegalAccessException {
		// TODO Auto-generated method stub
		int updateMethod = updateMethod("lodger", "update", lodger, null);
		if(updateMethod!=1) {
			throw new SQLException("更新用户信息失败");
		}
	}

	@Override
	public List<Lodger> getAllLodger() throws SQLException {
		String sql = "select * from lodger";
		List<Lodger> list = DruidUtils.queryList(sql, Lodger.class, null);
		if(list.size() > 0) {
			throw new SQLException("查询所有用户失败");
		}
		return list;
	}

	@Override
	public void delLodger(String id, String idCard) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from lodger where id=? and idCard=?";
		int update = DruidUtils.update(sql, id+","+idCard);
		if(update!=1) {
			throw new SQLException("删除用户失败");
		}
	}

}
