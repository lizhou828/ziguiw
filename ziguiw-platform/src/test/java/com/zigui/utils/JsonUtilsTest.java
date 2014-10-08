package com.zigui.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-18
 * Time: A.M. 10:31
 */
public class JsonUtilsTest {
    @Test
      /*one json object to one java object*/
    public void getObject(){
        try{
            String url="http://10.0.1.55:8088/DSIS_zgw_syndata/bussiness.htm?table=homeworkmsg&pages=1&pagesize=15";
            String encoding="UTF-8";
            String content=HttpUtils.getResponse(url,encoding);
            Map<String,Object> map=JsonToJavaUtils.getObject(content);

            int nowpage=(Integer)map.get("nowpage");
            int pagesize= (Integer)map.get("pagesize");
            int rectotal=(Integer)map.get("rectotal");
            System.out.println(nowpage+","+pagesize+","+rectotal);

            JSONArray jsonArray=(JSONArray)map.get("data");
            if(jsonArray.size() == 0 ||jsonArray== null ){
                System.out.println("未查询到相关信息。。。。");
            }
            JSONObject jsonObject;
            for(int i=0;i<jsonArray.size();i++){
                jsonObject=jsonArray.getJSONObject(i);
                System.out.println("jsonArray.getJSONObject()=记录流水号" + jsonObject.get("R")+",短信id=" +
                        ""+jsonObject.get("SMS_PUBLISH_ID")+",学生卡号="+jsonObject.get("CARDID")+"," +
                        "学生姓名="+jsonObject.get("XS_XMING")+",作业="+jsonObject.get("MSM_CONTENT"));
            }
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*more json objects to more java objects*/

//    public void getObjectList(){
//        String url=" http://10.0.1.55:8088/DSIS_zgw_syndata/synchon.htm?table=teacher&pages=1&pagesize=10";
//        String encoding="UTF-8";
//        String content=HttpUtils.getResponse(url,encoding);
//        System.out.println("content="+content);
//        JSONObject jsonObject=JsonUtils.getObject(content);
//
//
//    }


}
