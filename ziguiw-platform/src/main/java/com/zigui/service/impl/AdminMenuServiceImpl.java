package com.zigui.service.impl;

import com.zigui.domain.AdminMenu;
import com.zigui.domain.Administrator;
import com.zigui.domain.Role;
import com.zigui.service.IAdminMenuService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: YeQiang
 * Date: 12-12-25
 * Time: 上午9:44
 */
public class AdminMenuServiceImpl implements IAdminMenuService {

    private static List topList;
    private static List leftList;

    public AdminMenuServiceImpl() {
        if (topList == null) {
            topList = new ArrayList();
            topList.add(new AdminMenu(1, 0, "资源审核", "/admin/resource/resource_welcome.jsp"));
            topList.add(new AdminMenu(2, 0, "我的控制台", "/admin/myConsole/index.jsp"));
            topList.add(new AdminMenu(3, 0, "模版管理", "/admin/template/template_welcome.jsp"));
            topList.add(new AdminMenu(4, 0, "新闻管理", "/admin/news/news_welcome.jsp"));
            topList.add(new AdminMenu(5, 0, "用户管理", "/admin/user/user_welcome.jsp"));
            topList.add(new AdminMenu(6, 0, "权限管理", "/admin/auth/role_welcome.jsp"));
            topList.add(new AdminMenu(7, 0, "知识管理", "/admin/knowledge/knowledge_welcome.jsp"));
            topList.add(new AdminMenu(8, 0, "社区管理", "/admin/community/community_welcome.jsp"));
            topList.add(new AdminMenu(9, 0, "广告管理", "/admin/ad/ad_welcome.jsp"));
            topList.add(new AdminMenu(10, 0, "内容管理", "/admin/content/content_welcome.jsp"));
            topList.add(new AdminMenu(11, 0, "提醒管理", "/admin/notify/notify_welcome.jsp"));
            topList.add(new AdminMenu(12, 0, "认证管理", "/admin/vip/vip_welcome.jsp"));
            topList.add(new AdminMenu(13, 0, "配置中心", "/admin/config/config_welcome.jsp"));
        }
        if (leftList == null) {
            leftList = new ArrayList();
            leftList.add(new AdminMenu(101, 1, "资源审核", "/admin/resource/resourcesAudit_list.action"));

            leftList.add(new AdminMenu(201, 2, "我的首页", "/admin/myConsole/index.jsp"));
            leftList.add(new AdminMenu(202, 2, "修改密码", "/admin/myConsole/administrator_modifyPasswd.jsp"));

            leftList.add(new AdminMenu(301, 3, "查询模板", "/admin/template/template_getAll.action"));
            leftList.add(new AdminMenu(302, 3, "新增模板", "/admin/template/template_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(303, 3, "新增数据函数", "/admin/template/dataFunction_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(304, 3, "数据函数管理", "/admin/template/dataFunction_getAll.action"));

            leftList.add(new AdminMenu(401, 4, "查询频道", "/admin/news/newsChannle_getAll.action"));
            leftList.add(new AdminMenu(402, 4, "新增频道", "/admin/news/newsChannle_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(403, 4, "频道迁移", "/admin/news/newsChannleManager_move.jsp"));
            leftList.add(new AdminMenu(404, 4, "查询推荐位", "/admin/news/newsRecommendRegion_getAll.action"));
            leftList.add(new AdminMenu(405, 4, "新增推荐位", "/admin/news/newsRecommendRegion_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(406, 4, "查询新闻", "/admin/news/news_listNewsByCondition.jsp?opType=0"));
            leftList.add(new AdminMenu(407, 4, "审批新闻", "/admin/news/news_listNewsByCondition.action?opType=1&query.state=1"));
            leftList.add(new AdminMenu(408, 4, "恢复新闻", "/admin/news/news_listNewsByCondition.action?opType=2&query.state=-1"));
            leftList.add(new AdminMenu(409, 4, "新增新闻", "/admin/news/news_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(410, 4, "查询班级新闻", "/admin/news/classNews_listByCondition.action"));
            leftList.add(new AdminMenu(412, 4, "新增班级新闻", "/admin/news/classNews_view.action"));
            leftList.add(new AdminMenu(413, 4, "查询校园新闻", "/admin/news/schoolNews_listByCondition.action"));
            leftList.add(new AdminMenu(414, 4, "新增校园新闻", "/admin/news/schoolNews_view.action"));
            leftList.add(new AdminMenu(415, 4, "推荐新闻", "/admin/news/news_listNewsByCondition.action?opType=3&query.state=2"));

            leftList.add(new AdminMenu(501, 5, "用户信息管理", "/admin/user/user_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(502, 5, "推荐用户", "/admin/user/user_listByCondition.action?opType=1&query.state=1"));
            leftList.add(new AdminMenu(503, 5, "说说管理", "/admin/user/userMood_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(504, 5, "推荐说说", "/admin/user/userMood_listByCondition.action?opType=1&query.state=1"));
            leftList.add(new AdminMenu(505, 5, "日志管理", "/admin/user/userDiary_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(506, 5, "推荐日志", "/admin/user/userDiary_listByCondition.action?opType=1&query.state=1"));
            leftList.add(new AdminMenu(507, 5, "照片管理", "/admin/user/userPhoto_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(508, 5, "推荐照片", "/admin/user/userPhoto_listByCondition.action?opType=1&query.state=1"));
            leftList.add(new AdminMenu(509, 5, "积分管理", "/admin/user/points_history.jsp"));

            leftList.add(new AdminMenu(601, 6, "查询角色", "/admin/auth/role_getAll.jsp"));
            leftList.add(new AdminMenu(602, 6, "新增角色", "/admin/auth/role_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(603, 6, "新增权限", "/admin/auth/authority_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(604, 6, "查询权限", "/admin/auth/authority_getAll.jsp"));
            leftList.add(new AdminMenu(605, 6, "新增管理员", "/admin/auth/administrator_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(606, 6, "管理员列表", "/admin/auth/administrator_getAll.jsp"));

            leftList.add(new AdminMenu(701, 7, "查询频道", "/admin/knowledge/knowledgeChannle_getAll.action"));
            leftList.add(new AdminMenu(702, 7, "新增频道", "/admin/knowledge/knowledgeChannle_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(703, 7, "频道迁移", "/admin/knowledge/knowledgeChannleManager_move.jsp"));
            leftList.add(new AdminMenu(704, 7, "查询推荐位", "/admin/knowledge/knowledgeRecommendRegion_getAll.action"));
            leftList.add(new AdminMenu(705, 7, "新增推荐位", "/admin/knowledge/knowledgeRecommendRegion_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(706, 7, "查询知识", "/admin/knowledge/knowledge_listKnowledgesByCondition.jsp?opType=0"));
            leftList.add(new AdminMenu(707, 7, "审批知识", "/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=1&amp;query.state=1"));
            leftList.add(new AdminMenu(708, 7, "恢复知识", "/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=2&amp;query.state=-1"));
            leftList.add(new AdminMenu(709, 7, "新增知识", "/admin/knowledge/knowledge_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(710, 7, "推荐知识", "/admin/knowledge/knowledge_listKnowledgesByCondition.action?opType=3&amp;query.state=2"));
            leftList.add(new AdminMenu(711, 7, "查询提问", "/admin/knowledge/question_listByCondition.jsp?opType=0"));
            leftList.add(new AdminMenu(712, 7, "审批提问", "/admin/knowledge/question_listByCondition.action?opType=1&amp;query.state=1"));
            leftList.add(new AdminMenu(713, 7, "恢复提问", "/admin/knowledge/question_listByCondition.action?opType=2&amp;query.state=-1"));
            leftList.add(new AdminMenu(714, 7, "新增辩论", "/admin/knowledge/debate_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(715, 7, "查询辩论", "/admin/knowledge/debate_listByCondition.jsp?opType=0"));
            leftList.add(new AdminMenu(716, 7, "审批辩论", "/admin/knowledge/debate_listByCondition.action?opType=1&amp;query.state=1"));
            leftList.add(new AdminMenu(717, 7, "恢复辩论", "/admin/knowledge/debate_listByCondition.action?opType=2&amp;query.state=-1"));

            leftList.add(new AdminMenu(801, 8, "新增版块", "/admin/community/board_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(802, 8, "查询版块", "/admin/community/board_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(803, 8, "查询主贴", "/admin/community/forum_listByCondition_success.jsp?opType=0"));
            leftList.add(new AdminMenu(804, 8, "推荐主贴", "/admin/community/forum_listByCondition.action?opType=1&amp;query.state=1&amp;query.isnew=1"));
            leftList.add(new AdminMenu(805, 8, "恢复主贴", "/admin/community/forum_listByCondition.action?opType=2&amp;query.state=-1"));
            leftList.add(new AdminMenu(806, 8, "查询回贴", "/admin/community/forum_listByCondition_success.jsp?opType=10"));
            leftList.add(new AdminMenu(807, 8, "恢复回贴", "/admin/community/forum_listByCondition.action?opType=12&amp;query.state=-1"));
            leftList.add(new AdminMenu(808, 8, "新增爱心榜信息", "/admin/community/love_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(809, 8, "查询爱心榜信息", "/admin/community/love_listByCondition_success.jsp?opType=0"));

            leftList.add(new AdminMenu(901, 9, "新增广告位", "/admin/ad/ad_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(902, 9, "查询广告位", "/admin/ad/ad_getAll.action"));
            leftList.add(new AdminMenu(903, 9, "新增广告计划", "/admin/ad/adPlan_saveOrUpdate.jsp"));
            leftList.add(new AdminMenu(904, 9, "未执行广告计划", "/admin/ad/adPlan_getPlansByType.action?type=new"));
            leftList.add(new AdminMenu(905, 9, "已执行广告计划", "/admin/ad/adPlan_getPlansByType.action?type=old"));
            leftList.add(new AdminMenu(906, 9, "执行中广告计划", "/admin/ad/adPlan_getPlansByType.action?type=cur"));

            leftList.add(new AdminMenu(1001, 10, "新闻评论管理", "/admin/content/news_comment.jsp"));
            leftList.add(new AdminMenu(1002, 10, "相册评论管理", "/admin/content/photo_comment.jsp"));
            leftList.add(new AdminMenu(1003, 10, "留言管理", "/admin/content/message_list.jsp"));
            leftList.add(new AdminMenu(1004, 10, "新增照片，视频", "/admin/classPhoto/classPhoto_view.action"));
            leftList.add(new AdminMenu(1005, 10, "查看照片，视频", "/admin/classPhoto/classPhoto_list.action"));

            leftList.add(new AdminMenu(1101, 11, "内容提醒", "/admin/notify/notify_getAll_success.jsp"));

            leftList.add(new AdminMenu(1201, 12, "学校用户列表", "/admin/vip/school_vip.jsp"));
            leftList.add(new AdminMenu(1202, 12, "教师用户列表", "/admin/vip/teacher_vip.jsp"));
            leftList.add(new AdminMenu(1203, 12, "学生用户列表", "/admin/vip/student_vip.jsp"));
            leftList.add(new AdminMenu(1204, 12, "家长用户列表", "/admin/vip/parent_vip.jsp"));
            leftList.add(new AdminMenu(1205, 12, "学校用户审批", "/admin/vip/school_audit.jsp"));
            leftList.add(new AdminMenu(1206, 12, "教师用户审批", "/admin/vip/teacher_audit.jsp"));
            leftList.add(new AdminMenu(1207, 12, "学生用户审批", "/admin/vip/student_audit.jsp"));
            leftList.add(new AdminMenu(1208, 12, "家长用户审批", "/admin/vip/parent_audit.jsp"));

            leftList.add(new AdminMenu(1301, 13, "配置查看", "/admin/config/configCenter_getConfig.action"));
            leftList.add(new AdminMenu(1302, 13, "配置修改", "/admin/config/config_get4update.action"));
        }
    }

    @Override
    public List getTopMenu(Administrator admin) {

        if (admin == null)
            return null;
        if (admin.getNickName().startsWith("admin"))
            return topList;
        Role role = admin.getRole();
        List list = new ArrayList();
        for (int i = 0; i < topList.size(); i++) {
            for (int j = 0; j < role.getAuthoritys().size(); j++) {
                if (((AdminMenu) topList.get(i)).getUrl().equals(role.getAuthoritys().get(j).getUrl())) {
                    list.add(topList.get(i));
                }
            }
        }
        return list;
    }

    @Override
    public List getLeftMenu(Administrator admin, String requestURI) {
        if (admin == null)
            return null;
        Role role = admin.getRole();
        long parentId = 0;
        for (int i = 0; i < topList.size(); i++) {
            if (((AdminMenu) topList.get(i)).getUrl().equals(requestURI)) {
                parentId = ((AdminMenu) topList.get(i)).getId();
                break;
            }
        }

        if (parentId == 0) {
            for (int i = 0; i < leftList.size(); i++) {
                if (((AdminMenu) leftList.get(i)).getUrl().equals(requestURI)) {
                    parentId = ((AdminMenu) leftList.get(i)).getParentId();
                    break;
                }
            }
        }

        if (parentId == 0) {
            //bug code here.cased by <s:action/> tag!!!
            List list = new ArrayList();
            for (int i = 0; i < leftList.size(); i++) {
                if (((AdminMenu) leftList.get(i)).getParentId() != 0) {
                    if (admin.getNickName().startsWith("admin")) {
                        list.add(leftList.get(i));
                    } else {
                        for (int j = 0; j < admin.getRole().getAuthoritys().size(); j++) {
                            if (admin.getRole().getAuthoritys().get(j).getUrl()
                                    .equals(((AdminMenu) leftList.get(i)).getUrl())) {
                                list.add(leftList.get(i));
                                break;
                            }
                        }
                    }
                }
            }
            return list;
        } else {
            List list = new ArrayList();
            for (int i = 0; i < leftList.size(); i++) {
                if (((AdminMenu) leftList.get(i)).getParentId() == parentId) {
                    if (admin.getNickName().startsWith("admin")) {
                        list.add(leftList.get(i));
                    } else {
                        for (int j = 0; j < admin.getRole().getAuthoritys().size(); j++) {
                            if (admin.getRole().getAuthoritys().get(j).getUrl()
                                    .equals(((AdminMenu) leftList.get(i)).getUrl())) {
                                list.add(leftList.get(i));
                                break;
                            }
                        }
                    }
                }
            }
            return list;
        }
    }

    @Override
    public List getLeftMenu(Administrator admin, long menuId) {
        if(admin == null)
            return null;
        List list = new ArrayList();
        for (int i = 0; i < leftList.size(); i++) {
            if (((AdminMenu) leftList.get(i)).getParentId() == menuId) {
                if (admin.getNickName().startsWith("admin")) {
                    list.add(leftList.get(i));
                } else {
                    for (int j = 0; j < admin.getRole().getAuthoritys().size(); j++) {
                        if (admin.getRole().getAuthoritys().get(j).getUrl()
                                .equals(((AdminMenu) leftList.get(i)).getUrl())) {
                            list.add(leftList.get(i));
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }
}

