package com.zigui.utils;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片上传工具类
 * @author Pigming
 * @update 2012-7-15
 */
public class ImageUpload  extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = -8769117627278687577L;

	private File uploadFile;
	
	private String uploadFileName;

	private ServletContext context;
	
	private String imgUrl;

	
	public String execute() throws Exception {
		String targetDirectory = context.getRealPath("/image/upload");
		String targetFileName = generateFileName(uploadFileName);
		File target = new File(targetDirectory, targetFileName);

		FileUtils.copyFile(uploadFile, target);
		
		setImgUrl(context.getContextPath() + "/image/upload/" + targetFileName);

		return SUCCESS;
	}

	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);

		return formatDate + random + extension;
	}
	
	/**
     * 检查是否是约定的图片格式:jpg, jpeg, png
     * @param 扩展名
     * @return 约定的图片格式返回true，否则返回false
     */
	public static boolean checkIsImage(String imgStr) {
		boolean flag = false;
		if (imgStr != null) {
			if (imgStr.equalsIgnoreCase(".jpg")
					|| imgStr.equalsIgnoreCase(".jpeg")
					|| imgStr.equalsIgnoreCase(".png")
				|| imgStr.equalsIgnoreCase(".gif")) {
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 获取新的图片名称
	 * @param srcFileName
	 * @return
	 */
	public static String getRandomStr(String srcFileName) {
		// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
		// 获取随机数
		int rannum = (int) (new Random().nextDouble() * (99999 - 10000 + 1)) + 10000; 
		// 当前时间
		String nowTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		// 扩展名
		String extName = null;
		// 获取拓展名
		if (srcFileName.lastIndexOf(".") >= 0) {
			extName = srcFileName.substring(srcFileName.lastIndexOf("."));
		}
		// 文件重命名后的名字
		String newFileName = nowTimeStr + rannum + extName; 
		// 返回
		return newFileName;
	}
	
	/**
	 * @Description: 将srcImageFile裁剪后生成destImageFile
	 * @param srcImageFile  原始图
	 * @param destImageFile  目标图
	 * @param width          原始图预处理后width
	 * @param height         原始图预处理后height
	 * @param rect           目标图输出的格式(rect.x, rect.y, rect.width, rect.height)
	 * @throws java.io.IOException
	 * @author
	 */
	public static void cut(String srcImageFile, String destImageFile, int width, int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(new File(srcImageFile));
		BufferedImage bImage = makeThumbnail(image, width, height);
		saveSubImage(bImage, rect, new File(destImageFile));
	}


	/**
	 * @Description: 将srcImageFile裁剪后生成destImageFile
	 * @param srcImageFile  原始图
	 * @param destImageFile  目标图
	 * @param width          原始图预处理后width
	 * @param height         原始图预处理后height
	 * @param rect           目标图输出的格式(rect.x, rect.y, rect.width, rect.height)
	 * @throws java.io.IOException
	 * @author
	 */
	public static void cut(File srcImageFile, File destImageFile, int width, int height, Rectangle rect) throws IOException {
		Image image = ImageIO.read(srcImageFile);
		BufferedImage bImage = makeThumbnail(image, width, height);
		saveSubImage(bImage, rect, destImageFile);
	}

	/**
	 * @Description: 对原始图片根据(x, y, width, height) = (0, 0, width, height)进行缩放，生成BufferImage
	 * @param img
	 * @param width 预处理后图片的宽度
	 * @param height 预处理后图片高度
	 * @return
	 * @author Sun Yanjun
	 * @throws java.io.IOException
	 */
	private static BufferedImage makeThumbnail(Image img, int width, int height) throws IOException {
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics g = tag.getGraphics();
		g.drawImage(img.getScaledInstance(width, height, 4), 0, 0, null);

		g.dispose();
		return tag;
	}

	/**
	 * @Description: 对BufferImage按照(x, y, width, height) = (subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height)进行裁剪
	 *               如果subImageBounds范围过大，则用空白填充周围的区域。
	 *
	 * @param image
	 * @param subImageBounds
	 * @param destImageFile
	 * @throws java.io.IOException
	 * @author Sun Yanjun
	 */
	private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File destImageFile) throws IOException {
		String fileName = destImageFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
		BufferedImage subImage = new BufferedImage(subImageBounds.width, subImageBounds.height, 1);
		Graphics g = subImage.getGraphics();


		if ((subImageBounds.width > image.getWidth())
				|| (subImageBounds.height > image.getHeight())) {
			int left = subImageBounds.x;
			int top = subImageBounds.y;
			if (image.getWidth() < subImageBounds.width)
				left = (subImageBounds.width - image.getWidth()) / 2;
			if (image.getHeight() < subImageBounds.height)
				top = (subImageBounds.height - image.getHeight()) / 2;
			g.setColor(Color.white);
			g.fillRect(0, 0, subImageBounds.width, subImageBounds.height);
			g.drawImage(image, left, top, null);
			ImageIO.write(image, formatName, destImageFile);
		} else {
			g.drawImage(image.getSubimage(subImageBounds.x, subImageBounds.y,
					subImageBounds.width, subImageBounds.height), 0, 0, null);
		}
		g.dispose();
		ImageIO.write(subImage, formatName, destImageFile);
	}

	/**
     * 对图片进行缩放 (使用java调用外部应用程序的方式)
     *
     * @param srcImgFileName
     * @throws java.io.IOException
     */
	public static void zoomImage(String srcImgPath, String destImgPath, int width, int height) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/C", "convert", "-sample", width + "x" + height, srcImgPath, destImgPath);
	    System.out.println(processBuilder.environment());
	    try {
	        Process process = processBuilder.start();
	        process.waitFor();
	        System.out.println(process.exitValue());
	        InputStream stream = process.getErrorStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

	        for(String s=reader.readLine();s!=null;s=reader.readLine()){
	            System.out.println(s);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }


	/**
	 * 给图片添加水印图片
	 *  @param  pressImg -- 水印文件
	 *  @param  targetImg  -- 目标文件
	 *  @param  x
	 *  @param  y
	 * @throws java.io.IOException
	 */
    public   static   void  pressImage(String pressImgPath, String targetImgPath,  int  x,  int  y) throws IOException  {
            File _file  =   new  File(targetImgPath);
            Image src  =  ImageIO.read(_file);
            int  wideth  =  src.getWidth( null );
            int  height  =  src.getHeight( null );
            BufferedImage image  =   new  BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
            Graphics g  =  image.createGraphics();
            g.drawImage(src,  0 ,  0 , wideth, height,  null );

            //  水印文件
            File _filebiao  =   new  File(pressImgPath);
            Image src_biao  =  ImageIO.read(_filebiao);
            int  wideth_biao  =  src_biao.getWidth( null );
            int  height_biao  =  src_biao.getHeight( null );
            g.drawImage(src_biao, wideth  -  wideth_biao  -  x, height  -  height_biao  - y, wideth_biao,
                     height_biao,  null );
            g.dispose();
            BufferedImage tag = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src_biao, 0, 0, wideth, height, null);
            FileOutputStream out  =   new  FileOutputStream(targetImgPath);
            ImageIO.write(tag, "JPG", out);
            out.close();
    }

    /**
     * 将图片以x，y为左上起点，width为宽，height为高进行剪切
     *
     * @param srcImageFile
     * @throws java.io.IOException
     */  
    public static void cut(String srcImagePath, String destImagePath, int leftTopX, int leftTopY, int width, int height) throws IOException {  
        Image img;  
        ImageFilter cropFilter;  
        // 读取源图像  
        BufferedImage src = ImageIO.read(new File(srcImagePath));  
        
        // 四个参数分别为图像起点坐标和宽高  
        cropFilter = new CropImageFilter(leftTopX, leftTopY, width, height);  
        img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter));  
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics g = tag.getGraphics();  
        g.drawImage(img, 0, 0, null); // 绘制小图  
        g.dispose();  
        // 输出为文件  
        File f = new File(destImagePath);  
        ImageIO.write(tag, "JPEG", f);  
            
    }  
    
    public static void main(String[] args){
        String a = "d:/dest.jpg";
        String b = "d:/src.jpg";
        String c = "d:/press.png";
        String d = "d:/cut.jpg";
        
        try {
            cut(b, b, 0, 0, 10, 10);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setContentType(String contentType) {
		this.setContentType(contentType);
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}
