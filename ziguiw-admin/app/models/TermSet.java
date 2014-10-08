package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-27
 * Time: P.M.3:55
 * 学校学期
 */
@Entity
@Table(name = "T_TERM_SET")
@Form(displayName = "学期")
@SequenceGenerator(name="T_TERM_SET_SEQ", sequenceName="T_TERM_SET_SEQ",allocationSize = 1)
public class TermSet extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_TERM_SET_SEQ")
    @Column(name = "term_id")
    public Integer termId;

    @Column(name="xxid")
    @Field(displayName = "学校ID")
    public String xxid;

    @Column(name="term_name")
    @Field(displayName = "学期名称")
    public String termName;

    @Column(name="term_year")
    @Field(displayName = "学年")
    public String termYear;

    @Column(name="holiday_begin")
    @Field(displayName = "学期开始日期")
    public Date holidayBegin;

    @Column(name="holiday_end")
    @Field(displayName = "学期结束日期")
    public Date holidayEnd;

    public static List<TermSet> findBySchoolId(String xxId) {
        return  TermSet.find("byXxId",xxId).fetch();
    }
}
