package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-10
 * Time: A.M.10:44
 *
 * 科目表
 * 跟资源有关的科目设置
 */
@Entity
@Table(name="r_subject")
@SequenceGenerator( name="R_SUBJECT_SEQ", sequenceName="r_subject_seq",allocationSize=1)
public class Subject extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_SUBJECT_SEQ")
    @Column(name = "id")
    public Long id;

    @Column(name = "subject_name")
    public String subjectName;

    @Column(name = "srcid")
    public String srcId;

    public static Subject findByName(String subjectName){
        String hql = "from Subject where subjectName =?";
        Subject subject = find(hql,subjectName).first();
        if(subject  == null ){
            return null;
        }else {
            subject.srcId = subject.srcId.trim();
            return subject;
        }

    }


}