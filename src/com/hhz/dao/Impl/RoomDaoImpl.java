package com.hhz.dao.Impl;

import com.hhz.dao.RoomDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.Room;
import com.hhz.pojo.RoomType;
import com.hhz.utils.DaoUtils;
import com.hhz.utils.DruidUtils;
import com.hhz.vo.RoomVo;

import java.sql.SQLException;
import java.util.List;

public class RoomDaoImpl implements RoomDao {

    @Override
    public Room getRoom(Room room) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(room);
        String sql = "select * from room where "+field.get(0);
        DruidUtils.query(sql, Room.class, field.get(1).toString());
        return null;
    }

    @Override
    public void updateRoomStatus(Room room, String roomNo) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(room);
        String sql = "update room set ("+field.get(0)+") where roomNo=?";
        int update = DruidUtils.update(sql, field.get(1) + roomNo);
        if(update != 1){
            throw new SQLException("更新客人住房状态失败");
        }
    }

    @Override
    public List<RoomVo> getAllRoom(Integer offset, Integer limit) throws SQLException {
        String sql = "select m.roomNo,m.typeName,m.`status`,s.floor,s.descript,m.dayPrice,m.hourPrice\n" +
                "from (select r.storeyId,r.`status`,r.roomNo,rt.typeName,rt.dayPrice,rt.hourPrice from room r left join roomtype rt on r.typeId=rt.id) m \n" +
                "left join storey s on m.storeyId=s.floor limit " + offset+","+limit;
        return DruidUtils.selectTableList(sql, RoomVo.class, null);
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "select count(*) from (select r.storeyId from room r left join roomtype rt on r.typeId=rt.id) m left join storey s on m.storeyId=s.floor";
        Long value = (Long) DruidUtils.getValue(sql, null);
        return value.intValue();
    }

    public static void main(String[] args) throws SQLException {
        RoomDaoImpl roomDao = new RoomDaoImpl();
        roomDao.getCount();
    }

    @Override
    public RoomType getRoomPrice(String typeId) throws SQLException {
        String sql = "select * from roomType where id=?";
        return DruidUtils.query(sql, RoomType.class, typeId);
    }


}
