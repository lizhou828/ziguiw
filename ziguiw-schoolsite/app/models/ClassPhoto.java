package models;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.Boolean;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-16
 * Time: 上午10:50
 */
@Entity
@Table(name = "s_class_photo")
@Form(displayName = "照片/视频")
@SequenceGenerator(name = "s_class_photo_seq", sequenceName = "s_class_photo_seq", allocationSize = 1, initialValue = 100000)
public class ClassPhoto extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_class_photo_seq")
    public Long id;

    @Column(name = "title", length = 255)
    @Field(displayName = "标题")
    public String title;

    @Column(name = "cause", length = 1000)
    @Field(displayName = "失败原因")
    public String cause;

    @Column(name = "class_id")
    @Field(displayName = "班级ID")
    public Long classId;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "url", nullable = false, length = 255)
    @Field(displayName = "图片")
    public String url;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;

    @Column(name = "xxId", nullable = false)
    @Field(displayName = "学校编码")
    public String xxId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Field(displayName = "用户")
    public UserBase user;

    @Column(name="recommend")
    @Field(displayName = "推荐到首页")
    public Integer recommend = com.arj.ziguiw.common.Boolean.NO;

    @Column(name="type", columnDefinition = "number(2)", nullable = false)
    @Field(displayName = "类型")
    public int type = SchoolPhotoType.PHOTO;


    @Column(name = "status", nullable = false, columnDefinition = "number(3)")
    @Field(displayName = "状态")
    public int status = Status.UNVERIFIED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ablum_id")
    @Field(displayName = "相册")
    public SchoolAlbum album;

    @Column(name = "cover", columnDefinition = "varchar2(255)")
    @Field(displayName = "封面照")
    public String cover;

    public static List<ClassPhoto> findByType(Long classId, int type, int count){
          return find("select c from ClassPhoto c where c.type = ? and c.classId = ? and c.status = ? order by c.createTime desc"
          ,type,classId,Status.OK).fetch(count);
    }

    public static List<ClassPhoto> findPhotoByAlbumId(Long classId, Long albumId){
        return find("select c from ClassPhoto c where c.type = ? and c.classId = ? and c.status = ? and album.id = ?" +
                "order by c.createTime desc", SchoolPhotoType.PHOTO, classId, Status.OK, albumId).fetch();
    }

    public static List<ClassPhoto> findPhoto(Long classId, Integer count){
        return find("select c from ClassPhoto c where c.type = ? and c.classId = ? and c.status = ? and recommend = ?" +
                "order by c.createTime desc", SchoolPhotoType.PHOTO, classId, Status.OK, Boolean.YES).fetch(count);
    }

    public static Page<ClassPhoto> findPageByType(int type,long classId,int page,int pageSize){
        List<ClassPhoto> list = find("select c from ClassPhoto c where c.type = ? and c.classId = ? and c.status = ? " +
                "order by c.createTime desc",type,classId,Status.OK).fetch(page,pageSize);
        long count = count("select count(c) from ClassPhoto c where c.type = ? and c.classId = ? and c.status = ? " +
                "order by c.createTime desc",type,classId,Status.OK);
        return new Page<ClassPhoto>(page,pageSize,count,list);
    }

     public static ClassPhoto findByClassPhotoId(long id){
         return find("select c from ClassPhoto c where c.id = ?",id).first();
     }

    public static ClassPhoto findNew(long classId) {
        return find("from ClassPhoto where classId = ? order by id desc",classId).first();
    }

    public static Page<ClassPhoto> findPhotoPage(Integer page, Integer pageSize, Long classId, Integer type) {
           List<ClassPhoto> list = find("from ClassPhoto where classId= ? and type = ? and status = ? order by createTime desc",
                    classId,type,Status.OK).fetch(page,pageSize);
           long count = count("select count(*) from ClassPhoto where classId = ? and type = ? and status = ? order by createTime desc",
                   classId,type,Status.OK);
           return new Page<ClassPhoto>(page,pageSize,count,list);
    }

}
