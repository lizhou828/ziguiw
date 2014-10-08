package com.zigui.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.SouceSubject;
import com.zigui.service.SouceSubjectService;
import com.zigui.service.SourceService;



@Controller("sourceInfoAction")
@Scope("prototype")
public class SourceInfoAction extends BaseSourceAction{
	
	
	private static final long serialVersionUID = 1L;

	private List<SouceSubject> subjectList; // 科目

	private List<List<CcczgInfoSouce>> xiaoxueSourceLists;// 小学资源

	private List<List<CcczgInfoSouce>> chuzhongSourceLists;// 初中资源

	private List<List<CcczgInfoSouce>> gaozhongSourceLists;// 高中资源
	
	@Autowired
	private SourceService sourceService;
	@Autowired
	private SouceSubjectService souceSubjectService;
	
	private List<CcczgInfoSouce> newVadiosourceList;// 教学视频

	private List<CcczgInfoSouce> newSjuansourceList;// 考试试卷


	public String info() {		
		
			/*String l_username = getParameter("user");	
			System.out.println("l_username:"+l_username);
			if(l_username == null || "".equals(l_username)){
				
			}else{
				Member l_member = new Member();
				
				l_member = memberService.getMember(l_username);
				
				if(l_member!=null){
					setSession(Member.LOGIN_MEMBER_ID_SESSION_NAME, l_member.getMemberid());//写入session
					setSession(Member.LOGIN_MEMBER_USERNAME_COOKIE_NAME, l_member.getUsername());//写入session
					setSession(Member.LOGIN_MEMBER_TYPE, l_member.getMemberType());
					setSession(Member.LOGIN_MEMBER_SESSION_XXID, l_member.getXxid());//将会员所属学校id写入session
					setSession(Member.LOGIN_MEMBER_SESSION_DSISUSERID, l_member.getDsisuserid());
					setSession(Member.LOGIN_MEMBER_HEADPATH, l_member.getHeadpath());
					setSession(Member.LOGIN_MEMBER_NIKENAME, l_member.getNikename());
					setSession(Member.LOGIN_MEMBER_POINT, l_member.getMemberpoint());
					setSession(Member.LOGIN_MEMBER_EMAIL, l_member.getEmail());
					setSession(Member.LOGIN_SUCCESS_COUNT, l_member.getLoginsuccesscount());
					//return ajaxJsonSuccessMessage("会员登录成功！",String.valueOf(l_member.getMemberType()));
				}else{
					return ERROR;
				}
			}		*/
		
		subjectList = souceSubjectService.getAll();
		xiaoxueSourceLists = new ArrayList<List<CcczgInfoSouce>>();
		chuzhongSourceLists = new ArrayList<List<CcczgInfoSouce>>();
		gaozhongSourceLists = new ArrayList<List<CcczgInfoSouce>>();
		for (SouceSubject subject : subjectList) {
			xiaoxueSourceLists.add(sourceService.getListBySubjectIdAndLeve(
					subject.getSubjectId(), 1, S_LEN));
			chuzhongSourceLists.add(sourceService.getListBySubjectIdAndLeve(
					subject.getSubjectId(), 2, S_LEN));
			gaozhongSourceLists.add(sourceService.getListBySubjectIdAndLeve(
					subject.getSubjectId(), 3, S_LEN));
		}
		newVadiosourceList = sourceService.getListByTypeName("视频", LEN);
		newSjuansourceList = sourceService.getListByTypeName("试卷", LEN);
		newKejiansourceList = sourceService.getListByTypeName("课件", LEN);
		newTjiansourceList = sourceService.getListByCreateDateOrderRecommend(LEN);
		remenList = sourceService.getListByCreateDateOrderResourceDownnum(LEN);
		
		return SUCCESS;
	}
	
	
	public String schoolInfo() {
		subjectList = souceSubjectService.getAll();		
		Integer l_leve = leve;
		if (l_leve != null) {
			xiaoxueSourceLists = new ArrayList<List<CcczgInfoSouce>>();
			chuzhongSourceLists = new ArrayList<List<CcczgInfoSouce>>();
			gaozhongSourceLists = new ArrayList<List<CcczgInfoSouce>>();
			List<CcczgInfoSouce> list = null;
			for (int i=0;i<subjectList.size();i++) {
				SouceSubject subject = subjectList.get(i);
				switch (l_leve) {				
				case 1:
					list = sourceService
					.getListBySubjectIdAndLeve(subject.getSubjectId(),
							1, SCHOOLS_LEN);
					if(list.size() > 0){
						xiaoxueSourceLists.add(list);
					} else {
						subjectList.remove(subject);
						i--;
					}
					break;
				case 2:
					list = sourceService
					.getListBySubjectIdAndLeve(subject.getSubjectId(),
							2, SCHOOLS_LEN);
					if(list.size() > 0){
						chuzhongSourceLists.add(list);
					} else {
						subjectList.remove(subject);
						i--;
					}
					break;
				case 3:
					list = sourceService
					.getListBySubjectIdAndLeve(subject.getSubjectId(),
							3, SCHOOLS_LEN);
					if(list.size() > 0){
						gaozhongSourceLists.add(list);
					} else {
						subjectList.remove(subject);
						i--;
					}
					break;
				default:
					break;
				}
			}
			newRemensourceList = sourceService.getListOrderByResourceDownnum(l_leve, LEN);
			newZuixinsourceList = sourceService.getListByLeve(l_leve, LEN);
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<SouceSubject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SouceSubject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<List<CcczgInfoSouce>> getXiaoxueSourceLists() {
		return xiaoxueSourceLists;
	}

	public void setXiaoxueSourceLists(List<List<CcczgInfoSouce>> xiaoxueSourceLists) {
		this.xiaoxueSourceLists = xiaoxueSourceLists;
	}

	public List<List<CcczgInfoSouce>> getChuzhongSourceLists() {
		return chuzhongSourceLists;
	}

	public void setChuzhongSourceLists(
			List<List<CcczgInfoSouce>> chuzhongSourceLists) {
		this.chuzhongSourceLists = chuzhongSourceLists;
	}

	public List<List<CcczgInfoSouce>> getGaozhongSourceLists() {
		return gaozhongSourceLists;
	}

	public void setGaozhongSourceLists(
			List<List<CcczgInfoSouce>> gaozhongSourceLists) {
		this.gaozhongSourceLists = gaozhongSourceLists;
	}

	public SourceService getSourceService() {
		return sourceService;
	}

	public void setSourceService(SourceService sourceService) {
		this.sourceService = sourceService;
	}

	public SouceSubjectService getSouceSubjectService() {
		return souceSubjectService;
	}

	public void setSouceSubjectService(SouceSubjectService souceSubjectService) {
		this.souceSubjectService = souceSubjectService;
	}

	public List<CcczgInfoSouce> getNewVadiosourceList() {
		return newVadiosourceList;
	}

	public void setNewVadiosourceList(List<CcczgInfoSouce> newVadiosourceList) {
		this.newVadiosourceList = newVadiosourceList;
	}

	public List<CcczgInfoSouce> getNewSjuansourceList() {
		return newSjuansourceList;
	}

	public void setNewSjuansourceList(List<CcczgInfoSouce> newSjuansourceList) {
		this.newSjuansourceList = newSjuansourceList;
	}

	public List<CcczgInfoSouce> getNewKejiansourceList() {
		return newKejiansourceList;
	}

	public void setNewKejiansourceList(List<CcczgInfoSouce> newKejiansourceList) {
		this.newKejiansourceList = newKejiansourceList;
	}

	public List<CcczgInfoSouce> getNewTjiansourceList() {
		return newTjiansourceList;
	}

	public void setNewTjiansourceList(List<CcczgInfoSouce> newTjiansourceList) {
		this.newTjiansourceList = newTjiansourceList;
	}

	public List<CcczgInfoSouce> getNewRemensourceList() {
		return newRemensourceList;
	}

	public void setNewRemensourceList(List<CcczgInfoSouce> newRemensourceList) {
		this.newRemensourceList = newRemensourceList;
	}

	public List<CcczgInfoSouce> getNewZuixinsourceList() {
		return newZuixinsourceList;
	}

	public void setNewZuixinsourceList(List<CcczgInfoSouce> newZuixinsourceList) {
		this.newZuixinsourceList = newZuixinsourceList;
	}

	public List<CcczgInfoSouce> getRemenList() {
		return remenList;
	}

	public void setRemenList(List<CcczgInfoSouce> remenList) {
		this.remenList = remenList;
	}

	public Integer getLeve() {
		return leve;
	}

	public void setLeve(Integer leve) {
		this.leve = leve;
	}

	private List<CcczgInfoSouce> newKejiansourceList;// 教学课件

	private List<CcczgInfoSouce> newTjiansourceList;// 最新推荐

	private List<CcczgInfoSouce> newRemensourceList;// 热门资源

	private List<CcczgInfoSouce> newZuixinsourceList;// 最新资源
	
	private List<CcczgInfoSouce> remenList;//本月热门
	
	private Integer leve;

	private final Integer LEN = 9;// 数据条数

	private final Integer S_LEN = 12;// 横向菜单数据条数
	
	private final Integer SCHOOLS_LEN = 9;// school页面数据条数

}
