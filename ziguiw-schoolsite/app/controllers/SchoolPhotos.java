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
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-11
 * Time: P.M 5:22
 */
@With(SecureCAS.class)
public class SchoolPhotos extends Application {

    private static final Log logger = LogFactory.getLog(SchoolPhotos.class);

    @UnSecure
    public static void pshow() {
        List<SchoolPhoto> schoolPhotos = SchoolPhoto.findSchoolPhotos(((School) renderArgs.get("school")).id, 100,1,null);
        render(schoolPhotos);
    }

    @UnSecure
    public static void plist(int type){
        if(type == SchoolPhotoType.PHOTO){
           Page<SchoolAlbum> page = SchoolAlbum.findByPage(((School) renderArgs.get("school")).id, getPage(), 8);
            render(page, type);
        }else {
           Page<SchoolPhoto> page = SchoolPhoto.findPhotoPage
                    (getPage(),8,((School) renderArgs.get("school")).id, type);
            render(page, type);
        }
    }

    @UnSecure
    public static void index(){
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findSchoolId(((School) renderArgs.get("school")).id, 100);
        List<SchoolPhoto> schoolVideos = SchoolPhoto.findSchoolPhotos(((School) renderArgs.get("school")).id, 8, SchoolPhotoType.VIDEO,null);
        render(schoolAlbums,schoolVideos);
    }

    @UnSecure
    public static void pshowVideo(Long id){
        SchoolPhoto video = SchoolPhoto.findById(id);
        List<SchoolPhoto> schoolPhotos = SchoolPhoto.findSchoolPhotos(((School)renderArgs.get("school")).id, 4, video.type, null);
        render(video,schoolPhotos);
    }

    @UnSecure
    public static void pshowPhoto(Long id){
        SchoolAlbum schoolAlbum = SchoolAlbum.findById(id);
        List<SchoolAlbum> schoolAlbums = SchoolAlbum.findSchoolId(schoolAlbum.school.id, 10086);
        List<SchoolPhoto> schoolPhotos = SchoolPhoto.findSchoolPhotos(schoolAlbum.school.id, 10000,SchoolPhotoType.PHOTO,schoolAlbum.id);
        Page<PhotoReply> page = PhotoReply.findByPage(id, getPage(), getPageSize());
        School school = School.findById(schoolAlbum.school.id);
        SchoolExtend schoolExtend = SchoolExtend.findBySchoolId(school.id);
        renderArgs.put("school", school);
        renderArgs.put("schoolExt", schoolExtend);
        renderArgs.put("xxId", school.xxId);
        render(schoolPhotos,schoolAlbums,schoolAlbum,page);
    }

