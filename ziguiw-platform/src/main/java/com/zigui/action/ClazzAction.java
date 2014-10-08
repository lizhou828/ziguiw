package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Clasz;
import com.zigui.service.impl.ClassServiceImpl;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-12-5
 * Time: 下午3:55
 */
public class ClazzAction {
    private List<Clasz> clazzList;
    private Long teacherId;
    private String xxId;

    @Autowired
    private ClassServiceImpl classService;

    public String findClasses(){
        HttpServletRequest request = ServletActionContext.getRequest();
        clazzList=classService.findAllClassByTeacherId(teacherId);
        request.setAttribute("clazzList",clazzList);
        return Action.SUCCESS;
    }

    public String getAllClass(){
        clazzList = classService.findAllClassBySchoolId(xxId);
        return "list";
    }


    public List getClazzList() {
        return clazzList;
    }

    public void setClazzList(List clazzList) {
        this.clazzList = clazzList;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getXxId() {
        return xxId;
    }

    public void setXxId(String xxId) {
        this.xxId = xxId;
    }
}
