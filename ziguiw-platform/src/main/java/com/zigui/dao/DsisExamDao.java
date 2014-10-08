package com.zigui.dao;

import com.zigui.domain.Exam;
import com.zigui.utils.Page;


import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-22
 * Time: P.M.3:30
 */
public class DsisExamDao extends  GenericDao<Exam> {

    public Page<Exam> queryExamName (int pageNo, int pageSize, Long schoolId,Integer termId){
        String sql="select * from t_examination t where t.xxid=" +schoolId +"  and term_id="+termId+"order by t.create_exam_date desc";
        Page page=pageQueryBySql(sql,pageNo,pageSize);
        Page<Exam> examPage=new Page<Exam>();
        List examList=new ArrayList<Exam>();
        if( examPage.getList()==null || examPage.getList().size()==0){
            examPage.setList(examList);
            return examPage;
        }
        Exam exam;
        for (int i=0;i<pageSize;i++){
            Object[] objects=(Object[])page.getList().get(i);
            exam=new Exam();
            exam.setEXAM_id(Integer.valueOf(objects[0].toString()));
            exam.setTERM_ID(objects[1].toString());
            exam.setEXAM_NAME(objects[2].toString());
            exam.setType(Integer.valueOf(objects[3].toString()));
            exam.setCREATOR_ID(Integer.valueOf(objects[4].toString()));
            exam.setCREATOR_ROLE_ID(Integer.valueOf(objects[5].toString()));
            exam.setIdStr(objects[6].toString());
            exam.setEXAM_DATE(objects[7].toString());
            exam.setXxID(objects[8].toString());

            examList.add(exam) ;
        }
        examPage.setList(examList);
        return examPage ;


    }

//    public  List findAllBySQL(String schoolId){
//        String sql =String.format("select exam_id,exam_name from t_examination  where xxid=%s  order by t.create_exam_date desc",schoolId);
//        return find(sql,new Object[]{schoolId});
//    }
}
