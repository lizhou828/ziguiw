package controllers;

import com.arj.ziguiw.common.ImageSize;
import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.School;
import models.SchoolTeacher;
import play.data.binding.Binder;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.With;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-11
 * Time: P.M 4:45
 */
@With(SecureCAS.class)
public class SchoolTeachers extends Application {

    @UnSecure
    public static void plist() {
        List<SchoolTeacher> schoolTeachers = SchoolTeacher.find("from SchoolTeacher where school.id = ? and status = ? ",
                ((School) renderArgs.get("school")).id, Status.OK).fetch();
        render(schoolTeachers);
    }

    public static void create(SchoolTeacher object, File file) throws Exception {
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render(request.controller.replace(".", "/") + "/blank.html", object);
        }
        if (file != null) {
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
            object.photo = FileUtils.copyAndCompressImage(file,file.getName(),uploadDir, new String []{ImageSize.TC});
        }
        object.save();
        flash.success("保存成功!");
        if (params.get("_save") != null) {
            redirect(request.controller + ".list");
        }
        redirect(request.controller + ".show", object._key());
    }


    public static void delete(Long id){
        SchoolTeacher object = SchoolTeacher.findById(id);
        notFoundIfNull(object);
        object.status = Status.DELETED;
        object.save();
        redirect(request.controller + ".list");
    }

    public static void list(String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        int page = getPage();
        if (orderBy == null) orderBy = "id";
        if (order == null) order = "DESC";
        String condition = String.format("school.id = %s and status != %s",
                ((School)(renderArgs.get("school"))).id,Status.DELETED);
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, condition);
        Long count = type.count(search, searchFields, condition);
        Long totalCount = type.count(null, null, condition);
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }
}
