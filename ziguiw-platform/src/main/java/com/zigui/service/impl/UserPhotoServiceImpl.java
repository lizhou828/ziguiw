package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.dao.UserPhotoDao;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserPhoto;
import com.zigui.utils.Page;

public class UserPhotoServiceImpl {
	
	@Autowired
	private UserPhotoDao userPhotoDao;
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void save(UserPhoto photo) {
		// TODO Auto-generated method stub
		userPhotoDao.save(photo);
	}
	
	public List<UserPhoto> getPhotoByAlbum(long albumId){
		return userPhotoDao.findBy(UserPhoto.class, "albumId", albumId);
	}
	
	public UserPhoto getPhotoById(long photoId){
		return userPhotoDao.findUniqueBy(UserPhoto.class, "id", photoId);
	}
	
	public List<UserPhoto> getRoundPhoto(long photoId, long albumId, int halfCount){
		UserPhoto currentPhoto = userPhotoDao.findUniqueBy(UserPhoto.class, "id", photoId);
		
		Page<UserPhoto> beforePhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? and id < ?", 1, halfCount, new Object[]{albumId, photoId});
		Page<UserPhoto> afterPhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? and id > ?", 1, halfCount, new Object[]{albumId, photoId});
		
		List<UserPhoto> photos = new ArrayList<UserPhoto>();
		if(beforePhoto != null && CollectionUtils.isNotEmpty(beforePhoto.getList())){
			photos.addAll(beforePhoto.getList());
		}
		
		photos.add(currentPhoto);
		
		if(afterPhoto != null && CollectionUtils.isNotEmpty(afterPhoto.getList())){
			photos.addAll(afterPhoto.getList());
		}
		
		return photos;
	}
	
	public List<UserPhoto> getPrePhoto(long photoId, long albumId, int halfCount){
		UserPhoto currentPhoto = userPhotoDao.findUniqueBy(UserPhoto.class, "id", photoId);
		
		Page<UserPhoto> beforePhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? and id < ? order by id desc", 1, halfCount, new Object[]{albumId, photoId});
	
		return beforePhoto.getList();
	}
	
	public List<UserPhoto> getNextPhoto(long photoId, long albumId, int halfCount){
		UserPhoto currentPhoto = userPhotoDao.findUniqueBy(UserPhoto.class, "id", photoId);
		
		Page<UserPhoto> afterPhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? and id > ? order by id asc", 1, halfCount, new Object[]{albumId, photoId});
		
		return afterPhoto.getList();
	}
	
	public UserPhoto getFirstPhoto(long albumId){
		Page<UserPhoto> afterPhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? order by id asc", 1, 1, new Object[]{albumId});
		
		return afterPhoto.getList().get(0);
	}
	
	public UserPhoto getLastPhoto(long albumId){
		Page<UserPhoto> afterPhoto = userPhotoDao.pagedQuery("from UserPhoto where albumId = ? order by id desc", 1, 1, new Object[]{albumId});
		
		return afterPhoto.getList().get(0);
	}
	

	public Page listByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
		
		List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());

		
	if(StringUtils.isNotEmpty((String)queryCondition.get("startTime"))){
		try {
			criterionsList.add(Restrictions.ge("createTime", format.parse((String)queryCondition.get("startTime"))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	if(StringUtils.isNotEmpty((String)queryCondition.get("endTime"))){
		try {
			criterionsList.add(Restrictions.le("createTime", format.parse((String)queryCondition.get("endTime"))));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
		
		if(queryCondition.get("status") != null && NumberUtils.toInt((String)queryCondition.get("status")) != 0){
			criterionsList.add(Restrictions.eq("status", NumberUtils.toInt((String)queryCondition.get("status"))));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + queryCondition.get("title") + "%"));
		}		
		
		if(queryCondition.get("userId") != null&&!((String)queryCondition.get("userId")).equals("0")){
			criterionsList.add(Restrictions.eq("user.id", (String)queryCondition.get("userId")));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("userNick"))){
			criterionsList.add(Restrictions.eq("user", userBaseDao.findUniqueBy(UserBase.class, "nickName", (String)queryCondition.get("userNick"))));
		}

		if(queryCondition.get("regionId") != null&&!((String)queryCondition.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String)queryCondition.get("regionId")))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return userPhotoDao.pagedQuery(UserPhoto.class, pageNo, pageSize, orderBy, isAsc,queryString,criterions);
		
	}

	public void delete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<UserPhoto> page = userPhotoDao.pagedQuery(UserPhoto.class, 1, ids.length,
				inCodition);
	
		for (UserPhoto userPhoto : page.getList()) {
			userPhotoDao.remove(userPhoto);
		}
	}
	
	public UserPhoto getById(long id){
		return userPhotoDao.get(UserPhoto.class, id);
	}
	
	public void recommend(Long[] ids, long regionId) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<UserPhoto> page = userPhotoDao.pagedQuery(UserPhoto.class, 1, ids.length,
				inCodition);
		
		KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

		for (UserPhoto userPhoto : page.getList()) {
			userPhoto.setKnowledgeRecommendRegion(knowledgeRecommendRegion);
			userPhotoDao.saveOrUpdate(userPhoto);
		}
	}

}
