package com.zigui.dao;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceVersion;


public interface VersionDao extends IBaseDao<SouceVersion, String>{
	public Result getPager(Page page, String sqlKeyword);
}
