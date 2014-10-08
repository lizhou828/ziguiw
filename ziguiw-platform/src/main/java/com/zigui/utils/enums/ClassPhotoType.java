package com.zigui.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-27
 * Time: 上午11:02
 */
public enum ClassPhotoType {
    PHOTO(0,"照片"),
    VIDEO(1,"视频");

    private long code;
    private String displayName;
    private static Map list;

    ClassPhotoType(long code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static ClassPhotoType parse(long code)
    {
        if(code == 0)
            return ClassPhotoType.PHOTO;
        if(code == 1)
            return ClassPhotoType.VIDEO;
        return null;
    }

    public static Map getList() {
        if(list == null)
        {
            list = new HashMap();
            for(ClassPhotoType classPhotoType : ClassPhotoType.values())
            {
                list.put(classPhotoType.code,classPhotoType.displayName);
            }
        }
        return list;//singleton is unnecessary,this function run too fast and init a little data
    }



    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
