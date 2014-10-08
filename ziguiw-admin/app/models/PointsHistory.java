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
@Table(name = "r_point_history")
@SequenceGenerator(name = "R_POINT_HISTORY_SEQ",
        sequenceName = "r_point_history_seq",
        allocationSize = 1)
public class PointsHistory extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_POINT_HISTORY_SEQ")
    public long id;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id", insertable=true, updatable=true)
    public UserBase user;

    @Column(name="points_change")
    public int pointsChange;

    //1是增加，2是减少
    public int flag;

    //类型,1代表下载资源扣减积分，2代表上传资源增加积分，3代表上传资源审核通过增加积分
    @Column(name="change_type")
    public int type;

    @Column(name="change_time")
    public Date changeTime = new Date();

}
