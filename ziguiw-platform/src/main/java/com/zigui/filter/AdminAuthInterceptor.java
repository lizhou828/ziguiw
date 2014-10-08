package com.zigui.filter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zigui.domain.Administrator;
import com.zigui.domain.Authority;
import com.zigui.exception.InsufficientPermissionsException;
import com.zigui.service.impl.AdministratorServiceImpl;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class AdminAuthInterceptor implements Interceptor {

    @Autowired
    private AdministratorServiceImpl administratorService;

    @Override
    public void destroy() {

    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext ctx = ActionContext.getContext();
        String url = ServletActionContext.getRequest().getRequestURI(); //   /admin/login.jsp

        Map session = ctx.getSession();

        if (url.indexOf("login") < 0 && url.indexOf("welcome") < 0 && url.indexOf("logout") < 0) {
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

        String result = actionInvocation.invoke();
        return result;
    }

}
