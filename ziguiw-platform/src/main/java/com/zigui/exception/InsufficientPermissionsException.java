package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-24
 * Time: 下午3:55
 * To change this template use File | Settings | File Templates.
 */
public class InsufficientPermissionsException extends AbstractException {
    public InsufficientPermissionsException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.INSUFFICIENT_PERMISSIONS;
    }

    public InsufficientPermissionsException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public InsufficientPermissionsException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
