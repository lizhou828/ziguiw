package com.zigui.dao;

import com.zigui.domain.CommonMessage;
import com.zigui.domain.GrowArchives;
import com.zigui.domain.Student;
import com.zigui.domain.UserBase;
import com.zigui.utils.Page;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonDao extends GenericDao<Object> {
	
	public Page<GrowArchives> getGrowArchivess(final int pageNum, final int pageSize,
			final List<Student> students) {
    	final String hql = "from GrowArchives where student in (:alist) order by createTime desc";
 	   	@SuppressWarnings("unchecked")
		List<GrowArchives> res = getHibernateTemplate()
 	   		.executeFind(new HibernateCallback<List<GrowArchives>>() {
 				public List<GrowArchives> doInHibernate(Session session)
 						throws HibernateException, SQLException {
 					//session.flush();
					//session.clear();
 	   				return session.createQuery(hql).setParameterList("alist", students)
 							.setFirstResult((pageNum - 1)*pageSize)
 							.setMaxResults(pageSize).list();
 	   			}
 	   	});
 	   	
 	   final String hql1 = "select count(*) from GrowArchives where student in (:alist)";
	   	@SuppressWarnings("unchecked")
		List<Long> res1 = getHibernateTemplate()
	   		.executeFind(new HibernateCallback<List<Long>>() {
				public List<Long> doInHibernate(Session session)
						throws HibernateException, SQLException {
					//session.flush();
					//session.clear();
	   				return session.createQuery(hql1).setParameterList("alist", students).list();
	   			}
	   	});
	   	
	   int totalCount = (int)res1.get(0).longValue();
	   
	   Page<GrowArchives> result = new Page<GrowArchives>(Page.getStartOfPage(pageNum, pageSize), totalCount, pageSize, res);
 	   return result;
	}
	
	
	public Page<UserBase> getTeacherStudents(final int pageNum, final int pageSize,
			final List<Student> students) {
		
		final List<Long> foreignKeys = new ArrayList<Long>();
		for(Student student : students){
			foreignKeys.add((long)student.getXs_id());
		}
		
    	final String hql = "from UserBase where foreignKey in (:alist) and type=4 order by createTime desc";
 	   	@SuppressWarnings("unchecked")
		List<UserBase> res = getHibernateTemplate()
 	   		.executeFind(new HibernateCallback<List<UserBase>>() {
 				public List<UserBase> doInHibernate(Session session)
 						throws HibernateException, SQLException {
 					//session.flush();
					//session.clear();
 	   				return session.createQuery(hql).setParameterList("alist", foreignKeys)
 							.setFirstResult((pageNum - 1)*pageSize)
 							.setMaxResults(pageSize).list();
 	   			}
 	   	});
 	   	
 	   final String hql1 = "select count(*) from UserBase where type=4 and foreignKey in (:alist)";
	   	@SuppressWarnings("unchecked")
		List<Long> res1 = getHibernateTemplate()
	   		.executeFind(new HibernateCallback<List<Long>>() {
				public List<Long> doInHibernate(Session session)
						throws HibernateException, SQLException {
					//session.flush();
					//session.clear();
	   				return session.createQuery(hql1).setParameterList("alist", foreignKeys).list();
	   			}
	   	});
	   	
	   int totalCount = (int)res1.get(0).longValue();
	   
	   Page<UserBase> result = new Page<UserBase>(Page.getStartOfPage(pageNum, pageSize), totalCount, pageSize, res);
 	   return result;
	}
	
	public Page<CommonMessage> getClassNoticeByTeacher(final int pageNum, final int pageSize,
			final List<String> classIds) {
		
		
    	final String hql = "from CommonMessage where classId in (:alist) and kind = 4 and state = 1 order by createTime desc";
 	   	@SuppressWarnings("unchecked")
		List<CommonMessage> res = getHibernateTemplate()
 	   		.executeFind(new HibernateCallback<List<CommonMessage>>() {
 				public List<CommonMessage> doInHibernate(Session session)
 						throws HibernateException, SQLException {
 					//session.flush();
					//session.clear();
 	   				return session.createQuery(hql).setParameterList("alist", classIds)
 							.setFirstResult((pageNum - 1)*pageSize)
 							.setMaxResults(pageSize).list();
 	   			}
 	   	});
 	   	
 	   final String hql1 = "select count(*) from CommonMessage where classId in (:alist) and kind = 4  and state = 1 ";
	   	@SuppressWarnings("unchecked")
		List<Long> res1 = getHibernateTemplate()
	   		.executeFind(new HibernateCallback<List<Long>>() {
				public List<Long> doInHibernate(Session session)
						throws HibernateException, SQLException {
					//session.flush();
					//session.clear();
	   				return session.createQuery(hql1).setParameterList("alist", classIds).list();
	   			}
	   	});
	   	
	   int totalCount = (int)res1.get(0).longValue();
	   
	   Page<CommonMessage> result = new Page<CommonMessage>(Page.getStartOfPage(pageNum, pageSize), totalCount, pageSize, res);
 	   return result;
	}

}
