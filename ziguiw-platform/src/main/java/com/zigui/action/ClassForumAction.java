package com.zigui.action;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.ClassForum;
import com.zigui.domain.ClassReply;
import com.zigui.domain.UserBase;
import com.zigui.utils.Page;
import org.apache.struts2.ServletActionContext;

import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-25
 * Time: 上午10:19
 */
public class ClassForumAction extends BaseAction{

    private Page<ClassForum> classForumPage;

    private Page<ClassReply> classReplyPage;

    private Long class_id;

    private Long classForumId;

    private Long classFroumId;

    private Long classReplyId;

    private int currentPage = 1;

    private int pageSize = 10;

    private String pageString;

    private ClassForum classForum;

    private ClassReply classReply;

    public String getClassForumList(){
        classForumPage = classForumService.getClassForumByClassId(currentPage,pageSize,class_id);
        pageString = classForumPage.getPageString(ServletActionContext.getRequest().getQueryString());
        return "forumlist";
    }

    public String saveClassForum(){
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        classForum.setUser(user);
        classForum.setCreateTime(new Date());
        classForum.setLastReplyTime(new Date());
        classForumService.saveClassForum(classForum);
        class_id = classForum.getClass_id();
        return "saveForumSuccess";
    }

    public String getClassForumInfo(){
           pageSize = 5;
           classForum = classForumService.getClassForumById(classForumId);
           if(classForum.getVisitCount() == null || classForum.getVisitCount() == 0){
               classForum.setVisitCount(1l);
           }else{classForum.setVisitCount(classForum.getVisitCount()+1);}
           classReplyPage = classForumService.getClassReplyByClassForumId(currentPage,pageSize,classForumId);
           pageString = classReplyPage.getPageString(ServletActionContext.getRequest().getQueryString());
           class_id = classForum.getClass_id();
           return "forumInfo";
    }

    public String saveClassReply(){
        ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        UserBase user = (UserBase)session.get("VALID_USER");
        classForum = classForumService.getClassForumById(classForumId);
        classReply.setCreateTime(new Date());
        classReply.setForumId(classForumId);
        classReply.setUser(user);
        classForumService.saveClassReply(classReply);
        classForum.setLastReplyTime(new Date());
        if(classForum.getReplyCount()==null || classForum.getReplyCount()==0){
        classForum.setReplyCount(1l);
        }else{classForum.setReplyCount(classForum.getReplyCount()+1);}
        classForumService.saveClassForum(classForum);
        return "saveReplySuccess";
    }

    public Long getClassReplyId() {
        return classReplyId;
    }

    public void setClassReplyId(Long classReplyId) {
        this.classReplyId = classReplyId;
    }

    public Long getClassFroumId() {
        return classFroumId;
    }

    public void setClassFroumId(Long classFroumId) {
        this.classFroumId = classFroumId;
    }

    public Page<ClassForum> getClassForumPage() {
        return classForumPage;
    }

    public void setClassForumPage(Page<ClassForum> classForumPage) {
        this.classForumPage = classForumPage;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageString() {
        return pageString;
    }

    public void setPageString(String pageString) {
        this.pageString = pageString;
    }

    public ClassForum getClassForum() {
        return classForum;
    }

    public void setClassForum(ClassForum classForum) {
        this.classForum = classForum;
    }

    public Page<ClassReply> getClassReplyPage() {
        return classReplyPage;
    }

    public void setClassReplyPage(Page<ClassReply> classReplyPage) {
        this.classReplyPage = classReplyPage;
    }

    public Long getClassForumId() {
        return classForumId;
    }

    public void setClassForumId(Long classForumId) {
        this.classForumId = classForumId;
    }

    public ClassReply getClassReply() {
        return classReply;
    }

    public void setClassReply(ClassReply classReply) {
        this.classReply = classReply;
    }
}
