package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-12
 * Time: 上午11:37
 */
@Entity
@Table(name = "home_question")
@SequenceGenerator(name = "HOME_QUESTION_SEQ",
        sequenceName = "home_question_seq",
        allocationSize = 1)
public class HomeQuestion extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOME_QUESTION_SEQ")
    public long id;

    @Column(name = "text")
    public String text;

    @Column(name = "status")
    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static Page findByUserId(Long userId , Integer page , Integer pageSize) {
        List<HomeQuestion> list = find("from HomeQuestion q where q.user.id = ? and q.status = ? order by q.createTime desc",userId,Status.OK).fetch(page,pageSize);
        long count = count("select count(q) from HomeQuestion q where q.user.id = ? and q.status = ?",userId,Status.OK);
        return new Page(page,pageSize,count,list);
    }
}
