package models;

import com.arj.ziguiw.common.SchoolPhotoType;
import com.arj.ziguiw.common.Status;
import org.hibernate.annotations.Formula;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午10:50
 */
@Entity
@Table(name = "s_class_photo")
@Form(displayName = "班级照片/视频")
@SequenceGenerator(name = "s_class_photo_seq", sequenceName = "s_class_photo_seq", allocationSize = 1)
public class ClassPhoto extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_class_photo_seq")
    public Long id;

    @Column(name = "title", length = 255)
    @Field(displayName = "标题")
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name = "class_id")
    @Field(displayName = "班级")
    public Long classId;

    @Formula(value = "(select sc.bj_mcheng from t_classes sc where sc.bj_id = class_id)")
    @Field(displayName = "班级")
    public String className;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();


    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;

    @Column(name = "url", nullable = false, length = 255)
    @Field(displayName = "图片")
    public String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "创建人")
    public UserBase user;

    @Column(name = "xxId", nullable = false)
    @Field(displayName = "学校")
    public String xxId;

    @Formula(value = "(select s.sch_name from t_schoolinfo s where s.xxId = xxId)")
    @Field(displayName = "学校")
    public String schoolName;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = SchoolPhotoType.PHOTO;

    @Column(name = "status", nullable = false, columnDefinition = "number(3)")
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;


    @Column(name = "cover", length = 1000)
    @Field(displayName = "封面照")
    public String cover;

}
