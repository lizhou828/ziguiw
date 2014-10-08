package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import models.FatherActivity;
import models.FatherMessage;
import models.FatherMessageReplay;
import models.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.Http;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-9
 * Time: 下午2:59
 */
public class FatherActivitys extends Application{

    private static Log log = LogFactory.getLog(FatherActivity.class);

    public static void information(Long fatherActivityId, String title, String displayName, Integer age, String contact
        , String email, String schoolName, String bj){
            FatherActivity fatherActivity = FatherActivity.findById(fatherActivityId);
            Integer flag;
            if(title == null || displayName == null || contact == null ||
                title.trim().equals("") || displayName.trim().equals("")  || contact.trim().equals("")){
                flag = -1;
                render("/FatherActivitys/wxAdd.html",fatherActivityId,flag,fatherActivityId,title,displayName,age,contact,email);
            }else {
                fatherActivity.age = age;
                fatherActivity.title = title;
                fatherActivity.contact = contact;
                fatherActivity.email = email;
                fatherActivity.displayName = displayName;
                fatherActivity.schoolName = schoolName;
                fatherActivity.bj = bj;
                fatherActivity.save();
                render(fatherActivity,fatherActivityId);
            }
        }

        public static void list(Integer flag){
            Page<FatherActivity> page = FatherActivity.findPage(getPage(), 9);
            List<FatherMessage> list = FatherMessage.findByTime();
            List<FatherMessageReplay> replays = FatherMessageReplay.findAll();
            render(page,flag,list,replays);
        }

        public static void plist(Integer flag){
            Page<FatherActivity> page = FatherActivity.findClickCount(getPage(), 12);
            render(page,flag);
        }

