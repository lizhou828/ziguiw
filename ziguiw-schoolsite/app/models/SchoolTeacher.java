package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-11
 * Time: P.M 4:38
 */
@Entity
@Form(displayName = "师资力量")
@Table(name="s_school_teacher")
@SequenceGenerator(name="s_school_teacher_seq", sequenceName="s_school_teacher_seq", allocationSize=1)
public class SchoolTeacher extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_teacher_seq")
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @Column(name="name", length = 255, nullable = false)
    @Field(displayName = "姓名")
    public String name;

    @Column(name="photo", length = 1000)
    @Field(displayName = "照片")
    public String photo;

    @Column(name="bj")
    @Field(displayName = "班级")
    public String bj;

    @Column(name="km")
    public String km;

    @Column(name="zw")
    @Field(displayName = "职位")
    public String zw;

    @Column(name="zc")
    public String zc;

    @Column(name="description", length = 1000)
    @Field(displayName = "描述")
    public String description;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    public static List<SchoolTeacher> findLastSchoolTeachers(Long id, int count) {
            return find("select t from SchoolTeacher t where t.school.id = ? and status = ? order by t.id desc",id,Status.OK).fetch(count);
    }
}
