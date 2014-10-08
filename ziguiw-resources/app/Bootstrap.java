import com.arj.ziguiw.common.JedisPoolSource;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.ResourceConversion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import play.Play;
import play.jobs.Every;
import play.jobs.Job;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: Liujy
 * Date: 13-4-25
 * Time: 上午10:26
 */
@Every("15s")
public class Bootstrap extends Job {
    private static Log log = LogFactory.getLog("queue_resource");
    private JedisPoolSource jedisPoolSource = new JedisPoolSource((String) Play.configuration.get("redis.host"),
            Play.configuration.get("redis.port") == null ? null : Integer.parseInt(Play.configuration.get("redis.port").toString()) );

    private static final int COREPOOLSIZE = 5;
    private static final int MAXINUMPOOLSIZE = 10;
    private static final long KEEPALIVETIME = 15;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORKQUEUE = new ArrayBlockingQueue<Runnable>(5);
    private static final ThreadPoolExecutor.CallerRunsPolicy HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(COREPOOLSIZE, MAXINUMPOOLSIZE, KEEPALIVETIME, UNIT, WORKQUEUE, HANDLER);

    @Override
    public void doJob() throws Exception {
        receiveResourceConversion();

    }

    private void receiveResourceConversion() {
        for (int x = 0; x <= 20; x++) {
            Map<String, Object> message = (Map<String, Object>) jedisPoolSource.lpop(Queues.RESOURCE_CONVERSION);
            if(message != null){
                try{
                    log.info(String.format("The resource conversion message is %s",message));
                    String resourcePath = message.get(ResourceConversion.RESOURCE_PATH).toString();
                    String resourceFileType = message.get(ResourceConversion.RESOURCE_TYPE).toString().toUpperCase();
                    threadPool.execute(new ConvertDoc(message, jedisPoolSource, resourcePath));
                }catch (Throwable t){
                    log.error("receive resource message failed!", t);
                    jedisPoolSource.rpush(Queues.RESOURCE_CONVERSION, message);
                }
            }
        }
    }
}
