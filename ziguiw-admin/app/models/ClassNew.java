package models;

import com.arj.ziguiw.common.ClassNewType;
import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午10:42
 */
@Entity
@Form(displayName = "班级动态")
@Table(name="S_CLASS_NEWS")
@SequenceGenerator(name="S_CLASS_NEWS_SEQ", sequenceName="S_CLASS_NEWS_SEQ", allocationSize=1)
public class ClassNew extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CLASS_NEWS_SEQ")
    public Long id;

    @Column(name="title", length = 500, nullable = false)
    @Field(displayName = "标题")
    @MaxSize(value = 100)
    @Required
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = ClassNewType.NEWS;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;

    @Column(name = "class_id")
    @Field(displayName = "班级")
    public Long classId;

    @Formula(value = "(select sc.bj_mcheng from t_classes sc where sc.bj_id = class_id)")
    @Field(displayName = "班级")
    public String className;

    @Column(name = "xxId")
    @Field(displayName = "学校")
    public String xxId;

    @Formula(value = "(select s.sch_name from t_schoolinfo s where s.xxId = xxId)")
    @Field(displayName = "学校")
    public String schoolName;

    @Column(name="reprinted", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "是否转载")
    public Integer reprinted = com.arj.ziguiw.common.Boolean.NO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "创建人")
    public UserBase user;


    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;


    @Column(name = "url")
    @Field(displayName = "封面图")
    public String url;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;
}
