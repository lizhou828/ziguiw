import play.Play;
import play.server.Server;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-12-29
 * Time: P.M 3:27
 */

public class Main {


    public static void main(String[] args) throws Exception {
        String applicationPath = args.length > 0 ? args[0] : null;
        if (applicationPath == null) {
            applicationPath = Main.class.getResource("/").getPath();
            int idx = applicationPath.lastIndexOf("/");
            applicationPath = applicationPath.substring(0, idx);
            idx = applicationPath.lastIndexOf("/");
            applicationPath = applicationPath.substring(0, idx);
            idx = applicationPath.lastIndexOf("/");
            applicationPath = applicationPath.substring(0, idx);
        }
        System.out.println(String.format("the application path is %s", applicationPath));
        Play.frameworkPath = new File(applicationPath.substring(0, applicationPath.lastIndexOf("/")) + "/playframework");
        System.setProperty("application.path", applicationPath);
        Server.main(new String[]{});
    }

}
