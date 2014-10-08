package models;

import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-27
 * Time: 下午2:41
 */
@Entity
@Table(name = "debate_opinion")
@SequenceGenerator(name = "DEBATE_OPINION_SEQ", 
        sequenceName = "debate_opinion_seq", 
        allocationSize = 1)
public class DebateOpinion extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEBATE_OPINION_SEQ")
    public long id;



    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="topic_id")
    public Debate debate;

    @Column(name = "status")
    public int status;

    @Column(name="opinion")
    public String opinion;

    @Column(name = "state")
    public int state = Status.OK;


    @Column(name="creator_id")
    public long creatorId;

    @Column(name="creator_nick")
    public String creatorNick;

    @Column(name="create_time")
    public Date createTime = new Date();

    @Formula("(select u.portrait from user_base u where u.id = creator_id)")
    public String creatorPortrait;

    public static List<DebateOpinion> findByStatusAndDebateId(int status,Long debateId){
        return find("select d from" +
                " DebateOpinion d where d.status = ? and d.debate.id = ? " +
                "and d.state = ? order by d.createTime desc",status,debateId,Status.OK)
                .fetch();
    }
    
}
