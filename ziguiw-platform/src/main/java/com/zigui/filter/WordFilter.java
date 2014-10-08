package com.zigui.filter;

import com.zigui.utils.CharResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-10-22
 * Time: 上午10:14
 */
public class WordFilter implements Filter {

    private static Properties keywords = new Properties();

    /* the regex to match the request uri */
    private String replacedUriRegex = "(community/\\w+/list)||(community/\\w+/detail/\\d+)||(study/\\w+/list)||(myhome/\\w+/diary/\\d+)||(user/portal\\.jsp)";

    private static final Log log = LogFactory.getLog(WordFilter.class);

    /* this filter is enabled */
    private boolean disable;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String file = filterConfig.getInitParameter("keywords");
        if (file == null) {
            log.warn(String.format("the parameter keywords is %s ",file));
        } else {
            String realPath = filterConfig.getServletContext().getRealPath(file);
            try {
                keywords.load(new FileInputStream(realPath));
            } catch (IOException e) {
                log.error(String.format("load the keyword.properties failed! the file path is %s", realPath), e);
            }
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        int idx = uri.lastIndexOf(".");
        if ((idx == -1 || uri.contains(".jsp") || uri.contains(".action"))&&(!uri.contains("kiss"))) {
            CharResponseWrapper res = new CharResponseWrapper((HttpServletResponse) response);
            chain.doFilter(request, res);
            String output = res.getCharArrayWriter().toString();
            for (Object obj : keywords.keySet()) {
                if(output.contains((String)(obj))){
                     log.info(String.format("被过滤的关键字是：%s",(String)obj));
                     output = output.replace((String) obj, "***");
                }

            }

            PrintWriter out = response.getWriter();
            out.write(output);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
