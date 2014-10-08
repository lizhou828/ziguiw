import play.Play;
import play.jobs.Job;

import java.io.File;

/**
 * on application stop, delete the pid file
 * User: pengchangguo
 * Date: 13-3-21
 * Time: P.M 2:13
 */

public class Stop extends Job {

    private static boolean exec;

    @Override
    public void doJob() throws Exception {
        if (!exec) {
            File file = Play.applicationPath;
            for (File file1 : file.listFiles()) {
                if (file1.getName().equals("server.pid")) {
                    file1.delete();
                }
            }
            exec = true;
        }
    }
}
