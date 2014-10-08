package controllers;

import controllers.modules.cas.SecureCAS;
import models.Page;
import models.PointsHistory;
import models.UserBase;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-11
 * Time: 上午10:02
 */
@With(SecureCAS.class)
public class PointsHistorys extends Application{

        public static void list(long userId){
            UserBase userBase = UserBase.findById(userId);
            Page<PointsHistory> page = PointsHistory.findByuserId(userId ,getPage(),10);
            render(userBase,page);
        }
}
