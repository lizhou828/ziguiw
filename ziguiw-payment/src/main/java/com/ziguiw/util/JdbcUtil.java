package com.ziguiw.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ziguiw.payment.exception.DataAccessException;

/**
 * 
 * @author liujianyu
 *
 */
public final class JdbcUtil {
	
	private static String url;
	private static String username;
	private static String password;
	private static String driver;
	private static String sql = "select * from user";
	private static ComboPooledDataSource dataSource;
	private static Log log = LogFactory.getLog(JdbcUtil.class);

	static{
		dataSource = new ComboPooledDataSource();
	}
	
	public static Connection getMySQLConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
		} else {
			log.warn(String.format("connection is null!"));
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}
	public static void close(ResultSet rs)  {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}
	public static void begin(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}

	public static void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
}