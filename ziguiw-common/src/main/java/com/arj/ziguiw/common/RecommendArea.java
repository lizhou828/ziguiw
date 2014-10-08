package com.arj.ziguiw.common;

import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 13-1-15
 * Time: P.M 5:34
 */
public class RecommendArea {

    /* portal recommend area */
    public static final String PORTAL_INDEX_HD = "PORTAL_INDEX_HD";

    public static final String PORTAL_INDEX_JINRITOUTIAO = "PORTAL_INDEX_JINRITOUTIAO";

    public static final String PORTAL_INDEX_BIANJITUIJIAN = "PORTAL_INDEX_BIANJITUIJIAN";

    public static final String PORTAL_INDEX_JIAODIANHUATI = "PORTAL_INDEX_JIAODIANHUATI";
    public static final String PORTAL_INDEX_XIAOYUANXINWEN = "PORTAL_INDEX_XIAOYUANXINWEN";
    public static final String PORTAL_INDEX_KAOSHISHENGXUE = "PORTAL_INDEX_KAOSHISHENGXUE";
    public static final String PORTAL_INDEX_YOUERQITOP = "PORTAL_INDEX_YOUERQITOP";
    public static final String PORTAL_INDEX_YOUERQILIST = "PORTAL_INDEX_YOUERQILIST";
    public static final String PORTAL_INDEX_SHAOERQITOP = "PORTAL_INDEX_SHAOERQITOP";
    public static final String PORTAL_INDEX_SHAOERQILIST = "PORTAL_INDEX_SHAOERQILIST";
    public static final String PORTAL_INDEX_QINGNIANQITOP = "PORTAL_INDEX_QINGNIANQITOP";
    public static final String PORTAL_INDEX_QINGNIANQILIST = "PORTAL_INDEX_QINGNIANQILIST";
    public static final String PORTAL_INDEX_TONGCHENGQUANTOP = "PORTAL_INDEX_TONGCHENGQUANTOP";
    public static final String PORTAL_INDEX_TONGCHENGQUANLIST = "PORTAL_INDEX_TONGCHENGQUANLIST";
    public static final String PORTAL_INDEX_TONGLINGQUANTOP = "PORTAL_INDEX_TONGLINGQUANTOP";
    public static final String PORTAL_INDEX_TONGLINGQUANLIST = "PORTAL_INDEX_TONGLINGQUANLIST";
    public static final String PORTAL_INDEX_XUEXIJIAOLIU = "PORTAL_INDEX_XUEXIJIAOLIU";
    public static final String PORTAL_INDEX_ZHUANJIAWENDA = "PORTAL_INDEX_ZHUANJIAWENDA";
    public static final String PORTAL_INDEX_GERENRIJI = "PORTAL_INDEX_GERENRIJI";
    public static final String PORTAL_INDEX_MINGXINGQIANG = "PORTAL_INDEX_MINGXINGQIANG";

    public static final String PORTAL_INDEX_HOTDIARY = "PORTAL_INDEX_HOTDIARY";

    public static final String PORTAL_INDEX_ZHAOPIAN = "PORTAL_INDEX_ZHAOPIAN";


    public static final String PORTAL_INDEX_RIGHT_HOT = "PORTAL_INDEX_RIGHT_HOT";



    public static final String ONLINE_INDEX_HD = "ONLINE_INDEX_HD";

    public static final String ONLINE_INDEX_JINRITOUTIAO = "ONLINE_INDEX_JINRITOUTIAO";

    public static final String ONLINE_INDEX_BIANJITUIJIAN = "ONLINE_INDEX_BIANJITUIJIAN";

    public static final String ONLINE_INDEX_JIAODIANHUATI = "ONLINE_INDEX_JIAODIANHUATI";

    public static final String ONLINE_INDEX_REMENGTUIJIAN = "ONLINE_INDEX_REMENGTUIJIAN";

    public static final String ONLINE_INDEX_XIAOYUANXINWEN = "ONLINE_INDEX_XIAOYUANXINWEN";

    public static final String ONLINE_INDEX_KAOSHISHENGXUE = "ONLINE_INDEX_KAOSHISHENGXUE";

    public static final String ONLINE_INDEX_DIFANGZIXUN = "ONLINE_INDEX_DIFANGZIXUN";



    public static final String KNOWLEDGE_INDEX_HD = "KNOWLEDGE_INDEX_HD";

    public static final String KNOWLEDGE_INDEX_TOP = "KNOWLEDGE_INDEX_TOP";

    public static final String KNOWLEDGE_INDEX_YOUERQI = "KNOWLEDGE_INDEX_YOUERQI";

