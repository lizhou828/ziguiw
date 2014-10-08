package com.arj.ziguiw.datasynch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-9-18
 * Time: P.M2:45
 */
public class HttpUtil {

    private static Log log = LogFactory.getLog(HttpUtil.class);

    /**
     * to do getContent from httpUrl
     * @param httpUrl of  WJ URL
     * @param charsetName response charset
     * @return String with response content
     * @throws java.io.IOException of url
     */
    public static String connect(String httpUrl, String charsetName) throws IOException{
        HttpURLConnection httpConn = null;
        try {
            URL url = new URL(httpUrl);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.connect();
            InputStream is = httpConn.getInputStream();
            StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, charsetName));
            String tmp;
            while ((tmp = bufferedReader.readLine()) != null) {
                sb.append(tmp);
            }
            return sb.toString();
        } catch (Exception e) {
            log.error(String.format("connect the rpc destination %s error!", httpUrl), e);
            throw new IllegalStateException(e);
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
        }
    }

}



