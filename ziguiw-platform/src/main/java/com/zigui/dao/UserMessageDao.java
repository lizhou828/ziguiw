package com.zigui.dao;

import com.zigui.domain.UserMessage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class UserMessageDao extends GenericDao<UserMessage> {
	
    public void save(UserMessage userMsg) {
        getHibernateTemplate().save(userMsg);
    }
    
    public void delete(UserMessage userMsg) {
        getHibernateTemplate().delete(userMsg);
    }
    
    public void update(UserMessage userMsg) {
    	getHibernateTemplate().update(userMsg);
    }

    public UserMessage queryById(int id) {
        return getHibernateTemplate().get(UserMessage.class, id);
    }

    public void removeUserMsg(int id) {
    	UserMessage userMsg = getHibernateTemplate().load(UserMessage.class, new Integer(id));
    	getHibernateTemplate().delete(userMsg);
    }

	public List<UserMessage> getMessageList(final int pageNum, final int pageSize,
			final long ownerId) {
    	final String hql = "from UserMessage where toUser.id = ? and kind = 1 order by createTime desc";
 	   	@SuppressWarnings("unchecked")
		List<UserMessage> res = getHibernateTemplate()
 	   		.executeFind(new HibernateCallback<List<UserMessage>>() {
 				public List<UserMessage> doInHibernate(Session session)
 						throws HibernateException, SQLException {
// 					session.flush();
// 					session.clear();
 	   				return session.createQuery(hql).setLong(0, ownerId)
 							.setFirstResult((pageNum - 1)*pageSize)
 							.setMaxResults(pageSize).list();
 	   			}
 	   	});
 	   return res;
	}

	@SuppressWarnings("unchecked")
	public List<UserMessage> getReplies(long id) {
		final String hql = "from UserMessage where parentMsg.id = ? order by createTime";
		return (List<UserMessage>)getHibernateTemplate().find(hql,new Object[]{id});
	}
}
