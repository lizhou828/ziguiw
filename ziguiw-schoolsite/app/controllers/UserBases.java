package controllers;

import com.arj.ziguiw.common.UserBaseType;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnCache;
import models.SchoolAdmin;
import models.UserBase;
import org.apache.commons.lang.StringUtils;
import play.Play;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-6
 * Time: P.M 3:15
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
}
