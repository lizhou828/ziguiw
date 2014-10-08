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
 * Time: 上午11:40
 */
@Entity
@Table(name = "home_answer")
@SequenceGenerator(name = "HOME_ANSWER_SEQ",
        sequenceName = "home_answer_seq",
        allocationSize = 1)
public class HomeAnswer extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOME_ANSWER_SEQ")
    public long id;

    @Column(name = "text")
    public String text;

    @Column(name = "status")
    public int status = Status.OK;

    @Column(name = "question_id")
    public Long questionId;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<HomeAnswer> findByQuestionId(long questionId){
        return find("from HomeAnswer a where a.questionId = ? and a.status = ? order by a.createTime desc",questionId,Status.OK).fetch();
    }

}
