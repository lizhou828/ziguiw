package controllers;

import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Page;
import models.PhotoAlbum;
import models.UserBase;
import models.UserPhoto;
import play.mvc.With;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-15
 * Time: 上午10:04
 */
@With(SecureCAS.class)
public class PhotoAlbums extends Application{

    @UnSecure
    public static void list(Long userId){
        UserBase userBase = UserBase.findById(userId);
        Page<PhotoAlbum> page = PhotoAlbum.findByUserId(userId,getPage(),getPageSize());
        render(userBase,page);
    }

    public static void add(Long userId){
        UserBase userBase = UserBase.findById(userId);
        render(userBase);
    }

    public static void update(Long photoAlbumId){
        PhotoAlbum photoAlbum = PhotoAlbum.findById(photoAlbumId);
        UserBase userBase = UserBase.findById(photoAlbum.user.id);
        render(userBase,photoAlbum);
    }

    public static void create(String name,String describe,Long photoAlbumId){
        String error = "";
        UserBase userBase = (UserBase) renderArgs.get("user");
        if(name != null && name.trim().length() > 0){
            if(photoAlbumId == null){
                PhotoAlbum photoAlbum = new PhotoAlbum();
                photoAlbum.describe = describe;
                photoAlbum.name = name;
                photoAlbum.user = userBase;
                photoAlbum.create();
            }else {
                PhotoAlbum photoAlbum = PhotoAlbum.findById(photoAlbumId);
                photoAlbum.name = name;
                photoAlbum.describe = describe;
                photoAlbum.save();
            }
        }else{
            error = "标题不能为空";
            flash.put("error",error);
            add(userBase.id);
        }
        list(userBase.id);

    }

    public static void delete(Long photoAlbumId){
        PhotoAlbum photoAlbum = PhotoAlbum.findById(photoAlbumId);
        List<UserPhoto> list = UserPhoto.findByPhotoAlbumId(photoAlbum.id);
        if(photoAlbum != null){
            photoAlbum.status = Status.DELETED;
            photoAlbum.save();
        }
        list(photoAlbum.user.id);
    }
}
