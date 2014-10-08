package controllers;

import models.Recommend;
import models.UserMood;

/**
 * User: Liujy
 * Date: 13-2-20
 * Time: 下午2:42
 */
public class UserMoods extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                UserMood userMood = UserMood.findById(id);
                if (userMood == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = userMood.text;
                recommend.area = recommendArea;
                recommend.objectId = userMood.id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }
}
