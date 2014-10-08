package com.zigui.service.impl;

import com.zigui.dao.DsisTermSetDao;
import com.zigui.domain.TermSet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-30
 * Time: P.M.2:06
 */
public class DsisTermSetServiceImpl {
    @Autowired
    private DsisTermSetDao dsisTermSetDao;

    public TermSet findById(Long termId){
        return dsisTermSetDao.get(TermSet.class,termId);
    }

    public  List getBySchoolId(String schoolId){
        return dsisTermSetDao.find("select t from TermSet t where t.xxid=?",new Object[]{schoolId});
    }


}
