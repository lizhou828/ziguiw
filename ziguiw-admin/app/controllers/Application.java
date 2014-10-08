package controllers;

import com.arj.ziguiw.common.JedisPoolSource;
import models.Menu;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.Play;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Http;
import play.mvc.Scope;

import java.util.List;

public class Application extends CRUD {

    protected static String uploadDir = Play.configuration.getProperty("upload.dir", "/data/upload");

    private static final Log opLog = LogFactory.getLog("op");

    protected static final JedisPoolSource jedisPoolSource = new JedisPoolSource(
            (String) Play.configuration.get("redis.host"),
            null);

    @Before(unless = {"Administrators.login", "Administrators.authenticate", "Administrators.logout"}, priority = 0)
    public static void authenticate() {
        if(!session.contains("administrator")){
            Administrators.login();
        }
    }

    @Before(unless = {"Administrators.noty"},priority = 1)
    public static void before() {
        /* log the url */
        MDC.put("url", request.url.replaceAll("'", "‘"));
        Http.Header userAgent = request.headers.get("user-agent");
        Http.Header referer = request.headers.get("referer");
        if (userAgent != null) MDC.put("userAgent", userAgent.value().replace("'", "‘"));
        if (referer != null) MDC.put("referer", referer.value().replace("'", "‘"));

        opLog.info(String.format("%s(%s)", Http.Request.current().url, Http.Request.current().params));
        List<Menu> menus = Menu.findAll();
        renderArgs.put("menus", menus);
        String parentMenuId = params.get("parentMenuId");
        if (parentMenuId == null) parentMenuId = Scope.Session.current().get("parentMenuId");
        if (parentMenuId == null) parentMenuId = menus.get(0).id + "";
        Scope.Session.current().put("parentMenuId", parentMenuId);
        renderArgs.put("parentMenuId",  parentMenuId);
        renderArgs.put("ctx", Play.configuration.get("static.domain") == null ? "" : Play.configuration.get("static.domain"));
        String loginUserAccount = Scope.Session.current().get("administrator");
        if (loginUserAccount != null) MDC.put("username", loginUserAccount);
    }

    public static void index() {
        render();
    }

    public static void flushCache() {
        if (!"admin".equals(session.get("administrator"))) {
            renderHtml("<script>alert('Operation failed!, You are not an administrator!');history.go(-1)</script>");
        } else {
            Cache.clear();
            renderHtml("<script>alert('OK!');history.go(-1)</script>");
        }
    }

    public static Integer getPage() {
        return request.params.get("page") == null ? 1 : Integer.valueOf(request.params.get("page"));
    }

    public static Integer getPageSize() {
        return request.params.get("pageSize") == null ? getDefaultPageSize() : Integer.valueOf(request.params.get("pageSize"));
    }
}