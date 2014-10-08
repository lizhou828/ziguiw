package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-2-21
 * Time: 下午3:37
 */
@Entity
@Table(name = "user_photo")
@SequenceGenerator(name = "USER_PHOTO_SEQ",
        sequenceName = "user_photo_seq",
        allocationSize = 1)
@Form(displayName = "用户照片")
public class UserPhoto extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PHOTO_SEQ")
    public long id;

    @Column(name = "title")
    @Field(displayName = "标题")
    public String title;

    @Column(name = "status")
    @Field(displayName = "状态")
    public int state;

    @Column(name = "url")
    @Field(displayName = "照片url")
    public String url;

    @Column(name = "text")
    @Field(displayName = "描述")
    public String text;

    @Column(name="create_time")
    @Field(displayName = "创建时间")
    public Date createTime;
}
