package controllers;

import com.arj.ziguiw.common.JedisPoolSource;
import models.Resource;
import models.UserBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.Play;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

import java.io.IOException;
import java.util.List;

public class Application extends Controller {
    private static Log log = LogFactory.getLog(Application.class);
    protected static String uploadDir = Play.configuration.getProperty("upload.dir", "/data/upload");
    protected static String ctx = Play.configuration.getProperty("static.domain");
    protected static String diskRoot = "/data";
    protected static final JedisPoolSource jedisPoolSource = new JedisPoolSource(
            (String) Play.configuration.get("redis.host"),
            null);

    @Before
    public static void before() {
        /* log the url */
        MDC.put("url", request.url.replaceAll("'", "‘"));
        Http.Header userAgent = request.headers.get("user-agent");
        Http.Header referer = request.headers.get("referer");
        if (userAgent != null) MDC.put("userAgent", userAgent.value().replace("'", "‘"));
        if (referer != null) MDC.put("referer", referer.value().replace("'", "‘"));

        String userName = (String) DsisSecurity.connected();
        if (userName != null) {
            MDC.put("username", userName);
            UserBase userBase = UserBase.find("byNickName",userName).first();
            if (userBase != null) {
                renderArgs.put("user", userBase);
            }
        }
        renderArgs.put("ctx",ctx);
    }

    public static void index() {
        render();
    }

    public static void clear(){
        Cache.clear();
    }

    public static void convert(){
        String hql = "select r from Resource r where r.resourceFileType = ? or r.resourceFileType = ?";
        final List<Resource> list = Resource.find(hql,"flv","FLV").fetch();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Resource resource : list){
                    String resourcePath = uploadDir + "/" + resource.resourcePath
                            .substring(resource.resourcePath.indexOf("/") + 1);
                    String outPutPath = resourcePath.substring(0,resourcePath.lastIndexOf(".") + 1) + "mp4";
                    Runtime runtime = Runtime.getRuntime();
                    String command = String.format("ffmpeg -i %s -strict -2 -ar 22050 %s", resourcePath, outPutPath);
                    try {
                        runtime.exec(command);
                    } catch (IOException e) {
                        log.error("receive resource message failed! resourcePath = " + resourcePath,e);
                    }
                }
            }
        }).start();

    }

    public static void parseResourceXml(){
        String path = Play.configuration.getProperty("xmlPath","/data/101xml");
        Resource.parseXml(path,log);
        renderText("SUCCESS");
    }


    public static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    public static Integer getPageSize() {
        return request.params.get("pageSize") == null ? 15 : Integer.valueOf(request.params.get("pageSize"));
    }

    final static int COUNT=9;
}