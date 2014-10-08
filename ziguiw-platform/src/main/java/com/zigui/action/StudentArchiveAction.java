package com.zigui.action;

import com.zigui.domain.*;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-4
 * Time: A。M。9:16
 */
//处理学生学籍档案业务的类
public class StudentArchiveAction extends  BaseAction implements ServletContextAware {
    private static final Log log = LogFactory.getLog("dsis");
    private Parent parent;
    private Student student;
    private Teacher teacher;
    private Integer userType;
    private Long foreignKey;
    private List<Student> students;
    private Page<Student> studentPage=new Page<Student>();
    private Clasz clasz=new Clasz();
    private List<TeacherClasz> teacherClasses=new ArrayList<TeacherClasz>();

    private List<Clasz> claszList=new ArrayList<Clasz>();
    private Page<Clasz> classPage=new Page<Clasz>();
    private Map<String, Object> map;

    private long classId;
    private String njid;
    private int studentGradeId;
    private Grade grade;
    private List<Grade> gradeList;
    private int studentId=0;
    private Object[] queryObj = new Object[0];

    private Integer countPerPage = 20;
    private Integer currentPage = 1;
    private Result result;
    private String gotoPage="1";

    //学籍档案操作
    private String xsIds;
    private String status;
    private List pageList=new ArrayList();
    private Integer totalPages;

    //查询学生的学籍档案信息
    public String showXueJi(){
        System.out.println("StudentArchiveAction's showXueJi method is running...");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            if(userTemp == null || userTemp.getId() <= 0L)  {
                return INPUT;
            }
            user=userService.getUserBaseById(userTemp.getId());
            foreignKey=user.getForeignKey();
            userType=user.getType();
            if(userTemp==null||userTemp.getId()<0L||user.getForeignKey()<0L){
                return INPUT;
            }
            if(userType==2){
                //如果是老师，需要把老师带的所有班级的所有学生信息显示到列表页面
                teacher=teacherService.getById(foreignKey);
                if(teacher.getSchool()==null|| teacher.getSchool().getXxID().equals(" ")||teacher.getSchool().getXxID()==null){
                    return "teacherNone";
                }
                //获取老师带的班级集合,只有通过班级才能获取到年级
                String  hql="select c from Clasz c where c.Bj_id in(select t.clasz from TeacherClasz t " +
                       "where t.teacherID="+teacher.getTeacherID()+")";
                System.out.println("the hql you're visiting is="+hql);
                Page<Object> objectPage= commonService.getByHql(hql, new Integer(currentPage),
                        new Integer(countPerPage), queryString, queryObj);
                classPage=CommonUtils.transform(Clasz.class, objectPage);
                claszList=classPage.getList();
                if(claszList==null || claszList.size()==0){
                    return   "teacherNone";
                }
                //获取老师所带的年级集合
                gradeList=new ArrayList<Grade>();
                for(int i=0;i<claszList.size();i++){
                    if( " ".equals(claszList.get(i).getNj_id() )){
                        return "teacherNone";
                    }
                    grade=new Grade();
                    grade=nianJiService.getById(claszList.get(i).getNj_id());
                    gradeList.add(grade);
                }
                request.setAttribute("gradeList",gradeList);
                request.setAttribute("classPage",classPage);
                 return "classXueJi";
            }
            //显示单个学生的详细信息到详细页面
            if(userType==3){
                parent=parentService.getById(foreignKey.intValue());
                students=parentService.findChildren(parent.getMobliephone());
                if(students == null || students.size() == 0){
                    return "studentXueJi";
                }
                if(studentId <=0){
                    student=students.get(0);
                }else {
                    student=studentService.getById(studentId);
                }
                clasz=student.getClasz();
                if(clasz==null){
                     return "studentXueJi";
                }
                Integer nj_id=clasz.getNj_id();
                grade=nianJiService.getById(nj_id);
                return "studentXueJi";
            }
            if(userType==4){
                student=studentService.getById(foreignKey.intValue());
                if(student==null){
                    return ERROR;
                }
                clasz=student.getClasz();
                Integer nj_id=clasz.getNj_id();
                grade=nianJiService.getById(nj_id);
                return "studentXueJi";
            }
            return NONE;
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    //按照班级id查询学生信息
    public String queryStudentsXueJiByClassId(){
        System.out.println("StudentArchiveAction's queryStudentsXueJiByClassId method is running...classId="+classId+",njid="+njid);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session=request.getSession();
        try{
            UserBase userTemp=(UserBase)session.getAttribute("VALID_USER");
            user=userService.getUserBaseById(userTemp.getId());
            if(userTemp == null || user == null || userTemp.getId() < 0L || user.getForeignKey()<0L || user.getType() != 2){
                return INPUT;
            }
            if(classId  <=0L ){
                return "NoStudentArchives";
            }
            clasz= classService.getById(classId);

            System.out.println("gotoPage=" + gotoPage);
            if(gotoPage !=null && !"".equals(gotoPage)){
                studentPage=teacherService.getStudentsByClassId(classId,new Integer(gotoPage));
            } else {
                studentPage=teacherService.getStudentsByClassId(classId);
            }
            request.setAttribute("studentPage",studentPage);
            totalPages=studentPage.getPageCount();
            pageList=new ArrayList();
            for(int i=0;i<studentPage.getPageCount();i++){
                pageList.add(i+1);
            }
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
           throw new RuntimeException(e) ;
        }
    }

    //学籍异动
    public  String changeSchoolStatus(){
        String studentId[]  = xsIds.split("\\|");
        if( new Integer(status) == Constant.STUDENT_ARCHIVE_QUIT){  //退学
            studentArchiveService.changeSchoolStatus(studentId,status);
        }else  if( new Integer(status) == Constant.STUDENT_ARCHIVE_RETURN){ //复学
            studentArchiveService.changeSchoolStatus(studentId,status);
        }else  if( new Integer(status) == Constant.STUDENT_ARCHIVE_BREAK){  //休学
            studentArchiveService.changeSchoolStatus(studentId,status);
        }else  if( new Integer(status) == Constant.STUDENT_ARCHIVE_TURN){   //转学
            studentArchiveService.changeSchoolStatus(studentId,status);
        }
        return NONE;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Page<Student> getStudentPage() {
        return studentPage;
    }

    public void setStudentPage(Page<Student> studentPage) {
        this.studentPage = studentPage;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
    }

    public List<TeacherClasz> getTeacherClasses() {
        return teacherClasses;
    }

    public void setTeacherClasses(List<TeacherClasz> teacherClasses) {
        this.teacherClasses = teacherClasses;
    }

    public List<Clasz> getClaszList() {
        return claszList;
    }

    public void setClaszList(List<Clasz> claszList) {
        this.claszList = claszList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public int getStudentGradeId() {
        return studentGradeId;
    }

    public void setStudentGradeId(int studentGradeId) {
        this.studentGradeId = studentGradeId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Object[] getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(Object[] queryObj) {
        this.queryObj = queryObj;
    }


    @Override
    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Page<Clasz> getClassPage() {
        return classPage;
    }

    public void setClassPage(Page<Clasz> classPage) {
        this.classPage = classPage;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }



    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public String getNjid() {
        return njid;
    }

    public void setNjid(String njid) {
        this.njid = njid;
    }


    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(Integer countPerPage) {
        this.countPerPage = countPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(String gotoPage) {
        this.gotoPage = gotoPage;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getXsIds() {
        return xsIds;
    }

    public void setXsIds(String xsIds) {
        this.xsIds = xsIds;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
