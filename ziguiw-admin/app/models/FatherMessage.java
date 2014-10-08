package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-9
 * Time: 下午10:05
 */
@Entity
@Table(name = "father_message")
@SequenceGenerator(name = "FATHER_MESSAGE_SEQ",
        sequenceName = "father_message_seq",
        allocationSize = 1)
@Form(displayName = "父亲留言")
public class FatherMessage extends JPASupport{

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FATHER_MESSAGE_SEQ")
        public long id;

        @Column(name = "user_name")
        public String userName;

        @Column(name = "mobile")
        public String mobile;

        @Column(name = "content",length = 140)
        public String content;

        @Column(name = "appellation")
        public String appellation;

        @Column(name = "signature")
        public String signature;

        @Column(name = "contact")
        public String contact;

        @Column(name = "email")
        public String email;

        @Column(name = "status")
        public Integer status = Status.UNVERIFIED;

        @Column(name = "create_time")
        public Date createTime = new Date();

}
