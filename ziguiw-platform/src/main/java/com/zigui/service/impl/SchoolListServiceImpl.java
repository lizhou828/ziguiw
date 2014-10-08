package com.zigui.service.impl;

import com.zigui.dao.SchoolListDao;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-19
 * Time: 下午2:40
 */
public class SchoolListServiceImpl {

     @Autowired
     private SchoolListDao schoolListDao;

     public Page<List> getSchoolList(int pageNum,int pageSize){
         String sb = "and (u.nickName='4301246001' " +
                 "or u.nickName='4301057000' " +
                 "or u.nickName='4301117006' " +
                 "or u.nickName='4301113000' " +
                 "or u.nickName='4306021005' " +
                 "or u.nickName='4301111000' " +
                 "or u.nickName='4306023002' " +
                 "or u.nickName='4306023001' " +
                 "or u.nickName='4306021009' " +
                 "or u.nickName='4301048001' " +
                 "or u.nickName='4301046000' " +
                 "or u.nickName='4301047001')";
         String hql = "select distinct u.portrait,s.sch_name,u.nickName,s.XxID,s.xx_ID from UserBase u ,School s " +
                 "where s.xx_ID = u.foreignKey and u.type = 1 " + sb +
                 " order by s.xx_ID desc";
         return  schoolListDao.pagedQuery(hql,pageNum,pageSize);
     }

    public Page<List> getSchoolInfoList(int pageNum, int pageSize) {
        String hql = "select distinct s.sch_name,u.nickName,u.createTime from School s,UserBase u where s.xx_ID = u.foreignKey and u.type = 1 order by u.createTime desc";
        return schoolListDao.pagedQuery(hql,pageNum,pageSize);
    }
}
