package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liubingbing
 * Date: 13-1-12
 * Time: P.M 10:25
 */
public class UserBaseType {

    public static final int VISITOR = 0;

    public static final int SCHOOL = 1;

    public static final int TEACHER = 2;

    public static final int PARENT = 3;

    public static final int STUDENT = 4;

    public static final int SCHOOL_EDITOR = 5;

    public static final Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(VISITOR, "游客");
        map.put(SCHOOL, "学校");
        map.put(TEACHER, "老师");
        map.put(PARENT, "家长");
        map.put(STUDENT, "学生");
        map.put(SCHOOL_EDITOR, "驻校编辑");
    }


}

