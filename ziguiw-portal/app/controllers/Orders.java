package controllers;

import com.arj.ziguiw.common.PayMethod;
import com.arj.ziguiw.common.PayState;
import com.arj.ziguiw.common.PayType;
import com.arj.ziguiw.payment.AlipayService;
import com.arj.ziguiw.payment.TenpayConfig;
import com.arj.ziguiw.payment.utils.TenpayUtil;
import com.arj.ziguiw.payment.utils.UtilDate;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.Order;
import models.UserBase;
import play.Play;
import play.mvc.With;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Liujy
 * Date: 13-5-3
 * Time: 下午3:07
 */
@With(SecureCAS.class)
public class Orders extends Application {

    @UnSecure
    public static void payOne(){
        //UserBase userBase = (UserBase) renderArgs.get("user");
        render();
    }

    @UnSecure
    public static void payTwo(String type,String amount){
        render(type,amount);
    }

    public static void payment(String type,Integer amount,Integer paymethod){
       if(paymethod == PayMethod.ALIPAY){
           String subject = "";

           UserBase user =(UserBase) renderArgs.get("user");
           String out_trade_no = UtilDate.getOrderNum();

           Order order = new Order();
           if(type.equals(PayType.POINT)){

               subject = "充值子贵网积分:" + new Integer(amount)*100+"个";
               order.type = PayType.POINTS_CHARGE;//代表充值的是积分

           }else if(type.equals(PayType.WEBUSE)){
               subject = "充值子贵网增值服务费 :" + new Integer(amount)+"元";
               order.type = PayType.ANNUAL_FEE;//代表充值网站使用费
           }
           order.orderNo = out_trade_no;
           order.payMethod = PayMethod.ALIPAY;//支付宝支付
           order.price = new Double(amount);//金额
           order.state = PayState.NOT_PAYED;//未支付
           order.user = user;
           order.createDate = new Date();
           order.create();
           alipayTo(out_trade_no,subject,"",order.price+"");

       }else if(paymethod == PayMethod.TENPAY) {
           String product_name =  "";

           UserBase user = (UserBase) renderArgs.get("user");
           String out_trade_no = UtilDate.getOrderNum();

           Order order = new Order();
           if(type.equals(PayType.POINT)){

               product_name = "充值子贵网积分:" + new Integer(amount)*100+"个";
               order.type = PayType.POINTS_CHARGE;//代表充值的是积分

           }else if(type.equals(PayType.WEBUSE)){
               product_name = "充值子贵网增值服务费 :" + new Integer(amount)+"元";
               order.type = PayType.ANNUAL_FEE;//代表充值网站使用费
           }
           order.orderNo = out_trade_no;
           order.payMethod = PayMethod.TENPAY;//财付通支付
           order.price = new Double(amount);//金额
           order.state = PayState.NOT_PAYED;//未支付
           order.user = user;
           order.createDate = new Date();
           order.create();
           tenpayTo(order.price+"",product_name,out_trade_no);
       }
    }

    public static void alipayTo(String _out_trade_no,String _subject,String _body,String _total_fee){
        //必填参数//

        //请与贵网站订单系统中的唯一订单号匹配
        String out_trade_no = _out_trade_no;

        //订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。
        String subject =  _subject;
        //订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里
        //String body = new String(request.getParameter("alibody").getBytes("ISO-8859-1"),"utf-8");
        String body =  _body;
        //订单总金额，显示在支付宝收银台里的“应付总额”里
        String total_fee = _total_fee;

        //扩展功能参数——默认支付方式//

        //默认支付方式，取值见“即时到帐接口”技术文档中的请求参数列表
        String paymethod = "1";
        //默认网银代号，代号列表见“即时到帐接口”技术文档“附录”→“银行列表”
        String defaultbank = "";

        //扩展功能参数——防钓鱼//

        //防钓鱼时间戳
        String anti_phishing_key  = "";
        //获取客户端的IP地址，建议：编写获取客户端IP地址的程序
        String exter_invoke_ip= "";
        //注意：
        //1.请慎重选择是否开启防钓鱼功能
        //2.exter_invoke_ip、anti_phishing_key一旦被设置过，那么它们就会成为必填参数
        //3.开启防钓鱼功能后，服务器、本机电脑必须支持远程XML解析，请配置好该环境。
        //4.建议使用POST方式请求数据
        //示例：
        //anti_phishing_key = AlipayService.query_timestamp();	//获取防钓鱼时间戳函数
        //exter_invoke_ip = "202.1.1.1";

        //扩展功能参数——其他///

        //自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上
        String extra_common_param = "";
        //默认买家支付宝账号
        String buyer_email = "";
        //商品展示地址，要用http:// 格式的完整路径，不允许加?id=123这类自定义参数
        //String show_url = "http://www.xxx.com/order/myorder.jsp";
        String show_url = "";
        //扩展功能参数——分润(若要使用，请按照注释要求的格式赋值)//

        //提成类型，该值为固定值：10，不需要修改
        String royalty_type = "";
        //提成信息集
        String royalty_parameters ="";
        //注意：
        //与需要结合商户网站自身情况动态获取每笔交易的各分润收款账号、各分润金额、各分润说明。最多只能设置10条
        //各分润金额的总和须小于等于total_fee
        //提成信息集格式为：收款方Email_1^金额1^备注1|收款方Email_2^金额2^备注2
        //示例：
        //royalty_type = "10"
        //royalty_parameters	= "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二"

        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("body", body);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("paymethod", paymethod);
        sParaTemp.put("defaultbank", defaultbank);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
        sParaTemp.put("extra_common_param", extra_common_param);
        sParaTemp.put("buyer_email", buyer_email);
        sParaTemp.put("royalty_type", royalty_type);
        sParaTemp.put("royalty_parameters", royalty_parameters);
        if (Play.configuration.get("alipay.notify_url") != null) {
            sParaTemp.put("notify_url", (String) Play.configuration.get("alipay.notify_url"));
        }

        //构造函数，生成请求URL
        String sHtmlText = AlipayService.create_direct_pay_by_user(sParaTemp);
        response.setContentTypeIfNotSet("text/html");
        response.print(sHtmlText);
    }

