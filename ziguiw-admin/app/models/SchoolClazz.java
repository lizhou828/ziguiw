package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-11
 * Time: P.M 5:10
 */
@Entity
@Table(name="t_classes")
@Form(displayName = "班级")
public class SchoolClazz extends JPASupport{

    @Id
    @Column(name="BJ_ID")
    @Field(displayName = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "BJ_MCHENG")
    public String name;

    @Column(name="NJ_ID")
    @Field(displayName = "年级ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long njId;

    @Column(name="XXID")
    @Field(displayName = "编码")
    public String xxId;

    @Column(name = "bj_ztai")
    public int ztai;

    public static List<SchoolClazz> findByXXId(String xxId, int ztai) {
        return find("select c from SchoolClazz c where c.xxId = ? and c.ztai = ?", xxId, ztai).fetch();
    }

    public static SchoolClazz findById(Integer classId){
        return find("select c from SchoolClazz c where c.id = ?",classId).first();
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", name, id);
    }
}
