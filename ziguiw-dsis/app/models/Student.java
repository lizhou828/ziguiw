package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-19
 * Time: P.M 5:59
 */
@Entity()
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

    @Column(name = "birthplace")
    @Field(displayName = "出生地")
    public String birthplace;

    @Column(name = "politicalface")
    @Field(displayName = "政治面貌")
    public String politicalFace;

    @Column(name = "idcard")
    @Field(displayName = "一卡通卡号")
    @Required
    public String idCard;

    @Column(name = "accountPlace")
    @Field(displayName = "出生地")
    public String accountPlace;

    @Column(name = "sex")
    @Field(displayName = "性别")
    @Required
    public String sex ;

    @Column(name = "birthday")
    @Field(displayName = "生日")
    public String birthday ;

    @Field(displayName = "班级")
    @Column(name = "bj_id")
    public Integer bjId;

    @Column(name = "accommodation")
    @Field(displayName = "住校情况")
    @Required
    public Integer accommodation ;

    @Column(name = "mzhu")
    @Field(displayName = "名族")
    public String mzhu;

    @Column(name = "hobby")
    @Field(displayName = "爱好")
    public String hobby;

    @Column(name = "personalphoto")
    @Field(displayName = "照片")
    @Required
    public String personalPhoto;

    @Column(name = "homephone")
    @Field(displayName = "家里电话")
    @Required
    public String homePhone;

    @Column(name = "zip")
    @Field(displayName = "邮编")
    public String zip;

    @Column(name = "homeaddress")
    @Field(displayName = "家庭住址")
    public String homeAddress;

    @Column(name = "otherlinks")
    @Field(displayName = "其他联系人")
    public String otherLinks;

    @Column(name = "healthstate")
    @Field(displayName = "健康状况")
    public String healthState;

    @Column(name = "xxid")
    @Field(displayName = "学校ID")
    @Required
    public String xxId;

    @Column(name = "banganbuid")
    @Field(displayName = "班干部ID")
    public String banGanBuId;

    @Column(name = "ybhao")
    @Field(displayName = "医保号")
    @Required
    public String ybHao;

    @Column(name = "datetoschool")
    @Field(displayName = "入学日期")
    public Date dateToSchool;

    @Column(name = "status")
    @Field(displayName = "学籍状态")
    @Required
    public String status;


    @Override
    public String toString() {
        return String.format("%s[%s,%s,%s,%s]", xsXming, xsId,xxId,bjId,idCard);
    }
}
