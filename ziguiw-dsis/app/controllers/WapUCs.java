package controllers;

import com.arj.ziguiw.common.UserBaseType;
import models.Parent;
import models.Student;
import models.Teacher;
import models.UserBase;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import play.mvc.*;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-1
 * Time: A.M.9:11
 */

public class WapUCs extends Controller {

    /* the default page size for list */
    private static final Integer DEFAULT_PAGE_SIZE = 100;

    /* the default charset for the response of remote process call */
    protected static final String RPC_DEFAULT_CHARSET = "UTF-8";

    private static Log log = LogFactory.getLog(WapUCs.class);

    @Before
    public static void before() {
        String wapUserName = Scope.Session.current().get("wapUserName");
        if(wapUserName != null && !"".equals(wapUserName)){
            MDC.put("wapUserName",wapUserName);
            UserBase wapUserBase = UserBase.find("byNickName", wapUserName).first();
            if (wapUserBase != null) {
                renderArgs.put("wapUserBase", wapUserBase);
                renderArgs.put("wapUserName", wapUserName);

                //inject the user info into renderArgs
                injectUserInfo(wapUserBase, renderArgs);

                // handle authority
                handleAuthority(wapUserBase, renderArgs);
            }else {
                render("../views/WapUCs/wapLogin.html");
            }
        }
    }

    public static void wapLogin(String nickName,String password,String saveUserInCookie){
        if(nickName == null && password == null){
           getUserInCookie();
        }
        String verifyResult = verifyLogin(nickName,password);
        if("loginSuccess".equals(verifyResult)){
            UserBase userBase = UserBase.find("byNickName",nickName).first();
            Scope.Session.current().put("wapUserName",userBase.nickName);
            saveUserInfoInCookie(userBase, saveUserInCookie);
            index();
        }
        render(verifyResult);
    }

    private static void autoLoginWithCookie(UserBase userBaseCookie) {
        index();
    }


    public static void wapLogout(){
        Scope.Session.current().remove("wapUserName");
        render("WapUCs/wapLogin.html");
    }
    public static void index(){
        UserBase userBase = (UserBase)renderArgs.get("userBase");
        if(userBase == null ){
            String userName = session.get("wapUserName");
            userBase = UserBase.find("byNickName" ,userName).first();
        }
        render(userBase);
    }
    //verify nickName and password
    private static String verifyLogin(String nickName,String password){
        String verifyResult = "";
        if (StringUtils.isEmpty(nickName)) {
            verifyResult = "用户名为空";
            return verifyResult;
        }
        if (StringUtils.isEmpty(password)) {
            verifyResult = "密码为空";
            return verifyResult;
        }
        UserBase tmpUser = UserBase.find("byNickName",nickName).first();
        if (tmpUser == null) {
            verifyResult = "不存在该用户";
            return verifyResult;
        }
        String passwordAfterMd5 = DigestUtils.md5Hex(password);
        if(!passwordAfterMd5.equalsIgnoreCase(tmpUser.password)){
            verifyResult = "密码错误";
            return verifyResult;
        }
        if(tmpUser.state < 2){
            verifyResult = "帐号未激活";
            return verifyResult;
        }else {
            verifyResult = "loginSuccess";
            return verifyResult;
        }
    }



    private static void saveUserInfoInCookie(UserBase userBase, String saveUserInCookie) {
        if(saveUserInCookie != null && !"".equals(saveUserInCookie) && saveUserInCookie.equalsIgnoreCase("on")){
            response.setCookie("wapUser",userBase.nickName+"-"+userBase.password);
        }
    }

    private static void getUserInCookie() {
        Map<String ,Http.Cookie> cookies = request.cookies;
        Http.Cookie cookie = new Http.Cookie();
        if(cookies != null && cookies.size() != 0 ){
            for(int i = 0; i < cookies.size(); i++){
                cookie = cookies.get("wapUser");
            }
        }

        String cookieValue = cookie == null ? "" : cookie.value;
        UserBase userBase = null;
        if( cookieValue.indexOf("-") > 0){
            String[] keyAndValue = cookieValue.split("-");
            if(keyAndValue.length > 1 ){
                userBase = UserBase.find("byNickName",keyAndValue[0]).first();
                if(userBase != null && userBase.nickName.equals(keyAndValue[0]) && userBase.password.equals(keyAndValue[1]) ){
                    Scope.Session.current().put("wapUserName",userBase.nickName);
                    autoLoginWithCookie(userBase);
                }
            }
        }
    }


    @Util
    protected static void injectUserInfo(UserBase userBase, Scope.RenderArgs renderArgs1) {
        renderArgs1.put("userBase", userBase);
        if(userBase.type == UserBaseType.TEACHER){
            Teacher teacher = Teacher.find("byTeacherId", userBase.foreignKey.intValue()).first();
            renderArgs1.put("teacher", teacher);
            renderArgs1.put("displayName", teacher.name);
        } else if (userBase.type == UserBaseType.PARENT){
            Parent parent = Parent.find("byParentId", userBase.foreignKey).first();
            renderArgs1.put("parent", parent);
            String wapXsId  = request.params.get("wapXsId");
            if (wapXsId == null) wapXsId = parent.xsId + "";
            Student student = Student.find("byXsId", Long.valueOf(wapXsId)).first();
            if (student != null) {
                renderArgs1.put("student", student);
                renderArgs1.put("displayName", student.xsXming + "家长");
            } else {
                renderArgs1.put("displayName", userBase.nickName);
                render("unbind.html");
            }
        } else if(userBase.type == UserBaseType.STUDENT){
            Student student = Student.find("byXsId", userBase.foreignKey).first();
            renderArgs1.put("student", student);
            renderArgs1.put("displayName", student.xsXming);
        }
    }

    @Util
    protected static void handleAuthority(UserBase userBase, Scope.RenderArgs renderArgs1) {
        String action = Http.Request.current().actionMethod;
        if (action.equalsIgnoreCase("waptlist") && userBase.type != UserBaseType.TEACHER) {
            throw new IllegalOperationException("非法操作");
        }
    }

}
