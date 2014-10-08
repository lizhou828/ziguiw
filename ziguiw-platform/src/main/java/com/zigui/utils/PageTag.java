package com.zigui.utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import com.zigui.domain.Page;


/**
 * 公用分页标签
 * @author sly
 * 
 */
public class PageTag extends BodyTagSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//分页信息
	private Page page;
	//当前页码前和后
	private final int show=2;
	
	private String formId;

	public PageTag() {
	}

	public PageTag(String pageDiv, Page page, String formId) {
		super();
		this.page = page;
//		this.url = url;
		this.formId = formId;
	}

	public int doStartTag() throws JspException {

//		<div class="pageNav">
//		<span class="na">&lt;上一页</span>
//		<strong>1</strong>
//		<a href="#">2</a>
//		<a href="#">3</a>
//		<a href="#">4</a>
//		<a href="#">5</a>
//		<span class="mor">...</span>
//		<a href="#">17</a>
//		<a class="f12" href="#">下一页&gt;</a>
//		</div>
		
		JspWriter out = pageContext.getOut();  
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("<input type=\"hidden\" id=\"gotoPage\"  name=\"gotoPage\" />");
			sb.append("<div class=\"pageNav\">");
			if(page != null){
				//中间数字
				//总页数小于等于总共要显示的页数
				if (page.getTotalPage() <= show * 2 + 3) {//当前页前后还显示两个时，加上当前页，加上首尾两个页码
					//上一页 首页数字+省略号
					if(page.getHasPrePage()){
						sb.append("<a class=\"f12\" href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+String.valueOf(page.getCurrentPage()-1)+"');"+formId+".submit();\">&lt;上一页</a>");
					}
					else{
						sb.append("<span class=\"na\">&lt;上一页</span>");
					}
					for (int i=1; i<=page.getTotalPage(); i++) {
						if (page.getCurrentPage() == i) {
							sb.append("<strong>"+i+"</strong>");
						} else {
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+i+"');"+formId+".submit();\">"+i+"</a>");
						}
					}	
					//省略号+尾页数字 下一页
					if(page.getHasNextPage()){
						sb.append("<a class=\"f12\" href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+String.valueOf(page.getCurrentPage()+1)+"');"+formId+".submit();\">下一页&gt;</a>");
					}
					else{
						sb.append("<span class=\"na\">下一页&gt;</span>");
					}
				}
				//否则
				else {
					//上一页 首页数字+省略号
					if(page.getHasPrePage()){
						sb.append("<a class=\"f12\" href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+String.valueOf(page.getCurrentPage()-1)+"');"+formId+".submit();\">&lt;上一页</a>");
						if(page.getCurrentPage() > (show+2)){
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','1');"+formId+".submit();\">1</a>");
							sb.append("<span class=\"mor\">...</span>");
						}
						else if(page.getCurrentPage() == (show+2)){
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','1');"+formId+".submit();\">1</a>");
							
						}
					}
					else{
						sb.append("<span class=\"na\">&lt;上一页</span>");
					}
					
					int startIndex = page.getCurrentPage() - show;
					if (startIndex <=1 ) {startIndex = 1;}
					
					int endIndex = page.getCurrentPage() + show;
					if (endIndex >= page.getTotalPage()) {endIndex = page.getTotalPage();}
					
					if (page.getCurrentPage()<=show) {endIndex = show * 2 + 1;}
					if(page.getCurrentPage()>=page.getTotalPage()-show){startIndex = page.getTotalPage()-(show*2);}
					
					for (int i=startIndex; i<=endIndex; i++) {
						if (page.getCurrentPage() == i) {
							sb.append("<strong>"+i+"</strong>");
						} else {
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+i+"');"+formId+".submit();\">"+i+"</a>");
						}
					}
					//省略号+尾页数字 下一页
					if(page.getHasNextPage()){
						if(page.getCurrentPage() < page.getTotalPage()-(show + 1)){
							sb.append("<span class=\"mor\">...</span>");
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+page.getTotalPage()+"');"+formId+".submit();\">"+page.getTotalPage()+"</a>");
						}
						else if(page.getCurrentPage() == page.getTotalPage() - (show + 1)){
							sb.append("<a href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+page.getTotalPage()+"');"+formId+".submit();\">"+page.getTotalPage()+"</a>");
							
						}
						sb.append("<a class=\"f12\" href=\"javascript:document.getElementById('gotoPage').setAttribute('value','"+String.valueOf(page.getCurrentPage()+1)+"');"+formId+".submit();\">下一页&gt;</a>");
					}
					else{
						sb.append("<span class=\"na\">下一页&gt;</span>");
					}
				}
			}
			else{
				sb.append("<span class=\"na\">&lt;上一页</span>");
				sb.append("<span class=\"na\">下一页&gt;</span>");
			}
			
			sb.append("</div>");
			out.print(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}


	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getShow() {
		return show;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

}
