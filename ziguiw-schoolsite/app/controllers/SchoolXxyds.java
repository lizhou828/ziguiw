package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.SchoolXxydType;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.db.Model;
import play.mvc.Before;
import play.mvc.Http;
import play.mvc.Util;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-18
 * Time: 上午11:26
 */
@With(SecureCAS.class)
public class SchoolXxyds extends Application{
    private static final Log logger = LogFactory.getLog(ClassNews.class);

    @UnSecure
    public static void plist(Integer type){
        if(type == null){
            type = SchoolXxydType.COMPOSITION;
        }
        Page<SchoolXxyd> page = SchoolXxyd.findPage(getPage(),15 ,((School) renderArgs.get("school")).id , type);
        List<SchoolClazz> classes = SchoolClazz.findByXXId(((School) renderArgs.get("school")).xxId,70);
        render(page, type ,classes);
    }

    @UnSecure
    public static void pshow(Long  id ){
        SchoolXxyd schoolXxyd = SchoolXxyd.findById(id);
        if ( schoolXxyd.visitCount == null) { schoolXxyd.visitCount = 1;}
        else { schoolXxyd.visitCount += 1;}
        schoolXxyd.save();
        Integer type = schoolXxyd.type;
        List<SchoolClazz> classes = SchoolClazz.findByXXId(schoolXxyd.school.xxId,70);
        renderArgs.put("school", schoolXxyd.school);
        render(schoolXxyd,type,classes);
    }

    @UnSecure
    public static void pclist(Long classId, Integer type){
        if(type == null){
             type = SchoolXxydType.COMPOSITION;
        }
         Page<SchoolXxyd> page = SchoolXxyd.findPageByClassId(classId , ((School)(renderArgs.get("school"))).id , type ,getPage() , 15);
         List<ClassPhoto> classPhotos = ClassPhoto.findByType(classId, SchoolPhotoType.PHOTO,4);
        render(page,classPhotos,type);
    }

    @UnSecure
    public static void pcshow(Long id){
        SchoolXxyd schoolXxyd = SchoolXxyd.findById(id);
        if ( schoolXxyd.visitCount == null) { schoolXxyd.visitCount = 1;}
        else { schoolXxyd.visitCount += 1;}
        schoolXxyd.save();
        Integer type = schoolXxyd.type;
        if(type == null){
         type = SchoolXxydType.COMPOSITION;
        }
        List<ClassPhoto> classPhotos = ClassPhoto.findByType(schoolXxyd.clazz.id, SchoolPhotoType.PHOTO,4);
        renderArgs.put("school", School.find("byXxId", schoolXxyd.clazz.xxId).first());
        renderArgs.put("schoolClass", schoolXxyd.clazz);
        render(schoolXxyd,type,classPhotos);
    }

    @Before(only = {"delete","save"})
    public static void before(){
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String id = request.params.get("id");
        SchoolXxyd object = SchoolXxyd.findById(Long.valueOf(id));
        if(object.status == Status.OK){
            renderArgs.put("error","操作失败，新闻已审核!");
            render("SchoolXxyds/show.html",object,type);
        }
    }

    @Util
    public static void beforeCreate(Model object) {
        SchoolXxyd xxyd = (SchoolXxyd) object;
        File file = Http.Request.current().params.get("file", File.class);
        File video = Http.Request.current().params.get("videoUrl", File.class);
        UserBase userBase = (UserBase)renderArgs.get("user");
        xxyd.user = userBase;
        logger.info(String.format("The xxyd file is %s",file));
        logger.info(String.format("The xxyd video is %s",video));
        if (file != null) {
            xxyd.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSX , ImageSize.SKL});
            logger.info(String.format("The xxyd return url is %s",xxyd.url));
        }
        if(video != null){
            xxyd.videoUrl = FileUtils.copyVideo(video, video.getName() , uploadDir);
        }
        xxyd.content = FileUtils.replaceImgTag(xxyd.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeSave(Model object) {
        SchoolXxyd xxyd = (SchoolXxyd) object;
        File file = Http.Request.current().params.get("file", File.class);
        File video = Http.Request.current().params.get("videoUrl", File.class);
        logger.info(String.format("The xxyd file is %s",file));
        logger.info(String.format("The xxyd video is %s",video));
        if (file != null) {
            xxyd.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSX , ImageSize.SKL});
            logger.info(String.format("The xxyd return url is %s",xxyd.url));
        }
        if(video != null){
            xxyd.videoUrl = FileUtils.copyVideo(video, video.getName() ,uploadDir);
        }
        xxyd.status = Status.UNVERIFIED;
        xxyd.content = FileUtils.replaceImgTag(xxyd.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        String xxydClassId = params.get("classId");
        if (xxydClassId == null) {
            if (params.get("isSchool") != null) session.remove("xxydClassId");
            xxydClassId = session.get("xxydClassId");
        }
        if (xxydClassId == null) {
            queryParams.where = String.format("school.id = %s and status != %s",
                    ((School)(renderArgs.get("school"))).id,Status.DELETED);
        } else {
            queryParams.where = String.format("school.id = %s and clazz.id = %s  and status != %s",
                    ((School)(renderArgs.get("school"))).id, xxydClassId,Status.DELETED);
            session.put("xxydClassId", xxydClassId);
        }
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

    public static void delete(Long id){
        SchoolXxyd object = SchoolXxyd.findById(id);
        notFoundIfNull(object);
        object.status = Status.DELETED;
        object.save();
        redirect("/SchoolXxyds/list");
    }
}
