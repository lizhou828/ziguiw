package models;

import controllers.CRUD;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-3
 * Time: 上午9:12
 */
@Entity
@Form(displayName = "照片评论")
@Table(name="S_PHOTO_REPLY")
@SequenceGenerator(name="S_PHOTO_REPLY_SEQ", sequenceName="S_PHOTO_REPLY_SEQ", allocationSize=1, initialValue = 100000)
public class PhotoReply extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PHOTO_REPLY_SEQ")
    public Long id;

    @Column(name = "album_id")
    public long albumId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "用户")
    public UserBase user;

    @Column(name = "create_time", columnDefinition = "date", nullable = false)
    @CRUD.Exclude
    public Date createTime = new Date();

    @Column(name = "content")
    @Field(displayName = "内容")
    public String content;

    public static Page<PhotoReply> findByPage(Long albumId, Integer page, Integer pageSize){
        List<PhotoReply> list = find("from PhotoReply where albumId = ? order by createTime desc",albumId).fetch(page,pageSize);
        Long count = count("select count(*) from PhotoReply where albumId = ? order by createTime desc",albumId);
        return new Page(page, pageSize, count, list);
    }

}
