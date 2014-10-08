package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.service.impl.SchoolListServiceImpl;
import com.zigui.utils.Page;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-19
 * Time: 下午2:38
 */
public class SchoolListAction {

    @Autowired
    protected SchoolListServiceImpl schoolListService;

    private int pageNum;

    private int currentPage = 1;

    private int pageSize;

    private Page<List> page;

    private String pageString;


    public String getSchoolList(){
        page = schoolListService.getSchoolList(currentPage,pageSize);
        pageString = page.getPageString(ServletActionContext.getRequest().getQueryString());
        return Action.SUCCESS;
    }

    public String getSchoolInfoList(){
        page = schoolListService.getSchoolInfoList(pageNum, pageSize);
        return Action.SUCCESS;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Page<List> getPage() {
        return page;
    }

    public void setPage(Page<List> page) {
        this.page = page;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
