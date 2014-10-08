package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.dao.UserMoodDao;
import com.zigui.domain.Knowledge;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserDiary;
import com.zigui.domain.UserMood;
import com.zigui.utils.Page;

public class UserMoodServiceImpl {
	@Autowired
	private UserMoodDao userMoodDao;

	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public void save(UserMood mood) {
		userMoodDao.saveOrUpdate(mood);
	}

	public List<UserMood> getMoodList(int pageSize, int pageNum) {
		return userMoodDao.getMoodList(pageSize, pageNum);
	}

	public Page<UserMood> getUserMoodList(int pageSize, int pageNum, long userId, String queryString) {
		return userMoodDao.pagedQuery("from UserMood where user.id = ? order by createTime desc", pageNum, pageSize, queryString, new Object[]{userId});
	}

	public int userMoodCount(long id) {
		return userMoodDao.userMoodCount(id);
	}

	public Page listByCondition(Map queryCondition, int pageNo, int pageSize,
			String orderBy, boolean isAsc, String queryString) {

		List<Criterion> criterionsList = new ArrayList<Criterion>(
				queryCondition.size());

		if (StringUtils.isNotEmpty((String) queryCondition.get("startTime"))) {
			try {
				criterionsList
						.add(Restrictions.ge("createTime",
								format.parse((String) queryCondition
										.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty((String) queryCondition.get("endTime"))) {
			try {
				criterionsList.add(Restrictions.le("createTime",
						format.parse((String) queryCondition.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if (queryCondition.get("status") != null
				&& NumberUtils.toInt((String) queryCondition.get("status")) != 0) {
			criterionsList.add(Restrictions.eq("status",
					NumberUtils.toInt((String) queryCondition.get("status"))));
		}

		if (queryCondition.get("userId") != null
				&& !((String) queryCondition.get("userId")).equals("0")) {
			criterionsList.add(Restrictions.eq("user.id",
					(String) queryCondition.get("userId")));
		}

		if (StringUtils.isNotEmpty((String) queryCondition.get("userNick"))) {
			criterionsList.add(Restrictions.eq(
					"userId",
					userBaseDao.findUniqueBy(UserBase.class, "nickName",
							new Long((String) queryCondition.get("userNick")))
							.getId()));
		}
		
		if(queryCondition.get("regionId") != null&&!((String)queryCondition.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String)queryCondition.get("regionId")))));
		}

		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);

		return userMoodDao.pagedQuery(UserMood.class, pageNo, pageSize,
				orderBy, isAsc, queryString, criterions);

	}

	public void delete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<UserMood> page = userMoodDao.pagedQuery(UserMood.class, 1,
				ids.length, inCodition);

		for (UserMood userMood : page.getList()) {
			userMoodDao.remove(userMood);
		}
	}
	
	public UserMood getById(long id){
		return userMoodDao.get(UserMood.class, id);
	}
	
	public void recommend(Long[] ids, long regionId) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<UserMood> page = userMoodDao.pagedQuery(UserMood.class, 1, ids.length,
				inCodition);
		
		KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

		for (UserMood userMood : page.getList()) {
			userMood.setKnowledgeRecommendRegion(knowledgeRecommendRegion);
			userMoodDao.saveOrUpdate(userMood);
		}
	}
}
