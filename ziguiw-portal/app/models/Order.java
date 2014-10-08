package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Liujy
 * Date: 13-5-3
 * Time: 下午2:34
 */
@Entity
@Table(name="t_order")
public class Order extends JPASupport{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    //订单编号
    @Column(nullable=false,unique=true)
    public String orderNo;

    //支付状态
    public int state;

    //支付方式
    public int payMethod;

    //充值金额 单位：元
    @Column(nullable=false)
    public double price;

    //充值类型，例如充值积分，还是充值其他的服务
    public int type;

    @Temporal(TemporalType.TIMESTAMP)
    public Date createDate;//订单创建时间

    @Temporal(TemporalType.TIMESTAMP)
    public Date payDate;

    //充值人
    @ManyToOne()
    @JoinColumn(name="userId")
    public UserBase user;

}
