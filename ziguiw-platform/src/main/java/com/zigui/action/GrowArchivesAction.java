package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.zigui.domain.*;
import com.zigui.service.impl.CommonServiceImpl;
import com.zigui.service.impl.GrowArchivesServiceImpl;
import com.zigui.utils.CommonUtils;
import com.zigui.utils.Constant;
import com.zigui.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GrowArchivesAction extends BaseAction {
	
	private static final long serialVersionUID = 669965698029364302L;
	
	private static Log log = LogFactory.getLog("BUSINESS_DATA");
	
	@Autowired
	private GrowArchivesServiceImpl growArchivesService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	private GrowArchives growArchives;
	
	private Map<String, String> query;
	
	private Page<GrowArchives> growArchivess=new Page<GrowArchives>();

    private Teacher teacher;
    private Parent parent;
    private Student student;
    private List<Student> studentList=new ArrayList<Student>();
    private Integer studentId=0;
    private Integer bjId=0;
	private String userId;
    private Integer userType;
    private Long foreignKey;
	private String sid;
	
	private boolean isAsc = false;
	private long growArchivesId;
	private int queryType;
	private Long[] opIds;
	private Long totalCount;//总记录数
    private Long totalPage;//总页数
	private String countPerPage = "10";
	private String currentPage;
	private Integer gotoPage=1;
	private String orderField = "createTime";
    private List<Integer> pageList=new ArrayList<Integer>();
	
	public String add(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		String sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			if(log.isDebugEnabled()){
				log.debug("student:" + student);
			}
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.TEACHER_TYPE){
			return "";
		}
		
		
		Page<Object> pageObj = commonService.getByHql("from GrowArchives where userId=?", 1, 1, new Object[]{new Long(sid)});
		if(pageObj!=null&&pageObj.getList()!=null&&pageObj.getList().size()>0){
			growArchives = (GrowArchives)pageObj.getList().get(0);
		}
		return Action.SUCCESS;
	}
	
	public String saveOrUpdate(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		
		UserBase ub = (UserBase)session.get("VALID_USER");
		String sid = "" + ub.getId();
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			Parent parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
			Student student = commonService.get(Student.class, parent.getStudent().getXs_id());
			
			System.out.println("student11" + student);
			
			sid = "" + CommonUtils.getUserIdByXsID(student.getXs_id());
			
			System.out.println("sid" + sid);
			
			growArchives.setStudent(student);
		}
		
		if(ub.getType() != null && ub.getType().intValue() == Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
			Student student = commonService.get(Student.class, (int)ub.getForeignKey().longValue());
			
			growArchives.setStudent(student);
		}
		
		if(ub.getType() != null && (ub.getType().intValue() == Constant.PARENT_TYPE || ub.getType().intValue() == Constant.STUDENT_TYPE)){
			
			growArchives.setUserId(new Long(sid));
		}
		if(ub.getType() != null && ub.getType().intValue() == Constant.TEACHER_TYPE){
			GrowArchives temp = commonService.get(GrowArchives.class, growArchives.getId());
			growArchives.setStudent(temp.getStudent());
		}
		growArchives.setCreatorId(user.getId());
		growArchives.setCreatorNick(user.getNickName());
		growArchivesService.saveOrUpdate(growArchives);	
		userId = new Long(growArchives.getUserId()).toString();
		return Action.SUCCESS;
	}
	
	public String listByCondition(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserBase ub = (UserBase)session.get("VALID_USER");
        userType=ub.getType();

		if(ub.getType() != null && userType == Constant.PARENT_TYPE && ub.getForeignKey() != 0L){
			parent = commonService.get(Parent.class, (int)ub.getForeignKey().longValue());
            studentList=parentService.findChildren(parent.getMobliephone());
            //if parent have more than one child
            if(studentList == null || studentList.size() == 0) return SUCCESS;
            if(studentId <= 0){
               student=studentList.get(0);
            }else {
                student=studentService.getById(studentId);
            }
            //page query student's grow archives
            if(currentPage == null ||currentPage==""){
                currentPage="1";
            }else {
                currentPage=""+gotoPage;
            }
            growArchivess=growArchivesService.getByStudentId( new Integer(currentPage),new Integer(countPerPage),student.getXs_id());
		}
		
		if(ub.getType() != null && userType== Constant.STUDENT_TYPE && ub.getForeignKey() != 0L){
			student =studentService.getById(ub.getForeignKey().intValue());
            //page query student's grow archives
            if(currentPage == null ||currentPage==""){
                currentPage="1";
            }else {
                currentPage=""+gotoPage;
            }
            growArchivess=growArchivesService.getByStudentId( new Integer(currentPage),new Integer(countPerPage),student.getXs_id());
		}
		
		if(ub.getType() != null && userType == Constant.TEACHER_TYPE && ub.getForeignKey() != 0L){
            if(currentPage==null || currentPage==""){
                currentPage="1";
            }else {
                currentPage=gotoPage+"";
            }
            countPerPage="5";
            if(studentId > 0){
                growArchivess=growArchivesService.getByStudentId(new Integer(currentPage),new Integer(countPerPage),studentId);
                student=studentService.getById(studentId);
            }else {
                growArchivess=growArchivesService.pageQueryByBjId(
                        new Integer(currentPage),new Integer(countPerPage) , bjId.longValue());
            }
            totalCount=growArchivess.getTotalCount();
            Long pageSize=new Long (countPerPage);
            totalPage= totalCount%pageSize==0 ? totalCount/pageSize :(totalCount/pageSize)+1;
            for(int i=0;i<totalPage;i++){
                pageList.add(i+1);
            }
		}
		return Action.SUCCESS;
	}
	
	public String getById(){		
		growArchives = growArchivesService.getById(growArchivesId);
		return Action.SUCCESS;
	}

	/**
	 * 删除健康档案
	 * @return                                                                                                                                                                    	 */
	public String fakeDelete(){
		
		//改为真实删除，假删没有意义
		growArchivesService.delete(opIds);
		return Action.SUCCESS;
	}
	
	public GrowArchives getGrowArchives() {
		return growArchives;
	}

	public void setGrowArchives(GrowArchives growArchives) {
		this.growArchives = growArchives;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public void setQuery(Map<String, String> query) {
		this.query = query;
	}

	public Page<GrowArchives> getGrowArchivess() {
		return growArchivess;
	}

	public void setGrowArchivess(Page<GrowArchives> growArchivess) {
		this.growArchivess = growArchivess;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public long getGrowArchivesId() {
		return growArchivesId;
	}

	public void setGrowArchivesId(long growArchivesId) {
		this.growArchivesId = growArchivesId;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(Long foreignKey) {
        this.foreignKey = foreignKey;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Integer getBjId() {
        return bjId;
    }

    public void setBjId(Integer bjId) {
        this.bjId = bjId;
    }

    public Integer getGotoPage() {
        return gotoPage;
    }

    public void setGotoPage(Integer gotoPage) {
        this.gotoPage = gotoPage;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }
}
