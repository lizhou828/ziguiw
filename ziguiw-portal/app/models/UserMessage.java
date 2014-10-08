package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-13
 * Time: 上午10:18
 */
@Entity
@Table(name = "user_message")
@SequenceGenerator(name = "USER_MESSAGE_SEQ",
        sequenceName = "user_message_seq",
        allocationSize = 1)
public class UserMessage extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MESSAGE_SEQ")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "text")
    public String text;

    @Column(name = "status")
    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="from_user_id", insertable=true, updatable=true)
    public UserBase formUser;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="to_user_id", insertable=true, updatable=true)
    public UserBase toUser;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "parent_msg_id")
    public UserMessage parentMsg;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static Page<UserMessage> findParentUserMessageByUserId(Long userId, Integer page, Integer pageSize) {
        String hql = "select u from UserMessage u where u.status = ? and  " +
                     "u.parentMsg is null and u.toUser.id = ? order by u.createTime desc";
        List<UserMessage> list = find(hql,Status.OK,userId).fetch(page,pageSize);
        hql = "select count(*) from UserMessage u where u.status = ? and " +
                "u.parentMsg is null and u.toUser.id = ?";
        Long totalCount = count(hql,Status.OK,userId);
        return new Page<UserMessage>(page,pageSize,totalCount,list);
    }

    public static List<UserMessage> findChildMessageByParentId(long parentId) {
        String hql = "select u from UserMessage u where u.status = ? " +
                     "and u.parentMsg.id = ? order by createTime";
        List<UserMessage> list = find(hql,Status.OK,parentId).fetch();
        return list;
    }
}
