package com.zigui.dao;

import com.zigui.utils.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-11-21
 * Time: A.M 11:09
 */
public class DsisDaoTest extends BaseTest{

    private DsisDao dsisDao;

    @Test
    public void queryStudentComments() {
        Page page = dsisDao.queryStudentComments(1, 5, null, null, null, null);
        Assert.assertNotNull(page);
    }

}
