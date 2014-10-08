package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-13
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
public class NotBindClassException extends AbstractException {
    public NotBindClassException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.NOT_BIND_CLASS;//map error code
    }

    public NotBindClassException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public NotBindClassException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
