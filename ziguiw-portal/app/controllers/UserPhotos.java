package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserTrendsType;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.PhotoAlbum;
import models.UserBase;
import models.UserPhoto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.With;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-15
 * Time: 下午1:53
 */
@With(SecureCAS.class)
public class UserPhotos extends Application{

    private static final Log log = LogFactory.getLog(UserPhotos.class);

    @UnSecure
    public static void list(Long photoAlbumId){
        List<UserPhoto> list = UserPhoto.findByPhotoAlbumId(photoAlbumId);
        UserBase userBase = ((PhotoAlbum) PhotoAlbum.findById(photoAlbumId)).user;
        render(list,userBase);
    }

    public static void delete(Long userPhotoId){
        UserPhoto userPhoto = UserPhoto.findById(userPhotoId);
        userPhoto.status = Status.DELETED;
        userPhoto.save();
        list(userPhoto.photoAlbum.id);
    }

    //设为封面
    public static void top(Long userPhotoId){
        UserPhoto userPhoto = UserPhoto.findById(userPhotoId);
        PhotoAlbum photoAlbum = PhotoAlbum.findById(userPhoto.photoAlbum.id);
        photoAlbum.cover = userPhoto.url;
        photoAlbum.save();
        list(photoAlbum.id);
    }
    @UnSecure
    public static void detailList(Long userPhotoId){
        UserPhoto userPhoto = UserPhoto.findById(userPhotoId);
        List<UserPhoto> list = UserPhoto.findByPhotoAlbumId(userPhoto.photoAlbum.id);
        UserBase userBase = UserBase.findById(userPhoto.userId);
        PhotoAlbum photoAlbum = userPhoto.photoAlbum;
        render(list,userBase,photoAlbum);
    }

    public static void uploadUI(){
        UserBase userBase = (UserBase) renderArgs.get("user");
        List<PhotoAlbum> list = PhotoAlbum.find("select p from PhotoAlbum p where p.status = ? and p.user.id = ?",Status.OK ,userBase.id)
                                                .fetch();
        render(list,userBase);
    }

    //上传图片
    public static void upload(File file,Long photoAlbumId,String text){
        String error = "";
        if(file == null){
            error = "上传失败，请选择图片!";
            flash.put("error",error);
            uploadUI();
        }
        if(photoAlbumId == null){
            error = "上传失败，请选择相册!";
            flash.put("error",error);
            uploadUI();
        }
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.photoAlbum = PhotoAlbum.findById(photoAlbumId);
        userPhoto.text = text;
        userPhoto.userId = userPhoto.photoAlbum.user.id;
        //图片需压缩
        userPhoto.url = FileUtils.copyAndCompressImage(file,file.getName(),
                uploadDir,new String[]{ImageSize.PHOTOLIST ,ImageSize.SZD , ImageSize.XINGCEFENGMIAN});
        userPhoto.create();
        Map<String,Object> map = UserTrendsType.newMessage(userPhoto.userId, UserTrendsType.CREATE, text, UserTrendsType.PHOTO, userPhoto.id);
        jedisPoolSource.rpush(Queues.USER_TRENDS, map);
        list(userPhoto.photoAlbum.id);
    }


    @UnSecure
    public static void mobileCreate(Long userId, Long albumId, String title, File file0, String scale){
        String ctx = (String) renderArgs.get("ctx");
        log.info(String.format("user photo mobile create params {userId = %s, albumId = %s, title = %s, file = %s, scale = %s}",
                userId,albumId,title,file0,scale));
        UserBase userBase = UserBase.findById(userId);
        PhotoAlbum photoAlbum = PhotoAlbum.findById(albumId);
        if(userBase != null && photoAlbum != null){
            log.info("user photo mobile create file name = " + file0.getName());
            UserPhoto userPhoto = new UserPhoto();
            userPhoto.userId = userId;
            userPhoto.photoAlbum = photoAlbum;
            userPhoto.title = title;
            if(file0 != null){
                if(scale != null && (!scale.trim().equals(""))){
                    userPhoto.url = FileUtils.copyAndCompressImage(file0,file0.getName(),uploadDir,new String[]{scale});
                }else {
                    userPhoto.url = FileUtils.copyImage(file0,file0.getName(),uploadDir);
                }
            }
            userPhoto.create();
            if(photoAlbum.cover == null || photoAlbum.cover.trim().equals("")){
                photoAlbum.cover = userPhoto.url;
            }
            renderText(userPhoto.id);
        }else {
            log.info("user photo mobile create userBase is null or photoAlbum is null!");
            renderText("-1");
        }
    }
    @UnSecure
    public static void test(){
        render();
    }
}
