package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-18
 * Time: A.M.10:52
 */
public class ConsumeType {
    private static final String CONSUME = "0";
    private static final String CHARGE = "1";
    private static final String WITHDRAWAL = "2";
    private static final String  ALLOWANCE= "3";
    private static final String  ILLEGAL_EXIT= "14";

    public static Map<String,Object> map = new HashMap<String ,Object>();

    static {
        map.put(CONSUME,"消费");
        map.put(CHARGE,"充值");
        map.put(WITHDRAWAL,"取款");
        map.put(ALLOWANCE,"补助");
        map.put(ILLEGAL_EXIT,"非法退出");
    }
}
