package com.zigui.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigui.dao.IResourcesAuditDao;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.Page;
import com.zigui.domain.Result;
import com.zigui.service.IResourcesAuditService;
import com.zigui.utils.Common;
import com.zigui.utils.DateUtil;

@Service("resourcesAuditService")
public class ResourcesAuditServiceImpl extends BaseServiceImpl<CcczgInfoSouce, String> implements IResourcesAuditService {
	
	@Autowired
	private IResourcesAuditDao resourcesAuditDao;
	
	@Override
	public Common updateResources(String id, Long state) {
		try {
			CcczgInfoSouce ccczgsouce = resourcesAuditDao.get(id);
			ccczgsouce.setCheckSign(state);
			/*if (state == 1 && ccczgsouce.getModifyDate() == null) {
				pointsHistoryService.updatePointsChange(ccczgsouce.getMember()
						.getMemberid(), PointsHistory.POINT_UPLOADING);
			}*/
			ccczgsouce.setCheckDate(DateUtil.setDateFormat(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			resourcesAuditDao.update(ccczgsouce);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return common;
	}

	@Override
	public Result getResouce(Page page, String param) {
		StringBuffer paramsql = new StringBuffer(" and 1=1 ");
		if ("1".equals(param) && param != null) {
			paramsql.append(" and t.checkSign in ('1','2')  ");
		}
		if ("2".equals(param) && param != null) {
			paramsql.append(" and t.checkSign='0' ");
		}
		if (!"".equals(page.getKeyword()) && page.getKeyword() != null) {
			paramsql.append(" and t.title like '%" + page.getKeyword() + "%' ");
		}
		paramsql.append(" order by t.createDate desc ");
		return resourcesAuditDao.getResource(page, paramsql.toString());
	}
    @Override
    public CcczgInfoSouce get(String id){
    	
    	return resourcesAuditDao.get(id);
    }
    
    
    
	public IResourcesAuditDao getResourcesAuditDao() {
		return resourcesAuditDao;
	}

	public void setResourcesAuditDao(IResourcesAuditDao resourcesAuditDao) {
		this.resourcesAuditDao = resourcesAuditDao;
	}

	
	
}
