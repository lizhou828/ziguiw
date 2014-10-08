package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.zigui.dao.GradeDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceStugrade;
import com.zigui.service.GradeService;


@Service("gradeService")
public class GradeServiceIml  extends BaseServiceImpl<SouceStugrade, String> implements GradeService{
	@Autowired
	private GradeDao gradeDao;

	public GradeDao getGradeDao() {
		return gradeDao;
	}

	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
		super.setBaseDao(gradeDao);
	}

	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword = "from SouceStugrade where 1=1 " + sqlKeyword;
		return gradeDao.getPager(page, sqlKeyword);
	}
	
	public List<SouceStugrade> getList(String sql){
		return gradeDao.getList(sql+" order by seat ");
	}
}
