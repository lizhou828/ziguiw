package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-15
 * Time: 下午3:12
 */

@Entity
@Table(name="sign")
@SequenceGenerator(
        name="SIGN_SEQ",
        sequenceName="sign_seq",
        allocationSize=1
)
public class Sign extends JPASupport{

        @Id
        @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="SIGN_SEQ")
        public long id;

        @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
        @JoinColumn(name="user_id", insertable=true, updatable=true)
        public UserBase user;

        @Column(name = "signTime")
        public Date signTime = new Date();


        public static Sign findByUserId(Long userId){
            return find("from Sign where user.id = ?",userId).first();
        }
}
