package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-18
 * Time: A.M 9:40
 */
public class BaseException extends RuntimeException {

    /* the exception code, like http exception code : 404 500 502 */
    int errorCode;

    /* the exception handler */
    ExceptionHandler handler;


    public BaseException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BaseException(String message, int errorCode, ExceptionHandler handler) {
        super(message);
        this.errorCode = errorCode;
        this.handler = handler;
    }

    public BaseException(Throwable cause, int errorCode, ExceptionHandler handler) {
        super(cause);
        this.errorCode = errorCode;
        this.handler = handler;
    }

    public BaseException(String message, Throwable cause, int errorCode, ExceptionHandler handler) {
        super(message, cause);
        this.errorCode = errorCode;
        this.handler = handler;
    }

    public BaseException(int errorCode, ExceptionHandler handler, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.handler = handler;
    }

    public BaseException(ExceptionHandler handler) {
        this.handler = handler;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int errorCode) {
        this.errorCode = errorCode;
    }
}
