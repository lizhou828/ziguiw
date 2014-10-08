package com.zigui.service.impl;

import com.zigui.dao.NewsChannleDao;
import com.zigui.dao.NewsDao;
import com.zigui.dao.NewsRecommendRegionDao;
import com.zigui.domain.News;
import com.zigui.domain.NewsRecommendRegion;
import com.zigui.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class NewsServiceImpl {

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private NewsChannleDao newsChannleDao;
	
	@Autowired
	private NewsRecommendRegionDao newsRecommendRegionDao;

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public long saveOrUpdate(News news) {
		newsDao.saveOrUpdate(news);

		return news.getId();
	}
    public Page<News> queryForPage(Integer currentPage,Integer currentPerPage,Object...values){
        String hql="select n from News n where n.newsChannle.id = ? order by n.createTime desc";
        return newsDao.pagedQuery(hql,currentPage,currentPerPage,values);
    }

	public News getNewsById(long id) {
		return newsDao.get(News.class, id);
	}

	public void approveNews(Long[] ids, int approveFlag) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<News> page = newsDao.pagedQuery(News.class, 1, ids.length,
				inCodition);

		for (News news : page.getList()) {
			news.setState(approveFlag);

			newsDao.saveOrUpdate(news);
		}
	}

	public void fakeDeleteNews(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<News> page = newsDao.pagedQuery(News.class, 1, ids.length,
				inCodition);

		for (News news : page.getList()) {
			news.setState(-1);

			newsDao.saveOrUpdate(news);
		}
	}

	public void deleteNews(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<News> page = newsDao.pagedQuery(News.class, 1, ids.length,
				inCodition);

		for (News news : page.getList()) {
			newsDao.remove(news);
		}
	}
	
	public void restore(Long[] ids) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<News> page = newsDao.pagedQuery(News.class, 1, ids.length,
				inCodition);

		for (News news : page.getList()) {
			news.setState(1);

			newsDao.saveOrUpdate(news);
		}
	}
	
	public void recommend(Long[] ids, long regionId) {
		Criterion inCodition = Restrictions.in("id", ids);
		Page<News> page = newsDao.pagedQuery(News.class, 1, ids.length,
				inCodition);
		
		NewsRecommendRegion newsRecommendRegion = newsRecommendRegionDao.get(NewsRecommendRegion.class, regionId);

		for (News news : page.getList()) {
			news.setNewsRecommendRegion(newsRecommendRegion);

			newsDao.saveOrUpdate(news);
		}
	}

	public Page<News> getPagedNewsByCondition(Map<String, String> query,
			int currentPage, int countPerPage, String orderBy, boolean isAsc) {
		Criterion eqChannleName = null, likeTitle = null, eqProvice = null, eqState = null, beCreateTime = null;
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("1", 1));
		if (query != null) {
			if (StringUtils.isNotEmpty(query.get("channleName"))
					&& !query.get("channleName").equals("0")) {
				eqChannleName = Restrictions.eq("channle_id", query
						.get("channleName"));
				conjunction.add(eqChannleName);
			}
			if (StringUtils.isNotEmpty(query.get("title"))
					&& !query.get("title").equals("")) {
				likeTitle = Restrictions.like("title", query.get("title"));
				conjunction.add(likeTitle);
			}
			if (StringUtils.isNotEmpty(query.get("province"))
					&& !query.get("province").equals("")) {
				eqProvice = Restrictions.eq("province", query.get("province"));
				conjunction.add(eqProvice);
			}
			if (StringUtils.isNotEmpty(query.get("state"))
					&& !query.get("state").equals("")
					&& !query.get("state").equals("0")) {
				eqState = Restrictions.eq("state", query.get("state"));
				conjunction.add(eqState);
			} else {
				eqState = Restrictions.sizeGt("state", 0);
				conjunction.add(eqState);
			}
			if (StringUtils.isNotEmpty(query.get("startTime"))
					&& !query.get("startTime").equals("")
					&& StringUtils.isNotEmpty(query.get("endTime"))
					&& !query.get("endTime").equals("")) {

				try {
					Date startDate = format.parse(query.get("startTime"));
					Date endDate = format.parse(query.get("endTime"));
					beCreateTime = Restrictions.between("create_time",
							startDate.getTime(), endDate.getTime());
					conjunction.add(beCreateTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		Page<News> page = newsDao.pagedQuery(News.class, currentPage,
				countPerPage, orderBy, isAsc, conjunction);

		return page;
	}


}