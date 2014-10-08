package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: 下午2:40
 */
public class StudentInSchoolState {
    public static final String STUDENT_STATE_NORMAL="1";

    public static final String STUDENT_STATE_QUIT="2";

    public static final String STUDENT_STATE_BREAK="3";

    public static final String STUDENT_STATE_TURN="4";

    public static final String STUDENT_STATE_RETURN = "5";

    public static Map<String,Object> map=new HashMap<String,Object>();

    static {
        map.put(STUDENT_STATE_NORMAL, "在校");
        map.put(STUDENT_STATE_QUIT, "退学");
        map.put(STUDENT_STATE_BREAK, "休学");
        map.put(STUDENT_STATE_TURN, "转学");
        map.put(STUDENT_STATE_RETURN, "复学");
    }
}
