package com.zigui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigui.dao.SousownDao;
import com.zigui.domain.CcczgInfoSousown;
import com.zigui.service.SousownService;

@Service("sousownService")
public class SousownServiceImpl extends BaseServiceImpl<CcczgInfoSousown, String> implements SousownService{
	
	@Autowired
	private SousownDao sousownDao;

	public SousownDao getSousownDao() {
		return sousownDao;
	}

	public void setSousownDao(SousownDao sousownDao) {
		this.sousownDao = sousownDao;
		super.setBaseDao(sousownDao);
	}

}
