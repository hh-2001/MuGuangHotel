package com.hhz.service;

import com.hhz.pojo.PageBean;
import com.hhz.pojo.PageParam;
import com.hhz.pojo.Room;

import java.sql.SQLException;



public interface RoomService {
	//新增房间
	void saveRoom(Room room) throws SQLException, IllegalAccessException;
	//房间列表
	PageBean<Room> getRoomList(PageParam param) throws SQLException;
	//修改房间
	void updateRoom(Room room, String id) throws IllegalAccessException, SQLException;
	//删除房间
	void delRoom(String id) throws SQLException;
	Room getRoomByNo(String no) throws SQLException;

}
