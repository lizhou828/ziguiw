package com.zigui.filter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zigui.domain.UserBase;

public class SourceInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// 当前登录的用户
		UserBase user = (UserBase) ActionContext.getContext().getSession().get("VALID_USER");

		// 当前访问的权限URL（namespaceName + actionName，去掉最前面的'/'）
		String namespaceName = arg0.getProxy().getNamespace();
		String actionName = arg0.getProxy().getActionName();

		String privilegeUrl = namespaceName + actionName;

		// 1，如果未登录
		if (user == null) {
			if(privilegeUrl.contains("addsourceGet") || privilegeUrl.contains("downLoad")){
				return "fail";
			}
		}
		return arg0.invoke();
	}

}
