package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-12
 * Time: 下午4:16
 */
public class DirtWordFoundException extends AbstractException {
    public DirtWordFoundException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.DIRT_WORD_FOUND;
    }

    public DirtWordFoundException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public DirtWordFoundException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
