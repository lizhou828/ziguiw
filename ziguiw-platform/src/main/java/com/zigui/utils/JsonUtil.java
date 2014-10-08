package com.zigui.utils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.SouceStugrade;
import com.zigui.domain.SouceSubject;
import com.zigui.domain.SouceVersion;
import com.zigui.domain.SourceType;
import com.zigui.domain.UserBase;

/**
 *
 */
public class JsonUtil {

    /**
     *
     * @param jsonString
     * @return
     */
    public static List<CcczgInfoSouce> json2List(String jsonString) throws ClassNotFoundException, IOException {
        List<CcczgInfoSouce> list = new ArrayList<CcczgInfoSouce>();
        JSONArray jsonArray =  JSONArray.fromObject(jsonString);
        for(int x =0;x<jsonArray.size();x++){
            CcczgInfoSouce cis = new CcczgInfoSouce();
            JSONObject json = JSONObject.fromObject(jsonArray.getString(x));

            cis.setFtpFileLocal((String)json.get("FTPFileLocal"));
            cis.setSourceTypes(1);

            String dateStr = (String)json.get("CreateDate");
            dateStr = dateStr.substring(6,dateStr.length()-2);

            cis.setCreateDate(new Date(new Long(dateStr)));



            cis.setTitle((String)json.get("Title"));
            cis.setResourceSize(json.get("FileSize").toString());
            cis.setCheckDate(new Date().toString());
            cis.setCheckSign(1L);
            cis.setNetPath("");
            cis.setResourcePath("");
            cis.setResourceSwfpath("");
            //设置科目
             setSubject(json.get("Subject").toString(),cis);
            
            //设置年级
             String grade = json.get("Grade").toString();

             setGrade(grade,cis);
           
            //设置教程版本
             setVersion("8a8081a1388efd5e01388f0462890002",cis);
            
            //设置souretype
            setSoureType("8a8081a1389285720138928d71f80004",cis);
           
            //设置会员
            setMember("46502",cis);

             list.add(cis);
        }
        return list;
    }




    //1：七年级、2：八年级、3：九年级、4：高一、5:高二、6:高三、7:小六(54)、8:大学、9:一年级、10:二年级、11:三年级、12:四年级、13:五年级、14:六年级、
    private static void setGrade(String grade, CcczgInfoSouce cis) throws IOException, ClassNotFoundException {
        SouceStugrade stugrade = new SouceStugrade();
        String njId = (String)getProperties("grade.properties").get(grade);
        stugrade.setNjId(njId);
       

        stugrade.getCcczgInfoSouces().add(cis);
        cis.setSouceStugrade(stugrade);



    }


    private static void setVersion(String s, CcczgInfoSouce cis) {
        SouceVersion souceVersion = new SouceVersion();
        souceVersion.setVersionId(s);
        souceVersion.getCcczgInfoSouces().add(cis);
        cis.setSouceVersion(souceVersion);

    }


    //1:英语、2:语文、3:数学、4:历史、5:政治、6:物理、7:化学、8:地理、9:生物
    private static void setSubject(String subject, CcczgInfoSouce cis) throws IOException, ClassNotFoundException {

        SouceSubject subjects = new SouceSubject();
        String subjectId = (String)getProperties("subject.properties").get(subject);
        subjects.setSubjectId(subjectId);
      

        subjects.getCcczgInfoSouces().add(cis);
        cis.setSouceSubject(subjects);

    }

    private static Properties getProperties(String fileName) throws ClassNotFoundException, IOException {
       InputStream ips =  JsonUtil.class.getClassLoader().getResourceAsStream(fileName);
       Properties pp = new Properties();
       pp.load(ips);
        return  pp;
    }

    private static void setSoureType(String s, CcczgInfoSouce cis) {
        SourceType sourceType = new SourceType();
        sourceType.setTypeId(s);
        sourceType.getCcczgInfoSouces().add(cis);
        cis.setSourceType(sourceType);
    }


    private static void setMember(String s, CcczgInfoSouce cis) {
        UserBase member = new UserBase();
        member.setId(new Long(s));
        member.getCcczInfoSouces().add(cis);
        cis.setMember(member);
    }




}
