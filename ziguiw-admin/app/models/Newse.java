package models;

import com.arj.ziguiw.common.Status;
import controllers.CRUD;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-1-24
 * Time: A.M 11:16
 */
@Entity
@Table(name="news")
@SequenceGenerator(
        name="NEWS_SEQ",
        sequenceName="news_seq",
        allocationSize=1
)
@Form(displayName = "新闻")
public class Newse extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="NEWS_SEQ")
    @Field(displayName = "ID")
    @CRUD.Exclude
    public long id;

    @Column(name="title")
    @Field(displayName = "标题")
    @Required
    @MaxSize(value = 50)
    public String title;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="channle_id")
    @Field(displayName = "新闻频道")
    @Required
    public NewsChannel newsChannel;

    @Column(name = "state")
    @Field(displayName = "状态")
    public Integer state = Status.UNVERIFIED;

    @Column(name = "creator_id")
    @Field(displayName = "创建人ID")
    public Long creatorId;

    @Column(name = "creator_nick")
    @Field(displayName = "创建人名称")
    public String creatorNick;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "last_modify_id")
    @Field(displayName = "最近修改人ID")
    public Long lastModifyId;

    @Column(name = "last_modify_nick")
    @Field(displayName = "最近修改人名称")
    public String lastModifyNick;

    @Column(name = "last_modify_time")
    @Field(displayName = "最近修改时间")
    public Date lastModifyTime = new Date();

    @Column(name = "subtitle")
    @Field(displayName = "子标题")
    @MaxSize(20)
    public String subtitle;

    @Column(name = "keywords")
    @Field(displayName = "关键字")
    @MaxSize(20)
    public String keywords;

    @Column(name = "description", columnDefinition = "varchar2(2000)")
    @Field(displayName = "描述")
    @MaxSize(1000)
    public String descr;

    @Lob
    @Basic(fetch= FetchType.LAZY,optional=true)
    @Column(name="content")
    @Field(displayName = "内容")
    public String content;
    
    @Column(name = "editors")
    @Field(displayName = "编辑人")
    public String editors;

    @Column(name = "click_count")
    @Field(displayName = "点击数")
    public int clickCount;

    @Column(name = "first_image")
    public String firstImage;

    @Override
    public String toString() {
        return String.format("%s[%s]", title, id);
    }
}
