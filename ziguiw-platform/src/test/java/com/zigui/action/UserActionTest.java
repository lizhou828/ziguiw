package com.zigui.action;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserActionTest {
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:applicationContext-*.xml" });
		UserAction userAction = (UserAction) context.getBean("userAction");

		userAction.setCountPerPage("3");
		userAction.setCurrentPage("1");
		Map<String, String> query = new HashMap<String, String>();
		query.put("regionId", "103");
		userAction.setQuery(query);		
		userAction.listByCondition();
	}

}
