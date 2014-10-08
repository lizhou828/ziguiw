package controllers;

import com.arj.ziguiw.common.SchoolNewsType;
import com.arj.ziguiw.common.SchoolPhotoType;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import play.mvc.With;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 13-1-6
 * Time: P.M 4:27
 */
@With(SecureCAS.class)
public class Schools extends Application {

    @UnSecure
    public static void plist() {
        Page page = School.findIndex(getPage(), getPageSize());
        List<SchoolNew> schoolNewses = SchoolNew.findLastNewessByType(SchoolNewsType.NEWS, 10);
        render(page, schoolNewses);
    }

    @UnSecure
    public static void pshow() {
        session.remove("classId");
        renderArgs.put("schoolClass",null);
        School school = School.find("byXxId", renderArgs.get("xxId")).first();
        SchoolExtend schoolExtend = SchoolExtend.findById(school.id);
        List<SchoolNew> schoolNewses = SchoolNew.findLastNewessByType(school.id, SchoolNewsType.NEWS, 6);
        List<SchoolNew> schoolNewsBulletins = SchoolNew.findLastNewessByType(school.id, SchoolNewsType.BULLETIN, 6);
        List<SchoolPhoto> schoolPhotos = SchoolPhoto.findByRecommend(school.id, 10086 ,SchoolPhotoType.PHOTO);
        List<SchoolPhoto> schoolVideos = SchoolPhoto.findSchoolPhotos(school.id, 8 ,SchoolPhotoType.VIDEO,null);
        List<SchoolNew> schoolNewsUrl = SchoolNew.findByUrl(school.id,5);
        List<SchoolXxyd> schoolXxyds = SchoolXxyd.findBySchoolId(school.id,8);
        List<SchoolXxyd> schoolXxydUrl = SchoolXxyd.findUrl(school.id,4);
        render( schoolExtend, schoolNewses, schoolNewsBulletins,schoolPhotos,schoolVideos,schoolXxyds,schoolNewsUrl,schoolXxydUrl);
    }

    @UnSecure
    public static void pdesc() {
        List<SchoolTeacher> schoolTeachers = SchoolTeacher.findLastSchoolTeachers(((School) renderArgs.get("school")).id,1000000);
        render(schoolTeachers);
    }

    @UnSecure
    public static void pcontact(){
        School school = School.find("byXxId",renderArgs.get("xxId")).first();
        SchoolExtend schoolExtend = SchoolExtend.findById(school.id);
        render(schoolExtend,school);
    }

    public static void admin() {
        render();
    }

}
