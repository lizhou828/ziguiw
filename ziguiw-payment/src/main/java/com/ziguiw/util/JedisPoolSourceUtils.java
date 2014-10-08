package com.ziguiw.util;

import com.arj.ziguiw.common.JedisPoolSource;

/**
 * User: Liujy
 * Date: 13-5-13
 * Time: 上午10:02
 */
public class JedisPoolSourceUtils {

    private static final JedisPoolSource jedisPoollSource = new JedisPoolSource((String)ReadUtil.getValue("redis.host"),null);

    public static JedisPoolSource getJedisPoollSource(){
        return jedisPoollSource;
    }
}
