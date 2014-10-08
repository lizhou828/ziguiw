package models;

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
 * Date: 13-1-18
 * Time: 上午11:17
 */
@Entity
@Form(displayName = "班级话题")
@Table(name="S_CLASS_FORUM")
@SequenceGenerator(name="S_CLASS_FORUM_SEQ", sequenceName="S_CLASS_FORUM_SEQ", allocationSize=1, initialValue = 100000)
public class ClassForum extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CLASS_FORUM_SEQ")
    public Long id;

    @Column(name="title", length = 500, nullable = false)
    @Field(displayName = "标题")
    @MaxSize(value = 100)
    @Required
    public String title;

    @Column(name = "xxId", nullable = false)
    @Field(displayName = "学校编码")
    public String xxId;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;


    @Column(name = "class_id")
    @Field(displayName = "班级ID")
    public Long classId;


    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int state = Status.UNVERIFIED;

    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "REPLY_COUNT")
    @Field(displayName = "回复数")
    public int replyCount = 0;

    @Column(name = "VISIT_COUNT")
    @Field(displayName = "查看数")
    public int visitCount = 0;

    @Column(name = "LAST_REPLY_TIME")
    @Field(displayName = "最后回复时间")
    public Date lastReplyTime = new Date();

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;


    public static Page<ClassForum> findPage(long classId,int page,int pageSize){
        List<ClassForum> list = find("select f from ClassForum f where f.classId = ? and state = ? order by f.createTime desc"
                ,classId,Status.OK).fetch(page,pageSize);
        long count = count("select count(*) from ClassForum f where f.classId = ? and state = ? order by f.createTime desc",classId,Status.OK);
        return new Page<ClassForum>(page,pageSize,count,list);
    }

    public static ClassForum findById(long id){
        return find("select f from ClassForum f where f.id = ?",id).first();
    }

    public static List<ClassForum> findByClassId(long classId,int count) {
        return find("from ClassForum where classId = ? and state = ? order by createTime desc",
                classId,Status.OK).fetch(count);
    }

    public static ClassForum findByHot(long classId){
        return find("from ClassForum where classId = ? and state = ? order by replyCount desc",
                classId,Status.OK).first();
    }
}
