package com.zigui.dao;

import com.zigui.domain.Clasz;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-17
 * Time:  am 10:55
 */
public class ClassDao extends  GenericDao<Clasz>{

    public List<Clasz> findAllClassByTeacherId(Long teacherId){
        return find("from Clasz c where c.Bj_id in(select t.clasz.Bj_id from TeacherClasz t  where t.teacherID=? )",new Object[]{teacherId.intValue()});
    }

}
