package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-12
 * Time: 下午4:04
 */

@Entity
@Table(name = "user_mood")
@SequenceGenerator(name = "USER_MOOD_SEQ",
        sequenceName = "user_mood_seq",
        allocationSize = 1)
public class UserMood extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MOOD_SEQ")
    public long id;

    @Column(name = "text")
    public String text;

    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static Page<UserMood> findByUserId(Long userId, Integer page, Integer pageSize) {
        String hql = "select u from UserMood u where u.status = ? and u.user.id = ?" +
                " order by u.createTime desc";
        List<UserMood> list = find(hql,Status.OK,userId).fetch(page,pageSize);
        hql = "select count(*) from UserMood u where u.status = ? and u.user.id = ?";
        Long totalCount = count(hql,Status.OK,userId);
        return new Page<UserMood>(page,pageSize,totalCount,list);
    }

}
