package com.zigui.dao;

import com.zigui.domain.UserPhoto;

public class UserPhotoDao extends GenericDao<UserPhoto> {
	
    public void save(UserPhoto photo) {
        getHibernateTemplate().save(photo);
    }
    
    public void delete(UserPhoto photo) {
        getHibernateTemplate().delete(photo);
    }
    
    public void update(UserPhoto photo) {
    	getHibernateTemplate().update(photo);
    }

    public UserPhoto queryById(int id) {
        return getHibernateTemplate().get(UserPhoto.class, id);
    }

    public void removeUserMood(int id) {
    	UserPhoto photo = getHibernateTemplate().load(UserPhoto.class, new Integer(id));
    	getHibernateTemplate().delete(photo);
    }
}
