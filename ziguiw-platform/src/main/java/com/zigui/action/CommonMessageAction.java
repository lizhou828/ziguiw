package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.CommonMessage;
import com.zigui.domain.Parent;
import com.zigui.domain.Student;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.CommonMessageServiceImpl;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 通用消息类
 *
 */
public class CommonMessageAction extends BaseAction {

	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	private static final long serialVersionUID = 2838500431499796719L;

	@Autowired
	private CommonMessageServiceImpl commonMessageService;

	@Autowired
	private CommonServiceImpl commonService;

	@Autowired
	private UserServiceImpl userService;

	private CommonMessage commonMessage;

	private Map<String, String> query;

	private Page<CommonMessage> commonMessages;

	private boolean isAsc = false;
	private long commonMessageId;
	private int queryType;
	private Long[] opIds;

	private String countPerPage = "10";

	private String currentPage = "1";

	private String orderField = "createTime";

	private String kind;

	private String userId;

	private String sids;

	private String[] sidArray;

	/**
	 * 保存消息，教师留言，教师评语，班级通知均使用该方法
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		if (log.isDebugEnabled()) {
			log.debug("CommonMessageAction|saveOrUpdate|sidArray:"
					+ Arrays.toString(sidArray));
			log.debug("CommonMessageAction|saveOrUpdate|sids:" + sids);
		}

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		UserBase ub = (UserBase) session.get("VALID_USER");

		CommonMessage message = null;

		//分支1
		if (commonMessage.getKind() == Constant.TEACHER_LEAVING_MESSAGE_TYPE
				|| commonMessage.getKind() == Constant.TEACHER_COMMENT_MESSAGE_TYPE) {
			
			// 没有用户ID输入，此时有可能是回复消息
			if (ArrayUtils.isEmpty(sidArray)) {
				message = new CommonMessage();
				message.setFromUser(ub);
				message.setText(commonMessage.getText());
				message.setTitle(commonMessage.getTitle());
				message.setKind(commonMessage.getKind());
				if (commonMessage.getParentMsg() != null) {
					System.out.println("start to get parent");

					CommonMessage temp = commonMessageService
							.getById(commonMessage.getParentMsg().getId());
					message.setParentMsg(temp);
					message.setToUser(temp.getFromUser());

					System.out.println("get parent: " + message.getParentMsg());
					commonMessageService.saveOrUpdate(message);

					// 修改父消息的最后更新时间
					temp.setLastModifyTime(new Date());
					commonMessageService.saveOrUpdate(temp);
				}
			}
			// 发送消息
			else {
				for (int i = 0; i < sidArray.length; i++) {
					message = new CommonMessage();
					message.setFromUser(ub);
					message.setText(commonMessage.getText());
					message.setTitle(commonMessage.getTitle());
					message.setKind(commonMessage.getKind());
					if (commonMessage.getKind() == Constant.TEACHER_LEAVING_MESSAGE_TYPE
							&& commonMessage.getParentMsg() != null) {
						System.out.println("start to get parent");

						message.setParentMsg(commonMessageService
								.getById(commonMessage.getParentMsg().getId()));
					}
					System.out.println("get parent: " + message.getParentMsg());
					message.setToUser(userService.getUserBaseById(new Long(
							sidArray[i]).longValue()));
					commonMessageService.saveOrUpdate(message);
				}
			}
		} else if (commonMessage.getKind() == Constant.CLASS_LEAVING_MESSAGE_TYPE) {
			message = new CommonMessage();
			message.setFromUser(ub);
			message.setText(commonMessage.getText());
			message.setTitle(commonMessage.getTitle());
			message.setKind(commonMessage.getKind());
			message.setClassId(commonMessage.getClassId());

			if (commonMessage.getKind() == Constant.TEACHER_LEAVING_MESSAGE_TYPE
					&& commonMessage.getParentMsg() != null) {
				System.out.println("start to get parent");

				message.setParentMsg(commonMessageService.getById(commonMessage
						.getParentMsg().getId()));
			}
			System.out.println("get parent: " + message.getParentMsg());

			commonMessageService.saveOrUpdate(message);
		}
		userId = sids;
		kind = new Integer(commonMessage.getKind()).toString();

		return Action.SUCCESS;
	}

	public String listByCondition() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();

		UserBase ub = (UserBase) session.get("VALID_USER");
		String sid = "" + ub.getId();

		String bjId = "";

		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.PARENT_TYPE
				&& ub.getForeignKey() != 0L) {
			Parent parent = commonService.get(Parent.class, (int) ub
					.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent
					.getStudent().getXs_id());

			bjId = student.getBj_ID();

			if (log.isDebugEnabled()) {
				log.debug("student:" + student);
			}

			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());

			query.put("opIds", sid);

			if (StringUtils.equals(query.get("kind"), "4")) {
				query.put("opIds", bjId);
			}

			if (StringUtils.equals(query.get("kind"), "3")) {
				query.put("opIds", sid);
				// commonMessages = commonMessageService.listByCondition(query,
				// new Integer(currentPage).intValue(),
				// new Integer(countPerPage).intValue(), orderField, isAsc);

				Page<Object> tmp = commonService
						.getByHql(
								"from CommonMessage where (formUser = ? or toUser = ?) and kind = 3  and state = 1 and parentMsg is null order by lastModifyTime desc",
								new Integer(currentPage).intValue(),
								new Integer(countPerPage).intValue(),
								new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			} else if (StringUtils.equals(query.get("kind"), "7")) {
				query.put("opIds", sid);
				// commonMessages = commonMessageService.listByCondition(query,
				// new Integer(currentPage).intValue(),
				// new Integer(countPerPage).intValue(), orderField, isAsc);

				Page<Object> tmp = commonService
						.getByHql(
								"from CommonMessage where (formUser = ? or toUser = ?) and kind = 7  and state = 1 and parentMsg is null order by lastModifyTime desc",
								new Integer(currentPage).intValue(),
								new Integer(countPerPage).intValue(),
								new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			}else {
				commonMessages = commonMessageService.listByCondition(query,
						new Integer(currentPage).intValue(), new Integer(
								countPerPage).intValue(), orderField, isAsc);
			}
		}

		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.STUDENT_TYPE
				&& ub.getForeignKey() != 0L) {
			Student student = commonService.get(Student.class, (int) ub
					.getForeignKey().longValue());

			bjId = student.getBj_ID();

			if (log.isDebugEnabled()) {
				log.debug("student:" + student);
			}

			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());

			query.put("opIds", sid);

			if (StringUtils.equals(query.get("kind"), "4")) {
				query.put("opIds", bjId);
			}

			//老师留言分支
			if (StringUtils.equals(query.get("kind"), "3")) {
				query.put("opIds", sid);
				
				Page<Object> tmp = commonService
						.getByHql(
								"from CommonMessage where (formUser = ? or toUser = ?) and kind = 3  and state = 1 and parentMsg is null order by lastModifyTime desc",
								new Integer(currentPage).intValue(),
								new Integer(countPerPage).intValue(),
								new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			}else if (StringUtils.equals(query.get("kind"), "7")) {
				query.put("opIds", sid);
				
				Page<Object> tmp = commonService
						.getByHql(
								"from CommonMessage where (formUser = ? or toUser = ?) and kind = 7  and state = 1 and parentMsg is null order by lastModifyTime desc",
								new Integer(currentPage).intValue(),
								new Integer(countPerPage).intValue(),
								new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			} else { //非老师留言使用通用的消息查询接口
				commonMessages = commonMessageService.listByCondition(query,
						new Integer(currentPage).intValue(), new Integer(
								countPerPage).intValue(), orderField, isAsc);
			}
		}

		if (ub.getType() != null
				&& ub.getType().intValue() == Constant.TEACHER_TYPE
				&& ub.getForeignKey() != 0L) {

			if (StringUtils.equals(query.get("kind"), "4")) {
				commonMessages = commonMessageService.getClassNoticeByTeacher(
						ub, new Integer(currentPage).intValue(), new Integer(
								countPerPage).intValue());
			} else if (StringUtils.equals(query.get("kind"), "3")) {
				query.put("opIds", sid);

				Page<Object> tmp = commonService
						.getByHql(
								"from CommonMessage where (formUser = ? or toUser = ?) and kind = 3  and state = 1 and parentMsg is null order by lastModifyTime desc",
								new Integer(currentPage).intValue(),
								new Integer(countPerPage).intValue(),
								new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			} else if (StringUtils.equals(query.get("kind"), "7")) {
				Page<Object> tmp = commonService
				.getByHql(
						"from CommonMessage where (formUser = ? or toUser = ?) and kind = 7  and state = 1 and parentMsg is null order by lastModifyTime desc",
						new Integer(currentPage).intValue(),
						new Integer(countPerPage).intValue(),
						new Object[] { ub, ub });

				commonMessages = CommonUtils
						.transform(CommonMessage.class, tmp);
			}
		}

		return Action.SUCCESS;
	}

	public String getById() {
		commonMessage = commonMessageService.getById(commonMessageId);
		return Action.SUCCESS;
	}

	public String fakeDelete() {
		commonMessageService.delete(opIds);
		return Action.SUCCESS;
	}

	public CommonMessage getCommonMessage() {
		return commonMessage;
	}

	public void setCommonMessage(CommonMessage commonMessage) {
		this.commonMessage = commonMessage;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Page<CommonMessage> getCommonMessages() {
		return commonMessages;
	}

	public void setCommonMessages(Page<CommonMessage> commonMessages) {
		this.commonMessages = commonMessages;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getCommonMessageId() {
		return commonMessageId;
	}

	public void setCommonMessageId(long commonMessageId) {
		this.commonMessageId = commonMessageId;
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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSids() {
		return sids;
	}

	public void setSids(String sids) {
		this.sids = sids;
	}

	public String[] getSidArray() {
		return sidArray;
	}

	public void setSidArray(String[] sidArray) {
		this.sidArray = sidArray;
	}

}
