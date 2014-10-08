import models.UserBase;
import play.jobs.Job;
import play.test.Fixtures;

public class Bootstrap extends Job {
 
    public void doJob() {
        // Check if the database is empty
        if(UserBase.count() == 0) {
            Fixtures.load("initial-data.yml");
        }
    }
 
}