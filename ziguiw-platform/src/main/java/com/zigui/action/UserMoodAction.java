package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserMood;
import com.zigui.service.impl.UserMoodServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class UserMoodAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6293778069263234640L;

	private UserMood mood;
	
	private List<UserMood> moods;
	
	private int pageSize;
	
	private int pageNum = 1;
	
	private long hostUserId;
	
	private String hostUserName;
	
	private Map<String, String> query;

	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";
	
	private Page<UserMood> userMoods;

	private long userMoodId;
	
	private Long[] opIds;
	
	private long recommendRegionId;
	
	@Autowired
	private UserMoodServiceImpl userMoodService;
	
	@Autowired
	private UserServiceImpl userService;
	
	private String encoderdHostUserName;


	public String save() {
		ActionContext ctx = ActionContext.getContext();
        Map<String, Object> session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
        
        UserBase hostUser = userService.getUserBaseById(user.getId());
        
        hostUserName = hostUser.getNickName();
        
        UserBase tmpUser = new UserBase();
        tmpUser.setId(user.getId());
        
        mood.setUser(tmpUser);
		
		userMoodService.save(mood);
		return Action.SUCCESS;
	}
	
	public String getMoodList() {
		moods = userMoodService.getMoodList(pageSize, pageNum);
		return Action.SUCCESS;
	}
	
	public String getUserMoodList() {
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map<String, Object> session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		hostUserId = userService.getUserBaseByNickName(hostUserName).getId();
		
		userMoods = userMoodService.getUserMoodList(pageSize, NumberUtils.toInt(currentPage), hostUserId, queryString);
		return Action.SUCCESS;
	}
	
	public String listByCondition() {
		boolean isAsc = false;
		if (orderType !=null && orderType.equals("ASC")) {
			isAsc = true;
		}
		userMoods = userMoodService.listByCondition(query, NumberUtils.toInt(currentPage),
				new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);

		return Action.SUCCESS;
	}
	public String delete(){
		//opIds = new Long[]{knowledgeId};
		userMoodService.delete(opIds);
		
		return Action.SUCCESS;
	}
	
	public String delMood(){
		
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		userMoodService.delete(new Long[]{mood.getId()});
		
		return Action.SUCCESS;
	}
	
	public String recommend(){
//		for(long id : opIds){
//			UserMood tmpUserMood = userMoodService.getById(id);
//			KnowledgeRecommendRegion region = new KnowledgeRecommendRegion();
//			region.setId(Constant.USER_MOOD_RECOMMEND_REGION_ID);
//			tmpUserMood.setKnowledgeRecommendRegion(region);
//			
//			userMoodService.save(tmpUserMood);
//		}
		userMoodService.recommend(opIds, recommendRegionId);
		return Action.SUCCESS;
	}
	
	public UserMood getMood() {
		return mood;
	}


	public void setMood(UserMood mood) {
		this.mood = mood;
	}


	public List<UserMood> getMoods() {
		return moods;
	}


	public void setMoods(List<UserMood> moods) {
		this.moods = moods;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public long getHostUserId() {
		return hostUserId;
	}

	public void setHostUserId(long hostUserId) {
		this.hostUserId = hostUserId;
	}

	public UserMoodServiceImpl getUserMoodService() {
		return userMoodService;
	}

	public void setUserMoodService(UserMoodServiceImpl userMoodService) {
		this.userMoodService = userMoodService;
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



	public Page<UserMood> getUserMoods() {
		return userMoods;
	}

	public void setUserMoods(Page<UserMood> userMoods) {
		this.userMoods = userMoods;
	}

	public long getUserMoodId() {
		return userMoodId;
	}

	public void setUserMoodId(long userMoodId) {
		this.userMoodId = userMoodId;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

	public String getHostUserName() {
		return hostUserName;
	}

	public void setHostUserName(String hostUserName) {
		this.hostUserName = hostUserName;
	}

	public long getRecommendRegionId() {
		return recommendRegionId;
	}

	public void setRecommendRegionId(long recommendRegionId) {
		this.recommendRegionId = recommendRegionId;
	}
	
	public String getEncoderdHostUserName() {
		try {
			return URLEncoder.encode(this.getHostUserName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return this.getHostUserName();
	}

}
