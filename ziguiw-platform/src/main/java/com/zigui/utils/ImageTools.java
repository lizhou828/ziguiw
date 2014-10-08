package com.zigui.utils;

import magick.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageTools {
	
	static{   
        //不能漏掉这个，不然jmagick.jar的路径找不到  
        System.setProperty("jmagick.systemclassloader","no");   
    }   
       
    /**  
     * 压缩图片  
     * @param filePath 源文件路径  
     * @param toPath   缩略图路径  
     */  
    public static void createThumbnail(String filePath, String toPath) throws MagickException {
        ImageInfo info = null;
        MagickImage image = null;
        Dimension imageDim = null;   
        MagickImage scaled = null;
        try{   
            info = new ImageInfo(filePath);
            image = new MagickImage(info);
            imageDim = image.getDimension();   
            int wideth = imageDim.width;   
            int height = imageDim.height;   
            if (wideth > height) {   
                height = 660 * height / wideth;   
                wideth = 660;   
            }    
            scaled = image.scaleImage(wideth, height);//小图片文件的大小.   
            scaled.setFileName(toPath);   
            scaled.writeImage(info);   
        }finally{   
            if(scaled != null){   
                scaled.destroyImages();   
            }   
        }     
    }   
       
    /**  
     * 水印(图片logo)  
     * @param filePath  源文件路径  
     * @param toImg     修改图路径  
     * @param logoPath  logo图路径  
     * @throws magick.MagickException
     */
    public static void initLogoImg(String filePath, String toImg, String logoPath) throws MagickException {
        ImageInfo info = new ImageInfo();
        MagickImage fImage = null;
        MagickImage sImage = null;
        MagickImage fLogo = null;
        MagickImage sLogo = null;
        Dimension imageDim = null;
        Dimension logoDim = null;
        try {
            fImage = new MagickImage(new ImageInfo(filePath));
            imageDim = fImage.getDimension();
            int width = imageDim.width;
            int height = imageDim.height;
            if (width > 660) {
                height = 660 * height / width;
                width = 660;
            }
            sImage = fImage.scaleImage(width, height);

            fLogo = new MagickImage(new ImageInfo(logoPath));
            logoDim = fLogo.getDimension();
            int lw = width / 8;
            int lh = logoDim.height * lw / logoDim.width;
            sLogo = fLogo.scaleImage(lw, lh);

            sImage.compositeImage(CompositeOperator.AtopCompositeOp, sLogo,  width-(lw + lh/10), height-(lh + lh/10));
            sImage.setFileName(toImg);
            sImage.writeImage(info);
        } finally {
            if(sImage != null){
                sImage.destroyImages();
            }
        }
    }

    /**
     * 水印(文字)
        * @param filePath 源文件路径
     * @param toImg    修改图路径
     * @param text     名字(文字内容自己随意)
     * @throws magick.MagickException
     */
    public static void initTextToImg(String filePath, String toImg,  String text) throws MagickException {
            ImageInfo info = new ImageInfo(filePath);
            if (filePath.toUpperCase().endsWith("JPG") || filePath.toUpperCase().endsWith("JPEG")) {
                info.setCompression(CompressionType.JPEGCompression); //压缩类别为JPEG格式
                info.setPreviewType(PreviewType.JPEGPreview); //预览格式为JPEG格式
                info.setQuality(95);
            }
            MagickImage aImage = new MagickImage(info);
            Dimension imageDim = aImage.getDimension();
            int wideth = imageDim.width;
            int height = imageDim.height;
            if (wideth > 660) {
                height = 660 * height / wideth;
                wideth = 660;
            }
            int a = 0;
            int b = 0;
            String[] as = text.split("");
            for (String string : as) {
                if(string.matches("[\u4E00-\u9FA5]")){
                    a++;
                }
                if(string.matches("[a-zA-Z0-9]")){
                    b++;
                }
            }
            int tl = a*12 + b*6 + 300;
            MagickImage scaled = aImage.scaleImage(wideth, height);
            if(wideth > tl && height > 5){
                DrawInfo aInfo = new DrawInfo(info);
                aInfo.setFill(PixelPacket.queryColorDatabase("white"));
                aInfo.setUnderColor(new PixelPacket(0,0,0,100));
                aInfo.setPointsize(12);
                //解决中文乱码问题,自己可以去随意定义个自己喜欢字体，我在这用的微软雅黑
//              String fontPath = "C:/WINDOWS/Fonts/MSYH.TTF";
                String fontPath = "/usr/maindata/MSYH.TTF";
                aInfo.setFont(fontPath);
                aInfo.setTextAntialias(true);
                aInfo.setOpacity(0);
                aInfo.setText("　" + text + "于　" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "　上传于XXXX网，版权归作者所有！");
                aInfo.setGeometry("+" + (wideth-tl) + "+" + (height-5));
                scaled.annotateImage(aInfo);
            }
            scaled.setFileName(toImg);
            scaled.writeImage(info);
            scaled.destroyImages();
    }


    /**
     * 切图
     * @param imgPath 源图路径
     * @param toPath  修改图路径
     * @param w
     * @param h
     * @param x
     * @param y
     * @throws magick.MagickException
     */  
    public static void cutImg(String imgPath, String toPath, int w, int h, int x, int y) throws MagickException {
        ImageInfo infoS = null;
        MagickImage image = null;
        MagickImage cropped = null;
        Rectangle rect = null;   
        try {   
            infoS = new ImageInfo(imgPath);
            image = new MagickImage(infoS);
            rect = new Rectangle(x, y, w, h);   
            cropped = image.cropImage(rect);   
            cropped.setFileName(toPath);   
            cropped.writeImage(infoS);   
               
        } finally {   
            if (cropped != null) {   
                cropped.destroyImages();   
            }   
        }   
    }   
    
    public static void compressImg(String imgPath,String toPath, int w, int h){  
    	FileOutputStream out = null;
    	
        try{  
            ImageInfo info=new ImageInfo(imgPath);
            MagickImage image=new MagickImage(info);
            
            int x = 0, y = 0; // 缩略图在背景的座标
            
            Dimension imageDim = image.getDimension();   
            double wideth = imageDim.width;   
            double height = imageDim.height;   
            if (wideth >= height) {   
                height = w * height / wideth;   
                wideth = w;   
                x = 0;
    			y = (int) (h - height) / 2;
            }else{
            	
            	wideth = h * wideth / height;
            	height = h;
            	
            	y = 0;
    			x = (int) (w - wideth) / 2;
            }
            MagickImage scaled=image.scaleImage((int)wideth, (int)height);
            
            ImageInfo compositedImageInfo = new ImageInfo();
            MagickImage blankImage = new MagickImage(compositedImageInfo);
    		byte[] pixels = new byte[w * h * 4];
    		for (int i = 0; i < w * h; i++) {
    			pixels[4 * i] = (byte) 255;
    			pixels[4 * i + 1] = (byte) 255;
    			pixels[4 * i + 2] = (byte) 255;
    			pixels[4 * i + 3] = (byte) 255;
    		}

    		blankImage.constituteImage(w, h, "RGBA", pixels);
    		blankImage.compositeImage(CompositeOperator.AtopCompositeOp,
    				scaled, x, y);
    		blankImage.setFileName(toPath);
    		blankImage.writeImage(compositedImageInfo);
            
            
            
        }catch(Throwable ex){
        	imageZipProce(imgPath, toPath, w, h, 1.0f);
        	
            ex.printStackTrace();  
        }finally{
        	
        }  
    }  
    
    /** 
     * 压缩图片方法 
     *  
     * @param oldFile 
     *            将要压缩的图片 
     * @param width 
     *            压缩宽 
     * @param height 
     *            压缩长 
     * @param quality 
     *            压缩清晰度 <b>建议为1.0</b> 
     * @param smallIcon 
     *            压缩图片后,添加的扩展名 
     * @return 
     */  
     public static void imageZipProce(String imgPath, String toPath, int w, int h, float quality) {  
         if (imgPath == null) {  
             return;  
         }
         try {  
             File file = new File(imgPath);  
             //文件不存在时  
             if(!file.exists())
            	 return;  
             /** 对服务器上的临时文件进行处理 */  
             Image srcFile = ImageIO.read(file);  
             int new_w=0,new_h=0;  
             //获取图片的实际大小 高度  
             int height=(int)srcFile.getHeight(null);  
             //获取图片的实际大小 宽度  
             int wideth=(int)srcFile.getWidth(null);  
             // 为等比缩放计算输出的图片宽度及高度  
             if((((double)wideth) >(double)w)||(((double)height)>(double) h))  
             {  
                 double rate=0;//算出图片比例值  
                 //宽度大于等于高度  
                 if (wideth >= height) {   
                     height = w * height / wideth;   
                     wideth = w;   
                 }else{
                 	wideth = h * wideth / height;
                 	height = h;
                 }
                 /** 宽,高设定 */  
                 BufferedImage tag = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);  
                 tag.getGraphics().drawImage(srcFile, 0, 0, wideth, height, null);  
                 
                 BufferedImage pic = new BufferedImage(w - (w - wideth) / 2, h,
                		 tag.getType());
                 Graphics2D g = pic.createGraphics();
                 g.setColor(Color.white);
                 g.fillRect(0, 0, (w - wideth) / 2, h);
                 g.drawImage(tag.getScaledInstance(wideth, height, Image.SCALE_SMOOTH), (w - wideth) / 2, (h - height) / 2, null);
                 
               
                 g.dispose();
                 
                 /** 压缩之后临时存放位置 */  
                 FileOutputStream out = new FileOutputStream(toPath);
                 ImageIO.write(pic, "JPG", out);
                 
                 out.close();  
                 srcFile.flush();  
             }  
         } catch (FileNotFoundException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }
     }
     
     public static void main(String[] args){
    	 int w = 50;
    	 int h = 50;
    	 
    	 double wideth = 120;   
         double height = 150;   
         if (wideth >= height) {   
             height = w * height / wideth;   
             wideth = w;   
         }else{
         	wideth = h * (wideth / height);

         	height = h;
         }
         
         System.out.println("width:" + wideth);
         System.out.println("height:" + height);
     }
}  
