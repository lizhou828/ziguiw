package com.zigui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.UserAlbumDao;
import com.zigui.domain.UserAlbum;
import com.zigui.utils.Page;

public class UserAlbumServiceImpl {
	@Autowired
	private UserAlbumDao userAlbumDao;

	public void save(UserAlbum album) {
		userAlbumDao.saveOrUpdate(album);
	}
	
	public UserAlbum getById(long id){
		return userAlbumDao.get(UserAlbum.class, id);
	}

	public Page<UserAlbum> getAlbumList(int pageSize, int pageNum, long userId) {
		
		return userAlbumDao.pagedQuery("from UserAlbum where user.id = ?", pageNum, pageSize, new Object[]{userId});
	}
	
	public void saveAlbumCover(long albumId, long photoId){
		userAlbumDao.update("update UserAlbum set coverPhoto.id = " + photoId + " where id = " + albumId);
	} 
	

	public void delById(long id) {
		userAlbumDao.removeById(UserAlbum.class, id);
	}

	public int userAlbumCount(long id) {
		return userAlbumDao.userAlbumCount(id);
	}

}
