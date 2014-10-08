package controllers;

import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-25
 * Time: P.M.3:51
 * 学生成绩查询
 */
public class ExamResults extends Application {

    private static final String STUDENT_URL = (String) Play.configuration.getProperty("ExamResults_student_url");
    private static final String TEACHER_URL = (String) Play.configuration.getProperty("ExamResults_teacher_url");
    private static final String DSIS_RPC_URL = (String) Play.configuration.getProperty("dsis_rpc_url");
    private static final Log log = LogFactory.getLog(ExamResults.class);

    //老师身份查询学生成绩
    public static void tlist(Integer isAjax, Integer bjId, Integer termId, Integer examId)throws InterruptedException, ParseException {
        UserBase userBase = (UserBase)renderArgs.get("userBase");
        List<Clazz> clazzList = Teacher.findClazz(userBase.foreignKey.intValue());
        try {
            if (isAjax != null) {
                Clazz clazz = getClazzInfo(clazzList,bjId);
                TermSet termSet = getTermInfo(clazz,termId);
                Exam exam = getExamInfo(termSet,clazz,examId);
                String url = String.format(DSIS_RPC_URL + TEACHER_URL+"?bjid=%s&njid=%s&examId=%s&pages=%s&pagesize=%s&startTime=%s&endTime=%s" ,
                        clazz.bjId, clazz.njId, exam.examId, getPage(), getPageSize(), termSet.holidayBegin.getTime(), termSet.holidayEnd.getTime());
                Page page = getDataByRpc(url);
                render("ExamResults/tlist_ajax.html");
           } else {
                render(clazzList);
           }
        } catch (RpcResponseFormatException e) {
            throw new DsisException(e, isAjax == null ? false : true);
        }
    }