    public static void tenpayTo(String _order_price,String _product_name,String _order_no){

        String order_price= _order_price;
        /* 商品价格（包含运费），以分为单位 */
        double total_fee = (Double.valueOf(order_price) * 100);
        int fee = (int)total_fee;

        //获取提交的商品名称
        String product_name= _product_name;

        //获取提交的备注信息
        String remarkexplain= "";

        String desc = "商品：" + product_name;

        //获取提交的订单号
        String out_trade_no= _order_no;

        //支付方式
        String trade_mode= "1";


        String currTime = TenpayUtil.getCurrTime();
        //创建支付请求对象
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init();
        //设置密钥
        reqHandler.setKey(TenpayConfig.key);
        //设置支付网关
        reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");


        //-----------------------------
        //设置支付参数
        //-----------------------------
        reqHandler.setParameter("partner", TenpayConfig.partner);		        //商户号
        reqHandler.setParameter("out_trade_no", out_trade_no);		//商家订单号
        reqHandler.setParameter("total_fee", String.valueOf(fee));			        //商品金额,以分为单位
        reqHandler.setParameter("return_url", TenpayConfig.return_url);		    //交易完成后跳转的URL
        reqHandler.setParameter("notify_url", TenpayConfig.notify_url);		    //接收财付通通知的URL
        reqHandler.setParameter("body", desc);	                    //商品描述
        reqHandler.setParameter("bank_type", "DEFAULT");		    //银行类型(中介担保时此参数无效)
        reqHandler.setParameter("spbill_create_ip",request.remoteAddress);   //用户的公网ip，不是商户服务器IP
        reqHandler.setParameter("fee_type", "1");                    //币种，1人民币
        reqHandler.setParameter("subject", desc);              //商品名称(中介交易时必填)

        //系统可选参数
        reqHandler.setParameter("sign_type", "MD5");                //签名类型,默认：MD5
        reqHandler.setParameter("service_version", "1.0");			//版本号，默认为1.0
        reqHandler.setParameter("input_charset", "UTF-8");            //字符编码
        reqHandler.setParameter("sign_key_index", "1");             //密钥序号


        //业务可选参数
        reqHandler.setParameter("attach", "");                      //附加数据，原样返回
        reqHandler.setParameter("product_fee", "");                 //商品费用，必须保证transport_fee + product_fee=total_fee
        reqHandler.setParameter("transport_fee", "0");               //物流费用，必须保证transport_fee + product_fee=total_fee
        reqHandler.setParameter("time_start", currTime);            //订单生成时间，格式为yyyymmddhhmmss
        reqHandler.setParameter("time_expire", "");                 //订单失效时间，格式为yyyymmddhhmmss
        reqHandler.setParameter("buyer_id", "");                    //买方财付通账号
        reqHandler.setParameter("goods_tag", "");                   //商品标记
        reqHandler.setParameter("trade_mode", "1");                 //交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
        reqHandler.setParameter("transport_desc", "");              //物流说明
        reqHandler.setParameter("trans_type", "1");                  //交易类型，1实物交易，2虚拟交易
        reqHandler.setParameter("agentid", "");                     //平台ID
        reqHandler.setParameter("agent_type", "");                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
        reqHandler.setParameter("seller_id", "");                   //卖家商户号，为空则等同于partner

        //请求的url
        String requestUrl = null;
        try {
            requestUrl = reqHandler.getRequestURL();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        //out.print(requestUrl);
        //获取debug信息,建议把请求和debug信息写入日志，方便定位问题
        String debuginfo = reqHandler.getDebugInfo();
        //System.out.println("requestUrl:  " + requestUrl);
        //System.out.println("sign_String:  " + debuginfo);
        //String form = "";
        redirect(requestUrl);

    }

    public static void success(){
        render();
    }
}
