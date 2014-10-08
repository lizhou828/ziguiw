package models;
import play.db.jpa.JPASupport;

import javax.persistence.*;
/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-5-10
 * Time: A.M.10:58
 * 资源类型
 */
@Entity
@Table(name="r_type")
@SequenceGenerator(
        name="R_TYPE_SEQ",
        sequenceName="r_type_seq",
        allocationSize=1
)
public class ResourceType extends JPASupport {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_TYPE_SEQ")
    public Long id;

    @Column(name = "type_name")
    public String typeName;

    @Column(name = "suffix")
    public String suffix;
}
