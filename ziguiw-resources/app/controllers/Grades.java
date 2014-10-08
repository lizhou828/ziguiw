package controllers;

import com.arj.ziguiw.common.RecommendArea;
import models.Recommend;
import models.Resource;
import models.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Liujy
 * Date: 13-3-20
 * Time: 上午9:50
 */
public class Grades extends Application {

    public static void index(Integer leve){
        Map<Subject,List<Resource>> map = new HashMap<Subject,List<Resource>>();
        List<Subject> subjectList = Subject.findAll();
        if(leve == 1){
            for(Subject subject : subjectList){
                if(subject.subjectName.equals("语文") || subject.subjectName.equals("数学") ||
                        subject.subjectName.equals("英语")){
                    map.put(subject,Subject.findBySubjectIdAndLeve(subject.id,leve,5));
                }
            }
        }else{
            for(Subject subject : subjectList){
                map.put(subject,Subject.findBySubjectIdAndLeve(subject.id,leve,5));
            }
        }

        List<Recommend> recommends = Recommend.findByArea(RecommendArea.RESOURCES_INDEX_ZUIXINTUIJIAN);
        List<Resource> newResource = new ArrayList<Resource>();
        for (Recommend recommend : recommends){
            newResource.add((Resource)Resource.findById(new Long(recommend.objectId)));
        }
        List<Resource> downResource = Resource.findDownResource(9);
        Long totalCount = Resource.findTotalCount();

        List<Resource> hd = Resource.findHdByLeve(leve);
        List<Resource> newResources = Resource.findNewResourceByLeve(leve);

        render(map,newResource,downResource,totalCount,hd,newResources,leve);
    }


}
