package com.zigui.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes"})
public final class  Page<T> implements Serializable{
	
	private static final long serialVersionUID = 6439702038932670649L;

	private int startIndex;
	
	private long totalCount;
	
	private int pageSize;
	
	private List<T> list=new ArrayList<T>();
	
	private String pageString;
	
	public Page(){
		
	}
	
	public Page(int startIndex, long totalCount, int pageSize, List<T> list,String queryString){
		this.startIndex = startIndex;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.pageString = getPageString(queryString);
		this.list = list;
	}
	
	public Page(int startIndex, long totalCount, int pageSize, List<T> list){
		this(startIndex, totalCount, pageSize, list, "");
	}
	
	public static int getStartOfPage(int pageNo, int pageSize){
		if (pageNo <= 0) pageNo = 1;
        return (pageNo - 1) * pageSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	
	public String getPageString() {
		return pageString;
	}

	public void setPageString(String pageString) {
		this.pageString = pageString;
	}

	public int getPageCount(){
		if(pageSize == 0){
			return 0;
		}
		return (int)totalCount / pageSize +  + (totalCount % pageSize == 0 ? 0 : 1);
	}
	
	public int getCurrentPage(){
		if(pageSize == 0){
			return 0;
		}
		return (int)startIndex / pageSize + 1;
	}
	

	@Override
	public String toString() {
		return "Page [startIndex=" + startIndex + ", totalCount=" + totalCount
				+ ", pageSize=" + pageSize + ", list=" + list + "]";
	}

	/**
	 * 取得第一页的页号
	 * 
	 * @return int
	 */
	public int getFirstPage() {
		int pageCount = getPageCount();
		return pageCount == 0 ? 0 : 1;
	}

	/**
	 * 取得最后一页的页号
	 * 
	 * @return int
	 */
	public int getLastPage() {
		int pageCount = getPageCount();
		return pageCount == 0 ? 0 : pageCount;
	}

	/**
	 * 取得下一页的页号
	 * 
	 * @return int
	 */
	public int getNextPage() {
		int pageCount = getPageCount();
		int pageNow = getCurrentPage();
		int nextPage = 0;
		if (pageCount > 0) {
			if (pageNow < pageCount) {
				nextPage = pageNow + 1;
			} else {
				nextPage = pageNow;
			}
		}
		return nextPage;
	}
	
	/**
	 * 取得上一页的页号
	 * 
	 * @return int
	 */
	public int getPrePage() {
		int pageCount = getPageCount();
		int pageNow = getCurrentPage();
		int prePage = 0;
		if (pageCount > 0) {
			if (pageNow > 1) {
				prePage = pageNow - 1;
			} else {
				prePage = pageNow;
			}
		}
		return prePage;
	}
	
	public String filterQueryString(String queryString){
		if(queryString ==null || !(queryString.indexOf("currentPage")>=0)){
			return queryString;
		}
		String[] str = queryString.split("&");
		StringBuffer queryStringBuffer = new StringBuffer();
		for(int i=0 ; i<str.length ;i++){
			if(str[i].indexOf("currentPage")>=0){
				continue;
			}else{
				queryStringBuffer.append(str[i]+"&");
			}
		}
		if(queryStringBuffer.toString().length()>0){
			queryString = queryStringBuffer.toString().substring(0, queryStringBuffer.toString().length()-1);
		}
		return queryString;
	}
	
	public String getPageString(String queryString) {

		queryString = filterQueryString(queryString);
        int pageCount = getPageCount();
        int pageNow = getCurrentPage();
        
        int j = pageSize / 2;
        StringBuffer strbuf = new StringBuffer();
        if (pageNow > pageCount && pageCount != 0) {
            strbuf.append(" <font color=\"red\">... 要查看更多结果,请使用搜索 ... </font> ");
            return strbuf.toString();
        }

        if (pageNow > 1) {            
            strbuf.append("  ");
            strbuf.append("<a href=\"?currentPage=" + this.getFirstPage() + "&"
                    + queryString + "\" >");
            strbuf.append("第一页");
            strbuf.append("</a>");
            strbuf.append("  ");
        }

        if (pageNow > j + 1) {
            strbuf.append("  ");
            strbuf.append("<a href=\"?currentPage=" + this.getPrePage() + "&"
            		+ queryString + "\" >");
            strbuf.append("上一页");
            strbuf.append("</a>");
            strbuf.append("  ");
        }

        for (int i = pageNow - j; i < pageNow; i++) {
            if (i <= 0) continue;
            strbuf.append("  ");
            strbuf.append("<a href=\"?currentPage=" +  i + "&" + queryString + "\" >");
            strbuf.append("[" + i + "]");
            strbuf.append("</a>");
            strbuf.append("  ");
        }
        strbuf.append("  ");

        if (pageCount != 1 && pageCount != 0) {
            strbuf.append("<span> ");
            strbuf.append(pageNow);
            strbuf.append("</span>");
            strbuf.append("  ");
        }

        for (int i = pageNow + 1; (i < pageNow + j) && (i <= pageCount); i++) {
            strbuf.append("  ");
            strbuf.append("<a href=\"?currentPage=" + i + "&" + queryString + "\" >");
            strbuf.append("[" + i + "]");
            strbuf.append("</a>");
            strbuf.append("  ");
        }

        if (pageNow + j <= pageCount) {
            strbuf.append("  ");
            strbuf.append("<a href=\"?currentPage=" + this.getNextPage() + "&"
            		+ queryString + "\" >");
            strbuf.append("下一页");
            strbuf.append("</a>");
            strbuf.append("  ");
        }

         if(pageNow<pageCount)
         {
             strbuf.append(" ");
             strbuf.append("<a href=\"?currentPage=" + this.getLastPage() +
             "&"+ queryString + "\" >");
             strbuf.append("最后一页");
             strbuf.append("</a>");
             strbuf.append(" ");
         }
        return strbuf.toString();
    }

}
