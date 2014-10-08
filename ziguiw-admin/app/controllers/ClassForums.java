package controllers;

import models.ClassForum;
import models.QueryParams;
import models.SchoolClazz;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-26
 * Time: 上午11:53
 */
public class ClassForums extends Application{

    public static void show(Long id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        ClassForum object = ClassForum.findById(id);
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
}
