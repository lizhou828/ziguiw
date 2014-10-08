package com.zigui.utils;

import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.zigui.action.SourceAction;



public class FileOperate {
	private static final Log log = LogFactory.getLog(FileOperate.class);
	public static String makeResourcePath(String path){
		Calendar now=Calendar.getInstance();
		String year = Integer.toString(now.get(Calendar.YEAR));
		String month = Integer.toString(now.get(Calendar.MONTH)+1);
		String day = Integer.toString(now.get(Calendar.DAY_OF_MONTH));
		return path+"/" + year + "/" + month + day; 
	}
	
	/**
	 * 多文件上传
	 * 
	 * @param uploads
	 * @param fileNames
	 * @param uploadContentTypes
	 * @param filepath
	 *            文件存放路径
	 * @return
	 * @throws Exception
	 */
	public static String fileUpload(List<File> uploads, List<String> fileNames,
			List<String> uploadContentTypes, String filepath) throws Exception {
		StringBuffer sbFilepath = new StringBuffer();
		// 附件上传
		if (uploads != null && uploads.size() > 0) {
			// 取得服务器的绝对路径
			String realpath = HttpUtil.getRealPath();

			// 如果目录不存在，则创建
			File filedir = new File(realpath + filepath);
			if (!filedir.exists()) {
				filedir.mkdirs();
			}
			for (int i = 0; i < uploads.size(); i++) {
				Random rand = new Random();
				String filename = DateUtil.setDateFormat(new Date(), "HHmmss")
						+ rand.nextInt(100000) + "_" + fileNames.get(i);
				File file = uploads.get(i);
				if (file.exists()) {
					InputStream is = new FileInputStream(file);
					OutputStream os = new FileOutputStream(realpath + filepath
							+ filename);
					byte buffer[] = new byte[8192];
					int count = 0;
					while ((count = is.read(buffer)) > 0) {
						os.write(buffer, 0, count);
					}
					os.close();
					is.close();
					sbFilepath.append(filepath + filename);
					sbFilepath.append("#");
				}
			}
		}
		return sbFilepath.toString();
	}

