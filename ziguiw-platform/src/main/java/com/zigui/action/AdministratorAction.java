package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.Administrator;
import com.zigui.service.impl.AdministratorServiceImpl;
import com.zigui.utils.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 后台管理员接口
 * 
 * @author bertyang
 *
 */
public class AdministratorAction extends BaseAction {
	
	private Administrator administrator;
	
	private Page<Administrator> administrators;
	
	private int pageNo = 1;
	
	private int pageSize = 10000;
	
	private String newPasswd;
	private String secondPasswd;
	
	@Autowired
	private AdministratorServiceImpl administratorService;
	
	public String saveOrUpdate(){
		administratorService.saveOrUpdate(administrator);
		
		return Action.SUCCESS;
	}
	
	public String getAll(){
		administrators = administratorService.getAll(pageNo, pageSize);
		
		Set<Administrator> tmpAdministrators = new HashSet<Administrator>();
		for(Administrator administrator : administrators.getList()){
			tmpAdministrators.add(administrator);
		}
		
		List<Administrator> tmp = new ArrayList<Administrator>(tmpAdministrators);
		
		administrators.setList(tmp);
		
		return Action.SUCCESS;
	}
	
	public void validateLogin(){
		if(StringUtils.isEmpty(administrator.getNickName())){
			this.addFieldError("administrator.nickName", "用户名不能为空");
			return;
		}
		if(StringUtils.isEmpty(administrator.getPassword())){
			this.addFieldError("administrator.password", "用户密码不能为空");
			return;
		}
		
		Administrator tmpUser = null;
		
		if((tmpUser = administratorService.getByNickName(administrator.getNickName())) == null){
			this.addFieldError("administrator.nickName", "该用户名不存在");
			return;
		}
		
		if(!tmpUser.getPassword().equals(administrator.getPassword())){
			this.addFieldError("administrator.password", "密码不正确");
			return;
		}
	}
	
	public String login(){
		administrator = administratorService.getByNickName(administrator.getNickName());
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.put("ADMIN_USER", administrator);
		
		return Action.SUCCESS;
	}
	
	public void validateModifyPasswd(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		Administrator temp = (Administrator)session.get("ADMIN_USER");
		
		temp = administratorService.getByNickName(temp.getNickName());
		if(!StringUtils.equals(administrator.getPassword(), temp.getPassword())){
			this.addFieldError("administrator.password", "输入旧密码不正确");
			return;
		}
		
		if(!StringUtils.equals(newPasswd, secondPasswd)){
			this.addFieldError("newPasswd", "两次密码不正确");
			return;
		}
	}
	
	public String modifyPasswd(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		Administrator temp = (Administrator)session.get("ADMIN_USER");
		
		temp = administratorService.getByNickName(temp.getNickName());
		temp.setPassword(newPasswd);
		
		administratorService.saveOrUpdate(temp);
		
		session.put("ADMIN_USER", administrator);
		
		return Action.SUCCESS;
	}
	
	public String logout(){
		
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		session.remove("ADMIN_USER");
		
		return Action.SUCCESS;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public Page<Administrator> getAdministrators() {
		return administrators;
	}

	public void setAdministrators(Page<Administrator> administrators) {
		this.administrators = administrators;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSecondPasswd() {
		return secondPasswd;
	}

	public void setSecondPasswd(String secondPasswd) {
		this.secondPasswd = secondPasswd;
	}

	public String getNewPasswd() {
		return newPasswd;
	}

	public void setNewPasswd(String newPasswd) {
		this.newPasswd = newPasswd;
	}
	
	

}
