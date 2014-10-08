package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import models.SchoolExtend;
import play.data.binding.Binder;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 下午3:18
 */
public class SchoolExtends extends Application{

    public static void save(Long id, File file , File banner) throws Exception {
        SchoolExtend object = SchoolExtend.findById(id);
        Binder.bindBean(params.getRootParamNode(), "object", object);
//        validation.valid(object);
//        if (validation.hasErrors()) {
//            renderArgs.put("error", "保存失败!");
//            render("SchoolExtends/show.html", object);
//        }
        if (file != null) {
            object.logo = FileUtils.copyImage(file, file.getName(), uploadDir);
        }
        if (banner != null) {
            object.banner = FileUtils.copyImage(banner, banner.getName(), uploadDir);
        }
        object.save();
        flash.success("保存成功!");
        redirect("SchoolExtends.list");
    }
}
