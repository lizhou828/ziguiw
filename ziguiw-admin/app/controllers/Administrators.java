package controllers;

import com.arj.ziguiw.common.Queues;
import models.Administrator;

/**
 * User: Liujy
 * Date: 13-1-31
 * Time: 下午5:22
 */
public class Administrators extends Application{

    public static void login(){
        render();
    }

    public static void logout(){
        session.remove("administrator");
        login();
    }

    public static void authenticate(String nickName,String password){
        String error;
       Administrator administrator = Administrator.findByNickName(nickName);
       if(administrator == null){
           error = "用户名不存在";
           flash.put("loginError", error);
           login();
       }
       if(!password.equals(administrator.password)){
           error = "密码错误";
           flash.put("loginError", error);
           login();
       }
       session.put("administrator", nickName);
       redirect("/Application/index");
    }

    public static void noty() {
        renderText(jedisPoolSource.lpop(Queues.ADMINISTRATORS_NOTY));
    }

}
