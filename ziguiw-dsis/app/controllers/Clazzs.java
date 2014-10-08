package controllers;

import models.Clazz;
import models.TermSet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-28
 * Time: P.M.2:44
 */
public class Clazzs extends Application{
    public static void findTermByClazz(Integer bjId){
        Clazz clazz=Clazz.findById(bjId);
        List<TermSet> termSetList= TermSet.find("byXxId", clazz.xxId).fetch();
        renderJSON(termSetList);
    }
}
