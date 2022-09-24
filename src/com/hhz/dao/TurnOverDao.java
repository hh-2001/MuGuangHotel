package com.hhz.dao;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.BillDay;

import java.sql.SQLException;
import java.util.List;

public interface TurnOverDao {
    //添加营业额
    void addBillDay(BillDay billDay) throws SQLException, UpdateExceptioin;

    //更新营业额
    void updateBillDay(BillDay billDay, String date) throws UpdateExceptioin, SQLException;

    //获取当前日期的营业额
    BillDay getBillDay(String date) throws SQLException;
    List<BillDay> getAll() throws SQLException;
}
