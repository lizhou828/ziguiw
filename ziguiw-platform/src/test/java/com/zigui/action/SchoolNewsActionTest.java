package com.zigui.action;

import com.zigui.utils.LogTool;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 13-1-4
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public class SchoolNewsActionTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext-*.xml"});
        SchoolNewsAction schoolNewsAction = (SchoolNewsAction) context.getBean("schoolNewsAction");
        schoolNewsAction.setId(581);
        schoolNewsAction.getById();
        LogTool.getLog().info("ok");
    }
}
