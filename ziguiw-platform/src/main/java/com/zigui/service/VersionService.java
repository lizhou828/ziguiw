package com.zigui.service;

import java.util.List;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceVersion;


public interface VersionService extends IBaseService<SouceVersion, String> {

	
	public Result getPager(Page page, String sqlKeyword);
	
	public List<SouceVersion> getList(String sql);
}
