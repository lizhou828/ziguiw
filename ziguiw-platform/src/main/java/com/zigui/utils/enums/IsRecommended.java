package com.zigui.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum IsRecommended {
	
	YES(1L,"设为推荐"),
	NO(0L,"非推荐");
	
	private Long code;
	private String displayName;
	private static Map list;
	
	IsRecommended(Long code, String displayName)
	{
		this.code = code;
		this.displayName = displayName;
	}
	
	public static IsRecommended parse(long code) {
        if (code == 0)
            return IsRecommended.NO;
        if (code == 1)
            return IsRecommended.YES;
        return null;
    }

    public static Map getList() {
        if (list == null) {
            list = new HashMap();
            for (IsRecommended isRecommended : IsRecommended.values()) {
                list.put(isRecommended.code, isRecommended.displayName);
            }
        }
        return list;
    }

	public Long getCode() {
		return code;
	}

	public String getDisplayName() {
		return displayName;
	}	
	
    
}
