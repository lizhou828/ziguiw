package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-16
 * Time: 上午9:51
 */
@Entity
@Table(name = "father_message_replay")
@SequenceGenerator(name = "FATHER_MESSAGE_REPLAY_SEQ",
        sequenceName = "father_message_replay_seq",
        allocationSize = 1)
public class FatherMessageReplay extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FATHER_MESSAGE_REPLAY_SEQ")
    public long id;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "content")
    public String content;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<FatherMessageReplay> findByMobile(String mobile){
        return find("from FatherMessageReplay where mobile = ? order by createTime desc",mobile).fetch();
    }

}
