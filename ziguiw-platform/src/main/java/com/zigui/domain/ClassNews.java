package com.zigui.domain;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liumengjie
 * Date: 12-12-18
 * Time: PM3:24
 */
@Entity
@Table(name ="s_class_news")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ClassNews implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="CONTENT")
    private String content;

    @Column(name="CLASS_ID")
    private Long class_id;

    @Column(name="XXID")
    private String xx_id;

    @Column(name="TYPE")
    private Long type;

    @Column(name="CREATE_TIME")
    private Date create_time;

    @Column(name="STATUS")
    private Long status;

    @Column(name="AUTO_DESCRIPTION")
    private String autoDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public String getXx_id() {
        return xx_id;
    }

    public void setXx_id(String xx_id) {
        this.xx_id = xx_id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getAutoDescription() {
        return autoDescription;
    }

    public void setAutoDescription(String autoDescription) {
        this.autoDescription = autoDescription;
    }
}
