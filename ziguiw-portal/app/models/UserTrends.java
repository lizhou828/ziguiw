package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-22
 * Time: 下午2:22
 */
@Entity
@Table(name = "user_trends")
@SequenceGenerator(name = "USER_TRENDS_SEQ",
        sequenceName = "user_trends_seq",
        allocationSize = 1)
public class UserTrends extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_TRENDS_SEQ")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "type")
    public Integer type;

    @Column(name = "object_id")
    public Long objectId;

    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<UserTrends> findByFriendId(List<Long> friendId){
        String hql = "";
        hql = hql + "user.id = " + friendId.get(0);
        for (int i = 0; i<friendId.size(); i++){
             if(i > 0){
                 hql = hql + " or user.id = " + friendId.get(i);
             }
        }
        return find("from UserTrends where ("+hql+") and status = ? order by createTime desc",Status.OK).fetch();
    }

    public static List<UserTrends> findByUserId(long userId) {
        return find("from UserTrends where user.id = ?",userId).fetch();
    }

    public static Page<UserTrends> pageByFriendId(List<Long> friendId, Integer page, Integer pageSize){
        String hql = "";
        hql = hql + "user.id = " + friendId.get(0);
        for (int i = 0; i<friendId.size(); i++){
            if(i > 0){
                hql = hql + " or user.id = " + friendId.get(i);
            }
        }
        List<UserTrends> list = find("from UserTrends where ("+hql+") and status = ? order by createTime desc",Status.OK).fetch(page,pageSize);
        Long count = count("select count(*) from UserTrends where ("+hql+") and status = ? order by createTime desc",Status.OK);
        return new Page<UserTrends>(page,pageSize,count,list);
    }

    public static Page<UserTrends> pageByUserId(Long userId, Integer page, Integer pageSize){
        List<UserTrends> list = find("from UserTrends where user.id = ? order by createTime desc",userId).fetch(page,pageSize);
        Long count = count("select count(*) from UserTrends where user.id = ? order by createTime desc",userId);
        return new Page<UserTrends>(page,pageSize,count,list);
    }
}
