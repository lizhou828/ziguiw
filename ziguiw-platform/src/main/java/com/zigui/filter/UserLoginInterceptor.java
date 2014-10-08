package com.zigui.filter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import org.apache.struts2.ServletActionContext;

import java.util.Map;

public class UserLoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String url = ServletActionContext.getRequest().getRequestURI();
        Map session = ctx.getSession();
	    
	    if (url!= null && url.contains("login")) {
	    	// 如果请求login页面，将请求前的页面链接放入session，然后放行
	    	System.out.println("This is login action.");
            return actionInvocation.invoke();
	    }
	    if (session != null && session.get("VALID_USER") != null ) {
            System.out.println("Already login!");
            return actionInvocation.invoke();
        } else {
            // 终止后续操作，返回LOGIN
            System.out.println("Forward login page!");
//            ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/user/login.jsp");
            return "USER_NO_LOGIN";
        }  
	}  


}
