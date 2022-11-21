package com.hhz.service.Impl;

import com.hhz.component.IocContainer;
import com.hhz.dao.RoomDao;
import com.hhz.pojo.PageBean;
import com.hhz.pojo.PageParam;
import com.hhz.pojo.Room;
import com.hhz.service.BaseService;
import com.hhz.service.RoomService;
import com.hhz.utils.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class RoomServiceImpl extends BaseService implements RoomService {

	private RoomDao roomDao = (RoomDao) IocContainer.getBean("roomDao");

	@Override
	public PageBean<Room> getRoomList(PageParam param) throws SQLException {
		DruidUtils.getConnection();
		Map map = param.getKeywords(); // 拿到搜索关键字
		List<Room> list = new ArrayList<>();

		PageBean<Room> pBean = new PageBean<>();
		try {
			// {1}获取 sql 搜索 sql 字符串(片段)
			String sqlPart = roomDao.getSearchSQL(map, "");

			// {2}获取 用户的数量。
			int count = roomDao.getRoomCount(sqlPart);
			// {3}计算偏移量。
			int page = param.getPage();
			int offset = (page - 1) * param.getLimit();
			list = roomDao.getRoomList(sqlPart, offset, param.getLimit());
			pBean.setCount(count);
			pBean.setData(list);
		} finally {
			// 这里暂时还是需要去关闭连接, 原因是 DruidUtils
			// 里面使用 ThreadLocal, 除非把它去掉。
			DruidUtils.closeConnection();
		}
		return pBean;
	}

	@Override
	public void saveRoom(Room room) throws SQLException, IllegalAccessException {
		// TODO Auto-generated method stub
		Connection conn = DruidUtils.getConnection();
		String id = room.getId();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = simpleDateFormat.format(System.currentTimeMillis());
		conn.setAutoCommit(false);
		if (id != null) {
			if(room.getStatus().equals("已入住")) {
				room.setStatus("1", 1);
			}else if(room.getStatus().equals("空闲")){
				room.setStatus("0", 0);
			}
			room.setUpdateDate(now);
			roomDao.updateRoom(room, room.getId());
		} else {
			room.setId("r" + room.getRoomNo());
			room.setStatus("0", 0);
			room.setCreateDate(now);
			roomDao.addRoom(room);
		}
		conn.commit();
		DruidUtils.closeConnection();
	}

	@Override
	public void updateRoom(Room room, String id) throws IllegalAccessException, SQLException {
		DruidUtils.getConnection();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = simpleDateFormat.format(System.currentTimeMillis());
		room.setUpdateDate(now);
		roomDao.updateRoom(room, id);
		DruidUtils.closeConnection();
	}

	@Override
	public void delRoom(String id) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.getConnection();
		roomDao.delRoom(id);
		DruidUtils.closeConnection();
	}

	@Override
	public Room getRoomByNo(String no) throws SQLException {
		DruidUtils.getConnection();
		Room room = roomDao.getRoomById(no);
		DruidUtils.closeConnection();
		return room;
	}

}
