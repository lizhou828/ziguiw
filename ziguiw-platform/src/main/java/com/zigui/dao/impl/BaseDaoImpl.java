package com.zigui.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import com.zigui.dao.IBaseDao;
import com.zigui.domain.Page;
import com.zigui.domain.Page.OrderType;
import com.zigui.domain.Result;
import com.zigui.utils.LogUtil;
import com.zigui.utils.PageUtil;

@Repository("baseDao")
@Transactional
public class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao<T, PK> {
	private Class<T> entityClass;
	
	@Autowired
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		this.entityClass = null;
        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	@SuppressWarnings("unchecked")
	public T get(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public T load(PK id) {
		Assert.notNull(id, "id is required");
		return (T) getSession().load(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> get(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		String hql = "from " + entityClass.getName() + " as model where model.id in(:ids)";
		return getSession().createQuery(hql).setParameterList("ids", ids).list();
	}

	@SuppressWarnings("unchecked")
	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		return (T) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
//		Assert.notNull(value, "value is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName;
		if(null == value || String.valueOf(value).length() == 0){
			hql += " is null or model."+propertyName+" = ''";
			return getSession().createQuery(hql).list();
		}
		else{
			hql += " = ?";
			return getSession().createQuery(hql).setParameter(0, value).list();
		}
	}
	
	/**
	 * 根据属性名查询列表 并根据对应字段进行 排序
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(String propertyName, Object value, String oderyPropertypeName, String orderType) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.hasText(oderyPropertypeName, "oderyPropertypeName must not be empty");
		Assert.notNull(orderType,"orderType is required");
		String hql = "from " + entityClass.getName() + " as model where model." + propertyName;
		if(null == value || String.valueOf(value).length() == 0){
			hql += " is null or model."+propertyName+" = '' order by  model."+oderyPropertypeName+" "+orderType;
			return getSession().createQuery(hql).list();
		}
		else{
			hql += " = ? order by model."+oderyPropertypeName+" "+orderType;
			return getSession().createQuery(hql).setParameter(0, value).list();
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "from " + entityClass.getName();
		return getSession().createQuery(hql).list();
	}
	
	public Long getTotalCount() {
		String hql = "select count(*) from " + entityClass.getName();
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public Long getTotalCount(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		String hql = "select count(*) from " + entityClass.getName() + " as model where model." + propertyName + " = ?";
		
		return (Long) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
	}
	
	protected Long getTotalCount(String hql, List listvalue) {
		Assert.hasText(hql, "hql must not be empty");
		Assert.notNull(listvalue, "listvalue is required");

		Query query = getSession().createQuery(hql);
		for(int j = 0;j < listvalue.size();j++){
			query.setParameter(j, listvalue.get(j));
		}
		
		return (Long) query.uniqueResult();
	}
   
	/**
	 * 带条件的查询
	 * @param propertyName
	 * @param conditions
	 * @param values
	 * @param oldValue
	 * @param newValue
	 * @return
	 * @throws Exception
	 */
	public boolean isUnique(String propertyName,Object[] conditions,Object[] values,Object oldValue,Object newValue) throws Exception{
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		Hashtable table=new Hashtable();
		table.put(propertyName, newValue);
		if(null!=conditions && null!=values ){
			if(conditions.length!=values.length){
				throw new Exception("查询条件的参数和值数量不一致！");
			}
			if(conditions.length>0){
				for(int i=0;i<conditions.length;i++){
					table.put(conditions[i], values[i]);
				}
			}
			
		}
		
		T object = this.get(table);
		return object==null;
	}
	
	public boolean isUnique(String propertyName, Object oldValue, Object newValue) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		if (newValue instanceof String) {
			if (oldValue != null && StringUtils.equalsIgnoreCase((String) oldValue, (String) newValue)) {
				return true;
			}
		}
		T object = get(propertyName, newValue);
		return (object == null);
	}

	public boolean isUnique(Hashtable<String, Object> oldValue,Hashtable<String, Object> newValue) {
		Assert.notNull(newValue, "newValue is required");
		if (newValue == oldValue || newValue.equals(oldValue)) {
			return true;
		}
		
		//判断传入的old和new是否本身相等
		int temp = 1;//标记是否所有的参数都相等，0 否 1 是
		for(Map.Entry<String, Object> o : oldValue.entrySet()){

			for(Map.Entry<String, Object> n : newValue.entrySet()){
				if(StringUtils.equalsIgnoreCase(o.getKey(),n.getKey())){
					//判断两种相等的情况，否则为不相等
					if(o.getValue()==n.getValue() || o.getValue().equals(n.getValue())){
						temp = 1;
						break;
					}
					else if(o.getValue() instanceof String){
						if (o.getValue() != null && StringUtils.equalsIgnoreCase((String)o.getValue(), (String) n.getValue())) {
							temp = 1;
							break;
						}
					}
					
					temp = 0;
					break;
				}
			}
			if(temp == 0){
				break;
			}
		}
		if(temp == 1){
			return true;
		}
		
		T object = get(newValue);
		return (object == null);
	}
	
	public boolean isExist(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		List list = this.getList(propertyName, value);
		return (list != null && list.size() > 0);
	}

	@SuppressWarnings("unchecked")
	public boolean isExist(Hashtable<String, Object> table1) {
		Assert.notNull(table1, "table1 is required");
		
		StringBuffer hql = new StringBuffer();//hql语句
		List<String> propertyNameList = new ArrayList<String>();//属性名列表
		List<Object> valueList = new ArrayList<Object>();//对应的属性内容列表
		
		hql.append("from ").append(entityClass.getName());
		hql.append(" as model where 1=1");
		
		for(Map.Entry<String, Object> m : table1.entrySet()){
			propertyNameList.add(m.getKey());
			valueList.add(m.getValue());
		}
		
		for(int i = 0;i < propertyNameList.size();i++){
			hql.append(" and model.").append(propertyNameList.get(i)).append(" = ? ");
		}
		
		Query query = getSession().createQuery(hql.toString());
		for(int j = 0;j < valueList.size();j++){
			query.setParameter(j, valueList.get(j));
		}
		
		List list = query.list();
		
		return (list != null && list.size() > 0);
	}

	@SuppressWarnings("unchecked")
	public PK save(T entity) {
		Assert.notNull(entity, "entity is required");
		return (PK) getSession().save(entity);
	}

	public void update(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().update(entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity, "entity is required");
		getSession().delete(entity);
	}

	public void delete(PK id) {
		Assert.notNull(id, "id is required");
		T entity = load(id);
		getSession().delete(entity);
	}

	public void delete(PK[] ids) {
		Assert.notEmpty(ids, "ids must not be empty");
		for (PK id : ids) {
			T entity = load(id);
			getSession().delete(entity);
		}
	}
	
	public int delete(Hashtable<String, Object> table){
		Assert.notNull(table, "table1 is required");
		
		StringBuffer hql = new  StringBuffer();
		List<String> propertyNameList = new ArrayList<String>();//属性名列表
		List<Object> valueList = new ArrayList<Object>();//对应的属性内容列表
		
		hql.append("delete from ").append(entityClass.getName()).append(" as model where 1=1 ");
		
		for(Map.Entry<String, Object> m : table.entrySet()){
			propertyNameList.add(m.getKey());
			valueList.add(m.getValue());
		}
		
		for(int i = 0;i < propertyNameList.size();i++){
			hql.append(" and model.").append(propertyNameList.get(i)).append(" = ? ");
		}
		
		Query query = getSession().createQuery(hql.toString());
		for(int j = 0;j < valueList.size();j++){
			query.setParameter(j, valueList.get(j));
		}
		
		return query.executeUpdate();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}
	
	public Result findByPager(Page pager) {
		if (pager == null) {
			pager = new Page();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		return findByPager(pager, detachedCriteria);
	}

	public Result findByPager(Page pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new Page();
		}
		int beginIndex = pager.getBeginIndex();//当前页
		int pageSize = pager.getEveryPage();// 每页记录数
		String property = pager.getProperty();// 查找属性名称
		String keyword = pager.getKeyword();// 查找关键字
		String orderBy = pager.getOrderBy();// 排序字段
		OrderType orderType = pager.getOrderType();// 排序方式
//		Integer totalCount = pager.getTotalCount();
		
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
			String propertyString = "";
			if (property.contains(".")) {
				String propertyPrefix = StringUtils.substringBefore(property, ".");
				String propertySuffix = StringUtils.substringAfter(property, ".");
				criteria.createAlias(propertyPrefix, "model");
				propertyString = "model." + propertySuffix;
			} else {
				propertyString = property;
			}
			criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
		}
		
		
		criteria.setProjection(null);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult(beginIndex);
		criteria.setMaxResults(pageSize);
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		
		Result result = new Result();
		result.setPage(pager);
		result.setContent(criteria.list());
		return result;
	}
	
	
	/**
	 * 根据Page和sql语句进行查询(提供分页、查找、排序功能).
	 * 不需要先查询数据条数
	 * 
	 * @param pager
	 *            Pager对象
	 * @param sql
	 * 			  sql语句
	 * @return Pager对象
	 */
	public Result findByPager(Page pager, String sql) {
		try{
			//查总数
			StringBuffer count= new StringBuffer();
			count.append("select count(*) ");
			sql = sql.substring(sql.indexOf("from"));
			count.append(sql);

			//总数目
			Long totalRecords =(Long)getSession().createQuery(count.toString()).uniqueResult();
			pager.setTotalCount(totalRecords.intValue());

			//创建页面
			pager = PageUtil.createPage(pager, totalRecords.intValue()); 
			
			//查分页数据
			List list=null;
			int first = 0,max = 0;
			first = (pager.getCurrentPage() - 1) * pager.getEveryPage();
			max = pager.getEveryPage();
			if(first<0){
				first=0;
			}
			try{
				list=this.getSession().createQuery(sql)
				.setFirstResult(first)
				.setMaxResults(max)
				.list();
			}catch(Exception ex){
				ex.printStackTrace();
			}
	
			Result result = new Result();
			result.setPage(pager);
			result.setContent(list);
			return result;
		}catch(Exception e){
			LogUtil.logger.error("basedao分页查询错误!");
			e.printStackTrace();
			
			return null;
		}
	}
	

