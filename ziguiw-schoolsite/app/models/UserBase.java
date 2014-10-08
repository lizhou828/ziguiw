package models;

import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;

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
    public long id;

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

    @Column(name = "display_name")
    @Field(displayName = "用户名称")
    @Required
    public String displayName;

    @Column(name = "PORTRAIT")
    @Field(displayName = "头像")
    public String photo;

    @Column(name = "foreign_key")
    public Long foreignKey;

    @Column(name = "type", columnDefinition = "number(2)")
    public int type;

}
