package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: A.M.9:55
 */
public class ResourceCheckType {

    private static final int UNCHECKED = 0;
    private static final int CHECKED = 1;
    private static final int NOT_PASS = 2;

    private static final Map<Integer,String> map = new HashMap<Integer, String>();

    static {
        map.put(UNCHECKED, "未审核");
        map.put(CHECKED, "审核");
        map.put(NOT_PASS, "未通过");
    }
}
