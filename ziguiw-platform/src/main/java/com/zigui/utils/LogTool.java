package com.zigui.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-11
 * Time: 上午9:34.
 */
public class LogTool {

    private static final Log log = LogFactory.getLog(LogTool.class);

    public static Log getLog()
    {
        return  log;
    }
}
