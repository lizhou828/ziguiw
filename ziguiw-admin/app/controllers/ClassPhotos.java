package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.utils.FileUtils;
import models.ClassPhoto;
import models.QueryParams;
import models.SchoolClazz;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Http;
import play.mvc.Util;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-31
 * Time: 上午10:01
 */
public class ClassPhotos extends Application{

    @Util
    public static void beforeSave(Model _object) {
        ClassPhoto object = (ClassPhoto)_object;
        File file = Http.Request.current().params.get("file", File.class);
        File cover = Http.Request.current().params.get("cover", File.class);
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,
                    new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL,ImageSize.CZX});
        }
        if(file != null && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyImage(file,file.getName(),uploadDir);
            if(cover != null){
                object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
            }
        }
        renderArgs.put("photoType", object.type);
    }

    @Util
    public static void beforeCreate(Model _object) {
        ClassPhoto object = (ClassPhoto)_object;
        File file = Http.Request.current().params.get("file", File.class);
        File cover = Http.Request.current().params.get("cover", File.class);
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,
                    new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL,ImageSize.CZX});
        }
        if(object.type == SchoolPhotoType.VIDEO){
            if(file != null){
                object.url = FileUtils.copyImage(file,file.getName(),uploadDir);
            }
            if(cover != null){
                object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
            }
        }
        renderArgs.put("photoType", object.type);
    }


    @Util
    public static void beforeQuery(QueryParams queryParams) {
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
        String photoType = params.get("where.type");
        if (photoType == null) photoType = session.get("classAdminPhotoType");
        if (photoType == null) photoType = SchoolPhotoType.PHOTO + "";
        renderArgs.put("photoType", Integer.parseInt(photoType));
        session.put("classAdminPhotoType", photoType);
    }

    public static void show(Long id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        ClassPhoto object = ClassPhoto.findById(id);
        List<SchoolClazz> schoolClazz = SchoolClazz.findByXXId(object.xxId , 70);
        notFoundIfNull(object);
        try {
            render(type, object ,schoolClazz);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }
}
