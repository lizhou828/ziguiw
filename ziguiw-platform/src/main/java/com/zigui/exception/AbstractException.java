package com.zigui.exception;

import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午9:40
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractException extends RuntimeException {
    protected ErrorCodeEnum errorCode;

    private AbstractExceptionHandler abstractExceptionHandler;

    public AbstractException(String msg) {
        super(msg);
    }

    public AbstractException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public AbstractException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        this.abstractExceptionHandler = abstractExceptionHandler;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public AbstractExceptionHandler getAbstractExceptionHandler() {
        return abstractExceptionHandler;
    }

    public void setAbstractExceptionHandler(AbstractExceptionHandler abstractExceptionHandler) {
        this.abstractExceptionHandler = abstractExceptionHandler;
    }

    /**
     * appendExceptionMessageToClient add by YeQiang 2012-12-12
     */
    protected boolean appendExceptionMessageToClient = false;

    public boolean isAppendExceptionMessageToClient() {
        return appendExceptionMessageToClient;
    }

    public void setAppendExceptionMessageToClient(boolean appendExceptionMessageToClient) {
        this.appendExceptionMessageToClient = appendExceptionMessageToClient;
    }
}
