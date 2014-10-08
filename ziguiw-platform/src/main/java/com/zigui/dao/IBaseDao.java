package com.zigui.dao;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zigui.domain.Page;
import com.zigui.domain.Result;



public interface IBaseDao <T, PK extends Serializable>{
	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T get(PK id);

	/**
	 * 根据ID获取实体对象.
	 * 
	 * @param id
	 *            记录ID
	 * @return 实体对象
	 */
	public T load(PK id);
	
	/**
	 * 根据ID数组获取实体对象集合.
	 * 
	 * @param ids
	 *            ID对象数组
	 * 
	 * @return 实体对象集合
	 */
	public List<T> get(PK[] ids);
	
	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);

	/**
	 * 根据属性名和属性值获取实体对象. Hashtable
	 * 
	 * @param table key:属性名 value:属性值
	 * 
	 * @return
	 */
	public T get(Hashtable<String, Object> table);
	
	/**
	 * 根据属性名和属性值获取实体对象集合.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);
	
	
	/**
	 * 根据属性名和属性值获取实体对象集合 并根据属性值排序
	 * @param propertyName 属性名称
	 * @param value 属性值
	 * @param oderyPropertypeName 排序字段
	 * @param orderType 排序类型
	 * @return
	 */
	public List<T> getList(String propertyName, Object value, String oderyPropertypeName, String orderType);

	/**
	 * 根据属性名和属性值获取实体对象集合 Hashtable
	 * 
	 * @param table
	 * 
	 * @return
	 */
	public List<T> getList(Hashtable<String, Object> table);
	
	/**
	 * 根据传入的hql查询数据集合
	 * 
	 * @param hql
	 * 
	 * @return
	 */
	public List<T> getList(String hql);

	/**
	 * 获取所有实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();
	
	/**
	 * 获取所有实体对象总数.
	 * 
	 * @return 实体对象总数
	 */
	public Long getTotalCount();
	
	/**
	 * 根据属性名和属性值获取实体对象总数
	 * @param propertyName
	 * 			属性名称
	 * @param value
	 * 			属性值
	 * @return 是实体对象总数
	 */
	public Long getTotalCount(String propertyName, Object value);

	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(若新修改的值与原来值相等则直接返回true).
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param oldValue
	 *            修改前的属性值
	 * @param oldValue
	 *            修改后的属性值
	 * @return boolean
	 * true 唯一
	 * false 不唯一
	 */
	public boolean isUnique(String propertyName, Object oldValue, Object newValue);
	
	/**
	 * 
	 * @param propertyName  属性名
	 * @param conditions    条件字段
	 * @param values        条件值
	 * @param oldValue
	 * @param newValue
	 * @return
	 * @throws Exception
	 */
	public boolean isUnique(String propertyName,Object[] conditions,Object[] values,Object oldValue,Object newValue) throws Exception;
	
	/**
	 * 根据属性名、修改前后属性值判断在数据库中是否唯一(可以是多个参数，若新修改的值与原来值相等则直接返回true). hashtable
	 * 
	 * @param oldValue  
	 * 				修改前的属性名/属性值table
	 * @param newValue  
	 * 				修改后的属性名/属性值table
	 * @return
	 * true 唯一
	 * false 不唯一
	 */
	public boolean isUnique(Hashtable<String, Object> oldValue,Hashtable<String, Object> newValue);
	
	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean
	 * true 存在
	 * false 不存在
	 */
	public boolean isExist(String propertyName, Object value);
	
	/**
	 * 根据属性名,检查是否已经存在
	 * 
	 * @param table1 属性名称(key)+属性内容(value)
	 * 
	 * @return true 存在 false 不存在
	 */
	public boolean isExist(Hashtable<String, Object> table1);

	/**
	 * 保存实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return ID
	 */
	public PK save(T entity);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 *            对象
	 */
	public void update(T entity);
	
	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 *            对象
	 * @return
	 */
	public void delete(T entity);

	/**
	 * 根据ID删除实体对象.
	 * 
	 * @param id
	 *            记录ID
	 */
	public void delete(PK id);

	/**
	 * 根据ID数组删除实体对象.
	 * 
	 * @param ids
	 *            ID数组
	 */
	public void delete(PK[] ids);
	
	/**
	 * 根据指定条件删除实体对象.
	 * 
	 * @param table
	 */
	public int delete(Hashtable<String, Object> table);

	/**
	 * 刷新session.
	 * 
	 */
	public void flush();

	/**
	 * 清除Session.
	 * 
	 */
	public void clear();
	
	/**
	 * 清除某一对象.
	 * 
	 * @param object
	 *            需要清除的对象
	 */
	public void evict(Object object);

	/**
	 * 根据Page对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Result findByPager(Page pager);
	
	public Result findByPager(Page pager, String sql);
	
	/**
	 * 根据Page和DetachedCriteria对象进行查询(提供分页、查找、排序功能).
	 * 
	 * @param pager
	 *            Pager对象
	 * @return Pager对象
	 */
	public Result findByPager(Page pager, DetachedCriteria detachedCriteria);


}
