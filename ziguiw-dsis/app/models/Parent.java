package models;

import com.arj.ziguiw.common.UserBaseType;
import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 13-1-16
 * Time: 上午10:06
 */

@Entity()
@Table(name = "t_parentinfo")
@Form(displayName = "家长")
@SequenceGenerator(name = "T_PARENTINFO_SQE",sequenceName = "T_PARENTINFO_SQE",allocationSize = 1)
public class Parent extends JPASupport{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "T_PARENTINFO_SQE")
    @Column(name = "parents_id")
    public Long parentId;

    @Column(name = "xs_id")
    @Field(displayName = "孩子")
    @Required
    public long xsId;

    @Column(name = "parents_name")
    @Field(displayName = "家长姓名")
    @Required
    public String parentName;

    @Column(name = "mobliephone")
    @Field(displayName = "电话")
    @Required
    public String mobilePhone;

    @Column(name = "account_state")
    public Integer accountState;//1 为正常，0为已删除

    @Override
    public String toString(){
        return String.format("%s[%s,%s,%s]",mobilePhone,parentId,parentName,xsId);
    }

    //找到所有的孩子
    public static List<Student> findChildrenByMobilePhone(String mobilePhone){
        return find("select t from Student t where t.xsId in (select p.xsId from Parent p where (p.accountState != 0 or p.accountState is null) and p.mobilePhone = ? )",mobilePhone).fetch();
    }

    //找到交了年费的孩子
    public static List<Student> findPayedChildren(UserBase userBase) {
        String sql = String.format("select p from PayHistory p where p.state = 1 and p.type = 2 and p.userBase =%s ",userBase.id);
        List <PayHistory> payHistories= find(sql).fetch();
        List <Student> studentList = new ArrayList<Student>();
        if(payHistories.size() != 0 ){
            for(int i = 0 ; i < payHistories.size(); i++){
                long xsId = payHistories.get(i).xsId;
                Student student = Student.findById(xsId);
                studentList.add(student);
            }
        }
        return studentList;
    }
    // 根据学生id,判断该孩子是否交过年费
    public static boolean findPayedChildren(UserBase userBase,Long xsId) {
        String sql = "";
        if(userBase != null && userBase.type == UserBaseType.STUDENT){
            sql = String.format("select p from PayHistory p where p.state = 1 and p.type =2 " +
                    "and xsId =%s",xsId);
        } else {
            sql = String.format("select p from PayHistory p where p.state = 1 and p.type =2 and p.userBase =%s " +
                    "and xsId =%s",userBase.id,xsId);
        }

        List <PayHistory> payHistories= find(sql).fetch();
        if(payHistories != null && payHistories.size() != 0){
            return true;
        }
        return false;
    }

    public static boolean findPayedChildren(Student student) {
        String sql = String.format("select p from PayHistory p where p.state = 1 and p.type =2 and xsId =%s",student.xsId);
        List <PayHistory> payHistories= find(sql).fetch();
        if(payHistories != null && payHistories.size() != 0){
            return true;
        }
        return false;
    }

    //找出没有交年费的孩子
    public static List<Student> findNotPayedChild(UserBase userBase){
        List <Student> studentList = findChildrenByMobilePhone(userBase.nickName);
        List <Student> studentListPayed = findPayedChildren(userBase);
        List <Student> studentListNotPayed = new ArrayList<Student>();
        boolean flag;
        if(studentListPayed.size() != 0 ){
            for(int i = 0 ;i < studentList.size(); i++){
                flag = true;
                for(int j = 0;j <studentListPayed.size(); j++){
                    if(studentList.get(i).xsId == studentListPayed.get(j).xsId){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    studentListNotPayed.add(studentList.get(i));
                }
            }
        }
        return studentListNotPayed;
    }



}
