package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午11:09
 */
public class ClassNewType {

    public final static int NEWS = 1;
    public final static int BULLETIN = 2;

    public final static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(NEWS, "班级动态");
        map.put(BULLETIN, "班级公告");
    }
}
