package com.zigui.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanFactoryUtils implements ApplicationContextAware {
	
	private static ApplicationContext context = null;
    private Log log = LogFactory.getLog(BeanFactoryUtils.class);

    public static Object getBean(String beanName){
		if (context == null) throw new IllegalStateException("the context is not inject");
        return context.getBean(beanName);
	}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("inject the spring context!");
        context = applicationContext;
    }
}
