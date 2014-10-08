package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigui.dao.TypeDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.domain.SourceType;
import com.zigui.service.TypeService;


@Service("typeService")
public class TypeServiceIml extends BaseServiceImpl<SourceType, String> implements TypeService{
	
	@Autowired
	private TypeDao typeDao;

	public TypeDao getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
		super.setBaseDao(typeDao);
	}

	public Result getPager(Page page, String sqlKeyword) {
		sqlKeyword = "from SourceType where 1=1 " + sqlKeyword;
		return typeDao.getPager(page, sqlKeyword);
	}
	
	public List<SourceType> getList(String sql){
		return typeDao.getList(sql);
	}
}
