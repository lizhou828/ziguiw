package com.zigui.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.UserDiary;
import com.zigui.utils.LogTool;
import com.zigui.utils.StrutsPager;

/**
 * Created with IntelliJ IDEA. User: YeQiang Date: 12-12-20 Time: 下午12:02 To
 * change this template use File | Settings | File Templates.
 */
public class TestAction extends ActionSupport {

	private StrutsPager strutsPager;
	
	public String execute() {		
		
			strutsPager.queryList(UserDiary.class, "id", false);
		
		return "view";
	}

	public StrutsPager getStrutsPager() {
		return strutsPager;
	}

	@Resource
	public void setStrutsPager(StrutsPager strutsPager) {
		LogTool.getLog().info("set strutsPager");
		this.strutsPager = strutsPager;
	}
}
