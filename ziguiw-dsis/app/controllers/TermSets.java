package controllers;

import controllers.modules.cas.UnSecure;
import models.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-28
 * Time: P.M.2:49
 */

public class TermSets extends Application {

    @UnSecure
    public static void findExam(Integer termId,Integer bjId){
        Clazz clazz=Clazz.findById(bjId);
        List<Exam> examList = TermSet.findExam(termId, clazz.bjId);;
        renderJSON(examList);
    }
    @UnSecure
    public static void findTermByXsId(Long xsId){
        Student student = Student.findById(xsId);
        School school = School.find("byXxId",student.xxId).first();
        List<TermSet> termSetList = TermSet.findAllBySchool(school.xxId);
        renderJSON(termSetList);
    }
}
