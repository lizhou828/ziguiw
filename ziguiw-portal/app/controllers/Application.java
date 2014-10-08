package controllers;

import com.arj.ziguiw.common.JedisPoolSource;
import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.RecommendArea;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.Play;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;
import redis.clients.jedis.Jedis;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.arj.ziguiw.common.PointType.FABIAOTIEZI;
import static com.arj.ziguiw.common.PointType.TIWEN;


public class Application extends Controller {
    protected static String uploadDir = Play.configuration.getProperty("upload.dir", "/data/upload");
    protected static String ctx = Play.configuration.getProperty("static.domain");

    protected static JedisPoolSource jedisPoolSource = new JedisPoolSource((String)Play.configuration.get("redis.host"),
            Play.configuration.get("redis.port") == null ? null : Integer.parseInt(Play.configuration.get("redis.port").toString()) );

    private static Log log = LogFactory.getLog(Application.class);

    public static void index() {
        List<Recommend> portal_index_hd = Recommend.findByArea(RecommendArea.PORTAL_INDEX_HD);
        List<Recommend> portal_index_jinritoutiao = Recommend.findByArea(RecommendArea.PORTAL_INDEX_JINRITOUTIAO);
        List<Recommend> portal_index_bianjituijian = Recommend.findByArea(RecommendArea.PORTAL_INDEX_BIANJITUIJIAN);
        List<Recommend> portal_index_jiaodianhuati = Recommend.findByArea(RecommendArea.PORTAL_INDEX_JIAODIANHUATI);
        List<Recommend> portal_index_xiaoyuanxinwen = Recommend.findByArea(RecommendArea.PORTAL_INDEX_XIAOYUANXINWEN);
        List<Recommend> portal_index_kaoshishengxue = Recommend.findByArea(RecommendArea.PORTAL_INDEX_KAOSHISHENGXUE);
        List<Recommend> portal_index_youerqitop = Recommend.findByArea(RecommendArea.PORTAL_INDEX_YOUERQITOP);
        List<Recommend> portal_index_youerqilist = Recommend.findByArea(RecommendArea.PORTAL_INDEX_YOUERQILIST);
        List<Recommend> portal_index_shaoerqitop = Recommend.findByArea(RecommendArea.PORTAL_INDEX_SHAOERQITOP);
        List<Recommend> portal_index_shaoerqilist = Recommend.findByArea(RecommendArea.PORTAL_INDEX_SHAOERQILIST);
        List<Recommend> portal_index_qingnianqitop = Recommend.findByArea(RecommendArea.PORTAL_INDEX_QINGNIANQITOP);
        List<Recommend> portal_index_qingnianqilist = Recommend.findByArea(RecommendArea.PORTAL_INDEX_QINGNIANQILIST);

        List<Recommend> portal_index_tongchengquantop = Recommend.findByArea(RecommendArea.PORTAL_INDEX_TONGCHENGQUANTOP);
        List<Recommend> portal_index_tongchengquanlist = Recommend.findByArea(RecommendArea.PORTAL_INDEX_TONGCHENGQUANLIST);
        List<Recommend> portal_index_tonglingquantop = Recommend.findByArea(RecommendArea.PORTAL_INDEX_TONGLINGQUANTOP);
        List<Recommend> portal_index_tonglingquanlist = Recommend.findByArea(RecommendArea.PORTAL_INDEX_TONGLINGQUANLIST);
        List<Recommend> portal_index_xuexijiaoliu = Recommend.findByArea(RecommendArea.PORTAL_INDEX_XUEXIJIAOLIU);
        List<Recommend> portal_index_zhuanjiawenda = Recommend.findByArea(RecommendArea.PORTAL_INDEX_ZHUANJIAWENDA);

        List<Recommend> portal_index_mingxingqiang = Recommend.findByArea(RecommendArea.PORTAL_INDEX_MINGXINGQIANG);
        List<Recommend> portal_index_hotdiary = Recommend.findByArea(RecommendArea.PORTAL_INDEX_HOTDIARY);
        List<Recommend> portal_index_gerenriji = Recommend.findByArea(RecommendArea.PORTAL_INDEX_GERENRIJI);
        List<Recommend> portal_index_zhaopian = Recommend.findByArea(RecommendArea.PORTAL_INDEX_ZHAOPIAN);

        List<Knowledge> knowledges= Knowledge.findHotByKnowledgeChannelId(4l);
        List<Question> questions =  Question.findHot();
        render(portal_index_hd,
                portal_index_jinritoutiao,
                portal_index_bianjituijian,
                portal_index_jiaodianhuati,
                portal_index_xiaoyuanxinwen,
                portal_index_kaoshishengxue,
                portal_index_youerqitop,
                portal_index_tongchengquantop,
                portal_index_youerqilist,
                portal_index_shaoerqitop,
                portal_index_shaoerqilist,
                portal_index_tongchengquanlist,
                portal_index_qingnianqitop,
                portal_index_qingnianqilist,
                portal_index_tonglingquantop,
                portal_index_tonglingquanlist,
                portal_index_xuexijiaoliu,
                portal_index_zhuanjiawenda,
                knowledges,questions,portal_index_mingxingqiang,portal_index_hotdiary,portal_index_gerenriji,portal_index_zhaopian);
    }

