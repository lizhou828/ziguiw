package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Forum;
import com.zigui.service.impl.ForumServiceImpl;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 论坛帖子
 */
public class ForumAction extends BaseAction {

	
	private static final long serialVersionUID = 669963698029314302L;
	
	private Forum forum;
	
	private Page<Forum> forums;
	
	private Map<String, String> query;
	
	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";
	
	private boolean isAsc = false;
	
	private long forumId;
	
	private int queryType;
	
	private Long[] opIds;
	
	private Long[] boardId;
	
	private String operateType;
	
	private long recommendRegionId;
	
	private long id;
	
	@Autowired
	private ForumServiceImpl forumService;
	
	public String forumList(){
		if(boardId[0]!=1){
			return "forumlist";
		}else{
			return "lovelist";
		}
	}
	
	public String listByCondition(){
		boolean isAsc = false;
		if (orderType.equals("ASC")) {
			isAsc = true;
		}
		forums = forumService.listByCondition(query, new Integer(currentPage).intValue(), new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);		
		return Action.SUCCESS;
	}
	
	public String list(){
		Date ct = new Date();
		Date qt = new Date(ct.getTime()-1*24*60*60*1000);
		
		query.put("startTime", qt.toLocaleString().split(" ")[0]);
		query.put("endTime", ct.toLocaleString().split(" ")[0]);
		return listByCondition();		
	}
	
	/**
	 * 发帖校验
	 */
	public void validateSaveOrUpdate(){
		if(user == null || user.getId() == 0L){
			this.addFieldError("user", "您好，必须要登录之后才可以发帖");
			return;
		}
			if(forum.getIsnew()==1&& StringUtils.isEmpty(forum.getTitle())){
				this.addFieldError("forum.title", "话题标题不能为空");
				return;
			}	
			
			if(forum.getIsnew()==1&&forum.getTitle().length()>150){
				this.addFieldError("forum.title", "帖子标题太长");
				return;
			}
				
		if(StringUtils.isEmpty(forum.getContent())){
			this.addFieldError("forum.content", "帖子内容不能为空");
			return;
		}
		
	}
	
	/**
	 * 发帖
	 * @return
	 */
	public String saveOrUpdate() {
		forum.setFirstImage(getFirstImage(forum.getContent()));
		forum.setAutoDescription(StringUtils.substring(getPureText(forum.getContent()), 0, 110));
		forum.setCreatorId(user.getId());
		forum.setCreatorNick(user.getNickName());
		forumService.saveOrUpdate(forum);
		if(forum.isnew==1){
			id = forum.getBoard().id;
			//发帖获5分
			changePoints(user.getId(), Constant.PUBLISH_FORUM, Constant.PUBLISH_FORUM_POINT);
		    return Action.SUCCESS;
		}else{
			Forum parentForum = forumService.getById(forum.getParentForum().id);
			parentForum.setLastPostId(user.getId());
			parentForum.setLastPostNick(user.getNickName());
			parentForum.setLastPostTime(new Date());
			forumService.saveOrUpdate(parentForum);
			//回帖获2分
			changePoints(user.getId(), Constant.REPLY_FORUM, Constant.REPLY_FORUM_POINT);
			forumId = forum.getParentForum().id;
			return "postforum";
		}
		
    }
	
	public String getById(){
		forum = forumService.getById(forumId);	
		return Action.SUCCESS;
	}


	
	public String fakeDelete(){
		forumService.fakeDelete(opIds);
		return Action.SUCCESS;
	}
	
	
	public String restore(){
		forumService.restore(opIds);	
		return Action.SUCCESS;
	}
	
	
	public String delete(){
		forumService.delete(opIds);	
		return Action.SUCCESS;
	}
	
	public String recommend(){
		forumService.recommend(opIds, recommendRegionId);
		
		return Action.SUCCESS;
	}
	
	public String topOrElite() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		forum = forumService.getById(forumId);
		if(operateType.equals("top")){			
			forum.setIstop(Math.abs(forum.getIstop()-1));
			forumService.saveOrUpdate(forum);
		}else if(operateType.equals("elite")){
			forum.setElite(Math.abs(forum.getElite()-1));
			forumService.saveOrUpdate(forum);
		}
		print(response,"success");
		return null;
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
	
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Page<Forum> getForums() {
		return forums;
	}
	public void setForums(Page<Forum> forums) {
		this.forums = forums;
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


	public Long[] getBoardId() {
		return boardId;
	}

	public void setBoardId(Long[] boardId) {
		this.boardId = boardId;
	}

	public boolean isAsc() {
		return isAsc;
	}
	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}
	public long getForumId() {
		return forumId;
	}
	public void setForumId(long forumId) {
		this.forumId = forumId;
	}
	public int getQueryType() {
		return queryType;
	}
	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}
	public Long[] getOpIds() {
		return opIds;
	}
	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public long getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(long recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
