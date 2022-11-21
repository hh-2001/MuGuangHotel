package com.hhz.dao;

import java.sql.SQLException;
import java.util.List;

import com.hhz.dao.BaseDao;
import com.hhz.pojo.Room;

public interface RoomDao extends BaseDao {
	//新增房间
	void addRoom(Room room) throws SQLException, IllegalAccessException;
	//房间列表
	List<Room> getRoomList(String sqlPart,Integer offset, Integer limit) throws SQLException;
	//修改房间
	void updateRoom(Room room, String id) throws IllegalAccessException, SQLException;
	//删除房间
	void delRoom(String id) throws SQLException;
	//获取房间信息
	Room getRoomById(String no) throws SQLException;
	Integer getRoomCount(String sqlPart) throws SQLException;

}
