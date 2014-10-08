package com.ziguiw.payment.exception;

import java.sql.SQLException;

public class DataAccessException extends RuntimeException {

	public DataAccessException(Throwable e) {
		super(e);
	}

}
