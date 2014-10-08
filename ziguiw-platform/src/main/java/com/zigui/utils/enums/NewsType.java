package com.zigui.utils.enums;

import org.jfree.util.HashNMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-19
 * Time: 下午2:23
 */
public enum NewsType {
    NEWS(0, "班级动态"),
    ANNOUNCEMENT(1, "公告"),
    HONOR(2, "荣誉"),
    LEARNING_ZONE(3, "学习园地");

    private long code;
    private String displayName;
    private static Map list;

    NewsType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static NewsType parse(long code)
    {
        if(code == 0)
            return NewsType.NEWS;
        if(code == 1)
            return NewsType.ANNOUNCEMENT;
        if(code == 2)
            return NewsType.HONOR;
        if(code == 3)
            return NewsType.LEARNING_ZONE;
        //bad code
        return null;
    }

    public long getCode() {
        return code;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }


    public static Map getList() {
        if(list == null)
        {
            list = new HashMap();
            for(NewsType newsType : NewsType.values())
            {
                list.put(newsType.code,newsType.displayName);
            }
        }
        return list;//singleton is unnecessary,this function run too fast and init a little data
    }


}
