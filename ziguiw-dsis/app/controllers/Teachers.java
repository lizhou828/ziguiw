package controllers;

import models.Teacher;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 上午9:13
 */
public class Teachers extends Application{

    public static void queryStudentsByClassId(Integer bjId){
        List studentList=Teacher.findStudentByClazzId(bjId);
        renderJSON(studentList);
    }

}
