package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 上午10:05
 */

@Entity()
@Table(name = "t_schoolinfo")
@Form(displayName = "学校")
@SequenceGenerator(name = "T_SCHOOLINFO_SQE" ,sequenceName = "T_SCHOOLINFO_SQE",allocationSize = 1)
public class School extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_SCHOOLINFO_SQE")
    @Column(name = "xx_id")
    public Long xx_Id;

    @Column(name = "xxId")
    @Field(displayName = "学校ID")
    @Required
    public String xxId;

    @Column(name = "sch_name")
    @Field(displayName = "学校名称")
    @Required
    public String schName;

    @Column(name = "linkman")
    @Field(displayName = "学校联系人")
    @Required
    public String linkMan;

    @Column(name = "contactphone")
    @Field(displayName = "学校联系人电话")
    @Required
    public String contactPhone;


    @Column(name = "proid")
    @Field(displayName = "所在省份")
    @Required
    public String proId;


    @Column(name = "cityid")
    @Field(displayName = "所在城市")
    @Required
    public String cityId;


    @Override
    public String toString() {
        return String.format("%s[%s,%s,%s,%s]",schName, xxId,linkMan,contactPhone,cityId );
    }
}
