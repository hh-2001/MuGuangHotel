package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.LodgerDao;
import com.hhz.pojo.Lodger;
import com.hhz.service.BaseService;
import com.hhz.service.LodgerService;
import com.hhz.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;



public class LodgerServiceImpl extends BaseService implements LodgerService {

	private LodgerDao lodgerDao = (LodgerDao) IocContainer.getBean("lodgerDao");

	@Override
	public Lodger getLodgerById(String id, String idCard) throws SQLException {
		DruidUtils.getConnection();
		Lodger lodger = lodgerDao.getLodgerById(id, idCard);
		DruidUtils.closeConnection();
		return lodger;
	}

	@Override
	public void saveLodger(Lodger lodger) throws SQLException, IllegalAccessException {
		// TODO Auto-generated method stub
		Connection conn = DruidUtils.getConnection();
		String id = lodger.getId();
		String idCard = lodger.getIdCard();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = simpleDateFormat.format(System.currentTimeMillis());
		conn.setAutoCommit(false);
		try {
			if(id!=null && idCard!=null) {
				lodgerDao.updateLodger(lodger);
			}else{
				lodger.setId(makeRegisterId(lodger.getPhone(),lodger.getIdCard()));
				lodger.setCreateDate(now);
				lodgerDao.addLodger(lodger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		DruidUtils.closeConnection();
	}


	@Override
	public List<Lodger> getAllLodger() throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.getConnection();
		List<Lodger> lodger = lodgerDao.getAllLodger();
		DruidUtils.closeConnection();
		return lodger;
	}

	@Override
	public void delLodger(String id, String idCard) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.getConnection();
		lodgerDao.delLodger(id, idCard);
		DruidUtils.closeConnection();
	}

}
