package controllers;

import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Page;
import models.UserBase;
import models.UserMessage;
import play.mvc.With;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-13
 * Time: 上午10:34
 */
@With(SecureCAS.class)
public class UserMessages extends Application{

    @UnSecure
    public static void list(Long userId){
        Page<UserMessage> page = UserMessage.findParentUserMessageByUserId(userId, getPage(), 10);
        Map<UserMessage,List<UserMessage>> map = new HashMap<UserMessage,List<UserMessage>>();
        for(UserMessage userMessage : page.data){
            map.put(userMessage,UserMessage.findChildMessageByParentId(userMessage.id));
        }
        UserBase userBase = UserBase.findById(userId);
        render(page,map,userBase);
    }

    public static void create(String text,Long toUserId,Long parentId){
        String userName = (String) DsisSecurity.connected();
        UserBase userBase =  UserBase.find("byNickName", userName).first();
        if(parentId != null){
            if(text != null && text.trim().length() > 0){
                UserMessage userMessage = new UserMessage();
                userMessage.text = text;
                userMessage.parentMsg = UserMessage.findById(parentId);
                userMessage.formUser = UserBase.find("byNickName", userName).first();
                userMessage.toUser = UserBase.findById(toUserId);
                userMessage.create();
                Map<String,Object> _map = new HashMap<String, Object>();
                _map.put(PointType.POINT_MESSAGEKEY_USERID,userBase.id);
                _map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.FABIAOLIUYAN);
                _map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAOLIUYAN));
                _map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
                jedisPoolSource.rpush(Queues.USER_POINTS, _map);
            }
        }else {
            if(text!=null && text.trim().length()>0){
                UserMessage userMessage = new UserMessage();
                userMessage.toUser  =  UserBase.findById(toUserId);
                userMessage.formUser = UserBase.find("byNickName", userName).first();
                userMessage.text = text;
                userMessage.create();
                Map<String,Object> _map = new HashMap<String, Object>();
                _map.put(PointType.POINT_MESSAGEKEY_USERID,userBase.id);
                _map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.FABIAOLIUYAN);
                _map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAOLIUYAN));
                _map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
                jedisPoolSource.rpush(Queues.USER_POINTS, _map);
            }
        }
        list(toUserId);
    }

    public static void delete(Long userMessageId){
        UserMessage userMessage = UserMessage.findById(userMessageId);
        if(userMessage.parentMsg == null ){
            List<UserMessage> list = UserMessage.findChildMessageByParentId(userMessage.id);
            for(UserMessage  userMessage1 : list){
                userMessage1.status = Status.DELETED;
                userMessage1.save();
            }
        }
        userMessage.status = Status.DELETED;
        userMessage.save();
        list(userMessage.toUser.id);
    }
}
