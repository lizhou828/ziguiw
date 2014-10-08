package controllers;

import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import models.Recommend;
import models.Resource;
import models.UserBase;
import play.db.Model;
import play.mvc.Util;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: licanhui
 * Date: 13-3-29
 * Time: 上午10:29
 */
public class Resources extends Application {

    @Util
    public static void beforeSave(Model object) {
        Integer state = new Integer(request.params.get("object.state"));
        if(state == Status.OK){
            Integer points = new Integer(request.params.get("points"));
            if(points > 0){
                Resource resource = (Resource) object;
                UserBase userBase = resource.user;
                Map<String,Object> message = PointType.newMessage(userBase.id,null,PointType.SHENHEZIYUAN,points,PointType.ADD_POINT);
                jedisPoolSource.rpush(Queues.USER_POINTS,message);
            }
        }
    }

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                Resource resource = Resource.findById(id);
                if (resource == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = resource.title;
                recommend.area = recommendArea;
                recommend.description = resource.description;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }

}
