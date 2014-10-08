package com.arj.ziguiw.common;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-30
 * Time: P.M.4:14
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 子贵网——积分相关常量
 */
public class PointType {

    //积分类型
    public static final int DENGLU = 1;
    public static final int FABIAOPINGLUN = 2;
    public static final int FABIAOTIEZI = 3;
    public static final int HUITIE = 4;
    public static final int JINGHUATIE = 5;
    public static final int SHENHEZIYUAN = 6;
    public static final int XIAZAIZIYUAN = 7;
    public static final int ZIYUANBEIXIAZAI =8;
    public static final int ZHUCE = 9;
    public static final int WANSHANZILIAO = 10;
    public static final int CHONGZHIJIFEN = 11;
    public static final int SIGN = 12;
    public static final int ACTIVITY = 13;
    public static final int FABIAORIZHI = 14;
    public static final int FABIAOLIUYAN = 15;
    public static final int TIWEN = 16;
    public static final int FABIAOXINQING = 17;  //发表心情


    //消息对象为map，定义key如下
    public static final String POINT_MESSAGEKEY_USERID = "userId"; //用户ID
    public static final String POINT_MESSAGEKEY_USERNAME = "userName"; //用户账号
    public static final String POINT_MESSAGEKEY_POINTCHANGE = "pointChange"; //操作的积分数，例如增加10积分
    public static final String POINT_MESSAGEKEY_FLAG= "flag"; //加 or 减
    public static final String POINT_MESSAGEKEY_TYPE= "type"; //积分类型，同上

    public static final int ADD_POINT = 1; //加积分
    public static final int MINUS_POINT = 2; //减积分

    //积分类型中文显示
    public static final Map<Integer,String> maps = new HashMap<Integer, String>();
    static {
        maps.put(DENGLU,"登录");
        maps.put(FABIAOPINGLUN,"发表评论");
        maps.put(FABIAOTIEZI,"发表帖子");
        maps.put(HUITIE,"回帖");
        maps.put(JINGHUATIE,"帖子成为精华帖");
        maps.put(SHENHEZIYUAN,"上传资源审核通过");
        maps.put(XIAZAIZIYUAN,"下载资源");
        maps.put(ZIYUANBEIXIAZAI,"上传资源被下载");
        maps.put(ZHUCE,"注册");
        maps.put(WANSHANZILIAO,"完善资料");
        maps.put(CHONGZHIJIFEN,"充值积分");
        maps.put(SIGN,"签到积分");
        maps.put(ACTIVITY,"六一活动");
        maps.put(FABIAORIZHI,"发表日志");
        maps.put(FABIAOLIUYAN,"发表留言");
        maps.put(TIWEN,"提问");
        maps.put(FABIAOXINQING, "发表心情");

    }

    //积分类型对应的分数
    public static final Map<Integer, Integer> points =new HashMap<Integer, Integer>();
    static {
        points.put(DENGLU, 3);
        points.put(FABIAOPINGLUN, 1);
        points.put(FABIAOTIEZI, 2);
        points.put(HUITIE, 1);
        points.put( JINGHUATIE, 3);
        points.put(SHENHEZIYUAN, 30);
        points.put(ZHUCE, 15);
        points.put(WANSHANZILIAO, 20);
        points.put(SIGN, 2);
        points.put(ACTIVITY, 1000);
        points.put(FABIAORIZHI, 5);
        points.put(FABIAOLIUYAN, 5);
        points.put(TIWEN,5);
        points.put(FABIAOXINQING, 5);
    }

    public static Map<String,Object> newMessage(Long userId,String userName,Integer type,Integer changPoint,Integer flag){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(POINT_MESSAGEKEY_USERID,userId);
        map.put(POINT_MESSAGEKEY_USERNAME,userName);
        map.put(POINT_MESSAGEKEY_POINTCHANGE,changPoint);
        map.put(POINT_MESSAGEKEY_TYPE,type);
        map.put(POINT_MESSAGEKEY_FLAG,flag);
        return map;
    }

}
