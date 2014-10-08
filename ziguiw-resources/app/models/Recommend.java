package models;

import com.arj.ziguiw.common.RecommendArea;
import org.apache.commons.lang.StringUtils;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: administrator
 * Date: 13-2-25
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="RECOMMEND")
@SequenceGenerator(name="RECOMMEND_SEQ", sequenceName="RECOMMEND_SEQ", allocationSize=1, initialValue = 100000)
public class Recommend extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECOMMEND_SEQ")
    public Long id;

    @Column(name = "area" , nullable = false)
    public String area;

    @Column(name = "title", length = 255)
    public String title;

    @Column(name = "url", length = 1000)
    public String url;

    @Column(name = "link", length = 1000)
    public String link;

    @Column(name = "description", length = 2000)
    public String description;

    @Column(name = "object_id", length = 18)
    public String objectId;

    @Column(name = "module_name")
    public String moduleName;

    @Column(name = "module_id")
    public String moduleId;

    @Column(name = "create_user", length = 18)
    public String createUser;

    @Column(name = "create_username", length = 255)
    public String createUserName;

    @Column(name = "create_time")
    public Date createTime = new Date();

    public static List<Recommend> findByArea(String area) {
        String hql = "select r from Recommend r where r.area = ? order by r.objectId desc";
        return  find(hql, area).fetch(RecommendArea.countMap.get(area));
    }

    /**
     * get the link of recommend record, if recommend.link is null or empty, return the customize link
     * @param link customize link
     * @return link
     */
    public String link(String link) {
        if (StringUtils.isNotBlank(this.link)) return this.link;
        return link;
    }

}

