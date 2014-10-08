package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.DataFunctionDao;
import com.zigui.domain.DataFunction;

public class DataFunctionServiceImpl {
	
	@Autowired
	private DataFunctionDao dataFunctionDao;
	
	public long saveOrUpdate(DataFunction dataSource){
		
		dataFunctionDao.saveOrUpdate(dataSource);
		
		return dataSource.getId();
	}
	
	public DataFunction getByName(String name){
		return dataFunctionDao.findUniqueBy(DataFunction.class, "name", name);
	}
	
	public List<DataFunction> getAll(){
		
		return dataFunctionDao.getAll(DataFunction.class);
		
	}
	
	public DataFunction getById(long id){
		return dataFunctionDao.get(DataFunction.class, id);
	}

}
