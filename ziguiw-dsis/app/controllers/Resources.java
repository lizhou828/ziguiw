package controllers;

import models.Page;
import models.Resource;
import models.UserBase;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: A.M.10:06
 */
public class Resources extends Application{
    public static void list(){
        UserBase userBase = (UserBase)renderArgs.get("userBase");
        Page<Resource> page = Resource.findPage(userBase,getPage(),getPageSize());
        render(page);
    }
}
