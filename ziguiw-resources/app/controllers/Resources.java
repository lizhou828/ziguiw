package controllers;

import com.arj.ziguiw.common.*;
import com.arj.ziguiw.common.utils.FileUtils;
import controllers.modules.cas.SecureCAS;
import controllers.modules.cas.UnSecure;
import models.*;
import org.apache.commons.codec.digest.DigestUtils;
import play.cache.CacheFor;
import play.mvc.With;

import java.io.File;
import java.lang.Boolean;
import java.util.*;

/**
 * User: Liujy
 * Date: 13-3-20
 * Time: 上午9:50
 */
@With(SecureCAS.class)
public class Resources extends Application {

    private static final String DOWNLOADURL101 = "http://school.101sx.com/rd.action?";
    private static final String KEY = "!)!RESONLINE";

    @UnSecure
    @CacheFor("1mn")
    public static void index(){
        List<Subject> subjects = Subject.findAll();
        List<List<Resource>> list1 = new ArrayList<List<Resource>>();
        List<List<Resource>> list2 = new ArrayList<List<Resource>>();
        List<List<Resource>> list3 = new ArrayList<List<Resource>>();
        for(Subject subject : subjects){
            list1.add(Subject.findBySubjectIdAndLeve(subject.id,1,12));
            list2.add(Subject.findBySubjectIdAndLeve(subject.id,2,12));
            list3.add(Subject.findBySubjectIdAndLeve(subject.id,3,12));
        }

        List<Resource> videoList = Type.findByTypeName("视频",5);
        List<Resource> coursewareList = Type.findByTypeName("课件",5);

        List<Recommend> recommends = Recommend.findByArea(RecommendArea.RESOURCES_INDEX_ZUIXINTUIJIAN);
        List<Resource> newResource = new ArrayList<Resource>();
        for (Recommend recommend : recommends){
            newResource.add((Resource)Resource.findById(new Long(recommend.objectId)));
        }
        List<Resource> downResource = Resource.findDownResource(5);

        Long totalCount = Resource.findTotalCount();

        List<UserBase> teacherPoint = UserBase.findDescPointUser();

        render(subjects,list1,list2,list3,videoList,coursewareList,
                newResource,downResource,totalCount,teacherPoint);
    }

    public static void add(Long resourceId){
        Resource resource = null;
        if(resourceId != null){
            resource = Resource.findById(resourceId);
        }
        List<Type> typeList = Type.findAll();
        List<Version> versionList = Version.findByFlag();
        List<Grade> gradeList = Grade.findAll();
        List<Subject> subjectList = Subject.findAll();
        render(typeList,versionList,gradeList,subjectList,resource);
    }

    public static void  save(Long resourceId,File firstImage,String title,Long typeId,
                             Long versionId,Long gradeId,Long subjectId,String description,
                             String keys,Integer needPoint){
        Resource resource = Resource.findById(resourceId);
        //验证基本必填字段
        String error = Resource.check(resource,(firstImage==null? null : firstImage.getName()),title, typeId, versionId, gradeId, subjectId);
        if(!error.equals("")){
            flash.put("error",error);
            add(resourceId);
        }
        if(firstImage != null){
            String firstImagePath = FileUtils.copyAndCompressImage(firstImage,firstImage.getName(),
                    uploadDir,new String[]{});
            resource.firstImage = firstImagePath;
        }
        resource.title = title;
        resource.type  = Type.findById(typeId);
        resource.version = Version.findById(versionId);
        resource.grade = Grade.findById(gradeId);
        resource.subject = Subject.findById(subjectId);
        resource.description = description;
        resource.keys = keys;
        resource.needPoint = needPoint;
        resource.save();
        list();
    }


    public static void create(File file,File firstImage,String title,Long typeId,Long versionId,
                              Long gradeId,Long subjectId,String description,
                              String keys,Integer needPoint){
        String error = Resource.check(file,(firstImage==null? null : firstImage.getName()),
                title, typeId, versionId, gradeId, subjectId);
        if(!error.equals("")){
            flash.put("error",error);
            add(null);
        }
        Resource resource = new Resource();
        //上传文件
        String resourcePath = FileUtils.copyResource(file, file.getName(), uploadDir);
        if(resourcePath != null){
            resource.resourcePath = resourcePath;
            resource.resourceFileType = resourcePath.substring(resourcePath.lastIndexOf(".") + 1);
        }
        if(!resource.resourceFileType.toUpperCase().equals("FLV") && !resource.resourceFileType.toUpperCase().equals("RAR")
                && !resource.resourceFileType.toUpperCase().equals("EXE") && !resource.resourceFileType.toUpperCase().equals("CHM")){
            String resourceRealPath = uploadDir + "/" + resource.resourcePath.substring(resource.resourcePath.indexOf("/") + 1);
            Map<String,Object> message = new HashMap<String, Object>();
            message.put(ResourceConversion.RESOURCE_PATH,resourceRealPath);
            message.put(ResourceConversion.RESOURCE_TYPE,resource.resourceFileType);
            jedisPoolSource.rpush(Queues.RESOURCE_CONVERSION,message);
        }
        if(firstImage != null){
            String firstImagePath = FileUtils.copyAndCompressImage(firstImage,firstImage.getName(),
                    uploadDir,new String[]{ImageSize.RESOURCELUNBO});
            resource.firstImage = firstImagePath;
        }
        resource.description = description;
        resource.grade = Grade.findById(gradeId);
        resource.keys = keys;
        resource.needPoint = needPoint;
        resource.resourceSize = file.length();
        resource.subject = Subject.findById(subjectId);
        resource.title = title;
        resource.user = (UserBase)renderArgs.get("user");
        resource.version = Version.findById(versionId);
        resource.type = Type.findById(typeId);
        resource.uploadIp = request.remoteAddress;
        resource.create();
        list();
    }

