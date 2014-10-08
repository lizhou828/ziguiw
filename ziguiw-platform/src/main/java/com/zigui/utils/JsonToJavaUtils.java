package com.zigui.utils;

import com.zigui.exception.ErrorCode;
import com.zigui.exception.QueryBusinessDataException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-22
 * Time: P.M.1:43
 */
/*This util class is used to transform a jason object (or objects) to a java object from database pages.
* The object which you want to fetch with is in map
* */
public class JsonToJavaUtils {
    public static Map<String,Object> getObject(String content){
        try{

            System.out.println("JASONUtilsTest's getObject is running.... content="+content);

//            if(content.indexOf("[")!=-1){
//                content=content.replace("[", "");
//            }        if(content.indexOf("]")!=-1){
//                content=content.replace("]", "");
//            }
            content=content.replaceAll("\"","'");
            Map<String,Object> map=new HashMap<String, Object>();

            JSONObject json= JSONObject.fromObject(content);
            map.put("nowpage",json.get("nowpage"));
            map.put("pagesize",json.get("pagesize"));
            map.put("rectotal", json.get("rectotal"));
            map.put("data", json.get("data"));
            map.put("subList",json.get("subList"));


//            JSONArray jsonArray=(JSONArray)json.get("data");
//            Map<String ,Object> map1=(Map)jsonArray.get(0);
//            System.out.println("map1.get(\"R\")="+map1.get("R").toString());


            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new QueryBusinessDataException(e, ErrorCode.RPC);
        }
    }

        public <T> List<T> getJavaCollection(T clazz,String jsons) {
            List<T> objs=null;
            JSONArray jsonArray=(JSONArray) JSONSerializer.toJSON(jsons);
            if(jsonArray!=null){
                objs=new ArrayList<T>();
                List list=(List)JSONSerializer.toJava(jsonArray);
                for(Object o:list){
                    JSONObject jsonObject=JSONObject.fromObject(o);
                    T obj=(T)JSONObject.toBean(jsonObject, clazz.getClass());
                    objs.add(obj);
                }
            }
            return objs;
        }





}
