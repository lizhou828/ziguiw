package com.zigui.filter;

import com.zigui.domain.UserBase;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.BeanFactoryUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-10-16
 * Time: 下午4:11
 *
 * The filter can prevent user repeat login
 */
public class UserRepeatLoginFilter implements Filter {

    private UserServiceImpl userService = (UserServiceImpl)BeanFactoryUtils.getBean("userService");

    private FilterConfig filterConfig;

    private static final Log log = LogFactory.getLog(UserRepeatLoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
          this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            log.info("UserRepeatLoginFilter's doFilter method is running.....");
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res  = (HttpServletResponse)response;

            String url = req.getRequestURI();
            url = url.substring(url.lastIndexOf("/"));
            HttpSession session = req.getSession();
            UserBase user = (UserBase)session.getAttribute("VALID_USER");


            boolean  flag ;
            log.info(String.format("The url is %s",url));
            if(url.equals("/login.jsp") || url.equals("/register.jsp")||(user == null)||(user.getSessionId()==null)){
                flag = false;
            }else if( !(session.getId().equals(userService.getUserBaseByNickName(user.getNickName()).getSessionId()))){
                flag = true;
                session.setAttribute("userRepeatLogin","xxx");
            }else{
               log.info(String.format("Database sessionId is :%s",userService.getUserBaseByNickName(user.getNickName()).getSessionId()));
                flag = false;

            }

            log.info(String.format("Session id = %s",session.getId()));
            log.info(String.format("user = ",user));
            log.info(String.format("This flag = %s",flag));
            if(flag){
               res.sendRedirect("/user/login.jsp");
            } else {
                chain.doFilter(request, response);
            }
    }

    @Override
    public void destroy() {

    }
}
