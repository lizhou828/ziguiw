package com.zigui.service.impl;

import com.zigui.dao.ClassForumDao;
import com.zigui.domain.ClassForum;
import com.zigui.domain.ClassReply;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-25
 * Time: 上午10:26
 */
public class ClassForumServiceImpl {

    @Autowired
    private ClassForumDao classForumDao;

    public Page<ClassForum> getClassForumByClassId(int pageNum,int pageSize,long class_id){
        String hql = "from ClassForum c where c.class_id = "+class_id + "order by c.lastReplyTime desc";
        return classForumDao.pagedQuery(hql,pageNum,pageSize);
    }

    public void saveClassForum(ClassForum classForum) {
                classForumDao.saveObj(classForum);
    }

    public Page<ClassReply> getClassReplyByClassForumId(int pageNum,int pageSize,long classForumId){
          String hql = "from ClassReply c where c.forumId = " + classForumId + "order by c.createTime desc";
          return classForumDao.pagedQuery(hql,pageNum,pageSize);
    }

    public ClassForum getClassForumById(long classForumId){
        List<ClassForum>  list =  classForumDao.findBy(ClassForum.class, "id",classForumId);
        if(list.size() > 0){
            return list.get(0);
        }
        return new ClassForum();
    }

    public void saveClassReply(ClassReply classReply){
              classForumDao.saveObj(classReply);
    }
}