        public static void vote(Long fatherActivityId, Integer flag, String mobile) throws ParseException {
            if (Http.Response.current() == null) throw new RuntimeException("web socket!");
            FatherActivity fatherActivity = FatherActivity.findById(fatherActivityId);
            Integer _flag = 1;
            Jedis jedis = null;
            String ip = request.remoteAddress;
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                String shieldValue = UUID.randomUUID().toString();
                Http.Cookie shieldCookie = request.cookies.get("FatherActivitys");
                if (shieldCookie != null) {
                    shieldValue = shieldCookie.value;
                } else {
                    response.setCookie("FatherActivitys", shieldValue, null, "/", Integer.MAX_VALUE, false);
                }
                String key = "FatherActivitys_" + shieldValue;
                //String key = request.remoteAddress; // + "_" + fatherActivityId;
                String liaosl = 68 + "_" + "liaosl";
                jedis = jedisPoolSource.getJedis();
                if (jedis != null) {
                    String value = jedis.get(key);
                    String ipValue = jedis.get(ip);
                    log.info("vote key : " + key + ", value : " + value);
                    String num = "0";
                    String number = "0";
                    String ipNum = "0";
                    if (value != null) {
                        String time = value.split("_")[0];
                        num = value.split("_")[1];
                        if (currentDate.equals(time))  {
                            if ( Integer.valueOf(num) >= 5) {
                                _flag = -1;
                                log.info("key : " + key + ", value :" + value + ", _flag = " + _flag);
                            }
                        }  else {
                            num = "0";
                        }
                    }
                    if(ipValue != null) {
                        String ipTime = ipValue.split("_")[0];
                        ipNum = ipValue.split("_")[1];
                        if(currentDate.equals(ipTime)){
                            if(Integer.valueOf(ipNum) >= 100){
                                _flag = -1;
                                log.info("key : " + key + ", value :" + value + ", _flag = " + _flag);
                            }
                        }else {
                            ipNum = "0";
                        }
                    }
                    if(fatherActivityId == 68){
                        String liaoslValue = jedis.get(liaosl);
                        log.info("fatherActivity id 68 value = " + liaoslValue);
                        number = "0";
                        if(liaoslValue != null){
                            log.info("lisoslkey : " + liaosl + ", lisoslValue :" + liaoslValue);
                            String _time = liaoslValue.split("_")[0];
                            number = liaoslValue.split("_")[1];
                            if(currentDate.equals(_time)){
                                if (Integer.valueOf(number) >= 30) _flag = -1;
                            } else {
                                number = "0";
                            }
                        }
                        number = (Integer.valueOf(number) + 1) + "";
                        jedis.set(liaosl, currentDate + "_" + number);
                    }
                    ipNum = (Integer.valueOf(ipNum) + 1) + "";
                    jedis.set(ip, currentDate + "_" + ipNum);
                    num = (Integer.valueOf(num) + 1) + "";
                    jedis.set(key, currentDate + "_" + num);
                }
            } finally {
                if (jedis != null) jedisPoolSource.returnBrokenResource(jedis);
            }
            if(System.currentTimeMillis() >= sf.parse("2013-06-30 00:00:00").getTime()){
                     _flag = -2;
            }
            if (_flag != -1 && _flag != -2) {
                if (fatherActivity.clickCount == null) {
                    fatherActivity.clickCount = 1;
                } else {
                    fatherActivity.clickCount = fatherActivity.clickCount + 1;
                }
                fatherActivity.save();
            }
            if(flag == 1){
                show(fatherActivityId,_flag);
            }
            if(flag == 2){
                list(_flag);
            }
            if(flag == 3){
                plist(_flag);
            }
            if(flag == 4){
                search(mobile,_flag);
            }
        }

        public static void index(){
            render();
        }

        public static void upload(String title, String displayName, Integer age, String contact, String email, File file,
                                  String schoolName, String bj){
            Integer flag;
            String fileName = "";
            if(file != null){
                fileName = file.getName();
                fileName = fileName.substring(fileName.indexOf("."));
                log.info("father activity file name = " + fileName);
            }
            if(title == null || displayName == null || contact == null || file == null ||
               title.trim().equals("") || displayName.trim().equals("")  || contact.trim().equals("")){
                 flag = -1;
                 render("/FatherActivitys/add.html",title,displayName,age,contact,email,file,flag);
            }else if((!fileName.equalsIgnoreCase(".jpg")) && (!fileName.equalsIgnoreCase(".png")) && (!fileName.equalsIgnoreCase(".gif"))){
                 flag = -2;
                 render("/FatherActivitys/add.html",title,displayName,age,contact,email,file,flag);
            }else {
                FatherActivity fatherActivity = new FatherActivity();
                fatherActivity.title = title;
                fatherActivity.contact = contact;
                fatherActivity.email = email;
                fatherActivity.displayName = displayName;
                fatherActivity.age = age;
                fatherActivity.schoolName = schoolName;
                fatherActivity.bj = bj;
                fatherActivity.url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,new String[]{"284X210"});
                fatherActivity.create();
                show(fatherActivity.id, null);
            }
        }

        public static void add(){
            render();
        }

        public static void wxAdd(Long fatherActivityId){
            FatherActivity fatherActivity = FatherActivity.findById(fatherActivityId);
            render(fatherActivityId,fatherActivity);
        }

        public static void show(Long fatherActivityId, Integer flag){
            FatherActivity fatherActivity = FatherActivity.findById(fatherActivityId);
            List<FatherActivity> list = FatherActivity.findByTime();
            int index = list.indexOf(fatherActivity);
            int start = list.size() - 1;
            int end = 0;
            Long startId;
            Long endId;
            if(index != -1){
                if(index - 1 >= 0){
                     start = index - 1;
                }
                if(index + 1 < list.size()){
                    end = index + 1;
                }
            }
            startId = list.get(start).id;
            endId = list.get(end).id;
            render(fatherActivity,flag,startId,endId);
        }

        public static void search(String mobile,Integer flag){
            Page<FatherActivity> page = FatherActivity.searchMobile(mobile,getPage(),getPageSize());
            render(page,mobile,flag);
        }

        public static void  ActivitysGg() throws ParseException {
            List<FatherActivity> fatherActivities = FatherActivity.findHot();
            FatherActivity one = new FatherActivity();
            FatherActivity two = new FatherActivity();
            FatherActivity three = new FatherActivity();
            FatherActivity four = new FatherActivity();
            FatherActivity five = new FatherActivity();
            FatherActivity six = new FatherActivity();
            if(fatherActivities .size() >= 6){
                one = fatherActivities.get(0);
                two = fatherActivities.get(1);
                three = fatherActivities.get(2);
                four = fatherActivities.get(3);
                five = fatherActivities.get(4);
                six = fatherActivities.get(5);
            }
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int flag = -1;
            if(System.currentTimeMillis() >= sf.parse("2013-06-28 15:30:00").getTime()){
                log.info("activitys  date flag = 1 and now time is " + sf.format(new Date()));
                flag = 1;
            }
            render(one,two,three,flag,four,five,six);
        }
}
