package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.VersionDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceVersion;

@Repository("versionDao")
@Transactional
public class VersionDaoImpl extends BaseDaoImpl<SouceVersion, String> implements VersionDao{
	
	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword += " order by VOder ";
		return this.findByPager(page, sqlKeyword);
	}
}
