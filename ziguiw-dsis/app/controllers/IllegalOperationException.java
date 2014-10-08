package controllers;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-17
 * Time: P.M 3:03
 */
class IllegalOperationException extends DsisException {

    IllegalOperationException(Exception e, boolean isAjax) {
        super(e, isAjax);
    }

    IllegalOperationException(String tipMessage, Exception e, boolean isAjax) {
        super(tipMessage, e, isAjax);
    }

    IllegalOperationException(String message, Exception e) {
        super(message, e);
    }

    IllegalOperationException(String tipMessage) {
        super(tipMessage);
    }

    IllegalOperationException() {
        super("非法操作");
    }
}
