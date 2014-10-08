package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Knowledge;
import com.zigui.service.impl.KnowledgeServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class KnowledgeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6637467885049495893L;

	private Map<String, String> query;

	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";

	private Knowledge knowledge;

	private Page<Knowledge> pagedKnowledges;

	private long knowledgeId;
	
	private Long[] opIds;
	
	private int opType = 0;
	
	private int approveFlag = 1;
	
	private long recommendRegionId;
	
	
	@Autowired
	private KnowledgeServiceImpl knowledgeService;
	
	public void validateSaveOrUpdate(){
		/*为空判断*/
		if(StringUtils.isEmpty(knowledge.getTitle())){
			this.addFieldError("knowledge.title", "知识标题不能为空");
			return;
		}
		if(StringUtils.isEmpty(knowledge.getContent())){
			this.addFieldError("knowledge.content", "知识内容不能为空");
			return;
		}
		
		if(knowledge.getKnowledgeChannle() == null || knowledge.getKnowledgeChannle().getId() < 1L){
			this.addFieldError("knowledge.channle", "频道不能为空");
			return;
		}
		/*过长判断*/
		if(knowledge.getDescription().length()>400){//200字
			this.addFieldError("knowledge.description", "知识描述不能超过200字");
			return;			
		}
		
		if(knowledge.getFirstImage().length()>128){//200字
			this.addFieldError("knowledge.firstImage", "知识图片不能超过128字");
			return;			
		}
		
		if(knowledge.getTitle().length()>100){//200字
			this.addFieldError("knowledge.title", "知识标题不能超过50字");
			return;			
		}
		
		if(knowledge.getSubtitle().length()>100){//200字
			this.addFieldError("knowledge.subtitle", "知识副标题不能超过50字");
			return;			
		}
		
		
	}
	
	public String saveOrUpdate() {
		if(new Long(knowledge.getId())!=null&&knowledge.getId()>0){
			knowledge.setLastModifyId(admin.getId());
			knowledge.setLastModifyNick(admin.getNickName());
		}else{
			knowledge.setCreatorId(admin.getId());
			knowledge.setCreatorNick(admin.getNickName());
		}
	
		knowledge.setFirstImage(getFirstImage(knowledge.getContent()));
		knowledge.setAutoDescription(StringUtils.substring(getPureText(knowledge.getContent()), 0, 110));
		knowledgeService.saveOrUpdate(knowledge);
		return Action.SUCCESS;
	}

	public String listKnowledgesByCondition() {
		boolean isAsc = false;
		if (orderType.equals("ASC")) {
			isAsc = true;
		}
		pagedKnowledges = knowledgeService.getKnowledgesByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);

		return Action.SUCCESS;
	}

	public String getById() {
		knowledge = knowledgeService.getKnowledgeById(knowledgeId);
		return Action.SUCCESS;
	}
	

	public String fakeDelete(){
		knowledgeService.fakeDeleteKnowledges(opIds);
		
		return Action.SUCCESS;
	}
	
	public String approve(){
		knowledgeService.approveKnowledges(opIds, approveFlag);
		
		return Action.SUCCESS;
	}
	
	public String restore(){
		knowledgeService.restore(opIds);
		
		return Action.SUCCESS;
	}
	
	public String recommend(){
		knowledgeService.recommend(opIds, recommendRegionId);
		
		return Action.SUCCESS;
	}

	public String delete(){
		//opIds = new Long[]{knowledgeId};
		knowledgeService.deleteKnowledges(opIds);
		
		return Action.SUCCESS;
	}
	
	public String batchDelete(){
		knowledgeService.deleteKnowledges(opIds);
		
		return Action.SUCCESS;
	}
	
	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public Page<Knowledge> getPagedKnowledges() {
		return pagedKnowledges;
	}

	public void setPagedKnowledges(Page<Knowledge> pagedKnowledges) {
		this.pagedKnowledges = pagedKnowledges;
	}

	public long getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public int getOpType() {
		return opType;
	}

	public void setOpType(int opType) {
		this.opType = opType;
	}

	public int getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(int approveFlag) {
		this.approveFlag = approveFlag;
	}

	public long getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(long recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}	
	
private String getFirstImage(String html){
		
		String firstImage = null;
		
		Parser parser = Parser.createParser(html, "UTF-8");
		NodeFilter filter = new NodeClassFilter(ImageTag.class);

        try {
			NodeList images = parser.extractAllNodesThatMatch(filter);
			
			for (Node node : images.toNodeArray()) {
		           ImageTag imageTag = (ImageTag)node;
		           firstImage = imageTag.getImageURL();
		           
		           break;
	        }
		} catch (ParserException e) {
//			log.error(e.getMessage(), e);
		}

        return firstImage;	
	}

private String getPureText(String html){
    StringBuffer sb = new StringBuffer();  
    try  
    {  
    	Parser parser = Parser.createParser(html, "UTF-8");
    	NodeIterator its  = parser.elements();
         
        while(its.hasMoreNodes())  
        {  
           Node node = (Node) its.nextNode();
           sb.append(node.toPlainTextString());  
        }  
        return sb.toString();  
    }catch(Exception ex)  
    {  
         ex.printStackTrace();  
         return html;  
    }  
}
}
