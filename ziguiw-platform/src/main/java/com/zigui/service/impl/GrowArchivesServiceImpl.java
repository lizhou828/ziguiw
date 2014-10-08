package com.zigui.service.impl;

import com.zigui.dao.GenericDao;
import com.zigui.domain.GrowArchives;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrowArchivesServiceImpl {

	@Autowired
	private GenericDao<GrowArchives> growArchivesDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
	
	public Page<GrowArchives> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc){
		if(query == null){
			query = new HashMap<String, String>();
		}
		
		List<Criterion> criterionsList = new ArrayList<Criterion>(query.size());
		
		if(StringUtils.isNotEmpty((String)query.get("startTime"))){
			try {
				criterionsList.add(Restrictions.ge("createTime", format.parse((String)query.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", format.parse((String)query.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(query.get("state") != null && NumberUtils.toInt((String)query.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)query.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}

		
		if(StringUtils.isNotEmpty((String)query.get("opIds"))&&(((String)query.get("opIds")).split(";")).length>0){
			criterionsList.add(Restrictions.in("userId", CommonUtils.strsToLongs(((String)query.get("opIds")).split(";"))));
		}
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return growArchivesDao.pagedQuery(GrowArchives.class, pageNo, pageSize, orderBy, isAsc, criterions);
	}
	
	public long saveOrUpdate(GrowArchives growArchives){
		growArchivesDao.saveOrUpdate(growArchives);	
		return growArchives.getId();
	}
	
	public GrowArchives getById(long id){
		return growArchivesDao.get(GrowArchives.class, id);
	}

    public Page<GrowArchives> getByStudentId(int currentPage, int countPerPage,Integer studentId){
        String hql="from GrowArchives g where g.student.Xs_id= ? order by g.lastModifyTime desc";
        return growArchivesDao.pagedQuery(hql,currentPage,countPerPage,new Object[]{studentId});
    }
	
	public void fakeDelete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<GrowArchives> page = growArchivesDao.pagedQuery(GrowArchives.class, 1, ids.length,
				inCodition);

		for (GrowArchives growArchives : page.getList()) {
			growArchives.setState(-1);
			growArchivesDao.saveOrUpdate(growArchives);
		}
	}
	
	
	public void delete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<GrowArchives> page = growArchivesDao.pagedQuery(GrowArchives.class, 1, ids.length,
				inCodition);

		for (GrowArchives growArchives : page.getList()) {
			if(growArchives != null){
				growArchivesDao.remove(growArchives);
			}
		}
	}

    public Page<GrowArchives> pageQueryByBjId(int currentPage, int countPerPage, Long bjid) {
        String hql="from GrowArchives g where g.student.Xs_id in( select s.Xs_id from Student s where s.Bj_ID= "+bjid+" ) order by g.lastModifyTime desc";
        Page<GrowArchives> growArchivePage=growArchivesDao.pagedQuery(hql,currentPage,countPerPage);
        return growArchivePage;
    }
}
