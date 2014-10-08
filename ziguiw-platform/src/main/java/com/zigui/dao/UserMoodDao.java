package com.zigui.dao;

import com.zigui.domain.UserMood;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class UserMoodDao extends GenericDao<UserMood> {
	
    public void save(UserMood mood) {
        getHibernateTemplate().save(mood);
    }
    
    public void delete(UserMood mood) {
        getHibernateTemplate().delete(mood);
    }
    
    public void update(UserMood mood) {
    	getHibernateTemplate().update(mood);
    }

    public UserMood queryById(int id) {
        return getHibernateTemplate().get(UserMood.class, id);
    }

    public void removeUserMood(int id) {
    	UserMood mood = getHibernateTemplate().load(UserMood.class, new Integer(id));
    	getHibernateTemplate().delete(mood);
    }

    public List<UserMood> getMoodList(final int pageSize, final int pageNum) {
    	final String hql = "from UserMood order by createTime desc";
 	   	@SuppressWarnings("unchecked")
		List<UserMood> res = getHibernateTemplate()
 	   		.executeFind(new HibernateCallback<List<UserMood>>() {
 				public List<UserMood> doInHibernate(Session session)
 						throws HibernateException, SQLException {
// 					session.flush();
//					session.clear();
 	   				return session.createQuery(hql)
 							.setFirstResult((pageNum - 1)*pageSize)
 							.setMaxResults(pageSize).list();
 	   			}
 	   	});
 	   return res;
	}

    @SuppressWarnings("unchecked")
	public List<UserMood> getUserMoodList(final int pageSize, final int pageNum, final long userId) {
		final String hql = "from UserMood where user.id = ? order by createTime desc";
	    List<UserMood> res = getHibernateTemplate().executeFind(new HibernateCallback<List<UserMood> >() {
			public List<UserMood> doInHibernate(Session session) throws HibernateException, SQLException {
//				session.flush();
//				session.clear();
	           return session.createQuery(hql).setLong(0, userId)
	               .setFirstResult((pageNum - 1)*pageSize)
	               .setMaxResults(pageSize)
	               .list();
	        }
	    });
	    return res;
	}

	public int userMoodCount(long userId) {
        String sql = "select count(*) from UserMood where user.id = ?";
		Long res = (Long) getHibernateTemplate().find(sql, new Object[]{userId}).get(0);
		return res.intValue();
	}
	

}
