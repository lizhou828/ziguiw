package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.ClassNews;
import com.zigui.domain.ClassPhoto;
import com.zigui.service.impl.ClassInfoServiceImpl;
import com.zigui.utils.Page;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-18
 * Time: 下午5:28
 */
public class ClassInfoAction extends BaseAction {

        private Page<ClassNews> pageClassNews;

        private Page<ClassNews> pageClassGg;

        private Page<ClassNews> pageClassRy;

        private Page<ClassNews> pageClassXxtd;

        private Page<ClassPhoto> pageClassPhoto;

        private Page<ClassPhoto> pageClassPhotoTj;

        private Page<ClassPhoto> pageClassSp;

        private Page<ClassNews> pageClassNewsByType;

        private Page<ClassPhoto> pageClassPhotoByType;

        private long class_id;

        private int pageNum = 1;

        private int pageSize = 5;

        private int type;

    public String getAll(){
            pageClassNews = classInfoService.getClassNewByClassid(pageNum,pageSize,0,class_id);
            pageClassGg = classInfoService.getClassNewByClassid(pageNum,pageSize,1,class_id);
            pageClassRy = classInfoService.getClassNewByClassid(pageNum,pageSize,2,class_id);
            pageClassXxtd = classInfoService.getClassNewByClassid(pageNum,pageSize,3,class_id);
            pageClassPhoto = classInfoService.getClassPhotoByClassid(pageNum, pageSize, 0, class_id, 0);
            pageClassPhotoTj = classInfoService.getClassPhotoByClassid(pageNum,pageSize,0,class_id,1);
            pageClassSp = classInfoService.getClassSpByClassid(pageNum,pageSize,1,class_id);
            return Action.SUCCESS;
    }

    public String getClassNewsByType(){
           pageClassNewsByType = classInfoService.getClassNewByClassid(pageNum,pageSize,type,class_id);
           return Action.SUCCESS;
    }

    public String getClassPhotoByType(){
          pageClassPhotoByType = classInfoService.getClassPhotoByClassid(pageNum,pageSize,type,class_id,0);
          return Action.SUCCESS;
    }
    public Page<ClassNews> getPageClassGg() {
        return pageClassGg;
    }

    public void setPageClassGg(Page<ClassNews> pageClassGg) {
        this.pageClassGg = pageClassGg;
    }

    public Page<ClassNews> getPageClassRy() {
        return pageClassRy;
    }

    public void setPageClassRy(Page<ClassNews> pageClassRy) {
        this.pageClassRy = pageClassRy;
    }

    public Page<ClassNews> getPageClassXxtd() {
        return pageClassXxtd;
    }

    public void setPageClassXxtd(Page<ClassNews> pageClassXxtd) {
        this.pageClassXxtd = pageClassXxtd;
    }

    public Page<ClassPhoto> getPageClassPhoto() {
        return pageClassPhoto;
    }

    public void setPageClassPhoto(Page<ClassPhoto> pageClassPhoto) {
        this.pageClassPhoto = pageClassPhoto;
    }

    public Page<ClassPhoto> getPageClassSp() {
        return pageClassSp;
    }

    public void setPageClassSp(Page<ClassPhoto> pageClassSp) {
        this.pageClassSp = pageClassSp;
    }

    public Page<ClassNews> getPageClassNews() {
        return pageClassNews;
    }

    public void setPageClassNews(Page<ClassNews> pageClassNews) {
        this.pageClassNews = pageClassNews;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ClassInfoServiceImpl getClassInfoService() {
        return classInfoService;
    }

    public void setClassInfoService(ClassInfoServiceImpl classInfoService) {
        this.classInfoService = classInfoService;
    }

    public Page<ClassNews> getPageClassNewsByType() {
        return pageClassNewsByType;
    }

    public void setPageClassNewsByType(Page<ClassNews> pageClassNewsByType) {
        this.pageClassNewsByType = pageClassNewsByType;
    }

    public Page<ClassPhoto> getPageClassPhotoByType() {
        return pageClassPhotoByType;
    }

    public void setPageClassPhotoByType(Page<ClassPhoto> pageClassPhotoByType) {
        this.pageClassPhotoByType = pageClassPhotoByType;
    }

    public Page<ClassPhoto> getPageClassPhotoTj() {
        return pageClassPhotoTj;
    }

    public void setPageClassPhotoTj(Page<ClassPhoto> pageClassPhotoTj) {
        this.pageClassPhotoTj = pageClassPhotoTj;
    }
}
