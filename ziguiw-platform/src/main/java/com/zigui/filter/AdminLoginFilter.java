package com.zigui.filter;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		ActionContext ctx = ActionContext.getContext();
		String url = ServletActionContext.getRequest().getRequestURI();
        Map session = ctx.getSession();
        //sessionUser 如果session中不存在用户在返回index视图
        if (session.get("ADMIN_USER") == null && url.indexOf("login") < 0) {
        	response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
        	return;
        }
        
        chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
