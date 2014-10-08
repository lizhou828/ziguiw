package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: A.M.9:05
 */
/**
 *   资源实体类
 * */

@Entity
@Table(name="r_resources")
@SequenceGenerator(
        name="R_RESOURCES_SEQ",
        sequenceName="r_resources_seq",
        allocationSize=1
)
@Form(displayName = "资源")
public class Resource extends JPASupport{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCES_SEQ")
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name="grade_id")
    public GradeResource grade;//年级

    @ManyToOne
    @JoinColumn(name="subject_id")
    public Subject subject;//科目

    @ManyToOne
    @JoinColumn(name="type_id")
    public ResourceType type;//类型

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

    public static Page<Resource> findByGradeAndSubject(String srcGradeId,String srcSubjectId,Integer page,Integer pageSize){
        String hql = "from Resource r where r.grade.srcId =? and r.subject.srcId =? order by createTime desc ";
        List<Resource> data = find(hql,srcGradeId,srcSubjectId).fetch(page,pageSize);
        Long totalCount = count("select count(*) "+hql,srcGradeId,srcSubjectId);
        return new Page<Resource>(page,pageSize,totalCount,data);
    }
    public static Page<Resource> findPage(UserBase userBase, Integer page, Integer pageSize) {
        String hql = "from Resource where user = ? order by createTime desc " ;
        List<Resource> list= find(hql,userBase).fetch(page, pageSize);
        if(list.size() == 0){
            return new Page<Resource>(page, pageSize, 0, list);
        }
        long totalCount = count("select count(*)" + hql,userBase);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

}
