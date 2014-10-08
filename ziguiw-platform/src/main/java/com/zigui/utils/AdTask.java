package com.zigui.utils;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.domain.Ad;
import com.zigui.service.impl.AdServiceImpl;
/**
 * 每隔24小时监测一个ad下线所剩天数
 * 为零则改状态为 0 表示下线
 * */
public class AdTask extends TimerTask{
	private AdServiceImpl adService = (AdServiceImpl) BeanFactoryUtils.getBean("adService");
	
	List<Ad> list = null; 
	@Override
	public void run() {
		System.out.println("监测执行了---------------");
		list = adService.getAll();
		for(Ad ad : list){
			//计算下线所剩天数
			int day = adService.getDay(new Date(), ad.getEndDate());
			//如果小于等于零 ，则设置状态为0
			if(day<=0 && ad.getState()!=-1){
				ad.setState(-1);
				adService.saveOrUpdate(ad);
			}
		}
		
	}

}
