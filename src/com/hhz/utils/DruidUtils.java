package com.hhz.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class DruidUtils {
	//{1}声明一个静态属性 ds [数据源]
	private static DataSource ds;

	//{ps}声明一个线程局部变量。
	//    以后每一个用户线程使用一个数据库连接。[每一请求独享一个连接]
	//    每一个业务操作专用一个连接，一个事务。
	private static ThreadLocal<Connection> localVar = new ThreadLocal<>();

	static {
		//{1}获取到当前类的类装载器
		ClassLoader loader = DruidUtils.class.getClassLoader();
		//{2}获取源目录下的文件的输入流
		InputStream is = loader.getResourceAsStream(
				"druid.properties" );
		Properties prop = new Properties();
		try {
			prop.load( is );   //载入输入流
			ds = DruidDataSourceFactory
					.createDataSource( prop );
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection()
			throws SQLException{
		//{1}先从局部容器中先获取连接 【本地小仓库】
		Connection conn = localVar.get();
		//{2}没有再从【总仓库】获取连接
		if( conn==null ){
			conn = ds.getConnection();
			//{3}再扔进小仓库中
			localVar.set( conn );
		}
		return conn;
	}

	//{ps} 关闭连接。[可以不用]
	public static void closeConnection()
			throws SQLException {
		//{1}先从局部容器中先获取连接 【本地小仓库】
		Connection conn = localVar.get();
		if( conn!=null ){
			//{2}关闭连接。
			conn.close();
			//{3}从小仓库中移除这个连接。
			localVar.remove();
		}
	}

	//{ps}关闭资源: RS, PSMT, SMT, Conn。
	public static void closeRES(AutoCloseable...res)
			throws SQLException {
		try{
			for (AutoCloseable R : res) {
				R.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException("关闭资源失败。");
		}
	}

	private static QueryRunner QR = new QueryRunner();

	//{1}增， 删， 改方法。[三合一]
	public static int update(String sql, String param)
			throws SQLException {
		Object[] params = null;
		if(param != null){
			params = param.split(",");
		}
		Connection conn = getConnection();
		return QR.update(conn, sql, params);
	}

	//{2}查询单体对象方法。
	//  区别: BeanHandler()<User.class>
	public static <E> E queryObject(String sql,
									Class<E> clazz, String param)
			throws SQLException {
		Object[] params = null;
		if(param != null){
			params = param.split(",");
		}
		//{1}可以从 ThreadLocal 取连接。(后面)
		//   也可以从 ds 数据源中取连接。(第一次)
		Connection conn = getConnection();
		return QR.query( conn, sql,
				new BeanHandler<>(clazz), params);
	}

	//{3}查询对象列表方法。
	//   区别: BeanListHandler()<User.class>
	public static <E> List<E> queryList(
			String sql, Class<E> clazz, String param)
			throws SQLException {
		Object[] params = null;
		if(param != null){
			params = param.split(",");
		}
		Connection conn = getConnection();
		return QR.query( conn, sql, new BeanListHandler<>(clazz), params );
	}

	//{4}查询一个返回值方法。
	//   区别: ScalarHandler()
	public static Object getValue( String sql,
								   String param ) throws SQLException {
		Connection conn = getConnection();
		Object[] params = null;
		if(param != null){
			params = param.split(",");
		}
		return QR.query( conn, sql,
				new ScalarHandler(), params);
	}

}
