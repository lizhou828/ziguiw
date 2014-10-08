package models;

import com.arj.ziguiw.common.ClassNewType;
import com.arj.ziguiw.common.Status;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午10:42
 */
@Entity
@Form(displayName = "班级新闻")
@Table(name="S_CLASS_NEWS")
@SequenceGenerator(name="S_CLASS_NEWS_SEQ", sequenceName="S_CLASS_NEWS_SEQ", allocationSize=1, initialValue = 100000)
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
    public Integer type = ClassNewType.NEWS;

    @Column(name="reprinted", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "是否转载")
    public Integer reprinted= com.arj.ziguiw.common.Boolean.NO;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;

    @Column(name = "class_id")
    @Field(displayName = "班级ID")
    public Long classId;

    @Column(name = "xxId")
    @Field(displayName = "学校编码")
    public String xxId;

    @Column(name = "VISIT_COUNT")
    @Field(displayName = "查看次数")
    public Integer visitCount = 0;

    @Column(name = "AUTO_DESCRIPTION")
    @Field(displayName = "描述")
    public String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "用户")
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
    @Field(displayName = "新闻封面图")
    public String url;

    public static List<ClassNew> findByType(long classId, int type,int count){
            return find("select c from ClassNew c where c.type = ? and c.classId = ? and c.state = ? order by c.createTime desc"
                    ,type,classId,Status.OK).fetch(count);
    }

    public static Page<ClassNew> findAllNews(long classId,Integer page,Integer pageSize,Integer type){
           List<ClassNew> list = find("select c from ClassNew c where c.type = ? and c.classId = ? and c.state = ? " +
                   "order by c.createTime desc",type,classId,Status.OK).fetch(page,pageSize);
           long totalCount = count("select count(c) from ClassNew c where c.type = ? and c.classId = ? and c.state = ? " +
                   "order by c.createTime desc",type,classId,Status.OK);
          return new Page<ClassNew>(page,pageSize,totalCount,list);
    }

    public static ClassNew findNewById(long id){
           return find("select c from ClassNew c where id = ?",id).first();
    }

    public static List<ClassNew> findByUrl(long classId, int count) {
           return find("from ClassNew where classId = ? and url is not null order by createTime desc",
                   classId).fetch(count);
    }
}
