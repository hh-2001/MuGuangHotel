package com.hhz.service;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.BillDay;
import com.hhz.pojo.Room;
import com.hhz.pojo.page.PageBean;
import com.hhz.pojo.page.PageParam;
import com.hhz.vo.RoomVo;

import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    //获取房间信息
    List<RoomVo> getRoomList(PageParam param, PageBean bean) throws SQLException;

    //更新房间状态
    void updateStatus(Room room, String roomNo) throws SQLException, UpdateExceptioin;

    //添加营业额
    void addBillDay(BillDay billDay) throws SQLException, UpdateExceptioin;

    //更新营业额
    void updateBillDay(BillDay billDay, String date) throws UpdateExceptioin, SQLException;

    //获取当前日期的营业额
    BillDay getBillDay(String date) throws SQLException;
    List<BillDay> getAll() throws SQLException;
}
