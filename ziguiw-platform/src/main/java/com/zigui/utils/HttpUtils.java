package com.zigui.utils;

import com.zigui.exception.ErrorCode;
import com.zigui.exception.QueryBusinessDataException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-10-18
 * Time: A.M 10:05
 */
public class HttpUtils {

    public static String getResponse(String url, String encoding) {
        GetMethod getMethod = new GetMethod(url);
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.executeMethod(getMethod);
            byte[] responseBody = getMethod.getResponseBody();
            String responseStr=new String(responseBody,encoding);
            return  responseStr;
        } catch (IOException e) {
            throw new QueryBusinessDataException(e, ErrorCode.RPC);
        }

    }
}
