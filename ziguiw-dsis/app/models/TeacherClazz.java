package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 下午4:07
 */

@Entity()
@Table(name = "R_teacher_class")
@Form(displayName = "老师班级关系表")
@SequenceGenerator(name = "R_TEACHER_CLASS_SQE",sequenceName = "R_TEACHER_CLASS_SQE",allocationSize = 1)
public class TeacherClazz extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "R_TEACHER_CLASS_SQE")
    @Column(name = "rtc_id")
    public Integer rtc_id;

    @Field(displayName = "班级")
    @Column(name = "bj_id")
    public Integer bjId;

    @Column(name = "teacherid")
    @Field(displayName = "老师")
    public Integer teacherId;

}
