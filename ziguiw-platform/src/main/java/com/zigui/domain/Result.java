package com.zigui.domain;

import java.util.ArrayList;
import java.util.List;


public class Result {
	private Page page=new Page(); // 分页信息
	private List content=new ArrayList(); // 每页显示的集合��ʾ�ļ���

	/**
	 * The default constructor
	 */
	public Result() {
		super();
	}

	/**
	 * The constructor using fields
	 * 
	 * @param page
	 * @param content
	 */
	public Result(Page page, List content) {
		this.page = page;
		this.content = content;
	}

	/**
	 * @return Returns the content.
	 */
	public List getContent() {
		return content;
	}

	/**
	 * @return Returns the page.
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param content
	 *            The content to set.
	 */
	public void setContent(List content) {
		this.content = content;
	}

	/**
	 * @param page
	 *            The page to set.
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}
