package com.zigui.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this filter is used to intercept the illegal uri
 * u can config this filter in the web.xml, like this:
 * 
 * <pre>
 *      &lt filter &gt <br/>
 *          &lt filter-name &gt  URLFilter&lt /filter-name &gt <br/>
 *          &lt filter-class &gt  com.zigui.filter.URLFilter &lt /filter-class &gt <br/>
 *     &lt /filter &gt <br/>
 *     &lt filter-mapping &gt <br/>
 *     &lt filter-name &gt URLFilter&lt /filter-name &gt <br/>
 *          &lt url-pattern &gt *.action&lt /url-pattern &gt  <br/>
 *      &lt /filter-mapping &gt <br/>
 *     
 * </pre>
 * 
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-9-28
 * Time: P.M 5:28
 */
public class URLFilter implements Filter {

    private static final Log log = LogFactory.getLog(URLFilter.class);

    private String[] illegalUris;
    private String tipMessage;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(String.format("init URLFilter, to intercept the illegal url!"));

        /* init the illegalUris */
        String illegalUrisStr = filterConfig.getInitParameter("illegalUris");
        if (illegalUrisStr == null || "".equals(illegalUrisStr)) {
            log.warn("the filter initParameter illegalUrisStr is null");
            illegalUrisStr = "";
        }
        log.info(String.format("the illegalUris value is %s", illegalUrisStr));
        illegalUris = illegalUrisStr.split(",");

        /* init the tipMessage */
        tipMessage = filterConfig.getInitParameter("tipMessage");
        if (tipMessage == null || "".equals(tipMessage)) {
            log.warn("the filter initParameter tipMessage is null");
            tipMessage = "the illegal uri";
        }
        log.info(String.format("the tipMessage value is %s", tipMessage));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        System.out.println("URLFilter's doFilter method is running.....");
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String uri = req.getRequestURI();
        String queryString = req.getQueryString();
        log.info(String.format("access uri is %s", uri));
        boolean isSkip = false;
        for (String s : illegalUris) {
            if (uri.contains(s) || (queryString != null && queryString.contains(s))) {
                isSkip = true;
                break;
            }
        }

        if (isSkip) {
            log.warn(String.format("intercept the illegal url %s", uri));
            String responseContent = String.format("<script> alert('%s');history.go(-1);</script>", tipMessage);
            res.setCharacterEncoding("UTF-8");
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().print(responseContent);
        } else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }


}
