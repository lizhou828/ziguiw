package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-9
 * Time: P.M 5:44
 */
public class Status {

    public final static int UNVERIFIED = 0;

    public final static int DELETED = 1;

    public final static int OK = 2;

    public final static int FAIL = -1;

    public final static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(UNVERIFIED, "未审核");
        map.put(DELETED, "已删除");
        map.put(OK, "审核通过");
        map.put(FAIL, "审核失败");
    }

}
