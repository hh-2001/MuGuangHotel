package com.hhz.dao.Impl;

import com.hhz.dao.TurnOverDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.BillDay;
import com.hhz.utils.DaoUtils;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class TurnOverDaoImpl implements TurnOverDao {
    @Override
    public void addBillDay(BillDay billDay) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(billDay);
        String sql = "insert into billday (" + field.get(0).toString().replace("=?","") +") values (" + field.get(2) +")";
        int update = DruidUtils.update(sql, field.get(2).toString());
        if (update != 1){
            throw new SQLException("添加营业额列失败");
        }
    }

    @Override
    public void updateBillDay(BillDay billDay, String date) throws UpdateExceptioin, SQLException {
        List<Object> field = DaoUtils.getField(billDay);
        String sql = "update billday set " + field.get(0).toString() +"where date=?";
        int update = DruidUtils.update(sql, field.get(2).toString());
        if (update != 1){
            throw new SQLException("更新营业额失败");
        }
    }

    @Override
    public BillDay getBillDay(String date) throws SQLException {
        String sql = "select * from billday where date=?";
        return DruidUtils.query(sql, BillDay.class, date);
    }

    @Override
    public List<BillDay> getAll() throws SQLException {
        String sql = "select * from billday";
        return DruidUtils.selectTableList(sql, BillDay.class, null);
    }

//    public static void main(String[] args) throws SQLException {
//        TurnOverDaoImpl turnOverDao = new TurnOverDaoImpl();
//        List<BillDay> all = turnOverDao.getAll();
//        System.out.println(all.get(0));
//    }
}
