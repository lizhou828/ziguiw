package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:01
 */
@Entity
@Table(name="r_resources")
@Form(displayName = "资源")
@SequenceGenerator(
        name="R_RESOURCES_SEQ",
        sequenceName="r_resources_seq",
        allocationSize=1
)
public class Resource extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCES_SEQ")
    @Field(displayName = "ID")
    public Long id;


    @ManyToOne
    @JoinColumn(name="grade_id")
    @Field(displayName = "年级")
    public Grade grade;//年级

    @ManyToOne
    @JoinColumn(name="subject_id")
    @Field(displayName = "科目")
    public Subject subject;//科目

    @ManyToOne
    @JoinColumn(name="type_id")
    @Field(displayName = "类型")
    public Type type;//类型

    @ManyToOne
    @JoinColumn(name="user_id")
    @Field(displayName = "上传者")
    public UserBase user;//会员

    @ManyToOne
    @JoinColumn(name="version_id")
    @Field(displayName = "版本")
    public Version version;//版本


    @Column(name = "title")
    @Field(displayName = "标题")
    public String title;

    @Column(name = "resource_path")
    @Field(displayName = "路径")
    public String resourcePath;

    @Column(name = "resource_down_Number")
    @Field(displayName = "下载次数")
    public Long resourceDownNumber = 0l; //下载次数

    @Column(name = "resource_size")
    @Field(displayName = "资源大小")
    public Long resourceSize;

    @Column(name = "upload_ip")
    public String uploadIp;

    @Column(name = "need_point")
    public Integer needPoint = 0;

    @Column(name = "keys")
    public String keys;

    @Column(name = "resource_file_type")
    @Field(displayName = "资源文件类型")
    public String resourceFileType;

    @Column(name ="description")
    public String description;

    @Column(name = "create_time")
    @Field(displayName = "上传时间")
    public Date createTime = new Date();

    @Column(name = "alter_time")
    @Field(displayName = "修改时间")
    public Date alterTime = new Date();

    @Column(name = "state")
    @Field(displayName = "状态")
    public Integer state = Status.UNVERIFIED;

    @Field(displayName="操作")
    public String  caozuo="操作" ;

    public static String check(File file, String title, Long typeId, Long versionId, Long gradeId, Long subjectId) {
        String error = "";
        if(file == null){
            error = "文件不能为空";
            return error;
        }
        if(title == null || "".equals(title)){
            error = "标题不能为空";
            return error;
        }
        if(typeId == null){
            return error = "类型不能为空";
        }
        Type type1 = Type.findById(typeId);
        if(!type1.suffix.contains(file.getName().substring(file.getName().lastIndexOf(".") + 1))){
            return error = "不允许的文件类型";
        }
        if(versionId == null){
            return error = "版本不能为空";
        }
        if(gradeId == null){
            return error = "年级不能为空";
        }
        if(subjectId == null){
            return error = "科目不能为空";
        }
        return error;
    }

    public static Page<Resource> findByUserId(long userId,Integer page,Integer pageSize) {
        String hql = "select r from Resource r where r.user.id = ? and r.state = ? order by createTime desc";
        List<Resource> list = Resource.find(hql,userId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.user.id = ? and r.state = ?";
        Long totalCount  = count(hql,userId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static Page<Resource> findUncheckedRes(Integer page,Integer pageSize){
        String hql="select r from Resource r";
        List<Resource> list=Resource.find(hql).fetch(page,pageSize);
        hql="select count(*) from Resource ";
        Long totalCount=count(hql);
        return new Page<Resource>(page,pageSize,totalCount,list);

    }


    public static List<Resource> findNewResource() {
        String hql = "select r from Resource r where r.state = ? order by createTime desc";
        List<Resource> list = find(hql,Status.OK).fetch(1,9);
        return list;
    }

    public static List<Resource> findDownResource() {
        String hql = "select r from Resource r where r.state = ? order by resourceDownNumber desc";
        List<Resource> list = find(hql,Status.OK).fetch(1,9);
        return list;
    }
    public static List<Resource> findByleve(int leve){
        String hql="from Resource r where r.grade.leve=?";
        return find(hql,leve).fetch();
    }


    public static List<Resource> elementHot(int leve,int count){
        String hql="from Resource r where r.grade.leve=?  order by r.resourceDownNumber desc ";
        return find(hql,leve).fetch(count);
    }

    public static List<Resource> elementNew(int leve,int count){
        String hql="from Resource r where r.grade.leve=? order by r.createTime desc";
        return find(hql,leve).fetch(count);
    }
    public  static String getSwfPath(String path){
        path = path.substring(0,path.lastIndexOf("."));
        String swfPath = path + ".swf";
        return swfPath;
    }

    public static Resource findById(long id){
        String hql="from Resource r where r.id=?";
        return find(hql,id).first();
    }

    @Override
    public String toString() {
        return id + "";
    }
}
