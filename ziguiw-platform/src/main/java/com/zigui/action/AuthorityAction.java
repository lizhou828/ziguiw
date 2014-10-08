package com.zigui.action;

import com.opensymphony.xwork2.Action;
import com.zigui.domain.Authority;
import com.zigui.service.IAdminMenuService;
import com.zigui.service.impl.AuthorityServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限
 */
public class AuthorityAction extends BaseAction {

    private Authority authority;
    private List<Authority> authoritys;
    private String authCodeType;

    @Autowired
    private AuthorityServiceImpl authorityService;

    @Resource
    private IAdminMenuService adminMenuService;

    public void validateSaveOrUpdate() {
        if (StringUtils.isEmpty(authority.getUrl())) {
            this.addFieldError("authority.url", "url不能为空");
            return;
        }

        if (authorityService.getAuthorityByUrl(authority.getUrl()) != null) {
            this.addFieldError("authority.url", "url已经存在");
            return;
        }
    }

    public String getMenu() {
        initMenu();
        return null;
    }

    public String saveOrUpdate() {
        authorityService.saveOrUpdate(authority);
        return Action.SUCCESS;
    }

    public String getAll() {
        authoritys = authorityService.getAll();
        return Action.SUCCESS;
    }

    public String getAuthByType() {
        authoritys = authorityService.getAuthorityByType(authCodeType);
        return Action.SUCCESS;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<Authority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(List<Authority> authoritys) {
        this.authoritys = authoritys;
    }

    public String getAuthCodeType() {
        return authCodeType;
    }

    public void setAuthCodeType(String authCodeType) {
        this.authCodeType = authCodeType;
    }

    public IAdminMenuService getAdminMenuService() {
        return adminMenuService;
    }

    public void setAdminMenuService(IAdminMenuService adminMenuService) {
        this.adminMenuService = adminMenuService;
    }

}
