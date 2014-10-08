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
public class FatherActivity extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FATHER_ACTIVITY_SEQ")
    public long id;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "url")
    public String url;

    @Column(name = "title")
    public String title;

    @Column(name = "display_name")
    public String displayName;

    @Column(name = "age")
    public Integer age;

    @Column(name = "contact")
    public String contact;

    @Column(name = "email")
    public String email;

    @Column(name = "school_name")
    public String schoolName;

    @Column(name = "bj")
    public String bj;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "click_count")
    public Integer clickCount = 0;

    @Column(name = "status")
    public Integer status = Status.UNVERIFIED;

    public static Page findPage(Integer page, Integer pageSize){
        List<FatherActivity> list = FatherActivity.find("from FatherActivity where status = ? order by createTime desc",Status.OK).fetch(page, pageSize);
        Long count = count("select count(*) from FatherActivity where status = ? order by createTime desc",Status.OK);
        return new Page(page,pageSize,count,list);
    }

    public static Page findClickCount(Integer page, Integer pageSize){
        List<FatherActivity> list = FatherActivity.find("from FatherActivity where status = ? order by clickCount desc",Status.OK).fetch(page, pageSize);
        Long count = count("select count(*) from FatherActivity where status = ? order by clickCount desc",Status.OK);
        return new Page(page,pageSize,count,list);
    }

    public static Page<FatherActivity> searchMobile(String mobile, Integer page, Integer pageSize){
        List<FatherActivity> list = FatherActivity.find("from FatherActivity " +
                "where status = ? and contact = ? order by clickCount desc",Status.OK,mobile).fetch(page, pageSize);
        Long count = count("from FatherActivity " +
                "where status = ? and contact = ? order by clickCount desc",Status.OK,mobile);
        return new Page<FatherActivity>(page,pageSize,count,list);
    }

    public static List<FatherActivity> findByTime(){
        return find("from FatherActivity where status = ? order by createTime desc",Status.OK).fetch();
    }

    public static List<FatherActivity> findHot(){
          return find("from FatherActivity where status = ? order by clickCount desc",Status.OK).fetch();
    }
}
