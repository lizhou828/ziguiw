package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.exception.ErrorCode;
import com.zigui.exception.QueryBusinessDataException;
import com.zigui.utils.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-12-18
 * Time: 上午10:21
 */
public class UserCenterWapAction  extends BaseAction implements ServletContextAware {
    private static final Log log = LogFactory.getLog("dsis");
    private Date currentTime = new Date();
    private String loginPreUrl;
    private String errorMsg;
    private Integer userType;
    private Long foreignKey;

    private School school;
    private Teacher teacher;
    private Parent parent;
    private Student student;
    private List<Student> studentList=new ArrayList<Student>();
    private Map<String ,Object> map=new HashMap<String ,Object>();
    private String saveUserInCookie;


    //成绩查询
    private int studentId=0; //学生id
    private Integer bjid=0;       //班级id
    private Integer examId=0;    //考试id
    private Integer termId=0; //学期id
    private int njId=0;      //年级i
    private String url; // address that access dsis web interface
    private String content; // response from dsis web interface
    private String dsisRPCUrl = ConfigUtils.getProperty("dsis.rpc.url");
    private List subjectList;
    private Subject subject;
    private Exam exam;
    private Float totalScore =0f; //成绩总分
    private List<Float> examList=new ArrayList<Float>();
    private List  examResultList=new ArrayList();
    private Map studentExamInfoMap;
    private ArrayList<String> examStringList;
    private TermSet termSet;

    //public fields
    private List pageList=new ArrayList();
    private ObjectMapper objectMapper=new ObjectMapper();
    private List<Student> students;
    private Clasz clasz=new Clasz();
    private String gotoPage;   //要跳转到的页(数据库中的数据)
    private int nowpage=1;      // 当前页(接口中的数据)
    private int pages;        //当前页(接口中的参数)
    private int pagesize;    //每页显示记录条数(接口中的数据)
    private int rectotal;    //总记录条数(接口中的数据)
    private int totalPages=0;  //总页数
    //分页查询开始时间
    private String startTime;
    //分页查询结束时间
    private String endTime;
    private Map subjectMap=new HashMap();
    private Map dataMap=new HashMap();
    private Map studentMap=new HashMap();
    private Map examMap=new HashMap();
    private ArrayList dataList=new ArrayList();
    private ArrayList studentExamList;
    private String classId;
    private List<TermSet> termList=new ArrayList<TermSet>();


    public String wapAjaxLogin(){
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        session.remove("VALID_USER");

        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = (HttpServletRequest) context .get(ServletActionContext.HTTP_REQUEST);
        UserBase tmpUser=null;
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
         boolean isExist= existUserInCookie(request,user);
         if(!isExist){
             // Check if it exists user in session ?
             if (user == null) {
                 errorMsg="用户不存在";
                 return INPUT;
             }
             if (StringUtils.isEmpty(user.getNickName())) {
                 errorMsg="用户名为空";
                 return INPUT;
             }
             if (StringUtils.isEmpty(user.getPassword())) {
                 errorMsg="密码为空";
                 return INPUT;
             }
             tmpUser = userService.getUserBaseByNickName(user.getNickName());
             if (tmpUser == null) {
                 errorMsg="不存在该用户";
                 return INPUT;
             }
             if (!tmpUser.getPassword().equalsIgnoreCase(user.getPassword())) {
                 errorMsg="密码错误";
                 return INPUT;
             }
         }else {
             tmpUser = userService.getUserBaseByNickName(user.getNickName());
         }

        //未注册
        if (tmpUser.getState() < 2) {
            errorMsg="帐号未激活";
            return INPUT;
        }

        tmpUser.setLastLoginTime(new Date());
        HttpSession session1 = request.getSession();
        String sessionid = session1.getId();
        tmpUser.setSessionId(sessionid);
        if(tmpUser.getLoginCount()==null){
            tmpUser.setLoginCount(1);
        }else{
            tmpUser.setLoginCount(tmpUser.getLoginCount()+1);//用户每成功登陆一次，次数递增
        }
        tmpUser.setLastLoginIp(request.getRemoteAddr());
        userService.saveOrUpdate(tmpUser);
        session.remove("VALID_USER");
        session.put("VALID_USER", tmpUser);
        // save user in cookie ?
        if(saveUserInCookie!=null && saveUserInCookie!="" && saveUserInCookie.equalsIgnoreCase("on")){
            saveUserInCookie(request,tmpUser);
        }

        user = userService.getUserBaseByNickName(user.getNickName());
        loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");
        if (user != null && StringUtils.isNotBlank(user.getNickName())) {
            List<PointsHistory> logonPoints = userService
                    .getPointHistoryByType(user, Constant.EVERY_LOGON);
            boolean canModify = true;

            if (CollectionUtils.isNotEmpty(logonPoints)
                    && logonPoints.size() >= 5
                    && currentTime.getTime()
                    - logonPoints.get(4).getChangeTime().getTime() < 1000 * 60 * 60 * 24) {
                canModify = false;
            }

            if (canModify) {
                PointsHistory ph = new PointsHistory();
                ph.setUser(user);
                ph.setFlag(1);
                ph.setType(Constant.EVERY_LOGON);
                ph.setPointsChange(1);
                userService.changePoints(ph);
            }

            // 连续5天登录的积分
            boolean[] continueLogonArr = new boolean[] { false, false, false,
                    false };
            for (int i = 1; i < 5; i++) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentTime);
                calendar.add(Calendar.DAY_OF_YEAR, -1 * i);
                for (PointsHistory ph : logonPoints) {
                    if (ph.getChangeTime().after(calendar.getTime())) {
                        calendar.add(Calendar.DAY_OF_YEAR, -1);
                        if (ph.getChangeTime().before(calendar.getTime())) {
                            continueLogonArr[i - 1] = true;
                            break;
                        }
                    }
                }
            }

