package com.zigui.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.Ad;
import com.zigui.service.impl.AdServiceImpl;
import com.zigui.utils.ImageUpload;

/**
 * 广告
 * 
 */
public class AdAction extends ActionSupport implements ServletContextAware{

	private static final long serialVersionUID = 1L;
	
	private Ad ad;
	private List<Ad> ads;
	private long[] opIds;
	
	private File uploadFile;
	private String uploadFileFileName;
	private String uploadFileContentType;
	
	private ServletContext context;
	
	@Autowired
	private AdServiceImpl adService;
	
	public String saveOrUpdate(){
		
		if(ad.getId() > 0){
			Ad tmpAd = adService.getById(ad.getId());
			ad.setImageUrl(tmpAd.getImageUrl());
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
			
			String webPath =  "/upload/" + fileName;
			
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
			
			ad.setImageUrl(webPath);
		}
		
		adService.saveOrUpdate(ad);
		
		return Action.SUCCESS;
	}
	//通过状态跟标识获取
	public String getByStateAndFlag(){
		
		ads = adService.getByStateAndFlag(ad.getState(),ad.getFlag());
		System.out.println(ads.size());
		return Action.SUCCESS;
	}
	
	public String getAll(){
		ads = adService.getAll();
		ads = adService.getDaysBetween(ads); 
		
		return Action.SUCCESS;
	}
	
	public String getById(){
		ad = adService.getById(ad.getId());
		
		return Action.SUCCESS;
	}
	
	public String delete(){
		adService.delete(opIds);
		
		return Action.SUCCESS;
	}
	
	public String deleteAjax(){
		HttpServletResponse response = ServletActionContext.getResponse();
		adService.delete(opIds);
		try {
			print(response,"success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected void print(HttpServletResponse response, String info) throws IOException {
        try {
        	  response.setHeader("Cache-Control", "no-cache");
        	  response.setContentType("text/json;charset=utf-8");
              response.getWriter().print(info);
        } catch (IOException e) {
                e.printStackTrace();
                throw e;
        }
     }
	
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	public List<Ad> getAds() {
		return ads;
	}
	public void setAds(List<Ad> ads) {
		this.ads = ads;
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

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	

}
