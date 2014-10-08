package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:09
 */
@Entity
@Table(name="r_subject")
@SequenceGenerator(
        name="R_SUBJECT_SEQ",
        sequenceName="r_subject_seq",
        allocationSize=1
)
public class Subject extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_SUBJECT_SEQ")
    public Long id;

    @Column(name = "subject_name")
    public String subjectName;

    public static Page<Resource> findBySubjectId(long id, Integer page, Integer pageSize){
        String hql="from Resource r where r.subject.id=?";
        long count=count("select count(r) from Resource r where r.subject.id=?",id);
        List<Resource> list=find(hql,id).fetch(page,pageSize);
        return new Page<Resource>(page,pageSize,count,list);

    }

    public static List<Resource> findBySubjectIdAndLeve(Long subjectId, Integer leve) {
        String hql = "select r from Resource r where r.state = ? and r.subject.id = ? and r.grade.leve = ?" +
                " order by r.createTime desc";
        List<Resource> list = Resource.find(hql, Status.OK,subjectId,leve).fetch(1,12);
        return list;
    }

    @Override
    public String toString() {
        return subjectName;
    }
}
