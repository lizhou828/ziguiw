package com.zigui.utils;

/**
 * 系统工具类 - html数据处理
 * 
 * @copyright 湖南爱瑞杰科技发展股份有限公司
 * @author sly
 * @version 1.0
 * @since 2011年8月19日11:25:07
 */
public class HtmlUtil {
	/** 
     * 删除input字符串中的html格式 
     *  
     * @param input 要处理的字符串
     * @param length  需要截取的长度
     * @return 
     */  
    public static String splitAndFilterString(String input, int length) {  
        if (input == null || input.trim().equals("")) {  
            return "";  
        }  
        // 去掉所有html元素,  
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
                "<[^>]*>", "");  
        str = str.replaceAll("[(/>)<]", "");  
        //传入-1时返回所有
        if(length == -1){
        	return str;
        }
        int len = str.length();  
        if (len <= length) {  
            return str;  
        } else {  
            str = str.substring(0, length);  
            str += "......";  
        }  
        return str;  
    }  
}
