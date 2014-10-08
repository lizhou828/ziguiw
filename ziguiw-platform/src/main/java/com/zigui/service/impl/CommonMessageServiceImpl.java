/**
 * 
 */
package com.zigui.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.zigui.dao.CommonDao;
import com.zigui.dao.GenericDao;
import com.zigui.domain.CommonMessage;
import com.zigui.domain.TeacherClasz;
import com.zigui.domain.UserBase;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;

/**
 * @author Administrator
 *
 */
public class CommonMessageServiceImpl {

	@Autowired
	private GenericDao<CommonMessage> commonMessageDao;
	
	@Autowired
	private CommonDao commonDao;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");	
	
	private static Log log = LogFactory.getLog(CommonMessageServiceImpl.class); 
	
	public Page<CommonMessage> listByCondition(Map<String, String> query, int pageNo, int pageSize, String orderBy, boolean isAsc){
		if(query == null){
			query = new HashMap<String, String>();
		}
		log.debug("query :"+query.toString());
		List<Criterion> criterionsList = new ArrayList<Criterion>(query.size());
		
		if(query.get("kind") != null && NumberUtils.toInt((String)query.get("kind")) != 0){
			criterionsList.add(Restrictions.eq("kind", NumberUtils.toInt((String)query.get("kind"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("startTime"))){
			try {
				criterionsList.add(Restrictions.ge("createTime", format.parse((String)query.get("startTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(StringUtils.isNotEmpty((String)query.get("endTime"))){
			try {
				criterionsList.add(Restrictions.le("createTime", format.parse((String)query.get("endTime"))));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(query.get("state") != null && NumberUtils.toInt((String)query.get("state")) != 0){
			criterionsList.add(Restrictions.eq("state", NumberUtils.toInt((String)query.get("state"))));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("title"))){
			criterionsList.add(Restrictions.like("title", "%" + query.get("title") + "%"));
		}
		
		if(StringUtils.isNotEmpty((String)query.get("creatorNick"))){
			criterionsList.add(Restrictions.eq("creatorNick", (String)query.get("creatorNick")));
		}
		
		if(StringUtils.equals(query.get("kind"), "3") || StringUtils.equals(query.get("kind"), "7")){
			if(StringUtils.isNotEmpty((String)query.get("opIds"))&&(((String)query.get("opIds")).split(";")).length>0){
				System.out.println("opIds:" + (String)query.get("opIds"));
				
				Criterion noParentCondition = Restrictions.isNull("parentMsg");
				
				Criterion toUserCondition = Restrictions.in("toUser.id", CommonUtils.strsToLongs(((String)query.get("opIds")).split(";")));
				toUserCondition = Restrictions.and(toUserCondition, noParentCondition);
				
				Criterion fromUserCondition = Restrictions.in("formUser.id", CommonUtils.strsToLongs(((String)query.get("opIds")).split(";")));
				Criterion replyCondition = Restrictions.isNotEmpty("childMsgs");
				fromUserCondition = Restrictions.and(fromUserCondition, replyCondition);
				fromUserCondition = Restrictions.and(fromUserCondition, noParentCondition);
				
				Criterion fromAndTo = Restrictions.or(fromUserCondition, toUserCondition);
				criterionsList.add(fromAndTo);
			}
		}else if(StringUtils.equals(query.get("kind"), "4")){
			criterionsList.add(Restrictions.eq("classId", (String)query.get("opIds")));
		}
		
		
		Criterion[] criterions = new Criterion[criterionsList.size()];
		criterionsList.toArray(criterions);
		
		return commonMessageDao.pagedQuery(CommonMessage.class, pageNo, pageSize, orderBy, isAsc, criterions);
	}
	
	public long saveOrUpdate(CommonMessage commonMessage){
		commonMessageDao.saveOrUpdate(commonMessage);	
		return commonMessage.getId();
	}
	
	public CommonMessage getById(long id){
		return commonMessageDao.get(CommonMessage.class, id);
	}
	
	public void fakeDelete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<CommonMessage> page = commonMessageDao.pagedQuery(CommonMessage.class, 1, ids.length,
				inCodition);

		for (CommonMessage commonMessage : page.getList()) {
			commonMessage.setState(-1);
			commonMessageDao.saveOrUpdate(commonMessage);
		}
	}
	
	public void delete(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<CommonMessage> page = commonMessageDao.pagedQuery(CommonMessage.class, 1, ids.length,
				inCodition);

		for (CommonMessage commonMessage : page.getList()) {
			if(commonMessage != null){
				commonMessageDao.remove(commonMessage);
			}
		}
	}
	
	public Page<CommonMessage> getClassNoticeByTeacher(UserBase ub, int pageNo, int pageSize){
		
		Page<Object> temp1 = CommonUtils.getClaszByTeacher(ub);
		Page<TeacherClasz> temp2 = CommonUtils.transform(TeacherClasz.class, temp1);
		
		List<String> classIds = new ArrayList<String>();
		for(TeacherClasz tmp : temp2.getList()){
			classIds.add("" + tmp.getClasz().getBj_id());
		}
		
		return commonDao.getClassNoticeByTeacher(pageNo, pageSize, classIds);
		
	}

}
