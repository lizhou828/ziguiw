package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.zigui.domain.Authority;
import com.zigui.domain.Role;
import com.zigui.service.impl.AuthorityServiceImpl;
import com.zigui.service.impl.RoleServiceImpl;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleAction extends ActionSupport {
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private AuthorityServiceImpl authorityService;
	
	private Role role;
	private List<Role> roles;
	
	private Long[] authIds;
	
	private Long[] opIds;
	
	public void validateSaveOrUpdate(){
		if(StringUtils.isEmpty(role.getName())){
			this.addFieldError("role.name", "角色名字不能为空");
			return;
		}
		List<Role> roles = roleService.getRoleByName(role.getName());
		if(CollectionUtils.isEmpty(roles) && roles.size() > 0){
			this.addFieldError("role.name", "该角色已经存在");
			return;
		}
	}
	
	public String saveOrUpdate(){
		roleService.saveOrUpdate(role);
		
		return Action.SUCCESS;
	}
	
	public String getAll(){
		roles = roleService.getAll();
		
		Set<Role> tmpRoles = new HashSet<Role>();
		for(Role role : roles){
			tmpRoles.add(role);
		}
		
		roles = new ArrayList<Role>(tmpRoles);
		
		return Action.SUCCESS;
	}
	
	public void validateGrantAuth(){
		if(ArrayUtils.isEmpty(authIds)){
			this.addFieldError("authIds", "至少选中一个权限");
			return;
		}
		if(role.getId() < 1){
			this.addFieldError("role.id", "角色ID不能为空");
			return;
		}
	}
	
	public String getById(){
		role = roleService.getRoleById(role.getId());
		return Action.SUCCESS;
	}
	
	public String grantAuth(){
		List<Authority> authoritys = authorityService.getAuthoritysByIds(authIds);
		role = roleService.getRoleById(role.getId());
		
		role.setAuthoritys(authoritys);
		roleService.saveOrUpdate(role);
		
		return Action.SUCCESS;
	}
	
	public String deleteRole(){
		roleService.deleteRoles(opIds);
		
		return Action.SUCCESS;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Long[] getAuthIds() {
		return authIds;
	}

	public void setAuthIds(Long[] authIds) {
		this.authIds = authIds;
	}

	public Long[] getOpIds() {
		return opIds;
	}

	public void setOpIds(Long[] opIds) {
		this.opIds = opIds;
	}

}
