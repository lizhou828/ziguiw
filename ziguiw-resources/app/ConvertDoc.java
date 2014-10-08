import com.arj.ziguiw.common.JedisPoolSource;
import com.arj.ziguiw.common.Queues;
import com.arj.ziguiw.common.utils.JsonUtils;
import controllers.ConvertToSWF;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liujy
 * Date: 13-4-26
 * Time: 下午2:37
 */
public class ConvertDoc implements Runnable {

    private Map<String, Object> message;

    private JedisPoolSource jedisPoolSource;

    private String resourcePath;
    private static final Log log = LogFactory.getLog("queue_resource");

    public ConvertDoc(Map<String, Object> message, JedisPoolSource jedisPoolSource, String resourcePath) {
        this.message = message;
        this.jedisPoolSource = jedisPoolSource;
        this.resourcePath = resourcePath;
    }

    @Override
    public void run() {
        String pdfPath = resourcePath.substring(0,resourcePath.lastIndexOf(".") + 1) + "pdf";
        String swfPath = resourcePath.substring(0,resourcePath.lastIndexOf(".") + 1) + "swf";
        boolean  flag = ConvertToSWF.convertFile(resourcePath, pdfPath, swfPath);
        if(!flag){
            try {
                log.error(String.format("receive resource message failed! message: %s", JsonUtils.parse(message)));
            } catch (IOException e) {
                //do nothing
            }
            jedisPoolSource.rpush(Queues.RESOURCE_CONVERSION, message);
        }

    }
}
