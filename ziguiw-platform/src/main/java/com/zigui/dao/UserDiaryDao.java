package com.zigui.dao;

import com.zigui.domain.UserDiary;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class UserDiaryDao extends GenericDao<UserDiary> {

   public void save(UserDiary userDiary) {
       getHibernateTemplate().save(userDiary);
   }
   
   public void delete(UserDiary userDiary) {
       getHibernateTemplate().delete(userDiary);
   }
   
   public void update(UserDiary userDiary) {
	   getHibernateTemplate().update(userDiary);
   }
   
   public UserDiary queryById(long id) {
       return getHibernateTemplate().get(UserDiary.class, id);
   }

   public void removeById(int id) {
	   UserDiary userDiary = getHibernateTemplate().load(UserDiary.class, new Integer(id));
	   getHibernateTemplate().delete(userDiary);
   }

   @SuppressWarnings("unchecked")
   public List<UserDiary> getDiaryByStatus(final int pageSize, final int pageNum, final int status) {
	   final String hql = "from UserDiary where status = ? order by createTime desc";
       List<UserDiary> res = getHibernateTemplate().executeFind(new HibernateCallback<List<UserDiary> >() {
			public List<UserDiary> doInHibernate(Session session) throws HibernateException, SQLException {
//			session.flush();
//			session.clear();
               return session.createQuery(hql).setInteger(0, status)
                   .setFirstResult(pageNum*pageSize)
                   .setMaxResults(pageSize)
                   .list();
           }
       });
       return res;
   }
   
   @SuppressWarnings("unchecked")
   public List<UserDiary> getHotDiary(final int pageSize, final int pageNum) {
	   final String hql = "from UserDiary order by viewCount desc";
	   List<UserDiary> res = getHibernateTemplate()
	   		.executeFind(new HibernateCallback<List<UserDiary>>() {
				public List<UserDiary> doInHibernate(Session session)
						throws HibernateException, SQLException {
//					session.flush();
//					session.clear();
	   				return session.createQuery(hql)
							.setFirstResult(pageNum*pageSize)
							.setMaxResults(pageSize).list();
	   			}
	   		});
	   return res;	
   }

   @SuppressWarnings("unchecked")
   public List<UserDiary> getDiaryByUserId(final int pageSize, final int pageNum, final long userId) {
	   final String hql = "from UserDiary where user.id = ? order by createTime desc";   
	   List<UserDiary> res = getHibernateTemplate()
			.executeFind(new HibernateCallback<List<UserDiary>>() {
				public List<UserDiary> doInHibernate(Session session)
						throws HibernateException, SQLException {
//					session.flush();
//					session.clear();
					return session.createQuery(hql).setLong(0, userId).setFirstResult((pageNum - 1)*pageSize)
							.setMaxResults(pageSize).list();
				}
			});
   		return res;
   }

}
