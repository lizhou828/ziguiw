package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-22
 * Time: 下午2:39
 */
public class UserTrendsType {

        //用户操作类型
        public static final int DIARY = 0;//日志

        public static final int MOOD = 1;//心情

        public static final int PHOTO = 2;//照片


        //消息对象为map，定义key如下
        public static final String USERID = "userId"; //用户ID
        public static final String USERNAME = "userName"; //用户账号
        public static final String FLAG = "flag"; //标题
        public static final String TITLE = "title"; //标题
        public static final String TYPE = "type"; //发表的类型
        public static final String OBJECTID = "objectId"; //发表内容id

        public static final int CREATE = 1;//创建

        public static final int DELETED = 2;//删除


        public static Map<String,Object> newMessage(Long userId, Integer flag, String title, Integer type,Long objectId){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put(USERID, userId);
            map.put(FLAG, flag);
            map.put(TITLE,title);
            map.put(TYPE ,type);
            map.put(OBJECTID,objectId);
            return map;
        }
}
