package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-28
 * Time: 下午4:54
 */
@Entity
@Table(name = "s_school_album")
@Form(displayName = "相册")
@SequenceGenerator(name = "s_school_album_seq", sequenceName = "s_school_album_seq", allocationSize = 1, initialValue = 100000)
public class SchoolAlbum extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_school_album_seq")
    public Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    @Field(displayName = "学校")
    public School school;

    @Column(name = "class_id")
    @Field(displayName = "班级ID")
    public Long classId;

    @Column(name = "name")
    @Field(displayName = "相册名字")
    public String name;

    @Column(name="describe")
    @Field(displayName = "描述")
    public String describe ;

    @Column(name = "status", nullable = false, columnDefinition = "number(2)")
    @Field(displayName = "状态")
    public int status = Status.OK;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATE")
    @Field(displayName = "创建时间")
    public Date createTime = new Date();

    @Column(name = "cover_url", length = 1000)
    @Field(displayName = "封面图")
    public String coverUrl;

    public static List<SchoolAlbum> findSchoolId(Long schoolId ,int count){
        return find("from SchoolAlbum where school.id = ? and status = ? and classId is null order by createTime desc",
                 schoolId, Status.OK).fetch(count);
    }

    public static List<SchoolAlbum> findClassId(Long classId, int count){
        return find("from SchoolAlbum where status = ? and classId = ? order by createTime desc",
                 Status.OK, classId).fetch(count);
    }

    public static Page<SchoolAlbum> findByPage(Long schoolId, Integer page , Integer pageSize){
        List<SchoolAlbum> list = find("from SchoolAlbum where school.id = ? and classId is null and status = ? order by createTime desc",
                schoolId,Status.OK).fetch(page,pageSize);
        long count = count("select count(*) from SchoolAlbum where school.id = ? and classId is null and status = ? order by createTime desc",
                schoolId,Status.OK);
        return new Page<SchoolAlbum>(page,pageSize,count,list);
    }

    public static Page<SchoolAlbum> findByClassId(Long classId, Integer page , Integer pageSize){
        List<SchoolAlbum> list = find("from SchoolAlbum where status = ? and classId = ? order by createTime desc",
                Status.OK, classId).fetch(page,pageSize);
        long count = count("select count(*) from SchoolAlbum where status = ? and classId = ? order by createTime desc",
                Status.OK,classId);
        return new Page<SchoolAlbum>(page,pageSize,count,list);
    }

}
