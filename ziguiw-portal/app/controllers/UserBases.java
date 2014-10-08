package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.UserBaseType;
import com.arj.ziguiw.common.UserStatus;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnCache;
import controllers.modules.cas.UnSecure;
import models.SchoolAdmin;
import models.UserBase;
import models.ValidateCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;
import play.mvc.Http;
import play.mvc.With;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;


/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-6
 * Time: P.M 3:15
 */
@With(SecureCAS.class)
public class UserBases extends Application {

    private static final Log logger = LogFactory.getLog(UserBases.class);

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

    public static void alterPhoto(long  userId){
        UserBase userBase=UserBase.findById(userId);
        render("UserBases/alterPhoto.html", userBase, userId);
    }
    public static void alterInformation(long  userId){
        UserBase userBase=UserBase.findById(userId);
        render("UserBases/alterInformation.html",userBase,userId);
    }

    public static void alterPassword(long  userId){
        UserBase userBase=UserBase.findById(userId);
        render("UserBases/alterPassword.html",userBase,userId);
    }

    public static void updatePassword(long userId,String newPassword,String oldPassword,String secondPassword) {
        UserBase userBase=UserBase.findById(userId);
        String error = "";
        logger.info(String.format("alterPassword old password is %s",oldPassword));
        logger.info(String.format("alterPassword new password is %s",newPassword));
        logger.info(String.format("alterPassword second password is %s",secondPassword));
        if(oldPassword == null || "".equals(oldPassword.trim())){
            error = "旧密码不能为空";
            flash.put("error",error);
            alterPassword(userId);
        }
        if(newPassword == null || "".equals(newPassword.trim())){
            error = "新密码不能为空";
            flash.put("error",error);
            alterPassword(userId);
        }
        if(secondPassword == null || "".equals(secondPassword.trim())){
            error = "确认密码不能为空";
            flash.put("error",error);
            alterPassword(userId);
        }
        if(!DigestUtils.md5Hex(oldPassword).equals(userBase.password)){
            error = "旧密码错误";
            flash.put("error",error);
            alterPassword(userId);
        }
        if(!newPassword.equals(secondPassword)){
            error = "确认密码输入不正确";
            flash.put("error",error);
            alterPassword(userId);
        }
        userBase.password = DigestUtils.md5Hex(newPassword);
        userBase.save();
        error = "密码修改成功";
        flash.put("error",error);
        alterPassword(userId);
    }

    public static void updateInformation(long userId,String userSex,String userProvince,String userSignature,String displayName){
        UserBase userBase=UserBase.findById(userId);
        userBase.displayName = displayName;
        userBase.province=userProvince;
        userBase.sex=Integer.parseInt(userSex);
        userBase.signature=userSignature;
        userBase.save();
        redirect("/MyHomes/list?userId="+userId);
    }

    public static void updatePhoto(long userId){
        UserBase userBase=UserBase.findById(userId);
        File uploadFile = Http.Request.current().params.get("file", File.class);
        userBase.photo= FileUtils.copyAndCompressImage(uploadFile,uploadFile.getName(),uploadDir,new String[]{ImageSize.HOMEPHOTO });
        userBase.save();
        redirect("/MyHomes/list?userId=" + userId);

    }

    @UnSecure
    public static void registerUI(){
        render();
    }

