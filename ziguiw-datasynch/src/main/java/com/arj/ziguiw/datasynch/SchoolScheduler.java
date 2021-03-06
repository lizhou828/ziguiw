package com.arj.ziguiw.datasynch;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-19
 * Time: A.M.10:26
 */
public class SchoolScheduler {

    private static final Log log = LogFactory.getLog("exam");
    @Qualifier(value = "rsRpcManager")
    @Autowired
    private RsRpcManager rsRpcManager;

    @Value(value = "${rpc.schoolurl}")
    private String url;

    @Value(value = "${pageSize}")
    private Integer pageSize;


    @Autowired
    private @Getter
    @Setter
    ThreadPoolTaskExecutor taskExecutor;
    /**
     * @throws Exception of the java.io.exception
     */
    public void run() throws Exception {
        try {
            log.info(String.format("begin execute %s!", SchoolScheduler.class.getName()));
            Long schoolLastModifiedTime = rsRpcManager.findLastModifiedTime("t_SCHOOLinfo");
            log.info("userLastModifiedTime is:" + schoolLastModifiedTime);
            Integer totalPageCount = exec(schoolLastModifiedTime, 1);
            log.info("totalPageCount is " + totalPageCount);
            if (totalPageCount != null) {
                for (int pageNo = 2; pageNo <= totalPageCount; pageNo++) {
                    exec(schoolLastModifiedTime, pageNo);
                    Thread.sleep(1000);
                }
            }
            log.info(String.format("execute %s complete!", SchoolScheduler.class.getName()));
        } catch (SystemAbortException e) {
            log.error(e.getMessage(), e);
            System.exit(0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    private Integer exec(Long lastModifiedTime, int pageNo) throws SQLException{
        String targetUrl =  String.format(url, pageNo, pageSize);
        if (lastModifiedTime != null && lastModifiedTime != 0) {
            targetUrl = String.format("%s&lastModifiedTime=%s", targetUrl, lastModifiedTime);
        }
        log.info(String.format("the rpc url is : %s", targetUrl));
        String content;
        try {
            content = HttpUtil.connect(targetUrl, "utf-8");
            if(content.contains("{")){
                content = content.substring(content.indexOf("{"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> map;
        try {
            log.info(content);
            map = (Map<String, Object>) JsonUtil.format(content, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(String.format("format content error, content is %s", content), e);
        }

        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("data");
        Integer rectotal = Integer.valueOf(String.valueOf(map.get("rectotal")));
        Integer pageSize = Integer.valueOf(String.valueOf(map.get("pagesize")));
        Integer pageCount;
        log.info(String.format("school total record is %s",rectotal));
        if(rectotal%pageSize == 0){
            pageCount = rectotal/pageSize;
        }else{
            pageCount = rectotal/pageSize+1;
        }
        saveSchools(list);
        return pageCount;
    }

    /**
     * save schools by collection data
     *
     * @param list the data from remote server
     */
    private void saveSchools(List<Map<String, Object>> list) {
        for (Map<String, Object> map : list) {
            try {
                log.info("map ->" + map);
                rsRpcManager.saveSchool(new CaseInsensitiveMap(map));
            } catch (Exception e) {
                log.error("save school failed!", e);
            }
        }
    }
}
