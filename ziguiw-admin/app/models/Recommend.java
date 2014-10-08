package models;

import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-24
 * Time: 上午11:11
 */
@Entity
@Form(displayName = "推荐")
@Table(name="RECOMMEND")
@SequenceGenerator(name="RECOMMEND_SEQ", sequenceName="RECOMMEND_SEQ", allocationSize=1)
public class Recommend extends JPASupport{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECOMMEND_SEQ")
    public Long id;

    @Column(name = "area" , nullable = false)
    @Field(displayName = "推荐区域")
    public String area;

    @Column(name = "title", length = 255)
    @Field(displayName = "标题")
    public String title;

    @Column(name = "url", length = 1000)
    @Field(displayName = "图片")
    public String url;

    @Column(name = "link", length = 1000)
    @Field(displayName = "链接")
    public String link;

    @Column(name = "description", length = 2000)
    @Field(displayName = "描述")
    public String description;

    @Column(name = "object_id", length = 18)
    @Field(displayName = "关联对象ID")
    public String objectId;

    @Column(name = "module_name")
    @Field(displayName = "所属模块名称")
    public String moduleName;

    @Column(name = "module_id")
    @Field(displayName = "所属模块ID")
    public String moduleId;

    @Column(name = "create_user", length = 18)
    @Field(displayName = "推荐人ID")
    public String createUser;

    @Column(name = "create_username", length = 255)
    @Field(displayName = "推荐人名称")
    public String createUserName;

    @Column(name = "create_time")
    @Field(displayName = "推荐时间")
    public Date createTime = new Date();

}
