package models;

import controllers.CRUD;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-1-31
 * Time: 下午4:39
 */
@Entity
@Table(name="administrator")
@SequenceGenerator(
        name="ADMINISTRATOR_SEQ",
        sequenceName="administrator_seq",
        allocationSize=1
)
@Form(displayName = "管理员")
public class Administrator extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="ADMINISTRATOR_SEQ")
    @Field(displayName = "ID")
    @CRUD.Exclude
    public long id;

    @Column(name="nick_name")
    @Field(displayName = "用户名")
    public String nickName;

    @Column(name="real_name")
    @Field(displayName = "真实名字")
    public String realName;

    @Column(name="password")
    @Field(displayName = "密码")
    public String password;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    public static Administrator findByNickName(String nickName){
        return find("byNickName",nickName).first();
    }
}
