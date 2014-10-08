package controllers;

import controllers.modules.cas.SecureCAS;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 12-12-20
 * Time: P.M 2:13
 */
@With(SecureCAS.class)
public class Students extends CRUD {

}
