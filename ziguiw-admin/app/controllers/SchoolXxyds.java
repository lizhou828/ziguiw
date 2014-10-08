package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.utils.FileUtils;
import models.QueryParams;
import models.Recommend;
import models.SchoolXxyd;
import play.db.Model;
import play.mvc.Http;
import play.mvc.Util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-19
 * Time: 下午4:04
 */
public class SchoolXxyds extends Application{

    @Util
    public static void beforeCreate(Model object) {
        SchoolXxyd xxyd = (SchoolXxyd) object;
        File file = Http.Request.current().params.get("file", File.class);
        File video = Http.Request.current().params.get("videoUrl", File.class);
        if (file != null) {
            xxyd.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSX ,  ImageSize.SKL});
        }
        if(video != null){
            xxyd.videoUrl = FileUtils.copyImage(video, video.getName() ,uploadDir);
        }
        xxyd.content = FileUtils.replaceImgTag(xxyd.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeSave(Model object) {
        SchoolXxyd xxyd = (SchoolXxyd) object;
        File file = Http.Request.current().params.get("file", File.class);
        File video = Http.Request.current().params.get("videoUrl", File.class);
        if (file != null) {
            xxyd.url = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.SSX ,  ImageSize.SKL});
        }
        if (video != null) {
            xxyd.videoUrl = FileUtils.copyImage(video, video.getName() , uploadDir);
        }
        xxyd.content = FileUtils.replaceImgTag(xxyd.content ,uploadDir ,(String)renderArgs.get("ctx"));
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        if (queryParams.orderBy == null) queryParams.orderBy = "createTime";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

    public static void recommend(Long[] rid, String recommendArea) {
        if (rid != null) {
            for (Long id : rid) {
                SchoolXxyd newse = SchoolXxyd.findById(id);
                if (newse == null) continue;
                Recommend recommend = new Recommend();
                recommend.title = newse.title;
                recommend.area = recommendArea;
                recommend.description = newse.describe;
                recommend.url = newse.url;
                recommend.objectId = id + "";
                recommend.create();
            }
        }
        renderText("OK");
    }

    public static void deletePhoto(Long id) throws Exception {
        SchoolXxyd object = null;
        if(id != null){
            object = SchoolXxyd.findById(id);
            object.url = null;
            object.save();
        }
        render("SchoolXxyds/show.html",object);
    }
}
