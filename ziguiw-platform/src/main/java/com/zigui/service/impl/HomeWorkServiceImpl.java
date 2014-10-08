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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.GenericDao;
import com.zigui.domain.HomeWork;
import com.zigui.utils.Page;
import com.zigui.utils.CommonUtils;

public class HomeWorkServiceImpl {
	

	@Autowired
	private GenericDao<HomeWork> homeWorkDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
	
	private static Log log = LogFactory.getLog(CommonMessageServiceImpl.class); 
	
	public Page<HomeWork> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc){
		if(query == null){
			query = new HashMap<String, String>();
		}
		System.out.println("query :"+query.toString());
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
		
		if(StringUtils.isNotEmpty((String)query.get("opIds"))&&(((String)query.get("opIds")).split(";")).length>0){
			criterionsList.add(Restrictions.eq("classId", (String)query.get("opIds")));
		}
		
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		System.out.println("homeWorkDao :"+homeWorkDao);
		
		return homeWorkDao.pagedQuery(HomeWork.class, pageNo, pageSize, orderBy, isAsc, criterions);
	}
	
	public long saveOrUpdate(HomeWork homeWork){
		homeWorkDao.saveOrUpdate(homeWork);	
		return homeWork.getId();
	}
	
	public HomeWork getById(long id){
		return homeWorkDao.get(HomeWork.class, id);
	}

	
	public void fakeDelete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<HomeWork> page = homeWorkDao.pagedQuery(HomeWork.class, 1, ids.length,
				inCodition);

		for (HomeWork homeWork : page.getList()) {
			homeWork.setState(-1);
			homeWorkDao.saveOrUpdate(homeWork);
		}
	}
	
	public void delete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<HomeWork> page = homeWorkDao.pagedQuery(HomeWork.class, 1, ids.length,
				inCodition);

		for (HomeWork homeWork : page.getList()) {
			homeWorkDao.remove(homeWork);
		}
	}

}
