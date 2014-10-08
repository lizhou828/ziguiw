package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-2-25
 * Time: P.M 1:58
 */
@Entity
@Table(name="s_admin")
public class SchoolAdmin extends JPASupport{

    @Column(name="account", length = 50, nullable = false)
    @MaxSize(value = 50)
    @Required
    @Id
    public String account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    public School school;

    @Column(name = "class_id", columnDefinition = "number(10)")
    public Long classId;

    public static SchoolAdmin findByAccount(String account) {
        return find("from SchoolAdmin where account = ?",account).first();
    }
}
