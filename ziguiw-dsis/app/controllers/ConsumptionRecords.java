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
 * User: liz
 * Date: 13-1-23
 * Time: P.M.3:55
 */
public class ConsumptionRecords extends Application {
    private static final String DSIS_RPC_URL = (String)Play.configuration.getProperty("dsis_rpc_url");
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final Log log = LogFactory.getLog(ConsumptionRecords.class);

    public static void tlist(String startTime, String endTime, Integer bjId, Integer xsId, Integer isAjax)
            throws ParseException, IOException, InterruptedException {
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");
        String url = String.format(DSIS_RPC_URL+CONSUMPTION_URL + "?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        Map<String,Object> map = getClazzAndStudent(bjId,xsId);
        Student student = (Student)map.get("student");
        Clazz clazz = ( Clazz)map.get("clazz");
        List<Clazz> clazzList = (List<Clazz>)map.get("clazzList");
        url += student == null? "&bjid="+clazz.bjId : "&studentid=" + student.xsId;
        try {
            Page consumeRecordPage = getDataByRpc(url);
            if (isAjax != null) {
                render("ConsumptionRecords/tlist_ajax.html", consumeRecordPage,xsId,startTime,endTime,student,bjId);
            } else {
                render(startTime,endTime,clazzList, consumeRecordPage);
            }
        } catch (Exception e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }
    }



    public static void plist(String startTime,String endTime,Integer isAjax,Integer xsId)throws Exception{
        Map<String,String> timeMap=convertTime(startTime,endTime);
        startTime=timeMap.get("startTime") ;
        endTime=timeMap.get("endTime");
        String url = String.format(DSIS_RPC_URL+CONSUMPTION_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                startTime, endTime, getPage(), getPageSize());
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        url +="&studentid="+student.xsId;
        try {
            Page consumeRecordPage = getDataByRpc(url);
            if (isAjax != null) {
                render("ConsumptionRecords/plist_ajax.html",consumeRecordPage,student);
            } else {
                render(startTime,endTime,studentList, consumeRecordPage,student);
            }
        } catch (Exception e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }


    }

    @UnSecure
    public static void wapplist(Integer xsId){
        String url = String.format(DSIS_RPC_URL+CONSUMPTION_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                getStartTime(), getEndTime(), getPage(), getWapPageSize());
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