    //学生、家长身份查询学生成绩
    public static void plist(Integer xsId,Integer termId, Integer examId, Integer isAjax )
            throws InterruptedException, ParseException, IOException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        Clazz clazz = Clazz.findById(student.bjId);
        School school = School.find("byXxId",student.xxId).first();
        Grade grade = Grade.findById(clazz.njId);
        String gradeName = GradeNameUtils.transform(grade.njMcheng);
        GradeResource gradeResource =GradeResource.findByName(gradeName);
        TermSet termSet = getTermInfo(clazz,termId);
        try{
                String  url = String.format(DSIS_RPC_URL+STUDENT_URL+"?studentid=%s&termId=%s",student.xsId,termSet.termId);
                //根据接口地址获取数据
                Page page = getDataByRpc(url);
                //1、成绩表格
                //获取考试的集合
                List<ExamResult> exams = ExamResult.getExams(page);
                //获取该学校的所有科目名称
                List<SchoolSubject> subjects  = SchoolSubject.findByXxId(school.xxId);
                exams = ExamResult.convertExam(exams,subjects);
                //获取总分
                exams = ExamResult.getTotalScore(exams);


                //2、成绩曲线图
                //获取每个科目的成绩
                HashMap<String,Object>   subjectScore = ExamResult.convertExam(exams);
                //考试名称的集合
                List<String> examNameList = ExamResult.getExamName(exams);
            if (isAjax != null) {
                render("ExamResults/plist_ajax.html", page, termSet, clazz, school, grade, gradeResource,subjectScore, exams, examNameList);
            } else {
                List<TermSet> termSetList = TermSet.find("byXxId",clazz.xxId).fetch();
                render(page,studentList,clazz,school,grade,gradeResource,subjectScore,exams,examNameList,termSetList,termSet);
            }
        } catch (RpcResponseFormatException e){
            throw new DsisException(e,isAjax == null ? false : true);
        }
    }


    //用户点击成绩曲线图的某个节点,发送ajax请求，返回推荐的资源
    public static void checkExamResult(Integer xsId,String examName,String subjectName,Float score,Integer termId,String gradeName)
            throws ParseException, RpcResponseFormatException {
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        String  url = String.format(DSIS_RPC_URL+STUDENT_URL+"?studentid=%s&termId=%s",student.xsId,termId);
        //根据接口地址获取数据
        boolean  flag = false;
        String message = "";
        Page page = getDataByRpc(url);
        //1、成绩表格
        //获取考试的集合
        List<ExamResult> exams = ExamResult.getExams(page);
        //获取该学校的所有科目名称
        List<SchoolSubject> subjects  = SchoolSubject.findByXxId(student.xxId);
        exams = ExamResult.convertExam(exams,subjects);
        //获取总分
        exams = ExamResult.getTotalScore(exams);



        if(exams != null && exams.size() != 0){
            //遍历每次考试
            for(ExamResult exam:exams){
                //接口里面获取的数据与页面传过来的考试名称是否一致
                examName = examName.trim();
//                    System.out.println("examName="+examName+",exams.get(i).examName="+exams.get(i).examName+",equals? ="+examName.equals(exams.get(i).examName));
                if( examName.equals(exam.examName)  &&  exam.subList != null  &&  exam.subList.size() != 0 ){
                    //遍历该次考试的所有科目
                   for(SubjectBean subjectBean :exam.subList){
                        if(subjectName.equals(subjectBean.subName)){
                            //比较本次考试当前科目的学生得分与班级平均分
                            if(score == 0.0F){
                                message = String.format("本次考试没有考试该科目:%s",subjectName);
                            }else if(score < subjectBean.subAvg){
                                flag = true;
                                message = String.format("本次考试%s得分:%s,低于班级平均分(%s):，我们给您推荐了如下的学习资源:",subjectName,score,subjectBean.subAvg);
                            } else if(score < subjectBean.subTotal * 0.6){
                                flag = true;
                                message =String.format("本次考试%s得分:%s,及格分数为(%s)，我们给您推荐了如下的学习资源:",subjectName,score,subjectBean.subTotal*0.6);
                            }else if( score >subjectBean.subTotal*0.6 && score > subjectBean.subAvg ){
                                flag = true;
                                message = String.format("本次考试%s得分:%s,成绩优异,你可能会对以下的资源感兴趣:",subjectName,score);
                            }
                        }
                   }
                }
            }
        }
        HashMap<Subject,Page<Resource>>  resourceMap = new HashMap<Subject, Page<Resource>>();
        if(flag){
            GradeResource gradeResource = GradeResource.findByName(gradeName);
            Subject subject = Subject.findByName(subjectName);
            if(gradeResource != null && subject != null){
                Page<Resource> resourcePage1= Resource.findByGradeAndSubject(gradeResource.srcId,subject.srcId,1,10);
                resourceMap = new HashMap<Subject, Page<Resource>>();
                resourceMap.put(subject,resourcePage1);
            }
        }
        render("ExamResults/recommendSource.html",resourceMap,message);
    }





    @UnSecure
    public static void wapplist(Integer wapXsId,Integer termId, Integer examId,Integer isAjax)
            throws InterruptedException, ParseException{
        UserBase wapUserBase = wapGetUserInfo();
        Map<String, Object> map = getChildrenInfo(wapUserBase,wapXsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        Clazz clazz = Clazz.findById(student.bjId);
        TermSet termSet = getTermInfo(clazz,termId);
        Exam exam = getExamInfo(termSet,clazz,examId);
        String url = String.format(DSIS_RPC_URL + STUDENT_URL+"?studentid=%s&termId=%s" ,student.xsId,exam.examId,
                termSet.holidayBegin.getTime(),termSet.holidayEnd.getTime());
        try{
            Page page=getDataByRpc(url);

            //在page对象里面获取考试科目的集合
            List<HashMap<String,Object>> subjectList = getSubjectList(page);

               //在page对象里面获取成绩的集合
            Map<String,Object> scoreMap = getScoreMap(page, subjectList);

            //在page对象里面获取总分
            String totalScore = getTotalScore(page);

            if (isAjax != null) {
                render("ExamResults/wapplist_ajax.html",page,subjectList,scoreMap,student,totalScore);
            } else {
                List<TermSet> termSetList = TermSet.find("byXxId",clazz.xxId).fetch();
                render(studentList,student,clazz,page,termSetList);
            }
        } catch (RpcResponseFormatException e){
            throw new DsisException(e,isAjax == null ? false : true);
        }
    }

    //在page对象里面获取总分
    private static String getTotalScore(Page page) {
        String totalScore="0.0";
        if(page.data != null && page.data.size() > 1){
            List dataList = (ArrayList)page.data.get(1);
            Map<String,Object> dataMap = (HashMap<String,Object>)dataList.get(0);
            if(dataMap.get("alltotalScore") != null){
                totalScore = dataMap.get("alltotalScore").toString();
                totalScore.substring(0,totalScore.length());
            }
        }
        return totalScore;
    }

    //在page对象里面获取考试科目的集合
    private static List<HashMap<String,Object>> getSubjectList(Page page) {
        List<HashMap<String,Object>> subjectList = new ArrayList<HashMap<String,Object>>();
        if(page.data != null && page.data.size() > 1){
            List tempList = (ArrayList)page.data.get(0);
            if(tempList != null && tempList.size() != 0 ){
                for(int i = 0 ; i< tempList.size(); i++){
                    if(tempList.get(i) == null ){
                        subjectList.add(new HashMap<String,Object>());
                    } else {
                        subjectList.add((HashMap<String,Object>)tempList.get(i));
                    }
                }
            }
        }
        return subjectList;
    }

    //在page对象里面获取成绩的集合
    private static Map<String, Object> getScoreMap(Page page, List<HashMap<String,Object>> subjectList) {
        Map<String ,Object>  map = new HashMap<String,Object>();
        for (int i = 0; i < subjectList.size(); i++){
                String subjectName =  subjectList.get(i).size() == 0 ? "科目" : subjectList.get(i).get("subjectName").toString();
                List dataList = (ArrayList)page.data.get(1);
                Map<String,Object> dataMap = (HashMap<String,Object>)dataList.get(0);
                int index =i+1;
                map.put(subjectName ,dataMap.get("cj"+index));
        }
        return map;
    }

}
