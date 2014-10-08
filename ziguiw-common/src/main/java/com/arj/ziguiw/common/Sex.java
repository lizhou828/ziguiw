package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-15
 * Time: A.M 10:52
 */
public class Sex {

    public static final int MAN = 1;
    public static final int WOMEN = 0;

    public static final Map<Integer, String> map = new HashMap();

    static {
        map.put(MAN, "男");
        map.put(WOMEN, "女");
    }
}
