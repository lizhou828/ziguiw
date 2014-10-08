package com.zigui.filter;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zigui.domain.User;

public class UserAutoLoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request =(HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);            
	    Map session = (Map) request.getSession();  
		
	    if(session.get("VALID_USER") != null){
	    }else{
	    	Cookie[] cookies = request.getCookies();
	    	Cookie userCookie = null;
	    	
	    	if( cookies != null ){
		        for(Cookie cookie:cookies){
		            if(!(cookie.getValue() == null)&&!cookie.getValue().trim().equals("")&&cookie.getName().equals("VALID_USER")){
		            	userCookie = cookie;
		                break;
		            }
		        }
		        
		        if(userCookie != null){
		        	String userStr = URLDecoder.decode(userCookie.getValue(),"UTF-8");
		            try {
		                JSONObject jo = JSONObject.fromObject(userStr);
		                User user = new User();
		                user = (User) JSONObject.toBean(jo, user.getClass());
		                if(user != null && user.getUserName() != null && !user.getUserName().trim().equals("")){
		                	session.put("VALID_USER", user);
		                }
		            }catch (JSONException e) {
		                //clear the wrong cookie
		            	userCookie.setValue("logout");
		                userCookie.setMaxAge(0);
		                userCookie.setPath("/");
		                ServletActionContext.getResponse().addCookie(userCookie);
		            }   
		        }
	    	}
	    }
		
		return invocation.invoke();
	}

}
