package com.zigui.service.impl;

import com.zigui.dao.KnowledgeRecommendRegionDao;
import com.zigui.dao.PointsHistoryDao;
import com.zigui.dao.UserBaseDao;
import com.zigui.domain.*;
import com.zigui.utils.Page;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserServiceImpl {

    private static final DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    @Autowired
    private UserBaseDao userBaseDao;

    @Autowired
    private PointsHistoryDao pointsHistoryDao;

    @Autowired
    private KnowledgeRecommendRegionDao knowledgeRecommendRegionDao;


    public long saveOrUpdate(UserBase userBase) {
        userBaseDao.saveOrUpdate(userBase);
        return userBase.getId();
    }


    public List<UserBase> getUserBaseByName(String name) {

        List<UserBase> userBases = userBaseDao.findBy(
                UserBase.class, "nickName", name);

        return userBases;
    }

    public void changePoints(PointsHistory history) {
        pointsHistoryDao.saveOrUpdate(history);

        UserBase user = userBaseDao.get(UserBase.class, history.getUser().getId());
        user.setPoints(user.getPoints() + history.getPointsChange() * (history.getFlag() == 2 ? -1 : 1));
        userBaseDao.saveOrUpdate(user);
    }

    public List<PointsHistory> getPointHistoryByType(UserBase user, int changeType) {
        return userBaseDao.find("from PointsHistory where user = ? and type = ? order by changeTime desc", new Object[]{user, changeType});

    }

    public Page<PointsHistory> getPointHistory(UserBase user, int pageNo, int pageSize, String queryString) {
        return pointsHistoryDao.pagedQuery("from PointsHistory where user = ? order by changeTime desc", pageNo, pageSize, queryString, new Object[]{user});

    }

    public UserBase getUserBaseByEmail(String email) {

        UserBase userBase = userBaseDao.findUniqueBy(
                UserBase.class, "email", email);

        return userBase;
    }

    public UserBase getUserBaseByMobile(String mobile) {

        UserBase userBase = userBaseDao.findUniqueBy(
                UserBase.class, "mobile", mobile);

        return userBase;
    }

    public Page<UserBase> getHotHome() {

        return userBaseDao.pagedQuery("from UserBase order by spacePv desc", 1, 10, new Object[0]);
    }

    public UserBase getUserBaseById(long id) {
        UserBase user = new UserBase();
        user = userBaseDao.get(UserBase.class, id);
        return user;
    }

    public UserBase getUserBaseByNickName(String nickName) {
        return userBaseDao.findUniqueBy(UserBase.class, "nickName", nickName);
    }

    public void recommend(Long[] ids, long regionId) {
        Criterion inCodition = Restrictions.in("id", ids);
        Page<UserBase> page = userBaseDao.pagedQuery(UserBase.class, 1, ids.length,
                inCodition);

        KnowledgeRecommendRegion knowledgeRecommendRegion = knowledgeRecommendRegionDao.get(KnowledgeRecommendRegion.class, regionId);

        for (UserBase userBase : page.getList()) {
            userBase.setKnowledgeRecommendRegion(knowledgeRecommendRegion);
            userBase.setRegionTime(new Date());
            userBaseDao.saveOrUpdate(userBase);
        }
    }

    public void fakeDelete(Long[] ids) {
        Criterion inCodition = Restrictions.in("id", ids);
        Page<UserBase> page = userBaseDao.pagedQuery(UserBase.class, 1, ids.length,
                inCodition);

        for (UserBase userBase : page.getList()) {
            userBase.setState(-1);
            userBaseDao.saveOrUpdate(userBase);
        }
    }

    public void restore(Long[] ids) {
        Criterion inCodition = Restrictions.in("id", ids);
        Page<UserBase> page = userBaseDao.pagedQuery(UserBase.class, 1, ids.length,
                inCodition);

        for (UserBase userBase : page.getList()) {
            userBase.setState(2);
            userBaseDao.saveOrUpdate(userBase);
        }
    }

    public List<UserBase> getAll() {
        return userBaseDao.getAll(UserBase.class);
    }

    public List<CcczgInfoSousown> getDownSourceByUser(UserBase ub) {
        return userBaseDao.find("from CcczgInfoSousown where user= ? order by createDate desc  ", new Object[]{ub});

    }

    public List<CcczgInfoSouce> getUploadSourceByUser(UserBase ub) {

        return userBaseDao.find("from CcczgInfoSouce c where c.member.id = ? and c.delsign=0 order by c.createDate desc ", new Object[]{ub.getId()});
    }


    public UserBase getUserBaseBySchoolForeignkey(long xx_id) {
        List<UserBase> list = userBaseDao.findBy(UserBase.class, "foreignKey", xx_id);
        for(UserBase userBase : list) {
            if (userBase.getType() == 1)
                return userBase;
        }
        return null;
    }
}
