package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-19
 * Time: 下午5:34
 */
@Entity
@Table(name = "t_stugrade")
@Form(displayName = "年级")
@SequenceGenerator(name = "t_stugrade_seq", sequenceName = "t_stugrade_seq", allocationSize = 1, initialValue = 100000)
public class SchoolStugrade extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_stugrade_seq")
    @Column(name = "nj_id", nullable = false)
    public Long njId;

    @Column(name = "NJ_MCHENG")
    @Field(displayName = "年级名称")
    public String name;

    @Column(name = "NJ_ZTAI")
    @Field(displayName = "年级状态")
    public Integer status;

    @Column(name = "xxid")
    @Field(displayName = "学校id")
    public String xxId;

    public static List<SchoolStugrade> findByXxid(String xxId){
            return find("from SchoolStugrade where xxId = ? and status = ? order by njId desc",
                    xxId , 51).fetch();
    }
}
