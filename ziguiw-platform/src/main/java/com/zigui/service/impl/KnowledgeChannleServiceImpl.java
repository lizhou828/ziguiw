package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.KnowledgeChannleDao;
import com.zigui.dao.KnowledgeDao;
import com.zigui.domain.KnowledgeChannle;

public class KnowledgeChannleServiceImpl {
private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private KnowledgeChannleDao knowledgeChannleDao;
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	public long saveOrUpdate(KnowledgeChannle knowledgeChannle){
		knowledgeChannleDao.saveOrUpdate(knowledgeChannle);
		return knowledgeChannle.getId();
	}
	
	public List<KnowledgeChannle> getChannleByName(String name){
		
		List<KnowledgeChannle> knowledgeChannles = knowledgeChannleDao.findBy(KnowledgeChannle.class, "name", name);
		
		return knowledgeChannles;
	}
	
	public KnowledgeChannle getChannleById(long id){
		return knowledgeChannleDao.get(KnowledgeChannle.class, id);
	}
	
	public List<KnowledgeChannle> getAllChannles(){
		return knowledgeChannleDao.getAll(KnowledgeChannle.class);
	}
	
	public void move(long fromChannle, long toChannle, String startCreateTime, String endCreateTime){
		String hql = "update Knowledge set knowledgeChannle.id = " + toChannle + " where knowledgeChannle.id = " + fromChannle;
		
		if(StringUtils.isNotEmpty(startCreateTime)){
			hql += " and to_char(createTime,'yyyy-MM-dd') > '" + startCreateTime + "'";
			
		}
		if(StringUtils.isNotEmpty(endCreateTime)){
			hql += " and to_char(createTime,'yyyy-MM-dd') < '" + endCreateTime + "'";
			
		}
		
		knowledgeChannleDao.update(hql);
	}
	
	/**
	 * 
	 * @return
	 * 当返回0时候成功
	 * 返回1时是这个频道下面还有新闻
	 */
	public int deleteNewsChannle(Long[] ids){
		for(Long id : ids){
			knowledgeChannleDao.removeById(KnowledgeChannle.class, id);
		}
		
		return 0;
	}

}
