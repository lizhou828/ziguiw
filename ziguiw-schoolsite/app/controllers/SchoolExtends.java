package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import models.SchoolExtend;
import play.data.binding.Binder;
import play.mvc.With;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: liubingbing
 * Date: 13-1-12
 * Time: P.M 11:14
 */
@With(SecureCAS.class)
public class SchoolExtends extends Application {

    public static void create(SchoolExtend object, File file ,File banner) throws Exception {
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render("SchoolExtends/blank.html", object);
        }
        if (file != null) {
            object.logo = FileUtils.copyImage(file, file.getName(), uploadDir);
        }
        if (banner != null) {
            object.banner = FileUtils.copyImage(banner, banner.getName(), uploadDir);
        }
        object.create();
        flash.success("创建成功!");
        //show(object.schoolId + "");
        redirect("SchoolExtends.show", object.schoolId);
    }

    public static void save(Long id, File file ,File banner) throws Exception {
        SchoolExtend object = SchoolExtend.findById(id);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("SchoolExtends/blank.html", object);
        }
        if (file != null) {
            object.logo = FileUtils.copyImage(file, file.getName(), uploadDir);
        }
        if (banner != null) {
            object.banner = FileUtils.copyImage(banner, banner.getName(), uploadDir);
        }
        object.save();
        flash.success("保存成功!");
        //show(object.schoolId + "");
        redirect("SchoolExtends.show", object.schoolId);
    }
}
