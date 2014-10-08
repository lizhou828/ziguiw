package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-30
 * Time: P.M.2:46
 */
@Entity
@Table(name = "point_history")
@Form(displayName = "积分历史")
@SequenceGenerator(name = "POINT_HISTORY_SQE",sequenceName = "POINT_HISTORY_SQE",allocationSize = 1)
public class PointsHistory extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "POINT_HISTORY_SQE")
    @Column(name = "id")
    public long id;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="user_id", insertable=true, updatable=true)
    @Field(displayName = "用户")
    public UserBase user;

    @Column(name="points_change")
    @Field(displayName = "改变的积分")
    public int pointsChange;

    //1是增加，2是减少
    @Column(name="flag")
    @Field(displayName = "增减类型")
    public int flag = 1;

    //类型，例如是回答问题，还是登录、注册呢
    @Column(name="change_type")
    @Field(displayName = "改变类型")
    public int changeType;

    @Column(name="change_time")
    @Field(displayName = "修改时间")
    public Date changeTime = new Date();

   public static Page<PointsHistory> findPage(UserBase userBase, Integer page, Integer pageSize){
       String hql = "from PointsHistory where user.id = ? order by changeTime desc";
       List<PointsHistory> data = find(hql,userBase.id).fetch(page,pageSize);
       if(data.size() == 0) return new Page<PointsHistory>(page,pageSize,0,new ArrayList<PointsHistory>());
       long totalCount = count("select count(*)" + hql,userBase.id);
       return new Page<PointsHistory>(page,pageSize,totalCount,data);
   }

    




}
