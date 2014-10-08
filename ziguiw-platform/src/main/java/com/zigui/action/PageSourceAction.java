package com.zigui.action;

import com.zigui.domain.Page;
import com.zigui.domain.Result;


public class PageSourceAction extends BaseSourceAction{
	/**
	 * 实例化一个page对象
	 */
	protected Page page = new Page();
	/**
	 * 返回结果的Result对象
	 */
	protected Result result;
	/**
	 * 跳往第几页
	 */
	protected String gotoPage; 
	
	
	
	public PageSourceAction() {

	}
	
	public Result getResult() {		return result;	}
	public void setResult(Result result) {		this.result = result;	}
	public Page getPage() {		return page;	}
	public void setPage(Page page) {		this.page = page;	}
	public String getGotoPage() {		return gotoPage;	}
	public void setGotoPage(String gotoPage) {		this.gotoPage = gotoPage;	}
}
