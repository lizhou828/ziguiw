package controllers;

import com.arj.ziguiw.common.ClassNewType;
import com.arj.ziguiw.common.SchoolPhotoType;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import play.mvc.With;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-11
 * Time: P.M 5:08
 */
@With(SecureCAS.class)
public class SchoolClazzs extends Application {

    @UnSecure
    public static void plist() {
        List<SchoolClazz> schoolClazzs = SchoolClazz.findByXXId((String) renderArgs.get("xxId"), 70);
        List<SchoolStugrade>  schoolStugrades = SchoolStugrade.findByXxid((String) renderArgs.get("xxId"));
        render(schoolClazzs , schoolStugrades);
    }

    @UnSecure
    public static void pshow(){
        SchoolClazz schoolClazz = (SchoolClazz) renderArgs.get("schoolClass");
        School school =  School.find("byXxId", schoolClazz.xxId).first();
        long classId = schoolClazz.id;
        List<ClassNew> classNews = ClassNew.findByType(classId, ClassNewType.NEWS,6);
        List<ClassNew> classGg = ClassNew.findByType(classId,ClassNewType.BULLETIN,6);
        List<ClassNew> classNewUrl = ClassNew.findByUrl(classId,5);
        List<ClassPhoto> classPhotos = ClassPhoto.findPhoto(classId,4);
        List<ClassPhoto> classVideos = ClassPhoto.findByType(classId, SchoolPhotoType.VIDEO,6);
        List<ClassForum> classForums = ClassForum.findByClassId(classId,8);
        List<SchoolXxyd> schoolXxyds = SchoolXxyd.findByClassId(classId,school.id,8);
        List<SchoolXxyd> schoolXxydsUrl = SchoolXxyd.findByClassUrl(classId,school.id,4);
        ClassPhoto classVideo = ClassPhoto.findNew(classId);
        renderArgs.put("xxId", school.xxId);
        render(school, classNews,classGg,classPhotos,classVideos,schoolClazz,classNewUrl,classForums,schoolXxyds,schoolXxydsUrl,classVideo);
    }

    public static void admin(){
        render();
    }
}
