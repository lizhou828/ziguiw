package com.zigui.exception.handler;

import com.zigui.utils.LogTool;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午10:11
 */
public class FileNotFoundExceptionHandler extends AbstractExceptionHandler {
    public FileNotFoundExceptionHandler(Throwable exception) {
        super(exception);
    }

    @Override
    public void handleException() {
        LogTool.getLog().info("handleException by FileNotFoundExceptionHandler");
        /**
         * code such as reload file ,create file if necessary,email the project manager and so on
         * notice: the code with common function should be in the abstractHandler...
         */
    }
}
