package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-2-27
 * Time: P.M.4:08
 */
@Entity
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


    public static List<Student> findStudentByBjId(Integer bjId) {
        return find("select s from Student s where s.bjId =?",bjId).fetch();
    }
}
