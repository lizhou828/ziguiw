package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import models.Knowledge;
import models.Recommend;
import play.db.Model;

import play.mvc.Util;
import play.mvc.Http;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-29
 * Time: A.M 10:50
 */
public class Knowledges extends Application {

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                Knowledge know = Knowledge.findById(id);
                if (know == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = know.title;
                recommend.area = recommendArea;
                recommend.description = know.description;
                recommend.url = know.firstImage;
                recommend.objectId = id + "";
                recommend.moduleId = know.knowledgeChannel.id + "";
                recommend.moduleName = know.knowledgeChannel.name;
                recommend.create();
            }
        }
        renderText("OK");
    }

    @Util
    public static void beforeCreate(Model object) {
        Knowledge knowledge = (Knowledge) object;
        File file = Http.Request.current().params.get("file", File.class);
        if(file != null){
            knowledge.firstImage = FileUtils.copyImage(file,file.getName(),uploadDir);
        }
        knowledge.content = FileUtils.replaceImgTag(knowledge.content, uploadDir, (String) renderArgs.get("ctx"));
    }

    @Util
    public static void beforeSave(Model object) {
        Knowledge knowledge = (Knowledge) object;
        File file = Http.Request.current().params.get("file", File.class);
        if(file != null){
            knowledge.firstImage = FileUtils.copyImage(file,file.getName(),uploadDir);
        }
        knowledge.content = FileUtils.replaceImgTag(knowledge.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }
}
