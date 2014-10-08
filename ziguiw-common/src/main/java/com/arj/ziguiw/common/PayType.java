package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-28
 * Time: A.M.9:54
 * 用户支付类型常量类
 */
public class PayType {
    public static final int POINTS_CHARGE = 1;
    public static final int ANNUAL_FEE = 2;

    public static final String POINT = "point";
    public static final String WEBUSE = "webuse";

    public static Map<Integer,String> map = new HashMap<Integer,String>();

    static {
        map.put(POINTS_CHARGE,"充值积分");
        map.put(ANNUAL_FEE,"缴纳年费");
    }
}
