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
 * Time: 上午9:40
 */
@Entity
@Table(name = "photo_album")
@SequenceGenerator(name = "PHOTO_ALBUM_SEQ",
        sequenceName = "photo_album_seq",
        allocationSize = 1)
public class PhotoAlbum extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHOTO_ALBUM_SEQ")
    public Long id;

    @Column(name = "name")
    public String name;

    @ManyToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
    @JoinColumn(name="userId")
    @Required
    public UserBase user;

    @Column(name = "cover")
    public String cover;

    @Column(name = "status")
    public Integer status = Status.OK;

    @Column(name = "describe")
    public String describe;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static Page<PhotoAlbum> findByUserId(Long userId, Integer page, Integer pageSize) {
        String hql = "select p from PhotoAlbum p where p.status = ? and p.user.id = ? " +
                     "order by p.createTime desc";
        List<PhotoAlbum> list = PhotoAlbum.find(hql,Status.OK,userId).fetch(page,pageSize);
        hql = "select count(*) from PhotoAlbum p where p.status = ? and p.user.id = ?";
        Long totalCount = count(hql,Status.OK,userId);
        return new Page<PhotoAlbum>(page,pageSize,totalCount,list);
    }
}
