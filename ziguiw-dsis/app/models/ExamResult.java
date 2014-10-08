package models;

import com.arj.ziguiw.common.utils.JsonUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User:lizhou
 * Date: 13-5-27
 * Time: P.M.3:36
 */

@Form(displayName = "考试")
public class ExamResult {
    @Field(displayName = "考试名称")
    public String examName;

    @Field(displayName = "考试时间")
    public Date examTime;

    @Field(displayName = "考试总分")
    public Float totalScore;

    @Field(displayName = "考试科目")
    public ArrayList<HashMap<String,Object>> subjectList = new ArrayList<HashMap<String, Object>>();

    @Field(displayName = "考试科目")
    public ArrayList<SubjectBean> subList = new ArrayList<SubjectBean>();
    /**
     * convert exam from List 2 Map:
     *
     * the List is : [   {考试名:"",考试时间:""，考试总分:"",考试科目:[{科目：数学,得分：95},{科目：语文,得分：98}] },   {第二次考试.....}]
     * the map is :{数学:[84,90,68],语文:[],英语。。。。 }
     *
     * @param examResults
     * @return
     */
    public  static HashMap<String,Object> convertExam(List<ExamResult> examResults) throws IOException {
        HashMap<String,Object> map = new HashMap<String, Object>();

        //拿出所以考试科目名称的list集合
        List<String> subjectNameList=  new ArrayList<String>();
        if(examResults != null && examResults.size() != 0){
            ExamResult examResult = examResults.get(0);
            if (examResult.subList != null && examResult.subList.size() != 0){
                SubjectBean subjectBean;
                for(int i = 0 ;i<examResult.subList.size();i++ ){
                    subjectBean = examResult.subList.get(i);
                    subjectNameList.add(subjectBean.subName);
                }
            }
        }
        //把科目名称塞到map里面
        for(int ii = 0;ii<subjectNameList.size();ii++){
            List<Float> subjectScoreList = getSubjectScore(subjectNameList.get(ii),examResults);
            map.put(subjectNameList.get(ii).toString(), JsonUtils.parse(subjectScoreList));
        }
     return map;
    }

    /**
     * 获得所有考试的中，某一的科目的成绩的集合
     * */
    private static List<Float> getSubjectScore(String subjectName,List<ExamResult> examResults){
        List<Float> score = new ArrayList<Float>();
        //遍历所有考试
        for(int i = 0 ;i <examResults.size();i++){
            ExamResult examResult = examResults.get(i);
            if(examResult != null && examResult.subList != null &&  examResult.subList.size() != 0){
                SubjectBean currentSubject;
                for(int j =0;j<examResult.subList.size();j++){
                    currentSubject = examResult.subList.get(j);
                    if(  subjectName.equals(currentSubject.subName) ){
                        score.add(currentSubject.subScore);
                    }
                }
            }
        }
        return score;
    }



         //获取page里面的考试信息的集合
         public static List<ExamResult> getExams(Page page) throws ParseException {
        List<ExamResult> list = new ArrayList<ExamResult>();

        if(page != null && page.data != null && page.data.size() != 0){

            ExamResult examResult;
            HashMap<String,Object> examMap;
            //循环每次考试的信息
            for(int i = 0;i<page.data.size(); i++){
                examResult =  new ExamResult();
                examMap= (HashMap<String,Object>)page.data.get(i);
                //考试总分
                Float totalScore = examMap.get("totalScore").toString()==null || examMap.get("totalScore").toString().equals("null")
                        ? 0.0f :new Float(examMap.get("totalScore").toString());
                //若当前考试总分totalScore为0.0，表示没有参加该次考试
                examResult.totalScore = totalScore;

                //考试名称
                examResult.examName = (String) examMap.get("examName");
                //考试时间
                Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse((String)examMap.get("examTime"));
                examResult.examTime = date;
                //考试科目
                ArrayList arrayList =(ArrayList) examMap.get("subjectList");
                if(arrayList != null && arrayList.size() != 0){
                    HashMap<String,Object> subjectMap;
                    for(int j=0;j<arrayList.size();j++){
                        subjectMap = (HashMap<String,Object>)arrayList.get(j);
                        examResult.subjectList.add(subjectMap);
                    }
                }
                list.add(examResult);
            }
        }
        return list;
    }

    public static List<String> getExamName(List<ExamResult> exams) {
        List<String> list = new ArrayList<String>();
        if(exams != null && exams.size() != 0){
            for(int i = 0;i<exams.size();i++){
                list.add(exams.get(i).examName);
            }
        }
        return list;
    }


