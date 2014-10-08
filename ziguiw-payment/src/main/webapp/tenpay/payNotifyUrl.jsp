<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tenpay.all.*"%>
<%@ page import="com.ziguiw.dao.*" %>
<%@ page import="com.ziguiw.domain.*" %>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@ include file = "config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
//---------------------------------------------------------
//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
//---------------------------------------------------------
Log log = LogFactory.getLog("pay");
String out_trade_no = "";
String tenpay_no = "";
try{
PointHistoryDao pointHistoryDao = new PointHistoryDao();
OrderDao orderDao = new OrderDao();
UserDao userDao  = new UserDao();


//创建支付应答对象
ResponseHandler resHandler = new ResponseHandler(request, response);
resHandler.setKey(key);

System.out.println("后台回调返回参数:"+resHandler.getAllParameters());
tenpay_no = resHandler.getParameter("transaction_id");
//判断签名
if(resHandler.isTenpaySign()) {
	
	//通知id
	String notify_id = resHandler.getParameter("notify_id");
	
	//创建请求对象
	RequestHandler queryReq = new RequestHandler(null, null);
	//通信对象
	TenpayHttpClient httpClient = new TenpayHttpClient();
	//应答对象
	ClientResponseHandler queryRes = new ClientResponseHandler();
	
	//通过通知ID查询，确保通知来至财付通
	queryReq.init();
	queryReq.setKey(key);
	queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
	queryReq.setParameter("partner", partner);
	queryReq.setParameter("notify_id", notify_id);
	
	//通信对象
	httpClient.setTimeOut(5);
	//设置请求内容
	httpClient.setReqContent(queryReq.getRequestURL());
	System.out.println("验证ID请求字符串:" + queryReq.getRequestURL());
	
	//后台调用
	if(httpClient.call()) {
		//设置结果参数
		queryRes.setContent(httpClient.getResContent());
		System.out.println("验证ID返回字符串:" + httpClient.getResContent());
		queryRes.setKey(key);
			
			
		//获取id验证返回状态码，0表示此通知id是财付通发起
		String retcode = queryRes.getParameter("retcode");
		
		//商户订单号
		out_trade_no = resHandler.getParameter("out_trade_no");
		//财付通订单号
		String transaction_id = resHandler.getParameter("transaction_id");
		//金额,以分为单位
		String total_fee = resHandler.getParameter("total_fee");
		//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
		String discount = resHandler.getParameter("discount");
		//支付结果
		String trade_state = resHandler.getParameter("trade_state");
		//交易模式，1即时到账，2中介担保
		String trade_mode = resHandler.getParameter("trade_mode");
			
		//判断签名及结果
		if(queryRes.isTenpaySign()&& "0".equals(retcode)){ 
			
			if("1".equals(trade_mode)){       //即时到账 
				if( "0".equals(trade_state)){
					
					synchronized (Object.class){
						Order order = orderDao.getByOrderNo(out_trade_no);
						if(order.getIsPay()==0){
							order.setOrder_no(out_trade_no);
							order.setIsPay(1);
							orderDao.update(order);
							if(order.getType() == 1){
								int userId = order.getUserId();
								User user = userDao.getById(userId);
								user.setPoints(user.getPoints()+2);
								userDao.update(user);
								
								PointHistory ph = new PointHistory();
								ph.setFlag(1);
								ph.setPoint_chang(order.getAmount()*10);
								ph.setState(0);
								ph.setType(20);//充值获取的积分 类型为20
								ph.setUserId(user.getId());
								pointHistoryDao.save(ph);
							}
							
							log.info(String.format("pay success! orderno : %s", out_trade_no+" transaction_id = "+transaction_id+"\n"));
						}				
					}
					
					//给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
					resHandler.sendToCFT("success");
					
				}else{
					log.info("payment notified failed! orderno:"+out_trade_no+" transaction_id = "+transaction_id);
					resHandler.sendToCFT("fail");
				}
			}
		}else{
			//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
			log.info("查询验证签名失败或id验证失败"+",retcode:" + queryRes.getParameter("retcode") + " orderno:"+out_trade_no+" transaction_id = "+transaction_id);
		}
	} else {
		log.info("后台调用通信失败  " + "httpResponseCode = "+httpClient.getResponseCode()+ " httpError = "+httpClient.getErrInfo()+ " orderno:"+out_trade_no+" transaction_id = "+tenpay_no);
		System.out.println("后台调用通信失败");
		System.out.println(httpClient.getResponseCode());
		System.out.println(httpClient.getErrInfo());
		//有可能因为网络原因，请求已经处理，但未收到应答。
	}
}else{
	log.info("通知签名验证失败");
	System.out.println("通知签名验证失败");
}
}catch (Exception e){
	log.error("payment notified failed! orderno:"+out_trade_no+" transaction_id = "+tenpay_no, e);
	out.print("fail");
}
%>

