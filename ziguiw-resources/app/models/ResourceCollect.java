package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Liujy
 * Date: 13-4-12
 * Time: 下午4:38
 */
@Entity
@Table(name = "r_collect")
@SequenceGenerator(name = "R_COLLECT_SEQ",
        sequenceName = "r_collect_seq",
        allocationSize = 1)
public class ResourceCollect extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_COLLECT_SEQ")
    public long id;

    @Column(name = "title")
    public String title;

    @Column(name = "object_id")
    public String objectId;

    @Column(name = "r_type")
    public String resourceType;

    @Column(name = "status")
    public int status = Status.OK;

    @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static ResourceCollect findByResIdAndUserId(Long resourceId, long id) {
        String hql = "select r from ResourceCollect r where r.objectId=? and r.user.id = ?";
        return find(hql,resourceId+ "",id).first();
    }
}
