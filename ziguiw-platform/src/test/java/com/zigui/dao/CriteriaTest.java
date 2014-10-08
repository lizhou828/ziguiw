package com.zigui.dao;

import com.zigui.domain.ClassNews;
import com.zigui.utils.LogTool;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-24
 * Time: 上午11:41
 * To change this template use File | Settings | File Templates.
 */
public class CriteriaTest {
    @Test
    public void test()
    {
        //load spring
        ApplicationContext spring = new ClassPathXmlApplicationContext(new String[]{"classpath*:applicationContext-*.xml"});

        HibernateTemplate hibernateTemplate = (HibernateTemplate)spring.getBean("hibernateTemplate");

        Session session = hibernateTemplate.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(ClassNews.class);

        criteria =  criteria
                .addOrder(Order.desc("id"))
                .setFirstResult(0)
                .setMaxResults(2);

        List<ClassNews> list = criteria.list();

        for(ClassNews classNews : list)
        {
            LogTool.getLog().info(classNews.getTitle() + "\t" + classNews.getCreate_time());
        }

        LogTool.getLog().info(list.size());
    }
}
