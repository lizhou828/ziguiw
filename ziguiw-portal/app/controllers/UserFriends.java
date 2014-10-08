package controllers;

import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Page;
import models.UserBase;
import models.UserFriend;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.With;

import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-14
 * Time: 下午2:37
 */
@With(SecureCAS.class)
public class UserFriends extends Application {
    private static final Log logger = LogFactory.getLog(UserFriend.class);

    @UnSecure
    public static void list(Long userId){
        UserBase userBase = UserBase.findById(userId);
        Page<UserFriend> page = UserFriend.pageByUserId(userId,getPage(),getPageSize());
        render(page,userBase);
    }

    public static void addFriend(Long friendId,String condition,Long userId){
        UserBase userBase = (UserBase) renderArgs.get("user");
        UserBase friend = UserBase.findById(friendId);
        UserFriend userFriend = new UserFriend();
        userFriend.user = userBase;
        userFriend.friendUser = friend;
        userFriend.create();
        search(condition,userId, 1);
    }

    public static void removeFriend(Long friendId,String condition,Long userId){
        UserFriend userFriend = UserFriend.find("select u from UserFriend u where u.friendUser.id = ? and u.user.id = ?",friendId,userId)
                               .first();
        userFriend.delete();
        search(condition,userId, 0);
    }

    @UnSecure
    public static void search(String condition,Long userId, Integer flag){
        UserBase userBase = UserBase.findById(userId);
        Page<UserBase> page = UserBase.SearchDisplayName(condition, getPage(), 10);
        List<Long> userFriends = UserFriend.findFriendIdByUserId(userId);
        logger.info(String.format("the user friend search result is %s",page.data));
        logger.info(String.format("the user friend search condition is %s",condition));
        render(page,userFriends,userId,condition,userBase, flag);
    }

    public static void ajaxAdd(Long userId){
        UserBase userBase = UserBase.findById(userId);
        UserBase user = (UserBase) renderArgs.get("user");
        UserFriend userFriend = new UserFriend();
        userFriend.user = user;
        userFriend.friendUser = userBase;
        userFriend.create();
        renderText("ok");
    }
}
