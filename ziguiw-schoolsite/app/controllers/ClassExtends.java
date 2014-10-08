package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import models.ClassExtend;
import models.QueryParams;
import play.db.Model;
import play.mvc.Http;
import play.mvc.Util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 上午10:13
 */
public class ClassExtends extends Application{

    public static void show(Long classId) {
        if (classId == null) classId = Long.valueOf(session.get("classId"));
        ClassExtend classExtend = ClassExtend.findByClassId(classId);
        render(classExtend);
    }

    public static void sshow(Long classId) {
        if (classId == null) classId = Long.valueOf(session.get("classId"));
        ClassExtend classExtend = ClassExtend.findByClassId(classId);
        render(classExtend);
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        queryParams.where = " exists(select 0 from SchoolClazz sc where sc.ztai=70 and sc.id = class_id and sc.xxId = '" +
                renderArgs.get("xxId") + "') ";
    }

    public static void ssave(String id, ClassExtend classExtend) {
        String classId = session.get("classId");
        ClassExtend _classExtend;
        _classExtend = ClassExtend.findByClassId(Long.valueOf(classId));
        _classExtend.color = classExtend.color;
        _classExtend.hot = classExtend.hot;
        _classExtend.save();
        sshow(Long.valueOf(classId));
    }

    @Util
    public static void beforeSave(Model object) {
        ClassExtend classExtend = (ClassExtend)object;
        File file = Http.Request.current().params.get("file", File.class);
        if(file != null){
            classExtend.banner = FileUtils.copyImage(file,file.getName(),uploadDir);
        }
    }
}