	protected Result findByPager(Page pager, String hql,List values) {
		try{
			
			//查总数
			StringBuffer count= new StringBuffer();
			count.append("select count(*) ");
			hql = hql.substring(hql.indexOf("from"));
			count.append(hql);
			Query query = getSession().createQuery(count.toString());
			
			if(null != values){
				for(int i = 0;i < values.size();i++){
					query.setParameter(i, values.get(i));
				}
			}
			//总数目
	     	Long totalRecords =(Long)query.uniqueResult();
			pager.setTotalCount(totalRecords.intValue());
			//创建页面
			pager = PageUtil.createPage(pager, totalRecords.intValue()); 
			//查分页数据
			List list=null;
			int first = 0,max = 0;
			first = (pager.getCurrentPage() - 1) * pager.getEveryPage();
			max = pager.getEveryPage();
			if(first<0){
				first=0;
			}
			try{
				Query query_ = getSession().createQuery(hql);
				if(null != values){
					for(int i = 0;i < values.size();i++){
						query_.setParameter(i, values.get(i));
					}
				}
				list=query_.setFirstResult(first)
				.setMaxResults(max)
				.list();
			}catch(Exception ex){
				ex.printStackTrace();
			}
	
			Result result = new Result();
			result.setPage(pager);
			result.setContent(list);
			return result;
		}catch(Exception e){
			LogUtil.logger.error("basedao分页查询错误!");
			e.printStackTrace();
			
			return null;
		}
	}

