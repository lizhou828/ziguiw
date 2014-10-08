package com.zigui.task;

import com.zigui.utils.ImageTools;
import com.zigui.utils.ImageUpload;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.File;

public class CompressTask implements org.quartz.Job{
	
	public static Logger log = Logger.getLogger(CompressTask.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		log.debug("file");
		
		// 分目录存放用户图片
		File file = new File("/usr/local/tomcat_ziguiw/webapps/ziguiw/upload/46542");
		compress(file);
		file = new File("/usr/local/tomcat_ziguiw/webapps/ziguiw/upload/");
		compress(file);
		file = new File("/usr/local/tomcat_ziguiw/webapps/ROOT/upload/");
		compress(file);
		file = new File("/usr/local/tomcat_ziguiw/webapps/ROOT/ziguiw/upload");
		compress(file);
		
	}
	
	public void compress(File file){
		log.debug("file:" + file);
		
		if(!file.exists())
			return;
		
		if(file.isDirectory()){
			log.debug("file:1111111111111" + file);
			for(File file1 : file.listFiles()){
				log.debug("file1" + file1);
				log.debug("file1.getPath().substring(file1.getPath().lastIndexOf())" + file1.getPath().substring(file1.getPath().lastIndexOf(".")));
				log.debug("file1.getPath()" + file1.getPath());
				log.debug("ImageUpload.checkIsImage(file1.getPath().substring(file1.getPath().lastIndexOf()))" + ImageUpload.checkIsImage(file1.getPath().substring(file1.getPath().lastIndexOf("."))));
				log.debug("file1.getPath().contains()" + file1.getPath().substring(file1.getPath().lastIndexOf("/")).contains("_"));
				if(file1.isDirectory()){
					
					compress(file1);
				}else if(ImageUpload.checkIsImage(file1.getPath().substring(file1.getPath().lastIndexOf("."))) && !file1.getPath().substring(file1.getPath().lastIndexOf("/")).contains("_")){
					log.debug("file:222222222222" + file);
					try{
						String extName = file1.getPath().substring(file1.getPath().lastIndexOf("."));
						String fileName = file1.getAbsolutePath().substring(0, file1.getPath().lastIndexOf("."));
						
						ImageTools.compressImg(file1.getAbsolutePath(), fileName + "_50_50" + extName, 50, 50);
						ImageTools.compressImg(file1.getAbsolutePath(), fileName + "_70_70" + extName, 70, 70);
						ImageTools.compressImg(file1.getAbsolutePath(), fileName + "_120_120" + extName, 120, 120);
						ImageTools.compressImg(file1.getAbsolutePath(), fileName + "_160_160" + extName, 160, 160);
						
						log.debug("fileName" + fileName);
					}catch(Exception e){
						log.error(e.getMessage(), e);
					
					}
				}
			}
		}else{
			try{
				if(ImageUpload.checkIsImage(file.getPath().substring(file.getPath().lastIndexOf("."))) && !file.getPath().substring(file.getPath().lastIndexOf("/")).contains("_")){
					String extName = file.getPath().substring(file.getPath().lastIndexOf("."));
					String fileName = file.getAbsolutePath().substring(0, file.getPath().lastIndexOf("."));
					
					ImageTools.compressImg(file.getAbsolutePath(), fileName + "_50_50" + extName, 50, 50);
					ImageTools.compressImg(file.getAbsolutePath(), fileName + "_70_70" + extName, 70, 70);
					ImageTools.compressImg(file.getAbsolutePath(), fileName + "_120_120" + extName, 120, 120);
					ImageTools.compressImg(file.getAbsolutePath(), fileName + "_160_160" + extName, 160, 160);
					log.debug("fileName" + fileName);
				}
				
			}catch(Exception e){
				log.error(e.getMessage(), e);
			}
		}
	}


}
