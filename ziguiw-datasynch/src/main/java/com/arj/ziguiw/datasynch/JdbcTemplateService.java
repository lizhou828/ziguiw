package com.arj.ziguiw.datasynch;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-20
 * Time: P.M 2:35
 */
@Service(value = "jdbcTemplateService")
public class JdbcTemplateService {

    @Autowired
    @Qualifier(value = "jdbcTemplate")
    private  JdbcTemplate jdbcTemplate;
    private static final Log log_all = LogFactory.getLog(JdbcTemplateService.class);
    private static final Log log_th = LogFactory.getLog("teacher");
    private static final Log log_st = LogFactory.getLog("student");
    private static final Log log_pr = LogFactory.getLog("parent");
    private static final Log log_sh = LogFactory.getLog("school");
    private static final Log log_cl = LogFactory.getLog("class");
    private static final Log log_tc = LogFactory.getLog("teacherClass");
    private static final Log log_ex = LogFactory.getLog("exam");
    private static final Log log_termSet = LogFactory.getLog("termSet");
    private static final Log log_grade = LogFactory.getLog("grade");


    public Long findLastModifiedTime(String tableName) {
        String sql = String.format("select max(lastmodified) from %s",tableName);
        log_all.info(sql);
        return jdbcTemplate.queryForLong(sql);
    }
   public  void teacherInsert(Map<String, Object> teacherMap) {
       String  sqlInsert = String.format("insert into t_teacherinfo(teacherid,user_id,bm_id,name,sex,birthday," +
               "jobtilte,education,graduatesch,duties,resume,photo,officephone,fax,homephone,moblie,email," +
               "zipcode,address,otherlinks,xxid,lastmodified) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
               "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",teacherMap.get("TEACHERID"),teacherMap.get("USER_ID"),teacherMap.get("DEPT_ID"),
               isString(teacherMap,"NAME"),teacherMap.get("SEX"),isString(teacherMap,"BIRTHDAY"),teacherMap.get("JOBTILTE"),
               teacherMap.get("EDUCATION"),isString(teacherMap,"GRADUATESCH"),isString(teacherMap,"DUTIES"),isString(teacherMap,"RESUME"),
               isString(teacherMap,"PHOTO"),isString(teacherMap,"OFFICEPHONE"),isString(teacherMap,"FAX"),isString(teacherMap,"HOMEPHONE"),
               isString(teacherMap,"MOBLIE"),isString(teacherMap,"EMAIL"),isString(teacherMap,"ZIPCODE"),isString(teacherMap,"ADDRESS"),
               isString(teacherMap,"OTHERLINKS"),isString(teacherMap,"XXID"),getDateLong(teacherMap,"lastmodifytime"));
       log_th.info(sqlInsert);
       jdbcTemplate.update(sqlInsert);
   }

   public void teacherUpdate(Map<String, Object> teacherMap) {
       String  sqlUpdate = String.format("update t_teacherinfo set user_id=%s,bm_id=%s,name=%s,sex=%s,birthday=%s,jobtilte=%s," +
               "education=%s,graduatesch=%s,duties=%s,resume=%s,photo=%s,officephone=%s,fax=%s,homephone=%s,moblie=%s," +
               "email=%s,zipcode=%s,address=%s,otherlinks=%s,xxid=%s,lastmodified=%s where teacherid=%s",
               teacherMap.get("USER_ID"),teacherMap.get("DEPT_ID"),isString(teacherMap,"NAME"),teacherMap.get("SEX"),
               isString(teacherMap,"BIRTHDAY"),teacherMap.get("JOBTILTE"),teacherMap.get("EDUCATION"),
               isString(teacherMap,"GRADUATESCH"),isString(teacherMap,"DUTIES"),isString(teacherMap,"RESUME"),
               isString(teacherMap,"PHOTO"),isString(teacherMap,"OFFICEPHONE"),isString(teacherMap,"FAX"),isString(teacherMap,"HOMEPHONE"),
               isString(teacherMap,"MOBLIE"),isString(teacherMap,"EMAIL"),isString(teacherMap,"ZIPCODE"),isString(teacherMap,"ADDRESS"),
               isString(teacherMap,"OTHERLINKS"),isString(teacherMap,"XXID"),getDateLong(teacherMap,"lastmodifytime"),teacherMap.get("TEACHERID"));
       log_th.info(sqlUpdate);
       jdbcTemplate.update(sqlUpdate);
   }

