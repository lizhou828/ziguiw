package com.ziguiw.dao;

import com.ziguiw.payment.exception.DataAccessException;
import com.ziguiw.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

class BaseDao {
	
	void excute(JdbcCallback callback) {
		Connection connection = JdbcUtil.getMySQLConnection();
		JdbcUtil.begin(connection);
		callback.exec(connection);
		try {
			connection.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(connection);
			throw new DataAccessException(e);
		} finally {
			JdbcUtil.close(connection);
		}
	}
}
