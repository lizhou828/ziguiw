package com.zigui.dao;

import com.zigui.utils.Page;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.impl.CriteriaImpl.OrderEntry;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"hiding","unchecked","rawtypes"})
public class GenericDao<T> extends HibernateDaoSupport {
	
	private static final Log logger = LogFactory.getLog(GenericDao.class);

	/**
	 * 
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> getAll(final Class<T> entityClass){
		Session session = getSession();
		try{
			return session.createCriteria(entityClass).list();
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}

	/**
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T get(final Class<T> entityClass, final Serializable id){
		return (T)getHibernateTemplate().get(entityClass, id);
	}
	
	/**
	 * 
	 * @param instance
	 */
	public void saveOrUpdate(final T instance){
		getHibernateTemplate().saveOrUpdate(instance);
	}
	
	
	public void saveObj(final T instance){
		getHibernateTemplate().save(instance);
	}
	
	public void updateObj(final T instance){
		getHibernateTemplate().update(instance);
	}
	
	public void update(final String hql){
		Session session = getSession();
		try{
			Query query = session.createQuery(hql);
			query.executeUpdate();
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
	}
	
	/**
	 * 
	 * @param obj
	 */
	public void remove(final T obj){
		if(obj != null){
			getHibernateTemplate().delete(obj);
		}
	}
	
	/**
	 * 
	 * @param entityClass
	 * @param id
	 */
	public void removeById(Class<T> entityClass, Serializable id){
		T obj = get(entityClass, id);
		if(obj != null){
			remove(obj);
		}
	}
	
	
       
    /**  
     * 鏍规嵁hql鏌ヨ  
     * @param hql  
     * @param values  
     * @return  
     */ 
	public List find(final String hql,final Object...values){   
		Session session = getSession();
		try{
			return createQuery(session, hql, values).list();   
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }






    /**  
     * 鏍规嵁灞炴�鍚嶅拰灞炴�鍊兼煡璇�  
     *  
     * @return  
     */  
    public <T>List<T> findBy(Class<T> entityClass,String propertyName,Object value){ 
    	Session session = getSession();
    	try{
    		return createCriteria(session, entityClass, Restrictions.eq(propertyName, value)).list();
    	}catch(Throwable e){
    		logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }   
       
    /**  
     * 鏍规嵁灞炴�鍚嶅拰灞炴�鍊兼煡璇� 鏈夋帓搴� 
     * @param <T>  
     * @param entityClass  
     * @param propertyName  
     * @param value  
     * @param orderBy  
     * @param isAsc  
     * @return  
     */  
    public <T>List<T> findBy(Class<T> entityClass,String propertyName,Object value,String orderBy,boolean isAsc){
    	Session session = getSession();
    	try{
    		return createCriteria(session, entityClass, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
    	}catch(Throwable e){
    		logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }   
       
    /**  
     * 鏍规嵁灞炴�鍚嶅拰灞炴�鍊�鏌ヨ 涓旇姹傚璞″敮涓�  
     *  
     * @return 绗﹀悎鏉′欢鐨勫敮涓�璞�  
     */  
    public <T>T findUniqueBy(Class<T> entityClass,String propertyName,Object value){
    	Session session = getSession();
    	try{
    		return (T)createCriteria(session, entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
    	}catch(Throwable e){
    		logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }

    public Page<T> pageQueryBySql(final String sql, int pageNo, final int pageSize, final Object... values) {
        String countQueryString = "select count(0) " + removeSelect(removeOrders(sql));
        List countList = findBySql(countQueryString, values);
        BigDecimal bigDecimal = (BigDecimal) countList.get(0);
        Long totalCount = bigDecimal.longValue();
        if (totalCount == null || totalCount == 0) {
            return new Page();
        }
        final int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List list = executeQuery(new ZiguiHibernateCallBack() {
            @Override
            public Object execute(Session session) {
                Query query = session.createSQLQuery(sql);
                for(int i = 0;i<values.length;i++){
                    query.setParameter(i, values[i]);
                }
                return query.setFirstResult(startIndex).setMaxResults(pageSize).list();
            }
        });
        return new Page(startIndex, totalCount, pageSize, list);
    }

    private List executeQuery(ZiguiHibernateCallBack ziguiHibernateCallBack) {
        Session session = getSession();
        try {
            return (List) ziguiHibernateCallBack.execute(session);
        } finally {
//            session.flush();
//            session.close();
//            SessionFactoryUtils.releaseSession(session, getSessionFactory());
        }
    }

    private List findBySql(String sql, Object[] values) {
        Session session = getSession();
        try{
            Query query = session.createSQLQuery(sql);
            for(int i = 0;i<values.length;i++){
                query.setParameter(i, values[i]);
            }
            return query.list();
        }catch(Throwable e){
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }finally{
//            session.flush();
//            session.clear();
//            SessionFactoryUtils.releaseSession(session, getSessionFactory());
        }
    }

    /**  
     * 鍒嗛〉 閫氳繃hql杩涜  
     * @param hql  
     * @param pageNo  
     * @param pageSize  
     * @param values  
     * @return  
     */  
    public Page<T> pagedQuery(String hql,int pageNo,int pageSize,Object...values){
        String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));   
        System.out.println(countQueryString);   
        List countList = find(countQueryString, values);   
        long totalCount =  (Long)countList.get(0);   
        if(totalCount<1){
            return new Page();
        }
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Session session = getSession();
		try{
	        Query query = createQuery(session, hql, values);
	        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
	        return new Page(startIndex,totalCount,pageSize,list);
	    }catch(Throwable e){
	    	logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }
    
    public Page<T> pagedQuery(String hql,int pageNo,int pageSize, String queryString, Object...values){
    	String countQueryString = "select count(*) " + removeSelect(removeOrders(hql));   
        System.out.println(countQueryString);   
        List countList = find(countQueryString, values);   
        long totalCount =  (Long) countList.get(0);   
        System.out.println(totalCount);   
        if(totalCount<1){
            return new Page();
        }   
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        System.out.println(startIndex);  
        Session session = getSession();
		try{
	        Query query = createQuery(session, hql,values);
	        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();   
	        return new Page(startIndex,totalCount,pageSize,list, queryString);
	    }catch(Throwable e){
	    	logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }
    
       
    /**  
     * 鍒嗛〉鏌ヨ鍑芥暟  
     * @param entityClass  
     * @param pageNo  
     * @param pageSize  
     * @param criterions  
     * @return  
     */  
    public Page<T> pagedQuery(Class<T> entityClass,int pageNo,int pageSize,Criterion...criterions){
    	Session session = getSession();
		try{
	        Criteria criteria = createCriteria(session, entityClass, criterions);
	        return pagedQuery(criteria, pageNo, pageSize);
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }   
       
    /**  
     * 鍒嗛〉鏌ヨ甯︽帓搴� 
     * @param entityClass  
     * @param pageNo  
     * @param pageSize  
     * @param orderBy  
     * @param isAsc  
     * @param criterions  
     * @return  
     */  
    public Page<T> pagedQuery(Class<T> entityClass,int pageNo,int pageSize,String orderBy,boolean isAsc,Criterion...criterions){
    	Session session = getSession();
		try{
	    	Criteria criteria = createCriteria(session, entityClass, orderBy, isAsc, criterions);
	        return pagedQuery(criteria, pageNo, pageSize);  
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }  
    
    /**  
     * 鍒嗛〉鏌ヨ甯︽帓搴� 
     * @param entityClass  
     * @param pageNo  
     * @param pageSize  
     * @param orderBy  
     * @param isAsc  
     * @param criterions  
     * @return  
     */  
    public Page<T> pagedQuery(Class<T> entityClass,int pageNo,int pageSize,String orderBy,boolean isAsc,String queryString,Criterion...criterions){
    	Session session = getSession();
		try{
	    	Criteria criteria = createCriteria(session, entityClass, orderBy, isAsc, criterions);
	        return pagedQuery(criteria, pageNo, pageSize,queryString);  
		}catch(Throwable e){
			logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
		}finally{
//			session.flush();
//			session.clear();
//			SessionFactoryUtils.releaseSession(session, getSessionFactory());
		}
    }
       
    /**  
     * 鍘婚櫎hql鐨剆elect瀛愬彞銆� 
     * @param hql  
     * @return  
     * @see #pagedQuery(String,int,int,Object[])  
     */  
    private static String removeSelect(String hql){   
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);   
    }   
       
    /**  
     * 鍘婚櫎hql鐨刼rderBy瀛愬彞銆� 
     * @param hql  
     * @return  
     * @see #pagedQuery(String,int,int,Object[])  
     */  
    private static String removeOrders(String hql) {   
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);   
        Matcher m = p.matcher(hql);   
        StringBuffer sb = new StringBuffer();   
        while (m.find()) {   
            m.appendReplacement(sb, "");   
        }   
        m.appendTail(sb);   
        return sb.toString();   
    }
    
    /**  
     * 鍒涘缓涓�釜Query瀵硅薄銆� 
     * @param hql  
     * @param values  
     * @return  
     */  
    private Query createQuery(Session session, String hql,Object...values){
		Query query = session.createQuery(hql);
        for(int i = 0;i<values.length;i++){
        	query.setParameter(i, values[i]);
        }   
        return query;
		
    }   
       
    /**  
     * 鍒涘缓Criteria瀵硅薄銆� 
     * @param <T>  
     * @param entityClass  
     * @param criterions  
     * @return  
     */  
    public <T>Criteria createCriteria(Session session, Class<T> entityClass,Criterion...criterions){
    	Criteria criteria = session.createCriteria(entityClass);
        for(Criterion c:criterions){
            criteria.add(c);   
        }   
        return criteria;  
		
    }   
    /**  
     * 鍒涘缓Criteria瀵硅薄锛屾湁鎺掑簭鍔熻兘銆� 
     * @param <T>  
     * @param entityClass  
     * @param orderBy  
     * @param isAsc  
     * @param criterions  
     * @return  
     */  
    private <T>Criteria createCriteria(Session session, Class<T> entityClass,String orderBy,boolean isAsc,Criterion...criterions){
        Criteria criteria = createCriteria(session, entityClass, criterions);
        if(isAsc){   
            criteria.addOrder(Order.asc(orderBy));
        }else{   
            criteria.addOrder(Order.desc(orderBy));
        }   
        return criteria;   
    }
    
    /**  
     * 鍒嗛〉 閫氳繃criteria  
     * @param criteria  
     * @param pageNo  
     * @param pageSize  
     * @return  
     */  
    public Page<T> pagedQuery(Criteria criteria,int pageNo,int pageSize){
        CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
           
        //鍏堟妸Projection鍜孫rderBy鏉′欢鍙栧嚭鏉�娓呯┖涓よ�鏉ユ墽琛孋ount鎿嶄綔   
        Projection projection = criteriaImpl.getProjection();
        List<OrderEntry> orderEntitys = null;
        try {
            orderEntitys=(List<OrderEntry>) FieldUtils.readDeclaredField(criteriaImpl, "orderEntries", true);
            FieldUtils.writeDeclaredField(criteriaImpl, "orderEntries", new ArrayList(), true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        //鍙栧緱鎬荤殑鏁版嵁鏁�
        long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        //灏嗕箣鍓嶇殑Projection鍜孫rderBy鏉′欢閲嶆柊璁惧洖鍘�
        criteria.setProjection(projection);
        if (projection == null) {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        try {
        	FieldUtils.writeDeclaredField(criteriaImpl, "orderEntries", orderEntitys, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        if(totalCount<1)
            return new Page();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<T> data = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page( startIndex, totalCount, pageSize, data);
    }

    /**
     * 鍒嗛〉 閫氳繃criteria
     * @param criteria
     * @param pageNo
     * @param pageSize
     * @return
     */
    private Page<T> pagedQuery(Criteria criteria,int pageNo,int pageSize,String queryString){
        CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;

        //鍏堟妸Projection鍜孫rderBy鏉′欢鍙栧嚭鏉�娓呯┖涓よ�鏉ユ墽琛孋ount鎿嶄綔
        Projection projection = criteriaImpl.getProjection();
        List<OrderEntry> orderEntitys;
        try {   
            orderEntitys=(List<OrderEntry>) FieldUtils.readDeclaredField(criteriaImpl, "orderEntries", true);
            FieldUtils.writeDeclaredField(criteriaImpl, "orderEntries", new ArrayList(), true);
        } catch (Exception e) {   
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }   
        //鍙栧緱鎬荤殑鏁版嵁鏁�  
        long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        //灏嗕箣鍓嶇殑Projection鍜孫rderBy鏉′欢閲嶆柊璁惧洖鍘�  
        criteria.setProjection(projection);   
        if (projection == null) {   
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }   
           
        try {   
        	FieldUtils.writeDeclaredField(criteriaImpl, "orderEntries", orderEntitys, true);
        } catch (Exception e) {   
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }   
        if(totalCount<1)   
            return new Page();
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<T> data = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();   
        return new Page( startIndex, totalCount, pageSize, data,queryString);
    }

    private interface ZiguiHibernateCallBack {
        Object execute(Session session);
    }
}
