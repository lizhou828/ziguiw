package com.zigui.service.impl;

import com.zigui.utils.ConfigUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URLEncoder;

public class SmsServiceImpl {
    private String query = ConfigUtils.getProperty("sms.url");
    private static final Log log = LogFactory.getLog(SmsServiceImpl.class);

    public String sendSms(String mobile, String content) {
        try {
            String logQuery  = String.format(query, mobile,content);
            log.info(String.format("SMS url is = %s", logQuery));
            content = URLEncoder.encode(content, "GBK");
            query = String.format(query, mobile,content);
//            PostMethod postMethod = new PostMethod(query){
//                public String getRequestCharSet(){
//                    return "UTF-8";
//                }
//            };
//            NameValuePair valuePairContent = new NameValuePair("content",content);
//            NameValuePair[] pairs = new NameValuePair[]{valuePairContent};
//            postMethod.setRequestBody(pairs);
            GetMethod getMethod = new GetMethod(query);
            HttpClient htpc = new HttpClient();
            htpc.setConnectionTimeout(3000);
            htpc.setTimeout(3000);
            htpc.executeMethod(getMethod);
            byte[] responseBody = getMethod.getResponseBody();
            if (responseBody == null) {
                return "-1";
            }
            String responseStr = new String(responseBody, "UTF-8");
            log.info(String.format("send sms result is %s", responseStr));
            return responseStr;
        } catch (Exception e) {
            log.error("send sms error!", e);
        }
        return "-1";
    }


}
