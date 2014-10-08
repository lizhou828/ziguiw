package com.zigui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.AdDao;
import com.zigui.domain.Ad;

public class AdServiceImpl {
	
	@Autowired
	private AdDao adDao;
	
	public long saveOrUpdate(Ad ad){
		adDao.saveOrUpdate(ad);
		
		return ad.getId();
	}
	
	public List<Ad> getAll(){
		return adDao.getAll(Ad.class);
	}
	
	public void delete(long[] opIds){
		for(long id : opIds){
			adDao.removeById(Ad.class, id);
		}
		
	}
	
	public Ad getById(long id){
		return adDao.get(Ad.class, id);
	}
	
	//计算每个AD的下线天数
	public List<Ad> getDaysBetween(List<Ad> ads) {
		List<Ad> list = new ArrayList<Ad>();
		for(Ad ad : ads){
			ad.setDays(this.getDay(new Date(), ad.getEndDate()));
			list.add(ad);
		}
		
		return list;
	}
	
	public int getDay(Date startDate, Date endDate){
		 if(startDate.after(endDate)){
	         return 0;
	     }        
	    long sl=startDate.getTime();
	    long el=endDate.getTime();       
	    long ei=el-sl;           
	    return (int)(ei/(1000*60*60*24))+1; 
	}

	public List<Ad> getByState(int state) {
		
		return adDao.findBy(Ad.class, "state",state);
	}

	public List<Ad> getByStateAndFlag(int state, int flag) {
		String hql = "from Ad a where a.state=? and a.flag=?";
		return adDao.find(hql, state,flag);
		
	}

}
