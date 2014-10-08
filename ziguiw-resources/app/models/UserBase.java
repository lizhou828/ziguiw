package models;

import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserBaseType;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 12-12-29
 * Time: P.M 4:58
 */

@Entity
@Table(name = "user_base")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "user_seq", allocationSize = 1)
public class UserBase extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    public long id;

    @Column(name = "nick_name")
    @Required
    public String nickName;

    @Column(name = "email")
    @Required
    @Email
    public String email;

    @Column(name = "passwd")
    @Password
    @Required
    public String password;

    @Column(name = "display_name")
    @Required
    public String displayName;

    @Column(name = "PORTRAIT")
    public String photo;

    @Column(name = "foreign_key")
    public Long foreignKey;

    @Column(name = "type", columnDefinition = "number(2)")
    public int type;

    @Column(name = "province")
    public String province;

    @Column(name = "sex")
    public Integer sex;

    @Column(name = "points")
    public Integer points = 0;

    @Column(name = "signature")
    public String signature;

    @Column(name = "job_title")
    public String jobTitle;//职称

    @Column(name = "school")
    public String school; //所属学校

    @Column(name = "introduction")
    public String introduction;//简介

    @Column(name = "real_name")
    public String realName;

    public static List<UserBase> findDescPointUser() {

        String hql = "select u from UserBase u where u.type = ? order by u.points desc";
        return find(hql, UserBaseType.TEACHER).fetch(1,5);
    }

    public static List<Resource> findResourceByUserAndType(Long userId,Long typeId){
        String hql = "select r from Resource r where r.user.id = ? and r.type.id =?" +
                " and r.state = ? order by r.createTime desc";
        List<Resource> list = find(hql,userId,typeId, Status.OK).fetch(1,5);
        return list;
    }
}
