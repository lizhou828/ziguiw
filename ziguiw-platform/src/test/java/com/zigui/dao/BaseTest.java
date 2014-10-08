package com.zigui.dao;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: yeqiang
 * Date: 12-12-4
 * Time: A.M 9:35
 */
public class BaseTest {

    private ApplicationContext context;

    @BeforeClass
    public void init() {
        context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext-*.xml"});
    }

}
