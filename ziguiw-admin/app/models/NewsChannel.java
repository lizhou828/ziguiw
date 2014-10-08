package models;

import com.arj.ziguiw.common.Status;
import controllers.CRUD;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: pengcg
 * Date: 13-1-24
 * Time: A.M 10:58
 */
@Entity
@Table(name = "news_channle")
@SequenceGenerator(name = "NEWS_CHANNLE_SEQ", sequenceName = "news_channle_seq", allocationSize = 1)
@Form(displayName = "新闻频道")
public class NewsChannel extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_CHANNLE_SEQ")
    public long id;

    @Column(name = "name")
    @Field(displayName = "名称")
    public String name;

    @Column(name = "state")
    @Field(displayName = "状态")
    @CRUD.Exclude
    public int state = Status.OK;

    @Override
    public String toString() {
        return name;
    }
}
