package controllers;

import models.Recommend;
import models.UserDiary;

/**
 * User: Liujy
 * Date: 13-2-21
 * Time: 下午3:12
 */
public class UserDiarys extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                UserDiary userDiary = UserDiary.findById(id);
                if (userDiary == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = userDiary.title;
                recommend.area = recommendArea;
                recommend.description = userDiary.description;
                recommend.url = userDiary.firstImage;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }
}
