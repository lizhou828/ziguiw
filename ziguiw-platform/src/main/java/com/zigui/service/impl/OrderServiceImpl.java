package com.zigui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigui.dao.OrderDao;
import com.zigui.domain.Order;

@Service("orderService")
public class OrderServiceImpl {
	
	@Autowired
	private OrderDao orderDao;
	
	public List<Order> findAll(){
		return orderDao.getAll(Order.class);
	}
	
	public Order getByOrderNo(String orderNo){
		
		return  orderDao.findUniqueBy(Order.class, "orderNo", orderNo);
	}
	
	

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void saveOrUpdata(Order order) {
		orderDao.saveOrUpdate(order);
	}
}
