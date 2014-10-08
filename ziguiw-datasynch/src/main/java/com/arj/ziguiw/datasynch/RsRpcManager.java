package com.arj.ziguiw.datasynch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-19
 * Time: A.M 9:33
 */
@Service(value = "rsRpcManager")
public class RsRpcManager {

    @Qualifier(value = "jdbcTemplateService")
    @Autowired
    private JdbcTemplateService jdbcTemplateService;



    private static final Log log = LogFactory.getLog(RsRpcManager.class);

    public Long findLastModifiedTime(String tableName) throws SQLException {
        return jdbcTemplateService.findLastModifiedTime(tableName);
    }

    /*
    * update or insert teacher data
    *
    * */
    public void saveTeacher(Map<String, Object> teacherMap) throws SQLException {
        try {
            jdbcTemplateService.teacherInsert(teacherMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.teacherUpdate(teacherMap);
        }
        try {
            jdbcTemplateService.userBaseSaveTeacher(teacherMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.userBaseUpdateTeacher(teacherMap);
        }
    }

    /*
    * update or insert student data
    *
    * */
    public void saveStudent(Map<String, Object> studentMap) throws SQLException {
        try {
            jdbcTemplateService.studentInsert(studentMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.studentUpdate(studentMap);
        }
        if(studentMap.get("CARDID") != null && !"".equals(studentMap.get("CARDID").toString().trim())) {
            try {
                jdbcTemplateService.userBaseSaveStudent(studentMap);
            } catch (DuplicateKeyException e) {
                jdbcTemplateService.userBaseUpdateStudent(studentMap);
            }
        }
    }

    /*
    * update or inert school data
    *
    * */
    public void saveSchool(Map<String, Object> schoolMap) throws SQLException {
        try {
            jdbcTemplateService.schoolInsert(schoolMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.schoolUpdate(schoolMap);
        }
        try {
            jdbcTemplateService.userBaseSaveSchool(schoolMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.userBaseUpdateSchool(schoolMap);
        }
    }


    /*
   * update or inert school data
   *
   * */
    public void saveExam(Map<String, Object> examMap) throws SQLException {
        try {
            jdbcTemplateService.examInsert(examMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.exmaUpdate(examMap);
        }
    }
    /*
    * update or insert parent data
    *
    * */
    public void saveParent(Map<String, Object> parentMap) {
        try {
            jdbcTemplateService.parentInsert(parentMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.parentUpdate(parentMap);
        }
        try {
            jdbcTemplateService.userBaseSaveParent(parentMap);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.userBaseUpdateParent(parentMap);
        }
    }


    public void saveClass(Map<String , Object> map) {
        try {
            jdbcTemplateService.classInsert(map);
        } catch (DuplicateKeyException e) {
            jdbcTemplateService.classUpdate(map);
        }
    }

    public void saveTeacherClass(Map<String , Object> map) {
          try{
              jdbcTemplateService.teacherClassInsert(map);
          }catch (DuplicateKeyException e){
              log.info("the data exist in r_teacher_class");
          }
    }

    public void saveTermSet(Map<String , Object> map) {
        try{
            jdbcTemplateService.termSetInsert(map);
        }catch (DuplicateKeyException e){
           jdbcTemplateService.termSetUpdate(map);
        }
    }
    public void saveGrade(Map<String , Object> map) {
        try{
            jdbcTemplateService.gradeInsert(map);
        }catch (DuplicateKeyException e){
            jdbcTemplateService.gradeUpdate(map);
        }
    }

    public static void main(String [] args) throws ParseException {
            SimpleDateFormat  sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sf.parse("2013-06-28 15:03:38");
            Date date1 = sf.parse("2013-06-28 15:50:00");
            System.out.println(date.getTime() + "--------------");
            System.out.println(date1.getTime());
            System.out.println(sf.format(new Date()));
    }
}


