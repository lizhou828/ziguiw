package controllers;

import com.arj.ziguiw.common.JedisPoolSource;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.UserBaseType;
import models.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.Play;
import play.mvc.Before;
import play.mvc.Http;


public class Application extends CRUD {

    protected static String uploadDir = Play.configuration.getProperty("upload.dir", "/data/upload");

    private static final Log log = LogFactory.getLog(Application.class.getName());

    protected static final JedisPoolSource jedisPoolSource = new JedisPoolSource(
            (String) Play.configuration.get("redis.host"),
            null);

    @Before
    public static void before() {
        /* log the url */
        MDC.put("url", request.url.replaceAll("'", "‘"));
        Http.Header userAgent = request.headers.get("user-agent");
        Http.Header referer = request.headers.get("referer");
        if (userAgent != null) MDC.put("userAgent", userAgent.value().replace("'", "‘"));
        if (referer != null) MDC.put("referer", referer.value().replace("'", "‘"));

        String userName = (String) DsisSecurity.connected();
        UserBase userBase = null;
        SchoolAdmin schoolAdmin = null;
        if (userName != null) {
            MDC.put("username", userName);
            renderArgs.put("username", userName);
            userBase = UserBase.find("byNickName", userName).first();
            renderArgs.put("user", userBase);
            schoolAdmin = SchoolAdmin.findByAccount(userName);
            renderArgs.put("schoolAdmin",schoolAdmin);
        }

        String xxId = request.params.get("xxId");
        String schoolId = request.params.get("schoolId");
        if (schoolId != null && xxId == null) {
            School school = School.findById(Long.valueOf(schoolId));
            xxId = school.xxId;
        }
        if (xxId == null && userName != null) {
            if (userBase != null && userBase.type == UserBaseType.SCHOOL) {
                xxId = userName;
            }
            if (schoolAdmin != null) {
                xxId = schoolAdmin.school.xxId;
            }
        }
        if (xxId != null) {
            School school = School.find("byXxId", xxId).first();
            SchoolExtend schoolExtend = SchoolExtend.findBySchoolId(school.id);
            renderArgs.put("xxId", xxId);
            renderArgs.put("userBase", UserBase.find("from UserBase where type = ? and foreignKey = ?",
                    UserBaseType.SCHOOL, school.id).first());
            renderArgs.put("school", school);
            renderArgs.put("schoolExt", schoolExtend);
        }
        String classId = request.params.get("classId");
        if (classId == null){
            classId = session.get("classId");
        }
        if (classId != null) {
            long _classId = Long.valueOf(classId);
            SchoolClazz schoolClazz = SchoolClazz.findById(_classId);
            ClassExtend classExtend = ClassExtend.findByClassId(_classId);
            renderArgs.put("classId",_classId);
            renderArgs.put("schoolClass",schoolClazz);
            renderArgs.put("classExt",classExtend);
            session.put("classId", _classId);
        }
        renderArgs.put("ctx", Play.configuration.get("static.domain") == null ? "" : Play.configuration.get("static.domain"));
    }

    @Before (only = {"SchoolNews.create", "SchoolPhotos.create", "ClassNews.create", "ClassPhotos.create"}, priority = 1)
    public static void sendMessage2Admin() {
        SchoolAdmin schoolAdmin = (SchoolAdmin) renderArgs.get("schoolAdmin");
        if (schoolAdmin != null) {
            String username = schoolAdmin.school.name;
            String message = null;
            if ("SchoolNews.create".equals(request.action)) {
                message = username + "创建了一篇学校新闻，请审核";
            }
            if ("SchoolPhotos.create".equals(request.action)) {
                message = username + "上传了一张学校照片，请审核";
            }
            if ("ClassNews.create".equals(request.action)) {
                message = username + "创建了一篇班级新闻，请审核";
            }
            if ("ClassPhotos.create".equals(request.action)) {
                message = username + "上传了一张班级照片，请审核";
            }
            if (message != null) {
                jedisPoolSource.rpush(Queues.ADMINISTRATORS_NOTY, message);
            }
        }
    }

    public static void index() {
       render();
    }

//    public static void delete(String id, String fieldName) throws Exception {
//        if (id == null || fieldName == null) {
//            throw new IllegalArgumentException("id and fieldName is null");
//        }
//        ObjectType type = ObjectType.get(getControllerClass());
//        Model model = type.findById(id);
//        Field field = model.getClass().getField(fieldName);
//        field.set(model, Status.DELETED);
//        model._save();
//        redirect(request.controller + ".list");
//    }

    public static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    public static Integer getPageSize() {
        return request.params.get("pageSize") == null ? getDefaultPageSize() : Integer.valueOf(request.params.get("pageSize"));
    }
}