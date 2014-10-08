package com.zigui.dao;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SourceType;


public interface TypeDao extends IBaseDao<SourceType, String>{
	public Result getPager(Page page, String sqlKeyword);
}
