package models;

import com.arj.ziguiw.common.Status;
import controllers.CRUD;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

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
@Form(displayName = "辩论")
public class Debate extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="DEBATE_SEQ")
    @Field(displayName = "ID")
    @CRUD.Exclude
    public long id;

    @Column(name="title")
    @Field(displayName = "标题")
    @Required
    @MaxSize(value = 50)
    public String title;

    @Column(name="positive_topic")
    @Field(displayName = "正方观点")
    @Required
    public String positiveOpinion;

    @Column(name="negative_topic")
    @Field(displayName = "反方观点")
    @Required
    public String negativeOpinion;

    @Column(name="positive_support_count")
    @Field(displayName = "正方支持数")
    public Integer positiveSupportCount = 0;

    @Column(name="negative_support_count")
    @Field(displayName = "反方支持数")
    public Integer negativeSupportCount = 0;

    @Column(name="description")
    @Field(displayName = "描述")
    @Required
    public String description;

    @Column(name="open_days")
    @Field(displayName = "开放天数")
    public Integer openDays = 1;

    @Column(name = "status")
    @Field(displayName = "状态")
    public Integer state = Status.UNVERIFIED;

    @Column(name="creator_id")
    @Field(displayName = "创建者ID")
    public Long creatorId;

    @Column(name="creator_nick")
    @Field(displayName = "创建者昵称")
    public String creatorNick;

    @Column(name="create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

}
