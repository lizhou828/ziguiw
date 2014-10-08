package com.zigui.filter;

import com.zigui.exception.DirtWordFoundException;
import com.zigui.utils.LogTool;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-12
 * Time: 下午4:21
 * this class will replace the WordFilter
 */
public class DirtWordFilter implements Filter {

    private static Properties keywords = null;

    private static String[] filterUris = null;

    /* this filter is enabled */
    private static boolean enabled = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        /**
         * those codes should defined in a Utils class
         */
        if (filterConfig.getInitParameter("enabled").equals("true")) {
            enabled = true;
        }
        if (enabled) {
            keywords = new Properties();
            String file = filterConfig.getInitParameter("keywords");
            if (file == null) {
                LogTool.getLog().warn(String.format("the parameter keywords is %s ", file));
            } else {
                String realPath = filterConfig.getServletContext().getRealPath(file);
                try {
                    keywords.load(new FileInputStream(realPath));
                } catch (IOException e) {
                    LogTool.getLog().error(String.format("load the keyword.properties failed! the file path is %s", realPath), e);
                }
            }

            try {
                String filterUri = filterConfig.getInitParameter("filterUri");
                if (filterUri != null && filterUri.length() > 0) {
                    filterUris = filterUri.split(",");
                }
            } catch (Exception e) {
                LogTool.getLog().error("filterUris Init failed\t", e);
            }
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * if too deep!!!
         */
        if (isNeedToFilter(request)) {
            LogTool.getLog().info("need to filter");
            Collection values = request.getParameterMap().values();
            if (values != null && values.size() > 0) {
                for (Iterator iterator = values.iterator(); iterator.hasNext(); ) {
                    String clientInput = null;
                    try {
                        clientInput = ((String[]) iterator.next())[0];
                        //Chinese garbled when use http get method!!!this is a bug,fix later
                        clientInput = URLDecoder.decode(clientInput, "UTF-8");   //useless
                    } catch (Exception e) {
                        e.printStackTrace();
                        //the parameter is not a string type,let it go
                    }
                    for (Iterator<Object> iterator1 = keywords.keySet().iterator(); iterator1.hasNext(); ) {
                        Object o = iterator1.next();
                        String dirtWord = "";
                        try {
                            dirtWord = (String) o;
                        } catch (Exception e) {
                            //this exception do not need the exception framework to handle
                            LogTool.getLog().error("found a dirtWord that is not a String type!");
                        }
                        if (clientInput != null && dirtWord != null && clientInput.contains(dirtWord)) {
                            LogTool.getLog().info(String.format("found a dirtWord  %s in %s", dirtWord, clientInput));
                            DirtWordFoundException dirtWordFoundException = new DirtWordFoundException(dirtWord);
                            dirtWordFoundException.setAppendExceptionMessageToClient(true);
                            throw dirtWordFoundException;
                        }
                    }
                }
            }
        }
        //LogTool.getLog().info("not filtered");
        chain.doFilter(request, response);
    }

    private boolean isNeedToFilter(ServletRequest request) {

        if (!enabled && keywords == null) {
            return false;
        }

        /**
         * check the configured url
         */
        HttpServletRequest httpServletRequest = null;
        try {
            httpServletRequest = (HttpServletRequest) request;
        } catch (Exception e) {
            //not a httpServletRequest
        }
        if (filterUris != null && httpServletRequest != null) {
            String requestUri = httpServletRequest.getRequestURI();
            for (String uri : filterUris) {
                if (!uri.startsWith("/")) {
                    uri = "/" + uri;
                }
                if (uri.equals(requestUri)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}
