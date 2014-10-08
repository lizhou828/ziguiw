package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.utils.Page;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchAction extends ActionSupport {
	
	private static final long serialVersionUID = 4682799354755950896L;

	private static final Log logger = LogFactory.getLog(SearchAction.class);

	private static final String NEWS_SEARCH_URL = "http://localhost:8081/solr/news/";
	private static final String KNOWLEDGE_SEARCH_URL = "http://localhost:8081/solr/knowledge/";
	private static final String USER_SEARCH_URL = "http://localhost:8081/solr/user/";

	private int pageSize = 10;
	private int currentPage = 1;
	private String keyWords;
	private String type = "news";
	private Page<Map<String, String>> searchResult;

	public String execute() {
		if ("news".equals(type)) {
			return searchNews();
		} else if ("knowledge".equals(type)) {
			return searchKnowledge();
		} else if ("user".equals(type)) {
			return searchUser();
		}else{
			return searchUser();
		}   
	}

	public String searchNews() {
		SolrQuery query = new SolrQuery("title:" + keyWords + " or content:"
				+ keyWords);
		query.set("wt", "json");
		query.setHighlight(true).setHighlightSimplePre("<span class='red_b9'>")
				.setHighlightSimplePost("</span>").setStart(Page.getStartOfPage(currentPage, pageSize)).setRows(pageSize)
				.setHighlightFragsize(200);
		query.setParam("hl.fl", "title,content");

		String url = NEWS_SEARCH_URL;
		HttpSolrServer client = new HttpSolrServer(url);
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		try {
			QueryResponse response = client.query(query);
			SolrDocumentList list = response.getResults();
			
			searchResult = new Page<Map<String, String>>(Page.getStartOfPage(currentPage, pageSize), list.getNumFound(), pageSize, resultList, "keyWords=" + keyWords + "&type=news");

			for (SolrDocument sd : list) {
				Map<String, String> recored = new HashMap<String, String>();
				
				String id = (String) sd.getFieldValue("id");
				String title = (String)sd.getFieldValue("title");
				String content = (String)sd.getFieldValue("content");
				if(logger.isDebugEnabled()){
//					logger.debug("(String) sd.getFieldValue(\"id\"):" + (String) sd.getFieldValue("id"));
				}
				if (response.getHighlighting().get(id) != null) {
					if(logger.isDebugEnabled()){
						logger.debug("response.getHighlighting().get(id):" + response.getHighlighting().get(id));
					}
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("title"))){
						title = response.getHighlighting().get(id)
								.get("title").get(0);
					}
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("content"))){
						content = response.getHighlighting().get(id)
								.get("content").get(0);
					}
					
				}
				recored.put("id", id);
				recored.put("title", title);
				recored.put("content", content);
				
				resultList.add(recored);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public String searchKnowledge() {
		SolrQuery query = new SolrQuery("title:" + keyWords + " or content:"
				+ keyWords);
		query.set("wt", "json");
		query.setHighlight(true)
				.setHighlightSimplePre("<span class='red_b9'>")
				.setHighlightSimplePost("</span>").setStart(Page.getStartOfPage(currentPage, pageSize)).setRows(pageSize)
				.setHighlightFragsize(200);
		query.setParam("hl.fl", "title, content");

		String url = KNOWLEDGE_SEARCH_URL;
		HttpSolrServer client = new HttpSolrServer(url);
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		try {
			QueryResponse response = client.query(query);
			SolrDocumentList list = response.getResults();
			
			searchResult = new Page<Map<String, String>>(Page.getStartOfPage(currentPage, pageSize), list.getNumFound(), pageSize, resultList, "keyWords=" + keyWords + "&type=knowledge");

			for (SolrDocument sd : list) {
				Map<String, String> recored = new HashMap<String, String>();
				
				String id = (String) sd.getFieldValue("id");
				String title = (String)sd.getFieldValue("title");
				String content = (String)sd.getFieldValue("content");
				
				if (response.getHighlighting().get(id) != null) {
					if(logger.isDebugEnabled()){
						logger.debug("response.getHighlighting().get(id):" + response.getHighlighting().get(id));
					}
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("title"))){
						title = response.getHighlighting().get(id)
								.get("title").get(0);
					}
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("content"))){
						content = response.getHighlighting().get(id)
								.get("content").get(0);
					}
				}
				
				recored.put("id", id);
				recored.put("title", title);
				recored.put("content", content);
				
				resultList.add(recored);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}
	
	public String searchUser() {
		SolrQuery query = new SolrQuery("nick_name:" + keyWords + " or signature:"
				+ keyWords);
		query.set("wt", "json");
		query.setHighlight(true)
				.setHighlightSimplePre("<span class='red_b9'>")
				.setHighlightSimplePost("</span>").setStart(Page.getStartOfPage(currentPage, pageSize)).setRows(pageSize)
				.setHighlightFragsize(200);
		query.setParam("hl.fl", "nick_name, signature");

		String url = USER_SEARCH_URL;
		HttpSolrServer client = new HttpSolrServer(url);
		
		
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();

		try {
			QueryResponse response = client.query(query);
			SolrDocumentList list = response.getResults();
			
			searchResult = new Page<Map<String, String>>(Page.getStartOfPage(currentPage, pageSize), list.getNumFound(), pageSize, resultList, "keyWords=" + keyWords + "&type=user");

			for (SolrDocument sd : list) {
				Map<String, String> recored = new HashMap<String, String>();
				
				String id = (String) sd.getFieldValue("id");
				String nickName = (String)sd.getFieldValue("nick_name");
				String rawNickName = nickName;
				recored.put("rawNickName", rawNickName);
				String signature = (String)sd.getFieldValue("signature");
				String portrait = (String)sd.getFieldValue("portrait");
				
				if (response.getHighlighting().get(id) != null) {
					if(logger.isDebugEnabled()){
						logger.debug("response.getHighlighting().get(id):" + response.getHighlighting().get(id));
					}
					
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("nick_name"))){
						nickName = response.getHighlighting().get(id)
								.get("nick_name").get(0);
					}
					if(CollectionUtils.isNotEmpty(response.getHighlighting().get(id)
                            .get("signature"))){
						signature = response.getHighlighting().get(id)
								.get("signature").get(0);
					}
				}
				
				recored.put("id", id);
				recored.put("nick_name", nickName);
				recored.put("signature", signature);
				recored.put("portrait", portrait);
				
				resultList.add(recored);
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		return Action.SUCCESS;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Page<Map<String, String>> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(Page<Map<String, String>> searchResult) {
		this.searchResult = searchResult;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
