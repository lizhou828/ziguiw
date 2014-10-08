package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 13-1-6
 * Time: A.M 11:29
 */

@Entity
@Table(name="T_SCHOOLINFO")
@Form(displayName = "学校")
public class School extends JPASupport{

    @Id
    @Column(name="XX_ID")
    @Field(displayName = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="XXID")
    @Field(displayName = "编码")
    public String xxId;

    @Column(name="sch_name")
    @Field(displayName = "名称")
    public String name;

    @Column(name="linkman")
    @Field(displayName = "联系人")
    public String linkman;

    @Column(name="contactphone")
    @Field(displayName = "联系电话")
    public String contactphone;

    public String photo;

    public School(Long id, String xxId, String name, String photo) {
        this.id = id;
        this.xxId = xxId;
        this.name = name;
        this.photo = photo;
    }

    public String toString() {
        return String.format("%s", name);
    }
}
