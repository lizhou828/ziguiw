package controllers;

import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import play.mvc.With;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-12-20
 * Time: P.M 2:13
 *  student archive
 */

@With(SecureCAS.class)
public class Students extends Application {
    //家长身份
    public static void pshow(Integer xsId, Integer isAjax) {
        UserBase userBase = (UserBase) renderArgs.get("userBase");

        List<Student> studentList = Parent.findChildrenByMobilePhone(userBase.nickName);
        Student student = getStudent(studentList, xsId);
        Clazz clazz = (Clazz)Clazz.findById(student.bjId);
        Grade grade=Grade.findById(clazz.njId);
        School school= School.find("byXxId",student.xxId).first();
        if(isAjax != null ){
            render("/Students/pshow_ajax.html",student,clazz,school,grade,studentList);
        }else {
            render(student,clazz,school,grade,studentList);

        }
    }

    //学生身份
    public static void sshow(){
        UserBase userBase = (UserBase) renderArgs.get("userBase");
        Student student=Student.findById(userBase.foreignKey);
        Clazz clazz = (Clazz)Clazz.findById(student.bjId);
        Grade grade=Grade.findById(clazz.njId);
        School school= School.find("byXxId",student.xxId).first();
        render(student,clazz,grade,school);
    }

    //老师身份
    public static void tshow(Integer bjId ,Integer isAjax){
        UserBase userBase = (UserBase) renderArgs.get("userBase");
        List<Clazz> clazzList=Teacher.findClazz(userBase.foreignKey.intValue());
        Clazz clazz = getClazzInfo(clazzList,bjId);
        Page<Student> studentPage = Teacher.pageQueryStudentByBjId(getPage(), getPageSize(), clazz.bjId);
        if(isAjax != null ){
            render("/Students/tshow_ajax.html",studentPage,clazz);
        } else {
            render(studentPage,clazz,clazzList);
        }

    }

    //wap 版本的家长、学生身份
    @UnSecure
    public static void wapplist(Integer xsId){
        UserBase wapUserBase = wapGetUserInfo();
        Map<String,Object> map = getChildrenInfo(wapUserBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        Clazz clazz = (Clazz)Clazz.findById(student.bjId);
        Grade grade=Grade.findById(clazz.njId);
        School school= School.find("byXxId",student.xxId).first();
        render(student,clazz,school,grade,studentList);
    }


}
