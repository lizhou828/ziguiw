package models;

import com.arj.ziguiw.common.Status;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-1-31
 * Time: 下午3:50
 */
@Entity
@Table(name="debate")
@SequenceGenerator(
        name="DEBATE_SEQ",
        sequenceName="debate_seq",
        allocationSize=1
)
public class Debate extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="DEBATE_SEQ")
    public long id;

    @Column(name="title")
    @Required
    @MaxSize(value = 50)
    public String title;

    @Column(name="positive_topic")
    @Required
    public String positiveOpinion;

    @Column(name="negative_topic")
    @Required
    public String negativeOpinion;

    @Column(name="positive_support_count")
    public Integer positiveSupportCount = 0;

    @Column(name="negative_support_count")
    public Integer negativeSupportCount = 0;

    @Column(name = "click_count")
    public Integer clickCount;

    @Column(name="description")
    @Required
    public String description;

    @Column(name="open_days")
    public Integer openDays = 1;

    @Column(name = "status")
    public Integer state = Status.UNVERIFIED;

    @Column(name="creator_id")
    public Long creatorId;

    @Column(name="creator_nick")
    public String creatorNick;

    @Column(name="create_time")
    public Date createTime = new Date();


    public  static Debate findById(Long id){
        return find("byId",id).first();
    }

    public static Page<Debate> findAll(Integer page,Integer pageSize){
        String hql = "select d from Debate d where d.state = ? order by d.createTime desc";
        List<Debate> list = find(hql,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Debate d where d.state = ?";
        Long totalCount = Debate.count(hql,Status.OK);
        return  new Page<Debate>(page,pageSize,totalCount,list);
    }

    public static List<Debate> findHot() {
        String hql="select d from Debate d where d.state = ? order by d.clickCount desc";
        List<Debate> list = find(hql,Status.OK).fetch(1,10);
        return  list;
    }
}
