package controllers;

import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-24
 * Time: P.M 3:53
 * 学生评语
 */
public class StudentComments extends Application {

    private static final String TEACHER_URL = (String) Play.configuration.getProperty("StudentComment_teacher_url");

    private static final String STUDENT_URL = (String) Play.configuration.getProperty("StudentComment_student_url");

    private static final String DSIS_RPC_URL = (String)Play.configuration.getProperty("dsis_rpc_url");

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final Log log = LogFactory.getLog(StudentComments.class);

    //老师身份查询学生评语信息
    public static void tlist(String startTime, String endTime, Integer isAjax) throws ParseException, IOException, InterruptedException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");
//        String url = String.format(DSIS_RPC_URL+TEACHER_URL + "&startTime=%s&endTime=%s&pages=%s&pagesize=%s",
//                startTime, endTime, getPage(), getPageSize());
         List<Clazz> clazzList= Teacher.findClazz(userBase.foreignKey.intValue());
         if (clazzList.size() == 0) throw new TeacherNotBindClassException("对不起，您未绑定班级");
//        url += "&teacherId="+userBase.foreignKey;
//        try {
//            Page stuCommentPage = getDataByRpc(url);
         if (isAjax != null) {
//                render("StudentComments/tlist_ajax.html", stuCommentPage,startTime,endTime);
               render("StudentComments/tlist_ajax.html",startTime,endTime);
         } else {
//                render(startTime,endTime,clazzList, stuCommentPage);
                render(startTime,endTime,clazzList);
         }
//        } catch (Exception e) {
//            throw new DsisException(e, isAjax == null ? false : true);
//        }

    }

    public static void plist(String startTime,String endTime,Integer isAjax,Integer xsId)throws Exception{
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");
        String url = String.format(DSIS_RPC_URL+STUDENT_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        url +="&studentid="+student.xsId;
        try {
            Page stuCommentPage = getDataByRpc(url);
            if (isAjax != null) {
                render("StudentComments/plist_ajax.html",stuCommentPage,student);
            } else {
                render(startTime, endTime, studentList, stuCommentPage, student);
            }
        } catch (Exception e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }

    }

    @UnSecure
    public static void wapplist(Integer xsId){
        String url = String.format(DSIS_RPC_URL+STUDENT_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                getEndTime(), getEndTime(), getPage(), getWapPageSize());
        UserBase wapUserBase = wapGetUserInfo();
        Map<String,Object> map = getChildrenInfo(wapUserBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        url += "&studentid=" + student.xsId;
        try {
            Page page = getDataByRpc(url);
            render(studentList,student,page);
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e);
        }
    }

}
