package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-1
 * Time: 上午11:57
 */
@Entity
@Table(name = "s_school_xxyd")
@Form(displayName = "学习园地")
@SequenceGenerator(name = "s_school_xxyd_seq", sequenceName = "s_school_xxyd_seq", allocationSize = 1, initialValue = 100000)
public class SchoolXxyd extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_xxyd_seq")
    public Long id;

    @Column(name = "title", length = 255)
    @Field(displayName = "标题")
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "创建人")
    public UserBase user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    @Field(displayName = "班级")
    public SchoolClazz clazz;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public Integer type;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "url", length = 1000)
    @Field(displayName = "图片")
    public String url;

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;

    @Column(name = "status", nullable = false, columnDefinition = "number(2)")
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    @Column(name = "video_url", length = 1000)
    @Field(displayName = "图片")
    public String videoUrl;


    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;



    public static List<SchoolXxyd> findBySchoolId(long schoolId , int count){
         return find("from SchoolXxyd where school.id = ? and status = ? order by createTime desc",
                 schoolId,Status.OK).fetch(count);
    }

    public static List<SchoolXxyd> findUrl(Long schoolId , int count) {
        return find("from SchoolXxyd where school.id = ? and status = ? and url is not null order by createTime desc",
                schoolId,Status.OK).fetch(count);
    }

    public static Page<SchoolXxyd> findPage(Integer page , Integer pageSize ,Long schoolId,Integer type){
         List<SchoolXxyd> list = find("from SchoolXxyd where school.id = ? and status = ? and type = ? order by createTime desc",
                 schoolId,Status.OK,type).fetch(page,pageSize);
         long count = count("select count(*) from SchoolXxyd where school.id = ? and status = ? and type = ? order by createTime desc",
                 schoolId,Status.OK,type);
        return new Page<SchoolXxyd>(page,pageSize,count,list);
    }
}
