package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.NewsChannleDao;
import com.zigui.dao.NewsDao;
import com.zigui.dao.NewsRecommendRegionDao;
import com.zigui.domain.News;
import com.zigui.domain.NewsChannle;
import com.zigui.domain.NewsRecommendRegion;
import com.zigui.utils.Page;

@SuppressWarnings("deprecation")
public class NewsQueryServiceImpl {
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private NewsChannleDao newsChannleDao;
	
	@Autowired
	private NewsRecommendRegionDao newsRecommendRegionDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public Page getNewsByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
		
		List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("startTime"))){
			try {
				criterionsList.add(Restrictions.ge("createTime", format.parse((String) queryCondition.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
        else
        {
            try {
                criterionsList.add(Restrictions.ge("createTime", format.parse("2000-01-01")));//temp solution other wise ,dao layer will throw a null exception
            } catch (ParseException e) {

            }
        }
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", format.parse((String)queryCondition.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(queryCondition.get("state") != null && NumberUtils.toInt((String)queryCondition.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)queryCondition.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + queryCondition.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("province"))){
			criterionsList.add(Restrictions.eq("province", queryCondition.get("province")));
		}
		
		if(queryCondition.get("channleName") != null &&!((String)queryCondition.get("channleName")).equals("0")){
			criterionsList.add(Restrictions.eq("newsChannle", newsChannleDao.get(NewsChannle.class, new Long((String)queryCondition.get("channleName")))));
		}
		
		if(queryCondition.get("regionId") != null &&!((String)queryCondition.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("newsRecommendRegion", newsRecommendRegionDao.get(NewsRecommendRegion.class, new Long((String)queryCondition.get("regionId")))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return newsDao.pagedQuery(News.class, pageNo, pageSize, orderBy, isAsc, queryString,criterions);
		
	}

}
