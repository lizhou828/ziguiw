package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.domain.UserBase;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;

public class UserQueryServiceImpl {
	
	@Autowired
	private UserBaseDao userBaseDao;
	
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public Page getNewsByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
		if(queryCondition == null){
			queryCondition = new HashMap<String, String>();
		}
		List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("startCreateTime"))){
			try {
				criterionsList.add(Restrictions.ge("createTime", dateFormat.parse((String)queryCondition.get("startCreateTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("endCreateTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", dateFormat.parse((String)queryCondition.get("endCreateTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("startLoginTime"))){
			try {
				criterionsList.add(Restrictions.ge("lastLoginTime", dateFormat.parse((String)queryCondition.get("startLoginTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("endLoginTime"))){
			try {
				criterionsList.add(Restrictions.le("lastLoginTime", dateFormat.parse((String)queryCondition.get("endLoginTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(queryCondition.get("state") != null && NumberUtils.toInt((String)queryCondition.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)queryCondition.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("nickName"))){
			criterionsList.add(Restrictions.like("nickName", "%" + queryCondition.get("nickName") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("province"))){
			criterionsList.add(Restrictions.eq("province", (String)queryCondition.get("province")));
		}
		
		if(queryCondition.get("regionId") != null&&!((String)queryCondition.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String)queryCondition.get("regionId")))));
		}
		
		if(queryCondition.get("userId") != null && NumberUtils.toInt((String)queryCondition.get("userId")) != 0){
			criterionsList.add(Restrictions.eq("id", NumberUtils.toInt((String)queryCondition.get("userId"))));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("opIds"))&&(((String)queryCondition.get("opIds")).split(";")).length>0){
			criterionsList.add(Restrictions.in("id", CommonUtils.strsToLongs(((String)queryCondition.get("opIds")).split(";"))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return userBaseDao.pagedQuery(UserBase.class, pageNo, pageSize, orderBy, isAsc,queryString , criterions);
		
	}

}
