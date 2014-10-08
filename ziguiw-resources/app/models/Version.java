package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:12
 */
@Entity
@Table(name="r_version")
@SequenceGenerator(
        name="R_VERSION_SEQ",
        sequenceName="r_version_seq",
        allocationSize=1
)
public class Version extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_VERSION_SEQ")
    public Long id;

    @Column(name = "version_name")
    public String versionName;

    @Column(name = "flag")
    public Integer flag;

    public static Page<Resource> findByVersion(long id , int page , int pageSize){
        String hql="from Resource r where r.version.id=?";
        long count = count("select count(r) from Resource r where r.version.id = ?",id);
        List<Resource> list = find(hql,id).fetch(page,pageSize);
        return  new Page(page,pageSize,count,list);
    }
    public static List<Resource>  recomend(int count){
        String hql="from Resource r order by r.createTime desc " ;
        return find(hql).fetch(count);
    }

    public static List<Resource>  hot(int count){
        String hql="from Resource r order by r.resourceDownNumber desc";
        return find(hql).fetch(count);
    }

    public static Version findByName(String versionName) {
        return find("byVersionName",versionName).first();
    }

    public static List<Version> findByFlag() {
        String hql = "from Version v where v.flag is null";
        return find(hql).fetch();
    }
}


