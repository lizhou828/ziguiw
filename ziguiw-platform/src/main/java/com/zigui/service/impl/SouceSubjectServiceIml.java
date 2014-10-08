package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigui.dao.SouceSubjectDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SouceSubject;
import com.zigui.service.SouceSubjectService;
import com.zigui.utils.BusinessException;


@Service("souceSubjectService")
public class SouceSubjectServiceIml extends BaseServiceImpl<SouceSubject, String> implements SouceSubjectService {

	
	@Autowired
	private SouceSubjectDao souceSubjectDao;

	public SouceSubjectDao getSouceSubjectDao() {
		return souceSubjectDao;
	}

	public void setSouceSubjectDao(SouceSubjectDao souceSubjectDao) {
		this.souceSubjectDao = souceSubjectDao;
		super.setBaseDao(souceSubjectDao);
	}

	public Result getSubjectPager(Page page, String sqlKeyword) {
		sqlKeyword = "from SouceSubject where 1=1 " + sqlKeyword;
		return souceSubjectDao.getSubjectPager(page, sqlKeyword);
	}
	
	public List<SouceSubject> getList(String sql){
		return souceSubjectDao.getList(sql+" order by seat ");
	}
	
	public List<SouceSubject> getAll() throws BusinessException{
		StringBuffer hql = new StringBuffer(" from SouceSubject order by seat");
		return souceSubjectDao.getList(hql.toString());
	}
}
