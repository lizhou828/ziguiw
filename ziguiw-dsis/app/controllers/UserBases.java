package controllers;

import models.UserBase;
import play.exceptions.TemplateNotFoundException;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: liubingbing
 * Date: 12-12-30
 * Time: P.M 2:34
 */
public class UserBases extends Application{

    public static void create(UserBase object, File photo) {
        validation.valid(object);
        if (validation.hasErrors()) {
            renderArgs.put("error", "创建失败!");
            render("UserBases/blank.html", object);
        }
        object.create();
        flash.success("创建成功!");
        redirect(request.controller + ".list");
    }
}
