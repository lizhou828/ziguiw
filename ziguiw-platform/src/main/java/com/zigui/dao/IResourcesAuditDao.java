package com.zigui.dao;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.Page;
import com.zigui.domain.Result;


public interface IResourcesAuditDao extends IBaseDao<CcczgInfoSouce, String>{
	
	/**
	 * 查询未审核资源
	 * @param page 分页信息
	 * @param subjvalue
	 * @return
	 */
	public Result getResource(Page page, String paramsql);
}
