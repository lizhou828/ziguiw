package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Liujy
 * Date: 13-1-27
 * Time: 下午12:00
 */
@Entity
@Table(name = "knowledge_channle")
@SequenceGenerator(name = "KNOWLEDGE_CHANNLE_SEQ", sequenceName = "knowledge_channle_seq", allocationSize = 1)
public class KnowledgeChannel extends JPASupport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "KNOWLEDGE_CHANNLE_SEQ")
    public Long id;

    @Column(name = "name", length = 255)
    public String name;

    @Column(name = "state")
    public int state = Status.OK;

    @Override
    public String toString(){
        return  this.name;
    }

    public static KnowledgeChannel findById(Long id){
        return find("ById",id).first();
    }

}
