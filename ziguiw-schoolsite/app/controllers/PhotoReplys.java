package controllers;

import controllers.modules.cas.SecureCAS;
import models.PhotoReply;
import models.UserBase;
import play.mvc.With;

import java.lang.Long;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-3
 * Time: 上午9:27
 */
@With(SecureCAS.class)
public class PhotoReplys extends Application{

        public static void create(Long albumId, String content, String xxId){
            PhotoReply photoReply = new PhotoReply();
            UserBase userBase = (UserBase) renderArgs.get("user");
            photoReply.albumId = albumId;
            photoReply.content = content;
            photoReply.user = userBase;
            photoReply.create();
            redirect("/SchoolPhotos/pshowPhoto?id=" + albumId + "&xxId=" + xxId);
        }


        public static void ccreate(Long albumId, String content, String xxId, Long classId){
            PhotoReply photoReply = new PhotoReply();
            UserBase userBase = (UserBase) renderArgs.get("user");
            photoReply.albumId = albumId;
            photoReply.content = content;
            photoReply.user = userBase;
            photoReply.create();
            redirect("/ClassPhotos/pshow?id=" + albumId + "&xxId=" + xxId + "&classId=" +classId);
        }
}
