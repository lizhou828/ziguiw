import play.jobs.Every;
import play.jobs.Job;
import play.templates.TemplateLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-19
 * Time: A.M 9:34
 */

@Every(value = "7d")
public class LogDb extends Job {

    @Override
    public void doJob() throws Exception {
        createLogTable();
    }

    private void createLogTable() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test", "sa", "kxbsnljr");
            Statement statement = connection.createStatement();
//            TemplateEngine engine = new SimpleTemplateEngine();
//            Template template = engine.createTemplate(new File(this.getClass().getResource("/views/h2.html").getPath()));
//            String sql = template.make().toString();
            String sql = TemplateLoader.load("h2.html").render();
            statement.executeUpdate(sql);
            connection.commit();
        }  finally {
            if (connection != null) connection.close();
        }
    }
}
