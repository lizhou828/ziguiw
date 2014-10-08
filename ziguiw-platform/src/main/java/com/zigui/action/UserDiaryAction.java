package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.ContentNotify;
import com.zigui.domain.PointsHistory;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserDiary;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.UserDiaryServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.Constant;
import com.zigui.utils.HtmlUtils;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日志相关action
 */
public class UserDiaryAction extends BaseAction {

	
	private static final long serialVersionUID = -636171062140227205L;

	private UserDiary diary;
	
	private List<UserDiary> diaries;
	
	private Page<UserDiary> pagedDiaries;
	
	private Map<String, String> query;

	private String orderField = "createTime";

	private String orderType = "desc";

	private String countPerPage = "10";

	private String currentPage = "1";
	
	private Page<UserDiary> userDiaries;

	private long userDiaryId;
	
	private Long[] opIds;
	
	private long recommendRegionId;
	
	@Autowired
	private UserDiaryServiceImpl userDiaryService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private transient CommonServiceImpl commonService;

	private int pageSize = 10;
	//private int pageNum = 1;
	private int status;
	private int userId;
	
	private int type;
	
	private long hostUserId;
	
	private String hostUserName;

    private String pageString;
	
	private String encoderdHostUserName;
	
	/**
	 * 发表或者修改日志
	 * 
	 * @return
	 */
	public String save() {
		ActionContext ctx = ActionContext.getContext();
        Map session = ctx.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        
        if(user == null || user.getId() < 1){
        	return "USER_NO_LOGIN";
        }
        
        UserBase tmpUser = new UserBase();
        tmpUser.setId(user.getId());
		
        diary.setUser(tmpUser);
		diary.setCreateTime(new Date());
		String pureText = HtmlUtils.getPureText(diary.getText());
		diary.setDescription(StringUtils.substring(pureText, 0, 40));
		diary.setViewCount(0);
		
		// 完善资料增加积分的时候，注意只能增加一次，请勿重复增加，所以用type判断
		if (diary.getId() < 1) {
			PointsHistory ph = new PointsHistory();
			ph.setUser(user);
			ph.setFlag(1);
			ph.setType(Constant.DIARY_PUBLISH);

			ph.setPointsChange(Constant.DIARY_PUBLISH_POINT);
			userService.changePoints(ph);
		}
		
		diary.setFirstImage(HtmlUtils.getFirstImage(diary.getText()));
		
		type = diary.getType();
		
		userDiaryService.saveOrUpdate(diary);
		
		//内容提醒，注册提醒
		ContentNotify contentNotify = new ContentNotify();
		contentNotify.setType(Constant.DIARY_NOTIFY);
		contentNotify.setContentId(diary.getId());
		
		commonService.save(contentNotify);
		return Action.SUCCESS;
	}
	
	public void validateSave(){
		if(StringUtils.isEmpty(diary.getTitle())){
			this.addFieldError("diary.title", "标题不能为空");
			return;
		}
		if(StringUtils.isEmpty(diary.getText())){
			this.addFieldError("diary.text", "内容不能为空");
			return;
		}
	}
	
	
	public String getDiaryByStatus() {
		diaries = userDiaryService.getDiaryByStatus(pageSize,Integer.valueOf(currentPage),status);
		return Action.SUCCESS;
	}
	
	public String getHotDiary() {
		diaries = userDiaryService.getHotDiary(pageSize,Integer.valueOf(currentPage));
		return Action.SUCCESS;
	}
	
	public String getDiaryByUserNickName() {
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		hostUserId = userService.getUserBaseByNickName(hostUserName).getId();
		
		pagedDiaries = userDiaryService.getDiaryByUserId(pageSize,Integer.valueOf(currentPage),hostUserId);
		return Action.SUCCESS;
	}
	
	public String getSchoolDiaryByUserNickName() {
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		hostUserId = userService.getUserBaseByNickName(hostUserName).getId();
        pagedDiaries = userDiaryService.getDiaryByUserIdAndType(pageSize,Integer.valueOf(currentPage), type, hostUserId);
        pageString = pagedDiaries.getPageString(ServletActionContext.getRequest().getQueryString());
        return Action.SUCCESS;

	}

    public String getSchoolNews(){
        pagedDiaries = userDiaryService.getSchoolNews(pageSize,type);
        return Action.SUCCESS;
    }
	
	public String getDiaryById(){
		diary = userDiaryService.getById(diary.getId());
		
		return Action.SUCCESS;
	}
	
	public String listByCondition() {
		boolean isAsc = false;
		if (orderType !=null && orderType.equals("ASC")) {
			isAsc = true;
		}
		userDiaries = userDiaryService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc,this.queryString);

		return Action.SUCCESS;
	}
	
	public String delDiaryById(){
		if(StringUtils.isEmpty(hostUserName)){
			ActionContext ctx = ActionContext.getContext();
	        Map session = ctx.getSession();
	        UserBase user = (UserBase)session.get("VALID_USER");
	        
	        hostUserName = user.getNickName();
		}
		
		userDiaryService.delete(new Long[]{diary.getId()});
		
		return Action.SUCCESS;
	}

	public String delete(){
		//opIds = new Long[]{knowledgeId};
		userDiaryService.delete(opIds);
		
		return Action.SUCCESS;
	}
	
	public String recommend(){
//		for(long id : opIds){
//			UserDiary tmpUserDiary = userDiaryService.getById(id);
//			
//			KnowledgeRecommendRegion region = new KnowledgeRecommendRegion();
//			region.setId(Constant.USER_DIARY_RECOMMEND_REGION_ID);
//			tmpUserDiary.setKnowledgeRecommendRegion(region);
//			
//			userDiaryService.saveOrUpdate(tmpUserDiary);
//		}
		userDiaryService.recommend(opIds, recommendRegionId);
		return Action.SUCCESS;
	}
	
	public void setDiary(UserDiary diary) {
		this.diary = diary;
	}

	public UserDiary getDiary() {
		return diary;
	}

	public void setDiaries(List<UserDiary> diaries) {
		this.diaries = diaries;
	}

	public List<UserDiary> getDiaries() {
		return diaries;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public long getHostUserId() {
		return hostUserId;
	}

	public void setHostUserId(long hostUserId) {
		this.hostUserId = hostUserId;
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

	public UserDiaryServiceImpl getUserDiaryService() {
		return userDiaryService;
	}

	public void setUserDiaryService(UserDiaryServiceImpl userDiaryService) {
		this.userDiaryService = userDiaryService;
	}

	public Page<UserDiary> getUserDiaries() {
		return userDiaries;
	}

	public void setUserDiaries(Page<UserDiary> userDiaries) {
		this.userDiaries = userDiaries;
	}

	public long getUserDiaryId() {
		return userDiaryId;
	}

	public void setUserDiaryId(long userDiaryId) {
		this.userDiaryId = userDiaryId;
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

	public Page<UserDiary> getPagedDiaries() {
		return pagedDiaries;
	}

	public void setPagedDiaries(Page<UserDiary> pagedDiaries) {
		this.pagedDiaries = pagedDiaries;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }
}
