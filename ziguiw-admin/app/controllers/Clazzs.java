package controllers;

import models.Clazz;
import models.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-27
 * Time: P.M.4:09
 */
public class Clazzs extends Application{
    public static void student(Integer bjId) {
        List<Student> list = Clazz.findStudentByBjId(bjId);
        renderJSON(list);
    }
}
