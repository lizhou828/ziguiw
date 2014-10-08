package controllers;

import models.Page;
import models.PointsHistory;
import models.UserBase;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-30
 * Time: P.M.2:51
 */
public class PointsHistories extends Application{

    public static void index(){
        UserBase userBase=(UserBase)renderArgs.get("userBase");
        Page<PointsHistory> page = PointsHistory.findPage(userBase,getPage(),getPageSize());
        render(page,userBase);
    }

}