   public void studentInsert(Map<String, Object> studentMap) {
       String sqlInsert = String.format("insert into t_student(XS_ID,XJ_BHAO,XS_XMING,BIRTHPLACE,POLITICALFACE,IDCARD," +
               "ACCOUNTPLACE,SEX,BIRTHDAY,BJ_ID,ACCOMMODATION,DATEINTEGEROSCH,MZHU,HOBBY,PERSONALPHOTO,HOMEPHONE," +
               "ZIP,HOMEADDRESS,OTHERLINKS,HEALTHSTATE,XXID,GROUPID,BANGANBUID,YBHAO,DATETOSCHOOL,STATUS,lastmodified" +
               ") values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
               studentMap.get("XS_ID"),isString(studentMap,"XJ_BHAO"),isString(studentMap,"XS_XMING"),isString(studentMap,"BIRTHPLACE"),
               isString(studentMap,"POLITICALFACE"),isString(studentMap,"IDCARD"),isString(studentMap,"ACCOUNTPLACE"),isString(studentMap,"SEX"),
               isDate(studentMap,"BIRTHDAY"),studentMap.get("BJ_ID"),isString(studentMap,"ACCOMMODATION"),isDate(studentMap,"DATEINTOSCH"),
               isString(studentMap,"MZHU"),isString(studentMap,"HOBBY"),isString(studentMap,"PERSONALPHOTO"),isString(studentMap,"HOMEPHONE"),
               isString(studentMap,"ZIP"),isString(studentMap,"HOMEADDRESS"),isString(studentMap,"OTHERLINKS"),isString(studentMap,"HEALTHSTATE"),
               isString(studentMap,"XXID"),studentMap.get("GROUPID"),isString(studentMap,"BANGANBUID"),isString(studentMap,"YBHAO"),isDate(studentMap,"DATEINTOSCH"),
               isString(studentMap,"STATUS"),getDateLong(studentMap,"lastmodifytime"));
       log_st.info(sqlInsert);
       jdbcTemplate.update(sqlInsert);
   }

   public void studentUpdate(Map<String, Object> studentMap) {
       String sqlUpdate = String.format("update t_student set XJ_BHAO=%s,XS_XMING=%s,BIRTHPLACE=%s,POLITICALFACE=%s,IDCARD=%s," +
               "ACCOUNTPLACE=%s,SEX=%s,BIRTHDAY=%s,BJ_ID=%s,ACCOMMODATION=%s,DATEINTEGEROSCH=%s,MZHU=%s," +
               "HOBBY=%s,PERSONALPHOTO=%s,HOMEPHONE=%s,ZIP=%s,HOMEADDRESS=%s," +
               "OTHERLINKS=%s,HEALTHSTATE=%s,XXID=%s,GROUPID=%s,BANGANBUID=%s,YBHAO=%s,DATETOSCHOOL=%s," +
               "STATUS=%s,lastmodified=%s where XS_ID=%s",
               isString(studentMap,"XJ_BHAO"),isString(studentMap,"XS_XMING"),isString(studentMap,"BIRTHPLACE"),
               isString(studentMap,"POLITICALFACE"),isString(studentMap,"IDCARD"),isString(studentMap,"ACCOUNTPLACE"),isString(studentMap,"SEX"),
               isDate(studentMap,"BIRTHDAY"),studentMap.get("BJ_ID"),isString(studentMap,"ACCOMMODATION"),isDate(studentMap,"DATEINTOSCH"),
               isString(studentMap,"MZHU"),isString(studentMap,"HOBBY"),isString(studentMap,"PERSONALPHOTO"),isString(studentMap,"HOMEPHONE"),
               isString(studentMap,"ZIP"),isString(studentMap,"HOMEADDRESS"),isString(studentMap,"OTHERLINKS"),isString(studentMap,"HEALTHSTATE"),
               isString(studentMap,"XXID"),studentMap.get("GROUPID"),isString(studentMap,"BANGANBUID"),isString(studentMap,"YBHAO"),isDate(studentMap,"DATEINTOSCH"),
               isString(studentMap,"STATUS"),getDateLong(studentMap,"lastmodifytime"),studentMap.get("XS_ID"));
       log_st.info(sqlUpdate);
       jdbcTemplate.update(sqlUpdate);
   }

