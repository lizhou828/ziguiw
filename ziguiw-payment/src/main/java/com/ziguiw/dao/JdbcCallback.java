package com.ziguiw.dao;

import java.sql.Connection;

public interface JdbcCallback {

	Object exec(Connection connection);
}
