package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 上午10:05
 */
@Entity()
@Table(name = "t_teacherinfo")
@Form(displayName = "老师")
@SequenceGenerator(name = "T_TEACHERINFO_SQE",sequenceName = "T_TEACHERINFO_SQE",allocationSize = 1)
public class Teacher extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_TEACHERINFO_SQE")
    @Column(name = "teacherid")
    public Integer teacherId;

    @Column(name = "name")
    @Field(displayName = "姓名")
    @Required
    public String name;

    @Column(name = "moblie")
    @Field(displayName = "电话")
    public String mobilePhone;

    @Column(name = "xxid")
    @Field(displayName = "学校ID")
    @Required
    public String xxId;

    @Override
    public String toString(){
        return String.format("%s[%s,%s,%s]",name,teacherId,mobilePhone,xxId);
    }

    public static List<Student> findStudentByClazzId(Integer bjId){
          return find("select t from Student t where t.bjId= ? ",bjId).fetch();
    }



    public static List<Clazz> findClazz(Integer teacherId){
        return find("select c from Clazz c where c.bjId in" +
                "(select tc.bjId from TeacherClazz tc where tc.teacherId = ?)",teacherId).fetch();
    }

    public static Integer countStudentNumber(Integer teacherId){
        List<Clazz> clazzList=findClazz(teacherId);
        List<Student> studentList;
        Integer studentNumber=0;
        for(int i=0;i<clazzList.size();i++){
            studentList=findStudentByClazzId(clazzList.get(i).bjId);
            studentNumber+=studentList.size();
        }
        return studentNumber;
    }

    public static Page<Student> pageQueryStudentByBjId(Integer page,Integer pageSize,Integer bjId){
        List<Student> studentList=Teacher.find("select t from Student t where t.bjId= ? ",bjId).fetch(page,pageSize);
        long totalCount= Teacher.count("select count(t) from Student t where t.bjId= ? ",bjId);
        return new Page<Student>(page,pageSize,totalCount,studentList);

    }






}
