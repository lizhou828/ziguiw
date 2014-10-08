import models.Menu;
import play.jobs.Job;
import play.test.Fixtures;

import java.io.IOException;

public class Bootstrap extends Job {

//    private static boolean b;

    public void doJob() throws IOException {
        if (Menu.count() == 0) {
            Fixtures.load("initial-data-menu.yml");
        }
//        if (!b) {
//            List<SchoolPhoto> schoolPhotos = SchoolPhoto.findAll();
//            for (SchoolPhoto schoolPhoto : schoolPhotos) {
//                if (schoolPhoto.type == SchoolPhotoType.PHOTO) {
//                    FileUtils.compressImages("/data/" + schoolPhoto.url, new String []{ImageSize.SSX,ImageSize.SZX,ImageSize.SZD,ImageSize.SKL});
//                }
//            }
//            b = true;
//        }
    }
}