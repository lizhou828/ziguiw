package com.zigui.dao;

import com.zigui.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-7
 * Time: 上午11:55
 * To change this template use File | Settings | File Templates.
 */
public class ClassServiceDemo extends  GenericDao{
    private List<Student> studentList=new ArrayList<Student>();
    private String Bj_id;

    public List<Student> getStudentsByBjId(long id){
        return findBy(Student.class,Bj_id,id);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getBj_id() {
        return Bj_id;
    }

    public void setBj_id(String bj_id) {
        Bj_id = bj_id;
    }
}
