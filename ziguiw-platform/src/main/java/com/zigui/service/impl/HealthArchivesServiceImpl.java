package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.GenericDao;
import com.zigui.domain.HealthArchives;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;

public class HealthArchivesServiceImpl {
	
	@Autowired
	private GenericDao<HealthArchives> healthArchivesDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
	
	public Page<HealthArchives> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc){
		if(query == null){
			query = new HashMap<String, String>();
		}
		
		List<Criterion> criterionsList = new ArrayList<Criterion>(query.size());
		
		if(StringUtils.isNotEmpty((String)query.get("startTime"))){
			try {
				criterionsList.add(Restrictions.ge("createTime", format.parse((String)query.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", format.parse((String)query.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(query.get("state") != null && NumberUtils.toInt((String)query.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)query.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("opIds"))&&(((String)query.get("opIds")).split(";")).length>0){
			criterionsList.add(Restrictions.in("userId", CommonUtils.strsToLongs(((String)query.get("opIds")).split(";"))));
		}
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return healthArchivesDao.pagedQuery(HealthArchives.class, pageNo, pageSize, orderBy, isAsc, criterions);
	}
	
	public long saveOrUpdate(HealthArchives healthArchives){
		healthArchivesDao.saveOrUpdate(healthArchives);	
		return healthArchives.getId();
	}
	
	public HealthArchives getById(long id){
		return healthArchivesDao.get(HealthArchives.class, id);
	}

}
