package com.arj.ziguiw.common.utils;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA.
 * User: liujianyu
 * Date: 12-12-25
 * Time: P.M 5:28
 */
public class FileUtils {

    private static Log log = LogFactory.getLog(FileUtils.class);

    private static final String IMAGE_DIR = "images";

    private static final String VIDEO_DIR = "videos";

    private static final String RESOURCE_DIR = "resources";

    private static final String[] images = {"jpg", "jpeg", "gif", "png", "bmp"};

    public static String copyAndCompressImage(File file, String fileName, String rootDir, String[] sizes) {
        String _fileName = file.getName();
        if (_fileName.lastIndexOf(".") == -1 || !Arrays.asList(images).contains(_fileName.substring(_fileName.lastIndexOf(".") + 1).toLowerCase()))
            throw new RuntimeException();
        return copyAndCompressImage(file, fileName, rootDir, IMAGE_DIR, sizes);
    }

    public static String copyImage(File file, String fileName, String rootDir) {
        return copyAndCompressImage(file, fileName, rootDir, IMAGE_DIR, new String[]{});
    }

    public static String copyVideo(File file, String fileName, String rootDir) {
        return copyAndCompressImage(file, fileName, rootDir, VIDEO_DIR, new String[]{});
    }

    public static String copyResource(File file,String fileName,String rootDir){
        return  copyResource(file,fileName,rootDir,RESOURCE_DIR);
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
     * @return the image url to saved in db
     */
    private static String copyAndCompressImage(File file, String fileName, String rootDir, String objDir, String[] sizes) {
        if (file != null) {
            String savedFilePath = genericDestFilePath(fileName, objDir);
            String diskFilePath = format("%s/%s", rootDir, savedFilePath);
            try {
                org.apache.commons.io.FileUtils.copyFile(file, new File(diskFilePath));
                compressImages(diskFilePath, sizes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + savedFilePath;
        }
        return null;
    }

    private static String copyResource(File file, String fileName, String rootDir, String objDir){
        if(file != null){
            String saveFilePath = genericDestFilePath(fileName,objDir);
            String diskFilePath = format("%s/%s",rootDir,saveFilePath);
            try{
                org.apache.commons.io.FileUtils.copyFile(file, new File(diskFilePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + saveFilePath;
        }
        return null;
    }


    public static void compressImages(String diskFilePath, String[] sizes) {
        String compressImagePre = diskFilePath.substring(0, diskFilePath.lastIndexOf("."));
        String compressImageBack = diskFilePath.substring(diskFilePath.lastIndexOf("."));
        for (String size : sizes) {
            String[] wh = size.split("X");
            String imgFilePath = String.format("%s_%s%s", compressImagePre, size, compressImageBack);
            compress(diskFilePath, imgFilePath, Integer.parseInt(wh[0]), Integer.parseInt(wh[1]));
        }
    }


    /**
     * it depended jmagick, so u must make the jmagick.dll in java.libaray path
     * @param srcFilePath the source image
     * @param destFilePath the target image
     * @param width width of target image
     * @param height height of target image
     */
    private static void compress(String srcFilePath, String destFilePath, int width, int height) {
        File input = new File(srcFilePath);
        if (!input.exists()) {
            throw new IllegalArgumentException(String.format("image %s not found!", srcFilePath));
        }
        Image imageOriginal;
        try {
            imageOriginal = ImageIO.read(input);
        } catch (IOException e) {
            throw new IllegalStateException("read image failed!", e);
        }
        int w = imageOriginal.getWidth(null);
        int h = imageOriginal.getHeight(null);
        if (width > w) width = w;
        if (height > h) height = h;
        if (width > height) {
            if (height == h || h < w) height = (h * width) / w;
            else width = (w * height) / h;
        } else {
            if (width == w || h > w) width = (w * height) / h;
            else height = (h * width) / w;
        }
        try {
            imageMagick(new File(srcFilePath).getAbsolutePath(), new File(destFilePath).getAbsolutePath(), width, height);
            //compressToSquarePic(new File(destFilePath).getAbsolutePath(), new File(destFilePath).getAbsolutePath(), 1f);
        } catch (MagickException e) {
            throw new IllegalStateException("jmagick exception!", e);
        }
    }

    private static void imageMagick(String srcFilePath, String destFilePath, int width, int height) throws MagickException {
        try {
            log.info(String.format("compress source image : %s", srcFilePath));
            System.setProperty("jmagick.systemclassloader", "no");
            ImageInfo info = new ImageInfo(srcFilePath);
            MagickImage image = new MagickImage(info);
            MagickImage scaleImg = image.scaleImage(width, height);
            scaleImg.setFileName(destFilePath);
            scaleImg.writeImage(info);
            image = null;
            scaleImg = null;
            log.info(String.format("compress destination image %s success!", destFilePath));
        } catch (Throwable t) {
            log.error("call imageMagick error!", t);
            throw new RuntimeException("call imageMagick error!", t);
        }

    }

    /**
     * generic dest file path<br/>
     * for example:
     * 2012/0606/20120606
     *
     * @param fileName originFileName : "aaa.jpg"
     * @param objDir   the object' name : "goods" or other
     */
    private static String genericDestFilePath(String fileName, String objDir) {
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
                cal.get(Calendar.YEAR), month, calDate,System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));
    }

    public static String replaceImgTag(String content, String rootDir, String staticDomain) {
        String s = content;
        String patternStr = "<img\\s*[^>]*\\s*src=[\\\"|'](.*?)[\\\"|']\\s*[^>]*>";
        Pattern patternForTag = Pattern.compile (patternStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(content);
        while (matcherForTag.find()){
            String src = matcherForTag.group(1);
            String destFilePath = genericDestFilePath(src, IMAGE_DIR);
            File dir = new File(rootDir + destFilePath.substring(0, destFilePath.lastIndexOf("/")));
            if (!dir.exists()) {
                if (!dir.mkdirs()) throw new UnsupportedOperationException(String.format("create dir %s failed!", dir.getAbsolutePath()));
            }
            try {
                IOUtils.copy(new URL(src).openStream(), new FileOutputStream(new File(rootDir + "/" + destFilePath)));
                System.out.println(src);
                s = s.replace(src, staticDomain + "/" +rootDir.substring(rootDir.lastIndexOf("/") + 1) + "/" + destFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static List<String> acquireImgTag(String content) {
        String patternStr = "<img\\s*[^>]*\\s*src=[\\\"|'](.*?)[\\\"|']\\s*[^>]*>";
        Pattern patternForTag = Pattern.compile (patternStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE );
        Matcher matcherForTag = patternForTag.matcher(content);
        List<String> list = new ArrayList<String>();
        while (matcherForTag.find()){
            String src = matcherForTag.group(1);
            System.out.println(src);
            list.add(src);
        }
        return list;
    }
}
