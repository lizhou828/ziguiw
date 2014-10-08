package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.SchoolNewsType;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserBaseType;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Before;
import play.mvc.Http;
import play.mvc.Util;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-7
 * Time: P.M 5:07
 */
@With(SecureCAS.class)
public class SchoolNews extends Application {
    private static final Log logger = LogFactory.getLog(SchoolNews.class);

    @UnSecure
    public static void plist(Integer schoolNewsType) {
        Page<SchoolNew> page = SchoolNew.findPage(getPage(), 15, ((School) renderArgs.get("school")).id, schoolNewsType);
        List<SchoolNew> list = null;
        if(schoolNewsType == SchoolNewsType.NEWS){
            list = SchoolNew.findLastNewessByType(((School) renderArgs.get("school")).id,SchoolNewsType.BULLETIN,8);
        }
        if(schoolNewsType == SchoolNewsType.BULLETIN){
            list = SchoolNew.findLastNewessByType(((School) renderArgs.get("school")).id,SchoolNewsType.NEWS,8);
        }
        List<SchoolClazz> classes = SchoolClazz.findByXXId(((School) renderArgs.get("school")).xxId,70);
        render(page, schoolNewsType , list , classes);
    }

    @UnSecure
    public static void pshow(Long newsId) {
        renderArgs.put("schoolClass",null);
        SchoolNew schoolNew = SchoolNew.findById(newsId);
        if ( schoolNew.visitCount == null) { schoolNew.visitCount = 1;}
        else { schoolNew.visitCount += 1;}
        schoolNew.save();
        List<SchoolNew> list;
        if(schoolNew.type == SchoolNewsType.NEWS){
           list = SchoolNew.findLastNewessByType(schoolNew.school.id,SchoolNewsType.BULLETIN,8);
        }else{
           list = SchoolNew.findLastNewessByType(schoolNew.school.id,SchoolNewsType.NEWS,8);
        }
        String xxId = schoolNew.school.xxId;
        if (xxId != null) {
            School school = School.find("byXxId", xxId).first();
            SchoolExtend schoolExtend = SchoolExtend.findBySchoolId(school.id);
            List<SchoolClazz> clazzs = SchoolClazz.findByXXId(xxId,70);
            renderArgs.put("classes",clazzs);
            renderArgs.put("xxId", xxId);
            renderArgs.put("userBase", UserBase.find("from UserBase where type = ? and foreignKey = ?",
                    UserBaseType.SCHOOL, school.id).first());
            renderArgs.put("school", school);
            renderArgs.put("schoolExt", schoolExtend);
        }
        List<SchoolClazz> classes = SchoolClazz.findByXXId(((School) renderArgs.get("school")).xxId,70);
        render(schoolNew,list,classes);
    }

    public static void list( String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "createTime";
        if (order == null) order = "DESC";
        String condition = String.format("school.id = %s and status != %s",
                ((School)(renderArgs.get("school"))).id,Status.DELETED);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }

    @Util
    public static void beforeCreate(Model object) {
        SchoolNew newse = (SchoolNew) object;
        UserBase userBase = (UserBase)renderArgs.get("user");
        newse.user = userBase;
        File file = Http.Request.current().params.get("file", File.class);
        logger.info(String.format("The schoolnews file is %s",file));
        if (file != null) {
            newse.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL , ImageSize.SKL});
            logger.info(String.format("The schoolnews return url is %s",newse.url));
        }
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeSave(Model object) {
        SchoolNew newse = (SchoolNew) object;
        File file = Http.Request.current().params.get("file", File.class);
        logger.info(String.format("The schoolnews file is %s",file));
        if (file != null) {
            newse.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSL , ImageSize.SKL});
            logger.info(String.format("The schoolnews return url is %s",newse.url));
        }
        newse.status = Status.UNVERIFIED;
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }


    public static void delete(Long id){
        SchoolNew object = SchoolNew.findById(id);
        notFoundIfNull(object);
        object.status = Status.DELETED;
        object.save();
        redirect("/SchoolNews/list");
    }

    @Before(only = {"delete","save"})
    public static void before(){
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        String id = request.params.get("id");
        SchoolNew object = SchoolNew.findById(Long.valueOf(id));
        if(object.status == Status.OK){
            renderArgs.put("error","操作失败，新闻已审核!");
            render("SchoolNews/show.html",object,type);
        }
    }

    @Before(only = {"create"})
    public static void before1(SchoolNew object){
          String userName = (String) DsisSecurity.connected();
          SchoolAdmin schoolAdmin = SchoolAdmin.findByAccount(userName);
          School school = School.findById(schoolAdmin.school.id);
          ObjectType type = ObjectType.get(getControllerClass());
          notFoundIfNull(type);
          String error;
          logger.info(String.format("school new create param type = %s, reprinted = %s, title = %s, content = %s",object.type,object.reprinted,object.title,object.content));
          if(object.type == null){
              error = "请选择类型";
              flash.put("error",error);
              render("/SchoolNews/blank.html",object,type,school);
          }
          if(object.reprinted == null){
              error = "请选择是否转载";
              flash.put("error",error);
              render("/SchoolNews/blank.html",object,type,school);
          }
    }
}
