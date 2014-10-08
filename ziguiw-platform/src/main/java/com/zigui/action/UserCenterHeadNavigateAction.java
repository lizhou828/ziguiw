package com.zigui.action;

import com.zigui.domain.*;
import com.zigui.exception.ErrorCode;
import com.zigui.exception.UnLoginException;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-27
 * Time: P.M.2:20
 */
public class UserCenterHeadNavigateAction extends BaseAction implements ServletContextAware {
    private static final Log log = LogFactory.getLog(UserCenterAction.class);
    private UserBase user;
    private Page<Question> questions;
    private Page<Answer> answers;
    private Page<Question> userAnsweredQuestion;
    private Page<CcczgInfoSouce> uploadSourcePage;
    private Page<CcczgInfoSousown> downloadSourcePage;

    private Integer downloadSourceGotoPage;
    private Integer uploadSourceGotoPage;
    private Integer gotoPage;
    private String countPerPage = "10";
    private String currentPage="1";
    private String orderField = "createTime";
    private boolean isAsc = false;
    private Map<String, String> query = new HashMap<String, String>();
    private String hql;
    private Object obj;
    private Object[] queryObj = new Object[0];

    private long userId;
    private PointsHistory pointsHistory;
    private List<PointsHistory> pointsList;
    private Page<PointsHistory> pointsPage=new Page<PointsHistory>();
    private CcczgInfoSouce ccczgInfoSouce;
    private List<CcczgInfoSouce> sourceUploadList = new ArrayList<CcczgInfoSouce>();
    private List<CcczgInfoSousown> sourceDownList = new ArrayList<CcczgInfoSousown>();
    private Integer totalCount;
    private Integer totalPage=0;
    private Integer uploadTotalPage;
    private Integer downloadTotalPage;
    private Integer gotoPages;
    private List pageList;
    private Long newChannelId=2L;//正式数据库中是网站公告频道，测试数据库中是新闻
    private Page<News> noticePage=new Page<News>();



    public String questionAndAnswerList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        try {
            UserBase tempUser = (UserBase) session.getAttribute("VALID_USER");
            if (tempUser != null && tempUser.getId() > 0L) {
                user = userService.getUserBaseById(tempUser.getId());
                if (user != null && user.getId() != 0L) {
                    query.put("creatorId", String.valueOf(user.getId()));
                }
                questions = questionService.listByCondition(query, new Integer(currentPage).intValue(),
                        new Integer(countPerPage).intValue(), orderField, isAsc);
                String hql = "select q from Question q where id in(select a.question from  Answer a where creatorId="
                        + user.getId() + ") and state=1  order by q.createTime desc ";
                obj = commonService.getByHql(hql, new Integer(currentPage).intValue(),
                        new Integer(countPerPage).intValue(), queryString, queryObj);

                return SUCCESS;
            }

            return INPUT;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    /**
     * get my resources!
     *
     * @return the alias of view configured in the struts xml
     */
    public String mySource() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        UserBase tempUser = (UserBase) session.getAttribute("VALID_USER");
        if (tempUser == null) throw new UnLoginException(ErrorCode.UN_LOGIN);
        user = userService.getUserBaseById(tempUser.getId());
        //获取用户下载过的资源
        if (downloadSourceGotoPage == null) {
            downloadSourceGotoPage = 1;
        }
        currentPage = downloadSourceGotoPage.toString();
        Page<Object> tmp = commonService.getByHql("from CcczgInfoSousown  where user = ? " +
                "order by createDate desc ", new Integer(currentPage).intValue(),
                new Integer(countPerPage).intValue(), new Object[]{user});
        downloadSourcePage = CommonUtils.transform(CcczgInfoSousown.class, tmp);
        downloadTotalPage = downloadSourcePage.getPageCount();
        sourceDownList = downloadSourcePage.getList();
        request.setAttribute("sourceDownList", sourceDownList);
         //获取用户上传过的资源
//       sourceUploadList=userService.getUploadSourceByUser(user);        // get all
        if (uploadSourceGotoPage == null) {
            uploadSourceGotoPage = 1;
            currentPage = uploadSourceGotoPage.toString();
        } else {
            currentPage = uploadSourceGotoPage.toString();
        }
        Page<Object> tmp1 = commonService.getByHql("from CcczgInfoSouce c where c.member = ? " +
                " and c.delsign=0 order by c.createDate desc ", new Integer(currentPage).intValue(),
                new Integer(countPerPage).intValue(), new Object[]{user});
        uploadSourcePage = CommonUtils.transform(CcczgInfoSouce.class, tmp1);
        uploadTotalPage = uploadSourcePage.getPageCount();
        sourceUploadList = uploadSourcePage.getList();
        request.setAttribute("sourceUploadList", sourceUploadList);
        if (sourceDownList == null && sourceDownList.size() == 0 && sourceUploadList == null && sourceUploadList.size() == 0) {
            return NONE;
        }
        return SUCCESS;
    }