   public void schoolInsert(Map<String, Object> schoolMap) {
       String sqlInsert = String.format("insert into t_SCHOOLinfo(XX_ID,XXID,SCH_NAME,LINKMAN,CONTACTPHONE," +
               "AGENT_ID,DBNAME,CONNSTR,PROID,CITYID,REAMRKS,lastmodified) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
               schoolMap.get("xx_Id"),isString(schoolMap,"xxid"),isString(schoolMap,"schName"),
               isString(schoolMap,"linkman"),isString(schoolMap,"contactphone"),schoolMap.get("dlId"),isString(schoolMap,"dbname"),
               isString(schoolMap,"connstr"),schoolMap.get("proid"),schoolMap.get("cityid"),isString(schoolMap,"reamrks"),getDateLong(schoolMap,"lastmodifytime"));
       log_sh.info(sqlInsert);
       jdbcTemplate.update(sqlInsert);
   }

   public void schoolUpdate(Map<String, Object> schoolMap) {
       String sqlUpdate = String.format("update t_SCHOOLinfo set XXID=%s,SCH_NAME=%s,LINKMAN=%s,CONTACTPHONE=%s,AGENT_ID=%s,DBNAME=%s," +
               "CONNSTR=%s,PROID=%s,CITYID=%s,REAMRKS=%s,lastmodified=%s where XX_ID=%s",
               isString(schoolMap,"xxId"),isString(schoolMap,"schName"),
               isString(schoolMap,"linkman"),isString(schoolMap,"contactphone"),schoolMap.get("dlId"),isString(schoolMap,"dbname"),
               isString(schoolMap,"connstr"),schoolMap.get("proid"),schoolMap.get("cityid"),isString(schoolMap,"reamrks"),getDateLong(schoolMap,"lastmodifytime"),schoolMap.get("xx_Id"));
       log_sh.info(sqlUpdate);
       jdbcTemplate.update(sqlUpdate);
   }

   public void parentInsert(Map<String, Object> parentMap) {
       String sqlInsert = String.format("insert into t_PARENTinfo(PARENTS_ID,XS_ID,PARENTS_NAME,BIRTHDAY,OFFICEPHONE," +
               "MOBLIEPHONE,EMAIL,PARENTS_CALLER,PROFESSIONAL,WORKADDRESS,REMARKS,lastmodified,ACCOUNT_STATE)" +
               " values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
               parentMap.get("parentsId"),parentMap.get("xsId"),isString(parentMap,"parentsName"),
               isDate(parentMap,"birthday"),isString(parentMap,"officephone"),isString(parentMap,"mobliephone"),isString(parentMap,"email"),
               isString(parentMap,"PARENTS_CALLER"),parentMap.get("professional"),isString(parentMap,"workaddress"),isString(parentMap,"remarks"),getDateLong(parentMap,"lastmodifytime"),
               parentMap.get("ACCOUNT_STATE"));
       log_pr.info(sqlInsert);
       jdbcTemplate.update(sqlInsert);
   }

