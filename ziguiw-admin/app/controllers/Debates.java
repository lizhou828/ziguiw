package controllers;

import models.Debate;
import models.Recommend;

/**
 * User: Liujy
 * Date: 13-2-17
 * Time: 上午9:26
 */
public class Debates extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                Debate debate = Debate.findById(id);
                if (debate == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = debate.title;
                recommend.area = recommendArea;
                recommend.description = debate.description;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }

}
