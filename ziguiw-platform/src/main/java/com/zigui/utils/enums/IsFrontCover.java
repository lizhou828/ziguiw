package com.zigui.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-27
 * Time: 上午11:35
 */
public enum IsFrontCover {
    YES(1, "设为封面"),
    NO(0, "非封面");

    private long code;
    private String displayName;
    private static Map list;

    IsFrontCover(long code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static IsFrontCover parse(long code) {
        if (code == 0)
            return IsFrontCover.NO;
        if (code == 1)
            return IsFrontCover.YES;
        return null;
    }

    public static Map getList() {
        if (list == null) {
            list = new HashMap();
            for (IsFrontCover isFrontCover : IsFrontCover.values()) {
                list.put(isFrontCover.code, isFrontCover.displayName);
            }
        }
        return list;
    }

    public long getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
