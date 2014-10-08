package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.utils.FileUtils;
import models.QueryParams;
import models.SchoolTeacher;
import play.data.binding.Binder;
import play.mvc.Util;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-30
 * Time: 下午3:42
 */
public class SchoolTeachers extends Application{


    public static void create(SchoolTeacher object, File file) throws Exception {
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render(request.controller.replace(".", "/") + "/blank.html", object);
        }
        if (file != null) {
            //object.photo = FileUtils.copyImage(file,file.getName(),uploadDir);
           object.photo = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.TC});
        }
        object.create();
        flash.success("创建成功!");
        //show(object.schoolId + "");
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        if (params.get("_saveAndAddAnother") != null) {
            redirect(request.controller + ".blank");
        }
        redirect(request.controller + ".show", object._key());
    }

    public static void save(Long id, Long xxId ,File file){
        SchoolTeacher object = SchoolTeacher.findById(id);
        notFoundIfNull(object);
        Binder.bindBean(params.getRootParamNode(), "object", object);
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "保存失败!");
            render("SchoolTeachers/blank.html", object);
        }
        if(file != null){
            //object.photo = FileUtils.copyImage(file,file.getName(),uploadDir);
            object.photo = FileUtils.copyAndCompressImage(file, file.getName(), uploadDir, new String[]{ImageSize.TC});
        }
        object.save();
        flash.success("保存成功!");
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", object._key());
    }

    @Util
    public static void beforeQuery(QueryParams queryParams) {
        if (queryParams.orderBy == null) queryParams.orderBy = "id";
        if (queryParams.order == null) queryParams.order = "DESC";
    }

}
