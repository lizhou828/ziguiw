package com.zigui.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CookieUtils {
	
	public static String getCookie(Cookie[] allCookies, String cookieName){
		String cookieValue = "";
		
		if(allCookies!=null&&allCookies.length!=0)
		 {
		     for(int i=0;i<allCookies.length;i++)
		     {
		         String keyName=  allCookies[i].getName();
		         
		         if(keyName.endsWith(cookieName)){
		        	 cookieValue =  allCookies[i].getValue();
		         }
		   
		      }
		 }
		
		return cookieValue;
	}
	
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue){
		try {
			Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
			cookie.setMaxAge(60*60*24*365);
            //cookie.setDomain("ziguiw.com");
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}

}
