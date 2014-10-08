package controllers;

import com.arj.ziguiw.common.SchoolNewsType;
import com.arj.ziguiw.common.UserBaseType;
import com.arj.ziguiw.common.utils.HttpUtils;
import com.arj.ziguiw.common.utils.JsonUtils;
import controllers.modules.cas.SecureCAS;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.Play;
import play.cache.Cache;
import play.mvc.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@With(SecureCAS.class)
public class Application extends Controller {

    /* the default page size for list */
    protected static final Integer DEFAULT_PAGE_SIZE = 10;

    private static final Integer WAP_DEFAULT_PAGE_SIZE = 100;

    /* the default charset for the response of remote process call */
    protected static final String RPC_DEFAULT_CHARSET = "UTF-8";

    /* attendances webservice url */
    protected static final String ATTENDANCE_URL = (String) Play.configuration.getProperty("Attendances_url");

    /* consume webservice url */
    protected static final String CONSUMPTION_URL = (String) Play.configuration.getProperty("ConsumptionRecord_url");

    /* webservice host and port */
    protected static final String DSIS_RPC_URL = (String)Play.configuration.getProperty("dsis_rpc_url");

    private static Log log = LogFactory.getLog(Application.class);

    @Before
    public static void before() {
        /* log the url */
        MDC.put("url", request.url.replaceAll("'", "‘"));

        //PC login
        String userName = (String) DsisSecurity.connected();
        if (userName != null) {
            MDC.put("username", userName);
            UserBase userBase = UserBase.find("byNickName", userName).first();
            if (userBase != null) {
                renderArgs.put("userBase", userBase);
                renderArgs.put("username", userName);
                session.put("username", userName);
                Cache.add(userName + "_" + session.getId(), userBase);
                MDC.put("username", userName);
                //inject the user info into renderArgs
                injectUserInfo(userBase, renderArgs);
                // handle authority
                handleAuthority(userBase, renderArgs);
            }
        }
    }

    //@Before(only = {"Attendances.plist", "Attendances.tlist"}) // make the request passed, Because not data in pay table.
    public static void notPay() {
        String xsId = params.get("xsId");
        if (xsId == null) {
            //todo get xsId from userBase...
        }
        if ( xsId != null)  {
            boolean payed = Parent.findPayedChildren((UserBase)renderArgs.get("userBase"), Long.valueOf(xsId));
            if(!payed){
                render("Application/notPay.html");
            }
        }
    }

    @Util
    //处理权限
    protected static void handleAuthority(UserBase userBase, Scope.RenderArgs renderArgs1) {
        String action = Http.Request.current().actionMethod;
        if (action.equalsIgnoreCase("tlist") && userBase.type != UserBaseType.TEACHER) {
            throw new IllegalOperationException("非法操作");
        }
    }

    @Util
    //注入用户信息
    protected static void injectUserInfo(UserBase userBase, Scope.RenderArgs renderArgs1) {
        if(userBase.type == UserBaseType.TEACHER){
            Teacher teacher = Teacher.find("byTeacherId", userBase.foreignKey.intValue()).first();
            renderArgs1.put("teacher", teacher);
            renderArgs1.put("displayName", teacher.name);
        } else if (userBase.type == UserBaseType.PARENT){
            Parent parent = Parent.find("byParentId", userBase.foreignKey).first();
            renderArgs1.put("parent", parent);
            List<Student> studentList = Parent.findChildrenByMobilePhone(userBase.nickName);
            renderArgs1.put("studentList", studentList);
            String xsId  = request.params.get("xsId");
            Student student = getStudent(studentList,xsId == null|| "".equals(xsId) || xsId.equalsIgnoreCase("undefined") ? -1 : Integer.valueOf(xsId));
            if (student != null) {
                renderArgs1.put("student", student);
                renderArgs1.put("displayName", student.xsXming + "家长");
            } else {
                renderArgs1.put("displayName", userBase.nickName);
                render("unbind.html");
            }
        } else if(userBase.type == UserBaseType.STUDENT){
            Student student = Student.find("byXsId", userBase.foreignKey).first();
            renderArgs1.put("student", student);
            renderArgs1.put("displayName", student.xsXming);
        }
    }

