package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.LodgerDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Lodger;
import com.hhz.pojo.LodgingInfo;
import com.hhz.service.LodgerService;

import java.sql.SQLException;
import java.util.List;

public class LodgerServiceImpl implements LodgerService {

    private LodgerDao lodgerDao = (LodgerDao) IocContainer.getBean("lodgerDao");

    @Override
    public void udpateLodger(Lodger lodger) throws UpdateExceptioin, SQLException {
        lodgerDao.udpateLodger(lodger);
    }

    @Override
    public void addLodger(Lodger lodger) throws SQLException, UpdateExceptioin {
        lodgerDao.addLodger(lodger);
    }

    @Override
    public Lodger getLodger(Lodger lodger) throws SQLException {
        return lodgerDao.getLodger(lodger);
    }

    @Override
    public void addLodginInfo(LodgingInfo info) throws SQLException, UpdateExceptioin {
        lodgerDao.addLodginInfo(info);
    }

    @Override
    public void updateLodgingInfo(LodgingInfo info, String id) throws SQLException, UpdateExceptioin {
        lodgerDao.updateLodgingInfo(info, id);
    }

    @Override
    public List<LodgingInfo> getAllLodgingInfo() throws SQLException {
        return lodgerDao.getAllLodgingInfo();
    }
}
