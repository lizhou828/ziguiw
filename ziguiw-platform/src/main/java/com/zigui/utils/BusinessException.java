package com.zigui.utils;
/**
 * Description:校园数字化信息管理系统业务处理异常，用于在页面展示服务器端内部处理异常信息
 * @version 1.0
 * @author 湖南爱瑞杰科技发展股份有限公司技术部
 * History:         // 历史修改记录
 * <author>  <time>   <version >   <desc>
 *
 */
public class BusinessException extends RuntimeException{

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message) {
		super(createFriendlyErrMsg(message));
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static String createFriendlyErrMsg(String message){
		String prefixStr = "抱歉，";
		String suffixStr = " 请稍后再试或与管理员联系！";
		StringBuffer friendlyErrMsg = new StringBuffer("");
		friendlyErrMsg.append(prefixStr);
		friendlyErrMsg.append(message);
		friendlyErrMsg.append(suffixStr);
		return friendlyErrMsg.toString();
	}
}
