package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-30
 * Time: 下午3:00
 */
@Entity
@Table(name = "t_subject")
@Form(displayName = "学校科目")
@SequenceGenerator(name = "T_SUBJECT_SQE" ,sequenceName = "T_SUBJECT_SQE",allocationSize = 1)
public class SchoolSubject extends JPASupport{
    @Id
    @Column(name = "subject_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_SUBJECT_SQE")
    public  Integer subjectId;

    @Column(name = "subject_name")
    @Field(displayName = "科目名称")
    public String subjectName;

    @Column(name = "subject_code")
    @Field(displayName = "科目码")
    public String subjectCode;

    @Column(name = "xxid")
    @Field(displayName = "学校id")
    public String xxId;

    public static List<SchoolSubject> findByXxId(String xxId){
        return  find("from SchoolSubject s where s.xxId = ?",xxId).fetch();
    }

}
