package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

        public static List<FatherMessage> findByTime(){
            return find("from FatherMessage order by createTime desc").fetch();
        }

        public static Page<FatherMessage> search(String mobile, Integer page, Integer pageSize){
            List<FatherMessage> list = find("from FatherMessage where contact = ? and status = ? order by createTime desc"
            ,mobile,Status.OK).fetch(page,pageSize);
            Long count = count("select count(*) from FatherMessage where contact = ? and status = ? order by createTime desc",mobile,Status.OK);
            return new Page<FatherMessage>(page,pageSize,count,list);
        }

    public static List<FatherMessage> findByContact(String mobile){
        return find("from FatherMessage where contact = ? and status = ? order by createTime desc",mobile,Status.OK).fetch();
    }

}
