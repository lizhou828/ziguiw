package com.zigui.filter;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Administrator;
import com.zigui.domain.Authority;
import com.zigui.exception.InsufficientPermissionsException;
import com.zigui.service.impl.AdministratorServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-24
 * Time: 下午3:43
 */
public class AdminAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ActionContext ctx = ActionContext.getContext();
        if (ctx == null) {
            chain.doFilter(request, response);
            return;
        }
        String url = ServletActionContext.getRequest().getRequestURI(); //   /admin/login.jsp

        Map session = ctx.getSession();

        if (!(url.equals("/admin/login.jsp") || url.equals("/admin/logout.jsp"))) {
            String userName = "null";
            Administrator admin = (Administrator) session.get("ADMIN_USER");
            boolean auth = false;

            if (admin == null) {
                throw new RuntimeException("user not login!");
            } else {
                userName = admin.getNickName();
                if (admin.getNickName().equals("admin") || admin.getNickName().startsWith("admin"))//it's safe when use session
                {
                    auth = true;
                } else {
                    List<Authority> authorityList = admin.getRole().getAuthoritys();
                    for (Authority authority : authorityList) {
                        if (authority.getUrl().equals(url)) {
                            auth = true;
                            break;
                        }
                    }
                }
            }

            if (!auth) {
                throw new InsufficientPermissionsException("User [" + userName + "] try to visit [" + url + "] with no permission");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
