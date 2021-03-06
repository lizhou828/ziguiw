package com.zigui.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-13
 * Time: 下午5:03
 * this code should be generated by hibernateTool
 */
@Entity
@Table(name = "news_audit")
@SequenceGenerator(
        name="NEWS_AUDIT_SEQUENCE" ,
        sequenceName = "news_audit_sequence",
        allocationSize = 1
)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsAudit {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="NEWS_AUDIT_SEQUENCE")
    private long id;

    @Column(name="news_id")
    private long newsId;

    @Column(name="reject_reason")
    private String rejectReason;

    @Column(name="creator_id")
    private long creatorId;

    @Column(name="creator_nick")
    private String creatorNick;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "class_news_id")
    private long classNewsId;

    @Column(name = "school_news_id")
    private long schoolNewsId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorNick() {
        return creatorNick;
    }

    public void setCreatorNick(String creatorNick) {
        this.creatorNick = creatorNick;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getClassNewsId() {
        return classNewsId;
    }

    public void setClassNewsId(long classNewsId) {
        this.classNewsId = classNewsId;
    }

    public long getSchoolNewsId() {
        return schoolNewsId;
    }

    public void setSchoolNewsId(long schoolNewsId) {
        this.schoolNewsId = schoolNewsId;
    }
}
