package controllers;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Before;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 下午3:35
 */
@With(SecureCAS.class)
public class ClassNews extends Application{
    private static final Log logger = LogFactory .getLog(ClassNews.class);


    @UnSecure
    public static void plist(Integer type){
        if(type == null){
            type = ClassNewType.NEWS;
        }
        SchoolClazz schoolClazz = (SchoolClazz)renderArgs.get("schoolClass");
        School school = (School)renderArgs.get("school");
        long classId = schoolClazz.id;
        Page<ClassNew> page = ClassNew.findAllNews(classId,getPage(),getPageSize(),type);
        List<ClassPhoto> classPhotos = ClassPhoto.findByType(classId, SchoolPhotoType.PHOTO,4);
        List<SchoolXxyd> schoolXxyds = SchoolXxyd.findByClassId(classId,school.id,8);
        render(page,classPhotos,schoolClazz,type,schoolXxyds, classId);
    }

    @UnSecure
    public static void pshow(Long id){
        ClassNew classNew = ClassNew.findNewById(id);
        Long classId = classNew.classId;
        SchoolClazz schoolClass = SchoolClazz.findById(classId);
        if ( classNew.visitCount == null) { classNew.visitCount = 1;}
        else { classNew.visitCount += 1;}
        classNew.save();
        String xxId = classNew.xxId;
        if (xxId != null) {
            School school = School.find("byXxId", xxId).first();
            SchoolExtend schoolExtend = SchoolExtend.findBySchoolId(school.id);
            List<SchoolClazz> clazzs = SchoolClazz.findByXXId(xxId,70);
            renderArgs.put("classes",clazzs);
            renderArgs.put("xxId", xxId);
            renderArgs.put("userBase", UserBase.find("from UserBase where type = ? and foreignKey = ?",
                    UserBaseType.SCHOOL, school.id).first());
            renderArgs.put("school", school);
            renderArgs.put("schoolExt", schoolExtend);
        }
        List<ClassPhoto> classPhotos = ClassPhoto.findByType(classId, SchoolPhotoType.PHOTO,4);
        List<ClassNew> classNews;
        if(classNew.type == ClassNewType.NEWS){
              classNews = ClassNew.findByType(classId,ClassNewType.BULLETIN,8);
        }else{
              classNews = ClassNew.findByType(classId,ClassNewType.NEWS,8);
        }
        renderArgs.put("classId", classNew.classId);
        render(schoolClass,classNew,classNews,classPhotos);
    }

    @Before(only = {"blank","list","create","show"})
    public static void before(){
         String type = request.params.get("ClassNewstype");
         if(type != null){
             int ClassNewstype = Integer.parseInt(type);
             renderArgs.put("ClassNewstype",ClassNewstype);
         }
    }

    @Before(only = {"delete","save"})
    public static void before1(){
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String id = request.params.get("id");
        ClassNew object = ClassNew.findById(Long.valueOf(id));
        if(object.state == Status.OK){
            renderArgs.put("error","操作失败，新闻已审核!");
            render("ClassNews/show.html",object,type);
        }
    }

    public static void list( String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = String.format("classId = %s and status != %s",
                ((SchoolClazz)(renderArgs.get("schoolClass"))).id,Status.DELETED);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    public static void save(Long id, String xxId, Long classId ,File file){
        ClassNew object = ClassNew.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("ClassNews/show.html", object);
        }
        logger.info(String.format("The classnews file is %s",file));
        if(file != null){
            String url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir, new String []{ImageSize.SSL , ImageSize.SKL});
            object.url = url;
            logger.info(String.format("The classnews return url is %s",url));

        }
        object.state = Status.UNVERIFIED;
        object.content = FileUtils.replaceImgTag(object.content ,uploadDir ,(String)renderArgs.get("ctx"));
        object.save();
        flash.success("保存成功!");
        String saveUrl = String.format("/%s?id=%s&xxId=%s&classId=%s", "ClassNews/show", object.id, xxId, classId);
        String url =  String.format("/%s?&xxId=%s&classId=%s", "ClassNews/list",  xxId, classId );
        if (params.get("_save") != null) {
            redirect(url);
        }
        redirect(saveUrl);
    }

    public static void blank(long xxId , long classId){
        render(xxId,classId);
    }

    public static void create(ClassNew object ,String xxId , Long classId ,File file){
        ObjectType type = ObjectType.get(getControllerClass());
        object.classId = classId;
        UserBase userBase = (UserBase)renderArgs.get("user");
        object.user = userBase;
        logger.info(String.format("The classnews file is %s",file));
        if(file != null){
            String url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir, new String []{ImageSize.SSL , ImageSize.SKL});
            object.url = url;
            logger.info(String.format("The classnews return url is %s",url));
        }
        object.content = FileUtils.replaceImgTag(object.content ,uploadDir ,(String)renderArgs.get("ctx"));
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render("ClassNews/blank.html", object);
        }
        String error;
        if(object.type == null){
              error = "请选择类型";
              flash.put("error",error);
              render("/ClassNews/blank.html",object,xxId,classId,type);
        }
        if(object.reprinted == null){
            error = "请选择是否转载";
            flash.put("error",error);
            render("/ClassNews/blank.html",object,xxId,classId,type);
        }
        object.create();
        flash.success("创建成功!");
        String url1 = String.format("/%s?id=%s&xxId=%s&classId=%s", "ClassNews/show", object.id, xxId, classId);
        String url2 =  String.format("/%s?&xxId=%s&classId=%s", "ClassNews/list",  xxId, classId );
        String url3 =  String.format("/%s?xxId=%s&classId=%s", "ClassNews/blank", xxId, classId );
        if (params.get("_save") != null) {
            redirect(url2);
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(url3);
        }
        redirect(url1);
    }

        public static void delete(Long id){
            ClassNew object = ClassNew.findById(id);
            notFoundIfNull(object);
            object.state = Status.DELETED;
            object.save();
            redirect("/ClassNews/list");
        }


}