    public static void index() throws ParseException, RpcResponseFormatException {
        UserBase userBase = (UserBase) renderArgs.get("userBase");
        School school = null;
        if (userBase.type == UserBaseType.TEACHER) {
            Teacher teacher = (Teacher)(renderArgs.get("teacher"));
            school = School.find("byXxId", teacher.xxId).first();
            renderArgs.put("clazzList", Teacher.findClazz(teacher.teacherId));
            renderArgs.put("studentCount", Teacher.countStudentNumber(teacher.teacherId));
            renderArgs.put("school", school);
        }
        if (userBase.type == UserBaseType.PARENT) {
            Student student = (Student) renderArgs.get("student");
            school = School.find("byXxId", student.xxId).first();
            List<Student> studentList = Parent.findChildrenByMobilePhone(userBase.nickName);
            //找到缴纳过年费的孩子
            List<Student> studentListNotPayed = Parent.findNotPayedChild(userBase);
            renderArgs.put("studentListNotPayed", studentListNotPayed);
            renderArgs.put("clazz", Clazz.findById(student.bjId));
            renderArgs.put("school", school);
        }
        if (userBase.type == UserBaseType.STUDENT) {
            Student student = (Student) renderArgs.get("student");
            school = School.find("byXxId", student.xxId).first();
            renderArgs.put("clazz", Clazz.findById(student.bjId));
            renderArgs.put("school", school);
        }
        Date date=new Date();
        String currentTime=new SimpleDateFormat("yyyy年MM月dd日").format(date);

        /* get student attendance */
        Student student = (Student) renderArgs.get("student");
        if (student != null) {
            String startTime = null;
            String endTime = null;
            Map<String,String> timeMap = convertTime(startTime,endTime);
            startTime = timeMap.get("startTime") ;
            endTime = timeMap.get("endTime");
            String url = String.format(DSIS_RPC_URL+ATTENDANCE_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                    startTime, endTime, getPage(), getPageSize());
            url += "&studentid=" + student.xsId;
            Page attendancePage = getDataByRpc(url);
            renderArgs.put("attendancePage", attendancePage);

            url = String.format(DSIS_RPC_URL+CONSUMPTION_URL+"?startTime=%s&endTime=%s&pages=%s&pagesize=%s",
                    startTime, endTime, getPage(), getPageSize());
            url += "&studentid=" + student.xsId;
            Page consumePage = getDataByRpc(url);
            renderArgs.put("consumePage", consumePage);
        }
        if (school != null) {
            renderArgs.put("schoolNews_bulletin", SchoolNew.findLastNewessByType(school.xx_Id, SchoolNewsType.BULLETIN, 6));
            renderArgs.put("schoolNews_news", SchoolNew.findLastNewessByType(school.xx_Id, SchoolNewsType.NEWS, 6));
        }
        /* get student attendance end */
        render(school,currentTime);
    }

    public static void pshow_index(Integer xsId, Integer isAjax){
        UserBase userBase = (UserBase) renderArgs.get("userBase");

        List<Student> studentList = Parent.findChildrenByMobilePhone(userBase.nickName);
        List<Student> studentListNotPayed = Parent.findNotPayedChild(userBase);
        Student student = getStudent(studentList, xsId);
        Clazz clazz = (Clazz)Clazz.findById(student.bjId);
        Grade grade=Grade.findById(clazz.njId);
        School school= School.find("byXxId",student.xxId).first();
        render(student,clazz,school,grade,studentList,studentListNotPayed);
    }



    protected static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    protected static Integer getPageSize() {
        return request.params.get("pageSize") == null ? DEFAULT_PAGE_SIZE : Integer.valueOf(request.params.get("pageSize"));
    }

    //获取wap版的每页记录条数
    protected static Integer getWapPageSize(){
        return WAP_DEFAULT_PAGE_SIZE;
    }

    protected static String getStartTime(){
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        Date date=calendar.getTime();
        return  date.getTime()+"";
    }
    protected static String getEndTime(){
        return new Date().getTime()+"";
    }
    protected static Map<String,String> convertTime(String startTime,String endTime)throws ParseException {
        //获取传过来的查询起始时间
        if(endTime==null||"".equals(endTime)){
            Long end=new Date().getTime();
            endTime=end.toString();
        } else {
            Long end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime();
            endTime=end.toString();
        }
        if(startTime==null||"".equals(startTime)){
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.MONTH,-1);
            Date date=calendar.getTime();
            Long start=date.getTime();
            startTime=start.toString();
        }else {
            Long start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime();
            startTime=start.toString();
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return map;
    }
    protected static Page mapToPage(Map<String,Object> map){
        if( map == null || map.size() == 0 ){
            return new Page();
        }
        Integer page=(Integer)map.get("nowpage");
        Integer pageSize=(Integer)map.get("pagesize");
        Long totalCount=((Integer)map.get("rectotal")).longValue();
        List list=null;
//        if (map.get("subList") != null) {
//            list.add((ArrayList)map.get("subList"));
//            list.add(map.get("data"));
//        } else {
            list = "[]".equalsIgnoreCase(map.get("data").toString()) ? new ArrayList() : (ArrayList)map.get("data");
//        }
        return new Page(page,pageSize,totalCount,list);
    }

