package controllers;

import models.Forum;
import models.Recommend;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-22
 * Time: 下午3:19
 */
public class Forums extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                Forum forum = Forum.findById(id);
                if (forum == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = forum.title;
                recommend.area = recommendArea;
                recommend.description = forum.autoDescription;
                recommend.url = forum.firstImage;
                recommend.objectId = id + "";
                recommend.moduleName = forum.board.boardName;
                recommend.moduleId = forum.board.id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }

    public static void list( String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        String condition = String.format("parentid is null");
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }
}
