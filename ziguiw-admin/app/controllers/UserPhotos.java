package controllers;

import models.Recommend;
import models.UserPhoto;

/**
 * User: Liujy
 * Date: 13-2-21
 * Time: 下午3:45
 */
public class UserPhotos extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                UserPhoto userPhoto = UserPhoto.findById(id);
                if (userPhoto == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = userPhoto.title;
                recommend.area = recommendArea;
                recommend.description = userPhoto.text;
                recommend.url = userPhoto.url;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }
}
