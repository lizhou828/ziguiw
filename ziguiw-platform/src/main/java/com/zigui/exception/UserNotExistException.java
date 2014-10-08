package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-12-12
 * Time: P.M.4:09
 */
public class UserNotExistException extends AbstractException {

    public UserNotExistException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.USER_NOT_EXIST;//map error code
    }

    public UserNotExistException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public UserNotExistException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
