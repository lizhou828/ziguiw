package controllers;

import models.ReplyForum;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-25
 * Time: 上午10:05
 */
public class ReplyForums extends Application {

    public static void list( String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        String condition = String.format("parentid is not null and state = 2");
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    public static void delete(Long id){
        ReplyForum replyForum = ReplyForum.findById(id);
        replyForum.state = 1;
        replyForum.save();
        redirect(request.controller + ".list");
    }
}
