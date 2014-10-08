package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.News;
import com.zigui.service.impl.NewsQueryServiceImpl;
import com.zigui.service.impl.NewsServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

public class NewsAction extends BaseAction {

    private static final long serialVersionUID = 6737432582331279411L;

    private Map<String, String> query;

    private String orderField = "createTime";

    private String orderType = "desc";

//	private int countPerPage = 10;
//
//	private int currentPage = 1;

    private String countPerPage = "10";

    private String currentPage = "1";

    private News news;

    private Page<News> pagedNews;

    private long newsId;

    private Long[] opIds;
    private int opType = 0;

    private int approveFlag = 1;

    private long recommendRegionId;

    @Autowired
    private NewsServiceImpl newsService;

    @Autowired
    private NewsQueryServiceImpl newsQueryService;

    public void validateSaveOrUpdate() {
        /*为空判断*/
        if (StringUtils.isEmpty(news.getTitle())) {
            this.addFieldError("news.title", "新闻标题不能为空");
            return;
        }
        if (StringUtils.isEmpty(news.getContent())) {
            this.addFieldError("news.content", "新闻内容不能为空");
            return;
        }

        if (news.getNewsChannle() == null || news.getNewsChannle().getId() < 1L) {
            this.addFieldError("news.channle", "频道不能为空");
            return;
        }
        /*过长判断*/
        if (news.getDescription().length() > 400) {//200字
            this.addFieldError("news.description", "新闻描述不能超过200字");
            return;
        }

        if (news.getFirstImage().length() > 128) {//200字
            this.addFieldError("news.firstImage", "新闻图片不能超过128字");
            return;
        }

        if (news.getTitle().length() > 100) {//200字
            this.addFieldError("news.title", "知识标题不能超过50字");
            return;
        }

        if (news.getSubtitle().length() > 100) {//200字
            this.addFieldError("news.subtitle", "知识副标题不能超过50字");
            return;
        }

    }

    public String saveOrUpdate() {
        if (news.getId() > 0) {
            news.setLastModifyId(admin.getId());
            news.setLastModifyNick(admin.getNickName());
        } else {
            news.setCreatorId(admin.getId());
            news.setCreatorNick(admin.getNickName());
        }
        if (news.getFirstImage() == null || "".equals(news.getFirstImage())) {
            news.setFirstImage(getFirstImage(news.getContent()));
        }

        if (news.getEditors() == null || news.getEditors().equals("")) {
            news.setEditors(super.admin.getRealName());
        }
        news.setAutoDescription(StringUtils.substring(getPureText(news.getContent()), 0, 110));
        news.setCreateTime(news.getCreateTime() == null ? new Date() : news.getCreateTime());
        newsService.saveOrUpdate(news);

        return Action.SUCCESS;
    }

    public String listNewsByCondition() {
        boolean isAsc = false;
        if (orderType.equals("ASC")) {
            isAsc = true;
        }
        pagedNews = newsQueryService.getNewsByCondition(query, new Integer(currentPage).intValue(),
                new Integer(countPerPage).intValue(), orderField, isAsc, this.queryString);

        return Action.SUCCESS;
    }

    public void validateGetById() {
        if (newsId < 1L) {
            this.addFieldError("newsId", "新闻标题不能为空");
        }
    }

    public String getById() {

        news = newsService.getNewsById(newsId);

        return Action.SUCCESS;
    }

    public String delete() {
        //opIds = new Long[]{newsId};
        newsService.deleteNews(opIds);

        return Action.SUCCESS;
    }

    public void validateFakeDelete() {
        if (ArrayUtils.isEmpty(opIds)) {
            this.addFieldError("opIds", "至少选中一行记录执行操作");
        }
    }

    public String fakeDelete() {
        newsService.fakeDeleteNews(opIds);

        return Action.SUCCESS;
    }

    public void validateApprove() {
        if (ArrayUtils.isEmpty(opIds)) {
            this.addFieldError("opIds", "至少选中一行记录执行操作");
        }
    }

    public String approve() {
        if (approveFlag == -2) {
            /**
             * this code is disabled ,new function is "NewsAuditAction.recordAuditInfo"
             * by YeQiang 2012-12-14
             */
            return null;
        } else {
            newsService.approveNews(opIds, approveFlag);

            return Action.SUCCESS;
        }
    }

    public void validateRestore() {
        if (ArrayUtils.isEmpty(opIds)) {
            this.addFieldError("opIds", "至少选中一行记录执行操作");
        }
    }

    public String restore() {
        newsService.restore(opIds);

        return Action.SUCCESS;
    }

    public void validateRecommend() {
        if (ArrayUtils.isEmpty(opIds)) {
            this.addFieldError("opIds", "至少选中一行记录执行操作");
        }
        if (recommendRegionId < 1L) {
            this.addFieldError("recommendRegionId", "无效的区域ID");
        }
    }

    public String recommend() {
        newsService.recommend(opIds, recommendRegionId);

        return Action.SUCCESS;
    }


    public String batchDelete() {
        newsService.deleteNews(opIds);

        return Action.SUCCESS;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public Page<News> getPagedNews() {
        return pagedNews;
    }

    public void setPagedNews(Page<News> pagedNews) {
        this.pagedNews = pagedNews;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }


    public String getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(String countPerPage) {
        this.countPerPage = countPerPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public Long[] getOpIds() {
        return opIds;
    }

    public void setOpIds(Long[] opIds) {
        this.opIds = opIds;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public int getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(int approveFlag) {
        this.approveFlag = approveFlag;
    }

    public long getRecommendRegionId() {
        return recommendRegionId;
    }

    public void setRecommendRegionId(long recommendRegionId) {
        this.recommendRegionId = recommendRegionId;
    }

    private String getFirstImage(String html) {

        String firstImage = null;

        Parser parser = Parser.createParser(html, "UTF-8");
        NodeFilter filter = new NodeClassFilter(ImageTag.class);

        try {
            NodeList images = parser.extractAllNodesThatMatch(filter);

            for (Node node : images.toNodeArray()) {
                ImageTag imageTag = (ImageTag) node;
                firstImage = imageTag.getImageURL();

                break;
            }
        } catch (ParserException e) {
//			log.error(e.getMessage(), e);
        }

        return firstImage;
    }

    private String getPureText(String html) {
        StringBuffer sb = new StringBuffer();
        try {
            Parser parser = Parser.createParser(html, "UTF-8");
            NodeIterator its = parser.elements();

            while (its.hasMoreNodes()) {
                Node node = (Node) its.nextNode();
                sb.append(node.toPlainTextString());
            }
            return sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return html;
        }
    }


}
