package com.zigui.dao;

import com.zigui.domain.Parent;
import com.zigui.domain.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User:lizhou
 * Date: 12-10-17
 * Time: pm 2:31
 */
public class ParentDao extends GenericDao<Parent> {
    public List<Student> findChildren (String mobilePhone){
        return find("from Student t where t.Xs_id in(select p.student.Xs_id from Parent p where p.mobliephone=?)",new Object[]{mobilePhone});
    }
}
