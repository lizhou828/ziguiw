package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-23
 * Time: 下午5:05
 */
@Entity
@Table(name = "friend_visit")
@SequenceGenerator(name = "FRIEND_VISIT_SEQ",
        sequenceName = "friend_visit_seq",
        allocationSize = 1)
public class FriendVisit extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FRIEND_VISIT_SEQ")
    public long id;

    //查看者id
    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="from_userid")
    public UserBase fromUser;

    //被查看者id
    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="to_userid")
    public UserBase toUser;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<FriendVisit> findByToUserId(Long toUserId){
        return find("from FriendVisit where toUser.id = ? order by createTime desc",toUserId).fetch();
    }

    public static List<FriendVisit> findByFromUserId(long fromUserId) {
        return find("from FriendVisit where fromUser.id = ? order by createTime desc",fromUserId).fetch();
    }
}
