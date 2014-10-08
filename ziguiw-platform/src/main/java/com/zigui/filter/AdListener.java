package com.zigui.filter;

import com.zigui.utils.AdTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.Timer;

/**
 * 每隔24小时监测一次广告下线时间
 * */
public class AdListener implements ServletContextListener { 
	private Timer timer = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(timer!=null){
			timer.cancel();                              
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		timer = new Timer(true);
		Date date = new Date();
		date.setHours(24);
		date.setMinutes(00);     
		date.setSeconds(00);
		timer.schedule(new AdTask(), date , 24*60*60*1000);
	}
	

}
