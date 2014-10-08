package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.GradeDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceStugrade;


@Repository("gradeDao")
@Transactional
public class GradeDaoImpl extends BaseDaoImpl<SouceStugrade, String> implements GradeDao{
	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword += " order by seat ";
		return this.findByPager(page, sqlKeyword);
	}

}
