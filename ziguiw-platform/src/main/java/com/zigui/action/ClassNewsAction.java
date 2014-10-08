package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.ClassNews;
import com.zigui.domain.Clasz;
import com.zigui.domain.School;
import com.zigui.exception.PropertyNotSetException;
import com.zigui.service.impl.ClassInfoServiceImpl;
import com.zigui.service.impl.ClassServiceImpl;
import com.zigui.service.impl.SchoolServiceImpl;
import com.zigui.utils.Page;
import com.zigui.utils.enums.NewsStatus;
import com.zigui.utils.enums.NewsType;
import com.zigui.vo.VClassNews;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-19
 * Time: 上午10:50
 */
public class ClassNewsAction extends BaseAction {

    private long id;

    private String title;

    private int pageNo;

    private String pageString;
    private String schoolId;

    private Map<String, String> query;

    private String queryString;

    private List<School> schoolList;

    private List<Clasz> classList;

    private Map newsTypeList;

    private Page<VClassNews> pagedVClassNews;

    @Autowired
    private ClassInfoServiceImpl classInfoService;//not depend on abstract！！！

    @Autowired
    private ClassServiceImpl classService;

    @Autowired
    private SchoolServiceImpl schoolService;

    private Map newsStatusList;

    private String classNewsIdList;

    private int type;

    private long class_id;

    private int pageNum = 1;

    private int pageSize = 10;

    private Page<ClassNews> classNewsPage;

    private ClassNews classNews;

    private Page<ClassNews> classNewsNew;

    private Page<ClassNews> classNewsDt;


    private int countPerPage = 10;

    private int currentPage = 1;
    //what the fuck...

    public ClassNewsAction() {
        newsTypeList = NewsType.getList();
        newsStatusList = NewsStatus.getList();
        if (classList == null) {
            classList = new ArrayList<Clasz>();
            Clasz tmp = new Clasz();
            tmp.setBj_mcheng("-请选择班级-");
            classList.add(tmp);
        }
    }

    public String view() {
        if (id != 0) {
            classNews = classInfoService.getClassNewsById(id);
        }
        schoolList = schoolService.getSchoolList();
        return "view";
    }

    public String getById() {
        schoolList = schoolService.getSchoolList();
        this.classNews = classInfoService.getClassNewsById(id);
        if (this.classNews.getXx_id() != null && !this.classNews.getXx_id().equals("")) {
            classList = classService.findAllClassBySchoolId(classNews.getXx_id());
        }
        return "view";
    }

    public String deleteById() {
        this.classNews = classInfoService.getClassNewsById(id);
        if (this.classNews != null) {
            this.classNews.setStatus(NewsStatus.DELETED.getCode());
            classInfoService.saveOrUpdateClassNews(this.classNews);
            return Action.SUCCESS;
        } else {
            throw new RuntimeException("object not found");
        }
    }

    public String restore() {
        classNews = classInfoService.getClassNewsById(id);
        if (classNews != null) {
            classNews.setStatus(NewsStatus.WAITING_AUDIT.getCode());
            classInfoService.saveOrUpdateClassNews(classNews);
            return Action.SUCCESS;
        } else {
            throw new RuntimeException("object not found");
        }


    }

    public String batchAuditPass() {
        String[] ids = classNewsIdList.split(",");
        classInfoService.batchAuditPass(ids);
        return Action.SUCCESS;
    }

    public String saveOrUpdate() {
        PropertyNotSetException propertyNotSetException;
        //this validate function should done by struts!!
        if (this.classNews.getClass_id() == 0) {
            propertyNotSetException = new PropertyNotSetException("班级未设置");
            propertyNotSetException.setAppendExceptionMessageToClient(true);
            throw propertyNotSetException;
        }
        if (this.classNews.getContent() == null || this.classNews.getContent().equals("")) {
            propertyNotSetException = new PropertyNotSetException("新闻内容未设置");
            propertyNotSetException.setAppendExceptionMessageToClient(true);
            throw propertyNotSetException;
        }
        if (this.classNews.getTitle() == null || this.classNews.getTitle().equals("")) {
            propertyNotSetException = new PropertyNotSetException("新闻标题未设置");
            propertyNotSetException.setAppendExceptionMessageToClient(true);
            throw propertyNotSetException;
        }
        if (this.classNews.getXx_id() == null || this.classNews.getXx_id().equals("")) {
            propertyNotSetException = new PropertyNotSetException("学校未设置");
            propertyNotSetException.setAppendExceptionMessageToClient(true);
            throw propertyNotSetException;
        }
        //add new
        //set default value
        this.classNews.setCreate_time(new Date());
        //if (this.classNews.getStatus() == null || this.classNews.getStatus() == 0) {
            this.classNews.setStatus(NewsStatus.WAITING_AUDIT.getCode());
        //}
        classInfoService.saveOrUpdateClassNews(this.classNews);
        return Action.SUCCESS;
    }

