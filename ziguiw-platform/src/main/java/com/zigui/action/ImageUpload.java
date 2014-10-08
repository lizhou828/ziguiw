package com.zigui.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.zigui.utils.ConfigUtils;
import com.zigui.utils.FileOperate;
import com.zigui.utils.FileUtil;

public class ImageUpload extends ActionSupport implements ServletContextAware {
	
	private static final Log log = LogFactory.getLog(ImageUpload.class);

	private static final long serialVersionUID = -8769117627278687577L;
	
	private static final String rootDir = ConfigUtils.getProperty("static.savedPath");

	private File uploadFile;
	private String uploadFileFileName;
	private String contentType;
	
	private Map map = new HashMap();

	private ServletContext context;
	
	private String imgUrl;


	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}


	public void setDocContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String execute() throws Exception {
		String objDir = "images";
		String [] sizes = null;
		
		String dataSavePath = FileUtil.copyAndCompressImage(uploadFile, uploadFileFileName, rootDir, objDir, sizes);
		HttpServletRequest request = ServletActionContext.getRequest();
		String contextPath = request.getContextPath();
	    if("".equals(contextPath) || contextPath == null){
	    	contextPath = "/";
	    	imgUrl = contextPath + dataSavePath;
	    }else{
	    	imgUrl = contextPath + "/" + dataSavePath;
	    }
	    map.put("imgUrl", imgUrl);
		return SUCCESS;
	}

	private String generateFileName(String fileName) {
		//DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		long formatDate =System.currentTimeMillis();

		int random = new Random().nextInt(10000);

		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);

		return formatDate + random + extension;
	}

	public String getImgUrl() {
		return imgUrl;
	}

    public static boolean checkIsImage(String extName) {
        //to deleted
        return false;
    }
}
