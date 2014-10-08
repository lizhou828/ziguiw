package com.zigui.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LogUtil {
	public static Log logger = LogFactory.getLog(LogUtil.class);
//	private static Logger log = Logger.getLogger("Log");
	
	public static void main(String[] arg0){

		logger.debug("it is the debug info");
	}
}
