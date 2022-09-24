package com.hhz.dao;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.RoomType;

import java.sql.SQLException;

public interface PriceDao {

    //插入房间价格
    void addRoomType(RoomType roomType) throws UpdateExceptioin, SQLException;

    //更新房间价格表
    void updatePrice(RoomType roomType, String id) throws UpdateExceptioin, SQLException;
}
