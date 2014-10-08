package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-29
 * Time: 下午2:41
 */
public class UnLoginException extends BaseException {

    public UnLoginException(int errorCode) {
        super(errorCode);
    }
}
