package com.zigui.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zigui.dao.SousownDao;
import com.zigui.domain.CcczgInfoSousown;

@Repository("sousownDao")
@Transactional
public class SousownDaoImpl extends BaseDaoImpl<CcczgInfoSousown, String> implements SousownDao{

}
