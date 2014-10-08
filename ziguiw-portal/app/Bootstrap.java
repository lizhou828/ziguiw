import com.arj.ziguiw.common.*;
import models.PointsHistory;
import models.UserBase;
import models.UserTrends;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;
import play.jobs.Every;
import play.jobs.Job;

import java.util.Date;
import java.util.Map;

/**
 * User: liujy
 * Date: 13-4-9
 * Time: P.M 3:16
 */
@Every("15s")
public class Bootstrap extends Job {

    private static Log log = LogFactory.getLog("queue_points");
    private static Log log1 = LogFactory.getLog("queue_trends");
    private JedisPoolSource jedisPoolSource = new JedisPoolSource((String)Play.configuration.get("redis.host"),
            Play.configuration.get("redis.port") == null ? null : Integer.parseInt(Play.configuration.get("redis.port").toString()) );

    @Override
    public void doJob() throws Exception {
        receiveUserPoints();
        receiveUserTrends();

    }

    private void receiveUserTrends(){
        for (int x = 0; x<= 100; x++){
            Map<String, Object> message = (Map<String, Object>) jedisPoolSource.lpop(Queues.USER_TRENDS);
            if(message != null){
                try{
                    log1.info(String.format("The user trends message is %s",message));
                    UserTrends userTrends = new UserTrends();
                    userTrends.title = (String) message.get(UserTrendsType.TITLE);
                    userTrends.user = UserBase.findById(message.get(UserTrendsType.USERID));
                    userTrends.type = (Integer) message.get(UserTrendsType.TYPE);
                    userTrends.objectId = (Long) message.get(UserTrendsType.OBJECTID);
                    userTrends.createTime = new Date();
                    userTrends.status = Status.OK;
                    userTrends.create();
                    log1.info("The user trends save success!");
                }catch (Exception e){
                    log1.error("User trends save fail,Because of %s",e);
                    jedisPoolSource.rpush(Queues.USER_TRENDS,message);
                }
            }
        }
    }

    private void receiveUserPoints() {
        for (int x = 0; x <= 100; x++) {
            Map<String, Object> message = (Map<String, Object>) jedisPoolSource.lpop(Queues.USER_POINTS);
            try{
                if (message != null) {
                    log.info(String.format("message : %s", message));
                    Long userId = (Long) message.get(PointType.POINT_MESSAGEKEY_USERID);
                    Integer pointChange  = (Integer) message.get(PointType.POINT_MESSAGEKEY_POINTCHANGE);
                    Integer flag = (Integer) message.get(PointType.POINT_MESSAGEKEY_FLAG);
                    Integer type = (Integer) message.get(PointType.POINT_MESSAGEKEY_TYPE);
                    String userName = (String) message.get(PointType.POINT_MESSAGEKEY_USERNAME);
                    UserBase userBase;
                    if (userId != null) {
                        userBase = UserBase.findById(userId);
                    } else {
                        userBase = UserBase.findByUserName(userName);
                    }
                    /* check */
                    if (type == PointType.DENGLU) {
                        Long logonCount = PointsHistory.findLogonCount(userBase.id);
                        if(logonCount > 5){
                            continue;
                        }
                    }
                    /* check end */
                    if (flag == PointType.ADD_POINT) {
                        userBase.points = userBase.points + pointChange;
                    } else {
                        userBase.points = userBase.points - pointChange;
                    }
                    log.info(String.format("save userbase point, user : %s, point : %s", userBase.nickName, userBase.points));
                    PointsHistory.create(userBase, pointChange, flag, type);
                    log.info("save success!");
                }
            } catch (Throwable t) {
                log.error("receive point message failed!", t);
                jedisPoolSource.rpush(Queues.USER_POINTS, message);
            }
        }
    }


}