    /**
     *
     * @param exams 从成绩接口中返回的考试数据
     * @param subjects  该学校的所有科目
     * @return 考试数据,包含该学校所有科目
     */
    public static List<ExamResult> convertExam(List<ExamResult> exams, List<SchoolSubject> subjects) throws ParseException {
        List<ExamResult> examResults = new ArrayList<ExamResult>();
        if(exams == null || exams.size() == 0 ){
            return examResults;
        }else {
            //遍历所有考试
            ExamResult examResult=null;
            ArrayList<SubjectBean> subList = null;
            for(int i=0;i<exams.size();i++){
                examResult = new ExamResult();
                examResult.examName = exams.get(i).examName;
                examResult.examTime = exams.get(i).examTime;
                examResult.totalScore =exams.get(i).totalScore;
                List<HashMap<String,Object>> subjectList = exams.get(i).subjectList;

                //subList包含该学校的所有科目
                subList  = new ArrayList<SubjectBean>();
                if(subjects != null && subjects.size()!=0){
                    SubjectBean subjectBean = null;
                    for (int j =0;j<subjects.size();j++){
                        subjectBean = new SubjectBean();
                        subjectBean.subId = subjects.get(j).subjectId;
                        subjectBean.subName = subjects.get(j).subjectName;
                        subjectBean.subAvg = 0.0f;
                        subjectBean.subScore = 0.0f;
                        subjectBean.subTotal = 0.0f;
                        subList.add(subjectBean);
                    }
                }

                //subjectList是该次考试的实际考试科目
                ArrayList<SubjectBean> tempList = new ArrayList<SubjectBean>();
                //subList包含该学校的所有科目
                if(subList != null && subList.size() != 0){
                    SubjectBean subjectBean=null;
                     for(int j =0 ;j<subList.size();j++){
                         subjectBean = getSubjectBean(subList.get(j),subjectList);
                         tempList.add(subjectBean);
                     }
                }
                examResult.subList = tempList;
                examResults.add(examResult);
            }
            return examResults;
        }

    }

    /**
     * 根据学校的某一科目，去判断成绩接口中是否包含此科目成绩信息
     * @param subjectBean 数据库中的某一科目
     * @param subjectList 成绩接口中的所有考试科目
     * @return subjectBean
     */
    private static SubjectBean getSubjectBean(SubjectBean subjectBean, List<HashMap<String,Object>> subjectList){
        if(subjectList != null && subjectList.size() != 0){
            SubjectBean sb =null;
            //subjectList包含接口中的所有考试科目信息
            for(int i = 0 ;i<subjectList.size();i++){
                sb = convertSubject(  subjectList.get(i) );
                if(subjectBean.subId != sb.subId && !subjectBean.subName.equals(sb.subName)){
                    continue;
                }else {
                    return sb;
                }
            }

             return subjectBean;
        }
        return null;
    }

    /**
     * 科目类型转换
     * @param subjectMap 接口中的科目信息 hashMap类型
     * @return SubjectBean 实体对象类型
     */
    private static SubjectBean convertSubject(HashMap<String, Object> subjectMap) {
        SubjectBean subjectBean =new SubjectBean();
        if(subjectMap != null && subjectMap.size()!=0 ){
            subjectBean.subId = getByName("subId",subjectMap) == null ? 0: new Integer(getByName("subId",subjectMap));
            subjectBean.subName = getByName("subName",subjectMap);
            subjectBean.subScore = getByName("subScore",subjectMap) == null ? 0.0f : new Float(getByName("subScore",subjectMap));
            subjectBean.subAvg = getByName("subAvg",subjectMap) == null ? 0.0f :new Float(getByName("subAvg",subjectMap));
            subjectBean.subTotal = getByName("subTotal",subjectMap) ==null ? 0.0f :new Float(getByName("subTotal",subjectMap));
        }
        return subjectBean;
    }
    private static String getByName(String name,HashMap<String, Object> subjectMap){
        if(subjectMap != null && subjectMap.size()!=0 ){
            String str = subjectMap.get(name).toString().trim();
            if(str != null && !"".equals(str) && !"null".equalsIgnoreCase(str)){
                 return str;
            }
        }
        return null;
    }

    /***
     * 获取总分
     * @param exams
     * @return
     */
    public static List<ExamResult> getTotalScore(List<ExamResult> exams) {
            for(ExamResult examResult:exams){
                float totalScore = 0F;
                if(examResult.subList!= null && examResult.subList.size() != 0){
                     for(SubjectBean subjectBean:examResult.subList){
                         totalScore += subjectBean.subScore;
                     }
                }
                examResult.totalScore = totalScore ;
            }
            return exams;
    }
}
