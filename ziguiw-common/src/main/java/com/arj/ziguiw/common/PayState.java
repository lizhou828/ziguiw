package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-27
 * Time: A.M.10:48
 * 支付状态
 */

public class PayState {
    public static final int NOT_PAYED = 0;
    public static final int PAYED = 1;

    public static Map<Integer,String> map = new HashMap<Integer,String>();

    static{
        map.put(NOT_PAYED,"未支付");
        map.put(PAYED,"已支付");
    }
}