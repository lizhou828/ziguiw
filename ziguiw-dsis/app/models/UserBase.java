package models;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-12-29
 * Time: P.M 4:58
 */

@Entity
@Table(name = "user_base")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "user_seq", allocationSize = 1)
@Form(displayName = "用户")
public class UserBase extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "id")
    public Long id;

    @Column(name = "nick_name")
    @Field(displayName = "用户账号")
    @Required
    public String nickName;

    @Column(name = "email")
    @Field(displayName = "邮箱")
    @Required
    @Email
    public String email;

    @Column(name = "passwd")
    @Field(displayName = "密码")
    @Password
    @Required
    public String password;

    @Column(name = "real_name")
    @Field(displayName = "用户名称")
    @Required
    public String displayName;

    @Column(name = "portrait")
    @Field(displayName = "头像")
    public String portrait;

    @Column(name = "type")
    @Field(displayName = "角色类型")
    public int type;

    @Column(name = "state")
    @Field(displayName = "用户状态")
    public Integer state;

    @Column(name = "foreign_key")
    public Long foreignKey;

    @Column(name = "last_login_time")
    @Field(displayName = "上次登录")
    public Date lastLoginTime;

    @Column(name = "logincount")
    @Field(displayName = "登录次数")
    public Integer loginCount;

    @Column(name="points")
    @Field(displayName = "积分")
    public Integer points;

    @Override
    public String toString(){
        return String.format("user=[nick_nam=%s,id=%s,passwd=%s,type=%s,foreign_key=%s,points=%s," +
                "loginCount=%s,lastLoginTime=%s]",nickName,id,password,type,foreignKey,points,loginCount,
                lastLoginTime);
    }



}
