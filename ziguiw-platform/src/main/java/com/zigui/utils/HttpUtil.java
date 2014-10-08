package com.zigui.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * 获取和设置HTTP信息共用类
 * 
 * @copyright 湖南爱瑞杰科技发展股份有限公司
 * @author 唐其
 * @version 1.0
 * @since 2011年8月12日
 */
public class HttpUtil {

	// 获取Attribute
	public static Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}

	// 设置Attribute
	public static void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	// 获取Parameter
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	// 获取Parameter数组
	public static String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}

	// 获取Request
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	// 获取Response
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	// 获取Application
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	// 根据key获取Session
	public static Object getSession(String key) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(key);
	}

	// 获取Session
	public static Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}
	


	// 设置session
	public static void setSession(String key, Object value) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put(key, value);
	}
	

	// AJAX输出，返回null
	public static String ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// AJAX输出文本，返回null
	public static String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	// AJAX输出HTML，返回null
	public static String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	// AJAX输出XML，返回null
	public static String ajaxXml(String xml) {
		return ajax(xml, "text/xml");
	}

	// 根据字符串输出JSON，返回null
	public static String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/html");
	}

	// 根据Map输出JSON，返回null
	public static String ajaxJson(Map<String, String> jsonMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		return ajax(jsonObject.toString(), "text/html");
	}

	/**
	 * 获取某目录在服务器的绝对路径
	 * 
	 * @param filepath
	 * @return
	 */
	public static String getRealPath(String filepath) {
		return getRequest().getSession().getServletContext().getRealPath(
				filepath);
	}

	/**
	 * 获取服务器的绝对路径
	 * 
	 * @param filepath
	 * @return
	 */
	public static String getRealPath() {
		return getRequest().getSession().getServletContext().getRealPath("/");
	}

	 public static String getWebPath(){

	       String path = getRequest().getContextPath();

	       String basePath = getRequest().getScheme() + "://"

	              + getRequest().getServerName() + ":" + getRequest().getServerPort()

	              + path + "/";

	       return basePath;

	}
}
