package models;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.Boolean;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-7
 * Time: 下午5:00
 */
@Entity
@Form(displayName = "新闻动态")
@Table(name="s_school_news")
@SequenceGenerator(name="S_SCHOOL_NEWS_SEQ", sequenceName="S_SCHOOL_NEWS_SEQ", allocationSize=1)
public class SchoolNew extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHOOL_NEWS_SEQ")
    public Long id;

    @Column(name="title", length = 1000, nullable = false)
    @Field(displayName = "标题")
    @MaxSize(value = 100)
    @Required
    public String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "创建人")
    public UserBase user;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = SchoolNewsType.NEWS;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @Column(name="reprinted", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "是否转载")
    public int reprinted= Boolean.NO;

    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;

    @Column(name = "url")
    @Field(displayName = "新闻封面图")
    public String url;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;




}
