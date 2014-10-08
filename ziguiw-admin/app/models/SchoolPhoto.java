package models;

import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-9
 * Time: P.M 3:13
 */
@Entity
@Table(name = "s_school_photo")
@Form(displayName = "学校照片/视频")
@SequenceGenerator(name = "s_school_photo_seq", sequenceName = "s_school_photo_seq", allocationSize = 1)
public class SchoolPhoto extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_photo_seq")
    public Long id;

    @Column(name = "title", length = 255)
    @Field(displayName = "标题")
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "创建人")
    public UserBase user;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "url", nullable = false, length = 1000)
    @Field(displayName = "图片")
    public String url;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = SchoolPhotoType.PHOTO;

    @Column(name = "cover", length = 1000)
    @Field(displayName = "封面照")
    public String cover;

    @Column(name = "status", nullable = false, columnDefinition = "number(2)")
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    @Override
    public String toString() {
        return String.format("%s", title);
    }
}
