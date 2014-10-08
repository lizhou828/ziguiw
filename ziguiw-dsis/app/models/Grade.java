package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 下午4:17
 */

@Entity()
@Table(name = "T_stugrade")
@Form(displayName = "年级")
@SequenceGenerator(name="T_STUGRADE_SEQ", sequenceName="T_STUGRADE_SEQ",allocationSize = 1)
public class Grade extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_STUGRADE_SEQ")
    @Column(name = "nj_id")
    public Integer njId;

    @Column(name = "Nj_mcheng")
    @Field(displayName = "年级名称")
    @Required
    public String njMcheng;

    @Column(name = "xxid")
    @Field(displayName = "学校ID")
    @Required
    public String xxId;



}
