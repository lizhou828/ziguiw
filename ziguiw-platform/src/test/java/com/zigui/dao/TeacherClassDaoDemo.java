package com.zigui.dao;

import com.zigui.domain.TeacherClasz;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-7
 * Time: 上午11:37
 * To change this template use File | Settings | File Templates.
 */
public class TeacherClassDaoDemo extends GenericDao<TeacherClasz> {
    // TeacherClaszDao类的方法
    private String teacherID;
    public  List<TeacherClasz> getByTeacherId(long id){
        List<TeacherClasz> teacherClaszList= findBy(TeacherClasz.class,teacherID,id);
        return teacherClaszList;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }
}
