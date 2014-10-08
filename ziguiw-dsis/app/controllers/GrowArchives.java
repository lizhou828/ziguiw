package controllers;


import models.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-25
 * Time: P.M.3:47
 */

public class GrowArchives extends Application {
    public static void tlist(Integer bjId, Integer xsId, String startTime, String endTime) {

        render();

    }
    public static void plist(Integer xsId, Integer isAjax) {
        UserBase userBase = (UserBase)renderArgs.get("userBase");
        Map<String,Object> map = getChildrenInfo(userBase,xsId);
        Student student = (Student)map.get("student");
        List<Student> studentList = (List<Student>)map.get("studentList");
        Page page=GrowArchive.pageQuery(student.xsId.intValue(),getPage(),3);
        if( isAjax  != null ){
            render("GrowArchives/plist_ajax.html",student,page);
        }else{
            render(studentList,student,page);
        }


    }
}
