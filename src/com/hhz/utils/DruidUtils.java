package com.hhz.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DruidUtils {

    private static DataSource dataSource;

    //加载druid的配置信息，创建datasource
    static {
        try {
            Properties properties = new Properties();
            InputStream is = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    //获取连接的公共类
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //关闭连接的类
    public static void close(PreparedStatement pstat, Connection conn){
        close(pstat, null, conn);
    }

    public static void close(PreparedStatement stmt, ResultSet rs, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static QueryRunner QR = new QueryRunner(dataSource);
    //使用queryRunner
    public static int update(String sql, String param) throws SQLException {
        Connection conn = getConnection();
        String[] params = null;
        if(param != null){
            params = param.split(",");
        }
        return QR.update(conn ,sql, params);
    }

    //查询单个对象
    public static <E> E query(String sql, Class<E> clazz, String param) throws SQLException {
        Connection conn = getConnection();
        String[] params = null;
        if(param != null){
            params = param.split(",");
        }
        return QR.query(conn, sql, new BeanHandler<>(clazz), params);
    }

    //一个对象列表查询
    public static <E> List<E> selectTableList(String sql, Class<E> clazz, String param) throws SQLException {
        Connection conn = getConnection();
        String[] params = null;
        if(param != null){
            params = param.split(",");
        }
        return QR.query(conn, sql, new BeanListHandler<>(clazz), params);
    }

    //获取某一列的内容
    public static Object getValue(String sql, String param) throws SQLException {
        Connection conn = getConnection();
        String[] params = null;
        if(param != null){
            params = param.split(",");
        }
        return QR.query(conn, sql, new ScalarHandler(), params);
    }

//    public static void main(String[] args) throws SQLException {
//        String sql = "select * from user";
//        Connection conn = getConnection();
//        BeanListHandler<User> listHandler = new BeanListHandler<>(User.class);
//        List<User> users = DruidUtils.selectTableList(sql, listHandler, null);
//        for(User s : users){
//            System.out.println(s.toString());
//        }
//    }


//    public static void main(String[] args) throws SQLException {
//        Connection connection = DruidUtils.getConnection();
//        String sql = "select * from user";
//        PreparedStatement statement = connection.prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
//        boolean next = resultSet.next();
//        if (next){
//            System.out.println(resultSet.getString("id"));
//        }
//    }
}
