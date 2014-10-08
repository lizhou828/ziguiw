package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.*;
import com.zigui.exception.ErrorCode;
import com.zigui.exception.QueryBusinessDataException;
import com.zigui.exception.UserNotExistException;
import com.zigui.utils.*;
import com.zigui.utils.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.codehaus.jackson.map.ObjectMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:lizhou
 * Date: 12-10-11
 * Time:  pm 2:40
 */
public class UserCenterAction extends  BaseAction implements ServletContextAware {
    private static final Log log = LogFactory.getLog("dsis");
    private UserBase user;
    private Integer userType;
    private Long foreignKey;
    private Teacher teacher;
    private School school;
    private Parent parent;
    private Student student;
    private List<Student> students;
    private Page<Student> studentPage=new Page<Student>();
    private Clasz clasz=new Clasz();
    private List<TeacherClasz> teacherClasses=new ArrayList<TeacherClasz>();
    private TeacherClasz teacherClass=new TeacherClasz();

    //分页查询
    private Page<Clasz> claszPage=new Page<Clasz>();
    private Map<String, Object> map=new HashMap<String, Object>();
    private String gotoPage="1";   //要跳转到的页(数据库中的数据)
    private int nowpage=1;      // 当前页(接口中的数据)
    private int pages;        //当前页(接口中的参数)

    private int pagesize;    //每页显示记录条数(接口中的数据)
    private int rectotal;    //总记录条数(接口中的数据)
    private int totalPages=0;  //总页数
    private List<SchoolNotice> notices=new ArrayList<SchoolNotice>();
    //分页查询开始时间
    private String startTime;
    //分页查询结束时间
    private String endTime;
    private Result result=new Result();

    //InnerLetter
    private Page<CommonMessage> commonMessages;
    private String countPerPage = "10";
    private String currentPage;
    private String orderField = "createTime";
    private int letterId;
    private CommonMessage  commonMessage=new CommonMessage();
    private List<CommonMessage> commonMessageList;

    //reply inner letter
    private String replyContent;
    private long messageId;
    private long toUserId;
    //send inner letter
    private String toUserName;
    private String title;
    private String text;

    //dsis info
    private Page<Attendance> attendances=new Page<Attendance>();
    private List<StudentConsumeRecord> consumeRecord=new ArrayList<StudentConsumeRecord>();
    private List<StudentPayRecords> payRecordses=new ArrayList<StudentPayRecords>();
    private HomeWork homeWork;
    private Page<HomeWork> homeWorkPage;
    private ExamResult examResult;//考试结果.包含成绩
    private Page<ExamResult> examResultPage=new Page<ExamResult>();
    private List<HomeWork> studentHomeWorkList=new ArrayList<HomeWork>();
    private List<StudentPayRecords> payRecords=new ArrayList<StudentPayRecords>();
    private List<Subject> subjectList=new ArrayList<Subject>();
    private Subject subject;
    private Exam exam;
    private Float totalScore =0f; //成绩总分
    private List<Float> examList=new ArrayList<Float>();
    private String classId;//页面传过来的班级id
    private Integer bjid=0;       //班级id
    private Integer examId=0;    //考试id
    private Integer termId; //学期id
    private int njId;      //年级i
    private int studentId=0; //学生id

    private String graphURL;   //单科成曲线图路径

    private ArrayList examResultList;
    private ArrayList<String> examStringList;
    private String dsisRPCUrl =ConfigUtils.getProperty("dsis.rpc.url");
    private Page<Exam> examPage ;
    private List termList;
    private List exams;
    private TermSet termSet;
    private List<Clasz> claszList;
    private Integer studentCount=0;

    private ObjectMapper objectMapper=new ObjectMapper();
    private String url; // address that access dsis web interface
    private String content; // response from dsis web interface
    private List pageList=new ArrayList();
    private Boolean bind;//check  card number bind state of student whose parent is querying

