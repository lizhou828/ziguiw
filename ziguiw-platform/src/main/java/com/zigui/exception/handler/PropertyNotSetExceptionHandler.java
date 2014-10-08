package com.zigui.exception.handler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-21
 * Time: 上午10:04
 */
public class PropertyNotSetExceptionHandler extends AbstractExceptionHandler {
    public PropertyNotSetExceptionHandler(Throwable exception) {
        super(exception);
    }

    @Override
    public void handleException() {
        //nothing to do
    }
}
