package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-21
 * Time: 上午10:02
 * To change this template use File | Settings | File Templates.
 */
public class PropertyNotSetException extends AbstractException {
    public PropertyNotSetException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.PROPERTY_NOT_SET;
    }

    public PropertyNotSetException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public PropertyNotSetException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
