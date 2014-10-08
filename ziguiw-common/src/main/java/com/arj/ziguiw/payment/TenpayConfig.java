package com.arj.ziguiw.payment;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 13-5-6
 * Time: 下午3:17
 * To change this template use File | Settings | File Templates.
 */
public class TenpayConfig {
    //收款方
    public static final String spname = "湖南爱瑞杰科技发展股份有限公司";

    //商户号
    public static final String partner = "1214743501";

    //密钥
    public static final String key = "07790ebff50d20b3aaf4ce51a62582af";

    //交易完成后跳转的URL
    public static final String return_url = "http://www.ziguiw.com/Orders/success";

    //接收财付通通知的URL
    public static final String notify_url = "http://pay.ziguiw.com/pay/tenpay/payNotifyUrl.jsp";
}
