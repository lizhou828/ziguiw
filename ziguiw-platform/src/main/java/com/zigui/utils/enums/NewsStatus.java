package com.zigui.utils.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-19
 * Time: 下午2:01
 */
public enum NewsStatus {
    WAITING_AUDIT(1, "等待审核"),
    AUDIT_OK(2, "通过审核"),
    DELETED(-1, "已经删除"),
    AUDIT_FAILED(-2, "审核失败");

    private long code;
    private String displayName;
    private static Map list;

    NewsStatus(long code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public long getCode() {
        return code;
    }

    public static NewsStatus parse(long code) {

        if (code == 1)
            return NewsStatus.WAITING_AUDIT;
        if (code == 2)
            return NewsStatus.AUDIT_OK;
        if (code == -1)
            return NewsStatus.DELETED;
        if (code == -2)
            return NewsStatus.AUDIT_FAILED;

        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map getList() {
        if(list == null)
        {
            list = new HashMap();
            for(NewsStatus newsStatus : NewsStatus.values())
            {
                list.put(newsStatus.code,newsStatus.displayName);
            }
        }
        return list;//singleton is unnecessary,this function run too fast and init a little data
    }
}
