import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-11-27
 * Time: A.M 9:50
 */
public class PostMethodTest {

    @Test
    public void postMethodTest() throws IOException {
        PostMethod postMethod = new PostMethod("http://localhost:8866/test.jsp"){
            @Override
            public String getRequestCharSet() {
                return "UTF-8";
            }
        };
        NameValuePair valuePair = new NameValuePair("name", "刘盟洁");
        NameValuePair[] pairs = new NameValuePair[]{valuePair};
        postMethod.setRequestBody(pairs);
        HttpClient httpClient = new HttpClient();
        httpClient.executeMethod(postMethod);
        String s = new String(postMethod.getResponseBody(), "UTF-8");
        Assert.assertEquals(s.trim(), "刘盟洁");
    }
}