	/**
	 * 单文件上传
	 * 
	 * @param uploads
	 * @param fileNames
	 * @param uploadContentTypes
	 * @param filepath
	 *            上文件上传到指定目录下
	 * @param filename
	 *            保存为指定文件名
	 * @return 文件保存路径
	 * @throws Exception
	 */
	public static String fileUpload(File upload, String uploadFileName,
			String uploadContentType, String filepath, String filename)
			throws Exception {
		StringBuffer sbFilepath = new StringBuffer();
		// 附件上传
		if (upload != null && upload.exists()) {

			// 取得服务器的绝对路径
			String realpath = HttpUtil.getRealPath();

			// 如果目录不存在，则创建
			File filedir = new File(makeResourcePath(filepath + "/resources"));
			if (!filedir.exists()) {
				filedir.mkdirs();
			}

			InputStream is = new FileInputStream(upload);
			// 文件名过滤特殊字符
			String name = filename.substring(0, filename.lastIndexOf("."));
			String extension = filename.substring(filename.lastIndexOf("."));
			name = Pubfun.specialCharFilter(name);
			filename = name + extension;
			OutputStream os = new FileOutputStream(filedir.getAbsolutePath()
					+"/"+ filename);
			byte buffer[] = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				os.write(buffer, 0, count);
			}
			os.close();
			is.close();
			sbFilepath.append( makeResourcePath(filepath.substring(filepath.lastIndexOf("/")+1)+"/resources")+ "/" +filename);
		}
		return sbFilepath.toString();
	}

	/**
	 * 为上传文件自动分配文件名称
	 * 
	 * @param fileName
	 *            上传的原文件名
	 * @return
	 */
	public static String generateFileName(String fileName) {
		// 随即生产文件编号
		int random = new Random().nextInt(10000);
		// 获得文件后缀名
		String extension = fileName.substring(fileName.lastIndexOf("."));
		return (System.currentTimeMillis() + random + extension);
	}

	/**
	 * 文件复制
	 * 
	 * @param file
	 *            源文件
	 * @param path
	 *            目标路径
	 * @throws Exception
	 */
	public static void fileCopy(File file, String path) throws Exception {
		FileInputStream fis;
		FileOutputStream fos;
		File outFile = new File(path);
		if (!outFile.exists()) {
			outFile.mkdirs();
		}
		fis = new FileInputStream(file);
		fos = new FileOutputStream(path + "/cover.jpg");
		byte buffer[] = new byte[8192];
		int count = 0;
		while ((count = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, count);
		}
		fos.close();
		fis.close();
	}

	/**
	 * 删除指定路径文件
	 * 
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static void deleteFile(String filepath) {
		if (filepath != null && !filepath.equals("")) {
			//String strFile = HttpUtil.getRealPath(filepath);
			File file = new File(filepath);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 删除目录及文件
	 * 
	 * @param fileDirectory
	 */
	public static void deleteDirectoryAndFile(File fileDirectory) {
		if (fileDirectory.isDirectory()) {
			System.out.println(fileDirectory + "是文件夹--");
			File[] files = fileDirectory.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						System.out.println("删除文件" + file);
						file.delete();
					} else {
						System.out.println("函数回调");
						deleteDirectoryAndFile(file);
						file.delete();
					}
				}
			}
		}
		fileDirectory.delete();
	}

	/**
	 * 下载附件
	 * 
	 * @param filepath
	 *            指定目录下的（如upload下的传upload\\）
	 * @param filename
	 *            指定文件
	 * @return
	 * @throws Exception
	 */
	public static String downFile(String filepath, String filename)
			throws Exception {
		// 得到REQUEST对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fullfilepath = HttpUtil.getRealPath() + filepath + filename;
		File file = new File(fullfilepath);
		if (file.exists()) {
			// 中文文件名处理
			if (request.getHeader("User-Agent").toLowerCase()
					.indexOf("firefox") > 0) {
				// 特殊字符处理如空格
				filename = "\"" + filename + "\"";
				filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");// firefox浏览器
			} else {
				if (request.getHeader("User-Agent").toUpperCase().indexOf(
						"MSIE") > 0) {
					filename = URLEncoder.encode(filename, "UTF-8");// IE浏览器
				}
			}
			OutputStream out = response.getOutputStream();
			response.reset();
			// 指定返回的是一个不能被客户端读取的流，必须被下载
			response.setContentType("application/octet-stream;charset=utf-8");
			// 添加头信息，“为下载文件/另存为”对话框指定默认文件名
			response.setHeader("content-disposition", "attachment;filename="
					+ filename);

			FileInputStream fis = new FileInputStream(fullfilepath);
			int i = -1;
			while ((i = fis.read()) != -1) {
				out.write(i);
			}
			out.flush();
			out.close();
			fis.close();

		} else {
			response
					.getWriter()
					.println(
							"<script>alert('File does not exist!');history.go(-1);</script>");
			response.getWriter().close();
		}
		return null;
	}

	/**
	 * 下载附件
	 * 并指定用户下载时的文件名
	 * 
	 * @param filepath
	 *            指定目录下的（如upload下的传upload\\）
	 * @param filename
	 *            指定文件
	 * @param userfilename
	 *            指定用户下载后的文件名
	 * @return
	 * @throws Exception
	 */
	public static String downFile(String filepath, String filename,String userfilename)
			throws Exception {
		// 得到REQUEST对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String fullfilepath =  filepath + filename;
		String resourcePath = ConfigUtils.getProperty("static.savedPath");
		fullfilepath = resourcePath.substring(0,resourcePath.lastIndexOf("/")+1) + fullfilepath;
		File file = new File(fullfilepath);
		log.info("filepath ===== " + file.getPath());
		if (file.exists()) {
		
			if (request.getHeader("User-Agent").toUpperCase().indexOf(
					"MSIE") > 0) {
				userfilename = URLEncoder.encode(userfilename, "UTF-8");// IE浏览器
			}else{
				userfilename = new String(userfilename.getBytes("UTF-8"), "ISO8859-1");
			}
			InputStream fis = new BufferedInputStream(new FileInputStream(fullfilepath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + userfilename);
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream;charset=utf-8");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			
			
		} else {
			response
					.getWriter()
					.println(
							"<script>alert('File does not exist!');history.go(-1);</script>");
			response.getWriter().close();
		}
		return null;
	}

	/**
	 * 上传封面
	 * 
	 * @param item
	 * @param date
	 * @param id
	 * @param cover
	 * @param coverFileName
	 * @param coverContentType
	 * @return
	 * @throws Exception
	 */
	public static String uploadCover(String item, Date date, String id,
			File cover, String coverFileName, String coverContentType)
			throws Exception {
		// 文件保存路径
		String uploadFilepath = Pubfun.structurePath(item, date, id);

		// 获得文件后缀名
		String extension = coverFileName.substring(coverFileName
				.lastIndexOf("."));
		// 封面保存文件名
		String coverUploadFilename = "cover" + extension;
		// 上传封面
		String coverUploadPath = FileOperate.fileUpload(cover, coverFileName,
				coverContentType, uploadFilepath, coverUploadFilename);
		return coverUploadPath;
	}

    public static String saveFile(String alias, File file, String fileExt) throws IOException {
        String dbPath = FileOperate.makeResourcePath(alias);
        File dbPathFile = new File(dbPath);
        if(!dbPathFile.exists()) {
            dbPathFile.mkdirs();
        }
        String filePath = dbPath +"/" + System.currentTimeMillis() + "." + fileExt;
        String savedDiskPath = ConfigUtils.getProperty("static.savedPath") + "/" + filePath;
        FileUtils.copyFile(file, new File(savedDiskPath));
        return "upload/" + filePath;

    }
}
