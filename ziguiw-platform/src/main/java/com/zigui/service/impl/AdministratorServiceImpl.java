package com.zigui.service.impl;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.AdministratorDao;
import com.zigui.domain.Administrator;
import com.zigui.utils.Page;

public class AdministratorServiceImpl {
	
	@Autowired
	private AdministratorDao administratorDao;
	
	public long saveOrUpdate(Administrator administrator){
		administratorDao.saveOrUpdate(administrator);
		
		return administrator.getId();
		
	}
	
	public Page<Administrator> getAll(int pageNo, int pageSize){
		return administratorDao.pagedQuery(Administrator.class, pageNo, pageSize, "id", false, new Criterion[0]);
		
	}
	
	public Administrator getByNickName(String nickName){
		return administratorDao.findUniqueBy(Administrator.class, "nickName", nickName);
	}
	

}
