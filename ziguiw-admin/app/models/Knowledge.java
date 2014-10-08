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
 * User: Liujy
 * Date: 13-1-28
 * Time: 下午2:48
 */
@Entity
@Table(name="knowledge")
@SequenceGenerator(
        name="KNOWLEDGES_SEQ",
        sequenceName="knowledges_seq",
        allocationSize=1
)
@Form(displayName = "知识")
public class Knowledge   extends JPASupport {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="KNOWLEDGES_SEQ")
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
    @Required
    public String content;

    @Column(name="subtitle")
    @Field(displayName = "子标题")
    @MaxSize(value = 50)
    public String subtitle;

    @Column(name = "keywords")
    @Field(displayName = "关键字")
    public String keywords;

    @Column(name = "editors")
    @Field(displayName = "责任编辑")
    @Required
    public String editors;

    @Column(name = "first_image")
    @Field(displayName = "首图")
    public String firstImage;

    @Column(name = "description")
    @Field(displayName = "描述")
    public String description;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="channle_id")
    @Field(displayName = "知识频道")
    @Required
    public KnowledgeChannel knowledgeChannel;

    @Column(name = "province")
    @Field(displayName = "省份")
    public String province;

    @Column(name = "city")
    @Field(displayName = "城市")
    public String city;

    @Column(name = "click_count")
    @Field(displayName = "浏览次数")
    public Integer clicks = 0;

    @Column(name = "state")
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;

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
}
