package models;

import com.arj.ziguiw.common.Status;
import controllers.CRUD;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-2-20
 * Time: 下午2:27
 */
@Entity
@Table(name = "user_mood")
@SequenceGenerator(name = "USER_MOOD_SEQ",
        sequenceName = "user_mood_seq",
        allocationSize = 1)
@Form(displayName = "用户说说")
public class UserMood extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MOOD_SEQ")
    @Field(displayName = "ID")
    @CRUD.Exclude
    public long id;

    @Column(name = "text")
    @Field(displayName = "内容")
    public String text;

    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    @Required
    public UserBase user;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();


}
