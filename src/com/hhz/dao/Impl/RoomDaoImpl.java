package com.hhz.dao.Impl;

import com.hhz.dao.RoomDao;
import com.hhz.pojo.Room;
import com.hhz.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;



public class RoomDaoImpl implements RoomDao {

	@Override
	public void addRoom(Room room) throws SQLException, IllegalAccessException {
		// TODO Auto-generated method stub
		List<Object> field = getField(room);
		String sql = "insert into room(" + field.get(0).toString().replace("=?", "") + ") values(" + field.get(2) +")";
		int update = DruidUtils.update(sql, field.get(1).toString());
		if(update != 1) {
			throw new SQLException("新增房间失败");
		}
	}

	@Override
	public void updateRoom(Room room, String id) throws IllegalAccessException, SQLException {
		List<Object> field = getField(room);
		String sql = "update room set "+field.get(0).toString()+" where id=?";
		System.out.println(field.get(1));
		int update = DruidUtils.update(sql, field.get(1).toString()+id);
		if(update != 1) {
			throw new SQLException("房间信息更新失败");
		}
	}

	@Override
	public void delRoom(String id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from room where id=?";
		int update = DruidUtils.update(sql, id);
		if(update != 1) {
			throw new SQLException("房间信息删除失败");
		}
	}

	@Override
	public Room getRoomById(String id) throws SQLException {
		String sql = "select * from view_room where id=?";
		Room room = DruidUtils.queryObject(sql, Room.class, id);
		if(room==null) {
			throw new SQLException("没有此房间");
		}
		return room;
	}


	@Override
	public List<Room> getRoomList(String sqlPart, Integer offset, Integer limit) throws SQLException {
		String sql = "select * from view_room where 1=1" + sqlPart +" limit " +offset +","+limit;
		return DruidUtils.queryList(sql, Room.class, null);
	}

	@Override
	public Integer getRoomCount(String sqlPart) throws SQLException {
		String sql = "select count(*) from view_room where 1=1 "+sqlPart;
		Long value = (Long) DruidUtils.getValue(sql, null);
		return value.intValue();
	}

}
