package com.zigui.dao;

import com.zigui.domain.Student;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-11-23
 * Time: 1:40
 */
public class StudentDaoTest extends SpringTest {

    @Test
    public void testGetStudent() {
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        Student student = studentDao.get(Student.class, 617);
        Assert.assertNotNull(student.getSchool());
        Assert.assertNotNull(student.getSchool().getXxID());
    }

    @Test
    public void testGetUserBase() {
        StudentDao studentDao = (StudentDao) context.getBean("studentDao");
        Student student = studentDao.get(Student.class, 617);
        Assert.assertNotNull(student.getUser());
        Assert.assertNotNull(student.getUser().getNickName());
    }

}
