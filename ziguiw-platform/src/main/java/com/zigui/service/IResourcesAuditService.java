package com.zigui.service;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.utils.Common;

public interface IResourcesAuditService extends IBaseService<CcczgInfoSouce, String> {
	
	
	/**
	 * 审核
	 * @param id 资源id
	 * @param state 审核状态
	 * @return
	 */
	public Common updateResources(String id, Long state);
	
	/**
	 * 资源分页列表
	 * @param page 分页信息
	 * @param subjvalue
	 * @return
	 */
	public Result getResouce(Page page, String param);
}
