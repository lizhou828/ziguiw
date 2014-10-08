package controllers;

import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import controllers.modules.cas.SecureCAS;
import models.Sign;
import models.UserBase;
import play.mvc.With;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-15
 * Time: 下午3:52
 */
@With(SecureCAS.class)
public class Signs extends Application{

        public static void sign(){
            UserBase userBase = (UserBase) renderArgs.get("user");
            if(userBase != null){
                Sign sign = Sign.findByUserId(userBase.id);
                if(sign == null){
                    Sign _sign = new Sign();
                    _sign.signTime = new Date();
                    _sign.user = userBase;
                    _sign.create();
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put(PointType.POINT_MESSAGEKEY_USERID,userBase.id);
                    map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.SIGN);
                    map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.SIGN));
                    map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
                    jedisPoolSource.rpush(Queues.USER_POINTS, map);
                    renderText("OK");
                }else {
                    if(getTodayStart() < sign.signTime.getTime() && sign.signTime.getTime() < getTodayEnd()){
                        renderText("NO");
                    }else {
                        sign.signTime = new Date();
                        sign.save();
                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put(PointType.POINT_MESSAGEKEY_USERID,userBase.id);
                        map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.SIGN);
                        map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.SIGN));
                        map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
                        jedisPoolSource.rpush(Queues.USER_POINTS, map);
                        renderText("OK");
                    }
                }
            }
            renderJSON("LOGIN");
        }
}
