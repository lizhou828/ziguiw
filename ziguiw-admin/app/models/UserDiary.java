package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-2-21
 * Time: 下午3:06
 */
@Entity
@Table(name="user_diary")
@SequenceGenerator(name="USER_DIARY_SEQ",
        sequenceName="user_diary_seq", 
        allocationSize=1)
@Form(displayName = "用户日志")
public class UserDiary extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_DIARY_SEQ")
    public long id;

    @Column(name = "title")
    @Field(displayName = "标题")
    public String title;

    @Column(name = "status")
    @Field(displayName = "状态")
    public int state;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.LAZY)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "description")
    @Field(displayName = "摘要")
    public String description;

    @Lob
    @Basic(fetch= FetchType.EAGER,optional=true)
    @Column(name = "text")
    public String text;

    @Column(name = "first_image")
    @Field(displayName = "首图")
    public String firstImage;

}
