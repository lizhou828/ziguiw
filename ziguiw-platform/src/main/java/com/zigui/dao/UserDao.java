package com.zigui.dao;

import com.zigui.domain.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class UserDao extends HibernateDaoSupport {
    
	 /**

     * @ user 需要保存的User实例

     */
    public void save(User user) {
        getHibernateTemplate().save(user);
    }
    
    public void delete(User user) {
        getHibernateTemplate().delete(user);
    }
    
    public void update(User user) {
    	getHibernateTemplate().update(user);
    }
    
    /**

     * 根据主键返回特定实例

     * @ return 特定主键对应的User实例

     * @ param 主键值
     */
    public User queryById(long id) {
        return getHibernateTemplate().get(User.class, id);
    }

    public void removeUser(long id) {
    	User user = getHibernateTemplate().load(User.class, new Long(id));
    	getHibernateTemplate().delete(user);
    }

	

}
