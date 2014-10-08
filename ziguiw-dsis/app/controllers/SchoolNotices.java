package controllers;

import com.arj.ziguiw.common.UserBaseType;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-24
 * Time: P.M.5:27
 */
public class SchoolNotices extends Application {

    private static final String SCHOOL_NOTICE_URL = (String) Play.configuration.getProperty("SchoolNotice_url");

    private static final String DSIS_RPC_URL = (String)Play.configuration.getProperty("dsis_rpc_url");

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final Log log = LogFactory.getLog(SchoolNotices.class);


    public static void list(String startTime, String endTime, Integer xsId, Integer isAjax)
            throws ParseException, IOException, InterruptedException {
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");

        String url = String.format(DSIS_RPC_URL+SCHOOL_NOTICE_URL + "?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        String xxId="";
        List<Student> studentList=null;
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        if (userBase.type== UserBaseType.TEACHER){
              Teacher teacher=Teacher.find("byTeacherid",userBase.foreignKey).first();
              xxId=teacher.xxId;
        }else if(userBase.type==UserBaseType.PARENT){
            Map<String, Object> map = getChildrenInfo(userBase, xsId);
            Student student = (Student)map.get("student");
            studentList = (List<Student>)map.get("studentList");
            xxId = student.xxId;
        }else if(userBase.type==UserBaseType.STUDENT){
            Student student=Student.find("byXsId",userBase.foreignKey).first();
            xxId=student.xxId;
        }
        url += "&schoolid="+xxId;
        try {
            Page schNoticePage = getDataByRpc(url);
            if (isAjax != null) {
                render("SchoolNotices/list_ajax.html", schNoticePage);
            } else {
                render(startTime,endTime, schNoticePage,studentList);
            }
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }

    }

    @UnSecure
    public static void wapplist(Integer xsId){
        String url = String.format(DSIS_RPC_URL+SCHOOL_NOTICE_URL + "?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                getStartTime(), getEndTime(), getPage(), getWapPageSize());
        UserBase wapUserBase = wapGetUserInfo();
        Map<String, Object> map = getChildrenInfo(wapUserBase,xsId);
        Student student = (Student)map.get("student");
        School school = School.find("byXxId",student.xxId).first();
        url += "&schoolid=" + school.xxId;
        try {
            Page page = getDataByRpc(url);
            render(page,school);
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e);
        }
    }

}
