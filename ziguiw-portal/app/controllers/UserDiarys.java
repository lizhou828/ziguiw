package controllers;

import com.arj.ziguiw.common.PointType;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserTrendsType;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Page;
import models.UserBase;
import models.UserDiary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.mvc.Http;
import play.mvc.With;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Liujy
 * Date: 13-3-12
 * Time: 上午10:51
 */
@With(SecureCAS.class)
public class UserDiarys extends Application {

    public static final Log logger = LogFactory.getLog(UserDiary.class);

   @UnSecure
   public static void list(Long userId){
       UserBase userBase = UserBase.findById(userId);
       Page<UserDiary> page = UserDiary.findByUserId(userId,getPage(),getPageSize());
       render(userBase,page);
   }


    @UnSecure
    public static void show(Long userDiaryId){
        UserDiary userDiary = UserDiary.findById(userDiaryId);
        if(userDiary.clickCount == null){userDiary.clickCount = 1l;}
        else {userDiary.clickCount = userDiary.clickCount + 1;}
        userDiary.save();
        UserBase userBase = UserBase.findById(userDiary.user.id);
        render(userDiary,userBase);
    }

    @UnSecure
    public static void add(Long userId){
        UserBase userBase = UserBase.findById(userId);
        render(userBase);
    }

    public static void create(String title,String content,Long userId,String description){
        String error = "";
        if(title != null && title.trim().length() > 0){
           if(content != null && content.trim().length() > 0){
               UserDiary userDiary = new UserDiary();
               userDiary.description = description;
               userDiary.firstImage = UserDiary.getFirstImage(content);
               userDiary.text = content;
               userDiary.title = title;
               userDiary.user = UserBase.findById(userId);
               userDiary.create();
               Map<String,Object> map = UserTrendsType.newMessage(userId, UserTrendsType.CREATE, title, UserTrendsType.DIARY, userDiary.id);
               jedisPoolSource.rpush(Queues.USER_TRENDS, map);
               Map<String,Object> _map = new HashMap<String, Object>();
               _map.put(PointType.POINT_MESSAGEKEY_USERID,userId);
               _map.put(PointType.POINT_MESSAGEKEY_TYPE,PointType.FABIAORIZHI);
               _map.put(PointType.POINT_MESSAGEKEY_POINTCHANGE,PointType.points.get(PointType.FABIAORIZHI));
               _map.put(PointType.POINT_MESSAGEKEY_FLAG,PointType.ADD_POINT);
               jedisPoolSource.rpush(Queues.USER_POINTS, _map);
               list(userId);
           }else {
               error = "内容不能为空";
               flash.put("error",error);
           }
        }else {
            error = "标题不能为空";
            flash.put("error",error);
        }

    }

    @UnSecure
    public static void mobileCreate(Long userId, Integer type, String title, String text, String description, String mobile_ticket
                                      , String scale, Long userDiaryId){
          String ctx = (String) renderArgs.get("ctx");
          String patternStr = "\\@pic\\:file\\d+\\@";
          Pattern patternForTag = Pattern.compile (patternStr,Pattern.DOTALL|Pattern.CASE_INSENSITIVE );
          logger.info(String.format("the params userId = %s,type = %s,title = %s,text = %s,description = %s," +
                  "mobile_ticket = %s,scale = %s",userId,type,title,text,description,mobile_ticket,scale));
          try{
              if(userDiaryId == null){
                  UserBase userBase = UserBase.findById(userId);
                  if(userBase != null){
                         UserDiary userDiary = new UserDiary();
                         userDiary.description = description;
                         userDiary.title = title;
                         userDiary.user = userBase;
                         userDiary.type = type;
                              Matcher matcherForTag = patternForTag.matcher(text);
                              int i = -1;
                              while (matcherForTag.find()){
                                  i++;
                                  String src = matcherForTag.group();
                                  File file = Http.Request.current().params.get("file" + i, File.class);
                                  logger.info(String.format("the user diary text %s image url is %s and the file param name is %s",i,file,"file" + i));
                                  String url = "";
                                  if(file != null){
                                      if(scale == null){
                                          url = FileUtils.copyImage(file,file.getName(),uploadDir);
                                      }else {
                                          url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,new String[]{scale});
                                      }
                                  }
                                  String _url = "@img:" + ctx + "/" + url + "@";
                                  text = text.replace(src,_url);
                              }
                         userDiary.text = text;
                         userDiary.create();
                         renderText(userDiary.id);
                  }else {
                         logger.error("the mobileCreate fail reason is user not exist!");
                         renderText("-1");
                  }
              }else {
                  UserDiary userDiary = UserDiary.findById(userDiaryId);
                  if(text != null && (!text.trim().equals(""))){
                      Matcher matcherForTag = patternForTag.matcher(text);
                      int i = -1;
                      while (matcherForTag.find()){
                          i++;
                          String src = matcherForTag.group();
                          File file = Http.Request.current().params.get("file" + i, File.class);
                          logger.info(String.format("the user diary text %s image url is %s and the file param name is %s",i,file,"file" + i));
                          String url = "";
                          if(file != null){
                              if(scale == null){
                                  url = FileUtils.copyImage(file,file.getName(),uploadDir);
                              }else {
                                  url = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir,new String[]{scale});
                              }
                          }
                          String _url = "@img:" + ctx + "/" + url + "@";
                          text = text.replace(src,_url);
                      }
                      userDiary.text = text;
                      userDiary.save();
                  }
                  renderText(userDiaryId);
              }
          }catch (Exception e){
              logger.error(e);
              renderText("-1");
          }

    }

    public static void edit(Long userDiaryId){
        UserDiary userDiary = UserDiary.findById(userDiaryId);
        UserBase userBase = userDiary.user;
        render(userDiary,userBase);
    }

    public static void save(String title,String content,Long userDiaryId,String description){
        String error = "";
        UserDiary userDiary = UserDiary.findById(userDiaryId);
        if(title != null && title.trim().length() > 0){
            if(content != null && content.trim().length() > 0){
                userDiary.title = title;
                userDiary.description = description;
                userDiary.text = content;
                userDiary.firstImage = UserDiary.getFirstImage(content);
                userDiary.lastModifyTime = new Date();
                userDiary.save();
            }else {
                error = "内容不能为空";
                flash.put("error",error);
            }
        }else {
            error = "标题不能为空";
            flash.put("error",error);
        }
        list(userDiary.user.id);
    }

    public static void delete(Long userDiaryId){
        UserDiary userDiary = UserDiary.findById(userDiaryId);
        userDiary.status = Status.DELETED;
        userDiary.save();
        list(userDiary.user.id);
    }
    @UnSecure
    public static void test(){
        render();
    }
}
