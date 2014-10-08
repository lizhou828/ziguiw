package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:19
 */
@Entity
@Table(name="r_resourcedown")
@SequenceGenerator(
        name="R_RESOURCEDOWN_SEQ",
        sequenceName="r_resourcedown_seq",
        allocationSize=1
)
public class ResourceDown extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCEDOWN_SEQ")
    public Long id;

    @ManyToOne
    @JoinColumn(name="resource_id")
    public Resource resource;

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserBase user;

    @Column(name="create_date")
    public Date createDate = new Date();

    public static ResourceDown findByResIdAndUserId(Long resourceId,Long userId) {
        String hql = "select r from ResourceDown r where r.resource.id = ? and r.user.id = ?";
        return find(hql,resourceId,userId).first();
    }

    public static Page<ResourceDown> findByUserId(long id,Integer page,Integer pageSize) {
        String hql = "select r from ResourceDown r where r.user.id = ? order by r.createDate";
        List<ResourceDown> list = find(hql,id).fetch(page,pageSize);
        hql = "select count(*) from ResourceDown r where r.user.id = ?";
        Long totalCount = count(hql,id);
        return new Page<ResourceDown>(page,pageSize,totalCount,list);
    }

    public static Page<ResourceDown> findByKeyAndUserId(String key, long userId, Integer page, Integer pageSize) {
        String hql = "select r from ResourceDown r where r.user.id = ? and  (r.resource.title like '%"+key+"%' or r.resource.type.typeName like '%"+key+"%' " +
                "or r.resource.version.versionName like '%"+key+"%' or r.resource.subject.subjectName like '%"+key+"%' " +
                "or r.resource.grade.gradeName like '%"+key+"%') order by r.createDate desc";
        List<Resource> resourceList = find(hql,userId).fetch(page,pageSize);
        hql = "select count(*) from ResourceDown r where r.user.id = ? and (r.resource.title like '%"+key+"%' or r.resource.type.typeName like '%"+key+"%' " +
                "or r.resource.version.versionName like '%"+key+"%' " +
                "or r.resource.subject.subjectName like '%"+key+"%' " +
                "or r.resource.grade.gradeName like '%"+key+"%')";
        Long totalCount = count(hql,userId);
        return new Page<ResourceDown>(page,pageSize,totalCount,resourceList);
    }
}
