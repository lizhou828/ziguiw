package com.zigui.service;

import java.util.List;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceStugrade;



public interface GradeService extends IBaseService<SouceStugrade, String>{
public Result getPager(Page page, String sqlKeyword);
	
	public List<SouceStugrade> getList(String sql);
}
