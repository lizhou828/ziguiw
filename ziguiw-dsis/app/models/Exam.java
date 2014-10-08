package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-28
 * Time: A.M.11:37
 */
@Entity()
@Table(name = "T_examination")
@Form(displayName = "考试")
@SequenceGenerator(name = "T_EXAMINATION_SQE",sequenceName = "T_EXAMINATION_SQE",allocationSize = 1)
public class Exam extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_EXAMINATION_SQE")
    @Column(name = "exam_id")
    public int examId;

    @Column(name = "term_id")
    @Field(displayName = "学期ID")
    public String termId;

    @Column(name = "exam_name")
    @Field(displayName = "考试名称")
    public String examName;

    @Column(name = "idstr")
    @Field(displayName = "班级ids")
    public String bjIdStr;

    @Column(name = "xxid")
    @Field(displayName = "学校")
    public String xxId;

}
