package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.utils.FileUtils;
import models.SchoolPhoto;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Scope;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 下午2:45
 */
public class SchoolPhotos extends Application{

    public static void create(SchoolPhoto object, File file ,File cover) throws Exception {
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render(request.controller.replace(".", "/") + "/blank.html", object);
        }
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,
                    new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL});
        }
        if(file != null && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyImage(file,file.getName(),uploadDir);
            if(cover != null){
                object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
            }
        }
        object.create();
        flash.success("创建成功!");
        //show(object.schoolId + "");
        if (params.get("_save") != null) {
            redirect(request.controller + ".list",object.type);
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank",object.type);
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void save(Long id ,File file ,File cover){
        SchoolPhoto object = SchoolPhoto.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("SchoolPhotos/blank.html", object);
        }
        if(file != null && object.type == SchoolPhotoType.PHOTO){
            object.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir,
                    new String[]{ImageSize.SSX, ImageSize.SZX, ImageSize.SZD,ImageSize.SKL});
        }
        if(file != null && object.type == SchoolPhotoType.VIDEO){
            object.url = FileUtils.copyImage(file,file.getName(),uploadDir);
        }
        if(cover != null && object.type == SchoolPhotoType.VIDEO){
            object.cover = FileUtils.copyAndCompressImage(cover,cover.getName(),uploadDir,new String[]{ImageSize.TC,ImageSize.SKL});
        }
        object.save();
        flash.success("保存成功!");
        if (params.get("_save") != null) {
            redirect(request.controller + ".list",object.type);
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void list( Integer photoType ,String search, String searchFields, String orderBy, String order) {
        if(photoType == null){
            String _photoType = Scope.Session.current().get("school.photoType");
            photoType = _photoType == null ? null : Integer.valueOf(_photoType);
        }
        if (photoType == null) {
            photoType = SchoolPhotoType.PHOTO;
        }
        Scope.Session.current().put("school.photoType", photoType);
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = String.format("type = %s",photoType);
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
           render(photoType);
    }
}
