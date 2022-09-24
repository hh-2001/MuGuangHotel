package com.hhz.dao.Impl;

import com.hhz.dao.PriceDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.RoomType;
import com.hhz.utils.DaoUtils;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

public class PriceDaoImpl implements PriceDao {
    @Override
    public void addRoomType(RoomType roomType) throws UpdateExceptioin, SQLException {
        List<Object> field = DaoUtils.getField(roomType);
        String sql = "insert into lodgingInfo("+field.get(0).toString().replace("=?","")+") values(" + field.get(2) +")";
        int update = DruidUtils.update(sql, field.get(1).toString());
        if(update != 1){
            throw new SQLException("添加客人信息失败");
        }
    }

    @Override
    public void updatePrice(RoomType roomType, String id) throws UpdateExceptioin, SQLException {
        List<Object> field = DaoUtils.getField(roomType);
        String sql = "update roomType set ("+field.get(0)+") where id=?";
        int update = DruidUtils.update(sql, field.get(1).toString() + id);
        if(update != 1){
            throw new SQLException("修改房间标准信息失败");
        }
    }
}
