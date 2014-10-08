package models;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.Boolean;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-9
 * Time: P.M 3:13
 */
@Entity
@Table(name = "s_school_photo")
@Form(displayName = "快乐校园")
@SequenceGenerator(name = "s_school_photo_seq", sequenceName = "s_school_photo_seq", allocationSize = 1, initialValue = 100000)
public class SchoolPhoto extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_photo_seq")
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

    @Column(name="recommend")
    @Field(displayName = "推荐到首页")
    public Integer recommend = com.arj.ziguiw.common.Boolean.NO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "用户")
    public UserBase user;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = SchoolPhotoType.PHOTO;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ablum_id")
    @Field(displayName = "相册")
    public SchoolAlbum album;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "url", nullable = false, length = 1000)
    @Field(displayName = "图片")
    public String url;

    @Column(name = "cover", length = 1000)
    @Field(displayName = "封面照")
    public String cover;

    @Column(name = "status", nullable = false, columnDefinition = "number(2)")
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    public static List<SchoolPhoto> findByRecommend(Long schoolId, int count,int type) {
        return find("from SchoolPhoto where school.id = ? and type = ? and status = ? and recommend = ? order by createTime desc",
                schoolId, type, Status.OK, Boolean.YES).fetch(count);
    }

    public static List<SchoolPhoto> findSchoolPhotos(Long schoolId, int count,int type, Long albumId) {
        if(type == SchoolPhotoType.VIDEO){
            return find("from SchoolPhoto where school.id = ? and type = ? and status = ? order by createTime desc",
                schoolId, type, Status.OK).fetch(count);
        }else {
            return find("from SchoolPhoto where school.id = ? and type = ? and status = ? and album.id = ? order by createTime desc",
                schoolId, type, Status.OK, albumId).fetch();
        }
    }

    public static Page<SchoolPhoto> findPhotoPage(Integer page, Integer pageSize, Long schoolId, int type){
        List<SchoolPhoto> list = find("from SchoolPhoto where school.id = ? and type = ? and status = ? order by createTime desc",
                schoolId,type,Status.OK).fetch(page,pageSize);
        long count = count("select count(*) from SchoolPhoto where school.id = ? and type = ? and status = ? order by createTime desc",
                schoolId,type,Status.OK);
        return new Page<SchoolPhoto>(page,pageSize,count,list);
    }



    @Override
    public String toString() {
        return String.format("%s[%s]", title, id);
    }
}
