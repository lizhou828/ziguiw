package controllers;

import com.arj.ziguiw.common.utils.FileUtils;
import com.arj.ziguiw.common.utils.JsonUtils;
import models.FatherActivity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.lang.String.format;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-27
 * Time: 下午3:00
 */
public class WeiXin extends Application{

    public static final String TOKEN = "ziguiw";

    private static final Log logger = LogFactory.getLog(WeiXin.class);

    static List<NameValuePair> params = new ArrayList<NameValuePair>();

    public static void call() throws IOException {
        if ("GET".equalsIgnoreCase(request.method)) {
            String signature = request.params.get("signature");
            String timestamp = request.params.get("timestamp");
            String nonce = request.params.get("nonce");
            logger.info(String.format("weixin get params signature : %s, timestamp : %s, nonce : %s",
                    signature, timestamp, nonce));
            String[] arr = {TOKEN, timestamp, nonce};
            Arrays.sort(arr);
            String encrypt = encrypt(StringUtils.join(arr));
            String echostr = request.params.get("echostr");
            if (encrypt.equals(signature)) {
                if (StringUtils.isNotBlank(echostr)){
                    logger.info("weixin success!");
                    response.print(echostr);
                }
            }
        } else if ("POST".equalsIgnoreCase(request.method)) {
            String _body = "";
            try {
                List<String> list = IOUtils.readLines(request.body);
                for (String s : list) {
                    _body += s;
                    _body += "\n";
                }
            } catch (IOException e) {
                logger.error("read request body error!", e);
                throw new RuntimeException(e);
            }
            if (_body != "") logger.info("weixin post : " + _body);
            WeiXinMsg weiXinMsg = WeiXinMsg.getInstance(_body);
            String reply = weiXinMsg.reply();
            logger.info("reply : " + reply);
            response.print(reply);
        }
    }

    public static void menus() throws IOException {
        Map<String,List> parentMap = new HashMap<String,List>();
        Map<String,Object> childMap = new HashMap<String,Object>();
        Map<String,Object> map = new HashMap<String,Object>();
        List<Map> childList =  new ArrayList<Map>();
        List<Map> list = new ArrayList<Map>();

        //学生咨询菜单
        map.put("type","click");
        map.put("name","学籍档案");
        map.put("key","ZIGUIW_XJDA");
        list.add(map);
        map.clear();
        map.put("type", "click");
        map.put("name", "学生考勤");
        map.put("key", "ZIGUIW_XSKQ");
        list.add(map);
        map.clear();
        map.put("type", "click");
        map.put("name","学生作业");
        map.put("key","ZIGUIW_XSZY");
        list.add(map);
        map.clear();
        map.put("type", "click");
        map.put("name","学校/班级通知");
        map.put("key","ZIGUIW_TZ");
        list.add(map);
        map.clear();
        map.put("type", "click");
        map.put("name","最新消费");
        map.put("key","ZIGUIW_XF");
        list.add(map);
        map.clear();
        childMap.put("name","学生咨询");
        childMap.put("sub_button",list);
        childList.add(childMap);
        childMap.clear();
        list.clear();

        //班级空间菜单
        map.put("type","click");
        map.put("name","校园新闻/公告");
        map.put("key","ZIGUIW_XYXW");
        list.add(map);
        map.clear();
        map.put("type","click");
        map.put("name","班级新闻/公告");
        map.put("key","ZIGUIW_BJXW");
        list.add(map);
        map.clear();
        map.put("type","click");
        map.put("name","班级相册");
        map.put("key","ZIGUIW_BJXC");
        list.add(map);
        map.clear();
        map.put("type","click");
        map.put("name","学习园地");
        map.put("key","ZIGUIW_XXYD");
        list.add(map);
        map.clear();
        map.put("type","click");
        map.put("name","教育资源");
        map.put("key","ZIGUIW_JYZY");
        list.add(map);
        map.clear();
        childMap.put("name","班级空间");
        childMap.put("sub_button",list);
        childList.add(childMap);
        childMap.clear();
        list.clear();

        //班级互动菜单
        map.put("type","click");
        map.put("name","班级成员列表");
        map.put("key","ZIGUIW_BJCYLB");
        list.add(map);
        map.clear();
        map.put("type","click");
        map.put("name","班级话题");
        map.put("key","ZIGUIW_BJHT");
        list.add(map);
        map.clear();
        childMap.put("name","班级互动");
        childMap.put("sub_button",list);
        childList.add(childMap);
        childMap.clear();
        list.clear();

        parentMap.put("button",childList);
        String params = JsonUtils.parse(parentMap);

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ziguiw");
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        NameValuePair nameValuePair = new NameValuePair();
        nameValuePair.setValue(params);
        method.setRequestBody(new NameValuePair[]{nameValuePair});
        try {
            int status = client.executeMethod(method);
            if(status == HttpStatus.SC_OK) {
                byte[] responseBody = method.getResponseBody();
                String result = new String(responseBody, "UTF-8");
                logger.info(result);
                response.print(result);
            }
        } catch (IOException e) {
            logger.error("weixin post create menu excpeiton!", e);
        }
    }

