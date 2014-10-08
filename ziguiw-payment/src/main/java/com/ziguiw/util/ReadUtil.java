package com.ziguiw.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadUtil {
	
	private  static Properties pp = null;
	
	static{
		InputStream ips = ReadUtil.class.getClassLoader().getResourceAsStream("app.properties");
		pp = new Properties();
		try {
			pp.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		return pp.getProperty(key);
	}
}
