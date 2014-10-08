package com.zigui.dao;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-7
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */

import com.zigui.domain.Clasz;
import com.zigui.domain.Student;
import com.zigui.domain.TeacherClasz;
import com.zigui.service.impl.ClassServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据老师的id查询所有学生
 * 统计学生人数
 * 学生对象的集合
 * */

 public class GetStudentsByTeacherDao {

    @Test
    public  static  void main(){
        Clasz clasz=new Clasz();
        List<Clasz> claszList=new ArrayList<Clasz>();
        TeacherClasz teacherClasz=new TeacherClasz();
        TeacherClaszDao teacherClaszDao=new TeacherClaszDao();
        String teacherID;
        List<TeacherClasz> teacherClaszList=new ArrayList<TeacherClasz>();
        List<Student> studentList=new ArrayList<Student>();
        List<List> listList=new ArrayList<List>();

        long teacherId=46542;

        TeacherClassDaoDemo teacherClassDaoDemo=new TeacherClassDaoDemo();
        teacherClaszList=teacherClassDaoDemo.getByTeacherId(teacherId);
        List bjids=new ArrayList();
        long bjid=0L;
        for(int i=0;i<teacherClaszList.size();i++){

            bjid=teacherClaszList.get(i).getClasz().getBj_id();
            System.out.println("bjid="+bjid);
            //获取每个班级 对象
           clasz =new ClassServiceImpl().getById(bjid);
           claszList.add(clasz);
        }
        for(int i=0;i<claszList.size();i++){

            bjid=claszList.get(i).getBj_id();
            studentList=new ClassServiceDemo().getStudentsByBjId(bjid);
            listList.add(studentList);

        }
   }





}
