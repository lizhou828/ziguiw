package controllers;

import com.arj.ziguiw.common.Status;
import models.School;
import models.SchoolAlbum;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-26
 * Time: 上午11:36
 */
public class SchoolAlbums extends Application {

    public static void list(String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = "";
            condition = String.format("school.id = %s and status != %s and classId is null",
                    ((School)(renderArgs.get("school"))).id,Status.DELETED);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }


    public static void clist(Long classId, String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = "";
        condition = String.format("school.id = %s and status != %s and classId = %s",
                ((School)(renderArgs.get("school"))).id,Status.DELETED,classId);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    public static void create(SchoolAlbum object){
        String error = "";
        if(object.name == null || "".equals(object.name) || object.describe == null || "".equals(object.describe)){
             error = "相册名字或描述不能为空";
             flash.put("error",error);
             render("SchoolAlbums/blank.html", object);
        }else {
             object.create();
             redirect("/SchoolAlbums/list");
        }
    }

    public static void cblank(Long classId, String xxId){
        render(classId, xxId);
    }

    public static void ccreate(SchoolAlbum object){
        String error = "";
        if(object.name == null || "".equals(object.name) || object.describe == null || "".equals(object.describe)){
            error = "相册名字或描述不能为空";
            flash.put("error",error);
            render("SchoolAlbums/blank.html", object);
        }else {
            object.create();
            redirect("/SchoolAlbums/clist?classId=" + object.classId);
        }
    }
}
