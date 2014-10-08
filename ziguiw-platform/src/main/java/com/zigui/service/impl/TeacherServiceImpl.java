package com.zigui.service.impl;

import com.zigui.dao.TeacherDao;
import com.zigui.domain.Student;
import com.zigui.domain.Teacher;
import com.zigui.exception.UserNotExistException;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-17
 * Time: pm 3:51
 */
public class TeacherServiceImpl {
    @Autowired
    private TeacherDao teacherDao;
    private Student student=new Student();

    private String hql;
    private String countPerPage="15";
    private String currentPage = "1";
    private Object[] queryObj = new Object[0];

    private Page<Student> studentPage=new Page<Student>();

    private String queryString;
    private CommonServiceImpl commonService=new CommonServiceImpl();

    public Teacher getById(long id ){
        try{
            return teacherDao.get(Teacher.class,id);
        }catch (Exception e){
            throw new UserNotExistException("没有该老师信息或未绑定学校或班级！");
        }
    }

    //分页查询
    public Page<Student> getStudentsByClassId(long clazzId) {
        hql="select s from Student s where s.Bj_ID="+clazzId;
       Page<Object>  objPage = commonService.getByHql(hql, new Integer(currentPage).intValue(),
                new Integer(countPerPage).intValue(), queryString, queryObj);
        studentPage=CommonUtils.transform(Student.class,objPage);

        return studentPage;
    }
    //分页查询
    public Page<Student> getStudentsByClassId(long bj_id, Integer gotoPage) {
        hql="select s from Student s where s.Bj_ID="+bj_id;
        Page<Object>  objPage = commonService.getByHql(hql, new Integer(gotoPage).intValue(),
                new Integer(countPerPage).intValue(), queryString, queryObj);
        studentPage=CommonUtils.transform(Student.class,objPage);

        return studentPage;
    }



    public String getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(String countPerPage) {
        this.countPerPage = countPerPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Object[] getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(Object[] queryObj) {
        this.queryObj = queryObj;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public void setTeacherDao(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public CommonServiceImpl getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonServiceImpl commonService) {
        this.commonService = commonService;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }


}
