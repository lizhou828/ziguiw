package com.zigui.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-12-18
 * Time: PM3:35
 */
@Entity
@Table(name="s_class_photo ")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClassPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="URL")
    private String url;

    @Column(name="TITLE")
    private String title;

    @Column(name="TEXT")
    private String text;

    @Column(name="TYPE")
    private Long type;

    @Column(name="STATUS")
    private Long status;

    @Column(name="CLASS_ID")
    private Long class_id;

    @Column(name="CREATE_TIME")
    private Date create_time;

    @Column(name="XXID")
    private String xx_id;

    @Column(name="COVER")
    private Long cover;

    @Column(name="AUTO_DESCRIPTION")
    private String autoDescription;

    @Column(name = "ISRECOMMEND")
    private Long isRecommend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCover() {
        return cover;
    }

    public void setCover(Long cover) {
        this.cover = cover;
    }

    public String getXx_id() {
        return xx_id;
    }

    public void setXx_id(String xx_id) {
        this.xx_id = xx_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getAutoDescription() {
        return autoDescription;
    }

    public void setAutoDescription(String autoDescription) {
        this.autoDescription = autoDescription;
    }

    public Long getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Long isRecommend) {
        this.isRecommend = isRecommend;
    }
}
