package com.zigui.exception;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-10
 * Time: 上午11:57
 * To change this template use File | Settings | File Templates.
 */
public class ErrorCodeData {
    private String errorCode;
    private String clientErrorMessage;
    private String redirectUrl;
    private String target;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getClientErrorMessage() {
        return clientErrorMessage;
    }

    public void setClientErrorMessage(String clientErrorMessage) {
        this.clientErrorMessage = clientErrorMessage;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
