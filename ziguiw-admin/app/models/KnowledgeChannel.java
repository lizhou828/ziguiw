package models;

import com.arj.ziguiw.common.Status;
import controllers.CRUD;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Liujy
 * Date: 13-1-27
 * Time: 下午12:00
 */
@Entity
@Table(name = "knowledge_channle")
@SequenceGenerator(name = "KNOWLEDGE_CHANNLE_SEQ", sequenceName = "knowledge_channle_seq", allocationSize = 1)
@Form(displayName = "知识频道")
public class KnowledgeChannel extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KNOWLEDGE_CHANNLE_SEQ")
    @CRUD.Exclude
    public Long id;

    @Column(name = "name", length = 255)
    @Field(displayName = "名称")
    public String name;

    @Column(name = "state")
    @Field(displayName = "状态")
    @CRUD.Exclude
    public int state = Status.OK;



    @Override
    public String toString(){
        return  this.name;
    }

}