    public static void mobile() {
        render();
    }

    @Before
    public static void prepareData(){
        /* log the url */
        MDC.put("url", request.url.replaceAll("'", "‘"));
        Http.Header userAgent = request.headers.get("user-agent");
        Http.Header referer = request.headers.get("referer");
        if (userAgent != null) MDC.put("userAgent", userAgent.value().replace("'", "‘"));
        if (referer != null) MDC.put("referer", referer.value().replace("'", "‘"));


        String userName = (String) DsisSecurity.connected();
        if (userName != null) {
            MDC.put("username", userName);
            //登录用户
            UserBase userBase =  UserBase.find("byNickName", userName).first();
            List<Long> friendIds = null;
            //查询登录用户的朋友id集合
            if(userBase !=null){
                friendIds = UserFriend.findFriendIdByUserId(userBase.id);
            }
            renderArgs.put("user",userBase);
            renderArgs.put("friendIds",friendIds);
        }
        renderArgs.put("ctx", ctx);
    }

    @Before(only = {"UserMessages.create", "UserDiarys.create", "UserMoods.create", "Forums.reply"})
    public static void shield() {
        Jedis jedis = null;
        boolean shield = false;
        try {
            jedis = jedisPoolSource.getJedis();
            String key = "t_shield_" + Http.Request.current().action + "_" + session.getId();
            Long len = jedis.llen(key);
            log.info(String.format("shield, key is %s, len is %s", key, len));
            if (len.intValue() ==0) {
                jedis.lpush(key, System.currentTimeMillis() + "");
                jedis.expire(key, 5);
            } else {
                shield = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //do nothing!
        } finally {
            if (jedis != null) {
                jedisPoolSource.returnResource(jedis);
            }
        }
        if (shield) {
            play.db.DB.close(); //close current thread db connection!
            renderHtml("<script>alert('频繁操作，请休息一会儿');history.go(-1);</script>");
        }
    }

    @After(only = {"Forums.reply", "Forums.saveForum", "UserBases.register","UserMoods.create","UserBases.updateInformation","Questions.askquestion"})
    public static void sendPointMessage() {
        UserBase userBase = (UserBase) renderArgs.get("user");
        if (userBase == null) return;
        Map<String, Object> message = new HashMap<String, Object>();
        message.put(PointType.POINT_MESSAGEKEY_USERID, userBase.id);


        //发表回复
        if ( "Forums.reply".equalsIgnoreCase(request.action)) {
            message.put(PointType.POINT_MESSAGEKEY_TYPE, PointType.FABIAOPINGLUN);
            message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE, PointType.points.get(PointType.FABIAOPINGLUN));
            message.put(PointType.POINT_MESSAGEKEY_FLAG, PointType.ADD_POINT);
        }
        //注册
        if ("UserBases.register".equalsIgnoreCase(request.action)){
            message.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.ZHUCE);
            message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.ZHUCE));
            message.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
        }
        //发表日志
        if ("Forums.saveForum".equalsIgnoreCase(request.action)){
            message.put(PointType.POINT_MESSAGEKEY_TYPE, FABIAOTIEZI);
            message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAOTIEZI));
            message.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
        }
        //发表心情
        if("UserMoods.create".equalsIgnoreCase(request.action)){
            message.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.FABIAOXINQING);
            message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAOXINQING));
            message.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
        }
        //完善资料
        if("UserBases.updateInformation".equalsIgnoreCase(request.action)){
            PointsHistory pointsHistory = PointsHistory.findByType(userBase.id,PointType.WANSHANZILIAO);
            if(pointsHistory == null){
                message.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.WANSHANZILIAO);
                message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.WANSHANZILIAO));
                message.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
            }
         //提问
        if("Questions.askquestion".equalsIgnoreCase(request.action)){
            message.put(PointType.POINT_MESSAGEKEY_TYPE,TIWEN);
            message.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.TIWEN));
            message.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
        }
        }
        jedisPoolSource.rpush(Queues.USER_POINTS, message);
    }

    public static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    public static Integer getPageSize() {
        return request.params.get("pageSize") == null ? 15 : Integer.valueOf(request.params.get("pageSize"));
    }

    public static Long getTodayStart(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    public static Long getTodayEnd(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }


}