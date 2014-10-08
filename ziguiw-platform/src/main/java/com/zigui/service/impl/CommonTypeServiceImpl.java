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
import com.zigui.domain.CommonType;
import com.zigui.domain.HomeWork;
import com.zigui.utils.Page;

public class CommonTypeServiceImpl {
	

	@Autowired
	private GenericDao<CommonType> commonTypeDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
	
	public Page<CommonType> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc){
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
		
		if(query.get("type") != null && NumberUtils.toInt((String)query.get("type")) != 0){
			criterionsList.add(Restrictions.eq("type", NumberUtils.toInt((String)query.get("type"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return commonTypeDao.pagedQuery(CommonType.class, pageNo, pageSize, orderBy, isAsc, criterions);
	}
	
	public long saveOrUpdate(CommonType commonType){
		commonTypeDao.saveOrUpdate(commonType);	
		return commonType.getId();
	}
	
	public CommonType getById(long id){
		return commonTypeDao.get(CommonType.class, id);
	}
	
	public void fakeDelete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<CommonType> page = commonTypeDao.pagedQuery(CommonType.class, 1, ids.length,
				inCodition);

		for (CommonType commonType : page.getList()) {
			commonType.setState(-1);
			commonTypeDao.saveOrUpdate(commonType);
		}
	}

}
