package models;

import play.data.validation.Required;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-26
 * Time: A.M.9:56
 */
@Entity()
@Table(name = "grow_footmark")
@Form(displayName = "成长足迹")
@SequenceGenerator(name = "GROW_FOOTMARK_SQE",sequenceName = "GROW_FOOTMARK_SQE",allocationSize = 1)
public class GrowFootprint extends JPASupport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "GROW_FOOTMARK_SQE")
    @Column(name = "id")
    public long id;

    @Column(name="xs_id", columnDefinition = "number(10)")
    public Long xsId;

    @Column(name="bj_id", columnDefinition = "number(10)")
    public Integer bjId;

    @Column(name="title")
    @Field(displayName = "标题")
    @Required
    public String title;

    @Column(name="text")
    @Field(displayName = "内容")
    @Required
    public String text;

    @Column(name="description")
    @Field(displayName = "描述")
    @Required
    public String description;

    @Column(name="keyword")
    @Field(displayName = "关键字")
    public String  keyword;

    @Column(name="type")
    @Field(displayName = "类型")
    public int type;

    @Column(name="state")
    @Field(displayName = "状态")
    public int state=1;  //0：删除     1：未删除，

    @Column(name="creator_nick")
    @Field(displayName = "创建者")
    @Required
    public String creatorNick;

    @Column(name="create_time")
    @Field(displayName = "创建时间")
    @Required
    public Date createTime = new Date();

    @Column(name="last_modify_nick")
    @Field(displayName = "最后修改人")
    public String lastModifyNick;

    @Column(name="last_modify_time")
    @Field(displayName = "最后修改时间")
    public Date lastModifyTime;

    @Column(name="view_count")
    @Field(displayName = "浏览次数")
    public Integer viewCount;

    @Column(name="click_count")
    @Field(displayName = "点击次数")
    public long clickCount;

    @Column(name = "place",columnDefinition = "varchar2(64)")
    @Field(displayName = "地点")
    public String place;

    @Column(name = "result",columnDefinition = "varchar2(128)")
    @Field(displayName = "结果")
    public String result;


    public static Page findPage(Integer bjId, Integer xsId, String startTime, String endTime, Integer page, Integer pageSize) {
        String hql = "from GrowFootprint where 1=1 ";
        if (bjId != null && bjId > 0) {
            hql += "and bjId = " +bjId ;
        }
        if (xsId != null && xsId >0 ) {
            hql += "and xsId = " + xsId;
        }
        if (startTime != null ) {
            hql += "and createTime >= to_date('" + startTime +"','yyyy-mm-dd')";
        }
        if (endTime != null) {
            hql += "and createTime >= to_date('" + endTime +"','yyyy-mm-dd')";
        } else {
            return new Page(page,pageSize,0,null);
        }
        List<GrowFootprint> data = find(hql).fetch(page, pageSize);
        long totalCount = count("select count(*) " + hql);
        return new Page(page, pageSize, totalCount, data);
    }

    public static Page<GrowFootprint> findByXsId(Long xsId,Integer page,Integer pageSize) {
        String hql = "from GrowFootprint where xsId = ? order by createTime desc ";
        List<GrowFootprint> data = find(hql,xsId).fetch(page, pageSize);
        long totalCount = count("select count(*) " + hql,xsId);
        return new Page(page, pageSize, totalCount, data);
    }

    public static void deleteById(Long id) {
        String hql = "delete from GrowFootprint where id = ?";
        GrowFootprint.delete(hql,id);
    }
}
