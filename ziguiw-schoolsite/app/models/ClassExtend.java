package models;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.Boolean;
import org.hibernate.annotations.Formula;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 上午9:47
 */
@Entity
@Form(displayName = "班级信息")
@Table(name="S_CLASS_EXT")
@SequenceGenerator(name="S_CLASS_EXT_SEQ", sequenceName="S_CLASS_EXT_SEQ", allocationSize=1, initialValue = 100000)
public class ClassExtend extends JPASupport{

    @Column(name = "class_id", nullable = false)
    @Id
    public Long classId;

    @Formula(value = "(select sc.bj_mcheng from t_classes sc where sc.bj_id = class_id)")
    @Field(displayName = "班级")
    public String className;

    @Column(name = "banner", length = 1000)
    @Field(displayName = "banner")
    public String banner;


    @Column(name = "hot")
    @Field(displayName = "是否推荐")
    public Integer hot = Boolean.NO;

    @Column(name = "color")
    @Field(displayName = "班级页面背景颜色")
    public int color = ClassTemplateColor.BLUE;

    public static ClassExtend findByClassId(Long classId) {
            return find("from ClassExtend where classId = ?",classId).first();
    }
}
