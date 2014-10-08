package com.zigui.exception.handler;

import com.zigui.exception.AbstractException;
import com.zigui.utils.LogTool;

import static java.lang.Class.forName;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public class DefaultExceptionHandler extends AbstractExceptionHandler {

    public DefaultExceptionHandler(Throwable exception) {
        super(exception);
    }

    @Override
    public void handleException() {
        try {
            AbstractExceptionHandler handler = super.abstractException.getAbstractExceptionHandler();
            if (handler == null) {
                String className = super.abstractException.getClass().getPackage().getName() + ".handler." + super.abstractException.getClass().getSimpleName() + "Handler";
                /**
                 * use java reflection to init target handler(mapping config file is not needed!)
                 */
                handler = (AbstractExceptionHandler) forName(className).getConstructor(Throwable.class).newInstance(super.abstractException);
            }
            handler.handleException();
        } catch (Exception e) {
            LogTool.getLog().info("Concrete exception not handled, use default handle function");

            handleDefault();
        }
    }

    private void handleDefault() {
        /**
         * 1.Log Exception information
         * 2.View exception message to client
         */
        LogTool.getLog().error("Unhandled Exception(default view:error.jsp): ", super.exception);

        /**
         * step 2 will responsible by error.jsp(tomcat default)
         */
    }
}
