package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.utils.FileUtils;
import models.Administrator;
import models.Newse;
import models.Recommend;
import play.db.Model;
import play.mvc.Http;
import play.mvc.Util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-1-24
 * Time: A.M 11:28
 */
public class Newses extends Application {

    @Util
    public static void beforeCreate(Model object) {
        Newse newse = (Newse) object;
        newse.editors =  Administrator.findByNickName(session.get("administrator")).realName;
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
        File file = Http.Request.current().params.get("file", File.class);
        String contentUrl =  request.params.get("contentUrl");
        if (file != null) {
            newse.firstImage = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.X});
        }
        if(contentUrl != null){
            newse.firstImage = FileUtils.acquireImgTag(newse.content).get(0).replace(renderArgs.get("ctx")+"/", "");
        }
    }

    @Util
    public static void beforeSave(Model object) {
        Newse newse = (Newse) object;
        File file = Http.Request.current().params.get("file", File.class);
        newse.content = FileUtils.replaceImgTag(newse.content ,uploadDir ,(String)renderArgs.get("ctx"));
        String contentUrl =  request.params.get("contentUrl");
        if (file != null) {
            newse.firstImage = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.X});
        }
        if(contentUrl != null){
            newse.firstImage = FileUtils.acquireImgTag(newse.content).get(0).replace(renderArgs.get("ctx")+"/", "");
        }
    }

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                Newse newse = Newse.findById(id);
                if (newse == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = newse.title;
                recommend.area = recommendArea;
                recommend.description = newse.descr;
                recommend.url = newse.firstImage;
                recommend.objectId = id + "";
                recommend.moduleId = newse.newsChannel.id + "";
                recommend.moduleName = newse.newsChannel.name;
                recommend.create();
            }
        }
        renderText("OK");
    }

}
