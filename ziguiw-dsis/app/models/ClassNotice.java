package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-30
 * Time: A.M.9:47
 */
@Entity()
@Table(name = "common_message")
@Form(displayName = "班级通知")
@SequenceGenerator(name = "COMMON_MESSAGE_SQE",sequenceName = "COMMON_MESSAGE_SQE",allocationSize = 1)
public class ClassNotice extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMMON_MESSAGE_SQE")
    @Column(name = "id")
    public long id;

    @Column(name="from_user_id")
    @Field(displayName = "发送人")
    public long fromUserId;

    @Column(name="to_user_id")
    @Field(displayName = "接收人")
    public long toUserId;

    @Column(name="title")
    @Field(displayName = "标题")
    public String title;

    @Column(name="text")
    @Field(displayName = "内容")
    public String text;

    @Column(name="kind")
    @Field(displayName = "类型")
    public int kind;    // 7 stand for class notice

    @Column(name="state")
    @Field(displayName = "状态")
    public int state;   // the value equals 1 ,means not deleted

    @Column(name="class_id")
    @Field(displayName = "班级id")
    public String classId;

    @Column(name="create_time")
    @Field(displayName = "创建时间")
    public Date createTime;

    @Column(name="last_modify_time")
    @Field(displayName = "最后修改时间")
    public Date lastModifyTime;


    public static Page<ClassNotice> findPageByBjId(Integer bjId, String startTime, String endTime, Integer page, Integer pageSize) {
        String hql = "from ClassNotice  where kind = 7 and state = 1 ";
        if(bjId != null && bjId > 0 ) {
            hql += " and classId = "+ bjId.toString();
        }
        if (startTime != null ) {
            hql += " and createTime >= to_date('" + startTime +"','yyyy-mm-dd')";
        }
        if (endTime != null) {
            hql += " and createTime <= to_date('" + endTime +"','yyyy-mm-dd')";
        } else {
            return new Page(page,pageSize,0,null);
        }
        hql += " order by createTime desc";

        List<ClassNotice> data = find(hql).fetch(page,pageSize);
        if(data.size() == 0) return new Page<ClassNotice>(page,pageSize,0,data);
        long totalCount = count("select count(*) " + hql);
        return new Page<ClassNotice>(page,pageSize,totalCount,data);
    }
}
