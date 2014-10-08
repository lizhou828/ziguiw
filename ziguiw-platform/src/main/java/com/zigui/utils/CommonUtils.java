/**
 * 
 */
package com.zigui.utils;

import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.CommonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommonUtils {
	
	private static CommonServiceImpl commonService = (CommonServiceImpl) BeanFactoryUtils.getBean("commonService");
	
	private static String countPerPage = "100";
	private static String currentPage = "1";
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	public static Map getHeadInfo(){
		//log.debug("test123");
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		Map<String, Object> session = context.getSession();
		String paraString = request.getQueryString();
		String url = request.getRequestURI();
		System.out.println("url is :" + url);
		System.out.println("paraString is :" + paraString);
		Map result = new HashMap();
		String title = null;
		String keywords = null;
		String description = null;
		if(url!=null){
			if(url.indexOf("/index.jsp")>=0){//首页
				title = "子贵网—子承厚望 贵修举国";
				keywords = "数字校园,教育行业新闻资讯,教育资源,教育课件,教育知识,学习方法";
				description = "子贵网—子承厚望 贵修举国。通过采用“一人一空间”形式，打造成集管理、教研、教学、学习、娱乐、分享、互动交流于一体的中国最大的孩子成长信息、教育资源门户云平台。子贵网不仅为全国家长提供孩子的考勤、作业、成绩等服务，为孩子的安全、学习信息提供有效参考，而且通过整合优质的教学资源，为全国高中小学各学科教师提供展示教学水平、实现教育资源交易、优质教育资源共享的平台。";
			}else if(url.indexOf("/community/forum_index.jsp")>=0){//互动社区首页
				title = "老师、家长、学生的教学及学习的交流社区_子贵网";
				keywords = "教学交流,学习经验,学习技巧,同龄圈,同城圈,爱心捐助,爱心捐款";
				description = "互动社区栏目秉承为学生健康成长提供一个绿色的网络交流平台经营理念，实时为学生家长解答学习问题，用户可在线交流学习经验及技巧，还专门为需要帮助的贫困孩子开通爱心通道等。";
			}else if(url.indexOf("/community/forum_list.jsp")>=0){//互动社区列表页
				String boardId = request.getParameter("boardId");
				Board board = commonService.get(Board.class, new Long(boardId).longValue());
				title = board.boardname+"_子贵网";
				keywords = "教学交流,学习经验,学习技巧,同龄圈,同城圈,爱心捐助,爱心捐款";
				description = "互动社区栏目秉承为学生健康成长提供一个绿色的网络交流平台经营理念，实时为学生家长解答学习问题，用户可在线交流学习经验及技巧，还专门为需要帮助的贫困孩子开通爱心通道等。";
			}else if(url.indexOf("/user/home_index.jsp")>=0){//我的小家
				title = "我的小家_子贵网";
				keywords = "我的空间,我的相册,我的日志,我的心情,交友互动,智力游戏";
				description = "我的小家是用户的一人一空间栏目。用户可以在自己的空间写日记，记录心情、建立相册上传照片、还可以交友互动、给好友留言等。子贵网还为学生用户准备了智力游戏，引导孩子增强学习主动性，并促进情商和智商的健康全面发展；";
			}else if(url.indexOf("/user/portal.jsp")>=0){//我的小家的最终显示页
				String nickName = request.getParameter("hostUserName");
				if(StringUtils.isEmpty(nickName)){
					nickName = "个人中心";
				}
				title = nickName+"_子贵网";
				keywords = nickName+"_子贵网";
				description = "我的小家是用户的一人一空间栏目。用户可以在自己的空间写日记，记录心情、建立相册上传照片、还可以交友互动、给好友留言等。子贵网还为学生用户准备了智力游戏，引导孩子增强学习主动性，并促进情商和智商的健康全面发展";
			}else if(url.indexOf("/page.action")>=0){//模板类
				String templateName = request.getParameter("templateName");
				if(templateName.equals("news_index.ftl")){//新闻首页
					title = "教育行业新闻资讯_子贵网";
					keywords = "教育新闻资讯,升学考试政策,校园动态,校园新闻";
					description = "家长及老师登录子贵网数字校园系统即可查询到孩子的学籍档案、考勤、作业、成绩、健康状况、成长足迹、学校/老师发布的通知、在校的消费信息等。既优化了教育管理，又搭建起了家长、学校、老师和学生四方信息的平台，增进了家校交流。";
				}else if(templateName.equals("news_list.ftl")){//新闻列表页
					String channleId = request.getParameter("channleId");
					NewsChannle channle = commonService.get(NewsChannle.class, new Long(channleId).longValue());
					title = channle.getName()+"_子贵网";
					keywords = "教育新闻资讯,升学考试政策,校园动态,校园新闻";
					description = "教育在线栏目致力于打造最大的教育新闻资讯平台。面向全国高中小院校及家长、教师等各类用户提供及时、权威的教育新闻，发布升学考试政策，关注校园动态，追踪新闻热点。频道下设：焦点话题报道、校园新闻、升学考试指南及择校等教育行业资讯栏目。";
				}else if(templateName.equals("news.ftl")){//新闻浏览页
					String newsId = request.getParameter("newsId");
					News news = commonService.get(News.class, new Long(newsId).longValue());
					title = news.getTitle()+"_子贵网";
					keywords = news.getKeywords();
					if(news.getDescription()!=null){
						description = news.getDescription();
					}else{
						description = news.getAutoDescription();
					}
				}else if(templateName.equals("knowledge_index.ftl")){//知识首页
					title = "专业丰富的教育经验、知识分享栏目_子贵网";
					keywords = "教育知识,教育经验,学习方法,心理咨询,育儿方法,教育心得";
					description = "教育知识栏目是一个专业的教育知识平台，提供丰富的教育孩子健康成长的知识，分享孩子教育经验及心得，帮助学校及家长正确认识孩子教育方法，让孩子健康成长。频道下设：幼儿期、少儿期、青年期教育引导及同龄同城圈探讨、育儿问答、心理咨询、话题辩论等交流平台。";
				}else if(templateName.equals("knowledge_list.ftl")){//知识列表页
					String channleId = request.getParameter("channleId");
					KnowledgeChannle channle = commonService.get(KnowledgeChannle.class, new Long(channleId).longValue());
					title = channle.getName()+"_子贵网";
					keywords = "教育知识,教育经验,学习方法,心理咨询,育儿方法,教育心得";
					description = "教育知识栏目是一个专业的教育知识平台，提供丰富的教育孩子健康成长的知识，分享孩子教育经验及心得，帮助学校及家长正确认识孩子教育方法，让孩子健康成长。频道下设：幼儿期、少儿期、青年期教育引导及同龄同城圈探讨、育儿问答、心理咨询、话题辩论等交流平台。";
				}else if(templateName.equals("question_detail.ftl")){//问答详情页
					String questionId = request.getParameter("questionId");
					Question question = commonService.get(Question.class, new Long(questionId).longValue());
					title = question.getTitle()+"_子贵网";
					keywords = question.getTitle()+"_子贵网";
					description = question.getContent();
				}else if(templateName.equals("debate_detail.ftl")){//辩论详情页
					String debateId = request.getParameter("debateId");
					Debate debate = commonService.get(Debate.class, new Long(debateId).longValue());
					title = debate.getTitle()+"_子贵网";
					keywords = debate.getTitle()+"_子贵网";
					description = debate.getDescription();
				}else if(templateName.equals("forum_detail.ftl")){//辩论详情页
					String forumId = request.getParameter("forumId");
					Forum forum = commonService.get(Forum.class, new Long(forumId).longValue());
					title = forum.getTitle()+"_子贵网";
					keywords = forum.getTitle()+"_子贵网";
					description = "互动社区栏目秉承为学生健康成长提供一个绿色的网络交流平台经营理念，实时为学生家长解答学习问题，用户可在线交流学习经验及技巧，还专门为需要帮助的贫困孩子开通爱心通道等。";
				}else{//
					title = "子贵网—子承厚望 贵修举国";
					keywords = "";
					description = "";
				}
			}else{//
				title = "子贵网—子承厚望 贵修举国";
				keywords = "";
				description = "";
			}
		}
		result.put("title", title);
		result.put("keywords", keywords);
		result.put("description", description);
		return result;
	}
	
	
	public static Map getUserInfo(long userId){
		Map resultMap = new HashMap();
		UserBase user = commonService.get(UserBase.class, userId);
		if(user.getState()>=5&&user.getForeignKey()!=null){
			
			Map map = new HashMap();
			List operObjs = new ArrayList();
			String str = "";
			if(user.getType()==4){//学生
				Student student = commonService.get(Student.class, user.getForeignKey().longValue());
				map.put("obj", student);
				map.put("state", 5);
				map.put("type", 4);
			}else if(user.getType()==3){//parents
				Parent parent = commonService.get(Parent.class, (int)user.getForeignKey().longValue());
				if(log.isDebugEnabled()){
					log.debug("parent(" + user.getForeignKey() + "):" + parent);
				}
				
				operObjs.add(getUserIdByXsID(parent.getStudent().getXs_id()));
				map.put("obj", parent);
				map.put("state", 5);
				map.put("type", 3);
				map.put("operobjs", operObjs);
				if(operObjs!=null&&!operObjs.isEmpty()){
					for(int i=0 ; i<operObjs.size() ; i++){
						str = str + operObjs.get(i) +";";
					}
				}
				map.put("operostrs", str);
				map.put("class", 0);
			}else if(user.getType()==2){//teacher
				Teacher teacher = commonService.get(Teacher.class, user.getForeignKey().longValue());
				//operObjs.add(parent.getXs_id());
				Page<Object> pageObj = commonService.getByHql("from TeacherClasz where teacherID = ?", new Integer(currentPage).intValue(),
						new Integer(countPerPage).intValue(), new Object[]{user.getForeignKey().intValue()});//班级教师关系对象
				if(pageObj!=null&&pageObj.getList()!=null){
					Page<Object> studObj = null;
					for(Object obj:pageObj.getList()){
						studObj = commonService.getByHql("from Student where Bj_ID = ?", new Integer(currentPage).intValue(), 
								new Integer(countPerPage).intValue(), new Object[]{new Integer((int)((TeacherClasz)obj).getClasz().getBj_id()).toString()});//学生对象
						if(studObj!=null&&studObj.getList()!=null){
							for(Object sobj:studObj.getList()){
								operObjs.add(getUserIdByXsID(((Student)sobj).getXs_id()));
							}
						}
						
					}
					Object obj1 = pageObj.getList().get(0);
				    map.put("class", ((TeacherClasz)obj1).getClasz().getBj_id());
				}else{
					 map.put("class", 0);
				}
				
				map.put("obj", teacher);
				map.put("state", 5);
				map.put("type", 2);
				map.put("operobjs", operObjs);
				
				//Clasz clasz = getClaszByTeacher(user).getTotalCount() > 0 ? (Clasz)getClaszByTeacher(user).getList().get(0) : new Clasz();
				//map.put("class", clasz);
				if(operObjs!=null&&!operObjs.isEmpty()){
					for(int i=0 ; i<operObjs.size() ; i++){
						str = str + operObjs.get(i) +";";
					}
				}
				map.put("operostrs", str);
			}else if(user.getType()==1){//school
				School school = commonService.get(School.class, user.getForeignKey().longValue());
				//operObjs.add(parent.getXs_id());
				Page<Object> pageObj = commonService.getByHql("from Clasz where XxID = ?", new Integer(currentPage).intValue(),
						new Integer(countPerPage).intValue(), new Object[]{school.getXx_ID()});//班级对象
				if(pageObj!=null&&pageObj.getList()!=null){
					Page<Object> studObj = null;
					for(Object obj:pageObj.getList()){
						studObj = commonService.getByHql("from Student where Bj_ID = ?", new Integer(currentPage).intValue(), 
								new Integer(countPerPage).intValue(), new Object[]{((Clasz)obj).getBj_id()});//学生对象
						for(Object sobj:studObj.getList()){
							operObjs.add(getUserIdByXsID(((Student)sobj).getXs_id()));
						}
					}
				}
				map.put("obj", school);
				map.put("state", 5);
				map.put("type", 1);
				map.put("operobjs", operObjs);
				if(operObjs!=null&&!operObjs.isEmpty()){
					for(int i=0 ; i<operObjs.size() ; i++){
						str = str + operObjs.get(i) +";";
					}
				}
				map.put("operostrs", str);
				map.put("class", 0);
			}else{
				map.put("class", 0);
			}
			resultMap.put(userId, map);
		}
		return resultMap;
	}
	
    public static long getUserIdByXsID(int xsid){
    	Page<Object> pageObj = commonService.getByHql("from UserBase where type = 4 and foreignKey = ?", new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), new Object[]{new Long(xsid)});
    	UserBase user = null;
    	if(pageObj!=null && pageObj.getList()!=null){
    		user = (UserBase)pageObj.getList().get(0);
    	}
    	if(user!=null){
    		return user.getId();
    	}else{
    		return 0;
    	}  	
    }
    
    public static UserBase getUserByXsID(int xsid){
    	Page<Object> pageObj = commonService.getByHql("from UserBase where type = 4 and foreignKey = ?", new Integer(currentPage).intValue(),
				new Integer(countPerPage).intValue(), new Object[]{new Long(xsid)});
    	UserBase user = null;
    	if(pageObj!=null && pageObj.getList()!=null){
    		user = (UserBase)pageObj.getList().get(0);
    	}
    	if(user!=null){
    		return user;
    	}else{
    		return null;
    	}  	
    }
    
    public static Page<Object> getClaszByTeacher(UserBase user){
		
		long teacherId = user.getForeignKey();
		
		return commonService.getByHql("from TeacherClasz where teacherID = ?", 1, 
				1000, new Object[]{(int)teacherId});
		
	}
    
    public static List<Student> getStudentByTeacher(UserBase user){
		
		long teacherId = user.getForeignKey();
		
		Page<Object> tmp1 = commonService.getByHql("from TeacherClasz where teacherID = ?", 1,
				1000, new Object[]{(int)teacherId});
		
		Page<TeacherClasz> tmp2 = CommonUtils.transform(TeacherClasz.class, tmp1);
		List<Student> students = new ArrayList<Student>();
		for(TeacherClasz teacherClasz : tmp2.getList()){
			Page<Object> tmp3 = commonService.getByHql("from Student where Bj_ID = ?", 1, 100, new Object[]{"" + teacherClasz.getClasz().getBj_id()});
			
			Page<Student> tmp4 = CommonUtils.transform(Student.class, tmp3);
			
			students.addAll(tmp4.getList());
		}
		
		return students;
		
	}
    
    public static Page<Object> getTeachersBySchool(UserBase user){
		
		long xx_id = user.getForeignKey();
		School school = commonService.get(School.class, xx_id);
		
		return commonService.getByHql("from Teacher where XxID = ?", new Integer(currentPage).intValue(), 
				new Integer(countPerPage).intValue(), new Object[]{school.getXxID()});
		
	}
    
    public static Long[] strsToLongs(String[] strs){
    	Long[] ids = new Long[strs.length];
		for(int i=0 ; i<strs.length ; i++){
			ids[i] = new Long(strs[i]);
		}
		return ids;
    }
    
    public static <T> Page<T> transform(Class<T> clazz, Page<Object> raw){
    	
    	if(raw == null || raw.getList() == null){
    		return null;
    	}
    	
    	List<T> list = new ArrayList<T>();
    	for(Object obj : raw.getList()){
    		list.add((T)obj);
    	}
    	
    	Page<T> result = new Page<T>(raw.getStartIndex(), raw.getTotalCount(), raw.getPageSize(), list);
    	
    	return result;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		CommonServiceImpl commonService = (CommonServiceImpl)BeanFactoryUtils.getBean("commonService");
//		Student student = commonService.get(Student.class, 9995);
		String test= DigestUtils.md5DigestAsHex("E10ADC3949BA59ABBE56E057F20F883E".getBytes());
		System.out.print(test);
	}

}
