package com.zigui.service.impl;

import com.zigui.dao.NewsAuditDao;
import com.zigui.domain.NewsAudit;
import com.zigui.service.INewsExService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-14
 * Time: 上午10:51
 */
public class NewsExImplService implements INewsExService {

    private NewsAuditDao newsAuditDao;

    @Override
    public void saveNewsAudit(NewsAudit newsAudit) {
        this.newsAuditDao.saveObj(newsAudit);
    }

    @Override
    public List<NewsAudit> getNewsAudit(long newsId) {
        return newsAuditDao.getNewsAuditListByNewsId(newsId);
    }

    @Override
    public List<NewsAudit> getNewsAuditByClassNewsId(long classNewsId) {
        return newsAuditDao.getNewsAuditListByClassNewsId(classNewsId);
    }

    @Override
    public List<NewsAudit> getNewsAuditBySchoolNewsId(long schoolNewsId) {
        return newsAuditDao.getNewsAuditListBySchoolNewsId(schoolNewsId);
    }

    public NewsAuditDao getNewsAuditDao() {
        return newsAuditDao;
    }

    public void setNewsAuditDao(NewsAuditDao newsAuditDao) {
        this.newsAuditDao = newsAuditDao;
    }
}
