package com.zigui.service;

import com.zigui.domain.Administrator;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-25
 * Time: 上午9:15
 */
public interface IAdminMenuService {
    public List getTopMenu(Administrator admin);

    public List getLeftMenu(Administrator admin, String requestURI);

    public List getLeftMenu(Administrator admin, long menuId);
}