   public void parentUpdate(Map<String, Object> parentMap) {
       String sqlUpdate = String.format("update t_PARENTinfo set XS_ID=%s,PARENTS_NAME=%s,BIRTHDAY=%s,OFFICEPHONE=%s,MOBLIEPHONE=%s," +
               "EMAIL=%s,PARENTS_CALLER=%s,PROFESSIONAL=%s,WORKADDRESS=%s,REMARKS=%s,lastmodified=%s,ACCOUNT_STATE=%s where PARENTS_ID=%s",
               parentMap.get("xsId"),isString(parentMap,"parentsName"),isDate(parentMap,"birthday"),
               isString(parentMap,"officephone"),isString(parentMap,"mobliephone"),isString(parentMap,"email"),isString(parentMap,"PARENTS_CALLER"),
               parentMap.get("professional"),isString(parentMap,"workaddress"),isString(parentMap,"remarks"),getDateLong(parentMap,"lastmodifytime"),
               parentMap.get("ACCOUNT_STATE"),parentMap.get("parentsId"));
       log_pr.info(sqlUpdate);
       jdbcTemplate.update(sqlUpdate);
   }

   public void userBaseSaveTeacher(Map<String,Object> teacherMap) {
       String  sqlInsertUserBase = String.format("insert into user_base(ID,NICK_NAME,PASSWD,STATE,TYPE,REAL_NAME,FOREIGN_KEY ) " +
               "values(%s,%s,%s,%s,%s,%s,%s)",getUserBaseSeq(),
               isString(teacherMap,"ACCOUT"),isString(teacherMap,"PASS"),5,2,isString(teacherMap,"NAME"),teacherMap.get("TEACHERID"));
       log_th.info(sqlInsertUserBase);
       jdbcTemplate.update(sqlInsertUserBase);
   }

    public void userBaseUpdateTeacher(Map<String,Object> teacherMap) {
        String  sqlUpdateUserBase = String.format("update user_base set STATE=%s," +
                "TYPE=%s,REAL_NAME=%s,FOREIGN_KEY=%s where  NICK_NAME=%s" ,
                5,2,isString(teacherMap,"NAME"),teacherMap.get("TEACHERID"),isString(teacherMap,"ACCOUT"));
        log_th.info(sqlUpdateUserBase);
        jdbcTemplate.update(sqlUpdateUserBase);
    }

    public void userBaseSaveStudent(Map<String,Object> studentMap) {
        String  sqlInsertUserBase = String.format("insert into user_base(ID,NICK_NAME,PASSWD,STATE,TYPE,REAL_NAME,FOREIGN_KEY ) " +
                "values(%s,%s,%s,%s,%s,%s,%s)",getUserBaseSeq(),
                isString(studentMap,"CARDID"), "'"+DigestUtils.md5Hex("952116")+"'",5,4,isString(studentMap,"XS_XMING"),studentMap.get("XS_ID"));
        log_st.info(sqlInsertUserBase);
        jdbcTemplate.update(sqlInsertUserBase);
    }

    public void userBaseUpdateStudent(Map<String,Object> studentMap) {
        String  sqlUpdateUserBase = String.format("update user_base set PASSWD=%s,STATE=%s,TYPE=%s," +
                "REAL_NAME=%s,FOREIGN_KEY=%s where nick_name = %s  " ,
                "'"+DigestUtils.md5Hex("952116")+"'",5,4,isString(studentMap,"XS_XMING"),studentMap.get("XS_ID"),isString(studentMap,"CARDID"));
        log_st.info(sqlUpdateUserBase);
        jdbcTemplate.update(sqlUpdateUserBase);
    }

    public void userBaseSaveSchool(Map<String,Object> Map) {
        String  sqlInsertUserBase = String.format("insert into user_base(ID,NICK_NAME,PASSWD,STATE,TYPE,REAL_NAME,FOREIGN_KEY ) " +
                "values(%s,%s,%s,%s,%s,%s,%s)",getUserBaseSeq(),
                isString(Map,"xxid"),"'"+DigestUtils.md5Hex("952116")+"'",5,1,isString(Map,"schName"),Map.get("xx_Id"));
        log_sh.info(sqlInsertUserBase);
        jdbcTemplate.update(sqlInsertUserBase);
    }