    public String listByCondition() {
        schoolList = schoolService.getSchoolList();
        pagedVClassNews = classInfoService.getVClassNewByCondition(query, currentPage, countPerPage, queryString);
        return "list";
    }

    public String ajaxGetClass() {
        List<Clasz> classList = classService.findAllClassBySchoolId(this.schoolId);
        //Init json data
        String json = "{\"dataNode\":[";
        int counter = 0;
        for (Clasz clasz : classList) {
            json += "{\"key\":\"" + clasz.getBj_id() + "\",\"value\":\"" + clasz.getBj_mcheng() + "\"},";
            counter++;
        }
        if (counter > 0) {
            json = json.substring(0, json.length() - 1);
        }
        json += "]}";
        //write json data to client
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            //will return empty
        }
        writer.print(json);
        writer.flush();
        writer.close();
        return null;
    }

    public String getClassNewsBytype() {
        classNewsPage = classInfoService.getClassNewByClassid(currentPage, pageSize, type, class_id);
        pageString = classNewsPage.getPageString(ServletActionContext.getRequest().getQueryString());
        return Action.SUCCESS;
    }

    public String getClassNewsById() {
        classNews = classInfoService.getClassNewsById(id);
        classNewsNew = classInfoService.getClassNewByClassid(1,7,0,class_id);
        classNewsDt = classInfoService.getClassNewByClassid(1,7,1,class_id);
        return Action.SUCCESS;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ClassNews getClassNews() {
        return classNews;
    }

    public void setClassNews(ClassNews classNews) {
        this.classNews = classNews;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public Page<VClassNews> getPagedVClassNews() {
        return pagedVClassNews;
    }

    public void setPagedVClassNews(Page<VClassNews> pagedVClassNews) {
        this.pagedVClassNews = pagedVClassNews;
    }

    public ClassInfoServiceImpl getClassInfoService() {
        return classInfoService;
    }

    public void setClassInfoService(ClassInfoServiceImpl classInfoService) {
        this.classInfoService = classInfoService;
    }

    public ClassServiceImpl getClassService() {
        return classService;
    }

    public void setClassService(ClassServiceImpl classService) {
        this.classService = classService;
    }

    public SchoolServiceImpl getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolServiceImpl schoolService) {
        this.schoolService = schoolService;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Map getNewsTypeList() {
        return newsTypeList;
    }

    public void setNewsTypeList(Map newsTypeList) {
        this.newsTypeList = newsTypeList;
    }

    public Map getNewsStatusList() {
        return newsStatusList;
    }

    public void setNewsStatusList(Map newsStatusList) {
        this.newsStatusList = newsStatusList;
    }

    public List<Clasz> getClassList() {
        return classList;
    }

    public void setClassList(List<Clasz> classList) {
        this.classList = classList;
    }

    public String getClassNewsIdList() {
        return classNewsIdList;
    }

    public void setClassNewsIdList(String classNewsIdList) {
        this.classNewsIdList = classNewsIdList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Page<ClassNews> getClassNewsPage() {
        return classNewsPage;
    }

    public void setClassNewsPage(Page<ClassNews> classNewsPage) {
        this.classNewsPage = classNewsPage;
    }

    public Page<ClassNews> getClassNewsNew() {
        return classNewsNew;
    }

    public void setClassNewsNew(Page<ClassNews> classNewsNew) {
        this.classNewsNew = classNewsNew;
    }

    public Page<ClassNews> getClassNewsDt() {
        return classNewsDt;
    }

    public void setClassNewsDt(Page<ClassNews> classNewsDt) {
        this.classNewsDt = classNewsDt;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }

}
