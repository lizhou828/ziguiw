package controllers;

import controllers.modules.cas.Security;
import play.modules.cas.models.CASUser;
import play.mvc.Scope;

/**
 *  customize the receiver to receive the user info from cas server<br/>
 *  if u want get the user info from cas server, u can call like this:
 *  <pre>
 *     //get userInfo from cas server
 *     CASUser user = DsisSecurity.connected();.
 *  </pre>
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-12-20
 * Time: P.M 12:03
 */
public class DsisSecurity extends Security {

    public static boolean check(String profile) {
        return profile.equals("role1");
    }

    public static void onAuthenticated(CASUser user) {
       // Cache.set(Scope.Session.current().get("username"), user);
    }

    public static void onDisconnected() {
        //String userName = Scope.Session.current().get("username");
        //if (userName != null) Cache.delete(userName);
        Scope.Session.current().clear();
    }

    public static Object connected() {
        return Scope.Session.current().get("username");
        //return Cache.get(Scope.Session.current().get("username"));
    }

}