    public static String encrypt(String strSrc) {
        MessageDigest md;
        String strDes = "";
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(bt);
            byte[] bytes = md.digest();
            String tmp;
            for (byte aByte : bytes) {
                tmp = (Integer.toHexString(aByte & 0xFF));
                if (tmp.length() == 1) {
                    strDes += "0";
                }
                strDes += tmp;
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error("weixin get encrypt exception!", e);
            return null;
        }
        return strDes;
    }

    private static abstract class WeiXinMsg {

        protected String toUserName;

        protected String fromUserName;

        protected String createTime;

        protected String msgType;

        protected String msgId;

        public static WeiXinMsg getInstance(String body) {
            SAXReader saxReader = new SAXReader();
            Document document;
            try {
                document = saxReader.read(new ByteArrayInputStream(body.getBytes()));
            } catch (DocumentException e) {
                logger.error("parse request body error!", e);
                throw new RuntimeException(e);
            }
            Element element = document.getRootElement();
            Element msgType = element.element("MsgType");
            if (msgType != null) {
                if (msgType.getText().equalsIgnoreCase("event")) return new WeiXinEventMsg(document);
                if (msgType.getText().equalsIgnoreCase("text")) return new WeiXinTextMsg(document);
                if (msgType.getText().equalsIgnoreCase("image")) return new WeiXinImgMsg(document);
            }
            return null;
        }

        protected WeiXinMsg(Document document) {
            Element element = document.getRootElement();
            Element toUserName = element.element("ToUserName");
            Element fromUserName = element.element("FromUserName");
            Element createTime = element.element("CreateTime");
            Element msgType = element.element("MsgType");
            Element msgId = element.element("MsgId");
            if (toUserName != null) this.toUserName = toUserName.getText();
            if (fromUserName != null) this.fromUserName = fromUserName.getText();
            if (createTime != null) this.createTime = createTime.getText();
            if (msgType != null) this.msgType = msgType.getText();
            if (msgId != null) this.msgId = msgId.getText();
        }

        public abstract String reply() throws IOException;

    }

    private static class WeiXinEventMsg extends WeiXinMsg {

        protected String event;

        protected String eventKey;

        protected WeiXinEventMsg(Document document) {
            super(document);
            Element element = document.getRootElement();
            Element event = element.element("Event");
            Element eventKey = element.element("EventKey");
            if (event != null) this.event = event.getText();
            if (eventKey != null) this.eventKey = eventKey.getText();
        }

        @Override
        public String reply() {
            if ("subscribe".equalsIgnoreCase(event)) {
                return "<xml>\n" +
                        "<ToUserName><![CDATA[" + this.fromUserName + "]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[" + this.toUserName + "]]></FromUserName>\n" +
                        "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[感谢您关注子贵网！\n" +
                        "浓情六月，让爱不再含蓄。点击http://father.ziguiw.com了解活动详情。\n" +
                        "回复活动序号，参与父亲节献礼活动：\n\n" +
                        "1、父亲画像/影像征集；\n" +
                        "2、爸爸，我想对你说。\n\n" +
                        "参与活动即可为父亲们\"赚取\"一份精美的礼品哦！(不回复即不参与)" +
                        "]]></Content>\n" +
                        "<FuncFlag>0</FuncFlag>\n" +
                        "</xml>";
            }
            return null;
        }
    }

    private static class WeiXinTextMsg extends WeiXinMsg {

        protected String content;


        public WeiXinTextMsg(Document document) {
            super(document);
            Element element = document.getRootElement();
            Element content = element.element("Content");
            if (content != null) this.content = content.getText();
        }

