package models;

import com.arj.ziguiw.common.Boolean;
import com.arj.ziguiw.common.SchoolNewsType;
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
 * Date: 13-1-7
 * Time: 下午5:00
 */
@Entity
@Form(displayName = "新闻动态")
@Table(name="s_school_news")
@SequenceGenerator(name="S_SCHOOL_NEWS_SEQ", sequenceName="S_SCHOOL_NEWS_SEQ", allocationSize=1, initialValue = 100000)
public class SchoolNew extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHOOL_NEWS_SEQ")
    public Long id;

    @Column(name="title", length = 1000, nullable = false)
    @Field(displayName = "标题")
    @MaxSize(value = 100)
    @Required
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name = "VISIT_COUNT")
    @Field(displayName = "查看次数")
    public Integer visitCount = 0;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public Integer type = SchoolNewsType.NEWS;

    @Column(name="reprinted", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "是否转载")
    public Integer reprinted = Boolean.NO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "用户")
    public UserBase user;


    @Column(name = "url")
    @Field(displayName = "新闻封面图")
    public String url;

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;

    public static Page findPage(Integer page, Integer pageSize, Long schoolId, int type) {
        String hql = "select n from SchoolNew n where n.school.id = ? and n.status = ? and n.type = ? order by n.createTime desc";
        List<SchoolNew> schoolNewses = SchoolNew.find(hql, schoolId, Status.OK, type).fetch(page, pageSize);
        hql = "select count(n) from SchoolNew n where n.school.id = ? and n.status = ? and n.type = ? order by n.createTime desc";
        long totalCount =  SchoolNew.count(hql, schoolId, Status.OK, type);
        return new Page(page, pageSize, totalCount, schoolNewses);
    }

    public static List<SchoolNew> findByUrl(Long schoolId,int count){
          return find("from SchoolNew where school.id = ? and status = ? and url is not null order by createTime desc",
                  schoolId,Status.OK).fetch(count);
    }

    public static List<SchoolNew> findLastNewessByType(Long schoolId, int type, int count) {
        return SchoolNew.find("select n from SchoolNew n " +
                "where n.school.id = ? and n.status = ? and n.type = ? order by n.createTime desc",
                schoolId, Status.OK, type).fetch(count);
    }

    public static List<SchoolNew> findLastNewessByType(int type, int count) {
        return SchoolNew.find("select n from SchoolNew n " +
                "where n.type = ? and n.status = ? order by n.createTime desc", type, Status.OK).fetch(count);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", title.length() > 10 ? title.substring(0, 10) + ".." : title, id);
    }
}
