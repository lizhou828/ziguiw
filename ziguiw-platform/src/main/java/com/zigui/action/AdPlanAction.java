package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Ad;
import com.zigui.domain.AdPlan;
import com.zigui.service.impl.AdPlanServiceImpl;
import com.zigui.utils.ImageUpload;
import com.zigui.utils.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 广告执行计划，
 *
 */
public class AdPlanAction extends BaseAction implements ServletContextAware {
	
	private Ad ad;
	private AdPlan adPlan;
	private Page<AdPlan> adPlans;
	private long[] opIds;
	
	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;
	
	private ServletContext context;
	
	private String orderField = "createTime";

	private String orderType = "desc";
	
	private String countPerPage = "10";

	private String currentPage = "1";
	
	@Autowired
	private AdPlanServiceImpl adPlanService;
	
	public String type = "new";
	
	public String saveOrUpdate(){
		
		if(adPlan.getId() > 0){
			AdPlan tmpAdPlan = adPlanService.getById(adPlan.getId());
			adPlan.setImageUrl(tmpAdPlan.getImageUrl());
		}
		
		if(uploadFile != null){
			// 保存文件拓展名
	    	String extName = ""; 
	    	// 保存新的文件名
			String newFileName = "";
			// 保存当前时间
			String nowTimeStr = ""; 
			
			// 获取项目根路径
			String savePath = ServletActionContext.getServletContext().getRealPath("");
			
			// 分目录存放用户图片
			savePath = savePath + "/upload/";
			// 判断目录是否存在
			File saveFilePath = new File(savePath);
			if(!saveFilePath.exists()) {
				// 不存在则创建目录
				saveFilePath.mkdir();
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8"); 
			// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
			// 获取随机数
			int rannum = (int) (new Random().nextDouble() * (99999 - 10000 + 1)) + 10000; 
			// 当前时间
			nowTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
			// 获取拓展名
			if (uploadFileFileName.lastIndexOf(".") >= 0) {
				extName = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
			}
			
			String fileName = nowTimeStr + extName;
			
			String webPath = context.getContextPath() + "/upload/" + fileName;
			
			try {
				// 检查上传的是否是图片
				if (ImageUpload.checkIsImage(extName)) {
					FileUtils.copyFile(uploadFile, new File(savePath + fileName));
				} else {
					this.addFieldError("form", "");
				}
	
			} catch (Throwable e) {
				this.addFieldError("form", "");
			}
			
			adPlan.setImageUrl(webPath);
		}
		
		adPlanService.saveOrUpdate(adPlan);
		
		return Action.SUCCESS;
	}
	
	public String getPlansByType(){
		if(StringUtils.equals(type, "old")){
			adPlans = adPlanService.getOverduedPlan(NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage));
		}else if(StringUtils.equals(type, "new")){
			adPlans = adPlanService.getPendingPlan(NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage));
		}else if(StringUtils.equals(type, "cur")){
			adPlans = adPlanService.getCurrentPlan(NumberUtils.toInt(currentPage), NumberUtils.toInt(countPerPage));
		}
		
		return Action.SUCCESS;
	}
	
	public String getCurrentPlanById(){
		adPlan = adPlanService.getCurrentPlanByAd(ad.getId());
		
		return Action.SUCCESS;
	}
	
	
	public String getById(){
		adPlan = adPlanService.getById(adPlan.getId());
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		adPlanService.delete(opIds);
		
		return Action.SUCCESS;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public AdPlan getAdPlan() {
		return adPlan;
	}

	public void setAdPlan(AdPlan adPlan) {
		this.adPlan = adPlan;
	}

	public Page<AdPlan> getAdPlans() {
		return adPlans;
	}

	public void setAdPlans(Page<AdPlan> adPlans) {
		this.adPlans = adPlans;
	}

	public long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(long[] opIds) {
		this.opIds = opIds;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
	

}
