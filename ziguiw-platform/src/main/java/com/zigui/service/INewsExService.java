package com.zigui.service;

import com.zigui.domain.NewsAudit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-14
 * Time: 上午9:51
 * provide new version news relative operation
 */
public interface INewsExService {

    /**
     * not a OO program method
     */

    public void saveNewsAudit(NewsAudit newsAudit);

    public List<NewsAudit> getNewsAudit(long newsId);

    List<NewsAudit> getNewsAuditByClassNewsId(long classNewsId);

    List<NewsAudit> getNewsAuditBySchoolNewsId(long schoolNewsId);
}
