package com.zigui.exception;


import com.zigui.exception.handler.AbstractExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-7
 * Time: P.M 3:43.
 */
public class FileNotFoundException extends AbstractException {

    public FileNotFoundException(String msg) {
        super(msg);
        super.errorCode = ErrorCodeEnum.FILE_NOT_FOUND;//map error code
    }

    /**
     * you can overwrite the errorCode by this function,to map different error message
     * @param errorCode  ErrorCodeEnum
     */
    public FileNotFoundException(ErrorCodeEnum errorCode) {
        super(errorCode);
    }

    public FileNotFoundException(String s, AbstractExceptionHandler abstractExceptionHandler) {
        super(s, abstractExceptionHandler);
    }
}
