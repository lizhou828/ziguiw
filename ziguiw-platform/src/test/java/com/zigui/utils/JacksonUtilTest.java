package com.zigui.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-6
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class JacksonUtilTest {
    @Test
    public void test()throws IOException{

        String url="http://10.0.1.55:8088/DSIS_zgw_syndata/business/comexamList.htm?studentid=7751&examId=948&startTime=1314806400000&endTime=1327939200000";
        String jsonContent=HttpUtils.getResponse(url, "UTF-8");

        ObjectMapper objectMapper=new ObjectMapper();
        Map map = objectMapper.readValue(jsonContent, Map.class);


    }

}
