package com.zigui.dao;

import org.springframework.stereotype.Repository;

import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceStugrade;



public interface GradeDao extends IBaseDao<SouceStugrade, String>{
	public Result getPager(Page page, String sqlKeyword);
}
