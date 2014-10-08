package controllers;

import controllers.modules.cas.SecureCAS;
import models.ClassForum;
import models.ClassReply;
import models.UserBase;
import play.mvc.With;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-18
 * Time: 下午3:20
 */
@With(SecureCAS.class)
public class ClassReplys extends Application{


        public static void create(ClassReply object,String xxId,long classId){
            validation.valid(object);
            String userName = (String) renderArgs.get("username");
            object.user = UserBase.find("byNickName", userName).first();
            object.create();
            long forumId =  object.forumId;
            ClassForum classForum = ClassForum.findById(forumId);
            classForum.lastReplyTime = new Date();
            classForum.replyCount += 1;
            classForum.save();
            ClassForums.pshow(forumId,xxId,classId);
        }
}
