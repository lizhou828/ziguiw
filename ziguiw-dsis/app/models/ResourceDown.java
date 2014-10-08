package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 资源下载的记录表
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-31
 * Time: A.M.9:06
 */
@Entity
@Table(name="r_resourcedown")
@Form(displayName = "资源下载")
@SequenceGenerator(name = "r_resourcedown_sqe",sequenceName = "r_resourcedown_sqe",allocationSize = 1)
public class ResourceDown extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "r_resourcedown_sqe")
    @Column(name = "id")
    public String id;

    @ManyToOne
    @JoinColumn(name="resource_id")
    @Field(displayName = "资源id")
    public Resource resource;

    @ManyToOne
    @JoinColumn(name="MEMBERID")
    @Field(displayName = "用户")
    public UserBase userBase;

    @Column(name="CREATE_DATE")
    @Field(displayName = "时间")
    public Date createDate;


    public static Page<Resource> findPage(UserBase userBase, Integer page, Integer pageSize) {
        String hql = "from Resource where userBase = ? order by createDate desc " ;
        List<Resource> list= find(hql,userBase).fetch(page, pageSize);
        if(list.size() == 0){
            return new Page<Resource>(page, pageSize, 0, list);
        }
        long totalCount = count("select count(*)" + hql,userBase);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }
}
