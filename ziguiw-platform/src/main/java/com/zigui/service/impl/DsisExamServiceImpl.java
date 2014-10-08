package com.zigui.service.impl;


import com.zigui.dao.DsisExamDao;
import com.zigui.domain.Exam;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-22
 * Time: P.M.3:42
 */
public class DsisExamServiceImpl {
    @Autowired
    private DsisExamDao dsisExamDao;

    public Page<Exam> queryExam(int pageNo, int pageSize, Long schoolId,Integer termId){
      return dsisExamDao.queryExamName(pageNo,pageSize,schoolId,termId);
    }


}
