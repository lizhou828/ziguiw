package com.zigui.service.impl;

import com.zigui.dao.ParentDao;
import com.zigui.domain.Parent;
import com.zigui.domain.Student;
import com.zigui.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-17
 * Time: pm2:28
 */
public class ParentServiceImpl {
    @Autowired
    private ParentDao parentDao;

    public Parent getById(int id){
        try{
            return parentDao.get(Parent.class,id);
        }catch (Exception e){
            throw new UserNotExistException("用户不存在或者未绑定学生卡号");
        }
    }

    public List<Student> findChildren (String mobilePhone){
         return parentDao.find("from Student t where t.Xs_id in(select p.student.Xs_id from Parent p where p.mobliephone=?)",new Object[]{mobilePhone});

    }
}
