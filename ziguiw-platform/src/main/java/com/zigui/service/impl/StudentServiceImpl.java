package com.zigui.service.impl;

import com.zigui.dao.StudentDao;
import com.zigui.domain.Student;
import com.zigui.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-17
 * Time: am 10:24
 */
public class StudentServiceImpl {
    @Autowired
    private StudentDao  studentDao;

    public Student getById(int id){
         try{
             return studentDao.get(Student.class,id);
         }catch (Exception e){
             throw new UserNotExistException("未绑定学生卡号或学校！");
         }
    }

    //count every class's student number
    public List countStudentByClassId(Long classId) {
         List list=studentDao.find("select count(t.Xs_id) from Student t where t.Bj_ID=?",new Object[]{classId.toString()});
        return list;

    }

    public List ajaxQueryStudentsByClassId(Long classId){
        List list=new ArrayList();
        List objList=studentDao.find("select t.Xs_id,t.Xs_xming from Student t where t.Bj_ID=?",new Object[]{classId.toString()});
        if(objList==null|| objList.size()==0){
            return list;
        }

//        for(int i=0;i<objList.size();i++){
//            Object objects[]=(Object[])objList.get(i);
//            for(int j=0;j<objects.length;i++){
//                objects[]
//            }
//        }
        return  objList;
    }
    public List<Student> queryStudentListByClassId(Long classId){
        List list=new ArrayList();
        List objList=studentDao.find("select t from Student t where t.Bj_ID=?",new Object[]{classId.toString()});
        if(objList==null|| objList.size()==0){
            return list;
        }

//        for(int i=0;i<objList.size();i++){
//            Object objects[]=(Object[])objList.get(i);
//            for(int j=0;j<objects.length;i++){
//                objects[]
//            }
//        }
        return  objList;
    }

}
