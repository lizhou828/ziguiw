package com.zigui.utils;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-18
 * Time: A.M 10:04
 */
public class HttpUtilsTest {

    @Test
    public void getResponse() {
        String url = "http://localhost:8088/basedata/jsonDataDemo.jsp";
        String content = HttpUtils.getResponse(url, "UTF-8");
        Assert.assertNotNull(content);
        System.out.println(content);

    }

}
