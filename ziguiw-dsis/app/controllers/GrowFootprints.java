package controllers;

import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-25
 * Time: P.M.3:49
 */
public class GrowFootprints extends Application {

    public static void tlist(Integer bjId, Integer xsId, String startTime, String endTime,Integer isAjax) throws ParseException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        List clazzList=Teacher.findClazz(userBase.foreignKey.intValue());
        if (clazzList.size() == 0) throw new TeacherNotBindClassException("对不起，您未绑定班级");
        Page page = GrowFootprint.findPage(bjId, xsId, startTime, endTime, getPage(), 5);
        if(isAjax != null){
            render("GrowFootprints/tlist_ajax.html",page);
        }else {
            render(clazzList,startTime,endTime,page);
        }
    }

//    //发送ajax请求时，显示成长足迹
//    public static void plist(Integer xsId, String startTime, String endTime,Integer isAjax) throws ParseException {
//        UserBase userBase=(UserBase)renderArgs.get("userBase");
//        Map<String,Object> map = getChildrenInfo(userBase,xsId);
//        Student student = (Student)map.get("student");
//        List<Student> studentList = (List<Student>)map.get("studentList");
//        Page<GrowFootprint> page = GrowFootprint.findPage(new Integer(-1),student.xsId.intValue(),startTime,endTime,getPage(),new Integer(5));
//        if(isAjax != null){
//            render("GrowFootprints/plist_ajax.html",page,studentList,student);
//        }else {
//            render(studentList,startTime,endTime,page,student);
//        }
//    }
    //跳转到添加页面
    public static void pAdd(){
       Student student = (Student)renderArgs.get("student");
        List<Student> studentList = (List<Student>)renderArgs.get("studentList");
        render(student,studentList);
    }


    //保存
    public static void pCreate(String time,String place,String activitiesName,String result,Long xsId) throws ParseException {
        Student student=null;
        if(xsId != null && !"".equals(xsId)){
            student = Student.findById(xsId);
        }else {
            student= (Student)renderArgs.get("student");
        }
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        GrowFootprint growFootprint = new GrowFootprint();
        growFootprint.creatorNick = userBase.nickName;
        growFootprint.lastModifyNick = userBase.nickName;
        growFootprint.createTime = new SimpleDateFormat("yyyy-mm-dd").parse(time);
        growFootprint.lastModifyTime = new Date();
        growFootprint.xsId = student.xsId;
        growFootprint.bjId = student.bjId;
        growFootprint.title = activitiesName;
        growFootprint.place = place;
        growFootprint.result = result;
        growFootprint.create();
        List<Student> studentList = (List<Student>)renderArgs.get("studentList");
        render(growFootprint,studentList);
    }

    public static void pSave(String time,String activitiesName,String place,Long id,String result) throws ParseException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        GrowFootprint growFootprint = GrowFootprint.findById(id);
        growFootprint.createTime  = new SimpleDateFormat("yyyy-mm-dd").parse(time);
        growFootprint.title = activitiesName;
        growFootprint.lastModifyNick = userBase.nickName;
        growFootprint.lastModifyTime = new Date();
        growFootprint.result = result;
        growFootprint.place = place;
        growFootprint.save();
        plist("", "", 1);
    }

    public static void plist(String xsId,String isAjax,Integer page){
        Student student = null;
        if(xsId == null || "".equals(xsId)){
            student = (Student)renderArgs.get("student");
        }else {
            student = Student.findById(new Long(xsId));
        }
        Integer gotoPage =1;
        if(page != null && !"".equals(page) && !"undefined".equals(page)){
            gotoPage = new Integer(page);
        }
        Page<GrowFootprint> growFootprintPage = GrowFootprint.findByXsId(student.xsId,gotoPage,DEFAULT_PAGE_SIZE);
        if(isAjax == null || "".equals(isAjax)){
            render(growFootprintPage,student);
        }else {
            render("GrowFootprints/plist_ajax.html",growFootprintPage,student);
        }
    }

    public static void pShow (String id){
        GrowFootprint growFootprint = GrowFootprint.findById(new Long(id));
        render(growFootprint);
    }

    public static void pDelete(String id){
        GrowFootprint.deleteById(new Long(id));
        Student student = (Student)renderArgs.get("student");
       plist(student.xsId.toString(),"",1);
    }

}