        @Override
        public String reply() {
            if (content != null) {
                if (content.trim().equals("1")) {
                    return "<xml>\n" +
                            "<ToUserName><![CDATA[" + this.fromUserName + "]]></ToUserName>\n" +
                            "<FromUserName><![CDATA[" + this.toUserName + "]]></FromUserName>\n" +
                            "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>\n" +
                            "<MsgType><![CDATA[text]]></MsgType>\n" +
                            "<Content><![CDATA[您即将参与活动一<<父亲画像/影像征集>>\n" +
                            "请将手机拍摄的\"父亲的画像/影像作品\"发送给我们。]]></Content>\n" +
                            "<FuncFlag>0</FuncFlag>\n" +
                            "</xml>";
                }
                if (content.trim().equals("2")) {
                    return "<xml>\n" +
                            "<ToUserName><![CDATA[" + this.fromUserName + "]]></ToUserName>\n" +
                            "<FromUserName><![CDATA[" + this.toUserName + "]]></FromUserName>\n" +
                            "<CreateTime>" + System.currentTimeMillis()/1000 + "</CreateTime>\n" +
                            "<MsgType><![CDATA[text]]></MsgType>\n" +
                            "<Content><![CDATA[您即将参与活动二<<爸爸，我想对您说>>\n点击" +
                            "http://www.ziguiw.com/FatherMessages/wxAdd?userName=" + this.fromUserName + "填写相关资料参与活动。\n" +
                            "]]></Content>\n" +
                            "<FuncFlag>0</FuncFlag>\n" +
                            "</xml>";
                }
            }
            return null;
        }
    }

    private static class WeiXinImgMsg extends WeiXinMsg {

        private String picUrl;

        public WeiXinImgMsg(Document document) {
            super(document);
            Element element = document.getRootElement();
            Element picUrl = element.element("PicUrl");
            if (picUrl != null) this.picUrl = picUrl.getText();
        }

        @Override
        public String reply() throws IOException {
            String destFilePath = genericDestFilePath(this.picUrl, "images");
            File dir = new File(uploadDir + destFilePath.substring(0, destFilePath.lastIndexOf("/")));
            if (!dir.exists()) {
                if (!dir.mkdirs())
                    throw new UnsupportedOperationException(String.format("create dir %s failed!", dir.getAbsolutePath()));
            }
            IOUtils.copy(new URL(this.picUrl).openStream(), new FileOutputStream(new File(uploadDir + "/" + destFilePath)));
            String url = uploadDir.substring(uploadDir.lastIndexOf("/") + 1) + "/" + destFilePath;
            String diskFilePath = format("%s/%s", uploadDir, destFilePath);
            FileUtils.compressImages(diskFilePath, new String[]{"284X210"});
            FatherActivity fatherActivity = new FatherActivity();
            fatherActivity.url = url;
            fatherActivity.userName = this.fromUserName;
            fatherActivity.create();
            return "<xml>\n" +
                    "<ToUserName><![CDATA[" + this.fromUserName + "]]></ToUserName>\n" +
                    "<FromUserName><![CDATA[" + this.toUserName + "]]></FromUserName>\n" +
                    "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>\n" +
                    "<MsgType><![CDATA[text]]></MsgType>\n" +
                    "<Content><![CDATA[作品上传成功，点击http://www.ziguiw.com/FatherActivitys/wxAdd?fatherActivityId="
                    + fatherActivity.id + "补充相关资料]]></Content>\n" +
                    "<FuncFlag>0</FuncFlag>\n" +
                    "</xml>";
        }
    }

    private static String genericDestFilePath(String fileName, String objDir) {
        Calendar cal = Calendar.getInstance();
        String month;
        int monthNumber = cal.get(Calendar.MONTH) + 1;
        if (monthNumber < 10) {
            month = "0" + monthNumber;
        } else {
            month = String.valueOf(monthNumber);
        }
        String calDate;
        if (cal.get(Calendar.DATE) < 10) {
            calDate = "0" + cal.get(Calendar.DATE);
        } else {
            calDate = String.valueOf(cal.get(Calendar.DATE));
        }
        return String.format("%s/%s/%s%s/%s",objDir,
                cal.get(Calendar.YEAR), month, calDate,System.currentTimeMillis() + ".jpg");
    }
}
