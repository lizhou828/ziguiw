package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.AuthorityDao;
import com.zigui.domain.Authority;
import com.zigui.utils.Page;

public class AuthorityServiceImpl {
	
	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	@Autowired
	private AuthorityDao authorityDao;
	
	public long saveOrUpdate(Authority authority) {
		authorityDao.saveOrUpdate(authority);
		return authority.getId();
	}

	public List<Authority> getAuthorityByName(String name) {

		List<Authority> authoritys = authorityDao.findBy(
				Authority.class, "nickName", name);

		return authoritys;
	}

	public Authority getAuthorityById(long id) {
		return authorityDao.get(Authority.class, id);
	}
	
	public Authority getAuthorityByUrl(String url){
		return authorityDao.findUniqueBy(Authority.class, "url", url);
	}
	
	public Authority getAuthorityByCode(String code){
		return authorityDao.findUniqueBy(Authority.class, "code", code);
	}
	
	public List<Authority> getAll(){
		return authorityDao.getAll(Authority.class);
	}
	
	public List<Authority> getAuthorityByType(String code){
		return authorityDao.find("from Authority where code like ?", code + "%");
	}
	
	public void delete(long id){
		authorityDao.removeById(Authority.class, id);
	}
	
	public List<Authority> getAuthoritysByIds(Long[] ids){
		Criterion[] criterions = new Criterion[1];
		criterions[0] = Restrictions.in("id", ids);
		
		Page<Authority> page = authorityDao.pagedQuery(Authority.class, 1, ids.length, criterions);
		return page.getList();
	}

}
