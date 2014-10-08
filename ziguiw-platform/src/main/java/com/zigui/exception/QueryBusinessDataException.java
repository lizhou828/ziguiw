package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-18
 * Time: A.M 9:39
 */
public class QueryBusinessDataException extends BaseException {

    public QueryBusinessDataException(Throwable cause, int errorCode) {
        super(cause, errorCode);
    }
}
