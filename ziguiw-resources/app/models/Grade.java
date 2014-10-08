package models;

import play.db.jpa.JPASupport;


import javax.persistence.*;
import java.util.List;


/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:06
 */
@Entity
@Table(name="r_grade")
@SequenceGenerator(
        name="R_GRADE_SEQ",
        sequenceName="r_grade_seq",
        allocationSize=1
)
public class Grade extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_GRADE_SEQ")
    public Long id;

    @Column(name = "grade_name")
    public String gradeName;

    @Column(name = "leve")
    public Integer leve;//1:小学 2:初中 3:高中



    public static Page<Resource> findByGrade(long id ,int page,int pageSize){
        String hql="from Resource r where r.grade.id=?";
        long count=count("select count(r) from Resource r where r.grade.id=?",id);
        List<Resource> list=find(hql,id).fetch(page,pageSize);
        return new Page<Resource>(page,pageSize,count,list);

    }

    public static List<Resource>  recomend(int count){
        String hql="from Resource r order by r.createTime desc " ;
        return find(hql).fetch(count);
    }

    public static List<Resource>  hot(int count){
        String hql="from Resource r order by r.resourceDownNumber desc";
        return find(hql).fetch(count);
    }


    public static Grade findByName(String name) {
        return find("byGradeName",name).first();
    }
}

