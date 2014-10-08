package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.utils.FileUtils;
import models.*;
import play.data.binding.Binder;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Util;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 下午4:18
 */
public class ClassNews extends Application{


    public static void save(Long id ,File file){
        ClassNew object = ClassNew.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("ClassNews/blank.html", object);
        }
        if(file != null){
            object.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL ,  ImageSize.SKL});
        }
        object.content = FileUtils.replaceImgTag(object.content ,uploadDir ,(String)renderArgs.get("ctx"));
        object.save();
        flash.success("保存成功!");
        String saveUrl = String.format("/%s?id=%s", "ClassNews/show", object.id);
        String url =  String.format("/%s", "ClassNews/list");
        if (params.get("_save") != null) {
            redirect(url);
        }
        redirect(saveUrl);
    }

    public static void create(ClassNew object ,File file){
        validation.valid(object);
        if(file != null){
            object.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL ,  ImageSize.SKL});
        }
        object.content = FileUtils.replaceImgTag(object.content ,uploadDir ,(String)renderArgs.get("ctx"));
        object.create();
        flash.success("创建成功!");
        String url1 = String.format("/%s?id=%s", "ClassNews/show", object.id);
        String url2 =  String.format("/%s", "ClassNews/list");
        String url3 =  String.format("/%s", "ClassNews/blank");
        if (params.get("_save") != null) {
            redirect(url2);
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(url3);
        }
        redirect(url1);
    }

    public static void show(Long id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        ClassNew object = ClassNew.findById(id);
        List<SchoolClazz> schoolClazz = SchoolClazz.findByXXId(object.xxId , 70);
        notFoundIfNull(object);
        try {
            render(type, object ,schoolClazz);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                ClassNew newse = ClassNew.findById(id);
                if (newse == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = newse.title;
                recommend.area = recommendArea;
                recommend.description = newse.describe;
                recommend.url = newse.url;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }

    public static void deletePhoto(Long id) throws Exception {
        if(id != null){
            ClassNew classNew = ClassNew.findById(id);
            classNew.url = null;
            classNew.save();
        }
        show(id);
    }
}
