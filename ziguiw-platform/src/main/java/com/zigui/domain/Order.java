package com.zigui.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//订单编号
	@Column(nullable=false,unique=true)
	private String orderNo;
	
	//支付状态  1.代表已支付  0.代表未支付
	private int state;
	//支付方式 1.代表支付宝支付   2.....
	private int payMethod;
	
	//充值金额 单位：元
	@Column(nullable=false)
	private double price;
	
	//充值类型，例如充值积分，还是充值其他的服务    1.代表积分
	private int type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//订单创建时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;
	//充值人
	@ManyToOne()
	@JoinColumn(name="userId")
	private UserBase user;

	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(int payMethod) {
		this.payMethod = payMethod;
	}

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public UserBase getUser() {
		return user;
	}

	public void setUser(UserBase user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
	
}