    @UnSecure
    public static void register(String userName,String password,String password2,String email,String validateCode){
        String error = null;
        if(validateCode == null || "".equals(validateCode.trim())){
            error = "验证码不能为空";
        }
        if(!validateCode.toLowerCase().equalsIgnoreCase(session.get("verifyCode").toLowerCase())){
            error = "验证码不正确";
        }
        if (userName == null || "".equals(userName.trim())) {
            error =  "用户名不能为空!";
        }
        if (userName != null && UserBase.findByUserName(userName) != null) {
            error = "该用户已注册!";
        }
        if (password == null || "".equals(password.trim())) {
            error = "密码不能为空!";
        }
        if (password != null && password.length() < 3) {
            error = "密码长度不能低于3位!";
        }
        if(password.contains(" ")){
            error = "密码不能包含空格!";
        }
        if (password2 == null || "".equals(password2.trim())) {
            error =  "确认密码不能为空!";
        }
        if (password2 != null && !password2.trim().equals(password.trim())) {
            error = "两次输入的密码不一致!";
        }
        if (email == null || "".equals(email.trim())) {
            error = "email不能为空!";
        }
        if (email != null && !email.matches("[0-9a-zA-Z_-]+@[0-9a-zA-Z_-]+\\.[0-9a-zA-Z_-]+(\\.[0-9a-zA-Z_-])*")) {
            error = "email格式不正确!";
        }
        if(UserBase.find("byEmail",email).first() !=null){
            error = "邮箱已经被注册!";
        }
        if (error != null) {
            flash.put("error",error);
            render("/UserBases/registerUI.html", userName, email);
        } else {
            UserBase userBase = new UserBase();
            userBase.nickName = userName;
            userBase.email = email;
            userBase.password = DigestUtils.md5Hex(password);
            userBase.create();
            ValidateCode code = new ValidateCode();
            code.validateCode = RandomUtils.nextInt();
            code.invalidTime = new Date(System.currentTimeMillis() + 1000 * 60* 60 * 24 * 365);
            code.userId = userBase.id;
            code.create();
            renderArgs.put("user", userBase);   //put into renderArgs for send message
            /*  new a thread to send email */
            final String mail_email = userBase.email;
            final String mail_nickName = userBase.nickName;
            final int mail_code = code.validateCode;
            final long mail_userId = userBase.id;
            renderArgs.put("user", userBase);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Session session = UserBase.getSession();
                    MimeMessage message = new MimeMessage(session);
                    try {
                        message.setSubject("子贵网的注册激活邮件");
                        message.setSentDate(new Date());
                        message.setFrom(new InternetAddress("admin@ziguiw.com"));
                        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_email));
                        message.setContent("<html><head></head><body>您好，谢谢您注册了子贵网，您在子贵网注册的用户名是："
                                + mail_nickName
                                + "<br><a href='"
                                + Play.configuration.getProperty("activation")
                                + "?userId=" + mail_userId + "&activeCode="
                                + mail_code
                                + "'>点击此处激活</a></body></html>", "text/html;charset=utf-8");
                        // 发送邮件
                        Transport.send(message);
                    } catch (Exception e) {
                        logger.error(String.format("send email failed! email : %s, nickName : %s, userId : %s, code : %s",
                                mail_email, mail_nickName, mail_userId, mail_code
                                ), e);
                    }
                }
            }).start();

            render(userBase);
        }
    }



    @UnSecure
    public static void activation(String userId,String activeCode){
        String message;
        UserBase userBase = UserBase.findById(new Long(userId));
        ValidateCode validateCode = ValidateCode.find("byUserId",new Long(userId)).first();
        if(validateCode.validateCode == new Integer(activeCode)){
            userBase.state = UserStatus.activation;
            userBase.save();
            message = "激活成功<a href='/login'>登录</a>";
            render(message,"UserBases/activation.html");
        }
        message = "激活失败,请重新<a href='/UserBases/registerUI'>注册</a>";
        render(message, "UserBases/activation.html");
    }

    @UnSecure
    public static void verifyCode() throws IOException {
        System.setProperty("java.awt.headless", "true");
        BufferedImage img = new BufferedImage(68, 22,  BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        Random r = new Random();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        g.fillRect(0, 0, 68, 22);
        StringBuffer sb = new StringBuffer();
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int index, len = ch.length;
        for (int i = 0; i < 4; i ++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
            g.drawString("" + ch[index], (i * 15) + 3, 18);
            sb.append(ch[index]);
        }
        session.put("verifyCode", sb.toString());
        response.contentType = "image/jpeg";
        ImageIO.write(img, "JPG", response.out);
    }

    public static void userInfo(Long userId){
         UserBase userBase = UserBase.findById(userId);
         render(userBase);
    }

    public static void pass(){
        String userName = (String) DsisSecurity.connected();
        if(userName != null){
            UserBase userBase =  UserBase.find("byNickName", userName).first();
            if(userBase.type == UserBaseType.VISITOR){
                redirect("/MyHomes/list?userId="+userBase.id);
            }else if(userBase.type == UserBaseType.SCHOOL_EDITOR){
                SchoolAdmin schoolAdmin = SchoolAdmin.findByAccount(userName);
                if(schoolAdmin.classId == null){
                    redirect(Play.configuration.getProperty("schoolsite.domain")+"/Schools/admin");
                }else {
                    redirect(Play.configuration.getProperty("schoolsite.domain")
                            +"/SchoolClazzs/admin?xxId="+schoolAdmin.school.xxId+"&classId="+schoolAdmin.classId);
                }
            }else {
                redirect(Play.configuration.getProperty("dsis.domain"));
            }
        }
    }

    @UnSecure
    public static void backPassword(){
        render();
    }

    @UnSecure
    public static void findPassword(String email){
        UserBase userBase = UserBase.find("byEmail",email).first();
        ValidateCode code = new ValidateCode();
        code.validateCode = RandomUtils.nextInt();
        code.invalidTime = new Date(System.currentTimeMillis() + 1000 * 60* 60 * 24 * 365);
        code.userId = userBase.id;
        code.create();

        final String mail_email = userBase.email;
        final String mail_nickName = userBase.nickName;
        final int mail_code = code.validateCode;
        final long mail_userId = userBase.id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = UserBase.getSession();
                MimeMessage message = new MimeMessage(session);
                try {
                    message.setSubject("子贵网的重置密码邮件");
                    message.setSentDate(new Date());
                    message.setFrom(new InternetAddress("admin@ziguiw.com"));
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_email));
                    message.setContent("<html><head></head><body>您好："
                            + mail_nickName
                            + "<br><a href='"
                            + Play.configuration.getProperty("portal.domain")
                            + "/UserBases/setPassword?userId=" + mail_userId + "&activeCode="
                            + mail_code
                            + "'>点击此处重置密码</a></body></html>", "text/html;charset=utf-8");
                    // 发送邮件
                    Transport.send(message);
                } catch (Exception e) {
                    logger.error(String.format("send email failed! email : %s, nickName : %s, userId : %s, code : %s",
                            mail_email, mail_nickName, mail_userId, mail_code
                    ), e);
                }
            }
        }).start();

        render();
    }

    @UnSecure
    public static void setPassword(String userId, String activeCode){
        String message;
        UserBase userBase = UserBase.findById(new Long(userId));
        ValidateCode validateCode = ValidateCode.findByTimeDesc(new Long(userId)).get(0);
        if(validateCode.validateCode == new Integer(activeCode)){
            message = "ok";
        }else {
            message = "error";
        }
        render(message,activeCode,userBase);
    }

    @UnSecure
    public static void resetPassword(String password, String password1, String userId,String activeCode){
        String message;
        UserBase userBase = UserBase.findById(new Long(userId));
        ValidateCode validateCode = ValidateCode.findByTimeDesc(new Long(userId)).get(0);
        if(password == null || password1 == null){
           message = "密码不能为空";
           flash.put("error",message);
           setPassword(userId + "",activeCode);
        }
        if(!password.equals(password1)) {
            message = "两次密码输入不正确";
            flash.put("error",message);
            setPassword(userId + "",activeCode);
        }
        if(validateCode.validateCode == new Integer(activeCode)){
          userBase.password =  DigestUtils.md5Hex(password);
          userBase.save();
          message = "ok";
        } else {
          message = "error";
        }
        render(message);
    }

    @UnSecure
    public static void mobileUpdatePhoto(String userName , File file0){
            UserBase userBase = UserBase.find("byNickName",userName).first();
            logger.info(String.format("mobile user update photo params userName = %s , file = %s",userName,file0));
            if(userBase != null && file0 != null){
                  userBase.photo = FileUtils.copyAndCompressImage(file0,file0.getName(),uploadDir,new String[]{ImageSize.HOMEPHOTO});
                  userBase.save();
                  renderText(userBase.photo);
            }else {
                  logger.info("mobile user update photo fail,user or file is null!");
                  renderText(null);
            }
    }
}
