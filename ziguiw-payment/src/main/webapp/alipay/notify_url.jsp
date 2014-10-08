<%
/* *
 功能：支付宝服务器异步通知页面
 版本：3.2
 日期：2011-03-17
 说明：
 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 //***********页面功能说明***********
 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
 该页面调试工具请使用写文本函数logResult，该函数在com.alipay.util文件夹的AlipayNotify.java类文件中
 如果没有收到该页面返回的 success 信息，支付宝会在24小时内按一定的时间策略重发通知
 TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
 TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zhifubao.all.*" %>
<%@ page import="com.ziguiw.dao.*" %>
<%@ page import="com.ziguiw.domain.*" %>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@ page import="com.arj.ziguiw.common.PayState" %>
<%@ page import="com.arj.ziguiw.common.PayType" %>
<%@ page import="com.arj.ziguiw.common.PointType" %>
<%@ page import="com.ziguiw.util.JedisPoolSourceUtils" %>
<%@ page import="com.arj.ziguiw.common.Queues" %>
<%
Log log = LogFactory.getLog("pay");
String order_no = null;
String trade_no = null;
try {
	request.setCharacterEncoding("UTF-8");
	OrderDao orderDao = new OrderDao();
	UserDao userDao = new UserDao();
	PointHistoryDao pointHistoryDao = new PointHistoryDao();
	//获取支付宝POST过来反馈信息
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
		//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
		params.put(name, valueStr);
	}


	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	
	 trade_no = request.getParameter("trade_no");				//支付宝交易号
	 order_no = request.getParameter("out_trade_no");	        //获取订单号
	String total_fee = request.getParameter("total_fee");	        //获取总金额
	String subject = request.getParameter("subject");//商品名称、订单名称
	String body = "";
	if(request.getParameter("body") != null){
		//body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "utf-8");//商品描述、订单备注、描述
		body = request.getParameter("body");
	}
	String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
	String trade_status = request.getParameter("trade_status");		//交易状态
	
	//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
	boolean verify_result = AlipayNotify.verify(params);
	
	System.out.println();
	System.out.println();
	System.out.println("verify_result==============验证结果为："+verify_result);
	System.out.println();
	
	
	if(verify_result){//验证成功
		
		if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
			//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
			synchronized (Object.class){
				
				Order order = orderDao.getByOrderNo(order_no);
				System.out.println("order 的支付状态为== "+ order.getIsPay());
				if(order.getIsPay() == PayState.NOT_PAYED){
					order.setOrder_no(order_no);
					order.setIsPay(PayState.PAYED);
					orderDao.update(order);
					
					if(order.getType() == PayType.POINTS_CHARGE){
						long userId = order.getUserId();
                        int points = order.getAmount() * 100;
                        Map<String,Object> message = PointType.newMessage(userId,null,PointType.CHONGZHIJIFEN,points,PointType.ADD_POINT);
                        JedisPoolSourceUtils.getJedisPoollSource().rpush(Queues.USER_POINTS,message);
					}
				}
				log.info(String.format("pay success! orderno : %s", order_no+"  trade_no = "+trade_no+"\n"));
				out.println("success");	//请不要修改或删除——
			}	
			
		} 

			
		//////////////////////////////////////////////////////////////////////////////////////////
	}else{//验证失败
		log.error("payment notified failed! verify_result=" + verify_result + " orderno" + order_no+"  trade_no = "+trade_no);
		out.println("fail");
	}
	
} catch (Exception e) {
	log.error("payment notified failed! orderno=" + order_no+"  trade_no = "+trade_no, e);
	out.print("fail");
}
%>