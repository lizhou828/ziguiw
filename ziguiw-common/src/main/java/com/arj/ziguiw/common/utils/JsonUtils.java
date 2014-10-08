package com.arj.ziguiw.common.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-15
 * Time: P.M 2:09
 */
public class JsonUtils {

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
        if(json != null && !"".equals(json)){
            return objectMapper.readValue(json,clazz);
        }
        return new HashMap<String,Object>();
    }

    /**
     * <pre>
     *
     *     User.java :
     *     class User {
     *         int id;
     *         String name;
     *     }
     *     String json = "[{id:1,name:aaa},{id:2,name:bbb}]";
     *
     *     List<User> users = JsonUtils.formatArray(json, User.class);
     * </pre>
     *
     * @param json the json String
     * @param clazz the class of array's element
     * @param <T> T
     * @return collection
     * @throws IOException IOException
     */
    public static <T> List<T> formatArray(String json, Class<T> clazz) throws IOException {
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        return objectMapper.readValue(json, typeFactory.constructCollectionType(ArrayList.class, clazz));
    }


}
