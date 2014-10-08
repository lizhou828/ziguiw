package controllers;

import com.arj.ziguiw.common.RecommendArea;
import com.arj.ziguiw.common.UserBaseType;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnCache;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.lang.StringUtils;
import play.Play;
import play.mvc.With;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-20
 * Time: 上午10:16
 */
@With(SecureCAS.class)
public class UserBases extends Application {

    @UnCache
    public static void logon() {
        UserBase userBase = (UserBase) renderArgs.get("user");
        String displayName = userBase.nickName;
        if (displayName != null && displayName.length() == 11 && StringUtils.isNumeric(displayName)) {
            displayName = displayName.substring(0, 7) + "****";
        }
        String usernameUrl = "<a href='%s'>[%s]</a>";
        if (userBase.type == UserBaseType.PARENT || userBase.type == UserBaseType.STUDENT || userBase.type == UserBaseType.TEACHER) {
            usernameUrl = String.format(usernameUrl, Play.configuration.getProperty("dsis.domain"), displayName);
        } else if (userBase.type == UserBaseType.SCHOOL) {
            usernameUrl = String.format(usernameUrl, Play.configuration.get("schoolsite.domain") + "/schools/admin", displayName);
        } else if (userBase.type == UserBaseType.SCHOOL_EDITOR) {
            SchoolAdmin schoolAdmin = SchoolAdmin.find("byAccount", userBase.nickName).first();
            String adminUrl;
            if (schoolAdmin != null) {
                if (schoolAdmin.classId != null) {
                    adminUrl = String.format(Play.configuration.get("schoolsite.domain") + "/schoolclazzs/admin?xxId=%s&classId=%s", schoolAdmin.school.xxId, schoolAdmin.classId);
                } else {
                    adminUrl = Play.configuration.get("schoolsite.domain") + "/schools/admin";
                }
                usernameUrl = String.format(usernameUrl, adminUrl, displayName);
            } else {
                usernameUrl = String.format(usernameUrl, Play.configuration.getProperty("portal.domain"), displayName);
            }
        } else {
            StringBuffer url = new StringBuffer(Play.configuration.getProperty("portal.domain"));
            url = url.append("/myhomes/list?userId=" + ((UserBase)renderArgs.get("user")).id);
            usernameUrl = String.format(usernameUrl, url.toString(), displayName);
        }
        String toolBarBtn = String.format("您好，%s<a href='%s'>[退出]</a>", usernameUrl, "/logout");
        render(toolBarBtn);
    }

    @UnSecure
    public static void show(Long userId){
        UserBase userBase = UserBase.findById(userId);
        List<Type> list = Type.findAll();
        Map<Type,List<Resource>> map = new HashMap<Type, List<Resource>>();
        for(Type type : list){
            map.put(type, UserBase.findResourceByUserAndType(userId, type.id));
        }
        Long resourceTotalCount = Resource.findByUserId(userId,getPage(),getPageSize()).totalCount;

        List<Recommend> recommends = Recommend.findByArea(RecommendArea.RESOURCES_INDEX_ZUIXINTUIJIAN);
        List<Resource> newResource = new ArrayList<Resource>();
        for (Recommend recommend : recommends){
            newResource.add((Resource)Resource.findById(new Long(recommend.objectId)));
        }
        List<Resource> downResource = Resource.findDownResource(9);
        Long totalCount = Resource.findTotalCount();

        render(map,newResource,downResource,totalCount,userBase,resourceTotalCount);
    }
}
