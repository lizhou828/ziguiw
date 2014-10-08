package com.zigui.service.impl;

import com.zigui.dao.ClassDao;
import com.zigui.dao.TeacherClaszDao;
import com.zigui.domain.Clasz;
import com.zigui.utils.Page;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: lizhou
 * Date: 12-10-17
 * Time: am 10:54
 */
public class ClassServiceImpl {
    @Autowired
    private ClassDao classDao;
    @Autowired
    private TeacherClaszDao teacherClaszDao;
    private @Setter @Getter List<Clasz> claszList=new ArrayList<Clasz>();
    private Clasz clasz=new Clasz();
    private String countPerPage = "10";
    private String currentPage = "1";
    private Object[] queryObj = new Object[0];
    private Page<Clasz> claszPage=new Page<Clasz>();
    private String queryString="1=1";
    private CommonServiceImpl commonService=new CommonServiceImpl();
    private Object obj;

    public Clasz getById(long id){
        return classDao.get(Clasz.class,id);
    }

    public List<Clasz> findAllClassByTeacherId(Long teacherId){
        return classDao.find("from Clasz c where c.Bj_id in(select t.clasz.Bj_id from TeacherClasz t  where t.teacherID=? )",new Object[]{teacherId.intValue()});
    }

    //根据老师的id获取班级的集合对象(分页)
    public  Page<Clasz> getClassesByTeacherId(int id) {
        String hql="from Clasz c where c.Bj_id in(select t.clasz from TeacherClasz t where t.teacherID="+id+")";
        System.out.println("the hql your're visiting is "+hql);
        List<Object> listObj;

        try{
            Page<Object> claszObject = commonService.getByHql(hql, new Integer(currentPage).intValue(),
                    new Integer(countPerPage).intValue(), queryString, queryObj);
            listObj=claszObject.getList();
            System.out.println("ClassServiceImpl's getClassesByTeacherId is running...listObj.size="+listObj.size());
            if(listObj==null || listObj.size()==0){
                return null;
            }
            for(int i=0;i<listObj.size();i++){
                claszList.add((Clasz)listObj.get(i));
            }

            claszPage.setList(claszList);
            return claszPage;
        }catch ( Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e) ;
        }
    }


    public List<Clasz> findAllClassBySchoolId(String schoolId) {
        String hql = "from Clasz where Bj_ztai = 70 and XxID = ? order by Bj_mcheng";
        return classDao.find(hql,schoolId);
    }

    public Clasz findClassById(Long bj_id){
        List<Clasz>  list = classDao.findBy(Clasz.class,"Bj_id",bj_id);
        if(list.size() > 0 ){
            return list.get(0);
        }
           return new Clasz();
    }


    public ClassDao getClassDao() {
        return classDao;
    }

    public void setClassDao(ClassDao classDao) {
        this.classDao = classDao;
    }

    public TeacherClaszDao getTeacherClaszDao() {
        return teacherClaszDao;
    }

    public void setTeacherClaszDao(TeacherClaszDao teacherClaszDao) {
        this.teacherClaszDao = teacherClaszDao;
    }

    public List<Clasz> getClaszList() {
        return claszList;
    }

    public void setClaszList(List<Clasz> claszList) {
        this.claszList = claszList;
    }

    public Clasz getClasz() {
        return clasz;
    }

    public void setClasz(Clasz clasz) {
        this.clasz = clasz;
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

    public Object[] getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(Object[] queryObj) {
        this.queryObj = queryObj;
    }


    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public CommonServiceImpl getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonServiceImpl commonService) {
        this.commonService = commonService;
    }

    public Page<Clasz> getClaszPage() {
        return claszPage;
    }

    public void setClaszPage(Page<Clasz> claszPage) {
        this.claszPage = claszPage;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
