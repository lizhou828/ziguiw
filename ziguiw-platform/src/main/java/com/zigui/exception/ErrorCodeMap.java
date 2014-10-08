package com.zigui.exception;


import com.zigui.utils.LogTool;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-7
 * Time: 下午3:02
 * <p/>
 * this class can get client exception message from configuration file(errorCodeMap.properties)
 * notice: spring bean must config this class as a singleton class!
 */
public class ErrorCodeMap {

    /**
     * this class should have a Interface limit to it's behavior
     */
    private String errorCodeMapFileName = "/errorCodeMap.xml";

    private final String TAG_ERROR_CODE = "errorCode";
    private final String TAG_CLIENT_ERROR_MESSAGE = "clientErrorMessage";
    private final String TAG_REDIRECT_URL = "redirectUrl";
    private final String TAG_TARGET = "target";

    private HashMap map = null;

    private static ReentrantLock lock = new ReentrantLock();

    private static ErrorCodeMap instance = null;

    private ErrorCodeMap() {
        initMap();
    }

    /**
     * read the configuration file and init the map
     */
    protected void initMap() {
        try {
            LogTool.getLog().info("Init map");
            map = new HashMap();
            SAXReader saxReader = new SAXReader();
            InputStream input = this.getClass().getResourceAsStream(errorCodeMapFileName);
            Document document = saxReader.read(input);
            Element rootElement = document.getRootElement();
            for (Iterator i = rootElement.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                ErrorCodeData data = new ErrorCodeData();
                data.setErrorCode(element.element(TAG_ERROR_CODE).getText());
                data.setClientErrorMessage(element.element(TAG_CLIENT_ERROR_MESSAGE).getText());
                data.setRedirectUrl(element.element(TAG_REDIRECT_URL).getText());
                data.setTarget(element.element(TAG_REDIRECT_URL).getText());
                map.put(data.getErrorCode(), data);
            }
        } catch (Exception e) {
            LogTool.getLog().error("ErrorCodeMap init failed:\t" + e.getMessage(), e);
        }
    }

    public ErrorCodeData getErrorCodeData(ErrorCodeEnum errorCode) {
        try {
            return (ErrorCodeData) map.get(errorCode.getErrorCode());
        } catch (Exception e) {
            LogTool.getLog().error(e.getMessage(), e);
            return null;
        }
    }

    public static ErrorCodeMap getInstance() {
        if (instance == null) {
            lock.lock();
            if (instance == null) {
                instance = new ErrorCodeMap();
            }
            lock.unlock();   //may be not perfect
        }
        return instance;
    }
}