    public static void create(SchoolPhoto object, File file ,File cover) throws Exception {
        logger.info(String.format("The schoolvideo file is %s",file));
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,
                    new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL});
            logger.info(String.format("The schoolphoto return url is %s",object.url));
        }
        if(file != null && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyVideo(file,file.getName(),uploadDir);
            logger.info(String.format("The schoolvideo return url is %s",object.url));
            logger.info(String.format("The schoolvideo cover is %s",file));
            if(cover != null)
            object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
            logger.info(String.format("The schoolvideo return cover is %s",object.cover));
        }
        UserBase userBase = (UserBase)renderArgs.get("user");
        object.user = userBase;
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render("SchoolPhotos/blank.html", object);
        }
        object.create();
        flash.success("创建成功!");
        //show(object.schoolId + "");
        if (params.get("_save") != null) {
            if(object.type == SchoolPhotoType.PHOTO){
                redirect("/SchoolPhotos/list?photoType=" + object.type + "&ablumId=" + object.album.id);
            } else {
                redirect("/SchoolPhotos/list?photoType=" + object.type);
            }
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank",object.type);
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void save(Long id ,File file ,File cover){
        SchoolPhoto object = SchoolPhoto.findById(id);
        notFoundIfNull(object);
        if(object.status == Status.OK){
            renderArgs.put("error","操作失败，照片/视频已审核!");
            render("SchoolPhotos/show.html",object);
        }
        Binder.bindBean(params.getRootParamNode(), "object", object);
        logger.info(String.format("The schoolphoto file is %s",file));
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,
                  new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL});
            logger.info(String.format("The schoolphoto return url is %s",object.url));
        }
        if(file != null  && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyVideo(file,file.getName(),uploadDir);
            logger.info(String.format("The schoolvideo return url is %s",object.url));
            logger.info(String.format("The schoolvideo cover is %s",file));
        }
        if(cover != null && object.type == SchoolPhotoType.VIDEO){
            object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
            logger.info(String.format("The schoolvideo return cover is %s",object.cover));
        }
        object.status = Status.UNVERIFIED;
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("ClassNews/SchoolPhotos.html", object);
        }
        object.save();
        flash.success("保存成功!");
        if (params.get("_save") != null) {
            if(object.type == SchoolPhotoType.PHOTO){
                redirect("/SchoolPhotos/list?photoType=" + object.type + "&ablumId=" + object.album.id);
            } else {
                redirect("/SchoolPhotos/list?photoType=" + object.type);
            }
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void list( Integer photoType ,Long ablumId, String search, String searchFields, String orderBy, String order) {
        if(photoType == null){
            photoType = Integer.valueOf(session.get("schoolphoto.list.photoType"));
            if(photoType == null){
                photoType = SchoolPhotoType.PHOTO;
            }
        }
        if(photoType == SchoolPhotoType.PHOTO && ablumId == null){
            ablumId = Long.valueOf(session.get("schoolphoto.list.ablumId"));
        }
        session.put("schoolphoto.list.photoType",photoType);
        session.put("schoolphoto.list.ablumId",ablumId);
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = "";
        if(photoType == SchoolPhotoType.PHOTO){
            condition = String.format("school.id = %s and type = %s and status != %s and album.id = %s",
                    ((School)(renderArgs.get("school"))).id,photoType,Status.DELETED,ablumId);
        }else {
            condition = String.format("school.id = %s and type = %s and status != %s",
                ((School)(renderArgs.get("school"))).id,photoType,Status.DELETED);
        }
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order,photoType);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order,photoType);
        }
    }

    public static void blank(Integer photoType){
        if(photoType == null){
            photoType = SchoolPhotoType.PHOTO;
        }
        List<SchoolAlbum> schoolAlbums = null;
        if(photoType == SchoolPhotoType.PHOTO){
            schoolAlbums = SchoolAlbum.findSchoolId(((School)(renderArgs.get("school"))).id,10086);
        }
        render(photoType, schoolAlbums);
    }

    public static void delete(Long id, Integer photoType){
        SchoolPhoto object = SchoolPhoto.findById(id);
        notFoundIfNull(object);
        if(object.status == Status.OK){
            renderArgs.put("error","操作失败，照片/视频已审核!");
            render("SchoolPhotos/show.html",object);
        }
        object.status = Status.DELETED;
        object.save();
        redirect("/SchoolPhotos/list?photoType=" + photoType);
    }

    public static void show(Long id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        SchoolPhoto object = SchoolPhoto.findById(id);
        notFoundIfNull(object);
        List<SchoolAlbum> schoolAlbums = null;
        if(object.type == SchoolPhotoType.PHOTO){
            schoolAlbums = SchoolAlbum.findSchoolId(object.school.id, 10086);
        }
        try {
            render(type, object,schoolAlbums);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }

    public static void setCover(Long photoId){
        SchoolPhoto schoolPhoto = SchoolPhoto.findById(photoId);
        String error = "";
        if(schoolPhoto.album != null){
            SchoolAlbum schoolAlbum = SchoolAlbum.findById(schoolPhoto.album.id);
            schoolAlbum.coverUrl = schoolPhoto.url;
            schoolAlbum.save();
            error = "设置成功！";
            flash.put("error",error);
        }else {
            error = "设置失败!";
            flash.put("error",error);
        }
            redirect("/SchoolPhotos/list?photoType=" + schoolPhoto.type + "&ablumId=" + schoolPhoto.album.id);
    }

    public static void setRecommend(Long [] rid,Integer ablumId){
            String error = "";
            if(rid != null){
                for (long id : rid){
                    SchoolPhoto schoolPhoto = SchoolPhoto.findById(id);
                    schoolPhoto.recommend = Boolean.YES;
                    schoolPhoto.save();
                    error = "设置成功！";
                    flash.put("error",error);
                }
            } else {
                error = "请选择图片!";
                flash.put("error",error);
            }
        redirect("/SchoolPhotos/list?photoType=" + SchoolPhotoType.PHOTO + "&ablumId=" + ablumId);
    }


    public static void rlist(String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = "";
        condition = String.format("school.id = %s and type = %s and status != %s and recommend = %s",
                ((School)(renderArgs.get("school"))).id,SchoolPhotoType.PHOTO, Status.DELETED, Boolean.YES);
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
        SchoolPhoto schoolPhoto = SchoolPhoto.findById(id);
        schoolPhoto.recommend = Boolean.NO;
        schoolPhoto.save();
        redirect("/SchoolPhotos/rlist");
    }
}
