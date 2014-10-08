package com.zigui.dao;

import com.zigui.domain.NewsAudit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-14
 * Time: 上午11:27
 * - -# this code has no abstract class or interface,should be rewrite later
 */
public class NewsAuditDao extends GenericDao<NewsAudit> {

    public List<NewsAudit> getNewsAuditListByNewsId(long newsId) {
        return super.findBy(NewsAudit.class, "newsId", newsId);
    }

    public List<NewsAudit> getNewsAuditListByClassNewsId(long classNewsId) {
        return super.findBy(NewsAudit.class, "classNewsId", classNewsId);
    }

    public List<NewsAudit> getNewsAuditListBySchoolNewsId(long schoolNewsId) {
        return super.findBy(NewsAudit.class, "schoolNewsId", schoolNewsId);
    }
}