	public T get(Hashtable<String, Object> table) {
		Assert.notNull(table, "table1 is required");
		
		StringBuffer hql = new StringBuffer();//hql语句
		List<String> propertyNameList = new ArrayList<String>();//属性名列表
		List<Object> valueList = new ArrayList<Object>();//对应的属性内容列表
		
		hql.append("from ").append(entityClass.getName());
		hql.append(" as model where 1=1");
		//取出属性对
		for(Map.Entry<String, Object> m : table.entrySet()){
			propertyNameList.add(m.getKey());
			valueList.add(m.getValue());
		}
		//添加where条件
		for(int i = 0;i < propertyNameList.size();i++){
			hql.append(" and model.").append(propertyNameList.get(i)).append(" = ? ");
		}
		
//		System.out.println("sql语句:"+hql.toString());
		//设置参数
		Query query = getSession().createQuery(hql.toString());
		for(int j = 0;j < valueList.size();j++){
			query.setParameter(j, valueList.get(j));
		}
		
		return (T)query.uniqueResult();
	}

	public List<T> getList(Hashtable<String, Object> table) {
		Assert.notNull(table, "table1 is required");
		
		StringBuffer hql = new StringBuffer();//hql语句
		List<String> propertyNameList = new ArrayList<String>();//属性名列表
		List<Object> valueList = new ArrayList<Object>();//对应的属性内容列表
		
		hql.append("from ").append(entityClass.getName());
		hql.append(" as model where 1=1");
		//取出属性对
		for(Map.Entry<String, Object> m : table.entrySet()){
			propertyNameList.add(m.getKey());
			valueList.add(m.getValue());
		}
		//添加where条件
		for(int i = 0;i < propertyNameList.size();i++){
			hql.append(" and model.").append(propertyNameList.get(i)).append(" = ? ");
		}
		
//		System.out.println("sql语句:"+hql.toString());
		//设置参数
		Query query = getSession().createQuery(hql.toString());
		for(int j = 0;j < valueList.size();j++){
			query.setParameter(j, valueList.get(j));
		}
		
		return (List<T>)query.list();
	}

	public List<T> getList(String hql) {
		return getSession().createQuery(hql).list();
	}

	/**
	 * 此方法只能被继承使用 无对service层的接口
	 * 根据传入的hql语句 ，设置参数并查询数据返回
	 * @param hql hql语句
	 * @param list 将要设置的参数的值
	 * @return
	 */
	protected List<T> getList(String hql,List list) {

		Query query = getSession().createQuery(hql);
		for(int j = 0;j < list.size();j++){
			query.setParameter(j, list.get(j));
		}
		
		return (List<T>)query.list();
	}


	/**
	 * 此方法只能被继承使用 无对service层的接口
	 * 根据传入的hql语句 ，设置参数并查询数据返回
	 * @param hql hql语句
	 * @param list 将要设置的参数的值
	 * @param firstResult 返回的第一条数据的索引
	 * @param maxResults 返回的最后一条数据的索引
	 * @return
	 */
	protected List<T> getList(String hql,List list,int firstResult,int maxResults) {

		Query query = getSession().createQuery(hql);
		for(int j = 0;j < list.size();j++){
			query.setParameter(j, list.get(j));
		}
		
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		
		return (List<T>)query.list();
	}
}
