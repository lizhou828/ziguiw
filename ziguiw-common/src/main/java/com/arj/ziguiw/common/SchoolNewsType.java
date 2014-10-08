package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-9
 * Time: P.M 3:02
 */
public class SchoolNewsType {
    public final static int NEWS = 1;
    public final static int BULLETIN = 2;

    public final static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(NEWS, "新闻动态");
        map.put(BULLETIN, "校园公告");
    }
}
