package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.RoleDao;
import com.zigui.domain.News;
import com.zigui.domain.Role;
import com.zigui.utils.Page;

public class RoleServiceImpl {
	
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Autowired
	private RoleDao roleDao;
	
	public long saveOrUpdate(Role role) {
		roleDao.saveOrUpdate(role);
		return role.getId();
	}

	public List<Role> getRoleByName(String name) {

		List<Role> roles = roleDao.findBy(
				Role.class, "name", name);

		return roles;
	}
	
	public List<Role> getAll(){
		List<Role> roles = roleDao.getAll(Role.class);
		
		return roles;
	}

	public Role getRoleById(long id) {
		return roleDao.get(Role.class, id);
	}
	
	public void deleteRoles(Long[] ids){
		Criterion inCodition = Restrictions.in("id", ids);
		Page<Role> page = roleDao.pagedQuery(Role.class, 1, ids.length,
				inCodition);

		for (Role role : page.getList()) {
			roleDao.remove(role);
		}
	}

}
