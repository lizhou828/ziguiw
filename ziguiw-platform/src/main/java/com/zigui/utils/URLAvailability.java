package com.zigui.utils;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;


public class URLAvailability {
	

	private static Logger logger = Logger.getLogger(URLAvailability.class);

	private static URL urlStr;

	private static HttpURLConnection connection;

	private static int state = -1;

	private static String succ;

	/**  
     * 功能：检测当前URL是否可连接或是否有效,  
     * 描述：最多连接网络 3 次, 如果 3 次都不成功，视为该地址不可用  
     * @param  urlStr 指定URL网络地址  
     * @return URL  
     */  
    public static synchronized boolean isConnect(String url) {
    	final int NUM = 1;//请求次数
        int counts = 0;   
        succ = "";   
        if (url == null || url.length() <= 0) {                          
            return false;                    
        }   
        while (counts < NUM) {   
            long start = 0;   
            try {   
                urlStr = new URL(url);   
                start = System.currentTimeMillis();   
                connection = (HttpURLConnection) urlStr.openConnection();   
                state = connection.getResponseCode();   
                logger.info("请求断开的URL一次需要："+(System.currentTimeMillis()-start)+"毫秒");   
                if (state == 200) {   
                    succ = "ok";
                    logger.info(urlStr+"--可用");
                    return true;
                }   
                break;   
            }catch (Exception ex) {   
                counts++;    
                logger.info("请求断开的URL一次需要："+(System.currentTimeMillis()-start)+"毫秒");   
                logger.info("连接第 "+counts+" 次，"+urlStr+"--不可用");   
                continue;   
            }   
        }
        return false;
    }  

	
	public static void main(String[] args) {
		System.out.println(isConnect("http://www.agfh.com"));
	}
}
