package models;

import com.arj.ziguiw.common.Status;
import com.arj.ziguiw.common.UserBaseType;
import org.apache.commons.logging.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import play.db.jpa.JPASupport;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * User: Liujy
 * Date: 13-3-19
 * Time: 下午4:01
 */
@Entity
@Table(name="r_resources")
@SequenceGenerator(
        name="R_RESOURCES_SEQ",
        sequenceName="r_resources_seq",
        allocationSize=1
)
public class Resource extends JPASupport {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="R_RESOURCES_SEQ")
    public Long id;


    @ManyToOne
    @JoinColumn(name="grade_id")
    public Grade grade;//年级

    @ManyToOne
    @JoinColumn(name="subject_id")
    public Subject subject;//科目

    @ManyToOne
    @JoinColumn(name="type_id")
    public Type type;//类型

    @ManyToOne
    @JoinColumn(name="user_id")
    public UserBase user;//会员

    @ManyToOne
    @JoinColumn(name="version_id")
    public Version version;//版本


    @Column(name = "title")
    public String title;

    @Column(name = "resource_path")
    public String resourcePath;

    @Column(name = "resource_down_Number")
    public Long resourceDownNumber = 0l; //下载次数

    @Column(name = "resource_size")
    public Long resourceSize;

    @Column(name = "upload_ip")
    public String uploadIp;

    @Column(name = "need_point")
    public Integer needPoint = 0;

    @Column(name = "keys")
    public String keys;

    @Column(name = "resource_file_type")
    public String resourceFileType;

    @Column(name ="description")
    public String description;

    @Column(name = "create_time")
    public Date createTime = new Date();

    @Column(name = "state")
    public Integer state = Status.UNVERIFIED;

    @Column(name = "first_image")
    public String firstImage;

    @Column(name = "click_count")
    public Integer clickCount = 0;
    
    @Column(name = "resource_flag")
    public Integer resourceFlag;

    @Column(name = "resId")
    public String resId;//同步外部资源的id

    @Column(name = "for_object")
    public String forObject;

    @Column(name = "knowledge")
    public String knowledge;

    public static String check(File file,String imageName, String title, Long typeId, Long versionId, Long gradeId, Long subjectId) {
        String error = "";
        if(file == null){
            error = "文件不能为空";
            return error;
        }
        if(title == null || "".equals(title)){
            error = "标题不能为空";
            return error;
        }
        if(imageName != null){
            imageName = imageName.toLowerCase();
            if(!imageName.endsWith("jpg") && !imageName.endsWith("gif") && !imageName.endsWith("png") && !imageName.endsWith("bmp")){
                error = "图片格式不正确";
                return error;
            }
        }
        if(typeId == null){
            return error = "类型不能为空";
        }
        Type type1 = Type.findById(typeId);
        if(!type1.suffix.contains(file.getName().substring(file.getName().lastIndexOf(".") + 1))){
            return error = "不允许的文件类型";
        }
        if(versionId == null){
            return error = "版本不能为空";
        }
        if(gradeId == null){
            return error = "年级不能为空";
        }
        if(subjectId == null){
            return error = "科目不能为空";
        }
        return error;
    }

    public static Page<Resource> findByUserId(long userId,Integer page,Integer pageSize) {
        String hql = "select r from Resource r where r.user.id = ? and r.state = ? order by createTime desc";
        List<Resource> list = Resource.find(hql,userId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.user.id = ? and r.state = ?";
        Long totalCount  = count(hql,userId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static List<Resource> findNewResource(Integer length) {
        String hql = "select r from Resource r where r.state = ? order by createTime desc";
        List<Resource> list = find(hql,Status.OK).fetch(1,length);
        return list;
    }

    public static List<Resource> findDownResource(Integer length) {
        String hql = "select r from Resource r where r.state = ? order by resourceDownNumber desc";
        List<Resource> list = find(hql,Status.OK).fetch(1,length);
        return list;
    }
    public static List<Resource> findByleve(int leve){
        String hql="from Resource r where r.grade.leve=?";
        return find(hql,leve).fetch();
    }


    public static List<Resource> elementHot(int leve,int count){
        String hql="from Resource r where r.grade.leve=?  order by r.resourceDownNumber desc ";
        return find(hql,leve).fetch(count);
    }

    public static List<Resource> elementNew(int leve,int count){
        String hql="from Resource r where r.grade.leve=? order by r.createTime desc";
        return find(hql,leve).fetch(count);
    }
    public  static String getSwfPath(String path){
        path = path.substring(0,path.lastIndexOf("."));
        String swfPath = path + ".swf";
        return swfPath;
    }

    public static Long findTotalCount() {
        return count("select count(*) from Resource r where r.state = ?",Status.OK);
    }


    public static List<Resource> findHdByLeve(Integer leve) {
        String hql = "select r from Resource r where r.grade.leve = ? and r.state = ? and r.firstImage is not null " +
                "order by createTime desc";
        return find(hql,leve,Status.OK).fetch(1,5);
    }

    public static List<Resource> findNewResourceByLeve(Integer leve) {
        String hql = "select r from Resource r where r.grade.leve = ? and r.state = ? order by createTime desc";
        return find(hql,leve,Status.OK).fetch(1,7);
    }

    public static Page<Resource> findByVersionId(Long versionId, Integer page, Integer pageSize) {
        String hql = "select r from Resource r where r.version.id = ? and r.state = ? order by r.createTime desc";
        List<Resource> list = find(hql,versionId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.version.id = ? and r.state = ?";
        Long totalCount = count(hql,versionId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static Page<Resource> findByTypeId(Long typeId, Integer page, Integer pageSize) {
        String hql = "select r from Resource r where r.type.id = ? and r.state = ? order by r.createTime desc";
        List<Resource> list = find(hql,typeId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.type.id = ? and r.state = ?";
        Long totalCount = count(hql,typeId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static Page<Resource> findBySubjectId(Long subjectId, Integer page, Integer pageSize) {
        String hql = "select r from Resource r where r.subject.id = ? and r.state = ? order by r.createTime desc";
        List<Resource> list = find(hql,subjectId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.subject.id = ? and r.state = ?";
        Long totalCount = count(hql,subjectId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static Page<Resource> findByGradeId(Long gradeId, Integer page, Integer pageSize) {
        String hql = "select r from Resource r where r.grade.id = ? and r.state = ? order by r.createTime desc";
        List<Resource> list = find(hql,gradeId,Status.OK).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.grade.id = ? and r.state = ?";
        Long totalCount = count(hql,gradeId,Status.OK);
        return new Page<Resource>(page,pageSize,totalCount,list);
    }

    public static Page<UserBase> findTeacher(Integer page,Integer pageSize){
        String hql = "select distinct  r.user  from Resource r where r.user.type = ? and r.user.nickName like 'res_%'";
        List<UserBase> userBaseList = find(hql,UserBaseType.TEACHER).fetch(page,pageSize);
        hql = "select count(distinct r.user) from Resource r where r.user.type = ? and r.user.nickName like 'res_%'";
        Long totalCount = count(hql,UserBaseType.TEACHER);
        return new Page<UserBase>(page,pageSize,totalCount,userBaseList);
    }

    public static String check(Resource resource,String imageName, String title, Long typeId, Long versionId, Long gradeId, Long subjectId) {
        String error = "";
        if(title == null || "".equals(title)){
            error = "标题不能为空";
            return error;
        }
        if(imageName != null){
            if(!imageName.endsWith("jpg") && !imageName.endsWith("gif") && !imageName.endsWith("png") && !imageName.endsWith("bmp")){
                error = "图片格式不正确";
                return error;
            }
        }
        if(typeId == null){
            return error = "类型不能为空";
        }
        Type type1 = Type.findById(typeId);
        if(!type1.suffix.contains(resource.resourceFileType)){
            return error = "不允许的文件类型";
        }
        if(versionId == null){
            return error = "版本不能为空";
        }
        if(gradeId == null){
            return error = "年级不能为空";
        }
        if(subjectId == null){
            return error = "科目不能为空";
        }
        return error;
    }

    public static Page<Resource> findByKey(String key,Integer page,Integer pageSize) {
        String hql = "select r from Resource r where r.title like '%"+key+"%' or r.type.typeName like '%"+key+"%' " +
                "or r.version.versionName like '%"+key+"%' or r.subject.subjectName like '%"+key+"%' " +
                "or r.grade.gradeName like '%"+key+"%' order by r.createTime desc";
        List<Resource> resourceList = find(hql).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.title like '%"+key+"%' or r.type.typeName like '%"+key+"%' " +
                "or r.version.versionName like '%"+key+"%' " +
                "or r.subject.subjectName like '%"+key+"%' " +
                "or r.grade.gradeName like '%"+key+"%'";
        Long totalCount = count(hql);
        return new Page<Resource>(page,pageSize,totalCount,resourceList);
    }


    public static void parseXml(String filePath,Log log) {
            String [] grades = {"","一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三","高一","高二","高三"};
            XmlPullParser xmlPullParser = getXmlPullParser();
            InputStream ips = null;
            File file = new File(filePath);
            File[] files = file.listFiles();
            Resource resource = null;
            outer:for(int x = 0; x < files.length; x++){
                if(files[x].exists()){
                    log.info(String.format("resourceXml name is %s",files[x].getName()));
                    try{
                        ips = new FileInputStream(files[x]);
                        xmlPullParser.setInput(ips,"UTF-8");
                        int eventType=xmlPullParser.getEventType();
                        while(eventType!=XmlPullParser.END_DOCUMENT){
                            String nodeName=xmlPullParser.getName();
                            switch (eventType) {
                                case XmlPullParser.START_DOCUMENT:
                                    resource = new Resource();
                                    resource.resourceFlag = ResourceCommon.RESOURCE_FLAG_101;
                                    break;
                                case XmlPullParser.START_TAG:
                                    if("resid".equals(nodeName)){
                                        resource.resId = xmlPullParser.nextText();
                                    }else if("title".equals(nodeName)){
                                        resource.title = xmlPullParser.nextText();
                                    }else if("grade".equals(nodeName)){
                                        String gradeId = xmlPullParser.nextText();
                                        if(gradeId == null || "".equals(gradeId))continue outer;
                                        Grade grade = Grade.findByName(grades[Integer.valueOf(gradeId)]);
                                        resource.grade = grade;
                                    }else if("subject".equals(nodeName)){
                                        resource.subject = Subject.findByName(xmlPullParser.nextText());
                                    }else if("version".equals(nodeName)){
                                        String versionName = xmlPullParser.nextText();
                                        if(versionName == null || "".equals(versionName))continue outer;
                                        Version version = Version.findByName(versionName);
                                        if(version == null){
                                            version = new Version();
                                            version.versionName = versionName;
                                            version.flag = ResourceCommon.RESOURCE_FLAG_101;
                                            version.create();
                                        }
                                        resource.version = version;
                                    }else if("type".equals(nodeName)){
                                        resource.type = Type.findByName(xmlPullParser.nextText());
                                    }else if("keyword".equals(nodeName)){
                                        resource.keys = xmlPullParser.nextText();
                                    }else if("file_size".equals(nodeName)){
                                        resource.resourceSize = Long.valueOf(xmlPullParser.nextText());
                                    }else if("point".equals(nodeName)){
                                        resource.needPoint = Integer.valueOf(xmlPullParser.nextText())/10;
                                    }else if("knowledge".equals(nodeName)){
                                        resource.knowledge = xmlPullParser.nextText();
                                    }else if("forobject".equals(nodeName)){
                                        resource.forObject = xmlPullParser.nextText();
                                    }else if("desc".equals(nodeName)){
                                        resource.description = xmlPullParser.nextText();
                                    }else if("mode".equals(nodeName)){
                                        resource.resourceFileType  = xmlPullParser.nextText();
                                    }
                                    break;
                                case XmlPullParser.END_TAG:
                                    if("resource".equals(nodeName)){
                                        resource.user = UserBase.find("byNickName",ResourceCommon.RESOURCE_USER_101).first();
                                        resource.state = Status.OK;
                                        resource.create();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            eventType = xmlPullParser.next();
                        }
                    } catch (XmlPullParserException e){
                        throw new RuntimeException(String.format("parse resourceXml fail name is %s",files[x].getName()),e);
                    } catch (IOException e){
                        throw new RuntimeException(String.format("parse resourceXml fail name is %s",files[x].getName()),e);
                    }finally {
                        if(ips != null){
                            try {
                                ips.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    log.info(String.format("resourceXml parse success name is %s", files[x].getName()));
                }
            }
    }

    public static XmlPullParser getXmlPullParser(){
        XmlPullParserFactory pullParserFactory = null;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=pullParserFactory.newPullParser();
            return xmlPullParser;
        } catch (XmlPullParserException e) {
            throw new RuntimeException("create XmlPullParser fail",e);
        }
    }

    public static Page<Resource> findByKeyAndUserId(String key, long userId, Integer page, Integer pageSize) {
        String hql = "select r from Resource r where r.user.id = ? and (r.title like '%"+key+"%' or r.type.typeName like '%"+key+"%' " +
                "or r.version.versionName like '%"+key+"%' or r.subject.subjectName like '%"+key+"%' " +
                "or r.grade.gradeName like '%"+key+"%') order by r.createTime desc";
        List<Resource> resourceList = find(hql,userId).fetch(page,pageSize);
        hql = "select count(*) from Resource r where r.user.id = ? and (r.title like '%"+key+"%' or r.type.typeName like '%"+key+"%' " +
                "or r.version.versionName like '%"+key+"%' " +
                "or r.subject.subjectName like '%"+key+"%' " +
                "or r.grade.gradeName like '%"+key+"%')";
        Long totalCount = count(hql,userId);
        return new Page<Resource>(page,pageSize,totalCount,resourceList);
    }
}
