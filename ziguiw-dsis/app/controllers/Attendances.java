package controllers;

import controllers.modules.cas.UnSecure;
import models.Clazz;
import models.Page;
import models.Student;
import models.UserBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-15
 * Time: A.M 11:56
 */
public class Attendances extends Application {

    private static final Log log = LogFactory.getLog(Attendances.class);

    public static void tlist(String startTime, String endTime, Integer bjId, Integer xsId, Integer isAjax)
            throws ParseException, IOException, InterruptedException {
        Map<String, String> timeMap=convertTime(startTime, endTime);
        startTime = timeMap.get("startTime");
        endTime = timeMap.get("endTime");
        String url = String.format(DSIS_RPC_URL+ATTENDANCE_URL + "?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        Map<String,Object> map = getClazzAndStudent(bjId,xsId);
        Student student = (Student)map.get("student");
        Clazz clazz = ( Clazz)map.get("clazz");
        List<Clazz> clazzList = (List<Clazz>)map.get("clazzList");
        url += student == null? "&bjid="+clazz.bjId : "&studentid=" + student.xsId;
        try {
            Page attendancePage = getDataByRpc(url);
            if (isAjax != null) {
                render("Attendances/tlist_ajax.html", attendancePage,student);
            } else {
                render(startTime, endTime, clazzList,student, attendancePage);
            }
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }
    }

    public static void plist(String startTime,String endTime,Integer isAjax,Integer xsId)throws Exception{
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");
        String url = String.format(DSIS_RPC_URL+ATTENDANCE_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        UserBase userBase = (UserBase) renderArgs.get("userBase");
        Map<String, Object> map = getChildrenInfo(userBase ,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        url += "&studentid=" + student.xsId;
        try {
            Page attendancePage = getDataByRpc(url);
            if (isAjax != null) {
                render("Attendances/plist_ajax.html", attendancePage,student);
            } else {
                render(startTime,endTime,studentList,student, attendancePage);
            }
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }
    }


    @UnSecure
    public static void wapplist(Integer xsId)throws Exception{
        String url = String.format(DSIS_RPC_URL+ATTENDANCE_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                getStartTime(), getEndTime(), getPage(), getWapPageSize());
        UserBase wapUserBase = wapGetUserInfo();
        Map<String, Object> map = getChildrenInfo(wapUserBase,xsId);
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
