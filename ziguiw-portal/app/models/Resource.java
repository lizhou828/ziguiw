package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:01
 */
@Entity
@Table(name="r_resources")
@SequenceGenerator(
        name="R_RESOURCES_SEQ",
        sequenceName="r_resources_seq",
        allocationSize=1
)
public class Resource extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCES_SEQ")
    public Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserBase user;//会员

    @Column(name = "title")
    public String title;

    @Column(name = "resource_path")
    public String resourcePath;

    @Column(name = "resource_down_Number")
    public Long resourceDownNumber = 0l; //下载次数

    @Column(name = "resource_size")
    public Long resourceSize;

    @Column(name = "upload_ip")
    public String uploadIp;

    @Column(name = "need_point")
    public Integer needPoint = 0;

    @Column(name = "keys")
    public String keys;

    @Column(name = "resource_file_type")
    public String resourceFileType;

    @Column(name ="description")
    public String description;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "state")
    public Integer state = Status.UNVERIFIED;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "click_count")
    public Integer clickCount = 0;


   public static List<Resource> findByFriendId(List<Long> friendId){
           String hql = "";
           hql = hql + "user.id = " + friendId.get(0);
           for (int i = 0; i<friendId.size(); i++){
               if(i > 0){
                   hql = hql + " or user.id = " + friendId.get(i);
               }
           }
           return find("from Resource where ("+hql+") and state = ? order by createTime desc",Status.OK).fetch();
   }

}
