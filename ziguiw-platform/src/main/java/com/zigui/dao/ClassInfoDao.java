package com.zigui.dao;

import com.zigui.domain.ClassNews;
import com.zigui.domain.ClassPhoto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-12-18
 * Time: PM4:08.
 */
public class ClassInfoDao extends GenericDao{

       public List<ClassNews> getClassNewsByClassid(final Long class_id){
           final String hql = "from ClassNews where class_id = ? and status = 1 order by create_time desc";
           List<ClassNews> list = getHibernateTemplate().executeFind(new HibernateCallback<List<ClassNews>>() {
               public List<ClassNews> doInHibernate(Session session) throws HibernateException, SQLException {
                       return session.createQuery(hql).setLong(0, class_id).list();
               }
           });
           return list;
       }

       public  List<ClassPhoto> getClassPhotoByClassid(final Long class_id){
           final String hql = "from ClassPhoto where class_id = ? and status = 1 order by create_time desc";
           List<ClassPhoto> list = getHibernateTemplate().executeFind(new HibernateCallback<List<ClassPhoto>>() {
               public List<ClassPhoto> doInHibernate(Session session) throws HibernateException, SQLException {
                     return session.createQuery(hql).setLong(0,class_id).list();
               }
           });
           return list;
       }
}
