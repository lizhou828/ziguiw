package controllers;

import com.arj.ziguiw.common.UserBaseType;
import models.SchoolAdmin;
import models.SchoolClazz;
import models.UserBase;
import org.apache.commons.codec.digest.DigestUtils;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import play.mvc.Util;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-4
 * Time: 下午2:38
 */
public class SchoolAdmins extends Application{

    @Util
    public static void beforeCreate(Model object) {
            SchoolAdmin schoolAdmin  = (SchoolAdmin)object;
            UserBase userBase = UserBase.find("byNickName",schoolAdmin.account).first();
            if(userBase != null) {
                renderArgs.put("error", "用户已存在!");
            }
            String password = request.params.get("password");
            UserBase user = new UserBase();
            user.nickName = schoolAdmin.account;
            user.password = DigestUtils.md5Hex(password);
            user.type = UserBaseType.SCHOOL_EDITOR;
            user.create();
    }

    public static void show(Long id) throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        SchoolAdmin object = SchoolAdmin.findById(id);
        List<SchoolClazz> schoolClazz = SchoolClazz.findByXXId(object.school.xxId , 70);
        notFoundIfNull(object);
        try {
            render(type, object ,schoolClazz);
        } catch (TemplateNotFoundException e) {
            render("CRUD/show.html", type, object);
        }
    }
}
