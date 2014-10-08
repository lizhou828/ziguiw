package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-27
 * Time: 上午11:52
 */
@Entity
@Table(name="validate_code")
public class ValidateCode extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name="code")
    public int validateCode;

    @Column(name="user_id")
    public long userId;

    @Column(name="invalid_time")
    public Date invalidTime = new Date(System.currentTimeMillis() + 1000 * 60 * 60);

    public static List<ValidateCode> findByTimeDesc(Long userId){
            return find("from ValidateCode where userId = ? order by invalidTime desc",userId).fetch();
    }

}
