package com.zigui.dao;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceSubject;


public interface SouceSubjectDao extends IBaseDao<SouceSubject, String>{

	public Result getSubjectPager(Page page, String sqlKeyword);
}
