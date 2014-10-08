package com.zigui.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import com.zigui.utils.ConfigUtils;
import com.zigui.utils.Page;

public class BussnissDataServiceImpl {
	
	public Page<Map> queryBusnissData(String type, int currentPage, int countPerPage, String queryType, String queryId, String orderBy, boolean isAsc){
		//正式环境192.168.0.169:8089 测试环境：10.0.1.55:8088
		String query = ConfigUtils.getStringByKey("business_data_syn_url", "http://10.0.1.55:8088/DSIS_zgw_syndata/") + "bussiness.htm?table=" + type;
		query = query + "&pages=" + currentPage;
		query = query + "&pagesize=" + countPerPage;
		if(StringUtils.equals(queryType, "student")){
			query = query + "&studentid=" + queryId;
		}
		
		if(StringUtils.equals(queryType, "teacher") && StringUtils.equals(type, "attendanceRec")){
			query = query + "&bjid=" + queryId;
		}
		
		if(StringUtils.equals(queryType, "school") && StringUtils.equals(type, "schoolnotice")){
			query = query + "&schoolid=" + queryId;
		}
		
		GetMethod getMethod = new GetMethod(query);

		List<Map> datas = new ArrayList<Map>();
		int totalCount = 0;
		try{
			HttpClient htpc = new HttpClient();
			htpc.executeMethod(getMethod);
			
			byte[] responseBody = getMethod.getResponseBody();
			String responseStr = new String(responseBody, "UTF-8");
	
			JSONObject jsonObj = JSONObject.fromObject(responseStr);
			
			totalCount = NumberUtils.toInt(jsonObj.getString("rectotal"));
			
	
			JSONArray data = jsonObj.getJSONArray("data");
			int dataLength = data.size();
			
			for(int i = 0; i < dataLength; i ++){
				JSONObject tempJson = JSONObject.fromObject(data.get(i));
				
				datas.add(tempJson);
			}
		}catch(Exception e){
			
		}
		
		return new Page((currentPage - 1) * countPerPage, totalCount, countPerPage, datas, "type=" + type + "&countPerPage=" + countPerPage + "&currentPage=" + currentPage + "&queryType=" + queryType + "&queryId=" + queryId);
	}
	
	public static void main(String[] args){
		
		System.out.println(DigestUtils.md5Hex("123456"));
	}

}