    /**
     * show user's basic information in the head of user center pages
     * */
    public String showHeadMsg(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp == null || userTemp.getId() <= 0L){
                return INPUT;
            }
            user=userService.getUserBaseById(userTemp.getId());
            userType=user.getType();
            foreignKey=user.getForeignKey();
            if(userType==2){
                teacher=teacherService.getById(foreignKey.intValue());
            }else if (userType==3){
                parent=parentService.getById(foreignKey.intValue());
                student=parent.getStudent();
            }else if(userType==4) {
                student=studentService.getById(foreignKey.intValue());
            }else{
                return INPUT;
            }
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
           return SUCCESS;
        }
    }

    //显示角色信息
    public String showRoleInfo(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            userType=user.getType();
            foreignKey=user.getForeignKey();
            if(user == null && user.getId() <= 0L) {
                return INPUT;
            }
            if(userType==2){
                teacher=teacherService.getById(foreignKey);
                claszList =classService.findAllClassByTeacherId(foreignKey);
                if(claszList !=null && claszList.size()!=0){
                    String str="";
                    for(int i=0;i<claszList.size();i++){
                        long id=claszList.get(i).getBj_id();
                        str = (studentService.countStudentByClassId(id).get(0)).toString();
                        studentCount += Integer.parseInt(str);
                    }
                }
                return "teacherRole";
            }else if(userType==3){
                parent=parentService.getById(foreignKey.intValue());
                students=parentService.findChildren(parent.getMobliephone());
                if(students == null || students.size() == 0) {
                    return "parentRole";
                }
                if(studentId <=0 ){
                    student=students.get(0);
                }else {
                    student=studentService.getById(studentId);
                }
                long bjID=Long.parseLong(student.getBj_ID());
                clasz=classService.getById(bjID);
                return "parentRole";
            }else if(userType==4){
                student=studentService.getById(foreignKey.intValue());
                clasz= student.getClasz();
                return "studentRole";
            }else {
                return "visitorRole";
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            return "noMoreInfo";

        }
    }

    /*show student's score by access specified web interface*/
    public String examList() throws ClassNotFoundException {
        //Class clazz = Class.forName("antlr.ActionElement");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            if(user == null || user.getId() <= 0L || user.getType() <= 0 || user.getForeignKey() <= 0L){
                return INPUT;
            }
            userType=user.getType();
            foreignKey=user.getForeignKey();
            JSONObject jsonObject;
            if(userType==2){
                teacher=teacherService.getById(foreignKey);
                if(bjid <= 0 || examId <= 0 ){
                    log.info(String.format("班级id=%s.考试id=%s",bjid,examId));
                    return  "classExamListNone";
                }
                clasz=classService.getById(bjid);
                if("".equals(clasz.getNj_id())){
                    return "classExamListNone";
                }
                njId=Integer.valueOf(clasz.getNj_id());

                if(gotoPage == null || "".equals(gotoPage)){
                    pages=1;
                }else {
                    pages=Integer.valueOf(gotoPage);
                }
                school=teacher.getSchool();

                request.setAttribute("examPage",examPage);
                url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/teaexamList.htm?bjid=%s" +
                        "&examId=%s&njid=%s&pages=%s&pagesize=20&startTime=%s&endTime=%s",bjid,examId,njId,pages,startTime,endTime);
                //bjid=761&examId=411&njid=1942有数据
                log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                content=HttpUtils.getResponse(url, "UTF-8");
                log.info(content);
                map=JsonToJavaUtils.getObject(content);

                nowpage=(Integer)map.get("nowpage");
                pagesize= (Integer)map.get("pagesize");
                rectotal=(Integer)map.get("rectotal");
                if(pagesize!=0){
                    totalPages = rectotal%pagesize==0 ? rectotal/pagesize : (rectotal/pagesize)+1;
                }

                List pageList=new ArrayList();
                if(totalPages < 9 && totalPages>=1){
                    for(int i=0 ;i <totalPages;i++){
                        pageList.add(i+1);
                    }
                }
                request.setAttribute("pageList",pageList);
                //获取接口中的科目列表
                if(map.get("subList").toString().equals("null")){
                    return "classExamListNone";
                }
                JSONArray subList=(JSONArray)map.get("subList");
                System.out.println("subList.size="+subList.size());

                    //获取每个科目名称
                    for(int i=0;i<subList.size();i++){
                        subject=new Subject();
                        jsonObject=subList.getJSONObject(i);
                        subject.setXxID(jsonObject.get("xxid").toString());
                        subject.setSUBJECT_NAME(jsonObject.get("subjectName").toString());
                        subject.setSUBJECT_id(Long.parseLong(jsonObject.get("subjectId").toString()));
                        subject.setSUBJECT_CODE(jsonObject.get("subjectCode").toString());

                        subjectList.add(subject);
                    }
                    //获取接口中的考试信息

                    JSONArray tempArray=(JSONArray)map.get("data");
                    if(tempArray.size() == 0){
                        return "classExamListNone";
                    }
                    examResultList=new ArrayList();
                    for(int j=0;j<tempArray.size();j++){
                        examStringList=new ArrayList<String>();
                        JSONObject dataJsonObj=(JSONObject)tempArray.get(j);
                        if(dataJsonObj != null && dataJsonObj.size() != 0){
                            //获取学生信息
                            student=new Student();
                            JSONObject studentArray= (JSONObject)dataJsonObj.get("tstudent");
                            student.setXs_id(Integer.valueOf(studentArray.get("xsId").toString()));
                            student.setXs_xming(studentArray.get("xsXming").toString());

                            //获取考试信息
                            exam=new Exam();
                            JSONObject examArray=(JSONObject)dataJsonObj.get("texamination");
                            exam.setXxID(examArray.get("xxid").toString());
                            exam.setType(Integer.parseInt(examArray.get("typeId").toString()));
                            exam.setEXAM_id( Integer.parseInt(examArray.get("examId").toString()));
                            exam.setEXAM_NAME(examArray.get("examName").toString());
                            exam.setCREATE_EXAM_DATE(new Date(Long.parseLong(examArray.get("createExamDate").toString())));
                            //获取成绩集合
                            Float score=0f;
                            for(int i=4;i<subList.size()+4;i++){
                                String cjStr;
                                if(!dataJsonObj.get("cj"+(i-3)).toString().equals("null")){
                                    cjStr = dataJsonObj.get("cj"+(i-3)).toString();
                                }else {
                                    cjStr="0.0";
                                }
                                float f= Float.parseFloat(cjStr);
                                score = f + score;
                            }
                            examStringList.add(exam.getEXAM_NAME());
                            examStringList.add(student.getXs_xming());
                            examStringList.add(score.toString());
                            examResultList.add(examStringList);
                        }
                    }
                    request.setAttribute("examResultList",examResultList);
                    return "classExamList";

            }else {
                    if(userType==3){
                        parent=parentService.getById(foreignKey.intValue());
                        students=parentService.findChildren(parent.getMobliephone());
                        if(students == null || students.size() == 0)   return "studentExamListNone";
                        if(studentId <= 0){
                            student=students.get(0);
                        }else {
                            student=studentService.getById(studentId);
                        }
                    }else if(userType==4){
                        student=studentService.getById(foreignKey.intValue());
                    }
                    if(student == null || student.getSchool()==null || student.getSchool().getXxID()==null || "".equals(student.getSchool().getXxID())){
                        return "studentExamListNone";
                    }
                    if(examId <= 0 || termId <= 0){
                        System.out.println("学期id="+termId+",考试id="+examId);
                        return "studentExamListNone";
                    }
                    termSet=dsisTermSetService.findById(termId.longValue());
                    long start=termSet.getHolidayBegin().getTime();
                    long end=termSet.getHolidayEnd().getTime();
                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/comexamList.htm?studentid=%s" +
                            "&examId=%s&startTime=%s&endTime=%s",student.getXs_id(),examId,start,end);
                    //studentId=1124&examId=441有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s", url));
                    content=HttpUtils.getResponse(url, "UTF-8");
                    map=JsonToJavaUtils.getObject(content);
                    log.info(content);
                    pages=(Integer)map.get("nowpage");

                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    //获取接口中的科目列表
                    if(map.get("subList").toString().equals("null")){
                        return "studentExamListNone";
                    }
                    JSONArray subList=(JSONArray)map.get("subList");
                    System.out.println("subList.size="+subList.size());
                    if(subList!= null && subList.size()!=0){
                        //获取每个科目名称
                        for(int i=0;i<subList.size();i++){
                            subject=new Subject();
                            jsonObject=subList.getJSONObject(i);
                            subject.setXxID(jsonObject.get("xxid").toString());
                            subject.setSUBJECT_NAME(jsonObject.get("subjectName").toString());
                            subject.setSUBJECT_id(Long.parseLong(jsonObject.get("subjectId").toString()));
                            subject.setSUBJECT_CODE(jsonObject.get("subjectCode").toString());

                            subjectList.add(subject);
                        }
                        //获取接口中的考试信息

                        JSONArray tempArray=(JSONArray)map.get("data");
                        if(tempArray.size() == 0){
                            return "studentExamListNone";
                        }
                        JSONObject dataJsonObj=tempArray.getJSONObject(0);
                        String xxid=dataJsonObj.get("xxid").toString();
                        String cjId=dataJsonObj.get("cjId").toString();

                        if(dataJsonObj != null && dataJsonObj.size() != 0){
                            //获取考试信息
                            exam=new Exam();
                            JSONObject examArray=(JSONObject)dataJsonObj.get("texamination");
                            exam.setXxID(examArray.get("xxid").toString());
                            exam.setType(Integer.parseInt(examArray.get("typeId").toString()));
                            exam.setEXAM_id( Integer.parseInt(examArray.get("examId").toString()));
                            exam.setEXAM_NAME(examArray.get("examName").toString());
                            exam.setCREATE_EXAM_DATE(new Date(Long.parseLong(examArray.get("createExamDate").toString())));

                            //获取成绩集合

                            for(int i=4;i<subList.size()+4;i++){
                                String cjStr;
                                if(!dataJsonObj.get("cj"+(i-3)).toString().equals("null")){
                                    cjStr = dataJsonObj.get("cj"+(i-3)).toString();
                                }else {
                                    cjStr="0.0";
                                }
                                float f= Float.parseFloat(cjStr);
                                totalScore = f + totalScore;
                                 examList.add(f);
                            }
                            //成绩曲线图
                            //时间曲线数据集合
                            if(examList!=null&&subjectList!=null&&examList.size()==subjectList.size()){
                                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                                for(int i =0;i<examList.size();i++){
                                dataset.addValue(examList.get(i), "成绩", subjectList.get(i).getSUBJECT_NAME());
                                }
                                JFreeChart chart = ChartFactory.createBarChart3D("成绩统计图","科目","分数",dataset,PlotOrientation.VERTICAL,
                                        false,
                                        false,
                                        false);
                                CategoryPlot plot = chart.getCategoryPlot();
                                plot.setBackgroundPaint(Color.white);
                                plot.setDomainGridlinePaint(Color.pink);
                                plot.setRangeGridlinePaint(Color.pink);
                                BarRenderer3D renderer = new BarRenderer3D();
                                renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                                renderer.setBaseItemLabelsVisible(true);
                                renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
                                renderer.setItemLabelAnchorOffset(10D);
                                renderer.setItemMargin(0.4);
                                plot.setRenderer(renderer);
                                String filename = ServletUtilities.saveChartAsPNG(chart, 600, 300, null);
                                graphURL = filename;
                                log.info(String.format("成绩图的url是%s",filename));
                               }
                            }
                    } else {
                        return "studentExamListNone";
                    }
                    //获取数据库中，获取学生的考试名称、学期等信息
                    return "studentExamList";
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }
    }



    //显示学生作业
    public String showHomeWork(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            userType=user.getType();
            foreignKey =user.getForeignKey();
            JSONArray jsonArray;
            JSONObject jsonObject;
            if(foreignKey <= 0L || userTemp==null || userTemp.getId()<0L){
                return INPUT;
            }
            //若当前用户是老师类型
            if(userType==2){
                //判断是否带了班级
                claszPage=classService.getClassesByTeacherId(foreignKey.intValue());
                if(claszPage == null || claszPage.getList()==null || claszPage.getList().size()==0){
                    log.info(String.format("该老师没有带任何班级"));
                    return "classHomeworkNone";
                }

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
                //页面上传过来的：跳转到指定页面
                if(gotoPage == null || "".equals(gotoPage)){
                    pages=1;
                }else {
                    pages=Integer.valueOf(gotoPage);
                }
                if(classId == null || classId=="" ){
                    return "classHomeworkNone";
                }
                Integer bjId=new Integer(classId);
                if(bjId==null|| bjId <= 0){
                    claszList=classService.findAllClassByTeacherId(foreignKey);
                    bjId=(int)claszList.get(0).getBj_id();
                }
                //开始在接口中获取数据
                url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/teamsg.htm?bjid=%s" +
                        "&pages=%s&pagesize=20&startTime=%s&endTime=%s",bjId,pages,startTime,endTime);
                //bjid=665&pages=3&pagesize=10&startTime=1320949673000&endTime=1337827318000 有数据
                log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                content=HttpUtils.getResponse(url, "UTF-8");
                log.info(content);
                map = objectMapper.readValue(content, Map.class);
                pagesize= (Integer)map.get("pagesize");
                rectotal=(Integer)map.get("rectotal");
                if(pagesize!=0){
                    totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                }
                pageList =new ArrayList();
                for(int i=0;i<totalPages;i++){
                    pageList.add(i+1);
                }
                return "classHomeworkList";

            }else {
                if(userType==3){
                    parent=parentService.getById(foreignKey.intValue());
                    students=parentService.findChildren(parent.getMobliephone());
                    if(students == null || students.size() == 0){
                        return "stuHomeWorkNone";
                    }
                    if(studentId <=0 ){
                        student=students.get(0);
                    }else {
                        student=studentService.getById(studentId);
                    }
                }else if(userType==4){
                    student=studentService.getById(foreignKey.intValue());
                }
                if(student==null){
                    return "stuHomeWorkNone";
                }
                if(endTime==null||"".equals(endTime)){
                    Long end=new Date().getTime();
                    System.out.print("结束时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    endTime=end.toString();
                } else {
                    Long end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime();
                    endTime=end.toString();
                }
                if(startTime==null||"".equals(startTime)){
                    Calendar calendar=Calendar.getInstance();
                    calendar.add(Calendar.MONTH,-1);
                    Date date=calendar.getTime();
                    System.out.println("开始时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                    Long start=date.getTime();
                    startTime=start.toString();
                }else {
                    Long start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime();
                    startTime=start.toString();
                }
                //页面上传过来的：跳转到指定页面
                System.out.println("gotoPage="+gotoPage);
                if(gotoPage == null || "".equals(pages)){
                    pages=1;
                }else {
                    pages=Integer.valueOf(gotoPage);
                }
                url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/msg.htm?studentid=%s" +
                        "&pages=%s&pagesize=20&startTime=%s&endTime=%s",student.getXs_id(),pages,startTime,endTime);
                //studentid=833&startTime=1300949673000&endTime=1337827318000有数据
                log.info(String.format("the dsis web interface address  you're visiting is=%s", url));
                content=HttpUtils.getResponse(url, "UTF-8");
                log.info(content);
                map = objectMapper.readValue(content, Map.class);
                pagesize= (Integer)map.get("pagesize");
                rectotal=(Integer)map.get("rectotal");
                if(pagesize!=0){
                    totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                }
                pageList =new ArrayList();
                for(int i=0;i<totalPages;i++){
                    pageList.add(i+1);
                }
                return "studentHomeWork";
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }
    }


    //学生一卡通消费、缴费查询
    public String studentConsumptionQuery(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                foreignKey=user.getForeignKey();
                userType=user.getType();

                //获取传过来的查询起始时间
                if(endTime==null||"".equals(endTime)){
                    Long end=new Date().getTime();
                    endTime=end.toString();
                } else {
                    String s=new SimpleDateFormat("HH:mm:ss").format(new Date());
                    endTime=endTime+s;
                    Long end=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime).getTime();
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
                //go to page 。。。。
                if(gotoPage == null || "".equals(gotoPage)){
                    pages=1;
                }else {
                    pages=Integer.valueOf(gotoPage);
                }
                //judge the current login user's type
                if(userType==2){
                    teacher=teacherService.getById(foreignKey.intValue());
                    if(bjid <=0 ){
                        //初始化：加载默认班级
                        claszList=classService.findAllClassByTeacherId(foreignKey);
                        clasz=claszList.get(0);
                        bjid=(int)clasz.getBj_id();
                    }
//                    studentId=617;
                    url = studentId > 0 ? String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/recordflow.htm?studentid=%s&pages=%s&pagesize=20&startTime=%s&endTime=%s",studentId,pages,startTime,endTime)
                                          : String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/recordflow.htm?bjid=%s&pages=%s&pagesize=20&startTime=%s&endTime=%s",bjid,pages,startTime,endTime);
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url, "UTF-8");
                    log.info(content);
                    map = objectMapper.readValue(content, Map.class);
                    if(studentId > 0 ){
                        student=studentService.getById(studentId);
                        map.put("XS_XMING",student.getXs_xming());
                    }
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }
                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                    return Action.SUCCESS;
                }else {
                    if(userType == 3L ){
                        parent=parentService.getById(foreignKey.intValue());
                        if(parent == null)  return NONE;
                        students=parentService.findChildren(parent.getMobliephone());
                        if(students == null || students.size() == 0) return NONE;
                        if(studentId <= 0){
                            student=students.get(0);
                        }else {
                            student=studentService.getById(studentId);
                        }
                    }
                    if(userType == 4L ){
                        student=studentService.getById(foreignKey.intValue());
                        if(student==null) return NONE;
                    }
                    //access the web interface address
                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/recordflow.htm?studentid=%s" +
                            "&pages=%s&pagesize=20&startTime=%s&endTime=%s",student.getXs_id(),pages,startTime,endTime);
                    //studentid=617接口中有数据

                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url, "UTF-8");
                    log.info(content);
                    map = objectMapper.readValue(content, Map.class);
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }
                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                    return Action.SUCCESS;
                }
            }
            return  NONE;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }
    }


    /* show students' attendance records */
    public String showAttendance(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                userType=user.getType();
                foreignKey=user.getForeignKey();
                if(userType==2){
                    claszPage=classService.getClassesByTeacherId(foreignKey.intValue());
                    if(claszPage == null || claszPage.getList()==null || claszPage.getList().size()==0){
                        log.info(String.format("该老师没有带任何班级"));
                        return "studentsArchivesNone";
                    }
                    //若该老师带的班级比较多，默认加载班级集合中的第一班级
                    clasz=claszPage.getList().get(0);
                    Long id=claszPage.getList().get(0).getBj_id();
                    bjid=Integer.valueOf(id.toString());
                    studentPage=teacherService.getStudentsByClassId(clasz.getBj_id());
                    request.setAttribute("studentPage",studentPage);
                    //获取页面传过来的要跳转到的指定页面
                    pages=gotoPage == null ? 1:Integer.valueOf(gotoPage);
                    //获取页面传过来的classId
                    if (!"".equals(classId) && classId != null ) {
                        bjid=Integer.valueOf(classId.trim());
                    }
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
                        calendar.add(Calendar.DATE,-5);
                        Date date=calendar.getTime();
                        Long start=date.getTime();
                        startTime=start.toString();
                    }else {
                        Long start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime();
                        startTime=start.toString();
                    }
                    if(studentId > 0){
                        url= String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/bussiness/attendanceRec.htm?pages=%s" +
                                "&pagesize=20&studentid=%s&startTime=%s&endTime=%s",pages ,studentId,startTime,endTime);
                        student=studentService.getById(studentId);
                    }else {
                        url= String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/bussiness/attendanceRec.htm?pages=%s" +
                                "&pagesize=20&bjid=%s&startTime=%s&endTime=%s",pages,bjid,startTime,endTime);
                    }
                    //bjid=660有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url, "UTF-8");
                    log.info(content);
                    /* new version start */
                    map = objectMapper.readValue(content, Map.class);
                    if(studentId > 0){
                       student=studentService.getById(studentId);
                       map.put("XS_XMING",student.getXs_xming());
                    }
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }

                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                    /* new version end */
                    return "studentsAttendance";

                }else{
                    if(userType==3){
                        parent=parentService.getById(foreignKey.intValue());
                        students=parentService.findChildren(parent.getMobliephone());
                        if(students == null || students.size() == 0){
                            return "studentAttendanceNONE";
                        }
                        if(studentId <=0 ){
                            student=students.get(0);
                        }else {
                            student=studentService.getById(studentId);
                        }
                    }else if(userType==4){
                        student=studentService.getById(foreignKey.intValue());
                    }
                    if(student==null){
                        return "studentAttendanceNONE";
                    }
                    //获取传过来的查询起始时间
                    if(endTime != null && !"".equals(endTime)){
                        Long end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime();
                        endTime=end.toString();
                    } else {
                        Long end=new Date().getTime();
                        System.out.print("结束时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                        endTime=end.toString();
                    }
                    if(startTime != null && !"".equals(startTime)){
                        Long start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime();
                        startTime=start.toString();
                    }else {
                        Calendar calendar=Calendar.getInstance();
                        calendar.add(Calendar.MONTH,-1);
                        Date date=calendar.getTime();
                        System.out.println("开始时间=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                        Long start=date.getTime();
                        startTime=start.toString();
                    }

                    pages=gotoPage == null ? 1:Integer.valueOf(gotoPage);
                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/bussiness/attendanceRec.htm?pages=%s" +
                            "&pagesize=20&studentid=%s&startTime=%s&endTime=%s",pages ,student.getXs_id(),startTime,endTime);
                    //&studentid=1124，在接口中有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url, "UTF-8");
                    log.info(content);
                    map = objectMapper.readValue(content, Map.class);
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }
                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                    return "studentAttendance";
                }
            }
            return NONE;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }
    }

    //学校公告
    public String showSchoolNotice(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            if(user == null || user.getId() <= 0L || user.getType() < 0 || user.getForeignKey() <= 0L ) {
                return INPUT;
            }
            foreignKey=user.getForeignKey();
            userType=user.getType();
            if(userType==2){
                teacher=teacherService.getById(foreignKey);
                if( teacher==null || teacher.getSchool()==null){
                    return NONE;
                }
                school=teacher.getSchool();
            }else if(userType==3){
                parent=parentService.getById(foreignKey.intValue());
                students=parentService.findChildren(parent.getMobliephone());
                if(students == null || students.size() == 0){
                    return NONE;
                }
                if(studentId <= 0){
                    student=students.get(0);
                }else {
                    student=studentService.getById(studentId);
                }
                if(parent == null || parent.getStudent()==null || parent.getStudent().getSchool() == null) {
                    return NONE;
                }
                school=student.getSchool();
            }else if(userType==4){
                student=studentService.getById(Integer.valueOf(foreignKey.toString()));
                if(student==null || student.getSchool()==null){
                    return NONE;
                }
                school= student.getSchool();
            } else if(userType==1){
                if (schoolService.getById(foreignKey) == null) {
                    return NONE;
                }
                school=schoolService.getById(foreignKey);
            }else {
                return NONE;
            }
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
            if(gotoPage == null || "".equals(gotoPage)){
                pages=1;
            }else {
                pages=Integer.valueOf(gotoPage);
            }
            url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/bussiness/schoolnotice.htm?pages=%s" +
                    "&pagesize=15&schoolid=%s&startTime=%s&endTime=%s",pages,school.getXxID(),startTime,endTime);
            log.info(String.format("the dsis web interface address  you're visiting is =%s",url));
            //schoolid=2011001有数据
            content=HttpUtils.getResponse(url,"UTF-8");
            log.info(content);
            map = objectMapper.readValue(content, Map.class);
            pagesize= (Integer)map.get("pagesize");
            rectotal=(Integer)map.get("rectotal");
            if(pagesize!=0){
                totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
            }
            pageList =new ArrayList();
            for(int i=0;i<totalPages;i++){
                pageList.add(i+1);
            }
            request.setAttribute("notices" ,notices);
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e, ErrorCode.RPC);
        }
    }

    //班级通知
    public String classNoticeList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            if(user == null || user.getId() <= 0L || user.getType() <= 0 || user.getForeignKey() <= 0L ) {
                return INPUT;
            }
            userType=user.getType();
            foreignKey=user.getForeignKey();
            if(gotoPage == null || "".equals(pages)){
                currentPage="1";
            }else {
                currentPage=gotoPage;
            }
            if(userType==3){
                parent=parentService.getById(foreignKey.intValue());
                students=parentService.findChildren(parent.getMobliephone());
                if(students == null || students.size() == 0){
                    return Action.SUCCESS;
                }
                if(studentId <= 0){
                    student=students.get(0);
                    user=student.getUser();
                }else {
                    student=studentService.getById(studentId);
                    user=student.getUser();
                }
            }
            Page<Object> tmp = commonService.getByHql("from CommonMessage where toUser = ?" +
                    " and kind = 7  and state = 1 ", new Integer(currentPage).intValue(),
                    new Integer(countPerPage).intValue(), new Object[]{user});
            commonMessages = CommonUtils.transform(CommonMessage.class, tmp);
            request.setAttribute("classNotices",commonMessages);
            rectotal=(int)commonMessages.getTotalCount();
            pagesize=commonMessages.getPageSize();
            if(pagesize!=0){
                totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
            }
            pageList =new ArrayList();
            for(int i=0;i<totalPages;i++){
                pageList.add(i+1);
            }
            return Action.SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    //学生评语
    public String studentCommentList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                userType=user.getType();
                foreignKey=user.getForeignKey();
                JSONArray jsonArray;
                if(userType==2){
                   teacher= teacherService.getById(foreignKey);
                    if(endTime==null||"".equals(endTime)){
                        Long end=new Date().getTime();
                        endTime=end.toString();
                    } else {
                        Long end=new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime();
                        endTime=end.toString();
                    }
                    if(startTime==null||"".equals(startTime)){
                        Calendar calendar=Calendar.getInstance();
                        calendar.add(Calendar.DATE,-5);
                        Date date=calendar.getTime();
                        Long start=date.getTime();
                        startTime=start.toString();
                    }else {
                        Long start=new SimpleDateFormat("yyyy-MM-dd").parse(startTime).getTime();
                        startTime=start.toString();
                    }
                    //页面上传过来的：跳转到指定页面

                    if(gotoPage == null || "".equals(pages)){
                        pages=1;
                    }else {
                        pages=Integer.valueOf(gotoPage);
                    }

                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/msgSend.htm?typeid=3" +
                            "&teacherId=%s&pages=%s&pagesize=20&startTime=%s&endTime=%s",teacher.getTeacherID(),pages,startTime,endTime);
