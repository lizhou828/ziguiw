package com.zigui.service.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.AnswerDao;
import com.zigui.domain.Answer;
import com.zigui.utils.Page;

public class AnswerServiceImpl {
	
	@Autowired
	private AnswerDao answerDao;
	
	public long saveOrUpdate(Answer answer){
		
		answerDao.saveOrUpdate(answer);
		return answer.getId();
		
	}
	
	public Page<Answer> getAnswerByQuestionId(long questionId, int pageNo, int pageSize){
		Criterion criterion = Restrictions.eq("question.id", questionId);
		
		return answerDao.pagedQuery(Answer.class, pageNo, pageSize, criterion);
		
	}
	
	public Answer getById(long answerId){
		
		return answerDao.get(Answer.class, answerId);
		
	}
	
	public void fakeDeleteAnswers(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Answer> page = answerDao.pagedQuery(Answer.class, 1, ids.length,
				inCodition);

		for (Answer answer : page.getList()) {
			answer.setState(-1);
			answerDao.saveOrUpdate(answer);
		}
	}

}
