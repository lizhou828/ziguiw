package com.zigui.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zigui.dao.VersionDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceVersion;
import com.zigui.service.VersionService;

@Service("versionService")
public class VersionServiceIml extends BaseServiceImpl<SouceVersion, String> implements VersionService {
	
	@Autowired
	private VersionDao versionDao;

	public VersionDao getVersionDao() {
		return versionDao;
	}

	public void setVersionDao(VersionDao versionDao) {
		this.versionDao = versionDao;
		super.setBaseDao(versionDao);
	}

	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword = "from SouceVersion where 1=1 " + sqlKeyword;
		return versionDao.getPager(page, sqlKeyword);
	}
	
	public List<SouceVersion> getList(String sql){
		return versionDao.getList(sql+" order by VOder ");
	}
}
