package com.zigui.dao;

import com.zigui.domain.UserDiaryComment;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDiaryCommentDao extends HibernateDaoSupport {

   public void save(UserDiaryComment diaryComment) {
       getHibernateTemplate().save(diaryComment);
   }
   
   public void delete(UserDiaryComment diaryComment) {
       getHibernateTemplate().delete(diaryComment);
   }
   
   public void update(UserDiaryComment diaryComment) {
	   getHibernateTemplate().update(diaryComment);
   }
   
   public UserDiaryComment queryById(int id) {
       return getHibernateTemplate().get(UserDiaryComment.class, id);
   }

   public void removeById(int id) {
	   UserDiaryComment diaryComment = getHibernateTemplate().load(UserDiaryComment.class, new Integer(id));
	   getHibernateTemplate().delete(diaryComment);
   }

}
