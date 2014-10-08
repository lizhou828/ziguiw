package com.zigui.dao;

import com.zigui.domain.UserAlbum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

public class UserAlbumDao extends GenericDao<UserAlbum> {

	public void save(UserAlbum album) {
		getHibernateTemplate().save(album);
	}

	@SuppressWarnings("unchecked")
	public List<UserAlbum> getAlbumList(final int pageSize, final int pageNum, final long userId) {
		final String hql = "from UserAlbum where user.id = ? order by createTime desc";
	    List<UserAlbum> res = getHibernateTemplate().executeFind(new HibernateCallback<List<UserAlbum>>() {
				public List<UserAlbum> doInHibernate(Session session) throws HibernateException, SQLException {
//					session.flush();
//					session.clear();
	               return session.createQuery(hql).setLong(0, userId)
	                   .setFirstResult((pageNum - 1)*pageSize)
	                   .setMaxResults(pageSize)
	                   .list();
	           }
	    });
	    return res;
	}


	public int userAlbumCount(long userId) {
        String sql = "select count(*) from UserAlbum where user.id = ?";
		Long res = (Long) getHibernateTemplate().find(sql, new Object[]{userId}).get(0);
		return res.intValue();
	}
	

}
