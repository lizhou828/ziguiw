package com.zigui.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.School;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserDiary;
import com.zigui.service.impl.SchoolServiceImpl;
import com.zigui.service.impl.UserDiaryServiceImpl;
import com.zigui.service.impl.UserServiceImpl;
import com.zigui.utils.LogTool;
import com.zigui.utils.Page;
import com.zigui.utils.enums.NewsStatus;
import com.zigui.utils.enums.UserDiaryType;
import com.zigui.vo.VSchoolNews;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-21
 * Time: 下午5:47 .
 */
public class SchoolNewsAction extends ActionSupport {
    private long id;

    private int pageNo;

    private int pageSize;

    private String schoolId;

    private Map<String, String> query;

    private UserDiary userDiary;

    private Page<VSchoolNews> pagedVSchoolNews;

    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private UserDiaryServiceImpl userDiaryService;

    @Autowired
    private UserServiceImpl userService;

    private String schoolNewsIdList;

    private List<School> schoolList;

    private String XxID;

    private Map userDiaryTypeList;

    private Map newsStatusList;

    private int countPerPage = 10;

    private int currentPage = 1;

    private String queryString;


    public SchoolNewsAction() {
    	userDiaryTypeList = UserDiaryType.getList();
        newsStatusList = NewsStatus.getList();
    }

    public String view() {
        schoolList = schoolService.getSchoolList();
        if (id != 0) {
            userDiary = userDiaryService.getById(id);
        }
        return "view";
    }

    public String getById() {
        schoolList = schoolService.getSchoolList();
        userDiary = userDiaryService.getById(id);
        if (userDiary != null && userDiary.getUser() != null && userDiary.getUser().getSchool() != null) {
            XxID = userDiary.getUser().getSchool().getXxID();
        }
        return "view";
    }

    public String deleteById() {
        userDiary = userDiaryService.getById(id);
        if (userDiary != null) {
            userDiary.setStatus((int) NewsStatus.DELETED.getCode());
            userDiaryService.saveOrUpdate(userDiary);
            return Action.SUCCESS;
        } else {
            throw new RuntimeException("object not found");
        }
    }

    public String restore()
    {
        /**
         * code repeat,like deleteById()
         */
        userDiary = userDiaryService.getById(id);
        if (userDiary != null) {
            userDiary.setStatus((int) NewsStatus.WAITING_AUDIT.getCode());
            userDiaryService.saveOrUpdate(userDiary);
            return Action.SUCCESS;
        } else {
            throw new RuntimeException("object not found");
        }
    }

    public String batchAuditPass() {
        schoolList = schoolService.getSchoolList();
        String[] ids = schoolNewsIdList.split(",");
        userDiaryService.batchAuditPass(ids);
        return Action.SUCCESS;
    }

    public String saveOrUpdate() {
        schoolList = schoolService.getSchoolList();
        String tmpId = XxID;//actually is School.XxID
        //School.XxID -> School.xx_id -> UserBase.foreignKey -> UserDiary.user_id
        try {
            School school = schoolService.getInfoByXxID(tmpId);
            UserBase userBase = userService.getUserBaseBySchoolForeignkey(school.getXx_ID());
            userDiary.setUser(userBase);
        } catch (Exception e) {
            LogTool.getLog().error("can not find data!", e);
            throw new RuntimeException(e);
        }
        if(this.userDiary.getCreateTime() == null)
        {
        	this.userDiary.setCreateTime(new Date());
        }
        //if (this.userDiary.getStatus() == 0) {
            this.userDiary.setStatus((int) NewsStatus.WAITING_AUDIT.getCode());
        //}
        userDiaryService.saveOrUpdate(userDiary);
        return Action.SUCCESS;
    }

    public String listByCondition() {
        schoolList = schoolService.getSchoolList();
//        pagedUserDiary = userDiaryService.getVClassNewByCondition(query, this.pageNo, this.pageSize);
        pagedVSchoolNews = userDiaryService.getVSchoolNewsByCondition(query, currentPage, countPerPage, queryString);
        return "list";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public UserDiary getUserDiary() {
        return userDiary;
    }

    public void setUserDiary(UserDiary userDiary) {
        this.userDiary = userDiary;
    }

    public Page<VSchoolNews> getPagedVSchoolNews() {
        return pagedVSchoolNews;
    }

    public void setPagedVSchoolNews(Page<VSchoolNews> pagedVSchoolNews) {
        this.pagedVSchoolNews = pagedVSchoolNews;
    }

    public SchoolServiceImpl getSchoolService() {
        return schoolService;
    }

    public void setSchoolService(SchoolServiceImpl schoolService) {
        this.schoolService = schoolService;
    }

    public UserDiaryServiceImpl getUserDiaryService() {
        return userDiaryService;
    }

    public void setUserDiaryService(UserDiaryServiceImpl userDiaryService) {
        this.userDiaryService = userDiaryService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public String getSchoolNewsIdList() {
        return schoolNewsIdList;
    }

    public void setSchoolNewsIdList(String schoolNewsIdList) {
        this.schoolNewsIdList = schoolNewsIdList;
    }

    public List<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
    }

    public String getXxID() {
        return XxID;
    }

    public void setXxID(String xxID) {
        XxID = xxID;
    }    

    public Map getUserDiaryTypeList() {
		return userDiaryTypeList;
	}

	public void setUserDiaryTypeList(Map userDiaryTypeList) {
		this.userDiaryTypeList = userDiaryTypeList;
	}

	public Map getNewsStatusList() {
        return newsStatusList;
    }

    public void setNewsStatusList(Map newsStatusList) {
        this.newsStatusList = newsStatusList;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
