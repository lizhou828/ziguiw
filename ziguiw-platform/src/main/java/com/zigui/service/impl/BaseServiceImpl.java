package com.zigui.service.impl;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;


import com.zigui.dao.IBaseDao;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.service.IBaseService;
import com.zigui.utils.Common;


public class BaseServiceImpl <T, PK extends Serializable> implements IBaseService<T, PK>{
	
	@Autowired
	private IBaseDao<T, PK> baseDao;
	
	public IBaseDao<T, PK> getBaseDao() {		return baseDao;	}
	public void setBaseDao(IBaseDao<T, PK> baseDao) {		this.baseDao = baseDao;	}
	public static Common common = new Common();

	public T get(PK id) {
		return baseDao.get(id);
	}

	public T load(PK id) {
		return baseDao.load(id);
	}
	
	public List<T> get(PK[] ids) {
		return baseDao.get(ids);
	}
	
	public T get(String propertyName, Object value) {
		return baseDao.get(propertyName, value);
	}
	
	public List<T> getList(String propertyName, Object value) {
		return baseDao.getList(propertyName, value);
	}

	public List<T> getList(String propertyName, Object value, String oderyPropertypeName, String orderType) {
		return baseDao.getList(propertyName, value, oderyPropertypeName, orderType);
	}
	
	public List<T> getAll() {
		return baseDao.getAll();
	}
	
	public Long getTotalCount() {
		return baseDao.getTotalCount();
	}
	
	public Long getTotalCount(String propertyName, Object value){
		return baseDao.getTotalCount(propertyName, value);
	}

	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		return baseDao.isUnique(propertyName, oldValue, newValue);
	}
	
	public boolean isExist(String propertyName, Object value) {
		return baseDao.isExist(propertyName, value);
	}

	public PK save(T entity) {
		return baseDao.save(entity);
	}

	public void update(T entity) {
	
		baseDao.update(entity);
	}

	public void delete(T entity) {
		baseDao.delete(entity);
	}

	public void delete(PK id) {
		baseDao.delete(id);
	}

	public void delete(PK[] ids) {
		baseDao.delete(ids);
	}
	
	public void flush() {
		baseDao.flush();
	}

	public void clear() {
		baseDao.clear();
	}
	
	public void evict(Object object) {
		baseDao.evict(object);
	}

	public Result findByPager(Page pager) {
		return baseDao.findByPager(pager);
	}
	
	public Result findByPager(Page pager,String sql) {
		return baseDao.findByPager(pager,sql);
	}
	
	public Result findByPager(Page pager, DetachedCriteria detachedCriteria) {
		return baseDao.findByPager(pager, detachedCriteria);
	}
	
	public boolean isExist(Hashtable<String, Object> table1) {
		// TODO Auto-generated method stub
		return baseDao.isExist(table1);
	}
	public T get(Hashtable<String, Object> table) {
		// TODO Auto-generated method stub
		return baseDao.get(table);
	}
	public List<T> getList(Hashtable<String, Object> table) {
		// TODO Auto-generated method stub
		return baseDao.getList(table);
	}
	public List<T> getList(String hql) {
		// TODO Auto-generated method stub
		return baseDao.getList(hql);
	}  
	public int delete(Hashtable<String, Object> table) {
		// TODO Auto-generated method stub
		return baseDao.delete(table);
	}
	public boolean isUnique(String propertyName, Object[] conditions,
			Object[] values, Object oldValue, Object newValue) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.isUnique(propertyName, conditions, values, oldValue, newValue);
	}

}