//                    &teacherId=1220不用时间查询，有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url,"UTF-8");
                    log.info(content);
                    map = objectMapper.readValue(content, Map.class);
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }
                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                     return "studentsComments";
                }else {

                    if(userType==3){
                        parent=parentService.getById(foreignKey.intValue());
                        students=parentService.findChildren(parent.getMobliephone());
                        if(students == null || students.size() == 0){
                            return "stuComment";
                        }
                        if(studentId <= 0){
                            student=students.get(0);
                        }else {
                            student=studentService.getById(studentId);
                        }
                    }
                    if(userType==4){
                       student=studentService.getById(foreignKey.intValue());
                    }
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
                    if(gotoPage == null || "".equals(pages)){
                        pages=1;
                    }else {
                        pages=Integer.valueOf(gotoPage);
                    }
                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/commentsMsg.htm?studentid=%s" +
                            "&startTime=%s&endTime=%s&pages=%s&pagesize=20",student.getXs_id(),startTime,endTime,pages);
//                    studentid=1274&startTime=1301384833000&endTime=1333007233000有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content=HttpUtils.getResponse(url,"UTF-8");
                    log.info(content);
                    map = objectMapper.readValue(content, Map.class);
                    pagesize= (Integer)map.get("pagesize");
                    rectotal=(Integer)map.get("rectotal");
                    if(pagesize!=0){
                        totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                    }
                    pageList =new ArrayList();
                    for(int i=0;i<totalPages;i++){
                        pageList.add(i+1);
                    }
                     return "stuComment";
                }
            }
            return  INPUT;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC) ;
        }
    }

     //站内信列表页面
    public String innerLetterList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
