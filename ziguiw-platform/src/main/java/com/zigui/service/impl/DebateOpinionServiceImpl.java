package com.zigui.service.impl;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.GenericDao;
import com.zigui.domain.DebateOpinion;
import com.zigui.utils.Page;

public class DebateOpinionServiceImpl {
	@Autowired
	private GenericDao<DebateOpinion> debateOpinionDao;
	
	public Page<DebateOpinion> getDebateOpinionsByDebateId(long debateId, int pageNo, int pageSize){
		Criterion criterion = Restrictions.eq("debate.id", debateId);
		
		return debateOpinionDao.pagedQuery(DebateOpinion.class, pageNo, pageSize, criterion);
		
	}
	
	public void fakeDeleteDebateOpinions(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<DebateOpinion> page = debateOpinionDao.pagedQuery(DebateOpinion.class, 1, ids.length,
				inCodition);

		for (DebateOpinion debateOpinion : page.getList()) {
			debateOpinion.setState(-1);
			debateOpinionDao.saveOrUpdate(debateOpinion);
		}
	}
	
	public long saveOrUpdate(DebateOpinion opinion){
		debateOpinionDao.saveOrUpdate(opinion);
		
		return opinion.getId();
	}

}
