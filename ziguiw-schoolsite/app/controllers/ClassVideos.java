package controllers;

import com.arj.ziguiw.common.SchoolPhotoType;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.ClassPhoto;
import models.SchoolClazz;
import play.mvc.With;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-18
 * Time: 上午10:18
 */
@With(SecureCAS.class)
public class ClassVideos extends Application{

        @UnSecure
        public static void pshow(long id){
            SchoolClazz schoolClazz = (SchoolClazz)renderArgs.get("schoolClass");
            ClassPhoto classViedo = ClassPhoto.findById(id);
            List<ClassPhoto> classPhotos = ClassPhoto.findByType(schoolClazz.id, SchoolPhotoType.VIDEO,4);
            renderArgs.put("classId", classViedo.classId);
            render(classViedo,classPhotos);
        }

}