//                String hql=String.format("from CommonMessage where to_user_id %s and kind 3 and state 1",user.getId());
                if(gotoPage == null || "".equals(pages)){
                    currentPage="1";
                }else {
                    currentPage=gotoPage;
                }
                Page<Object> tmp = commonService.getByHql("from CommonMessage where toUser = ?" +
                        " and kind = 3  and state = 1 order by createTime desc ", new Integer(currentPage).intValue(),
                        new Integer(countPerPage).intValue(), new Object[]{user});
                commonMessages = CommonUtils.transform(CommonMessage.class, tmp);
                request.setAttribute("letters",commonMessages);
                rectotal=(int)commonMessages.getTotalCount();
                pagesize=commonMessages.getPageSize();
                if(pagesize!=0){
                    totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                }
                pageList =new ArrayList();
                for(int i=0;i<totalPages;i++){
                    pageList.add(i+1);
                }
                return Action.SUCCESS;

            }
            return  INPUT;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
           throw new RuntimeException(e);
        }
    }

    //站内信详细页面
    public String showLetterDetail(){
        try{
            HttpServletRequest request=ServletActionContext.getRequest() ;
            commonMessage=commonMessageService.getById(letterId);
            request.setAttribute("commonMessage",commonMessage);
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }



    }
    //回复站内信
    public String replyInnerLetter(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                log.info(String.format("UserCenterAction's replyInnerLetter method is running....." +
                        "MessageId=%s ,replyContent=%s",messageId,replyContent));

                commonMessage=new CommonMessage();
                commonMessage.setToUser(userService.getUserBaseById(toUserId));
                commonMessage.setFromUser(userService.getUserBaseById(user.getId()));
                commonMessage.setCreateTime(new Date());
                commonMessage.setKind(3);
                commonMessage.setParentMsg(commonMessageService.getById(messageId));
                commonMessage.setText(replyContent);
                commonMessageService.saveOrUpdate(commonMessage);

                return SUCCESS;
            }

            return NONE;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }

    }

    //站内信发送历史
    public String sendLetterList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                if(gotoPage == null || "".equals(pages)){
                    currentPage="1";
                }else {
                    currentPage=gotoPage;
                }
                Page<Object> letterPager = commonService.getByHql("from CommonMessage where fromUser = ?" +
                        " and kind = 3  and state = 1 order by createTime desc", new Integer(currentPage).intValue(),
                        new Integer(countPerPage).intValue(), new Object[]{user});

                commonMessages = CommonUtils.transform(CommonMessage.class, letterPager);
                request.setAttribute("sendLetters",commonMessages);
                pagesize=commonMessages.getPageSize();
                if(pagesize!=0){
                    totalPages=rectotal%pagesize == 0 ? rectotal/pagesize :(rectotal/pagesize)+1;
                }
                pageList =new ArrayList();
                for(int i=0;i<totalPages;i++){
                    pageList.add(i+1);
                }
                return SUCCESS;
            }

            return  INPUT;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }

    }
    //发送站内信
    public String sendInnerLetter(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp != null && userTemp.getId() > 0L) {
                user=userService.getUserBaseById(userTemp.getId());
                log.info(String.format("UserCenterAction's sendInnerLetter method is running...." +
                        "toUserName=%s,nickName=%s",toUserName,(userService.getUserBaseByNickName(toUserName)).getNickName()));

                UserBase toUser;
                toUser= userService.getUserBaseByNickName(toUserName.trim());
                log.info(String.format("toUser's info:id=%s,nickName=%s",toUser.getId(),toUser.getNickName()));
                commonMessage.setToUser(toUser);
                commonMessage.setFromUser(userService.getUserBaseById(user.getId()));
                commonMessage.setKind(3);
                commonMessage.setTitle(title);
                commonMessage.setText(text);
                commonMessage.setCreateTime(new Date());
                commonMessageService.saveOrUpdate(commonMessage);

                return SUCCESS;
            }
            return  NONE;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e,ErrorCode.RPC);
        }
    }


      //传入bj_id
      // 传出学期集合
      public String queryTermInfo(){
          System.out.println("UserCetner's queryTermInfo method is running...bjid="+bjid);

          if(bjid == null || bjid<=0){
              return NONE;
          }
          clasz=classService.getById(bjid);
          if(clasz == null || clasz.getXxID() == null || "".equals(clasz.getXxID())){
              return NONE;
          }
           termList = dsisTermSetService.getBySchoolId(clasz.getXxID());
          return SUCCESS;
      }

      public String queryExam(){
          HttpServletRequest request = ServletActionContext.getRequest();
          HttpSession session=request.getSession();
          try{
              UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
              if(userTemp != null && userTemp.getId() > 0L) {
                  user=userService.getUserBaseById(userTemp.getId());
                  log.info(String.format("UserCenterAction's examNameQuery method is running....termId=%s  and bjid=%s",termId,bjid));
                  if( termId <= 0 || bjid <= 0){
                          return ERROR;
                  }
                  clasz=classService.getById(bjid);
                  if(clasz == null){
                      return ERROR;
                  }
                  String schoolId=clasz.getXxID();
                  examPage=dsisExamService.queryExam(1,10,Long.valueOf(schoolId),termId);
                  exams=findExamsByBjid(examPage, bjid);
                  return SUCCESS;
              }
              return INPUT;
          }catch (Exception e){
              e.printStackTrace();
              log.error(e.getMessage(),e);
              throw new RuntimeException(e);
          }
      }

    public static List findExamsByBjid(Page<Exam> examPage,Integer bjid){
        //取到改学校的所有次考试
        if(examPage.getList() != null && examPage.getList().size() != 0){
            List examList=new ArrayList<Exam>();
            List  list=examPage.getList();
            //遍历所有次考试
            for(int i=0;i<list.size();i++){
                String idStr=((Exam)list.get(i)).getIdStr();
                if(idStr == null ){
                    return new ArrayList();
                }
                //判断是否有多个班级参加同一场考试
                if(idStr.indexOf(",")>=0){
                    String [] idArray = idStr.split(",");
                    //遍历某次考试的idStr字段（班级id的集合）
                    for(int j=0;j<idArray.length;j++){
                        if(idArray[j].toString().equals(bjid.toString())){
                            examList.add(list.get(i));
                        }
                    }
                }else {
                    if(idStr.equals(bjid.toString())){
                        examList.add(list.get(i));
                    }
                }
            }
            return examList;
        }
        return new ArrayList();
    }
    public String ajaxCheckBind()throws UserNotExistException{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        String bindMsg;
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            userType=user.getType();
            foreignKey=user.getForeignKey();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return INPUT;
        }
       if(userType==2){
            try{
                teacher=teacherService.getById(foreignKey);
                bind=true;
                map.put("bind",bind);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                bind=false;
                bindMsg=e.getMessage();
                map.put("bind",bind);
                map.put("bindMsg",bindMsg);
            }
       }else if(userType==3){
            try{
                parent=parentService.getById(foreignKey.intValue());
                bind=true;
                map.put("bind",bind);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                bind=false;
                bindMsg=e.getMessage();
                map.put("bind",bind);
                map.put("bindMsg",bindMsg);
            }

       }else if(userType==4){
            try{
                studentService.getById(foreignKey.intValue());
                bind=true;
                map.put("bind",bind);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                bind=false;
                bindMsg=e.getMessage();
                map.put("bind",bind);
                map.put("bindMsg",bindMsg);
            }
       } else {
            bind=false;
            bindMsg="您不是数字化校园用户";
            map.put("bind",bind);
            map.put("bindMsg",bindMsg);
       }
       return SUCCESS;
    }

    public String ajaxQueryStudentsByClassId(){
        if(bjid<=0){
            System.out.println("未选定班级");
            return NONE;
        }
        students=studentService.ajaxQueryStudentsByClassId((long)bjid);
        return SUCCESS;
    }



    public UserBase getUser() {
        return user;
    }

    public void setUser(UserBase user) {
        this.user = user;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    public Page<Clasz> getClaszPage() {
        return claszPage;
    }

    public void setClaszPage(Page<Clasz> claszPage) {
        this.claszPage = claszPage;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getRectotal() {
        return rectotal;
    }

    public void setRectotal(int rectotal) {
        this.rectotal = rectotal;
    }

    public List<SchoolNotice> getNotices() {
        return notices;
    }

    public void setNotices(List<SchoolNotice> notices) {
        this.notices = notices;
    }

    public Page<CommonMessage> getCommonMessages() {
        return commonMessages;
    }

    public void setCommonMessages(Page<CommonMessage> commonMessages) {
        this.commonMessages = commonMessages;
    }

    public String getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(String countPerPage) {
        this.countPerPage = countPerPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public CommonMessage getCommonMessage() {
        return commonMessage;
    }

    public void setCommonMessage(CommonMessage commonMessage) {
        this.commonMessage = commonMessage;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Page<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Page<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<StudentConsumeRecord> getConsumeRecord() {
        return consumeRecord;
    }

    public void setConsumeRecord(List<StudentConsumeRecord> consumeRecord) {
        this.consumeRecord = consumeRecord;
    }

    public List<StudentPayRecords> getPayRecordses() {
        return payRecordses;
    }

    public void setPayRecordses(List<StudentPayRecords> payRecordses) {
        this.payRecordses = payRecordses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<TeacherClasz> getTeacherClasses() {
        return teacherClasses;
    }

    public void setTeacherClasses(List<TeacherClasz> teacherClasses) {
        this.teacherClasses = teacherClasses;
    }

    @Override
    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Page<Student> getStudentPage() {
        return studentPage;
    }

    public void setStudentPage(Page<Student> studentPage) {
        this.studentPage = studentPage;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public Page<HomeWork> getHomeWorkPage() {
        return homeWorkPage;
    }

    public void setHomeWorkPage(Page<HomeWork> homeWorkPage) {
        this.homeWorkPage = homeWorkPage;
    }

    public ExamResult getExamResult() {
        return examResult;
    }

    public void setExamResult(ExamResult examResult) {
        this.examResult = examResult;
    }

    public Page<ExamResult> getExamResultPage() {
        return examResultPage;
    }

    public void setExamResultPage(Page<ExamResult> examResultPage) {
        this.examResultPage = examResultPage;
    }

    public List<HomeWork> getStudentHomeWorkList() {
        return studentHomeWorkList;
    }

    public void setStudentHomeWorkList(List<HomeWork> studentHomeWorkList) {
        this.studentHomeWorkList = studentHomeWorkList;
    }

    public List<StudentPayRecords> getPayRecords() {
        return payRecords;
    }

    public void setPayRecords(List<StudentPayRecords> payRecords) {
        this.payRecords = payRecords;
    }

    public TeacherClasz getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(TeacherClasz teacherClass) {
        this.teacherClass = teacherClass;
    }

    public List<CommonMessage> getCommonMessageList() {
        return commonMessageList;
    }

    public void setCommonMessageList(List<CommonMessage> commonMessageList) {
        this.commonMessageList = commonMessageList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public List<Float> getExamList() {
        return examList;
    }

    public void setExamList(List<Float> examList) {
        this.examList = examList;
    }

    public Integer getBjid() {
        return bjid;
    }

    public void setBjid(Integer bjid) {
        this.bjid = bjid;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getNjId() {
        return njId;
    }

    public void setNjId(int njId) {
        this.njId = njId;
    }


    public String getGraphURL() {
        return graphURL;
    }

    public void setGraphURL(String graphURL) {
        this.graphURL = graphURL;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(String gotoPage) {
        this.gotoPage = gotoPage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public ArrayList getExamResultList() {
        return examResultList;
    }

    public void setExamResultList(ArrayList examResultList) {
        this.examResultList = examResultList;
    }

    public ArrayList<String> getExamStringList() {
        return examStringList;
    }

    public void setExamStringList(ArrayList<String> examStringList) {
        this.examStringList = examStringList;
    }

    public String getDsisRPCUrl() {
        return dsisRPCUrl;
    }

    public void setDsisRPCUrl(String dsisRPCUrl) {
        this.dsisRPCUrl = dsisRPCUrl;
    }

    public Page<Exam> getExamPage() {
        return examPage;
    }

    public void setExamPage(Page<Exam> examPage) {
        this.examPage = examPage;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public List getTermList() {
        return termList;
    }

    public void setTermList(List termList) {
        this.termList = termList;
    }

    public List getExams() {
        return exams;
    }

    public void setExams(List exams) {
        this.exams = exams;
    }

    public TermSet getTermSet() {
        return termSet;
    }

    public void setTermSet(TermSet termSet) {
        this.termSet = termSet;
    }

    public List<Clasz> getClaszList() {
        return claszList;
    }

    public void setClaszList(List<Clasz> claszList) {
        this.claszList = claszList;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public Boolean getBind() {
        return bind;
    }

    public void setBind(Boolean bind) {
        this.bind = bind;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Long foreignKey) {
        this.foreignKey = foreignKey;
    }

}
