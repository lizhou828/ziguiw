package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.BoardDao;
import com.zigui.dao.ForumDao;
import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.domain.Board;
import com.zigui.domain.Debate;
import com.zigui.domain.Forum;
import com.zigui.domain.Knowledge;
import com.zigui.domain.KnowledgeChannle;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.utils.Page;

public class ForumServiceImpl {
	
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
	
	@Autowired
	private ForumDao forumDao;
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;
	
	public Page<Forum> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc,String queryString){
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
		
		if(query.get("boardid") != null &&!((String)query.get("boardid")).equals("0")){
			Board tmpBoard = boardDao.get(Board.class, new Long((String)query.get("boardid")));
			Criterion parentCriterion = Restrictions.eq("board", tmpBoard);
			List<Board> childBoards = boardDao.findBy(Board.class, "parentBoard", tmpBoard);
			if(CollectionUtils.isNotEmpty(childBoards)){
				Criterion childCriterion = Restrictions.in("board", childBoards);
				criterionsList.add(Restrictions.or(parentCriterion, childCriterion));
			}else{
				criterionsList.add(parentCriterion);
			}
//			criterionsList.add(childCriterion);
		}
		
		if(query.get("parentid") != null &&!((String)query.get("parentid")).equals("0")){
			criterionsList.add(Restrictions.eq("parentForum", forumDao.get(Forum.class, new Long((String)query.get("parentid")))));
		}
		
		if(query.get("isnew") != null&&StringUtils.isNumeric((String)query.get("isnew"))){
			criterionsList.add(Restrictions.eq("isnew", NumberUtils.toInt((String)query.get("isnew"))));
		}
		
		if(query.get("istop") != null&&StringUtils.isNumeric((String)query.get("istop"))){
			criterionsList.add(Restrictions.eq("istop", NumberUtils.toInt((String)query.get("istop"))));
		}		
		
		if(query.get("regionId") != null&&!((String)query.get("regionId")).equals("0")){
			criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String)query.get("regionId")))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return forumDao.pagedQuery(Forum.class, pageNo, pageSize, orderBy, isAsc, queryString , criterions);
	}
	
	public long saveOrUpdate(Forum forum){
		forumDao.saveOrUpdate(forum);	
		return forum.getId();
	}
	
	public Forum getById(long id){
		return forumDao.get(Forum.class, id);
	}
	
	public void fakeDelete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Forum> page = forumDao.pagedQuery(Forum.class, 1, ids.length,
				inCodition);

		for (Forum forum : page.getList()) {
			forum.setState(-1);
			forumDao.saveOrUpdate(forum);
		}
	}
	
	public void restore(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Forum> page = forumDao.pagedQuery(Forum.class, 1, ids.length,
				inCodition);

		for (Forum forum : page.getList()) {
			forum.setState(1);
			forumDao.saveOrUpdate(forum);
		}
	}
	
	
	public int delete(Long[] ids){
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Forum> page = forumDao.pagedQuery(Forum.class, 1, ids.length,
				inCodition);
		List<Forum> subForums = null;
		for (Forum forum : page.getList()) {
			subForums = forumDao.findBy(Forum.class, "parentForum.id", forum.id);
			   if(subForums != null && subForums.size() > 0){//存在子贴，彻删子贴
				   for(Forum subForum : subForums){
					   forumDao.remove(subForum);
				   }
			   }
			forumDao.remove(forum);
		}
		return 0;
	}
	
	public void recommend(Long[] ids, long regionId) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Forum> page = forumDao.pagedQuery(Forum.class, 1, ids.length,
				inCodition);
		
		KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

		for (Forum forum : page.getList()) {
			forum.setKnowledgeRecommendRegion(knowledgeRecommendRegion);

			forumDao.saveOrUpdate(forum);
		}
	}

}
