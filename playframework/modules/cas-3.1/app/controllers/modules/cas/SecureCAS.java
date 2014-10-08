/**
 *  This file is part of LogiSima-play-cas.
 *
 *  LogiSima-play-cas is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  LogiSima-play-cas is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with LogiSima-play-cas.  If not, see <http://www.gnu.org/licenses/>.
 */
package controllers.modules.cas;

import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.cache.Cache;
import play.modules.cas.CASUtils;
import play.modules.cas.annotation.Check;
import play.modules.cas.models.CASUser;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Router;
import play.mvc.Scope;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a part of the play module secure-cas. It add the ability to check if the user have access to the
 * request. If the user is note logged, it redirect the user to the CAS login page and authenticate it.
 * 
 * @author bsimard
 * 
 */
public class SecureCAS extends Controller {

    private final static Map<String, Scope.Session> ID_TO_SESSION_KEY_MAPPING = new HashMap<String, Scope.Session>();

    /**
     * Action for the login route. We simply redirect to CAS login page.
     * 
     * @throws Throwable
     */
    public static void login() throws Throwable {
        // We must avoid infinite loops after success authentication
        if (!Router.route(request).action.equals("modules.cas.SecureCAS.login")) {
            // we put into cache the url we come from
            if (getActionAnnotation(UnCache.class) == null) {
                Cache.add("url_" + session.getId(), request.method == "GET" ? request.url : "/", "10min");
            }
        }

        // we redirect the user to the cas login page
        String casLoginUrl = CASUtils.getCasLoginUrl(false);
        redirect(casLoginUrl);
    }

    /**
     * Action for the logout route. We clear cache & session and redirect the user to CAS logout page.
     * 
     * @throws Throwable
     */
    public static void logout() throws Throwable {

        String username = session.get("username");

        // we clear cache
        Cache.delete("pgt_" + username);

        // we clear session
        session.clear();

        // we invoke the implementation of "onDisconnected"
        Security.invoke("onDisconnected");

        // we redirect to the cas logout page.
        String casLogoutUrl = CASUtils.getCasLogoutUrl();
        redirect(casLogoutUrl);
    }

    /**
     * Action when the user authentification or checking rights fails.
     * 
     * @throws Throwable
     */
    public static void fail() throws Throwable {
        forbidden();
    }

    /**
     * Action for the CAS return.
     * 
     * @throws Throwable
     */
    public static void authenticate() throws Throwable {
        if ("POST".equalsIgnoreCase(request.method)) {    //log out callback
            BufferedReader reader =  new BufferedReader(new InputStreamReader(request.body));
            StringBuffer sb = new StringBuffer();
            String tmpStr;
            while ((tmpStr = reader.readLine()) != null) {
                sb.append(tmpStr);
                sb.append("\n");
            }
            if (sb.toString().indexOf("logoutRequest=") != -1) {
                String logoutRequest = URLDecoder.decode(StringUtils.substringAfter(sb.toString(), "logoutRequest="));
                String ticket = XmlUtils.getTextForElement(logoutRequest, "SessionIndex");
                Scope.Session session1 = ID_TO_SESSION_KEY_MAPPING.get(ticket);
                String username = session1.get("username");
                Cache.delete("pgt_" + username);
                Cache.delete("CAS_" + session1.getId());
                ID_TO_SESSION_KEY_MAPPING.remove(ticket);
                return;
            }
        }
        Boolean isAuthenticated = Boolean.FALSE;
        String ticket = params.get("ticket");
        if (ticket != null) {
            ID_TO_SESSION_KEY_MAPPING.put(ticket, session);
            Logger.debug("[SecureCAS]: Try to validate ticket " + ticket);
            CASUser user = CASUtils.valideCasTicket(ticket);
            if (user != null) {
                isAuthenticated = Boolean.TRUE;
                Cache.add("CAS_" + session.getId(), user.getUsername(), "30mn");
                session.put("username", user.getUsername());
                // we invoke the implementation of onAuthenticate
                Security.invoke("onAuthenticated", user);
            }
        }

        if (isAuthenticated) {
            // we redirect to the original URL
            String url = (String) Cache.get("url_" + session.getId());
            Cache.delete("url_" + session.getId());
            if (url == null) {
                url = "/";
            }
            Logger.debug("[SecureCAS]: redirect to url -> " + url);
            redirect(url);
        }
        else {
            fail();
        }
    }

    /**
     * Action for the proxy call back url.
     */
    public static void pgtCallBack() throws Throwable {
        // CAS server call this URL with PGTIou & PGTId
        String pgtIou = params.get("pgtIou");
        String pgtId = params.get("pgtId");

        // here we put in cache PGT with PGTIOU as key
        if (pgtIou != null || pgtId != null) {
            Cache.set(pgtIou, pgtId);
        }
    }

    /**
     * Method that do CAS Filter and check rights.
     * 
     * @throws Throwable
     */
    @Before(unless = { "login", "logout", "fail", "authenticate", "pgtCallBack" })
    public static void filter() throws Throwable {
        Logger.debug("[SecureCAS]: CAS Filter for URL -> " + request.url);

        // if user is authenticated, the username is in session !
        if (Cache.get("CAS_" + session.getId()) != null) {
            // We check the user's profile with class annotation
            Check controllerCheck = getControllerInheritedAnnotation(Check.class);
            if (controllerCheck != null) {
                check(controllerCheck);
            }
            // We check the user's profile with action annotation
            Check actionCheck = getActionAnnotation(Check.class);
            if (actionCheck != null) {
                check(actionCheck);
            }
        }
        else {
            if (getActionAnnotation(UnSecure.class) != null) return;
            Logger.debug("[SecureCAS]: user is not authenticated");
            // we put into cache the url we come from
            if (getActionAnnotation(UnCache.class) == null) {
                Cache.add("url_" + session.getId(), request.method == "GET" ? request.url : "/", "10min");
            }
            // we redirect the user to the cas login page
            String casLoginUrl = CASUtils.getCasLoginUrl(true);
            redirect(casLoginUrl);
        }
    }

    /**
     * Function to check the rights of the user. See your implementation of the Security class with the method check.
     * 
     * @param check
     * @throws Throwable
     */
    private static void check(Check check) throws Throwable {
        for (String profile : check.value()) {
            boolean hasProfile = (Boolean) Security.invoke("check", profile);
            if (!hasProfile) {
                Security.invoke("onCheckFailed", profile);
            }
        }
    }

}
