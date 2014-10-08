package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.GrowFootmark;
import com.zigui.domain.Parent;
import com.zigui.domain.Student;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.GrowFootmarkServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Map;
@Entity
@Table(name="grow_footmark")
public class GrowFootmarkAction extends BaseAction {
	
	private static final long serialVersionUID = 669964698029364302L;
	
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	@Autowired
	private GrowFootmarkServiceImpl growFootmarkService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private GrowFootmark growFootmark;
	
	private Map<String, String> query;
	
	private Page<GrowFootmark> growFootmarks;


	private boolean isAsc = false;
	private long growFootmarkId;
	private int queryType;
	private Long[] opIds;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	private String userId;
	
	public String saveOrUpdate(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		String sid = "" + ub.getId();
		
		/**
		 * 家长用户，需要首先拿到学生用户的uin再进行保存
		 */
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		/**
		 * 如果是学生类型的话，这里直接使用session中的用户做为userId即可
		 */
		if(ub.getType() != null && ub.getType().intValue() == Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
		}
		
		growFootmark.setUserId(new Long(sid));
		growFootmark.setFirstImage(getFirstImage(growFootmark.getText()));
		growFootmark.setDescription(StringUtils.substring(getPureText(growFootmark.getText()), 0, 110));
		growFootmark.setCreateTime(growFootmark.getCreateTime()==null?new Date():growFootmark.getCreateTime());
		
		growFootmark.setCreatorId(user.getId());
		growFootmark.setCreatorNick(user.getNickName());
		growFootmarkService.saveOrUpdate(growFootmark);	
		userId = new Long(growFootmark.getUserId()).toString();
		return Action.SUCCESS;
	}
	
	public String listByCondition(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		String sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
			Student student = commonService.get(Student.class, (int)ub.getForeignKey().longValue());
			
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		query.put("opIds", sid);
		
		growFootmarks = growFootmarkService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc);		
		return Action.SUCCESS;
	}
	
	public String getById(){		
		growFootmark = growFootmarkService.getById(growFootmarkId);
		return Action.SUCCESS;
	}

	/**
	 * 删除成长足迹
	 * @return
	 */
	public String fakeDelete(){
		growFootmarkService.delete(opIds);
		return Action.SUCCESS;
	}

	public GrowFootmark getGrowFootmark() {
		return growFootmark;
	}

	public void setGrowFootmark(GrowFootmark growFootmark) {
		this.growFootmark = growFootmark;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Page<GrowFootmark> getGrowFootmarks() {
		return growFootmarks;
	}

	public void setGrowFootmarks(Page<GrowFootmark> growFootmarks) {
		this.growFootmarks = growFootmarks;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getGrowFootmarkId() {
		return growFootmarkId;
	}

	public void setGrowFootmarkId(long growFootmarkId) {
		this.growFootmarkId = growFootmarkId;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
