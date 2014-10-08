package controllers;

import models.FatherMessage;
import models.FatherMessageReplay;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-9
 * Time: 下午10:13
 */
public class FatherMessages extends Application{

        public static void mUpload(String userName, String mobile, String content, String appellation
                                    , String signature, String contact, String email){
                Integer flag;
                if(mobile == null || content == null || appellation == null || signature == null || contact == null ||
                   mobile.trim().equals("") || content.trim().equals("") || appellation.trim().equals("") || signature.trim().equals("") ||
                        contact.trim().equals("")){
                      flag = -1;
                      render("/FatherMessages/wxAdd.html",userName,mobile,content,appellation,signature,contact,email,flag);
                }else {
                    FatherMessage fatherMessage = new FatherMessage();
                    fatherMessage.userName = userName;
                    fatherMessage.mobile = mobile;
                    fatherMessage.content = content;
                    fatherMessage.appellation = appellation;
                    fatherMessage.signature = signature;
                    fatherMessage.contact = contact;
                    fatherMessage.email = email;
                    fatherMessage.create();
                    flag = 1;
                    render("/FatherMessages/wxAdd.html",flag);
                }
        }

        public static void upload(String mobile, String content, String appellation
                , String signature, String contact, String email){
            Integer flag;
            if(mobile == null || content == null || appellation == null || signature == null || contact == null ||
                    mobile.trim().equals("") || content.trim().equals("") || appellation.trim().equals("") || signature.trim().equals("") ||
                    contact.trim().equals("")){
                flag = -1;
                render("/FatherMessages/add.html",mobile,content,appellation,signature,contact,email,flag);
            }else {
                FatherMessage fatherMessage = new FatherMessage();
                fatherMessage.mobile = mobile;
                fatherMessage.content = content;
                fatherMessage.appellation = appellation;
                fatherMessage.signature = signature;
                fatherMessage.contact = contact;
                fatherMessage.email = email;
                fatherMessage.create();
                flag = 1;
                render("/FatherMessages/add.html",flag);
            }
        }

       public static void add(){
           render();
       }

       public static void wxAdd(String userName){
           render(userName);
       }

       public static void search(String mobile){
           List<FatherMessage> fatherMessageList = FatherMessage.findByContact(mobile);
           List<FatherMessageReplay> replayList = FatherMessageReplay.findByMobile(mobile);
           render(fatherMessageList,mobile,replayList);
       }
}
