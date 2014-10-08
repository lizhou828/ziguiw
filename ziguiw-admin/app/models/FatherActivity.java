package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-9
 * Time: 下午2:54
 */
@Entity
@Table(name = "father_activity")
@SequenceGenerator(name = "FATHER_ACTIVITY_SEQ",
        sequenceName = "father_activity_seq",
        allocationSize = 1)
@Form(displayName = "父亲照片")
public class FatherActivity extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FATHER_ACTIVITY_SEQ")
    public long id;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "url")
    @Field(displayName = "照片")
    public String url;

    @Column(name = "title")
    @Field(displayName = "标题")
    public String title;

    @Column(name = "display_name")
    @Field(displayName = "姓名")
    public String displayName;

    @Column(name = "age")
    @Field(displayName = "年龄")
    public Integer age;

    @Column(name = "contact")
    @Field(displayName = "联系电话")
    public String contact;

    @Column(name = "school_name")
    public String schoolName;

    @Column(name = "bj")
    public String bj;

    @Column(name = "email")
    @Field(displayName = "邮箱")
    public String email;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "click_count")
    public Integer clickCount = 0;

    @Column(name = "status")
    @Field(displayName = "状态")
    public Integer status = Status.UNVERIFIED;

    public static Page findPage(Integer page, Integer pageSize){
        List<FatherActivity> list = FatherActivity.find("from FatherActivity order by createTime desc").fetch(page, pageSize);
        Long count = count("select count(*) from FatherActivity order by createTime desc");
        return new Page(page,pageSize,count,list);
    }
}
