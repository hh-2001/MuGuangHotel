package com.hhz.dao;

import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Room;
import com.hhz.pojo.RoomType;
import com.hhz.vo.RoomVo;

import java.sql.SQLException;
import java.util.List;

//客房记录操作(住客就登记,更新)
public interface RoomDao {

    //查询指定的房间信息;两种方式roomNo，storeyId
    Room getRoom(Room room) throws SQLException, UpdateExceptioin;//通过传递的参数不同获取

    //room(有人入住就改变房间状态):只能更新status(viewList一直挂着的页面)需要作联表roomtype并且更新bill表
    void updateRoomStatus(Room room, String roomNo) throws SQLException, UpdateExceptioin;

    //viewlist
    List<RoomVo> getAllRoom(Integer offset, Integer limit) throws SQLException;
    int getCount() throws SQLException;

    //获取房间的不同价格
    RoomType getRoomPrice(String typeId) throws SQLException;
}
