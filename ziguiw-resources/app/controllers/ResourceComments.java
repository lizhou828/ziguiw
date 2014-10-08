package controllers;

import controllers.modules.cas.SecureCAS;
import models.Resource;
import models.ResourceComment;
import models.UserBase;
import play.mvc.With;

/**
 * User: Liujy
 * Date: 13-5-21
 * Time: 上午10:58
 */
@With(SecureCAS.class)
public class ResourceComments extends Application {

    public static void create(String content,Long resourceId,Boolean flag){
        ResourceComment resourceComment = new ResourceComment();
        resourceComment.content = content;
        resourceComment.resource = Resource.findById(resourceId);
        resourceComment.userBase = (UserBase)renderArgs.get("user");
        resourceComment.create();
        Resources.show(resourceId,flag);
    }
}
