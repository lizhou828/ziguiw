package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.SouceSubjectDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceSubject;

@Repository("souceSubjectDao")
@Transactional
public class SourceSubjectDaoImpl extends BaseDaoImpl<SouceSubject, String> implements SouceSubjectDao{
	
	public Result getSubjectPager(Page page, String sqlKeyword) {
		sqlKeyword += " order by seat ";
		return this.findByPager(page, sqlKeyword);
	}
}
