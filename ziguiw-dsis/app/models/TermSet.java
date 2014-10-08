package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-28
 * Time: A.M.11:35
 */
@Entity()
@Table(name = "T_TERM_SET")
@Form(displayName = "学期")
@SequenceGenerator(name="T_TERM_SET_SEQ", sequenceName="T_TERM_SET_SEQ",allocationSize = 1)
public class TermSet extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_TERM_SET_SEQ")
    @Column(name = "term_id")
    public Integer termId;

    @Column(name="xxid")
    @Field(displayName = "学校ID")
    public String xxid;
    
    @Column(name="term_name")
    @Field(displayName = "学期名称")
    public String termName;

    @Column(name="term_year")
    @Field(displayName = "学年")
    public String termYear;

    @Column(name="holiday_begin")
    @Field(displayName = "学期开始日期")
    public Date holidayBegin;

    @Column(name="holiday_end")
    @Field(displayName = "学期结束日期")
    public Date holidayEnd;

    public static List<Exam> findExam(Integer termId,Integer bjId){
        Clazz clazz=Clazz.findById(bjId);
        List<Exam> tempList= TermSet.find("select e from Exam e where e.termId = ? and e.xxId=? ", termId+"", clazz.xxId).fetch();
        List<Exam> examList=new ArrayList<Exam>();
        if( tempList != null && tempList.size() != 0){
            for( int i = 0;i< tempList.size();i++){

                if( tempList.get(i).bjIdStr.indexOf(clazz.bjId.toString()) != -1 ){
                    examList.add(tempList.get(i));
                }
            }
        }
        return examList;
    }

    public static List<TermSet> findAllBySchool(String xxId) {
            return  TermSet.find("byXxId",xxId).fetch();
    }
}
