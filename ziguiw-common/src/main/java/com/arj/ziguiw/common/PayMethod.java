package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-26
 * Time: P.M.2:32
 * 年费支付方式
 */
public class PayMethod {
    public static final int ALIPAY = 1;
    public static final int TENPAY = 2;
    public static final int BILL = 3;
    public static final int SCHOOL_ACCEPT = 4;

    public static Map<Integer,String> map = new HashMap<Integer,String>();

    static{
        map.put(ALIPAY,"支付宝");
        map.put(TENPAY,"财付通");
        map.put(BILL,"快钱");
        map.put(SCHOOL_ACCEPT,"学校代收");

    }
}
