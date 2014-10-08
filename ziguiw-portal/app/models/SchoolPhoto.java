package models;

import com.arj.ziguiw.common.Boolean;
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
@SequenceGenerator(name = "s_school_photo_seq", sequenceName = "s_school_photo_seq", allocationSize = 1, initialValue = 100000)
public class SchoolPhoto extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_photo_seq")
    public Long id;

    @Column(name = "title", length = 255)
    public String title;

    @Column(name = "cause", length = 1000)
    public String cause;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    public School school;

    @Column(name="recommend")
    public Integer recommend = Boolean.NO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserBase user;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    public int type = SchoolPhotoType.PHOTO;

    @Column(name="describe")
    public String describe ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ablum_id")
    public SchoolAlbum album;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    public Date createTime = new Date();

    @Column(name = "url", nullable = false, length = 1000)
    public String url;

    @Column(name = "cover", length = 1000)
    public String cover;

    @Column(name = "status", nullable = false, columnDefinition = "number(2)")
    public int status = Status.UNVERIFIED;


    public static SchoolPhoto findByTime(){
        return find("from SchoolPhoto s where s.status = ? order by s.createTime desc",Status.OK).first();
    }
}
