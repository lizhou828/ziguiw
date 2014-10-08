package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

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
        PointsHistory pointsHistory = new PointsHistory();
        pointsHistory.user = user;
        pointsHistory.pointsChange = pointsChange;
        pointsHistory.flag = flag;
        pointsHistory.type = type;
        pointsHistory.create();
    }

}
