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
public class School extends JPASupport{

    @Id
    @Column(name="XX_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="XXID")
    public String xxId;

    @Column(name="sch_name")
    public String name;

    @Column(name="linkman")
    public String linkman;

    @Column(name="contactphone")
    public String contactphone;

    public String photo;

    public School(Long id, String xxId, String name, String photo) {
        this.id = id;
        this.xxId = xxId;
        this.name = name;
        this.photo = photo;
    }


    @Override
    public String toString() {
        return String.format("%s[%s]", name, id);
    }


}
