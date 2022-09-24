package com.hhz.dao;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Lodger;
import com.hhz.pojo.LodgingInfo;

import java.sql.SQLException;
import java.util.List;

//客人记录操作
public interface LodgerDao {
    //更新客人单次记录信息
    void udpateLodger(Lodger lodger) throws UpdateExceptioin, SQLException;

    //lodger(登记客人信息):只能增加(新客人就增加)
    void addLodger(Lodger lodger) throws SQLException, UpdateExceptioin;
    Lodger getLodger(Lodger lodger) throws SQLException;
    List<LodgingInfo> getAllLodgingInfo() throws SQLException;

    //lodginginfo:更新(离开时间)，若是新的登记就增加一条记录(同时在lodger查找，若无在lodger增加)
    void addLodginInfo(LodgingInfo info) throws SQLException, UpdateExceptioin;
    void updateLodgingInfo(LodgingInfo info, String id) throws SQLException, UpdateExceptioin;
}