    @UnSecure
    public static void show(Long resourceId,Boolean flag){
        String isAjax = params.get("isAjax");
        List<Resource> newResource = Resource.findNewResource(9);
        List<Resource> downResource = Resource.findDownResource(9);
        Page<ResourceComment> page = ResourceComment.findByResourceId(resourceId,getPage(),getPageSize());
        Resource resource = Resource.findById(resourceId);
        resource.clickCount = resource.clickCount + 1 ;
        resource.save();
        if(resource.resourceFlag != null && resource.resourceFlag == ResourceCommon.RESOURCE_FLAG_101){
            if(isAjax != null){
                render("Resources/show101_ajax.html",page,flag);
            }
            render("Resources/show101.html",resource,newResource,downResource,page);
        }
        if(isAjax != null){
            render("Resources/show_ajax.html",page,flag,resource);
        }
        render(resource,newResource,downResource,flag,page);
    }



    public static void list(){
        UserBase userBase = (UserBase) renderArgs.get("user");
        if(userBase.type == UserBaseType.TEACHER){
            Page<Resource> page = Resource.findByUserId(userBase.id,getPage(),getPageSize());
            render(page);
        }else{
            Page<ResourceDown> page = ResourceDown.findByUserId(userBase.id,getPage(),getPageSize());
            render("/Resources/plist.html",page);
        }
    }

    public static void download101(Resource resource){
        StringBuffer sb = new StringBuffer();
        int point = resource.needPoint;
        String resId = resource.resId;
        String userName = ((UserBase) renderArgs.get("user")).nickName;
        Long timestamp = new Date().getTime()/1000;
        String key = DigestUtils.md5Hex(KEY + userName + timestamp).toLowerCase();
        sb.append(DOWNLOADURL101).append("username=" + userName + "&")
                .append("timestamp=" + timestamp + "&")
                .append("point=" + point + "&")
                .append("resid=" + resId + "&")
                .append("key=" + key);
        String url = sb.toString();
        redirect(url);
    }

    public static void download(Long resourceId,String resId){
        UserBase downUserBase = (UserBase) renderArgs.get("user");
        Resource resource = Resource.findById(resourceId);
        if(downUserBase.points < resource.needPoint){
            render("Resources/download.html");
        }

        ResourceDown resourceDown1 = ResourceDown.findByResIdAndUserId(resourceId, downUserBase.id);
        if(resourceDown1 == null){
            Map<String,Object> download = PointType.newMessage(downUserBase.id,null,PointType.XIAZAIZIYUAN,resource.needPoint,PointType.MINUS_POINT);
            Map<String,Object> upload = PointType.newMessage(resource.user.id,null,PointType.ZIYUANBEIXIAZAI,resource.needPoint,PointType.ADD_POINT);
            jedisPoolSource.rpush(Queues.USER_POINTS,download);
            jedisPoolSource.rpush(Queues.USER_POINTS,upload);
            ResourceDown resourceDown = new ResourceDown();
            resourceDown.user = downUserBase;
            resourceDown.resource = resource;
            resourceDown.create();
        }
        resource.resourceDownNumber = resource.resourceDownNumber + 1;
        resource.save();
        if(resource.resourceFlag != null && resource.resourceFlag == ResourceCommon.RESOURCE_FLAG_101 && resId != null){
            download101(resource);
        }
        String sourcePathInDb = resource.resourcePath;
        if (sourcePathInDb.startsWith("/")) sourcePathInDb = sourcePathInDb.substring(1, sourcePathInDb.length());// convert "/upload/resources/aaa.doc" 2 "upload/resources/aaa.doc"
        String path = diskRoot + "/" + sourcePathInDb;
        File file = new File(path);
        renderBinary(file, resource.title + "." + resource.resourceFileType.toLowerCase());
    }

