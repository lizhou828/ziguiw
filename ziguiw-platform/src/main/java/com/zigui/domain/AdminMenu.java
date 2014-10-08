package com.zigui.domain;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-25
 * Time: 上午9:36
 */
public class AdminMenu {
    private long id;
    private long parentId;
    private String value;
    private String url;

    public AdminMenu(long id,long parentId,String value,String url)
    {
        this.id = id;
        this.parentId = parentId;
        this.value = value;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
