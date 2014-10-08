package models;

import com.arj.ziguiw.common.Status;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-15
 * Time: 上午9:46
 */

@Entity
@Table(name = "user_photo")
@SequenceGenerator(name = "USER_PHOTO_SEQ",
        sequenceName = "user_photo_seq",
        allocationSize = 1)
public class UserPhoto extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PHOTO_SEQ")
    public long id;

    public String title;

    public String url;

    public String text;

    public Integer status = Status.OK;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="photoAlbumId")
    @Required
    public PhotoAlbum photoAlbum;

    @Column(name = "user_id")
    public Long userId;

    public static List<UserPhoto> findByPhotoAlbumId(Long photoAlbumId) {
        String hql = "select u from UserPhoto u where u.status = ? and u.photoAlbum.id = ?";
        return find(hql,Status.OK,photoAlbumId).fetch();
    }
}
