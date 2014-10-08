package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 下午4:06
 */

@Entity()
@Table(name = "T_classes")
@Form(displayName = "班级")
@SequenceGenerator(name="T_CLASSES_SQE",sequenceName = "T_CLASSES_SQE",allocationSize = 1)
public class Clazz extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_CLASSES_SQE")
    @Column(name = "bj_id")
    public Integer bjId;

    @Column(name = "Bj_mcheng")
    @Field(displayName = "班级名称")
    public String bjMcheng;

    @Column(name = "Nj_id")
    @Field(displayName = "年级")
    public Integer njId;

    @Column(name = "XxID")
    @Field(displayName = "学校编码")
    public String xxId;


}
