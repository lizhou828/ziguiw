package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:14
 */
@Entity
@Table(name="r_type")
@SequenceGenerator(
        name="R_TYPE_SEQ",
        sequenceName="r_type_seq",
        allocationSize=1
)
public class Type extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_TYPE_SEQ")
    public Long id;

    @Column(name = "type_name")
    public String typeName;

    @Column(name = "suffix")
    public String suffix;


    public static Page<Resource> findByTypeId(long id ,int page,int pageSize){
        String hql="from Resource r where r.type.id=?";
        long count=count("select count(r) from Resource r where r.type.id=?",id);
        List<Resource> list=find(hql,id).fetch(page,pageSize);
        return new Page<Resource>(page,pageSize,count,list);

    }
    public static List<Resource> findByTypeName(String typeName,Integer length) {
        String hql = "select r from Resource r where r.state = ? and r.type.typeName = ? " +
                "order by r.createTime desc";
        return Resource.find(hql, Status.OK,typeName).fetch(1,length);
    }


    public static Type findByName(String name) {
        return find("byTypeName",name).first();
    }
}