    public static final String KNOWLEDGE_INDEX_SHAOERQI = "KNOWLEDGE_INDEX_SHAOERQI";

    public static final String KNOWLEDGE_INDEX_QINGNIANQI = "KNOWLEDGE_INDEX_QINGNIANQI";

    public static final String COMMUNITY_INDEX_HD = "COMMUNITY_INDEX_HD";
    public static final String COMMUNITY_INDEX_SQTONGLINGQUAN = "COMMUNITY_INDEX_SQTONGLINGQUAN";
    public static final String COMMUNITY_INDEX_SQTONGCHENGQUAN = "COMMUNITY_INDEX_SQTONGCHENGQUAN";
    public static final String COMMUNITY_INDEX_SQXUEXIJIAOLIU = "COMMUNITY_INDEX_SQXUEXIJIAOLIU";
    public static final String COMMUNITY_INDEX_SQZHUANJIAWENDA = "COMMUNITY_INDEX_SQZHUANJIAWENDA";
    public static final String COMMUNITY_INDEX_TONGLINGQUAN = "COMMUNITY_INDEX_TONGLINGQUAN";
    public static final String COMMUNITY_INDEX_TONGCHENGQUAN = "COMMUNITY_INDEX_TONGCHENGQUAN";
    public static final String COMMUNITY_INDEX_XUEXIJIAOLIU = "COMMUNITY_INDEX_XUEXIJIAOLIU";
    public static final String COMMUNITY_INDEX_ZHUANJIAWENDA = "COMMUNITY_INDEX_ZHUANJIAWENDA";

    public static final String MYHOME_INDEX_TUIJIANUSER = "MYHOME_INDEX_TUIJIANUSER";
    public static final String MYHOME_INDEX_RIJIJINGXUANTOP = "MYHOME_INDEX_RIJIJINGXUANTOP";
    public static final String MYHOME_INDEX_RIJIJINGXUANLIST = "MYHOME_INDEX_RIJIJINGXUANLIST";
    public static final String MYHOME_INDEX_ZHAOPINJINGXUAN = "MYHOME_INDEX_ZHAOPINJINGXUAN";
    public static final String MYHOME_INDEX_DAJIADOUZAISHUO = "MYHOME_INDEX_DAJIADOUZAISHUO";
    public static final String MYHOME_INDEX_JIAYUANRENQI = "MYHOME_INDEX_JIAYUANRENQI";

    public static final String RESOURCES_INDEX_ZUIXINTUIJIAN = "RESOURCES_INDEX_ZUIXINTUIJIAN";

    public static final TreeMap<String, String> map = new TreeMap<String, String>();

