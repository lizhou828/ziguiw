package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.HealthArchivesServiceImpl;
import com.zigui.service.impl.HealthHistoryServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class HealthArchivesAction extends BaseAction {
	
	private static final long serialVersionUID = 669963698029364302L;
	
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	@Autowired
	private HealthArchivesServiceImpl healthArchivesService;
	
	@Autowired
	private HealthHistoryServiceImpl healthHistoryService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private HealthArchives healthArchives;
	private HealthHistory healthHistory;
	private Map<String, String> query;
	
	private BaseHealth baseHealth;
	private Page<HealthArchives> healthArchivess;
	
	private Page<HealthHistory> healthHistorys;

	private boolean isAsc = false;
	private long healthArchivesId;
	private int queryType;
	private Long[] opIds;
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	private String orderField = "createTime";
	
	private String userId;
	
	private String sid;
	
	private Student student;
	
	private List<Student> students;
	
	private String type = "1";
	
	public String add(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		if(log.isDebugEnabled()){
			log.debug("ub.getType():" + ub.getType());
			log.debug("ub.getForeignKey():" + ub.getForeignKey());
			log.debug("Constant.PARENT_TYPE:" + Constant.PARENT_TYPE);
			log.debug("ub.getType() == Constant.PARENT_TYPE:" + (ub.getType() == Constant.PARENT_TYPE));
			log.debug("ub.getForeignKey() != 0L:" + (ub.getForeignKey() != 0L));
		}
		
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		if(log.isDebugEnabled()){
			log.debug("sid:" + sid);
		}
		
		Page<Object> pageObj = commonService.getByHql("from HealthArchives where userId = ? order by createTime desc", 1, 1, new Object[]{new Long(sid)});
		if(pageObj!=null&&pageObj.getList()!=null&&pageObj.getList().size()>0){
			healthArchives = (HealthArchives)pageObj.getList().get(0);
			
			healthArchives.setId(0L);
		}
		return Action.SUCCESS;
	}
	
	public String saveOrUpdate(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		healthArchives.setUserId(new Long(sid));
		healthArchives.setCreatorId(user.getId());
		healthArchives.setCreatorNick(user.getNickName());
		healthArchivesService.saveOrUpdate(healthArchives);
		
			
		userId = "" + ub.getId();
		
		return Action.SUCCESS;
	}
	
	public String listByCondition(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
		}
		
		query.put("opIds", sid);
		
		healthArchivess = healthArchivesService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc);
		
		
		Page<Object> pageObj = commonService.getByHql("from BaseHealth where userId = ? order by createTime desc", 1, 1, new Object[]{new Long(sid)});
		if(pageObj!=null&&pageObj.getList()!=null&&pageObj.getList().size()>0){
			baseHealth = (BaseHealth)pageObj.getList().get(0);
			
		}
		
		return Action.SUCCESS;
	}

	public String listHistoryByCondition(){
		healthHistorys = healthHistoryService.listByCondition(query, new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), orderField, isAsc);		
		return Action.SUCCESS;
	}
	
	public String getById(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		if(log.isDebugEnabled()){
			log.debug("sid:" + sid);
		}
		
		HealthArchives tmp = null;
		Page<Object> pageObj = commonService.getByHql("from HealthArchives where userId = ? order by createTime desc", 1, 1, new Object[]{new Long(sid)});
		if(pageObj!=null&&pageObj.getList()!=null&&pageObj.getList().size()>0){
			tmp = healthArchives = (HealthArchives)pageObj.getList().get(0);
			
		}
		
		healthArchives = commonService.get(HealthArchives.class, healthArchives.getId());
		
		if(tmp != null){
			healthArchives.setBloodType(tmp.getBloodType());
			healthArchives.setDrugAllergyHistory(tmp.getDrugAllergyHistory());
			healthArchives.setGeneticHistory(tmp.getGeneticHistory());
			healthArchives.setLocalHistory(tmp.getLocalHistory());
		}
		
		return Action.SUCCESS;
	}
	
	public String getBaseById(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		if(log.isDebugEnabled()){
			log.debug("sid:" + sid);
		}
		
//		BaseHealth tmp = null;
		Page<Object> pageObj = commonService.getByHql("from BaseHealth where userId = ? order by createTime desc", 1, 1, new Object[]{new Long(sid)});
		if(pageObj!=null&&pageObj.getList()!=null&&pageObj.getList().size()>0){
			baseHealth = (BaseHealth)pageObj.getList().get(0);
			
		}
		
		if(log.isDebugEnabled()){
			log.debug("baseHealth:" + baseHealth);
		}
		
//		baseHealth = commonService.get(BaseHealth.class, tmp.getId());
		
		return Action.SUCCESS;
	}
	
	public String saveOrUpdateBase(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		
		sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
		}
		
		baseHealth.setUserId(new Long(sid));
		
		
		baseHealth.setCreatorId(ub.getId());
		baseHealth.setCreatorNick(ub.getNickName());
		commonService.saveOrUpdate(baseHealth);
			
		userId = "" + ub.getId();
		
		return Action.SUCCESS;
	}
	
	public HealthArchives getHealthArchives() {
		return healthArchives;
	}

	public void setHealthArchives(HealthArchives healthArchives) {
		this.healthArchives = healthArchives;
	}

	public HealthHistory getHealthHistory() {
		return healthHistory;
	}

	public void setHealthHistory(HealthHistory healthHistory) {
		this.healthHistory = healthHistory;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getHealthArchivesId() {
		return healthArchivesId;
	}

	public void setHealthArchivesId(long healthArchivesId) {
		this.healthArchivesId = healthArchivesId;
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

	public Page<HealthArchives> getHealthArchivess() {
		return healthArchivess;
	}

	public void setHealthArchivess(Page<HealthArchives> healthArchivess) {
		this.healthArchivess = healthArchivess;
	}

	public Page<HealthHistory> getHealthHistorys() {
		return healthHistorys;
	}

	public void setHealthHistorys(Page<HealthHistory> healthHistorys) {
		this.healthHistorys = healthHistorys;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BaseHealth getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(BaseHealth baseHealth) {
		this.baseHealth = baseHealth;
	}
	

}
