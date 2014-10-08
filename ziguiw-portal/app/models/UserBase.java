package models;

import com.arj.ziguiw.common.UserStatus;
import play.data.validation.Email;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.persistence.*;
import java.util.List;
import java.util.Properties;

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

    @Column(name = "mobile_ticket")
    public String mobileTicket;

    @Column(name = "click_count")
    public Integer clickCount;

    public Integer state = UserStatus.inactive;

    public static UserBase findByUserName(String userName){
        return find("byNickName",userName).first();
    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin@ziguiw.com", "adminziguiw602");
            }
        });
        return session;
    }

    public static Page<UserBase> SearchDisplayName(String condition,Integer page, Integer pageSize){
        List<UserBase> list = find("from UserBase where displayName like '%" + condition + "%'").fetch(page,pageSize);
        Long count = count("select count(*) from UserBase where displayName like '%" + condition + "%'");
        return new Page<UserBase>(page,pageSize,count,list);
    }

    public static List<UserBase> findListId(List<Long> list){
        String hql = "";
        hql = hql + "id = " + list.get(0);
        for(int i = 0;i < list.size();i++){
            if(i > 0){
                hql = hql + "or id = " + list.get(i);
            }
        }
        List<UserBase> userBases = find("from UserBase where (" + hql + ") order by clickCount desc").fetch();
        return userBases;
    }
}
