package models;

import org.hibernate.annotations.Formula;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-4
 * Time: 下午2:35
 */
@Entity
@Table(name="s_admin")
@Form(displayName = "站内站管理用户")
public class SchoolAdmin extends JPASupport{

    @Column(name="account", length = 50, nullable = false)
    @Field(displayName = "账户名")
    @MaxSize(value = 50)
    @Required
    @Id
    public String account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @Column(name = "class_id")
    @Field(displayName = "班级")
    public Long classId;

    @Formula(value = "(select sc.bj_mcheng from t_classes sc where sc.bj_id = class_id)")
    @Field(displayName = "班级")
    public String className;

    @Column(name = "real_name",length = 50)
    @Field(displayName = "真实姓名")
    public String realName;

    public static SchoolAdmin findByAccount(String account) {
        return find("from SchoolAdmin where account = ?",account).first();
    }
}

