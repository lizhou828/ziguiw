package models;

import com.arj.ziguiw.common.Status;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-2-17
 * Time: 下午3:07
 */
@Entity
@Table(name="news")
@SequenceGenerator(
        name="NEWS_SEQ",
        sequenceName="news_seq",
        allocationSize=1
)
public class Newse extends JPASupport {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="NEWS_SEQ")
    public long id;

    @Column(name="title")
    @Required
    @MaxSize(value = 50)
    public String title;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="channle_id")
    @Required
    public NewsChannel newsChannel;

    @Column(name = "state")
    public Integer state = Status.UNVERIFIED;

    @Column(name = "creator_id")
    public Long creatorId;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "create_time")
    public Date createTime = new Date();


    @Column(name = "subtitle")
    @MaxSize(20)
    public String subtitle;

    @Column(name = "keywords")
    @MaxSize(20)
    public String keywords;

    @Column(name = "description")
    @MaxSize(100)
    public String descr;

    @Lob
    @Basic(fetch= FetchType.LAZY,optional=true)
    @Column(name="content")
    public String content;

    @Column(name = "editors")
    public String editors;

    @Column(name = "click_count")
    public int clickCount;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "last_modify_time")
    public Date lastModifyTime = new Date();


    public static Newse findById(Long id){
        return find("byId",id).first();
    }

    public static Page<Newse> findByNewsChannelId(Long id,Integer page,Integer pageSize){
        String hql = "select n from Newse n where n.newsChannel.id=? and n.state = ? order by n.createTime desc";
        List<Newse> list = find(hql,id,Status.OK).fetch(page,pageSize);
        hql = "select count(n) from Newse n where n.newsChannel.id=? and n.state = ?";
        Long totalCount = count(hql,id,Status.OK);
        return new Page<Newse>(page,pageSize,totalCount,list);
    }

    public static List<Newse> findHot(){
        String hql = "select n from Newse n order by n.clickCount desc";
        return  find(hql).fetch(1,10);
    }
}