    @CacheFor
    @UnSecure
    public static void resourceList(Long versionId,Long typeId,Long subjectId,Long gradeId){
        List<Recommend> recommends = Recommend.findByArea(RecommendArea.RESOURCES_INDEX_ZUIXINTUIJIAN);
        List<Resource> newResource = new ArrayList<Resource>();
        for (Recommend recommend : recommends){
            newResource.add((Resource)Resource.findById(new Long(recommend.objectId)));
        }
        List<Resource> downResource = Resource.findDownResource(9);
        Page<Resource> page = null;
        if(versionId != null){
            page = Resource.findByVersionId(versionId,getPage(),getPageSize());
            Version version = Version.findById(versionId);
            render(page,version,newResource,downResource);
        }
        if(typeId != null){
            Type type = Type.findById(typeId);
            page = Resource.findByTypeId(typeId,getPage(),getPageSize());
            render(page,type,newResource,downResource);
        }
        if(subjectId != null){
            Subject subject = Subject.findById(subjectId);
            page = Resource.findBySubjectId(subjectId,getPage(),getPageSize());
            render(page,subject,newResource,downResource);
        }
        if(gradeId != null){
            Grade grade = Grade.findById(gradeId);
            page = Resource.findByGradeId(gradeId,getPage(),getPageSize());
            render(grade,page,newResource,downResource);
        }

    }

    @UnSecure
    public static void teacherShow(){
        Page<UserBase> page = Resource.findTeacher(getPage(),getPageSize());
        Map<UserBase,Long> map = new HashMap<UserBase, Long>();
        for (UserBase userBase : page.data){
            map.put(userBase,Resource.findByUserId(userBase.id,getPage(),getPageSize()).totalCount);
        }

        List<Recommend> recommends = Recommend.findByArea(RecommendArea.RESOURCES_INDEX_ZUIXINTUIJIAN);
        List<Resource> newResource = new ArrayList<Resource>();
        for (Recommend recommend : recommends){
            newResource.add((Resource)Resource.findById(new Long(recommend.objectId)));
        }
        List<Resource> downResource = Resource.findDownResource(9);
        Long totalCount = Resource.findTotalCount();

        render(page,map,newResource,downResource,totalCount);

    }
    public static void  delete(Long resourceId){
        if(resourceId != null){
            Resource resource = Resource.findById(resourceId);
            resource.state = Status.DELETED;
            resource.save();
        }
        list();
    }

    @UnSecure
    public static void search(String key){
        List<Resource> newResource = Resource.findNewResource(9);
        List<Resource> downResource = Resource.findDownResource(9);
        Page<Resource> page = Resource.findByKey(key,getPage(),getPageSize());
        render(page,newResource,downResource,key);
    }

    public static void play(Long resourceId){
        UserBase downUserBase = (UserBase) renderArgs.get("user");
        Resource resource = Resource.findById(resourceId);
        if(downUserBase.points < resource.needPoint){
            flash.put("error","积分不够！请充值");
            show(resourceId,null);
        }
        ResourceDown resourceDown1 = ResourceDown.findByResIdAndUserId(resourceId,downUserBase.id);
        if(resourceDown1 == null){
            Map<String,Object> download = PointType.newMessage(downUserBase.id,null,PointType.XIAZAIZIYUAN,resource.needPoint,PointType.MINUS_POINT);
            Map<String,Object> upload = PointType.newMessage(resource.user.id,null,PointType.ZIYUANBEIXIAZAI,resource.needPoint,PointType.ADD_POINT);
            jedisPoolSource.rpush(Queues.USER_POINTS,download);
            jedisPoolSource.rpush(Queues.USER_POINTS,upload);
            ResourceDown resourceDown = new ResourceDown();
            resourceDown.user = downUserBase;
            resourceDown.resource = resource;
            resourceDown.create();
        }
        resource.resourceDownNumber = resource.resourceDownNumber + 1;
        resource.save();
        show(resourceId, true);
    }

    public static void collect(Long resourceId){
        Resource resource = Resource.findById(resourceId);
        UserBase userBase = (UserBase) renderArgs.get("user");
        ResourceCollect resourceCollect1 = ResourceCollect.findByResIdAndUserId(resourceId, userBase.id);
        if(resourceCollect1 == null){
            ResourceCollect resourceCollect = new ResourceCollect();
            resourceCollect.user = userBase;
            resourceCollect.objectId = resource.id + "";
            resourceCollect.title = resource.title;
            resourceCollect.resourceType = resource.resourceFileType;
            resourceCollect.create();
            renderJSON("true");
        }else {
            renderJSON("false");
        }

    }


    public static void psearch(String key){
        UserBase userBase = (UserBase) renderArgs.get("user");
        if(userBase.type == UserBaseType.TEACHER){
            Page<Resource> page = Resource.findByKeyAndUserId(key,userBase.id,getPage(),getPageSize());
            render("/Resources/list.html",page,key);
        }else{
            Page<ResourceDown> page = ResourceDown.findByKeyAndUserId(key,userBase.id,getPage(),getPageSize());
            render("/Resources/plist.html",page,key);
        }
    }

}
