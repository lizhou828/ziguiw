package com.zigui.domain;


import java.util.ArrayList;
import java.util.List;

public class CourseXml {
    private Integer type;

    private List<String> list = new ArrayList<String>();

    //private String imagePath;
    //private String soundPath;
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public List<String> getList() {
        return list;
    }
    public void setList(List<String> list) {
        this.list = list;
    }


}
