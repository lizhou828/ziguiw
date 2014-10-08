package controllers;

import models.School;
import models.SchoolClazz;
import models.TermSet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 下午3:22
 */
public class Schools extends Application{

    public static void clazz(String xxId) {
        List<SchoolClazz> list = SchoolClazz.findByXXId(xxId, 70);
        renderJSON(list);
    }
    public static void term(String xxId) {
        List<TermSet> list = TermSet.findBySchoolId(xxId);
        renderJSON(list);
    }

    public static void getClass(Long id){
        School school = School.findById(id);
        List<SchoolClazz> list = SchoolClazz.findByXXId(school.xxId, 70);
        renderJSON(list);
    }
}
