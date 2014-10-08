package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.IResourcesAuditDao;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.Page;
import com.zigui.domain.Result;

@Repository("resourcesAuditDao")
@Transactional
public class ResourcesAuditDaoImpl extends BaseDaoImpl<CcczgInfoSouce, String> implements IResourcesAuditDao {
	
	public Result getResource(Page page, String paramsql) {
		StringBuffer hql=new StringBuffer(" from CcczgInfoSouce t where 1=1 ");
		if(paramsql!=null&&!paramsql.equals("")){
			hql.append(paramsql);
		}
		return this.findByPager(page, hql.toString());
	}
}