    static {
        map.put(PORTAL_INDEX_HD, "首页-幻灯");
        map.put(PORTAL_INDEX_JINRITOUTIAO, "首页-今日头条");
        map.put(PORTAL_INDEX_BIANJITUIJIAN, "首页-编辑推荐");
        map.put(PORTAL_INDEX_JIAODIANHUATI,"首页-焦点话题");
        map.put(PORTAL_INDEX_XIAOYUANXINWEN,"首页-校园新闻");
        map.put(PORTAL_INDEX_KAOSHISHENGXUE,"首页-考试升学");
        map.put(PORTAL_INDEX_YOUERQITOP,"首页-幼儿TOP");
        map.put(PORTAL_INDEX_YOUERQILIST,"首页-幼儿LIST");
        map.put(PORTAL_INDEX_SHAOERQITOP,"首页-少儿TOP");
        map.put(PORTAL_INDEX_SHAOERQILIST,"首页-少儿LIST");
        map.put(PORTAL_INDEX_QINGNIANQITOP,"首页-青年TOP");
        map.put(PORTAL_INDEX_QINGNIANQILIST,"首页-青年LIST");
        map.put(PORTAL_INDEX_TONGCHENGQUANTOP,"首页-同城圈TOP");
        map.put(PORTAL_INDEX_TONGCHENGQUANLIST,"首页-同城圈LIST");
        map.put(PORTAL_INDEX_TONGLINGQUANTOP,"首页-同龄圈TOP");
        map.put(PORTAL_INDEX_TONGLINGQUANLIST,"首页-同龄圈LIST");
        map.put(PORTAL_INDEX_XUEXIJIAOLIU,"首页-学习交流");
        map.put(PORTAL_INDEX_ZHUANJIAWENDA,"首页-专家问答");
        map.put(PORTAL_INDEX_MINGXINGQIANG, "首页-我的小家-明星墙");
        map.put(PORTAL_INDEX_HOTDIARY, "首页-我的小家-热门日记");
        map.put(PORTAL_INDEX_GERENRIJI,"首页-我的小家-个人日记");
        map.put(PORTAL_INDEX_ZHAOPIAN,"首页-我的小家-照片");

        map.put(PORTAL_INDEX_RIGHT_HOT,"列表页-热点推荐");
        map.put(ONLINE_INDEX_HD, "教育在线-首页幻灯");
        map.put(ONLINE_INDEX_JINRITOUTIAO, "教育在线-首页今日头条");
        map.put(ONLINE_INDEX_BIANJITUIJIAN, "教育在线-首页编辑推荐");
        map.put(ONLINE_INDEX_JIAODIANHUATI, "教育在线-首页焦点新闻");
        map.put(ONLINE_INDEX_REMENGTUIJIAN, "教育在线-首页热门推荐");
        map.put(ONLINE_INDEX_XIAOYUANXINWEN, "教育在线-首页校园新闻");
        map.put(ONLINE_INDEX_KAOSHISHENGXUE, "教育在线-首页考试升学");
        map.put(ONLINE_INDEX_DIFANGZIXUN, "教育在线-首页地方资讯");

        map.put(KNOWLEDGE_INDEX_HD,"教育知识-幻灯");
        map.put(KNOWLEDGE_INDEX_TOP,"教育知识-头条");
        map.put(KNOWLEDGE_INDEX_YOUERQI,"教育知识-幼儿期");
        map.put(KNOWLEDGE_INDEX_SHAOERQI,"教育知识-少儿期");
        map.put(KNOWLEDGE_INDEX_QINGNIANQI,"教育知识-青年期");

        map.put(COMMUNITY_INDEX_HD,"互动社区-幻灯");
        map.put(COMMUNITY_INDEX_SQTONGLINGQUAN,"互动社区社区-同龄圈");
        map.put(COMMUNITY_INDEX_SQTONGCHENGQUAN,"互动社区社区-同城圈");
        map.put(COMMUNITY_INDEX_SQXUEXIJIAOLIU,"互动社区社区学-习交流");
        map.put(COMMUNITY_INDEX_SQZHUANJIAWENDA,"互动社区社区专-家问答");
        map.put(COMMUNITY_INDEX_TONGLINGQUAN,"互动社区-同龄圈");
        map.put(COMMUNITY_INDEX_TONGCHENGQUAN,"互动社区-同城圈");
        map.put(COMMUNITY_INDEX_XUEXIJIAOLIU,"互动社区学-习交流");
        map.put(COMMUNITY_INDEX_ZHUANJIAWENDA,"互动社区专-家问答");

        map.put(MYHOME_INDEX_TUIJIANUSER,"我的小家-用户推荐");
        map.put(MYHOME_INDEX_RIJIJINGXUANTOP,"我的小家-日记精选TOP");
        map.put(MYHOME_INDEX_RIJIJINGXUANLIST,"我的小家-日记精选LIST");
        map.put(MYHOME_INDEX_ZHAOPINJINGXUAN,"我的小家-照片精选");
        map.put(MYHOME_INDEX_DAJIADOUZAISHUO,"我的小家-大家都在说");
        map.put(MYHOME_INDEX_JIAYUANRENQI,"我的小家-家园人气榜");

        map.put(RESOURCES_INDEX_ZUIXINTUIJIAN,"资源首页-最新推荐");
    }

    public static final TreeMap<String, Integer> countMap = new TreeMap<String, Integer>();

