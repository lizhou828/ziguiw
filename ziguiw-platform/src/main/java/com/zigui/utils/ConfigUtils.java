package com.zigui.utils;

import com.zigui.service.impl.ConfigCenterServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigUtils {

    private static Log log = LogFactory.getLog(ConfigUtils.class);

    private ConfigCenterServiceImpl configService;
    private static ConcurrentHashMap<String, String> configValues = new ConcurrentHashMap<String, String>();

   /* public void init() {
        log.info("scan the configuration from database!");
        configService = (ConfigCenterServiceImpl) BeanFactoryUtils.getBean("configCenterService");
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                ConfigCenter configCenter = configService.getConfig();
                String content = configCenter.getContent();
                StringReader sr = new StringReader(content);
                BufferedReader br = new BufferedReader(sr);
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        String[] tmp = line.split("<br\\/>");
                        for (String tmpString : tmp) {
                            if (tmpString.startsWith("#")) {
                                continue;
                            } else {
                                String[] keyValue = tmpString.split("=");
                                if (keyValue.length == 2) {
                                    configValues.put(StringUtils.trim(keyValue[0]), StringUtils.trim(keyValue[1]));
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }, 1, 10, TimeUnit.SECONDS);
    }*/

    public static boolean getBoolByKey(String key, boolean defaultValue) {
        String rawValue = configValues.get(key);
        if ("true".equals(rawValue)) {
            return true;
        } else if ("false".equals(rawValue)) {
            return false;
        } else {
            return defaultValue;
        }
    }

    public static int getIntByKey(String key, int defaultValue) {
        return Integer.parseInt(configValues.get(key));

    }

    public static long getLongByKey(String key, long defaultValue) {
        return Long.parseLong(configValues.get(key));
    }

    public static String getStringByKey(String key, String defaultValue) {
        String rawValue = configValues.get(key);
        String result = defaultValue;
        if (StringUtils.isNotEmpty(rawValue)) {
            result = rawValue;
        }
        return result;
    }

    private static Properties _platform_properties = new Properties();
    static {
        //classPath = classPath.substring(0, classPath.lastIndexOf("/"));
        //classPath = classPath.substring(0, classPath.lastIndexOf("/"));
        try {
            _platform_properties.load(ConfigUtils.class.getResourceAsStream("/conf.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * get value from %classpath%/conf.properties by key
     *
     * @param key key of properties
     * @return value of properties
     */
    public static String getProperty(String key) {
        return _platform_properties.getProperty(key);
    }

}

	