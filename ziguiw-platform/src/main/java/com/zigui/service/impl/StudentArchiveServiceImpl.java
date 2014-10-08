package com.zigui.service.impl;

import com.zigui.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-12-28
 * Time: P.M.4:36
 */
public class StudentArchiveServiceImpl {
    @Autowired
    protected StudentDao studentDao;
    public void changeSchoolStatus(String[] ids,String status){
       if(ids != null && ids.length !=0 ){
           String hql;
           for(int i=0;i<ids.length;i++){
               hql=String.format("update Student s set s.status=%s where s.Xs_id=%s ",status,ids[i]);
               studentDao.update(hql);
           }
       }

    }
}
