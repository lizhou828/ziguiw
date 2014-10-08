package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 上午9:44
 */
public class ClassTemplateColor {

         public final static int BLUE = 0;

         public final static int YELLOW = 1;

         public final static int GREEN = 2;

         public final static Map<Integer, String> map = new HashMap<Integer, String>();

         static {
             map.put(BLUE,"蓝色");
             map.put(YELLOW,"黄色");
             map.put(GREEN,"绿色");
         }

}
