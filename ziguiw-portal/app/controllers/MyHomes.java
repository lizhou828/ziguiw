package controllers;

import com.arj.ziguiw.common.RecommendArea;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.With;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-15
 * Time: 下午2:35
 */
@With(SecureCAS.class)
public class MyHomes extends Application {

    private static final Log log = LogFactory.getLog(MyHomes.class);

    @UnSecure
    public static void list(Long userId, Long userid){
        if (userId == null) userId = userid;
        log.info(String.format("the userid = %s and userId = %s",userid,userId));
        UserBase userBase = UserBase.findById(userId);
        List<Long> friendId = UserFriend.findFriendIdByUserId(userId);
        Page<UserTrends> friendTrends = new Page<UserTrends>();
        List<Resource> resources = null;
        if(friendId != null && friendId.size() != 0){
           friendTrends = UserTrends.pageByFriendId(friendId, getPage(), getPageSize());
           resources = Resource.findByFriendId(friendId);
        }

        UserBase user = (UserBase) renderArgs.get("user");
        if(user != null && user.id != userBase.id){
            if(userBase.clickCount == null){
                userBase.clickCount = 1;
                userBase.save();
            }else {
                userBase.clickCount = userBase.clickCount + 1;
                userBase.save();
            }
            Boolean flag = true;
            List<FriendVisit> list = FriendVisit.findByToUserId(userBase.id);
              for (FriendVisit _friendVisit : list){
                  if(_friendVisit.fromUser.id == user.id){
                      flag = false;
                      _friendVisit.createTime = new Date();
                      _friendVisit.save();
                  }
              }
            if(flag){
                FriendVisit friendVisit1 = new FriendVisit();
                friendVisit1.fromUser = user;
                friendVisit1.toUser = userBase;
                friendVisit1.createTime = new Date();
                friendVisit1.create();
            }
        }

        List<ResourceCollect> resourceCollects = ResourceCollect.findByUserId(userBase.id);

        SchoolPhoto schoolPhoto = SchoolPhoto.findByTime();

        List<SchoolNew> schoolNews = SchoolNew.findByTime(9);

        Page<UserTrends> myTrends = UserTrends.pageByUserId(userBase.id, getPage(), getPageSize());

        List<FriendVisit> friendVisits = FriendVisit.findByToUserId(userBase.id);
        //心情最新5条
        List<UserMood> userMoods = UserMood.findByUserId(userId,1,5).data;
        //最新评论5条
        List<UserMessage> userMessages = UserMessage.findParentUserMessageByUserId(userId,1,5).data;
        //三条日记
        List<UserDiary> userDiaries = UserDiary.findByUserId(userId,1,3).data;
        //三个相册
        List<PhotoAlbum> photoAlbums = PhotoAlbum.findByUserId(userId,1,3).data;
        render(userMoods,userMessages,userDiaries,photoAlbums,userBase,friendTrends,friendVisits,myTrends,schoolPhoto,schoolNews,resources,resourceCollects);
    }

    public static void goMyHome(){
        String userName = (String) DsisSecurity.connected();
        UserBase userBase =  UserBase.find("byNickName", userName).first();
        list(userBase.id, userBase.id);
    }

    @UnSecure
    public static void index(){
        List<Recommend> myhome_index_tuijianuser = Recommend.findByArea(RecommendArea.MYHOME_INDEX_TUIJIANUSER);
        List<Recommend> myhome_index_rijijingxuantop = Recommend.findByArea(RecommendArea.MYHOME_INDEX_RIJIJINGXUANTOP);
        List<Recommend> myhome_index_rijijingxuanlist = Recommend.findByArea(RecommendArea.MYHOME_INDEX_RIJIJINGXUANLIST);
        List<Recommend> myhome_index_zhaopinjingxuan = Recommend.findByArea(RecommendArea.MYHOME_INDEX_ZHAOPINJINGXUAN);
        List<Recommend> myhome_index_dajiadouzaishuo = Recommend.findByArea(RecommendArea.MYHOME_INDEX_DAJIADOUZAISHUO);
        List<Recommend> myhome_index_jiayuanrenqi = Recommend.findByArea(RecommendArea.MYHOME_INDEX_JIAYUANRENQI);
        List<Long> list = new ArrayList<Long>();
        List<UserBase> userBases = null;
        for(Recommend r : myhome_index_jiayuanrenqi){
             list.add(Long.valueOf(r.objectId));
        }
        if(list.size() > 0){
            userBases = UserBase.findListId(list);
        }
//        List<UserMood> userMoods = new ArrayList<UserMood>();
//        for(Recommend recommend : myhome_index_dajiadouzaishuo){
//            userMoods.add((UserMood) UserMood.findById(new Long(recommend.objectId)));
//        }
        List<UserMood> userMoods = UserMood.find("from UserMood u where rownum < 6 order by u.createTime desc ").fetch(5);
        render(myhome_index_tuijianuser,
                myhome_index_rijijingxuantop,
                myhome_index_rijijingxuanlist,
                myhome_index_zhaopinjingxuan,
                myhome_index_jiayuanrenqi,
                userMoods, myhome_index_dajiadouzaishuo,userBases);
    }

    @UnSecure
    public static void resourcesList(long userId){
        UserBase userBase = UserBase.findById(userId);
        render(userBase);
    }

    public static void addResource(){
        UserBase userBase = (UserBase) renderArgs.get("user");
        render(userBase);
    }

    public static void myApplication(){
        UserBase userBase = (UserBase) renderArgs.get("user");
        render(userBase);
    }

    public static void games(long userId){
        UserBase userBase = UserBase.findById(userId);
        render(userBase);
    }
}
