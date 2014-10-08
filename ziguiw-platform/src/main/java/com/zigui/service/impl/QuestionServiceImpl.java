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

import com.zigui.dao.QuestionDao;
import com.zigui.domain.Knowledge;
import com.zigui.domain.Question;
import com.zigui.utils.Page;

public class QuestionServiceImpl {
	
	@Autowired
	private QuestionDao questionDao;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public long saveOrUpdate(Question question){
		questionDao.saveOrUpdate(question);
		
		return question.getId();
	}
	
	public Page<Question> listByCondition(@SuppressWarnings("rawtypes") Map query, int pageNo, int pageSize, String orderBy, boolean isAsc){
		if(query == null){
			query = new HashMap<String, String>();
		}
		List<Criterion> criterionsList = new ArrayList<Criterion>(query.size());
		if(StringUtils.isNumeric((String)query.get("creatorId")) && !"0".equals(query.get("creatorId"))){
			criterionsList.add(Restrictions.eq("creatorId", NumberUtils.toLong((String)query.get("creatorId"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		if(query.get("state") != null && NumberUtils.toInt((String)query.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)query.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("startTime"))){			
			try {
				criterionsList.add(Restrictions.ge("createTime", dateFormat.parse((String)query.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", dateFormat.parse((String)query.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return (Page<Question>)questionDao.pagedQuery(Question.class, pageNo, pageSize,orderBy,isAsc , criterions);
	}

	public void fakeDeleteQuestions(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Question> page = questionDao.pagedQuery(Question.class, 1, ids.length,
				inCodition);

		for (Question question : page.getList()) {
			question.setState(-1);

			questionDao.saveOrUpdate(question);
		}
	}
	
	public void restore(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Question> page = questionDao.pagedQuery(Question.class, 1, ids.length,
				inCodition);

		for (Question question : page.getList()) {
			question.setState(1);

			questionDao.saveOrUpdate(question);
		}
	}
	
	public void approveQuestions(Long[] ids, int approveFlag) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Question> page = questionDao.pagedQuery(Question.class, 1, ids.length,
				inCodition);

		for (Question question : page.getList()) {
			question.setState(approveFlag);
			questionDao.saveOrUpdate(question);
		}
	}
	
	public Question getById(long questionId){
		return questionDao.get(Question.class, questionId);
	}

}
