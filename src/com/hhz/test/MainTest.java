package com.hhz.test;

import com.hhz.component.IocContainer;
import com.hhz.dao.LodgerDao;
import com.hhz.dao.RoomDao;
import com.hhz.dao.UserDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.*;
import org.junit.Test;

import java.sql.SQLException;

//dao层测试类，保证sql没错，可以专心处理service
public class MainTest {

    private UserDao userDao = (UserDao) IocContainer.getBean("userDao");
    private RoomDao roomDao = (RoomDao) IocContainer.getBean("roomDao");
    private LodgerDao lodgerDao = (LodgerDao) IocContainer.getBean("lodgerDao");

    /**
    * @Description:
    * @Param: []
    * @Return: void
    * @Author: HHZ
    */
    @Test
    public void addUserTest() throws SQLException, UpdateExceptioin {
        User user = new User();
        user.setPassword("1hh3");
        user.setNickName("马克");
        userDao.updateUser(user, "bob1");
    }

    @Test
    public void addLodger() throws SQLException, UpdateExceptioin {
        Lodger lodger = new Lodger();
        lodger.setId("2d1f6b63f01a4405");
        lodger.setLodgerName("test");
        lodgerDao.addLodger(lodger);
    }

    @Test
    public void addLodginInfo() throws SQLException, UpdateExceptioin {
        LodgingInfo info = new LodgingInfo();
        info.setId("019aeec7fe3c448h");
        lodgerDao.addLodginInfo(info);
    }

    @Test
    public void getAllRoom() throws SQLException, UpdateExceptioin {

    }

    //获取房间的价格
    @Test
    public void getRoomPrice() throws SQLException {
        RoomType roomPrice = roomDao.getRoomPrice("2");
        System.out.println(roomPrice.toString());
    }

}
