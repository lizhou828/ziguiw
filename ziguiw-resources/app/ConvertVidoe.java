import com.arj.ziguiw.common.JedisPoolSource;
import com.arj.ziguiw.common.Queues;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-4-25
 * Time: 下午5:25
 */
class ConvertVidoe implements Runnable,Serializable {
    private static final long serialVersionUID = -8568367025140842876L;
    private static Log log = LogFactory.getLog("queue_resource");
    private Map<String,Object> map;
    private JedisPoolSource jedisPoolSource;
    private String inPutPath;

    public ConvertVidoe(Map<String, Object> map, JedisPoolSource jedisPoolSource, String inPutPath){
        this.map = map;
        this.jedisPoolSource = jedisPoolSource;
        this.inPutPath = inPutPath;
    }


    @Override
    public void run() {
        String outPutPath = inPutPath.substring(0, inPutPath.lastIndexOf(".") + 1) + "mp4";
        String command = String.format("ffmpeg -i %s -strict -2 -ar 22050 %s", inPutPath, outPutPath);
        log.info(command);
        Runtime r = Runtime.getRuntime();
        try {
            r.exec(command);
        } catch (Exception e) {
            log.error("receive resource message failed!", e);
            jedisPoolSource.rpush(Queues.RESOURCE_CONVERSION, map);
        }
    }
}
