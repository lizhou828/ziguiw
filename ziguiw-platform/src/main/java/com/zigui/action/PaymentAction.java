package com.zigui.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.Order;
import com.zigui.domain.UserBase;
import com.zigui.service.impl.OrderServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.AlipayNotify;
import com.zigui.utils.UtilDate;

/*
 * 处理所有支付请求
 * 
 * **/

@Controller("paymentAction")
@Scope("prototype")
public class PaymentAction extends BaseAction{

	private String out_trade_no;
	private String alibody;
	private String total_fee;
	private String type;
	private String amount;
	private String paymethod;
	
	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private UserServiceImpl userService;
	
	

	
	
	
	
	//处理支付宝同步通知请求
	public String return_url() throws Exception{
		
		HttpServletRequest request  = ServletActionContext.getRequest();
		
		//获取支付宝GET过来反馈信息
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

		String trade_no = request.getParameter("trade_no");				//支付宝交易号
		String order_no = request.getParameter("out_trade_no");	        //获取订单号
		String total_fee = request.getParameter("total_fee");	        //获取总金额
		//String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"utf-8");//商品名称、订单名称
		String subject = request.getParameter("subject");
		String body = "";
		if(request.getParameter("body") != null){
			body = request.getParameter("body");
			//body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "utf-8");//商品描述、订单备注、描述
		}
		String buyer_email = request.getParameter("buyer_email");		//买家支付宝账号
		String trade_status = request.getParameter("trade_status");		//交易状态
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				Order order = orderService.getByOrderNo(order_no);
				if(order.getState()==0){
					order.setState(1);
					order.setPayDate(new Date());
					UserBase user = order.getUser();
					user.setPoints(user.getPoints()+2);
					userService.saveOrUpdate(user);
					orderService.saveOrUpdata(order);
				}
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
			}
		
			return "success";
	}else{
			return "fail";
	}
	
  }
	
	public String pay() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		if(paymethod.equals("1")){//支付宝支付
			
			String subject = "";
			
			UserBase user = this.user;
			out_trade_no = UtilDate.getOrderNum();
			
			Order order = new Order();
			if(type.equals("points")){
				
				subject = "充值子贵网积分:" + new Integer(amount)*10+"个";
				order.setType(1);//代表充值的是积分
				
			}else if(type.equals("webuse")){
				subject = "充值子贵网增值服务费 :" + new Integer(amount)+"元";
				order.setType(2);//代表充值网站使用费
			}
			order.setOrderNo(out_trade_no);
			order.setPayMethod(1);//支付宝支付
			order.setPrice(new Double(amount));//金额
			order.setState(0);//未支付
			order.setUser(user);
			order.setCreateDate(new Date());
			
			orderService.saveOrUpdata(order);
			
			
			request.setAttribute("out_trade_no", out_trade_no);
			
			request.setAttribute("subject", subject);
			request.setAttribute("total_fee", order.getPrice());
			request.setAttribute("body", alibody);
			return "alipay";
		}else if (paymethod.equals("2")){//财付通支付
			String product_name =  "";
			
			UserBase user = this.user;
			out_trade_no = UtilDate.getOrderNum();
			
			Order order = new Order();
			if(type.equals("points")){
				
				product_name = "充值子贵网积分:" + new Integer(amount)*10+"个";
				order.setType(1);//代表充值的是积分
				
			}else if(type.equals("webuse")){
				product_name = "充值子贵网增值服务费 :" + new Integer(amount)+"元";
				order.setType(2);//代表充值网站使用费
			}
			order.setOrderNo(out_trade_no);
			order.setPayMethod(2);//财付通支付
			order.setPrice(new Double(amount));//金额
			order.setState(0);//未支付
			order.setUser(user);
			order.setCreateDate(new Date());
			
			orderService.saveOrUpdata(order);
			
			request.setAttribute("order_price", order.getPrice());
			request.setAttribute("product_name", product_name);
			request.setAttribute("order_no", out_trade_no);
			
			return "tenpay";
		}else{
			//
		}
		return null;
	}
	

	public String getOut_trade_no() {
		return out_trade_no;
	}


	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}






	public String getAlibody() {
		return alibody;
	}

	public void setAlibody(String alibody) {
		this.alibody = alibody;
	}

	public OrderServiceImpl getOrderService() {
		return orderService;
	}


	public void setOrderService(OrderServiceImpl orderService) {
		this.orderService = orderService;
	}
	
	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getAmount() {
		return amount;
	}




	public void setAmount(String amount) {
		this.amount = amount;
	}




	public String getPaymethod() {
		return paymethod;
	}




	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	
	
	
	
}
