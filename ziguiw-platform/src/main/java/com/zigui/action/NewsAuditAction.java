package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.NewsAudit;
import com.zigui.service.INewsExService;
import com.zigui.service.impl.ClassInfoServiceImpl;
import com.zigui.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-14
 * Time: 上午11:41
 */
public class NewsAuditAction extends BaseAction {

    private long newsId;
    private NewsAudit newsAudit;

    @Autowired
    private INewsExService newsExService;
    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    private ClassInfoServiceImpl classInfoService;

    private List<NewsAudit> newsAuditList;

    private long classNewsId;
    private long schoolNewsId;

    public NewsAuditAction() {
    }

    public String view() {
        return "view";
    }

    public String recordAuditInfo() {
        if(this.newsAudit.getClassNewsId() != 0)
        {
            //audit classNews
            classInfoService.auditFail(this.newsAudit.getClassNewsId());
            newsExService.saveNewsAudit(this.newsAudit);
            return Action.SUCCESS;
        }
        if(this.newsAudit.getSchoolNewsId() != 0)
        {
            //audit school news
        }
        if (this.newsAudit.getNewsId() != 0) {
            newsService.approveNews(new Long[]{this.newsAudit.getNewsId()}, -2);//bad code here,
            newsExService.saveNewsAudit(this.newsAudit);
            return Action.SUCCESS;
        }
        throw new RuntimeException("property of newsAudit.newsId not setted!");
    }

    public String viewRejectReason() {
        if(this.newsId != 0)
        {
        this.newsAuditList = newsExService.getNewsAudit(this.newsId);
        }
        if(this.classNewsId !=0)
        {
            this.newsAuditList = newsExService.getNewsAuditByClassNewsId(this.classNewsId);
        }
        if(this.schoolNewsId != 0)
        {
            this.newsAuditList = newsExService.getNewsAuditBySchoolNewsId(this.schoolNewsId);
        }
        return "reasonView";
    }

    public NewsAudit getNewsAudit() {
        return newsAudit;
    }

    //injected by struts
    public void setNewsAudit(NewsAudit newsAudit) {
        this.newsAudit = newsAudit;
        /**
         * this code below should be configured in pojo,think about the solution
         */
        this.newsAudit.setCreateTime(new Date());
        this.newsAudit.setCreatorId(super.admin.getId());
        this.newsAudit.setCreatorNick(super.admin.getNickName());
    }

    public long getNewsId() {
        return newsId;
    }

    //injected by struts
    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public INewsExService getNewsExService() {
        return newsExService;
    }

    public void setNewsExService(INewsExService newsExService) {
        this.newsExService = newsExService;
    }

    public NewsServiceImpl getNewsService() {
        return newsService;
    }

    public void setNewsService(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    public List<NewsAudit> getNewsAuditList() {
        return newsAuditList;
    }

    public void setNewsAuditList(List<NewsAudit> newsAuditList) {
        this.newsAuditList = newsAuditList;
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