            boolean continueLogon = true;
            for (boolean temp : continueLogonArr) {
                continueLogon = continueLogon && temp;
            }

            if (continueLogon) {
                PointsHistory ph = new PointsHistory();
                ph.setUser(user);
                ph.setFlag(1);
                ph.setType(Constant.CONTINUE_LOGON);

                ph.setPointsChange(10);
                userService.changePoints(ph);
            }
        }

        foreignKey=user.getForeignKey();
        userType=user.getType();
        if(userType == 1 && foreignKey > 0L){
            school=schoolService.getById(foreignKey);
            errorMsg="登录成功";
        }else if(userType ==2 && foreignKey > 0L){
            teacher=teacherService.getById(foreignKey);
            errorMsg="登录成功";
        }else if(userType ==3 && foreignKey > 0L){
            parent=parentService.getById(foreignKey.intValue());
            studentList = parentService.findChildren(parent.getMobliephone());
            if(studentList != null  &&  studentList.size() != 0){
                student = studentList.get(0);
            }
            errorMsg="登录成功";
        }else if(userType ==4 && foreignKey > 0L){
            student=studentService.getById(foreignKey.intValue());
            errorMsg="登录成功";
        }else{
            errorMsg="success3";
        }
        return SUCCESS;
    }


    private Boolean existUserInCookie(  HttpServletRequest request,UserBase user) {
        Cookie[] cookies=request.getCookies();
        if(cookies!=null && cookies.length != 0){
            String nickName;
            String password;
            for(int i=0;i<cookies.length;i++){
                String cookieName=cookies[i].getName();
                if(cookieName.equals("loginMemberUsername")){
                        String cookieValue[]=cookies[i].getValue().split("-");
                        if(cookieValue.length > 1 ){
                            nickName=cookieValue[0] ;
                            password=cookieValue[1];
                            password=DigestUtils.md5Hex(password);
                            if(nickName.equals(user.getNickName()) && password.endsWith(user.getPassword())){
                                return true ;
                            }
                            return false;
                        }
                }
            }
        }
        return false;
    }


    private void saveUserInCookie(HttpServletRequest request,UserBase user) {
        HttpServletResponse response=ServletActionContext.getResponse();
        //First : Judge if cookie exists the same cookieName
        //if exists,clear it
        Cookie cookies[]=request.getCookies();
        for (int i=0;cookies!=null&&i<cookies.length;i++){
            if(cookies[i].getName().equals("loginMemberUsername")){
                Cookie cookie = new Cookie("loginMemberUsername","");
                cookies[i]=cookie;
            }
        }
        //Second : if not exist,create it
        String cookieValue=user.getNickName()+"-"+user.getPassword();
        try{
            Cookie cookie = new Cookie("loginMemberUsername", URLEncoder.encode(cookieValue, "UTF-8"));
            cookie.setMaxAge(60*60*24*365);    // set valid time ,the parameter type is seconds
            response.addCookie(cookie);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    //退出
    public String logout() {
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        session.remove("VALID_USER");
        HttpServletRequest request = (HttpServletRequest) context
                .get(ServletActionContext.HTTP_REQUEST);
        loginPreUrl = CookieUtils.getCookie(request.getCookies(),
                "login_pre_url");
        if (StringUtils.isEmpty(loginPreUrl)) {
            loginPreUrl = "/index.jsp";
        }
        return Action.SUCCESS;
    }

    //用户中心：导航页面
    public String index(){
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        user=(UserBase)session.get("VALID_USER");
        userType=user.getType();
        foreignKey=user.getForeignKey();
        if(userType==1){
            school=schoolService.getById(foreignKey);
        }else if(userType==2){
            teacher=teacherService.getById(foreignKey);
        }else if(userType==3){
            parent=parentService.getById(foreignKey.intValue());
            studentList=parentService.findChildren(parent.getMobliephone());
            if(studentList != null && studentList.size() != 0){
                student=studentList.get(0);
            }
        }else if(userType==4){
            student=studentService.getById(foreignKey.intValue());
        }else{
            return INPUT;
        }
        return  SUCCESS;
    }

    //成绩查询
    public String examList(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            userType=user.getType();
            foreignKey =user.getForeignKey();
            if(user != null && userType > 0L && foreignKey > 0L ){
                if(userType==2){
                    teacher=teacherService.getById(foreignKey);
                    if(bjid <= 0 || examId <= 0 ){
                        log.info(String.format("班级id=%s.考试id=%s",bjid,examId));
                        return  SUCCESS;
                    }
                    clasz=classService.getById(bjid);
                    if( "".equals(clasz.getNj_id()) || null == clasz.getNj_id() ){
                        return SUCCESS;
                    }
                    njId=Integer.valueOf(clasz.getNj_id());
                    if(gotoPage == null || "".equals(gotoPage)){
                        pages=1;
                    }else {
                        pages=Integer.valueOf(gotoPage);
                    }
                    url=String.format("http://"+dsisRPCUrl+"/DSIS_zgw_syndata/business/teaexamList.htm?bjid=%s" +
                            "&examId=%s&njid=%s&pages=%s&pagesize=20",bjid,examId,njId,pages);
                    //bjid=761&examId=411&njid=1942有数据
                    log.info(String.format("the dsis web interface address  you're visiting is=%s",url));
                    content= HttpUtils.getResponse(url, "UTF-8");
                    log.info("The info you retrieve from dsis web interface is ="+content);
                    //分页信息
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
                    //获取科目的集合
                     subjectList=(ArrayList)map.get("subList");
                    if(subjectList == null || subjectList.size() == 0){
                       return SUCCESS;
                    }
                    List subList=new ArrayList();
                    for(int i=0;i<subjectList.size();i++){
                        subjectMap=(HashMap)subjectList.get(i);
                        subList.add(subjectMap);
                    }
                    //获取接口中的考试信息(考生信息、考试信息、成绩信息)
                    dataList=(ArrayList)map.get("data");
                    if(dataList == null || dataList.size() == 0 ){
                        return SUCCESS;
                    }
//                    遍历接口数据中的每一个学生
                    for(int j=0;j<dataList.size();j++){
                        dataMap=(HashMap)dataList.get(j);
                        studentMap=(HashMap)dataMap.get("tstudent");
                        examMap=(HashMap)dataMap.get("texamination");
                        //添加学生
                        studentExamInfoMap=new HashMap();
                        studentExamInfoMap.put("xs_xming",studentMap.get("xsXming"));
                        //获取成绩集合
                        totalScore=0f;
                        List tempList=new ArrayList();
                        Map tempMap=new HashMap();
                        //获取每一个科目相对应的成绩
                        for(int k=4;k<subjectList.size()+4;k++){
                            HashMap subjectHashMap=(HashMap)subList.get(k-4);
                            String cjStr;
                            if(!dataMap.get("cj"+(k-3)).toString().equals("null")){
                                cjStr = dataMap.get("cj"+(k-3)).toString();
                            }else {
                                cjStr="0.0";
                            }
                            float f= Float.parseFloat(cjStr);
                            totalScore = f + totalScore;
                            tempMap.put("subjectName",subjectHashMap.get("subjectName"));
                            tempMap.put("subjectScore",cjStr);
                            tempList.add(tempMap);

                        }
                        studentExamInfoMap.put("subjectList",tempList);
                        studentExamInfoMap.put("totalScore",totalScore);
                        examResultList.add(studentExamInfoMap);//添加每一个学生的成绩信息
                    }
                    return SUCCESS;

                }else {
                    if(userType==3){
                        parent=parentService.getById(foreignKey.intValue());
                        students=parentService.findChildren(parent.getMobliephone());
                        if(students == null || students.size() == 0){
                            return SUCCESS;
                        }
                        if(studentId <= 0){
                            student=students.get(0);
                        }else {
                            student=studentService.getById(studentId);
                        }
                    }else if(userType==4){
                        student=studentService.getById(foreignKey.intValue());
                    }
                    if(student == null || student.getSchool()==null || student.getSchool().getXxID()==null || "".equals(student.getSchool().getXxID())){
                        return SUCCESS;
                    }
                    if(examId <= 0 || termId <= 0){
                        System.out.println("学期id="+termId+",考试id="+examId);
                        return SUCCESS;
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
                    //获取科目的集合
                    subjectList=(ArrayList)map.get("subList");
                    if(subjectList == null || subjectList.size() == 0){
                        return SUCCESS;
                    }
                    List subList=new ArrayList();
                    for(int i=0;i<subjectList.size();i++){
                        subjectMap=(HashMap)subjectList.get(i);
                        subList.add(subjectMap);
                    }
                    //获取接口中的考试信息(考生信息、考试信息、成绩信息)
                    dataMap=(HashMap)map.get("data");
                    if(dataList == null || dataList.size() == 0 ){
                        return "studentExamListNone";
                    }
                    examMap=(HashMap)dataMap.get("texamination");
                    //获取成绩集合
                    studentExamList=new ArrayList();
                    Map tempMap=new HashMap();
                    //获取每一个科目相对应的成绩
                    for(int k=4;k<subjectList.size()+4;k++){
                        HashMap subjectHashMap=(HashMap)subList.get(k-4);
                        String cjStr;
                        if(!dataMap.get("cj"+(k-3)).toString().equals("null")){
                            cjStr = dataMap.get("cj"+(k-3)).toString();
                        }else {
                            cjStr="0.0";
                        }
                        float f= Float.parseFloat(cjStr);
                        totalScore = f + totalScore;
                        tempMap.put("subjectName",subjectHashMap.get("subjectName"));
                        tempMap.put("subjectScore",cjStr);
                        studentExamList.add(tempMap);
                    }
                    return "studentExamList";
                }
            }
            return NONE;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new QueryBusinessDataException(e, ErrorCode.RPC);
        }
    }

    public String queryTermInfo(){
        System.out.println("UserCetner's queryTermInfo method is running...bjid="+classId);
        if(classId==null || classId =="" ){
            return NONE;
        }
        clasz=classService.getById(new Long(classId));
        if(clasz == null || clasz.getXxID() == null || "".equals(clasz.getXxID())){
            return NONE;
        }
        termList = dsisTermSetService.getBySchoolId(clasz.getXxID());
        return SUCCESS;
    }

    @Override
    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Date getCurrentTime() {
        return new Date();
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
    public String getLoginPreUrl() {
        return loginPreUrl;
    }

    public void setLoginPreUrl(String loginPreUrl) {
        this.loginPreUrl = loginPreUrl;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getSaveUserInCookie() {
        return saveUserInCookie;
    }

    public void setSaveUserInCookie(String saveUserInCookie) {
        this.saveUserInCookie = saveUserInCookie;
    }

    public Integer getBjid() {
        return bjid;
    }

    public void setBjid(Integer bjid) {
        this.bjid = bjid;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public int getNjId() {
        return njId;
    }

    public void setNjId(int njId) {
        this.njId = njId;
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

    public String getDsisRPCUrl() {
        return dsisRPCUrl;
    }

    public void setDsisRPCUrl(String dsisRPCUrl) {
        this.dsisRPCUrl = dsisRPCUrl;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    public String getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(String gotoPage) {
        this.gotoPage = gotoPage;
    }

    public int getNowpage() {
        return nowpage;
    }

    public void setNowpage(int nowpage) {
        this.nowpage = nowpage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public TermSet getTermSet() {
        return termSet;
    }

    public void setTermSet(TermSet termSet) {
        this.termSet = termSet;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public List<Float> getExamList() {
        return examList;
    }

    public void setExamList(List<Float> examList) {
        this.examList = examList;
    }

    public List getExamResultList() {
        return examResultList;
    }

    public void setExamResultList(List examResultList) {
        this.examResultList = examResultList;
    }

    public Map getStudentExamInfoMap() {
        return studentExamInfoMap;
    }

    public void setStudentExamInfoMap(Map studentExamInfoMap) {
        this.studentExamInfoMap = studentExamInfoMap;
    }

    public ArrayList<String> getExamStringList() {
        return examStringList;
    }

    public void setExamStringList(ArrayList<String> examStringList) {
        this.examStringList = examStringList;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map getSubjectMap() {
        return subjectMap;
    }

    public void setSubjectMap(Map subjectMap) {
        this.subjectMap = subjectMap;
    }

    public Map getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map dataMap) {
        this.dataMap = dataMap;
    }

    public Map getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(Map studentMap) {
        this.studentMap = studentMap;
    }

    public Map getExamMap() {
        return examMap;
    }

    public void setExamMap(Map examMap) {
        this.examMap = examMap;
    }

    public ArrayList getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }

    public ArrayList getStudentExamList() {
        return studentExamList;
    }

    public void setStudentExamList(ArrayList studentExamList) {
        this.studentExamList = studentExamList;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public List<TermSet> getTermList() {
        return termList;
    }

    public void setTermList(List<TermSet> termList) {
        this.termList = termList;
    }
}
