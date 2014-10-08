package com.zigui.service.impl;

import com.zigui.dao.NianJiDao;
import com.zigui.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-11-9
 * Time: P.M.12:52
 */
public class NianJiServiceImpl {
    @Autowired
    protected NianJiDao nianJiDao;

    public Grade getById(Integer id){
        if(nianJiDao.get(Grade.class,id)==null){
            System.out.println("没有获取到该年级的信息");
            return null;
        }
        return nianJiDao.get(Grade.class,id);
    }

}
