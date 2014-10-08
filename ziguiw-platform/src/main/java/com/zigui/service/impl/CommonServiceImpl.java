package com.zigui.service.impl;

import com.zigui.dao.CommonDao;
import com.zigui.domain.GrowArchives;
import com.zigui.domain.Student;
import com.zigui.domain.UserBase;
import com.zigui.utils.Page;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class CommonServiceImpl {
	
	@Autowired
	private CommonDao commonDao;
	
	public Page<Object> getByHql(String hql, int pageNo, int pageSize, Object... values){
		System.out.println("hql" + hql);
		System.out.println("values" + ArrayUtils.toString(values));
		return commonDao.pagedQuery(hql, pageNo, pageSize, values);
	}
	
	public Page<Object> getByHql(String hql, int pageNo, int pageSize, String queryString, Object... values){
		return commonDao.pagedQuery(hql, pageNo, pageSize, queryString, values);
	}
	
	public <T> T get(final Class<T> entityClass, final Serializable id){
		return (T)commonDao.get(entityClass, id);
	}
	
	
	
	public void update(String hql){
		commonDao.update(hql);
	}
	
	public void save(Object obj){
		commonDao.saveObj(obj);
	}
	
	public void saveOrUpdate(Object obj){
		commonDao.saveOrUpdate(obj);
	}
	
	public void deleteById(final Class entityClass, final Serializable id){
		commonDao.removeById(entityClass, id);
	}
	
	public Page<GrowArchives> getGrowArchivess(final int pageNum, final int pageSize,
			final List<Student> students) {
		return commonDao.getGrowArchivess(pageNum, pageSize, students);
	}
	
	public Page<UserBase> getTeacherStudents(final int pageNum, final int pageSize,
			final List<Student> students) {
		return commonDao.getTeacherStudents(pageNum, pageSize, students);
	}
	
	public <T>T findUniqueBy(Class<T> entityClass,String propertyName,Object value){
		return commonDao.findUniqueBy(entityClass, propertyName, value);
    } 
	
	public <T>List<T> findBy(Class<T> entityClass,String propertyName,Object value){
		return commonDao.findBy(entityClass, propertyName, value, propertyName, false);
    }  

}
