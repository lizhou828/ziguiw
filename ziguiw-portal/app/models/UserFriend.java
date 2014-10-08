package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-14
 * Time: 下午2:35
 */
@Entity
@Table(name = "user_friend")
@SequenceGenerator(name = "USER_FRIEND_SEQ", 
        sequenceName = "user_friend_seq", 
        allocationSize = 1)
public class UserFriend extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_FRIEND_SEQ")
    public long id;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id", insertable=true, updatable=true)
    public UserBase user;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="friend_id", insertable=true, updatable=true)
    public UserBase friendUser;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<Long> findFriendIdByUserId(Long userId) {
        String hql = "select u from UserFriend u where u.user.id = ? order by createTime desc";
        List<UserFriend> list = find(hql,userId).fetch();
        List<Long> friends = new ArrayList<Long>();
        for(UserFriend userFriend : list){
            friends.add(userFriend.friendUser.id);
        }
        return friends;
    }

    public static Page<UserFriend> pageByUserId(Long userId, Integer page, Integer pageSize){
        String hql = "select u from UserFriend u where u.user.id = ? order by createTime desc";
        List<UserFriend> list = find(hql,userId).fetch(page, pageSize);
        Long count = count("select count(u) from UserFriend u where u.user.id = ? order by createTime desc",userId);
        return new Page<UserFriend>(page,pageSize,count,list);
    }
}
