package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * User: Liujy
 * Date: 13-2-17
 * Time: 下午3:15
 */
@Entity
@Table(name = "news_channle")
@SequenceGenerator(name = "NEWS_CHANNLE_SEQ", sequenceName = "news_channle_seq", allocationSize = 1)
public class NewsChannel  extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_CHANNLE_SEQ")
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "state")
    public int state = Status.OK;

    @Override
    public String toString() {
        return name;
    }

    public static NewsChannel findById(Long id){
        return find("ById",id).first();
    }
}
