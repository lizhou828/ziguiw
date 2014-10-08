package com.zigui.filter;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AdminLoginInterceptor implements Interceptor{

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String url = ServletActionContext.getRequest().getRequestURI();
        Map session = ctx.getSession();
        // sessionUser 如果session中不存在用户在返回index视图
        if (session.get("ADMIN_USER") == null && url.indexOf("login") < 0) {
            return "ADMIN_NO_LOGIN";
        }
        String result = actionInvocation.invoke();
        return result;
	}

}
