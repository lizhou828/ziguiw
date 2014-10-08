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
@Table(name="s_school_news")
@SequenceGenerator(name="S_SCHOOL_NEWS_SEQ", sequenceName="S_SCHOOL_NEWS_SEQ", allocationSize=1, initialValue = 100000)
public class SchoolNew extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHOOL_NEWS_SEQ")
    public Long id;

    @Column(name="title", length = 1000, nullable = false)
    @MaxSize(value = 100)
    @Required
    public String title;

    @Column(name = "cause", length = 1000)
    public String cause;

    @Column(name = "VISIT_COUNT")
    public Integer visitCount = 0;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    public int type = SchoolNewsType.NEWS;

    @Column(name="reprinted", columnDefinition = "number(2)", nullable = false)
    public int reprinted = Boolean.NO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    public School school;

    @Column(name="status", columnDefinition = "number(2)", nullable = false)
    public int status = Status.UNVERIFIED;

    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    public Date createTime = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserBase user;


    @Column(name = "url")
    public String url;

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    public String content;


    public static List<SchoolNew> findByTime(int count){
          return find("from SchoolNew s where s.status = ? order by s.createTime desc",Status.OK).fetch(count);
    }
}
