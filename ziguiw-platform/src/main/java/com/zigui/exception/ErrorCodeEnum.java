package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午9:34
 * To change this template use File | Settings | File Templates.
 */
public enum ErrorCodeEnum {
    /**
     * enumeration list that contains all of defined error
     */

    FILE_NOT_FOUND("1000"),
    USER_NOT_EXIST("1001"),
    NOT_BIND_CLASS("1002"),
    DIRT_WORD_FOUND("1101"),
    PROPERTY_NOT_SET("1102"),
    INSUFFICIENT_PERMISSIONS("1103");


    private String errorCode;
    private String clientExceptionMessage;
    private String redirectUrl;
    private String target;

    public ErrorCodeMap getErrorCodeMap() {
        return errorCodeMap;
    }

    private ErrorCodeMap errorCodeMap;

    ErrorCodeEnum(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getClientExceptionMessage() {
        try {
            return this.errorCodeMap.getErrorCodeData(this).getClientErrorMessage();
        } catch (Exception e) {
            return null;
        }
    }

    public String getRedirectUrl() {
        //read configuration file
        try {
            return this.errorCodeMap.getErrorCodeData(this).getRedirectUrl();
        } catch (Exception e) {
            return null;
        }
    }

    public String getTarget() {
        //read configration file
        try {
            return this.errorCodeMap.getErrorCodeData(this).getTarget();
        } catch (Exception e) {
            return null;
        }
    }

    public void setErrorCodeMap(ErrorCodeMap errorCodeMap) {
        this.errorCodeMap = errorCodeMap;
    }

}