    static {
        countMap.put(PORTAL_INDEX_HD, 5); //"首页-幻灯"
        countMap.put(PORTAL_INDEX_JINRITOUTIAO, 2);   //"首页-今日头条"
        countMap.put(PORTAL_INDEX_BIANJITUIJIAN, 8);//"首页-编辑推荐"
        countMap.put(PORTAL_INDEX_JIAODIANHUATI,8);//"首页-焦点话题"
        countMap.put(PORTAL_INDEX_XIAOYUANXINWEN,2);//"首页-校园新闻"
        countMap.put(PORTAL_INDEX_KAOSHISHENGXUE,10);//"首页-考试升学"
        countMap.put(PORTAL_INDEX_YOUERQITOP,1);//,"首页-幼儿TOP"
        countMap.put(PORTAL_INDEX_YOUERQILIST,4);//"首页-幼儿LIST"
        countMap.put(PORTAL_INDEX_SHAOERQITOP,1); //"首页-少儿TOP"
        countMap.put(PORTAL_INDEX_SHAOERQILIST,4); //"首页-少儿LIST"
        countMap.put(PORTAL_INDEX_QINGNIANQITOP,1);//"首页-青年TOP"
        countMap.put(PORTAL_INDEX_QINGNIANQILIST,4);//"首页-青年LIST"
        countMap.put(PORTAL_INDEX_TONGCHENGQUANTOP,1);//"首页-同城圈TOP"
        countMap.put(PORTAL_INDEX_TONGCHENGQUANLIST,5);//"首页-同城圈LIST"
        countMap.put(PORTAL_INDEX_TONGLINGQUANTOP,1); //"首页-同龄圈TOP"
        countMap.put(PORTAL_INDEX_TONGLINGQUANLIST,5);//"首页-同龄圈LIST"
        countMap.put(PORTAL_INDEX_XUEXIJIAOLIU,8); //"首页-学习交流"
        countMap.put(PORTAL_INDEX_ZHUANJIAWENDA,8); //"首页-专家问答"
        countMap.put(PORTAL_INDEX_MINGXINGQIANG,6);//"首页-我的小家-明星墙"
        countMap.put(PORTAL_INDEX_HOTDIARY,9 ); //"首页-我的小家-热门日记"
        countMap.put(PORTAL_INDEX_GERENRIJI,9);//"首页-我的小家-个人日记"
        countMap.put(PORTAL_INDEX_ZHAOPIAN,9);//"首页-我的小家-照片"

        countMap.put(PORTAL_INDEX_RIGHT_HOT,2); //"列表页-热点推荐"
        countMap.put(ONLINE_INDEX_HD,5); // "教育在线-首页幻灯"
        countMap.put(ONLINE_INDEX_JINRITOUTIAO, 1); //"教育在线-首页今日头条"
        countMap.put(ONLINE_INDEX_BIANJITUIJIAN,3 ); //"教育在线-首页编辑推荐"
        countMap.put(ONLINE_INDEX_JIAODIANHUATI,4 ); //"教育在线-首页焦点话题"
        countMap.put(ONLINE_INDEX_REMENGTUIJIAN,2 ); //"教育在线-首页热门推荐"
        countMap.put(ONLINE_INDEX_XIAOYUANXINWEN,4 );//"教育在线-首页校园新闻"
        countMap.put(ONLINE_INDEX_KAOSHISHENGXUE,6 ); //"教育在线-首页考试升学"
        countMap.put(ONLINE_INDEX_DIFANGZIXUN, 7); //"教育在线-首页地方资讯"

        countMap.put(KNOWLEDGE_INDEX_HD,5);//"教育知识-幻灯"
        countMap.put(KNOWLEDGE_INDEX_TOP,1);//"教育知识-头条"
        countMap.put(KNOWLEDGE_INDEX_YOUERQI,2); //"教育知识-幼儿期"
        countMap.put(KNOWLEDGE_INDEX_SHAOERQI,2); //"教育知识-少儿期"
        countMap.put(KNOWLEDGE_INDEX_QINGNIANQI,2);//"教育知识-青年期"

        countMap.put(COMMUNITY_INDEX_HD,5); //"互动社区-幻灯"
        countMap.put(COMMUNITY_INDEX_SQTONGLINGQUAN,14); //"互动社区社区-同龄圈"
        countMap.put(COMMUNITY_INDEX_SQTONGCHENGQUAN,14);//"互动社区社区-同城圈"
        countMap.put(COMMUNITY_INDEX_SQXUEXIJIAOLIU,14); //"互动社区社区学-习交流"
        countMap.put(COMMUNITY_INDEX_SQZHUANJIAWENDA,14);//"互动社区社区专-家问答"
        countMap.put(COMMUNITY_INDEX_TONGLINGQUAN,6); //"互动社区-同龄圈"
        countMap.put(COMMUNITY_INDEX_TONGCHENGQUAN,6); //"互动社区-同城圈"
        countMap.put(COMMUNITY_INDEX_XUEXIJIAOLIU,8);//"互动社区学-习交流"
        countMap.put(COMMUNITY_INDEX_ZHUANJIAWENDA,8);//"互动社区专-家问答"

        countMap.put(MYHOME_INDEX_TUIJIANUSER,10);//"我的小家-用户推荐"
        countMap.put(MYHOME_INDEX_RIJIJINGXUANTOP,2);//"我的小家-日记精选TOP"
        countMap.put(MYHOME_INDEX_RIJIJINGXUANLIST,8);//"我的小家-日记精选LIST"
        countMap.put(MYHOME_INDEX_ZHAOPINJINGXUAN,15);//"我的小家-照片精选"
        countMap.put(MYHOME_INDEX_DAJIADOUZAISHUO,4);//"我的小家-大家都在说"
        countMap.put(MYHOME_INDEX_JIAYUANRENQI,10); //"我的小家-家园人气榜"

        countMap.put(RESOURCES_INDEX_ZUIXINTUIJIAN,9);//教育资源-最新推荐
    }
}
