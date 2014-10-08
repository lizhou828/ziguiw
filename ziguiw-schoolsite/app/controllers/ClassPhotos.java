package controllers;

import com.arj.ziguiw.common.Boolean;
import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Before;
import play.mvc.Http;
import play.mvc.Util;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-18
 * Time: 上午9:44
 */
@With(SecureCAS.class)
public class ClassPhotos extends Application{
    private static final Log logger = LogFactory.getLog(ClassPhotos.class);

    @UnSecure
    public static void plist(Integer type){
        if(type == null){
            type = SchoolPhotoType.PHOTO;
        }
        if(type == SchoolPhotoType.PHOTO){
             Page<SchoolAlbum> page = SchoolAlbum.findByClassId(((SchoolClazz) renderArgs.get("schoolClass")).id, getPage(), 8);
             render(page,type);
        }else {
            Page<ClassPhoto> page = ClassPhoto.findPhotoPage
                    (getPage(), 8, ((SchoolClazz) renderArgs.get("schoolClass")).id, type);
            render(page,type);
        }
    }

    public static void show(String id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        Model object = type.findById(id);
        notFoundIfNull(object);
        String _type = request.params.get("classPhotoType");
        if (_type != null) {
            int classPhotoType = Integer.parseInt(_type);
            renderArgs.put("classPhotoType",classPhotoType);
        }
        SchoolClazz schoolClazz = ((SchoolClazz) renderArgs.get("schoolClass"));
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findClassId(schoolClazz.id, 10086);
        renderArgs.put("schoolAlbums",schoolAlbums);
        try {
            render(type, object);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }

    public static void blank(){
        String type = request.params.get("classPhotoType");
        if (type != null) {
            int classPhotoType = Integer.parseInt(type);
            renderArgs.put("classPhotoType",classPhotoType);
        }
        SchoolClazz schoolClazz = ((SchoolClazz) renderArgs.get("schoolClass"));
        School school = (School) renderArgs.get("school");
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findClassId(schoolClazz.id, 10086);
        renderArgs.put("schoolAlbums",schoolAlbums);
        render();
    }

    @Before(only = {"blank","list","create"})
    public static void before(){
        String type = request.params.get("classPhotoType");
        if (type != null) {
            int classPhotoType = Integer.parseInt(type);
            renderArgs.put("classPhotoType",classPhotoType);
        }
    }

    @Before(only = {"delete","save"})
    public static void before1(){
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String id = request.params.get("id");
        ClassPhoto object = ClassPhoto.findById(Long.valueOf(id));
        if(object.status == Status.OK){
            renderArgs.put("error","操作失败，照片/视频已审核!");
            render("ClassPhotos/show.html",object,type);
        }
    }

    @UnSecure
    public static void index(){
        SchoolClazz schoolClazz = (SchoolClazz) renderArgs.get("schoolClass");
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findClassId(schoolClazz.id, 8);
        List<ClassPhoto> classVideos = ClassPhoto.findByType(schoolClazz.id,SchoolPhotoType.VIDEO,8);
        render(schoolAlbums,classVideos);
    }

    @UnSecure
    public static void pshow(Long id) {
        SchoolClazz schoolClazz = (SchoolClazz) renderArgs.get("schoolClass");
        SchoolAlbum schoolAlbum = SchoolAlbum.findById(id);
        List<ClassPhoto> classPhotos = ClassPhoto.findPhotoByAlbumId(schoolClazz.id, schoolAlbum.id);
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findClassId(schoolClazz.id, 10086);
        Page<PhotoReply> page = PhotoReply.findByPage(id, getPage(), getPageSize());
        renderArgs.put("schoolAlbums",schoolAlbums);
        renderArgs.put("classId", schoolAlbum.classId);
        render(classPhotos, schoolAlbum, page);
    }

    @Util
    public static void beforeSave(Model _object) {
        ClassPhoto object = (ClassPhoto)_object;
        File file = Http.Request.current().params.get("file", File.class);
        File cover = Http.Request.current().params.get("cover", File.class);
        logger.info(String.format("The classphoto file is %s",file));
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            String url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir,
                    new String[]{ImageSize.SSX, ImageSize.SZX, ImageSize.SZD, ImageSize.SKL, ImageSize.CZX});
            object.url = url;
            logger.info(String.format("The classphoto return url is %s",url));
        }
        if(file != null && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyVideo(file,file.getName(),uploadDir);
            logger.info(String.format("The classvideo return url is %s",object.url));
            logger.info(String.format("The classvideo cover is %s",file));
            if(cover != null){
                object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
                logger.info(String.format("The classvideo return cover is %s",object.cover));
            }
        }
        object.status = Status.UNVERIFIED;
        renderArgs.put("photoType", object.type);
    }

    @Util
    public static void beforeCreate(Model _object) {
        ClassPhoto object = (ClassPhoto)_object;
        UserBase userBase = (UserBase)renderArgs.get("user");
        object.user = userBase;
        File file = Http.Request.current().params.get("file", File.class);
        File cover = Http.Request.current().params.get("cover", File.class);
        logger.info(String.format("The classphoto file is %s",file));
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir,
                    new String[]{ImageSize.SSX, ImageSize.SZX, ImageSize.SZD, ImageSize.SKL, ImageSize.CZX});
            logger.info(String.format("The classphoto return url is %s",object.url));
        }
        if(object.type == SchoolPhotoType.VIDEO){
            if(file != null){
                object.url = FileUtils.copyVideo(file,file.getName(),uploadDir);
                logger.info(String.format("The classvideo return url is %s",object.url));
                logger.info(String.format("The classvideo cover is %s",file));
            }
            if(cover != null){
                object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
                logger.info(String.format("The classvideo return cover is %s",object.cover));
            }
        }
        renderArgs.put("photoType", object.type);
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        String photoType = params.get("where.type");
        if (photoType == null) photoType = session.get("classAdminPhotoType");
        if (photoType == null) photoType = SchoolPhotoType.PHOTO + "";
        if(queryParams.where == null || queryParams.where.equals("")) {
            queryParams.where = String.format("where status != %s",Status.DELETED);
        }
        else {queryParams.where += String.format("and status != %s",Status.DELETED);}
        renderArgs.put("photoType", Integer.parseInt(photoType));
        session.put("classAdminPhotoType", photoType);
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

    public static void delete(Long id){
        ClassPhoto object = ClassPhoto.findById(id);
        notFoundIfNull(object);
        object.status = Status.DELETED;
        object.save();
        redirect("/ClassPhotos/list");
    }

    public static void setRecommend(Long [] rid,Integer ablumId, Long classId, Long xxId){
        String error = "";
        if(rid != null){
            for (long id : rid){
                ClassPhoto classPhoto = ClassPhoto.findById(id);
                classPhoto.recommend = com.arj.ziguiw.common.Boolean.YES;
                classPhoto.save();
                error = "设置成功！";
                flash.put("error",error);
            }
        } else {
            error = "请选择图片!";
            flash.put("error",error);
        }
        redirect("/ClassPhotos/list?where.type=" + SchoolPhotoType.PHOTO + "&where.album.id=" + ablumId
        + "&where.classId=" + classId + "&classId=" + classId + "&xxId=" + xxId);
    }

    public static void rlist(Long classId, String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = "";
        condition = String.format("type = %s and status != %s and recommend = %s and classId = %s",
                SchoolPhotoType.PHOTO, Status.DELETED, Boolean.YES,classId);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    public static void cancel(long id){
        ClassPhoto classPhoto = ClassPhoto.findById(id);
        classPhoto.recommend = Boolean.NO;
        classPhoto.save();
        redirect("/ClassPhotos/rlist?classId=" + classPhoto.classId);
    }

    public static void setCover(Long photoId){
        ClassPhoto classPhoto = ClassPhoto.findById(photoId);
        String error = "";
        if(classPhoto.album != null){
            SchoolAlbum schoolAlbum = SchoolAlbum.findById(classPhoto.album.id);
            schoolAlbum.coverUrl = classPhoto.url;
            schoolAlbum.save();
            error = "设置成功！";
            flash.put("error",error);
        }else {
            error = "设置失败!";
            flash.put("error",error);
        }
        redirect("/ClassPhotos/list?where.type=" + SchoolPhotoType.PHOTO + "&where.album.id=" + classPhoto.album.id
                + "&where.classId=" + classPhoto.classId + "&classId=" + classPhoto.classId + "&xxId=" + classPhoto.xxId);
    }
}
