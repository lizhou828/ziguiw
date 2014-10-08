package com.zigui.utils;



public class AlipayConfig {

	
	public static String service = "create_direct_pay_by_user";
	public static String partner = "2088801672801001";
	
	public static String key = "xeat80fjb8ovcsanrk4khq2703nbulwy";
	
	public static String seller_email = "pay@ziguiw.com";
	
	public static String notify_url = "http://testpay.ziguiw.com/pay/alipay/notify_url.jsp";


	public static String return_url = "http://test.ziguiw.com/payment/success.jsp";
	

	


    public static String log_path = "f:\\alipay_log_" + System.currentTimeMillis()+".txt";

    public static String input_charset = "UTF-8";

    public static String sign_type = "MD5";

}
