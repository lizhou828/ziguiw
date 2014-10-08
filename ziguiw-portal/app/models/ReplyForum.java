package models;

import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-4
 * Time: 上午10:05
 */
@Entity
@Table(name = "forum")
@SequenceGenerator(name = "FORUM_SEQ",
        sequenceName = "forum_seq",
        allocationSize = 1)
public class ReplyForum extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORUM_SEQ")
    public long id;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="parentid")
    public Forum parentForum;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="boardid")
    public Board board;

    @Column(name = "content")
    public String content;

    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "title")
    @Required
    public String title;


    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "state")
    public int state = Status.OK;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "elite")
    public int elite = 0;

    @Column(name = "istop")
    public int istop = 0;

    @Column(name = "auto_description")
    public String autoDescription;

    @Formula("(select u.portrait from user_base u where u.id = creator_id)")
    public String creatorPortrait;


    public static Page<ReplyForum> findByForumId(Long forumId,Integer page,Integer pageSize){
        String hql = "select r from ReplyForum r where r.parentForum.id = ? and r.state = 2";
        List<ReplyForum> list = find(hql,forumId).fetch(page,pageSize);
        hql = "select count(*) from ReplyForum r where r.parentForum.id = ? and r.state = 2 ";
        Long totalCount = count(hql,forumId);
        return new Page<ReplyForum>(page,pageSize,totalCount,list);
    }
}