    protected static Page getDataByRpc(String url) throws RpcResponseFormatException {
        log.info(String.format("get response from %s", url));
        String response = HttpUtils.getResponse(url, RPC_DEFAULT_CHARSET);
        log.info(String.format("response is %s", response));
        try {
            return mapToPage((Map) JsonUtils.format(response, Map.class));
        } catch (IOException e) {
            throw new RpcResponseFormatException();
        }
    }



    protected static Exam getExamInfo(TermSet termSet, Clazz clazz,Integer examId) {
        if(examId != null && examId != 0) return  Exam.findById(examId);
        List<Exam> examList = TermSet.findExam(termSet.termId,clazz.bjId);
        if(examList.size() == 0) throw new TermNotBindExamException("对不起，该学期还没有考试");
        Exam exam = examList.get(0);
        return exam;
    }

    protected static TermSet getTermInfo(Clazz clazz, Integer termId) {
        List<TermSet> termSetList = TermSet.find("byXxId",clazz.xxId).fetch();
        if(termSetList.size() == 0) throw new SchoolNotBindTermException("对不起，该学校未绑定学期");
        TermSet termSet = termSetList.get(0);
        if(termId != null && termId != -1) {
            termSet = TermSet.findById(termId);
        }
        return termSet;
    }

    protected static Clazz getClazzInfo(List<Clazz> clazzList, Integer bjId) {
        if (clazzList.size() == 0) throw new TeacherNotBindClassException("对不起，您未绑定班级");
        Clazz clazz = clazzList.get(0);
        if (bjId != null && bjId != -1) clazz = Clazz.findById(bjId);
        return clazz;
    }

    protected static Student getStudent(List<Student> studentList, Integer xsId) {
        if(studentList == null || studentList.size() == 0) throw new ParentNotBindChildrenException("对不起，未绑定学生卡号");
        Student student = studentList.get(0);
        if(xsId != null && xsId != -1) student = Student.findById(xsId.longValue());
        return student;
    }

    //初始化学生信息（家长、学生用户查询数字化校园时）
    protected static Map<String, Object> getChildrenInfo(UserBase userBase,Integer xsId) {
        List<Student> studentList= null;
        Student student= null;
        if (userBase.type == UserBaseType.PARENT) {
            studentList =Parent.findChildrenByMobilePhone(userBase.nickName);
            student = getStudent(studentList,xsId);
        } else if (userBase.type == UserBaseType.STUDENT) {
            student = Student.findById(userBase.foreignKey);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("studentList",studentList);
        map.put("student", student);
        return map;
    }

    //初始化班级、学生信息（老师用户查询数字化校园时）
    protected static Map<String, Object> getClazzAndStudent(Integer bjId, Integer xsId) {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        List<Clazz> clazzList= Teacher.findClazz(userBase.foreignKey.intValue());
        if (clazzList.size() == 0) throw new TeacherNotBindClassException("对不起，您未绑定班级");
        Clazz clazz = null;
        if(bjId == null || bjId == -1 ){
            clazz = clazzList.get(0);
        }else {
            clazz = Clazz.findById(bjId);
        }
        Student student=null;
        if(xsId != null && xsId != -1) student = Student.find("byXsId",new Long(xsId)).first();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clazzList",clazzList);
        map.put("clazz",clazz);
        map.put("student", student);
        return map;
    }

    //初始化班级老师用户查询数字化校园时）
    protected static Map<String, Object> getClazzInfo(Integer bjId) {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        List<Clazz> clazzList= Teacher.findClazz(userBase.foreignKey.intValue());
        if (clazzList.size() == 0) throw new TeacherNotBindClassException("对不起，您未绑定班级");
        Clazz clazz;
        if(bjId == null || bjId == -1 ){
            clazz = clazzList.get(0);
        } else {
            clazz = Clazz.findById(bjId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clazzList",clazzList);
        map.put("clazz",clazz);
        return map;
    }

    // wap版获取用户初始化信息
    protected static UserBase wapGetUserInfo() {
        String wapUserName = Scope.Session.current().get("wapUserName");
        UserBase wapUserBase = UserBase.find("byNickName",wapUserName).first();
        return wapUserBase;
    }
}