package controllers;

import models.Recommend;
import models.UserBase;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-2-1
 * Time: P.M 4:08
 */
public class UserBases extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                UserBase userBase = UserBase.findById(id);
                if (userBase == null) continue;
                Recommend recommend = new Recommend();
                recommend.url = userBase.photo;
                recommend.title = userBase.nickName;
                recommend.area = recommendArea;
                recommend.objectId = userBase.id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }
}
