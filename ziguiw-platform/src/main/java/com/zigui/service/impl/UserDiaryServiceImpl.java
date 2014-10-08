package com.zigui.service.impl;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.dao.UserDiaryDao;
import com.zigui.domain.KnowledgeRecommendRegion;
import com.zigui.domain.UserBase;
import com.zigui.domain.UserDiary;
import com.zigui.utils.LogTool;
import com.zigui.utils.Page;
import com.zigui.utils.enums.NewsStatus;
import com.zigui.utils.enums.UserDiaryType;
import com.zigui.vo.VSchoolNews;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDiaryServiceImpl {
    @Autowired
    private UserDiaryDao userDiaryDao;

    @Autowired
    private UserBaseDao userBaseDao;

    @Autowired
    private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public void saveOrUpdate(UserDiary diary) {
        userDiaryDao.saveOrUpdate(diary);
    }

    public List<UserDiary> getDiaryByStatus(int pageSize, int pageNum,
                                            int status) {
        return userDiaryDao.getDiaryByStatus(pageSize, pageNum, status);
    }

    public List<UserDiary> getHotDiary(int pageSize, int pageNum) {
        return userDiaryDao.getHotDiary(pageSize, pageNum);
    }

    public Page<UserDiary> getDiaryByUserId(int pageSize, int pageNum, long userId) {
        return userDiaryDao.pagedQuery("from UserDiary where user.id = ? order by id desc", pageNum, pageSize, new Object[]{userId});
    }

    public Page<UserDiary> getDiaryByUserIdAndType(int pageSize, int pageNum, int type, long userId) {
        return userDiaryDao.pagedQuery("from UserDiary where user.id = ? and type = ? and status = 2 order by id desc", pageNum, pageSize, new Object[]{userId, type});
    }


    public UserDiary getById(long id) {
        return userDiaryDao.queryById(id);
    }

    public Page listByCondition(Map queryCondition, int pageNo, int pageSize, String orderBy, boolean isAsc, String queryString) {

        List<Criterion> criterionsList = new ArrayList<Criterion>(queryCondition.size());


        if (StringUtils.isNotEmpty((String) queryCondition.get("startTime"))) {
            try {
                criterionsList.add(Restrictions.ge("createTime", format.parse((String) queryCondition.get("startTime"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isNotEmpty((String) queryCondition.get("endTime"))) {
            try {
                criterionsList.add(Restrictions.le("createTime", format.parse((String) queryCondition.get("endTime"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (queryCondition.get("status") != null && NumberUtils.toInt((String) queryCondition.get("status")) != 0) {
            criterionsList.add(Restrictions.eq("status", NumberUtils.toInt((String) queryCondition.get("status"))));
        }

        if (StringUtils.isNotEmpty((String) queryCondition.get("title"))) {
            criterionsList.add(Restrictions.like("title", "%" + queryCondition.get("title") + "%"));
        }

        if (queryCondition.get("userId") != null && !((String) queryCondition.get("userId")).equals("0")) {
            criterionsList.add(Restrictions.eq("user.id", (String) queryCondition.get("userId")));
        }

        if (StringUtils.isNotEmpty((String) queryCondition.get("userNick"))) {
            criterionsList.add(Restrictions.eq("user", userBaseDao.findUniqueBy(UserBase.class, "nickName", (String) queryCondition.get("userNick"))));
        }

        if (queryCondition.get("regionId") != null && !((String) queryCondition.get("regionId")).equals("0")) {
            criterionsList.add(Restrictions.eq("knowledgeRecommendRegion", knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, new Long((String) queryCondition.get("regionId")))));
        }

        Criterion[] criterions = new Criterion[criterionsList.size()];
        criterionsList.toArray(criterions);

        return userDiaryDao.pagedQuery(UserDiary.class, pageNo, pageSize, orderBy, isAsc, queryString, criterions);

    }

    public void delete(Long[] ids) {
        Criterion inCodition = Restrictions.in("id", ids);
        Page<UserDiary> page = userDiaryDao.pagedQuery(UserDiary.class, 1, ids.length,
                inCodition);

        for (UserDiary userDiary : page.getList()) {
            userDiaryDao.remove(userDiary);
        }
    }

    public void recommend(Long[] ids, long regionId) {
        Criterion inCodition = Restrictions.in("id", ids);
        Page<UserDiary> page = userDiaryDao.pagedQuery(UserDiary.class, 1, ids.length,
                inCodition);

        KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

        for (UserDiary userDiary : page.getList()) {
            userDiary.setKnowledgeRecommendRegion(knowledgeRecommendRegion);
            userDiaryDao.saveOrUpdate(userDiary);
        }
    }

    public void batchAuditPass(String[] ids) {
        for (String id : ids) {
            id = id.trim();
            UserDiary userDiary = (UserDiary) userDiaryDao.get(UserDiary.class, Long.parseLong(id));
            if (userDiary == null)
                throw new RuntimeException("target object not found!");
            userDiary.setStatus((int) NewsStatus.AUDIT_OK.getCode());
            userDiaryDao.updateObj(userDiary);
        }
    }

    public void auditFail(long classNewsId) {
        UserDiary userDiary = (UserDiary) userDiaryDao.get(UserDiary.class, classNewsId);
        if (userDiary == null)
            throw new RuntimeException("target object not found!");
        userDiary.setStatus((int) NewsStatus.AUDIT_FAILED.getCode());
        userDiaryDao.updateObj(userDiary);
    }

    public Page<UserDiary> getUserDiaryByCondition(Map<String, String> query, int pageNo, int pageSize, String queryString) {
        if (pageNo == 0)
            pageNo = 1;
        if (pageSize == 0)
            pageSize = 10;

        if (query != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<Criterion> criterionList = new ArrayList<Criterion>();
            String param;

            param = "startTime";
            if (org.apache.commons.lang.StringUtils.isNotEmpty((String) query.get(param))) {
                try {
                    criterionList.add(Restrictions.gt("createTime", dateFormat.parse((String) query.get(param))));
                } catch (ParseException e) {

                }
            }

            param = "endTime";
            if (org.apache.commons.lang.StringUtils.isNotEmpty((String) query.get(param))) {
                try {
                    criterionList.add(Restrictions.lt("createTime", dateFormat.parse((String) query.get(param))));
                } catch (ParseException e) {

                }
            }


            param = "title";
            if (org.apache.commons.lang.StringUtils.isNotEmpty((String) query.get(param))) {
                criterionList.add(Restrictions.like(param, (String) query.get(param)));
            }

            param = "status";
            if (org.apache.commons.lang.StringUtils.isNotEmpty((String) query.get(param))) {
                criterionList.add(Restrictions.eq(param, Integer.parseInt(query.get(param))));
            }
            
            param = "type";
            if (org.apache.commons.lang.StringUtils.isNotEmpty((String) query.get(param))) {
                criterionList.add(Restrictions.eq(param, Integer.parseInt(query.get(param))));
            }

            Criterion[] critions = new Criterion[criterionList.size()];
            criterionList.toArray(critions);

            return userDiaryDao.pagedQuery(UserDiary.class, pageNo, pageSize, "id", false, queryString, critions);

        } else {
            return userDiaryDao.pagedQuery(UserDiary.class, pageNo, pageSize, "id", false, queryString);
        }
    }

    public Page<VSchoolNews> getVSchoolNewsByCondition(Map<String, String> query, int pageNo, int pageSize, String queryString) {
        Page<UserDiary> list = getUserDiaryByCondition(query, pageNo, pageSize, queryString);
        Page<VSchoolNews> result = new Page<VSchoolNews>();
        result.setPageString(list.getPageString());
        List<VSchoolNews> tmpList = new ArrayList<VSchoolNews>();
        for (UserDiary userDiary : list.getList()) {
            VSchoolNews vSchoolNews = new VSchoolNews();
            vSchoolNews.setCreateTime(userDiary.getCreateTime().toString());
            vSchoolNews.setId(userDiary.getId());
            vSchoolNews.setTitle(userDiary.getTitle());
            NewsStatus newsStatus = NewsStatus.parse(userDiary.getStatus());
            if (newsStatus != null) {
                vSchoolNews.setStatus(newsStatus.getDisplayName());
            }
            vSchoolNews.setText(userDiary.getText());
            UserDiaryType userDiaryType = UserDiaryType.parse(userDiary.getType());
            if (userDiaryType != null) {
                vSchoolNews.setType(userDiaryType.getDisplayName());
            }
            vSchoolNews.setUser(userDiary.getUser().getNickName());
            if (userDiary.getClickCount() != null) {
                vSchoolNews.setViewCount(userDiary.getClickCount().toString());
            }
            
            try {
				vSchoolNews.setSchoolName(userDiary.getUser().getSchool().getSch_name());
			} catch (Exception e) {
				LogTool.getLog().error("cannot find data,will return null value", e);
			}
            tmpList.add(vSchoolNews);
        }
        result.setList(tmpList);
        return result;
    }

    public Page<UserDiary> getSchoolNews(int pageSize, int type) {
        return userDiaryDao.pagedQuery("from UserDiary where type = ? and status = 2 order by createTime desc", 1, pageSize, new Object[]{type});
    }
}
