package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;
import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-26
 * Time: P.M.2:14
 */
@Entity
@Table(name = "t_student")
@Form(displayName = "学生")
@SequenceGenerator(name = "T_STUDENT_SQE",sequenceName = "T_STUDENT_SQE",allocationSize = 1)
public class Student extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_STUDENT_SQE")
    @Column(name= "xs_id")
    public Long xsId;

    @Column(name = "xs_xming")
    @Field(displayName = "学生姓名")
    @Required
    public String xsXming;

    @Column(name = "idcard")
    @Field(displayName = "一卡通卡号")
    @Required
    public String idCard;

    @Column(name = "sex")
    @Field(displayName = "性别")
    @Required
    public String sex ;

    @Field(displayName = "班级")
    @Column(name = "bj_id")
    public Integer bjId;

    @Column(name = "accommodation")
    @Field(displayName = "住校情况")
    @Required
    public Integer accommodation ;

    @Column(name = "personalphoto")
    @Field(displayName = "照片")
    @Required
    public String personalPhoto;

    @Column(name = "homephone")
    @Field(displayName = "家里电话")
    @Required
    public String homePhone;

    @Column(name = "xxid")
    @Field(displayName = "学校ID")
    @Required
    public String xxId;

    @Override
    public String toString() {
        return xsXming;
    }
}
