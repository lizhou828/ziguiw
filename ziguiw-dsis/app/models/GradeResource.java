package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;


/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-10
 * Time: A.M.10:50
 * 年级表 : 跟资源有关的年级
 */
@Entity
@Table(name="r_grade")
@SequenceGenerator( name="R_GRADE_SEQ",sequenceName="r_grade_seq",allocationSize=1)
@Form(displayName = "年级")
public class GradeResource extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_GRADE_SEQ")
    @Column(name = "id")
    public Long id;

    @Column(name = "grade_name")
    @Field(displayName = "年级名称")
    public String gradeName;

    @Column(name = "leve")
    @Field(displayName = "级别")
    public Integer leve;//1:小学 2:初中 3:高中

    @Column(name = "srcid")
    public String srcId;

    public static GradeResource findByName (String gradeName){
        String hql = "from GradeResource where  gradeName = ?";
        GradeResource gradeResource = find(hql, gradeName).first();
        if(gradeResource == null ){
            return null;
        }else {
            gradeResource.srcId= gradeResource.srcId.trim();
            return gradeResource;
        }

    }

//    public static void main(String [] args){
//        String passwd = DigestUtils.md5Hex("9206065");
//        System.out.print(passwd);
//
//    }




}