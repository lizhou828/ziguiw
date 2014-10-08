package models;

import play.data.validation.MaxSize;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: pengchangguo
 * Date: 13-1-9
 * Time: P.M 4:32
 */
@Entity
@Form(displayName = "学校信息")
@Table(name="S_SCHOOL_EXT")
public class SchoolExtend extends JPASupport {

    @Column(name = "school_id", nullable = false)
    @Id
    public Long schoolId;

    @Column(name = "logo", length = 1000)
    public String logo;

    @Column(name = "banner", length = 1000)
    public String banner;

    @Lob
    @Basic(fetch= FetchType.EAGER, optional=true)
    @Column(name = "description")
    @Field(displayName = "学校描述")
    public String description;


    @Column(name = "addr", length = 255)
    @Field(displayName = "学校地址")
    @MaxSize(value = 100)
    public String addr;

    @Column(name = "email", length = 255)
    @Field(displayName = "邮箱")
    @MaxSize(value = 100)
    public String email;


    @Column(name = "tel", length = 255)
    @Field(displayName = "联系方式")
    public String tel;

    @Column(name = "postcode", length = 255)
    @Field(displayName = "邮编")
    @MaxSize(value = 100)
    public String postcode;

    @Column(name = "site", length = 255)
    @Field(displayName = "学校网址")
    @MaxSize(value = 100)
    public String site;

    public static SchoolExtend findBySchoolId(Long id) {
        return find("select s from SchoolExtend s where s.schoolId = ?", id).first();
    }

    @Override
    public String toString() {
        School school = School.findById(schoolId);
        return school != null ? school.name : String.format("%s(%s)", this.getClass().getName(), schoolId);
    }
}
