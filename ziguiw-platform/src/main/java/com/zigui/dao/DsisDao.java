package com.zigui.dao;

import com.zigui.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-11-21
 * Time: A.M 10:31
 */
public class DsisDao extends GenericDao{

    //查询学生评语
    public Page queryStudentComments(int pageNo, int pageSize, Integer studentId, Integer bjId, String beginTime,
                                     String endTime) {
        String sql = "select * from common_message t where t.kind=4";
        return pageQueryBySql(sql, pageNo, pageSize);
    }


    public Page queryTermsetList(int pageNo, int pageSize,int termId){
        String sql="select * from t_term_set t where t.term_id="+termId;
        return pageQueryBySql(sql,pageNo,pageSize);
    }



}
