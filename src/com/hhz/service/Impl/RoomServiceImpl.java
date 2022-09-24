package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.RoomDao;
import com.hhz.dao.TurnOverDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.BillDay;
import com.hhz.pojo.Room;
import com.hhz.pojo.page.PageBean;
import com.hhz.pojo.page.PageParam;
import com.hhz.service.RoomService;
import com.hhz.vo.RoomVo;

import java.sql.SQLException;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    private RoomDao roomDao = (RoomDao) IocContainer.getBean("roomDao");
    private TurnOverDao turnOverDao = (TurnOverDao) IocContainer.getBean("turnOverDao");

    @Override
    public List<RoomVo> getRoomList(PageParam param, PageBean bean) throws SQLException {
        int page = param.getPage();
        int offset = (page - 1) * param.getLimit();
        int count = roomDao.getCount();
        bean.setCount(count);
        return roomDao.getAllRoom(offset, param.getLimit());
    }

    @Override
    public void updateStatus(Room room, String roomNo) throws SQLException, UpdateExceptioin {
        roomDao.updateRoomStatus(room, roomNo);
    }

    @Override
    public void addBillDay(BillDay billDay) throws SQLException, UpdateExceptioin {
        turnOverDao.addBillDay(billDay);
    }

    @Override
    public void updateBillDay(BillDay billDay, String date) throws UpdateExceptioin, SQLException {
        turnOverDao.updateBillDay(billDay, date);
    }

    @Override
    public BillDay getBillDay(String date) throws SQLException {
        return turnOverDao.getBillDay(date);
    }

    @Override
    public List<BillDay> getAll() throws SQLException {
        return turnOverDao.getAll();
    }


}
