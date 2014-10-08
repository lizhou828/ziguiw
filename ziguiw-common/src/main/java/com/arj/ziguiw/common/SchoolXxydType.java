package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-1
 * Time: 上午11:59
 */
public class SchoolXxydType {
        /*优秀作文*/
        public static final int  COMPOSITION = 0;
        /*学习攻略*/
        public static final int  STRATEGY = 1;
        /*重点难点解答*/
        public static final int  ANSWER = 2;
        /*在线学习*/
        public static final int  STUDY = 3;
        /*教育资源*/
        public static final int  RESOURCE = 4;

        public final static Map<Integer, String> map = new HashMap<Integer, String>();

        static {
            map.put(COMPOSITION,"优秀作文");
            map.put(STRATEGY,"学习小攻略");
            map.put(ANSWER,"重点难点解答");
            map.put(STUDY,"在线学习");
            map.put(RESOURCE,"教育资源库");
        }

}
