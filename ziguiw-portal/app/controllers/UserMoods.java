package controllers;

import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserTrendsType;
import controllers.modules.cas.Security;
import controllers.modules.cas.UnSecure;
import models.*;
import play.mvc.With;

import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-12
 * Time: 下午4:06
 */
@With(Security.class)
public class UserMoods extends Application {

    @UnSecure
    public static void list(Long userId){
        UserBase userBase = UserBase.findById(userId);
        Page<UserMood> page = UserMood.findByUserId(userId,getPage(),getPageSize());
        SchoolPhoto schoolPhoto = SchoolPhoto.findByTime();
        List<SchoolNew> schoolNews = SchoolNew.findByTime(9);
        render(userBase,page,schoolPhoto,schoolNews);
    }

    public static void create(Long userId, String text){
        if(text != null && text.trim().length() > 0){
            UserMood userMood = new UserMood();
            userMood.user = UserBase.findById(userId);
            userMood.text = text;
            userMood.create();
            Map<String,Object> map = UserTrendsType.newMessage(userId, UserTrendsType.CREATE, text, UserTrendsType.MOOD, userMood.id);
            jedisPoolSource.rpush(Queues.USER_TRENDS, map);
        }
        list(userId);

    }

    public static void delete(Long userMoodId){
        UserMood userMood = UserMood.findById(userMoodId);
        userMood.status = Status.DELETED;
        userMood.save();
        list(userMood.user.id);
    }
}
