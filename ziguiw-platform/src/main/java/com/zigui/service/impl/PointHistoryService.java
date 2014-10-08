package com.zigui.service.impl;

import com.zigui.dao.PointsHistoryDao;
import com.zigui.domain.PointsHistory;
import com.zigui.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("pointHistoryService")
public class PointHistoryService {
	
	@Autowired
	PointsHistoryDao pointsHistoryDao;
    private String hql;


	public void save(PointsHistory pointsHistory) {
		pointsHistoryDao.saveOrUpdate(pointsHistory);
	}

    public Page<PointsHistory> queryForPage(Integer currentPage,Integer currentPerPage,Object...values) {
        hql="select p from PointsHistory p where p.state >= 0 and p.user.id =? order by changeTime desc";
        return pointsHistoryDao.pagedQuery(hql,currentPage,currentPerPage,values);

    }
}
