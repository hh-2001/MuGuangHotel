package com.hhz.dao.Impl;

import com.hhz.dao.LodgerDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Lodger;
import com.hhz.pojo.LodgingInfo;
import com.hhz.utils.DaoUtils;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class LodgerDaoImpl implements LodgerDao {

    @Override
    public void udpateLodger(Lodger lodger) throws UpdateExceptioin, SQLException {
        List<Object> field = DaoUtils.getField(lodger);
        String sql = "update lodger set " + field.get(1).toString() + " where id=?";
        int update = DruidUtils.update(sql, field.get(1).toString() + lodger.getId());
        if(update != 1){
            throw new SQLException("更新客户信息失败");
        }
    }

    @Override
    public void addLodger(Lodger lodger) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(lodger);
        String sql = "insert into lodger("+field.get(0).toString().replace("=?","")+") values("+field.get(2)+")";
        int update = DruidUtils.update(sql, field.get(1).toString());
        if(update != 1){
            throw new SQLException("新建客人信息失败");
        }
    }

    //通过id拿到数据
    @Override
    public Lodger getLodger(Lodger lodger) throws SQLException {
        String sql = "select * from lodger where id=?";
        return DruidUtils.query(sql, Lodger.class, lodger.getId());
    }

    @Override
    public List<LodgingInfo> getAllLodgingInfo() throws SQLException {
        String sql = "select * from lodgingInfo";
        return DruidUtils.selectTableList(sql, LodgingInfo.class, null);
    }

    //登记客人住房信息
    @Override
    public void addLodginInfo(LodgingInfo info) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(info);
        String sql = "insert into lodgingInfo("+field.get(0).toString().replace("=?","")+") values(" + field.get(2) +")";
        int update = DruidUtils.update(sql, field.get(1).toString());
        if(update != 1){
            throw new SQLException("登记客人住房失败");
        }
    }

    @Override
    public void updateLodgingInfo(LodgingInfo info, String id) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(info);
        String sql = "update lodgingInfo set ("+field.get(0)+") where id=?";
        int update = DruidUtils.update(sql, field.get(1) + id);
        if(update != 1){
            throw new SQLException("更新客人住房信息");
        }
    }

}