    public String myPoint(){
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        try {
            UserBase tempUser = (UserBase) session.getAttribute("VALID_USER");
            if (tempUser != null && tempUser.getId() > 0L) {
                user = userService.getUserBaseById(tempUser.getId());
                if (user == null || user.getId() == 0L) {
                    return INPUT;
                }
                if(gotoPage==null || gotoPage <= 0){
                    currentPage="1";
                } else {
                    currentPage=gotoPage.toString();
                }
                pointsPage=pointService.queryForPage(new Integer(currentPage),new Integer(countPerPage),user.getId());
                totalCount=(int)pointsPage.getTotalCount();
                if(pointsPage.getList() != null && pointsPage.getList().size() != 0){
                    pointsList=pointsPage.getList();
                    request.setAttribute("pointsList",pointsList);
                    totalCount=(int)pointsPage.getTotalCount();
                    if(pointsPage.getPageSize()!=0){
                        totalPage = totalCount%pointsPage.getPageSize()==0 ?
                                totalCount/pointsPage.getPageSize() :
                                totalCount/pointsPage.getPageSize()+1;
                    }

                    pageList=new ArrayList();
                    for(int i=1;i<totalPage+1;i++){
                         pageList.add(i);
                    }
                    request.setAttribute("pageList",pageList);
                }
                return SUCCESS;
            }
            return INPUT;
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }

    public String webNotice(){
       HttpServletRequest request = ServletActionContext.getRequest();
        if(gotoPage==null || gotoPage <= 0){
            currentPage="1";
        } else {
            currentPage=gotoPage.toString();
        }
        try{
            noticePage=newsService.queryForPage(new Integer(currentPage),new Integer(countPerPage),newChannelId);
            totalCount=(int)noticePage.getTotalCount();
            if(noticePage.getList() != null && noticePage.getList().size() != 0){
                if(noticePage.getPageSize()!=0){
                    totalPage = totalCount%noticePage.getPageSize()==0 ?
                            totalCount/noticePage.getPageSize() :
                            totalCount/noticePage.getPageSize()+1;
                }

                pageList=new ArrayList();
                for(int i=1;i<totalPage+1;i++){
                    pageList.add(i);
                }
            }
            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }



    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public Page<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Page<Answer> answers) {
        this.answers = answers;
    }


    public Page<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Page<Question> questions) {
        this.questions = questions;
    }

    public String getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(String countPerPage) {
        this.countPerPage = countPerPage;
    }

    public UserBase getUser() {
        return user;
    }

    public void setUser(UserBase user) {
        this.user = user;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object[] getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(Object[] queryObj) {
        this.queryObj = queryObj;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Page<Question> getUserAnsweredQuestion() {
        return userAnsweredQuestion;
    }

    public void setUserAnsweredQuestion(Page<Question> userAnsweredQuestion) {
        this.userAnsweredQuestion = userAnsweredQuestion;
    }

    public PointsHistory getPointsHistory() {
        return pointsHistory;
    }

    public void setPointsHistory(PointsHistory pointsHistory) {
        this.pointsHistory = pointsHistory;
    }

    @Override
    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public CcczgInfoSouce getCcczgInfoSouce() {
        return ccczgInfoSouce;
    }

    public void setCcczgInfoSouce(CcczgInfoSouce ccczgInfoSouce) {
        this.ccczgInfoSouce = ccczgInfoSouce;
    }


    public List<CcczgInfoSouce> getSourceUploadList() {
        return sourceUploadList;
    }

    public void setSourceUploadList(List<CcczgInfoSouce> sourceUploadList) {
        this.sourceUploadList = sourceUploadList;
    }

    public List<CcczgInfoSousown> getSourceDownList() {
        return sourceDownList;
    }

    public void setSourceDownList(List<CcczgInfoSousown> sourceDownList) {
        this.sourceDownList = sourceDownList;
    }

    public int getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(int gotoPage) {
        this.gotoPage = gotoPage;
    }

    public Page<CcczgInfoSouce> getUploadSourcePage() {
        return uploadSourcePage;
    }

    public void setUploadSourcePage(Page<CcczgInfoSouce> uploadSourcePage) {
        this.uploadSourcePage = uploadSourcePage;
    }

    public Page<CcczgInfoSousown> getDownloadSourcePage() {
        return downloadSourcePage;
    }

    public void setDownloadSourcePage(Page<CcczgInfoSousown> downloadSourcePage) {
        this.downloadSourcePage = downloadSourcePage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getDownloadSourceGotoPage() {
        return downloadSourceGotoPage;
    }

    public void setDownloadSourceGotoPage(Integer downloadSourceGotoPage) {
        this.downloadSourceGotoPage = downloadSourceGotoPage;
    }

    public Integer getUploadSourceGotoPage() {
        return uploadSourceGotoPage;
    }

    public void setUploadSourceGotoPage(Integer uploadSourceGotoPage) {
        this.uploadSourceGotoPage = uploadSourceGotoPage;
    }

    public Integer getDownloadTotalPage() {
        return downloadTotalPage;
    }

    public void setDownloadTotalPage(Integer downloadTotalPage) {
        this.downloadTotalPage = downloadTotalPage;
    }

    public Integer getUploadTotalPage() {
        return uploadTotalPage;
    }

    public void setUploadTotalPage(Integer uploadTotalPage) {
        this.uploadTotalPage = uploadTotalPage;
    }
    public Integer getGotoPages() {
        return gotoPages;
    }

    public void setGotoPages(Integer gotoPages) {
        this.gotoPages = gotoPages;
    }

    public String getHql() {
        return hql;
    }

    public void setHql(String hql) {
        this.hql = hql;
    }

    public Page<PointsHistory> getPointsPage() {
        return pointsPage;
    }

    public void setPointsPage(Page<PointsHistory> pointsPage) {
        this.pointsPage = pointsPage;
    }

    public List<PointsHistory> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<PointsHistory> pointsList) {
        this.pointsList = pointsList;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public Long getNewChannelId() {
        return newChannelId;
    }

    public void setNewChannelId(Long newChannelId) {
        this.newChannelId = newChannelId;
    }

    public Page<News> getNoticePage() {
        return noticePage;
    }

    public void setNoticePage(Page<News> noticePage) {
        this.noticePage = noticePage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}

