package com.zigui.utils;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zigui.exception.ImageException;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class FileUtil {
	
	private static final Log log = LogFactory.getLog(FileUtil.class);

    /* File copy */
    public static boolean copy(File src, File dst) {
        try {
            FileUtils.copyFile(src, dst);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
            return false;
        }
    }

    /**
     * intercepted   Images name
     *
     * @param fileName Images name
     * @return
     */
    public static String getExtension(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    /**
     * image thumbnail
     *
     * @param src   the source image file path
     * @param dst   the destination image file path
     * @param width the width of destination image
     */
    public static void compress(String src, String dst, int width) {
        try {
            File fi = new File(src); //大图文件
            File fo = new File(dst); //将要转换出的小图文件
            int nw = width;          //400

            AffineTransform transform = new AffineTransform();
            BufferedImage bis = ImageIO.read(fi); //读取图片
            int w = bis.getWidth();
            int h = bis.getHeight();
            int nh = (nw * h) / w;
            double sx = (double) nw / w;
            double sy = (double) nh / h;
            transform.setToScale(sx, sy);

            AffineTransformOp ato = new AffineTransformOp(transform, null);
            BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);

            ato.filter(bis, bid);
            ImageIO.write(bid, "jpeg", fo);
        } catch (Exception e) {
            throw new ImageException("compress image failed!", e);
        }
    }

    private static List<String> imageExtensions = new ArrayList<String>();

    static {
        imageExtensions.add(".jpg");
        imageExtensions.add(".jpeg");
//        imageExtensions.add(".gif");
        imageExtensions.add(".png");
        imageExtensions.add(".bmp");
//        imageExtensions.add(".psd");
//        imageExtensions.add(".pcx");
//        imageExtensions.add(".dxf");
//        imageExtensions.add(".cdr");
    }

    /**
     * image judge
     *
     * @param fileName fileName
     * @return boolean value
     */
    public static boolean isImage(String fileName) {
        return fileName != null && imageExtensions.contains(getExtension(fileName).toLowerCase());
    }

    /**
     * copy and compress image<br/>
     * for Example
     * <pre>
     * File file = struts2's file Object;  //this file maybe a temp file uploaded by struts2 : "*.tmp"
     * String fileName = struts2's fileName; //the name of the tmpFile's name : "aaa.jpg"
     * String rootDir = "/data/realshop/upload";    //from conf.properties
     * String objDir = "goods"; //the Object Name
     * </pre>
     * @param file     the image file uploaded
     * @param fileName fileName of image uploaded
     * @param rootDir  the root directory to save
     * @param objDir   the object name
     * @param sizes    X or S or M or L
     * @return
     */
    public static String copyAndCompressImage(File file, String fileName, String rootDir, String objDir, String[] sizes) {
        if (file != null) {
            String savedFilePath = genericDestFilePath(fileName, objDir);
            String diskFilePath = format("%s/%s", rootDir, savedFilePath);
            boolean isUpload = FileUtil.copy(file, new File(diskFilePath));
            if ( isUpload && sizes != null) {
                compressImages(diskFilePath, sizes);
            }
            
            String saveDatePath = rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + savedFilePath;
            
            return saveDatePath;
        }
        return null;
    }

    /**
     * generic dest file path<br/>
     * for example:
     * 2012/0606/20120606
     *
     * @param fileName originFileName : "aaa.jpg"
     * @param objDir   the object' name : "goods" or other
     */
    public static String genericDestFilePath(String fileName, String objDir) {
        Calendar cal = Calendar.getInstance();
        String month;
        int monthNumber = cal.get(Calendar.MONTH) + 1;
        if (monthNumber < 10) {
            month = "0" + monthNumber;
        } else {
            month = String.valueOf(monthNumber);
        }
        String calDate;
        if (cal.get(Calendar.DATE) < 10) {
            calDate = "0" + cal.get(Calendar.DATE);
        } else {
            calDate = String.valueOf(cal.get(Calendar.DATE));
        }
        return String.format("%s/%s/%s%s/%s",objDir, 
        		cal.get(Calendar.YEAR), month, calDate,System.currentTimeMillis() + FileUtil.getExtension(fileName));
    }

    public static void compressImages(String diskFilePath, String[] sizes) {
        String compressImagePre = diskFilePath.substring(0, diskFilePath.lastIndexOf("."));
        String compressImageBack = diskFilePath.substring(diskFilePath.lastIndexOf("."));
        for (String size : sizes) {
            String[] wh = size.split("X");
            String imgFilePath = String.format("%s_%s%s", compressImagePre, size, compressImageBack);
            ImageUtils.compress(diskFilePath, imgFilePath, Integer.parseInt(wh[0]), Integer.parseInt(wh[1]));
        }
    }

    public static void createFolder(String filePath) {
        String file = filePath.replaceAll("\\\\", "/");
        int n = file.lastIndexOf("/");
        int m = file.lastIndexOf(".");
        if (m > n) {
            file = file.substring(0, n);
        }
        File _file = new File(file);
        if (_file.exists()) return;
        if (_file.mkdirs()) {
            log.info("createFolder:" + file);
        } else {
            throw new RuntimeException(String.format("create Folder %s faild", file));
        }
    }

    
}