    public void userBaseUpdateSchool(Map<String,Object> Map) {
        String  sqlUpdateUserBase = String.format("update user_base set PASSWD=%s,STATE=%s,TYPE=%s," +
                "REAL_NAME=%s,FOREIGN_KEY=%s where nick_name = %s" ,
                 "'"+DigestUtils.md5Hex("952116")+"'",5,1,isString(Map,"schName"),Map.get("xx_Id"),isString(Map,"xxid"));
        log_sh.info(sqlUpdateUserBase);
        jdbcTemplate.update(sqlUpdateUserBase);
    }

    public void userBaseSaveParent(Map<String,Object> Map) {
        String  sqlInsertUserBase = String.format("insert into user_base(ID,NICK_NAME,PASSWD,STATE,TYPE,REAL_NAME,FOREIGN_KEY ) " +
                "values(%s,%s,%s,%s,%s,%s,%s)", getUserBaseSeq(),
                isString(Map,"mobliephone"),"'"+DigestUtils.md5Hex((String)Map.get("mobliephone"))+"'",5,3,isString(Map,"parentsName"),Map.get("parentsId"));
        log_pr.info(sqlInsertUserBase);
        jdbcTemplate.update(sqlInsertUserBase);
    }

    public void userBaseUpdateParent(Map<String,Object> Map) {
        String  sqlUpdateUserBase = String.format("update user_base set STATE=%s,TYPE=%s," +
                "REAL_NAME=%s,FOREIGN_KEY=%s where nick_name = %s" ,
                5,3,isString(Map,"parentsName"),Map.get("parentsId"),isString(Map,"mobliephone"));
        log_pr.info(sqlUpdateUserBase);
        jdbcTemplate.update(sqlUpdateUserBase);
    }

    public String isString(Map<String,Object> map,String name){
        if(map.get(name)==null){
            return null;
        }else {
            return "'"+map.get(name)+"'";
        }
    }

    public String isDate(Map<String,Object> map, String name) {
         if(map.get(name) == null) {
             return  null;
         }else{
             SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
             String date = sf.format(map.get(name));
             return "to_date('"+date+"','yyyy-MM-dd')";
         }
    }

    public Object getDateLong(Map<String,Object> map,String name){
           if (map.get(name) == null) {
               return null;
           } else {
               return  map.get(name);
//               SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//               try {
//                    return  sf.parse((String)map.get(name)).getTime();
//               } catch (ParseException e) {
//                    throw new RuntimeException(e);
//               }
           }
    }

    public Long getUserBaseSeq() {
        String sql = "select user_seq.NEXTVAL from dual";
        return jdbcTemplate.queryForLong(sql);
    }

    public void classInsert(Map<String, Object> map) {
        String sql = String.format("insert into t_classes(BJ_ID,NJ_ID,BJ_MCHENG,BJ_BMA,BJ_ZTAI,XXID,lastmodified) values(%s,%s,%s,%s,%s,%s,%s)",
        map.get("BJ_ID"), map.get( "NJ_ID"), isString(map,"BJ_MCHENG"),map.get("BJ_BMA"),map.get("BJ_ZTAI"),isString(map,"XXID"),map.get("LASTMODIFIEDTIME"));
        log_cl.info(sql);
        jdbcTemplate.update(sql);
    }

    public void classUpdate(Map<String,Object> map) {
        String sql = String.format("update t_classes set NJ_ID = %s,BJ_MCHENG=%s,BJ_BMA=%s,BJ_ZTAI=%s,XXID=%s,lastmodified=%s where BJ_ID=%s",
                 map.get( "NJ_ID"), isString(map,"BJ_MCHENG"),map.get("BJ_BMA"),map.get("BJ_ZTAI"),isString(map,"XXID"),map.get("LASTMODIFIEDTIME"),map.get("BJ_ID"));
        log_cl.info(sql);
        jdbcTemplate.update(sql);
    }

    public void teacherClassInsert(Map<String,Object> map) {
        String sql = String.format("insert into r_teacher_class(RTC_ID,TEACHERID,BJ_ID,lastmodified) values(%s,%s,%s,%s)",
                map.get("RTC_ID"),map.get("TEACHERID"),map.get("BJ_ID"),map.get("lastmodifiedtime"));
        log_tc.info(sql);
        jdbcTemplate.update(sql);
    }

