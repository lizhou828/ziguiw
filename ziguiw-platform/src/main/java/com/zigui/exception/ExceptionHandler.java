package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-18
 * Time: A.M 9:44
 */
public interface ExceptionHandler {


    /**
     * this is a callback function, return the tipMessage after handled exception
     * @return tipMessage
     */
    String handle(Object...objects);

}
