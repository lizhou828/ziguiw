package controllers;

import com.arj.ziguiw.common.utils.DateUtils;
import controllers.modules.cas.UnSecure;
import models.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-25
 * Time: P.M.2:52
 */
public class ClassNotices extends Application{
    public static void tlist(String startTime, String endTime, Integer bjId, Integer isAjax){
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        List<Clazz> clazzList = Teacher.findClazz(userBase.foreignKey.intValue());
        Clazz clazz = getClazzInfo(clazzList,bjId);
        startTime = DateUtils.getStartTime(startTime);
        endTime = DateUtils.getEndTime(endTime);
        Page<ClassNotice> page = ClassNotice.findPageByBjId(clazz.bjId,startTime,endTime,getPage(),getPageSize());
        if(isAjax != null ){
            render("ClassNotices/tlist_ajax.html",page);
        }else {
            render(clazzList,page,startTime,endTime);
        }
    }



    public static void plist(String startTime ,String endTime,Integer isAjax, Integer xsId) throws ParseException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        startTime = DateUtils.getStartTime(startTime);
        endTime = DateUtils.getEndTime(endTime);
        Page<ClassNotice> page = ClassNotice.findPageByBjId(student.bjId,startTime,endTime,getPage(),getPageSize());
        if(isAjax != null){
            render("ClassNotices/plist_ajax.html",page);
        }else {
            render(startTime,endTime,studentList,page);
        }

    }

    @UnSecure
    public static void wapplist(String startTime ,String endTime,Integer xsId){
        UserBase wapUserBase = wapGetUserInfo();
        Map<String,Object> map = getChildrenInfo(wapUserBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        startTime = DateUtils.getStartTime(startTime);
        endTime = DateUtils.getEndTime(endTime);
        Page<ClassNotice> page = ClassNotice.findPageByBjId(student.bjId,startTime,endTime,getPage(),getPageSize());
        render(page,student,studentList);
    }

}
