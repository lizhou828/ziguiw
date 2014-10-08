package com.zigui.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Lizhou
 * Date: 12-9-21
 * Time: am9:49.
 */
public class AntiSQLInjectFilter implements Filter{

    private String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|" +
            "truncate|char|declare|; |or|-|+|,";
    protected FilterConfig filterConfig = null;
    protected boolean ignore = true;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    @Override
    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
       System.out.println("AntiSQLInjectFilter's doFilter method is running..");
        HttpServletRequest req = (HttpServletRequest)request;
        Iterator values = req.getParameterMap().values().iterator();//获取所有的表单参数

        while(values.hasNext()){
            String[] value = (String[])values.next();

            for(int i = 0;i < value.length;i++){

                if(sql_inj(value[i])){

                    throw new IOException("系统提示:请不要在参数中包含非法字符尝试注入");

                }
            }
        }
        chain.doFilter(request, response);
    }

    public boolean sql_inj(String str){

        String[] inj_stra=inj_str.split("\\|");
        for (int i=0 ; i < inj_stra.length ; i++ ){
            if (str.indexOf(inj_stra[i])>=0){
                return true;
            }
        }
        return false;
    }

}
