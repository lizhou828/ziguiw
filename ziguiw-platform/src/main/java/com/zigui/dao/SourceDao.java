package com.zigui.dao;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.utils.BusinessException;

import java.util.List;


public interface SourceDao extends IBaseDao<CcczgInfoSouce, String>{
	public List<CcczgInfoSouce> getListBetMinAndMax(String hql,Integer len) throws BusinessException;
    public CcczgInfoSouce getById(String hql) throws BusinessException;


}
