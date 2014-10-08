package controllers;

import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import models.Page;
import models.ResourceCollect;
import models.UserBase;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-12
 * Time: 下午3:08
 */
@With(SecureCAS.class)
public class ResourceCollects extends Application{

        public static void list(long userId){
            UserBase userBase = UserBase.findById(userId);
            Page<ResourceCollect> page = ResourceCollect.findByUserId(userId,getPage(),getPageSize());
            render(userBase, page);
        }

        public static void delete(long collectId){
            UserBase userBase = (UserBase) renderArgs.get("user");
            ResourceCollect resourceCollect = ResourceCollect.findById(collectId);
            resourceCollect.status = Status.DELETED;
            resourceCollect.save();
            list(userBase.id);
        }

        public static void search(Long userId, String condition){
            UserBase userBase = UserBase.findById(userId);
            Page<ResourceCollect> page = ResourceCollect.findSearchTitle(userId,condition,getPage(),getPageSize());
            render(userBase,page,condition);
        }

         public static void sdelete(long collectId, String condition){
            UserBase userBase = (UserBase) renderArgs.get("user");
            ResourceCollect resourceCollect = ResourceCollect.findById(collectId);
            resourceCollect.status = Status.DELETED;
            resourceCollect.save();
            search(userBase.id,condition);
        }
}

