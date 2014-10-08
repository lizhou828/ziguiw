package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-21
 * Time: 上午11:36
 */
@Entity
@Table(name = "point_history")
@SequenceGenerator(name = "POINT_HISTORY_SEQ",
        sequenceName = "point_history_seq",
        allocationSize = 1)
public class PointsHistory extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POINT_HISTORY_SEQ")
    public long id;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id", insertable=true, updatable=true)
    public UserBase user;

    @Column(name="points_change")
    public int pointsChange;

    //1是增加，2是减少
    public int flag;

    @Column(name="change_type")
    public int type;

    @Column(name="change_time")
    public Date changeTime = new Date();

    public static void create(UserBase user,Integer pointsChange,Integer flag,Integer type){
        user.save();
        PointsHistory pointsHistory = new PointsHistory();
        pointsHistory.user = user;
        pointsHistory.pointsChange = pointsChange;
        pointsHistory.flag = flag;
        pointsHistory.type = type;
        pointsHistory.create();
    }

    public static Long findLogonCount(Long userId) {
        String hql = "select count(*) from PointsHistory p where to_char(p.changeTime,'yyyy-MM-dd') = to_char(?,'yyyy-MM-dd') and p.user.id = ?";
        return count(hql,new Date(),userId);
    }

    public static Page findByuserId(long userId , Integer page , Integer pageSize){
        List<PointsHistory> list = find("from PointsHistory p where p.user.id = ? order by p.changeTime desc",userId).fetch(page,pageSize);
        long count = count("select count(p) from PointsHistory p where p.user.id = ?",userId);
        return new Page(page ,pageSize ,count ,list);
    }

    public static PointsHistory findByType(Long userId, Integer type){
        return find("from PointsHistory where user.id = ? and type = ?",userId,type).first();
    }

}
