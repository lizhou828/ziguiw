package controllers;

import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import play.db.Model;
import play.mvc.Before;
import play.mvc.Util;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-18
 * Time: 上午11:33
 */
@With(SecureCAS.class)
public class ClassForums extends Application{

        @UnSecure
        public static void plist(long classId){
            Page<ClassForum> page = ClassForum.findPage(classId,getPage(),8);
            render(page);
        }

        @UnSecure
        public static void pshow(long forumId,String xxId,Long classId){
            SchoolClazz schoolClazz = (SchoolClazz)renderArgs.get("schoolClass");
            ClassForum classForum =  ClassForum.findById(forumId);
            ClassForum classHotForum = ClassForum.findByHot(schoolClazz.id);
            if(classHotForum == null){
                classHotForum = new ClassForum();
            }
            Page<ClassReply> page =  ClassReply.findPage(forumId,getPage(),getPageSize());
            renderArgs.put("classId", classForum.classId);
            render(classForum,page,forumId,classHotForum);
        }

         public static void pblank(String xxId,long classId){
             render(xxId,classId);
         }

         public static void pcreate(ClassForum object, String xxId, long classId){
             validation.valid(object);
             String userName = (String) renderArgs.get("username");
             object.create();
             plist(classId);
         }

        @Util
        public static void beforeQuery(QueryParams queryParams) {
           String forumsClassId = params.get("classId");
           if(forumsClassId == null){
               forumsClassId = session.get("forumsClassId");
           }else{
               session.remove("forumsClassId");
               session.put("forumsClassId",forumsClassId);
           }
           queryParams.where = String.format("xxId = %s and classId = %s and status != %s",
                   ((School)(renderArgs.get("school"))).xxId, forumsClassId,Status.DELETED);
            if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
            if (queryParams.order == null) queryParams.order = "DESC";
        }

        @Util
        public static void beforeSave(Model _object) {
            ClassForum object = (ClassForum)_object;
            object.state = Status.UNVERIFIED;
        }

        public static void delete(Long id){
            ClassForum object = ClassForum.findById(id);
            notFoundIfNull(object);
            object.state = Status.DELETED;
            object.save();
            redirect("/ClassForums/list");
        }

    @Before(only = {"delete","save"})
    public static void before(){
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String id = request.params.get("id");
        ClassForum object = ClassForum.findById(Long.valueOf(id));
        if(object.state == Status.OK){
            renderArgs.put("error","操作失败，帖子已审核!");
            render("ClassForums/show.html",object,type);
        }
    }
}
