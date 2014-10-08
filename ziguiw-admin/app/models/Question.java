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
 * Time: 上午11:14
 */
@Entity
@Table(name="question")
@SequenceGenerator(
        name="QUESTION_SEQ",
        sequenceName="question_seq",
        allocationSize=1
)
@Form(displayName = "问题")
public class Question extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="QUESTION_SEQ")
    @Field(displayName = "ID")
    @CRUD.Exclude
    public Long id;

    @Column(name="title")
    @Field(displayName = "标题")
    @Required
    @MaxSize(value = 50)
    public String title;

    @Lob
    @Basic(fetch= FetchType.LAZY,optional=true)
    @Column(name="content")
    @Field(displayName = "内容")
    public String content;

    @Column(name = "creator_nick")
    @Field(displayName = "创建者昵称")
    public String creatorNick;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime;

    @Column(name = "create_id")
    @Field(displayName = "")
    public Long createId;

    @Column(name = "state")
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;



}
