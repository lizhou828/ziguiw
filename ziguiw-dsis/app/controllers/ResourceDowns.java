package controllers;

import models.Page;
import models.Resource;
import models.ResourceDown;
import models.UserBase;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: A.M.10:07
 */
public class ResourceDowns extends Application{
    public static void list(){
        UserBase userBase = (UserBase)renderArgs.get("userBase");
        Page<Resource> page = ResourceDown.findPage(userBase, getPage(), getPageSize());
        render(page);
    }
}
