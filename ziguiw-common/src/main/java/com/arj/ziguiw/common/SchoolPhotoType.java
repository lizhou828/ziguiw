package com.arj.ziguiw.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午11:16
 */
public class SchoolPhotoType {

    public static final int PHOTO = 0;//照片

    public static final int VIDEO = 1;//视频

    public final static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(PHOTO, "照片");
        map.put(VIDEO, "视频");
    }
}
