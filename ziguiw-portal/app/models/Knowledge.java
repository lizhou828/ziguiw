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
public class Knowledge extends JPASupport {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="KNOWLEDGES_SEQ")
    public Long id;

    @Column(name="title")
    @Required
    @MaxSize(value = 50)
    public String title;

    @Lob
    @Basic(fetch= FetchType.LAZY,optional=true)
    @Column(name="content")
    @Required
    public String content;

    @Column(name="subtitle")
    @MaxSize(value = 50)
    public String subtitle;

    @Column(name = "keywords")
    public String keywords;

    @Column(name = "editors")
    @Required
    public String editors;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "description")
    public String description;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="channle_id")
    @Required
    public KnowledgeChannel knowledgeChannel;

    @Column(name = "province")
    public String province;

    @Column(name = "city")
    public String city;

    @Column(name = "click_count")
    public int clicks;

    @Column(name = "state")
    public int state = Status.UNVERIFIED;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "create_time")
    public Date createTime = new Date();



    public static Knowledge findById(Long id){
        return find("byId",id).first();
    }

    public static Page findByKnowledgeChannelId(Long id,Integer page,Integer pageSize){
       String hql = "select t from Knowledge t where t.knowledgeChannel.id = ? and t.state = ? order by t.createTime desc";
       List<Knowledge> list = find(hql,id,Status.OK).fetch(page,pageSize);
       hql = "select count(t) from Knowledge t where t.knowledgeChannel.id = ? and t.state = ?";
       Long totalCount = count(hql,id,Status.OK);
       return  new Page(page,pageSize,totalCount,list);
    }

    public static List<Knowledge> findHotByKnowledgeChannelId(Long channelId){
        String hql = "select t from Knowledge t where t.knowledgeChannel.id = ? order by t.clicks desc";
        return find(hql,channelId).fetch();
    }
}
