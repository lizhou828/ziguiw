package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeChannleDao;
import com.zigui.dao.KnowledgeDao;
import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.domain.Knowledge;
import com.zigui.domain.KnowledgeChannle;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.domain.News;
import com.zigui.domain.NewsRecommendRegion;
import com.zigui.utils.Page;

public class KnowledgeServiceImpl {
	@Autowired
	private KnowledgeDao knowledgeDao;

	@Autowired
	private KnowledgeChannleDao knowledgeChannleDao;
	
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public long saveOrUpdate(Knowledge knowledge) {
		knowledgeDao.saveOrUpdate(knowledge);

		return knowledge.getId();
	}

	public Knowledge getKnowledgeById(long id) {
		return knowledgeDao.get(Knowledge.class, id);
	}

	public void approveKnowledges(Long[] ids, int approveFlag) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, 1, ids.length,
				inCodition);

		for (Knowledge knowledge : page.getList()) {
			knowledge.setState(approveFlag);

			knowledgeDao.saveOrUpdate(knowledge);
		}
	}

	public void fakeDeleteKnowledges(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, 1, ids.length,
				inCodition);

		for (Knowledge knowledge : page.getList()) {
			knowledge.setState(-1);

			knowledgeDao.saveOrUpdate(knowledge);
		}
	}

	public void deleteKnowledges(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, 1, ids.length,
				inCodition);

		for (Knowledge knowledge : page.getList()) {
			knowledgeDao.remove(knowledge);
		}
	}
	
	public void restore(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, 1, ids.length,
				inCodition);

		for (Knowledge knowledge : page.getList()) {
			knowledge.setState(1);

			knowledgeDao.saveOrUpdate(knowledge);
		}
	}
	
	public void recommend(Long[] ids, long regionId) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, 1, ids.length,
				inCodition);
		
		KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

		for (Knowledge knowledge : page.getList()) {
			knowledge.setKnowledgeRecommendRegion(knowledgeRecommendRegion);

			knowledgeDao.saveOrUpdate(knowledge);
		}
	}

	public Page<Knowledge> getPagedKnowledgesByCondition(Map<String, String> query,
			int currentPage, int countPerPage, String orderBy, boolean isAsc) {
		Criterion eqChannleName = null, likeTitle = null, eqProvice = null, eqState = null, beCreateTime = null;
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("1", 1));
		if (query != null) {
			if (StringUtils.isNotEmpty(query.get("channleName"))
					&& !query.get("channleName").equals("0")) {
				eqChannleName = Restrictions.eq("channle_id", query
						.get("channleName"));
				conjunction.add(eqChannleName);
			}
			if (StringUtils.isNotEmpty(query.get("title"))
					&& !query.get("title").equals("")) {
				likeTitle = Restrictions.like("title", "%" + query.get("title")+"%");
				conjunction.add(likeTitle);
			}
			if (StringUtils.isNotEmpty(query.get("province"))
					&& !query.get("province").equals("")) {
				eqProvice = Restrictions.eq("province", query.get("province"));
				conjunction.add(eqProvice);
			}
			if (StringUtils.isNotEmpty(query.get("state"))
					&& !query.get("state").equals("")
					&& !query.get("state").equals("0")) {
				eqState = Restrictions.eq("state", query.get("state"));
				conjunction.add(eqState);
			} else {
				eqState = Restrictions.sizeGt("state", 0);
				conjunction.add(eqState);
			}
			if (StringUtils.isNotEmpty(query.get("startTime"))
					&& !query.get("startTime").equals("")
					&& StringUtils.isNotEmpty(query.get("endTime"))
					&& !query.get("endTime").equals("")) {

				try {
					Date startDate = format.parse(query.get("startTime"));
					Date endDate = format.parse(query.get("endTime"));
					beCreateTime = Restrictions.between("create_time",
							startDate.getTime(), endDate.getTime());
					conjunction.add(beCreateTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		Page<Knowledge> page = knowledgeDao.pagedQuery(Knowledge.class, currentPage,
				countPerPage, orderBy, isAsc, conjunction);

		return page;
	}
	
public Page getKnowledgesByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
		
		List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());
		
//		if(StringUtils.isNotEmpty((String)queryCondition.get("startTime"))){
//			try {
//				criterionsList.add(Restrictions.ge("createTime", dateFormat.parse((String)queryCondition.get("startTime"))));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		if(StringUtils.isNotEmpty((String)queryCondition.get("endTime"))){
//			try {
//				criterionsList.add(Restrictions.le("createTime", dateFormat.parse((String)queryCondition.get("endTime"))));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		
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
//		if(StringUtils.isNotEmpty((String)queryCondition.get("startTime"))){
//			criterionsList.add(Restrictions.ge("createTime", (String)queryCondition.get("startTime")));
//		}
//		
//		if(StringUtils.isNotEmpty((String)queryCondition.get("endTime"))){
//			criterionsList.add(Restrictions.ge("createTime", (String)queryCondition.get("endTime")));
//		}
//		
		if(queryCondition.get("state") != null && NumberUtils.toInt((String)queryCondition.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)queryCondition.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + queryCondition.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)queryCondition.get("province"))){
			criterionsList.add(Restrictions.eq("province", queryCondition.get("province")));
		}
		
		if(queryCondition.get("channleName") != null&&!((String)queryCondition.get("channleName")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeChannle", knowledgeChannleDao.get(KnowledgeChannle.class, new Long((String)queryCondition.get("channleName")))));
		}
		
		if(queryCondition.get("regionId") != null&&!((String)queryCondition.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String)queryCondition.get("regionId")))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return knowledgeDao.pagedQuery(Knowledge.class, pageNo, pageSize, orderBy, isAsc,queryString,criterions);
		
	}
}
