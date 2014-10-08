package com.arj.ziguiw.datasynch;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liumj
 * Date: 12-11-8
 * Time: P.M 2:15
 */
public class RsRpcManagerTest {

    ApplicationContext context;


    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext(new String[]{"classpath*:com/arj/ziguiw/datasynch/*.xml"});
    }

    @Test
    public void saveParent() throws SQLException {
        RsRpcManager rsRpcManager = (RsRpcManager) context.getBean("rsRpcManager");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PARENTS_ID", 1);
        rsRpcManager.saveParent(map);
        System.out.println(System.getProperty("user.dir"));
    }

}
