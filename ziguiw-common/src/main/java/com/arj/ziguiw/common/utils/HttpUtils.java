package com.arj.ziguiw.common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-15
 * Time: A.M 10:50
 */
public class HttpUtils {

    public static String getResponse(String url, String encoding) {
        GetMethod getMethod = new GetMethod(url);
        HttpClient httpClient = new HttpClient();
        try {
            httpClient.executeMethod(getMethod);
            byte[] responseBody = getMethod.getResponseBody();
            String responseStr = new String(responseBody,encoding);
            return  responseStr;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
