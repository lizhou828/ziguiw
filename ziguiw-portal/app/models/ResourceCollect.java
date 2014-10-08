package models;

import com.arj.ziguiw.common.Status;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-4-12
 * Time: 下午2:49
 */
@Entity
@Table(name = "r_collect")
@SequenceGenerator(name = "R_COLLECT_SEQ",
        sequenceName = "r_collect_seq",
        allocationSize = 1)
public class ResourceCollect extends JPASupport{


        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "R_COLLECT_SEQ")
        public long id;

        @Column(name = "title")
        public String title;

        @Column(name = "object_id")
        public String objectId;

        @Column(name = "r_type")
        public String resourceType;

        @Column(name = "status")
        public int status = Status.OK;

        @OneToOne(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
        @JoinColumn(name="user_id")
        public UserBase user;

        @Column(name = "create_time")
        public Date createTime = new Date();

        public static Page findByUserId(Long userId , Integer page, Integer pageSize){
            List<ResourceCollect>  list = find("from ResourceCollect c where c.user.id = ? and c.status = ? order by c.createTime desc",userId,Status.OK).fetch(page, pageSize);
            long count = count("select count(c) from ResourceCollect c where c.user.id = ? and c.status = ?",userId,Status.OK);
            return new Page(page, pageSize, count, list);
        }

       public  static List<ResourceCollect> findByUserId(Long userId){
           return find("from ResourceCollect where user.id = ? and status = ? order by createTime desc",userId,Status.OK).fetch();
       }

       public static Page findSearchTitle(Long userId, String condition, Integer page, Integer pageSize){
           List<ResourceCollect> list = find("from ResourceCollect c where c.user.id = ? and c.status = ? and c.title like '%" +
                   condition + "%'order by c.createTime desc",userId,Status.OK).fetch(page, pageSize);
           Long count = count("select count(c) from ResourceCollect c where c.user.id = ? and c.status = ? and c.title like '%" +
                   condition + "%'order by c.createTime desc",userId,Status.OK);
           return new Page(page,pageSize,count,list);
       }
}
