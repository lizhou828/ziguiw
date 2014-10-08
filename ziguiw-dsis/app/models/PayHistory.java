package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-22
 * Time: A.M.9:49
 * 用户缴费、充值历史记录
 */

@Entity
@Table(name = "t_order")
@Form(displayName = "用户支付")
@SequenceGenerator(name = "T_ORDER_SQE",sequenceName = "T_ORDER_SQE",allocationSize = 1)
public class PayHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_ORDER_SQE")
    @Column(name = "id")
    public long id;

    @Column(name = "orderno")
    @Field(displayName = "订单号")
    public String orderNo;

    @Column(name = "paymethod")
    @Field(displayName = "支付方式")  //支付方式 1.代表支付宝支付   2.....
    public int payMethod;

    @Column(name = "price")
    @Field(displayName = "金额")  	//充值金额 单位：元
    public double price;

    @Column(name = "state")
    @Field(displayName = "状态")   //支付状态  1.代表已支付  0.代表未支付
    public int state;

    @Column(name = "type")
    @Field(displayName = "充值类型") 	//充值类型，例如充值积分，还是充值其他的服务    1.代表积分 ,2代表年费
    public int type;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="userid", insertable=true, updatable=true)
    @Field(displayName = "用户")
    public UserBase userBase;

    @Column(name = "createdate")
    @Field(displayName = "创建时间")    //订单创建时间
    public Date createDate;

    @Column(name = "paydate")
    @Field(displayName = "支付时间")
    public Date payDate;

    @Column(name = "xs_id")
    @Field(displayName = "学生id")
    public long xsId;





}
