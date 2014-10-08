package models;

import controllers.CRUD;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-18
 * Time: 下午2:49
 */
@Entity
@Form(displayName = "班级论坛")
@Table(name="S_CLASS_REPLY")
@SequenceGenerator(name="S_CLASS_REPLY_SEQ", sequenceName="S_CLASS_REPLY_SEQ", allocationSize=1, initialValue = 100000)
public class ClassReply extends JPASupport{


        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CLASS_REPLY_SEQ")
        public Long id;

        @Column(name = "FORUM_ID")
        public long forumId;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        @Field(displayName = "用户")
        public UserBase user;

        @Column(name = "create_time", columnDefinition = "date", nullable = false)
        @CRUD.Exclude
        public Date createTime = new Date();

        @Column(name = "content")
        @Field(displayName = "内容")
        public String content;

        public static Page<ClassReply> findPage(long forumId,int page,int pageSize){
            List<ClassReply> list = find("select r from ClassReply r where r.forumId = ? order by r.createTime asc",
                    forumId).fetch(page,pageSize);
            long count = count("select count(*) from ClassReply r where r.forumId = ?",forumId);
            return new Page<ClassReply>(page,pageSize,count,list);
        }
}
