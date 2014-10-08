package controllers;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-22
 * Time: P.M 5:59
 */
public class DsisException extends RuntimeException {

    public boolean isAjax;

    public String tipMessage;

    public DsisException(Exception e, boolean isAjax){
        super(e);
        this.isAjax = isAjax;
    }

    public DsisException(String tipMessage, Exception e, boolean isAjax){
        super(e);
        this.tipMessage = tipMessage;
        this.isAjax = isAjax;
    }

    public DsisException(String message, Exception e){
        super(message, e);
    }

    public DsisException(Exception e){
        super(e);
    }

    public DsisException(String tipMessage) {
        this.tipMessage = tipMessage;
    }
}
