package com.zigui.utils.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserDiaryType {
	SCHOOL_NEWS(5, "校园新闻"), SCHOOL_ANNOUNCEMENT(6, "校园公告"), ADMISSSIONS_INFOMATION(
			7, "招生信息");

	private int code;
	private String displayName;
	private static Map list;

	private UserDiaryType(int code, String displayName) {
		this.code = code;
		this.displayName = displayName;
	}

	public static UserDiaryType parse(int code) {
		if (code == 5)
			return UserDiaryType.SCHOOL_NEWS;
		if (code == 6)
			return UserDiaryType.SCHOOL_ANNOUNCEMENT;
		if (code == 7)
			return UserDiaryType.ADMISSSIONS_INFOMATION;
		return null;
	}

	public static Map getList() {
		if (list == null) {
			list = new HashMap();
			for (UserDiaryType userDiaryType : UserDiaryType.values()) {
				list.put(userDiaryType.code, userDiaryType.displayName);
			}
		}
		return list;// singleton is unnecessary,this function run too fast and
					// init a little data
	}

	public int getCode() {
		return code;
	}

	public String getDisplayName() {
		return displayName;
	}
}
