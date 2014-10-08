package com.zigui.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.Administrator;
import com.zigui.domain.PointsHistory;
import com.zigui.domain.UserBase;
import com.zigui.service.IAdminMenuService;
import com.zigui.service.impl.*;
import com.zigui.utils.BeanFactoryUtils;
import com.zigui.utils.LogTool;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseAction extends ActionSupport {

    /**
     *
     */
    private static final long serialVersionUID = 3182241008941323348L;

    protected String queryString;

    protected UserBase user;

    protected Administrator admin;

    @Autowired
    protected UserServiceImpl userService;
    @Autowired
    protected StudentServiceImpl studentService;
    @Autowired
    protected ClassServiceImpl classService;
    @Autowired
    protected ParentServiceImpl parentService;
    @Autowired
    protected TeacherServiceImpl teacherService;
    @Autowired
    protected SchoolServiceImpl schoolService;
    @Autowired
    protected CommonMessageServiceImpl commonMessageService;
    @Autowired
    protected CommonServiceImpl commonService;
    @Autowired
    protected QuestionServiceImpl questionService;
    @Autowired
    protected NianJiServiceImpl nianJiService;
    @Autowired
    protected DsisExamServiceImpl dsisExamService;
    @Autowired
    protected DsisTermSetServiceImpl dsisTermSetService;
    @Autowired
    protected PointHistoryService pointService;
    @Autowired
    protected NewsServiceImpl newsService;
    @Autowired
    protected ClassInfoServiceImpl classInfoService;
    @Autowired
    protected ClassForumServiceImpl classForumService;
    @Autowired
    protected StudentArchiveServiceImpl studentArchiveService;

    protected List topMenuList;

    protected List leftMenuList;

    protected String menuId;

    IAdminMenuService adminMenuService;

    public BaseAction() {
        ActionContext context = ActionContext.getContext();
        if (context != null) {
            HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
            Map<String, Object> session = context.getSession();
            user = (UserBase) session.get("VALID_USER");
            admin = (Administrator) session.get("ADMIN_USER");
            queryString = request.getQueryString();
        }
    }

    protected void initMenu() {
        //init admin menu by YeQiang 2012-12-25
        adminMenuService = (IAdminMenuService) BeanFactoryUtils.getBean("adminMenuService");
        if (adminMenuService != null) {
            String requestURI = ServletActionContext.getRequest().getRequestURI();
            topMenuList = adminMenuService.getTopMenu(admin);
            try {
                menuId = (String) ActionContext.getContext().getSession().get("menuId");
            } catch (Exception e) {
                LogTool.getLog().error(e);
            }
            if (menuId != null) {
                long l = 0;
                try {
                    l = Long.parseLong(menuId);
                } catch (Exception e) {
                    LogTool.getLog().error(e);
                }
                leftMenuList = adminMenuService.getLeftMenu(admin, l);
            } else {
                leftMenuList = adminMenuService.getLeftMenu(admin, requestURI);
            }
        }
    }

    public void changePoints(long userId, int type, int pointsChange) {
        UserBase ub = userService.getUserBaseById(userId);

        PointsHistory history = new PointsHistory();
        history.setUser(ub);
        history.setType(type);
        history.setPointsChange(pointsChange);
        userService.changePoints(history);
    }

    protected void print(HttpServletResponse response, String info) throws IOException {
        try {
            response.setHeader("Cache-Control", "no-cache");
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().print(info);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public DsisExamServiceImpl getDsisExamService() {
        return dsisExamService;
    }

    public void setDsisExamService(DsisExamServiceImpl dsisExamService) {
        this.dsisExamService = dsisExamService;
    }

    public UserBase getUser() {
        return user;
    }

    public void setUser(UserBase user) {
        this.user = user;
    }

    public List getTopMenuList() {
        return topMenuList;
    }

    public List getLeftMenuList() {
        return leftMenuList;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        if (menuId == null) return; 
        this.menuId = menuId;
        ActionContext.getContext().getSession().put("menuId", menuId);
        initMenu();//what the fuck!!!
    }
}
