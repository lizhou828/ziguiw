package com.zigui.dao.impl;

import com.zigui.dao.SourceDao;
import com.zigui.domain.CcczgInfoSouce;
import com.zigui.utils.BusinessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("sourceDao")
public class SourceDaoImpl extends BaseDaoImpl<CcczgInfoSouce, String>
											implements SourceDao{
	@SuppressWarnings("unchecked")
	public List<CcczgInfoSouce> getListBetMinAndMax(String hql, Integer len)
			throws BusinessException {
		try {
			return this.getSession().createQuery(hql).setFirstResult(0)
					.setMaxResults(len).list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询出错");
		}
	}

    @SuppressWarnings("unchecked")
    public CcczgInfoSouce getById(String hql) throws BusinessException {
        try{
            return (CcczgInfoSouce)this.getSession().createQuery(hql).uniqueResult();
        }  catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("查询出错");
        }

    }
}