    public void examInsert(Map<String, Object> map) {
       String sql = String.format("insert into t_examination (exam_id,create_exam_date,creator_id,creator_role_id," +
               "exam_date,exam_name,is_final_exam,is_record_score,idstr,term_id,xxid,type_id,idstr2)" +
               " values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",map.get("examId"),isDate(map,"createExamDate"),
               map.get("creatorId"),map.get("creatorRoleId"),isString(map,"examDate"),isString(map,"examName"),
               isString(map,"isFinalExam"), isString(map,"isRecordScore"),isString(map,"idstr"),map.get("termId"),
               isString(map,"xxid"),map.get("typeId"),isString(map,"idstr2"));
       log_ex.info(sql);
       jdbcTemplate.update(sql);
    }

    public void exmaUpdate(Map<String, Object> map) {
        String sql = String.format("update t_examination set create_exam_date = %s, creator_id = %s," +
                "creator_role_id = %s, exam_date = %s, exam_name = %s ,is_final_exam = %s, is_record_score = %s," +
                "idstr = %s, term_id = %s, xxid = %s ,type_id = %s, idstr2 = %s where exam_id = %s" ,
                isDate(map,"createExamDate"), map.get("creatorId"),map.get("creatorRoleId"),isString(map,"examDate"),
                isString(map,"examName"),isString(map,"isFinalExam"), isString(map,"isRecordScore"),isString(map,"idstr"),
                map.get("termId"),isString(map,"xxid"),map.get("typeId"),isString(map,"idstr2"),map.get("examId"));
        log_ex.info(sql);
        jdbcTemplate.update(sql);
    }

    public void termSetInsert(Map<String, Object> map) {
        String sql = String.format("insert into t_term_set (term_id, term_name, term_year, term_type, " +
                "is_current_term,xxid, holiday_begin, holiday_end) values (%s,%s,%s,%s,%s,%s,%s,%s)",
                map.get("termid"), isString(map,"termname"),isString(map,"termyear"), map.get("termtype"),
                isString(map,"iscurrentterm"), isString(map,"xxid"), isDate(map,"holidaybegin"), isDate(map,"holidayend"));
        log_termSet.info(sql);
        jdbcTemplate.update(sql);
    }

    public void termSetUpdate(Map<String, Object> map) {
        String sql = String.format("update t_term_set set term_name = %s ,term_year = %s," +
                "term_type = %s,is_current_term = %s, xxid = %s, holiday_begin = %s, holiday_end = %s" +
                " where term_id = %s" ,
                isString(map,"termname"),isString(map,"termyear"), map.get("termtype"),isString(map,"iscurrentterm"),
                isString(map,"xxid"), isDate(map,"holidaybegin"), isDate(map,"holidayend"),map.get("termid"));
        log_termSet.info(sql);
        jdbcTemplate.update(sql);
    }


    public void gradeInsert(Map<String, Object> map) {
        String sql = String.format("insert into t_stugrade (nj_id, nj_mcheng, nj_bma, nj_ztai, sffban, nj_xsming," +
                "xxid ) values (%s,%s,%s,%s,%s,%s,%s)" ,map.get("njId"),isString(map,"njMcheng"),
                isString(map,"njBma"),map.get("njZtai"),map.get("sffban"),isString(map,"njXsming"),isString(map,"xxid"));
        log_grade.info(sql);
        jdbcTemplate.update(sql);
    }
    public void gradeUpdate(Map<String, Object> map) {
        String sql = String.format("update t_stugrade set nj_mcheng = %s, nj_bma= %s, nj_ztai = %s, sffban= %s," +
                " nj_xsming= %s,xxid = %s where nj_id =%s",isString(map,"njMcheng"),isString(map,"njBma"),
                map.get("njZtai"),map.get("sffban"),isString(map,"njXsming"),isString(map,"xxid"),map.get("njId"));
        log_grade.info(sql);
        jdbcTemplate.update(sql);
    }
}
