package com.arj.ziguiw.datasynch;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-16
 * Time: A.M11:09
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * <pre>
     *     Map<String, Object> map = new HashMap<Object, String>();
     *     map.put("1", "1");
     *     List<String> list = new ArrayList<String>();
     *     list.add("e1");
     *     list.add("e2");
     *     map.put("2", list);
     *     String json = JsonUtil.parse(map);
     *     //json value is :
     * <pre>
     * @param obj Object instance
     * @return JSON String
     * @throws java.io.IOException IOException
     */
    public static String parse(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     *
     * @param json's String
     * @param clazz of the json's Type
     * @return Object with json's format
     * @throws java.io.IOException IOException
     */
    public static Object format(String json, Class clazz) throws IOException {
        return objectMapper.readValue(json,clazz);
    }
}
