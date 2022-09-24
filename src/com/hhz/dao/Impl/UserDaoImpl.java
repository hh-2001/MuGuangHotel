package com.hhz.dao.Impl;

import com.hhz.dao.UserDao;
import com.hhz.exception.UpdateExceptioin;
import com.hhz.pojo.User;
import com.hhz.utils.DaoUtils;
import com.hhz.utils.DruidUtils;
import com.hhz.vo.UserVO;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 用户表操作
 * @Author: HHZ
 * @date: 2022-9-10
*/
public class UserDaoImpl implements UserDao {
    @Override
    public User getByAccount(User user) throws SQLException {
        String sql = "select u.*, r.roleName from user u "+
                " left join role r on u.roleId=r.id"+
                " where u.account=?";
        return DruidUtils.query(sql, User.class, user.getAccount().toString());
    }

    @Override
    public void updateUser(User user, String account) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(user);//需要判断传进来什么东西来判断改了什么
        String sql = "update user set " + field.get(0) + " where account=?";
        System.out.println(sql);
        int update = DruidUtils.update(sql, field.get(1) + account);
        if(update != 1){
            throw new SQLException("更新用户信息失败(前台使用)");
        }
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "select Count(*) from user";
        Long value = (Long) DruidUtils.getValue(sql, null);
        return value.intValue();
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sql = "select * from user";
        return DruidUtils.selectTableList(sql, User.class, null);
    }

    @Override
    public List<UserVO> getLimitUser(Integer offset, Integer limit) throws SQLException {
        String sql = "select * from user u left join role r on u.roleId=r.id limit "+ offset+","+limit;
        return DruidUtils.selectTableList(sql, UserVO.class, null);
    }

    @Override
    public User getUser(User user) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(user);
        String sql = "select * from user where " + field.get(0);
        return DruidUtils.query(sql, User.class, field.get(1).toString());
    }

    @Override
    public void addUser(User user) throws SQLException, UpdateExceptioin {
        List<Object> field = DaoUtils.getField(user);
        String sql = "insert into user (" + field.get(0).toString().replace("=?","") +") values (" +field.get(2) +")";
        int update = DruidUtils.update(sql, field.get(1).toString());
        if(update != 1){
            throw new SQLException("添加用户失败");
        }
    }

    @Override
    public void delUser(User user) throws UpdateExceptioin, SQLException {
        List<Object> field = DaoUtils.getField(user);
        String sql = "delete from user where " + field.get(0);
        int update = DruidUtils.update(sql, field.get(1).toString());
        if(update != 1){
            throw new SQLException("删除用户失败");
        }
    }

}
