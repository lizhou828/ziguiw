package models;

import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午3:22
 */
@Entity
@Table(name = "question")
@SequenceGenerator(name = "QUESTION_SEQ", sequenceName = "question_seq", allocationSize = 1)
public class Question extends JPASupport{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="QUESTION_SEQ")
    public Long id;

    @Column(name="title")
    @Required
    @MaxSize(value = 50)
    public String title;

    @Lob
    @Basic(fetch= FetchType.LAZY,optional=true)
    @Column(name="content")
    public String content;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "create_time")
    public Date createTime;

    @Column(name = "create_id")
    public Long createId;

    @Column(name = "point")
    public Integer point;

    @Column(name = "status")
    public int status;

    @Column(name = "click_count")
    public int clickCount;

    @Column(name = "pending_time")
    public int pendingTime;

    @Column(name = "state")
    public int state = Status.UNVERIFIED;

    @Column(name = "best_answer_id")
    public long bestAnswerId;

    @Column(name = "best_answer_author")
    public long bestAnswerAuthor;

    @Formula("(select count(*) from answer a where a.question_id = id)")
    public int answerCount;


    public static Page<Question> findAll(Integer page,Integer pageSize){
        String hql = "from Question q where q.state = ? order by q.createTime desc";
        List<Question> list = find(hql,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Question q where q.state = ?";
        Long totalCount = count(hql,Status.OK);
        return  new Page<Question>(page,pageSize,totalCount,list);
    }

    public static Page<Question> findByStatus(int status,Integer page,Integer pageSize){
        String hql = "select q from Question q where q.status = ? and q.state = ? order by q.createTime desc";
        List<Question> list = find(hql,status,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Question q where q.status = ? and q.state = ?";
        Long totalCount = count(hql,status,Status.OK);
        return  new Page<Question>(page,pageSize,totalCount,list);
    }

    public static List<Question> findHot(){
        String hql = "select q from Question q where q.state = ? order by q.clickCount desc";
        List<Question> list = find(hql,Status.OK).fetch(1,10);
        return  list;
    }

    public static Question findById(Long id){
        return find("byId",id).first();
    }

    public static Page<Question> findByCreateNickName(String userName, Integer page, Integer pageSize) {
        String hql = "select q from Question q where q.creatorNick = ? and q.state = ? order by q.createTime desc";
        List<Question> list = find(hql,userName,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Question q where q.creatorNick = ? and q.state = ?";
        Long totalCount = count(hql,userName,Status.OK);
        return  new Page<Question>(page,pageSize,totalCount,list);
    }
}
