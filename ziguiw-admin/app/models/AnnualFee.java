package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-26
 * Time: A.M.11:44
 * 年费实体类
 */
@Entity
@Table(name = "t_order")
@Form(displayName = "学生年费")
@SequenceGenerator(name = "T_ORDER_SQE",sequenceName = "T_ORDER_SQE",allocationSize = 1)
public class AnnualFee extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_ORDER_SQE")
    @Column(name = "id")
    public long id;

    @Column(name = "orderno")
    @Field(displayName = "订单号")
    public String orderNo;

    @Column(name = "paymethod")
    @Field(displayName = "支付方式")  //支付方式 1.代表支付宝支付   2.代表财付通  3代表快钱  4代表学校代收
    public int payMethod;

    @Column(name = "price")
    @Field(displayName = "金额")  	//充值金额 单位：元
    public double price;

    @Column(name = "state")
    @Field(displayName = "状态")   //支付状态  1.代表已支付  0.代表未支付
    public int state=1;

    @Column(name = "type")
    @Field(displayName = "充值类型") 	//充值类型，例如充值积分，还是充值其他的服务    1.代表积分 ,2代表年费
    public int type=2;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="userid", insertable=true, updatable=true)
    @Field(displayName = "支付者")
    public UserBase userBase;

    @Column(name = "createdate")
    @Field(displayName = "创建时间")    //订单创建时间
    public Date createDate;

    @Column(name = "paydate")
    @Field(displayName = "支付时间")
    public Date payDate;


    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="xs_id", insertable=true, updatable=true)
    @Field(displayName = "学生姓名")
    public Student student;

    public AnnualFee(long id, String orderNo, int payMethod, double price, int type, UserBase userBase, Date payDate, Student student) {
        this.id = id;
        this.orderNo = orderNo;
        this.payMethod = payMethod;
        this.price = price;
        this.type = type;
        this.userBase = userBase;
        this.payDate = payDate;
        this.student = student;
    }

    public AnnualFee(String orderNo, int payMethod, double price, int state, int type, Date payDate, Student student) {
        this.orderNo = orderNo;
        this.payMethod = payMethod;
        this.price = price;
        this.state = state;
        this.type = type;
        this.payDate = payDate;
        this.student = student;
    }

    public AnnualFee() {
    }

    public static List<AnnualFee> findAllByXsId(Long xsId) {
        String sql = String.format("from AnnualFee a where a.student.xsId =%s ",xsId);
        return find(sql).fetch();
    }
}
