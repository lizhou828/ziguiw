package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-19
 * Time: 下午3:35
 */
@Entity
@Table(name = "answer")
@SequenceGenerator(name = "ANSWER_SEQ", sequenceName = "answer_seq", allocationSize = 1)
public class Answer extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANSWER_SEQ")
    public long id;

    @Column(name = "creator_id")
    public long creatorId;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "content")
    public String content;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="question_id")
    public Question question;


    public static List<Answer> findByQuestionId(Long id){
        return find("select a from Answer a where a.question.id = ?",id).fetch();
    }


}
