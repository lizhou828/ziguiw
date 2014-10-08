package com.zigui.exception.handler;

import com.zigui.exception.AbstractException;
import com.zigui.utils.LogTool;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午9:41
 */
public abstract class AbstractExceptionHandler {

    protected Throwable exception;

    protected AbstractException abstractException;

    public AbstractExceptionHandler(Throwable exception)
    {
        /**
         * get root cause
         */
        while(exception.getCause() != null)
        {
            exception = exception.getCause();
        }

        this.exception = exception;

        try {
            this.abstractException = (AbstractException)exception;
        } catch (Exception e) {
            LogTool.getLog().info(e.getMessage());
            abstractException = null;
        }
    }

    public abstract void handleException();

    public AbstractException getAbstractException() {
        return abstractException;
    }
}
