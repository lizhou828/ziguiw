package com.zigui.dao;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-11-23
 * Time: P.M 1:42
 */
public class SpringTest {

    protected static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext-*.xml"});
    }
}
