package models;

import com.arj.ziguiw.common.Status;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-2-25
 * Time: 上午10:06
 */
@Entity
@Table(name = "forum")
@SequenceGenerator(name = "FORUM_SEQ",
        sequenceName = "forum_seq",
        allocationSize = 1)
@Form(displayName = "回帖")
public class ReplyForum extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORUM_SEQ")
    public long id;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="parentid")
    @Field(displayName = "主贴")
    public Forum parentForum;


    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="boardid")
    public Board board;


    @Column(name = "creator_nick")
    public String creatorNick;

    @Column(name = "title")
    @Field(displayName = "标题")
    @Required
    public String title;


    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "state")
    @Field(displayName = "状态")
    public int state = Status.OK;

    @Column(name = "first_image")
    @Field(displayName = "首图")
    public String firstImage;

    @Column(name = "elite")
    @Field(displayName = "是否加精")
    public int elite = 0;

    @Column(name = "istop")
    @Field(displayName = "是否置顶")
    public int istop = 0;

    @Column(name = "auto_description")
    @Field(displayName = "描述")
    public String autoDescription;

}
