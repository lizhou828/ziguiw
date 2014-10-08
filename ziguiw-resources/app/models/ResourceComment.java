package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: administrator
 * Date: 13-5-21
 * Time: 上午10:43
 */
@Entity
@Table(name="r_resource_comment")
@SequenceGenerator(
        name="R_RESOURCE_COMMENT_SEQ",
        sequenceName="r_resource_comment_seq",
        allocationSize=1
)
public class ResourceComment  extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCE_COMMENT_SEQ")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    public Resource resource;

    public String content;

    @JoinColumn(name = "create_time")
    public Date createTime = new Date();

    @OneToOne
    @JoinColumn(name = "user_id")
    public UserBase userBase;

    public static Page<ResourceComment> findByResourceId(Long id,Integer page,Integer pageSize){
        String hql = "select r from ResourceComment r where r.resource.id = ? order by r.createTime desc";
        List<ResourceComment> list = find(hql,id).fetch(page,pageSize);
        hql = "select count(*) from ResourceComment r where r.resource.id = ?";
        Long totalCount = count(hql,id);
        return new Page<ResourceComment>(page,pageSize,totalCount,list);
    }
}
