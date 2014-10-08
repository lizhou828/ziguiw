package com.zigui.utils;

import org.apache.commons.lang3.StringUtils;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zigui.exception.ImageException;

public class ImageUtils {
	
	public static String getSizedImage(String imageUrl, String defaultImage, int width){

        System.out.println("ImageUtils is running.. imageUrl="+imageUrl+",defaultImage="+defaultImage);
		return getSizedImage(imageUrl, defaultImage, width, width);
	}
	
	public static String getSizedImage(String imageUrl, String defaultImage, int width, int height){
		if(StringUtils.isEmpty(imageUrl) || "null".equals(imageUrl)){
			imageUrl = defaultImage;
		}
		
		int position = imageUrl.lastIndexOf(".");
        System.out.println("position="+position);
		String prefix = imageUrl.substring(0, position);
        System.out.println("prefix="+prefix);
		String suffix = imageUrl.substring(position);
        System.out.println("suffix="+suffix);
		
		String result = prefix + "_" + width + "_" + height + suffix;
        System.out.println("result url is="+result);
		return result;
	}
	
	private static final Log log = LogFactory.getLog(ImageUtils.class);

    /**
     * it depended jmagick, so u must make the jmagick.dll in java.libaray path
     * @param srcFilePath the source image
     * @param destFilePath the target image
     * @param width width of target image
     * @param height height of target image
     */
    public static void compress(String srcFilePath, String destFilePath, int width, int height) {
        File input = new File(srcFilePath);
        if (!input.exists()) {
            throw new IllegalArgumentException(String.format("image %s not found!", srcFilePath));
        }
        Image imageOriginal;
        try {
            imageOriginal = ImageIO.read(input);
        } catch (IOException e) {
            throw new ImageException("read image failed!", e);
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
            throw new ImageException("jmagick exception!", e);
        }
    }

    private static void imageMagick(String srcFilePath, String destFilePath, int width, int height) throws MagickException {
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
    }

    /**
    * change image to square
    *
    * @param srcFilePath   the source file path
    * @param destFilePath  the destination file path
    * @param per           the compress percent
    */
    private static void compressToSquarePic(String srcFilePath, String destFilePath, float per) {
        Image src;
        try {
            File srcFile = new File(srcFilePath);
            src = javax.imageio.ImageIO.read(srcFile);
            int old_w = src.getWidth(null);
            int old_h = src.getHeight(null);
            int new_w = 0;
            int new_h = 0;
            /* change to square begin [add white space by width or height] */
            BufferedImage oldPic;
            if (old_w > old_h) {
                oldPic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
            } else {
                if (old_w < old_h) {
                    oldPic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
                } else {
                    oldPic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
                }
            }
            Graphics2D g = oldPic.createGraphics();
            g.setColor(Color.white);
            if (old_w > old_h) {
                g.fillRect(0, 0, old_w, old_w);
                g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, Color.white, null);
            } else {
                if (old_w < old_h) {
                    g.fillRect(0, 0, old_h, old_h);
                    g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, Color.white, null);
                } else {
                    g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
                }
            }
            g.dispose();
            src = oldPic;
            /* change to square end */
            if(old_h > old_w){
                new_h = old_h;
                new_w = old_h;
            }else{
                new_h = old_w;
                new_w = old_w;
            }
            BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream newImage = new FileOutputStream(destFilePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newImage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /* compress quality */
            jep.setQuality(per, true);
            encoder.encode(tag, jep);
            newImage.close();
            log.info(String.format("change image %s to square success", srcFilePath));
        } catch (IOException ex) {
            Logger.getLogger(ImageUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getImagesUrl(String alias, String src, String size) {
        String staticDomain = ConfigUtils.getProperty("static.domain");
        if (size != null) {
            int idx = src.lastIndexOf(".");
            String preImageUrl = src.substring(0, idx);
            String backImageUrl = src.substring(idx);
            src = String.format("%s_%s%s", preImageUrl, size, backImageUrl);
        }
        return String.format("%s%s/%s/%s", "http://", staticDomain, alias, src);
    }

}
