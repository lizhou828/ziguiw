package com.zigui.service;

import java.util.List;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceSubject;
import com.zigui.utils.BusinessException;


public interface SouceSubjectService extends IBaseService<SouceSubject, String>{
	
	public Result getSubjectPager(Page page, String sqlKeyword);
	
	public List<SouceSubject> getList(String sql);
	
	public List<SouceSubject> getAll() throws BusinessException;
}
