package com.zigui.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.utils.FileOperate;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class KdImgUpload extends ActionSupport {

	private static final long serialVersionUID = 6608514462963923184L;
	
	private File imgFile;

	/** 文件名称 */
	private String imgFileFileName;

	/** 图片宽度 */
	private String imgWidth;

	/** 图片高度 */
	private String imgHeight;

	/** 图片对齐方式 */
	private String align;

	/** 图片标题 */
	private String imgTitle;

    private Log log = LogFactory.getLog(KdImgUpload.class);


    public String execute() throws IOException {
        ActionContext context = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
        response.setContentType("text/html; charset=UTF-8");
        String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
        long maxSize = 1024*1024;
        PrintWriter out = response.getWriter();
        if (imgFile == null) {
            out.println(getError("请选择文件。"));
            return null;
        }
        String fileExt = imgFileFileName.substring(imgFileFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(fileTypes).contains(fileExt)) {
            out.println(getError("上传文件扩展名[" + fileExt + "]是不允许的扩展名。"));
            return null;
        }
        if (imgFile.length() > maxSize) {
            out.println(getError("[ " + imgFileFileName + " ]超过单个文件大小限制，文件大小[ " + imgFile.length() + " ]，限制为[ " + maxSize + " ] "));
            return null;
        }
        response = ServletActionContext.getResponse();
        response.setCharacterEncoding("utf-8");
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url","/" + FileOperate.saveFile("images", imgFile, fileExt));
        out.println(obj.toString());
        return null;
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}
	
	


}
