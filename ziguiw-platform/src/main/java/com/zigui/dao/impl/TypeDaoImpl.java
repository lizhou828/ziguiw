package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.TypeDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SourceType;

@Repository("typeDao")
@Transactional
public class TypeDaoImpl extends BaseDaoImpl<SourceType, String> implements TypeDao{

	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword += " order by typeOrder ";
		return this.findByPager(page, sqlKeyword);
	}
}
