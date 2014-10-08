package controllers;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;

import java.io.*;
import java.net.ConnectException;

/**
 * 文件转换类
 * 将doc、ppt、xls文件转换为swf
 * @copyright 湖南爱瑞杰科技发展股份有限公司
 * @author sly
 * @version 1.0
 * @since 2012年3月22日10:39:07
 */
public class ConvertToSWF {


    private static final Log log = LogFactory.getLog(ConvertToSWF.class);

    /**
	 * convert pdf 2 swf
	 * @param sourcePath the origin file
	 * @param pdfPath the pdf file abs path
	 * @param swfPath the swf file abs path, to saved.
	 * @throws java.net.ConnectException
	 * @return boolean 返回是否成功转换文件
	 */
	public static boolean convertFile(String sourcePath,String pdfPath,String swfPath) {

		File sourceFile  = new File(sourcePath);		//转换源文件
		File pdfFile = new File(pdfPath);			//PDF目标文件
		File swfFile = new File(swfPath);			//SWF目标文件
		Runtime r;
		String fileType = sourcePath.substring(sourcePath.lastIndexOf(".")+1);

		if(fileType.equals("pdf")){
			pdfFile = sourceFile;
		}else{
		//转换成pdf文件if(isLegal(fileType.toUpperCase())){
		if(sourceFile.exists() && isLegal(fileType.toUpperCase())) {
			if(!pdfFile.exists()) {
				//获取连接对象
				try {
					OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
					//取得连接
					connection.connect();

					//创建文件格式转换对象
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					//实现文件格式转换
					converter.convert(sourceFile, pdfFile);
					//生成已转换的PDF文件
					pdfFile.createNewFile();
					//释放连接
					connection.disconnect();
				} catch (ConnectException e) {
					log.error("OpenOffice服务未启动", e);
					return false;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					log.error("读取文件失败", e);
					return false;
				} catch (Exception e){
					log.error(String.format("convert the origin file 2 pdf failed! the file path is %s", sourceFile.getPath()) , e);
					return false;
				}
			} else {
				log.warn("已转换为PDF，无需再次转换");
			}
		} else {
			log.error("要转换的文件不存在");
			return false;
		} 
		}
		//转换成swf文件
		r = Runtime.getRuntime();
		if(!swfFile.exists()){
			if(pdfFile.exists()) {
				try {
					String swfToolsPath = Play.configuration.getProperty("resources.swfTool");
					String command = String.format("%s %s -o %s -T 9", swfToolsPath, pdfFile.getPath(), swfFile.getPath());
					log.info(command);
					Process p = r.exec(command);
					int re = clearCache(p.getInputStream(), p.getErrorStream());
					if(re == 0){
						log.error("转换成swf文件时失败！");
						return false;
					}
					log.info("第三步：转换为SWF格式	路径：" + swfFile.getPath());
                    log.info("第si步：转换为SWF格式mingcheng：" + swfFile.getName());
				} catch (Exception e) {
					log.error("转换swf文件异常", e);
                    return false;
				}
			} else {
				log.error("PDF文件不存在，无法转换");
				return false;
			}
		} else {
			log.warn("已经转为SWF文件，无需再次转换");
		}
		return true;
		
	}
	

	/**
	 * 清理缓冲区
	 * @param isi
	 * @param ise	
	 */
	private static int clearCache(InputStream isi, InputStream ise){
		try {
			final InputStream is1 = isi;
			//启用单独线程清空InputStream缓冲区
			new Thread(new Runnable() {
			    public void run() {
			        BufferedReader br = new BufferedReader(new InputStreamReader(is1)); 
			        try {
						while(br.readLine() != null) ;
					} catch (IOException e) {
						e.printStackTrace();
					}
			    }
			}).start(); 
			//读入ErrorStream缓冲
			BufferedReader br = new BufferedReader(new InputStreamReader(ise));
			//保存缓冲输出结果
			StringBuilder buf = new StringBuilder();
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//循环等待进程结束
			while(line != null){
				buf.append(line);
				if(line.toLowerCase().indexOf("error") > -1){
					is1.close();
					ise.close();
					br.close();
					return 0;
				}
			}
			is1.close();
			ise.close();
			br.close();
			return 1;
		} catch (Exception e) {
			log.error("清理缓冲区失败", e);
			return 0;
		}
	}
	/**
	 * 判断所转换文件类型是否合法
	 * @param getFileType 	//文件格式
     */
	
	public static boolean isLegal(String getFileType){
		boolean fileLegalFlag = false;
		if(getFileType.equals("TXT")){
			fileLegalFlag = true;
		}else if(getFileType.equals("DOC")||getFileType.equals("DOCX")){
			fileLegalFlag = true;
		}else if(getFileType.equals("PPT")||getFileType.equals("PPTX")){
			fileLegalFlag = true;
		}else if(getFileType.equals("XLS")||getFileType.equals("XLSX")){
			fileLegalFlag = true;
		}else if(getFileType.equals("PDF")){
			fileLegalFlag = true;
		}else if(getFileType.equals("RAR")){
			fileLegalFlag = true;
		}else if(getFileType.equals("CHM")){
			fileLegalFlag = true;
		}
		return fileLegalFlag;
	}


}
