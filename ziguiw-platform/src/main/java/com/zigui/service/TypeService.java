package com.zigui.service;

import java.util.List;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SourceType;


public interface TypeService extends IBaseService<SourceType, String>{
	public Result getPager(Page page, String sqlKeyword);
	
	public List<SourceType> getList(String sql);
}
