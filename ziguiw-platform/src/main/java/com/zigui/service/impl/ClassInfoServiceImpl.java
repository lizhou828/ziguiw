package com.zigui.service.impl;

import com.zigui.dao.ClassInfoDao;
import com.zigui.dao.ClassPhotoDao;
import com.zigui.dao.SchoolDao;
import com.zigui.domain.ClassNews;
import com.zigui.domain.ClassPhoto;
import com.zigui.domain.Clasz;
import com.zigui.domain.School;
import com.zigui.utils.ConfigUtils;
import com.zigui.utils.LogTool;
import com.zigui.utils.Page;
import com.zigui.utils.enums.NewsStatus;
import com.zigui.utils.enums.NewsType;
import com.zigui.vo.VClassNews;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: liumengjie Date: 12-12-18 Time: PM4:35
 */
public class ClassInfoServiceImpl {
	@Autowired
	private ClassInfoDao classInfoDao;

	@Autowired
	private SchoolDao schoolDao;

	@Autowired
	private ClassPhotoDao classPhotoDao;

	public Page<ClassNews> getClassNewByClassid(int pageNum, int pageSize,
			int type, long class_id) {
		String hql = "from ClassNews where class_id = ? and type = ? and status = 2 order by create_time desc";
		return classInfoDao.pagedQuery(hql, pageNum, pageSize, new Object[] {
				class_id, new Long(type) });
	}

	public Page<ClassPhoto> getClassPhotoByClassid(int pageNum, int pageSize,
			int type, long class_id,long isRecommend) {
		String hql = "from ClassPhoto where class_id = ? and type = ? and status = 2 and isRecommend = ? order by create_time desc";
		return classInfoDao.pagedQuery(hql, pageNum, pageSize, new Object[] {
				class_id, new Long(type),isRecommend });
	}

    public Page<ClassPhoto> getClassSpByClassid(int pageNum, int pageSize,
                                                   int type, long class_id) {
        String hql = "from ClassPhoto where class_id = ? and type = ? and status = 2 order by create_time desc";
        return classInfoDao.pagedQuery(hql, pageNum, pageSize, new Object[] {
                class_id, new Long(type) });
    }

	public void saveOrUpdateClassNews(ClassNews classNews) {
		classInfoDao.saveOrUpdate(classNews);
	}

	public ClassNews getClassNewsById(long id) {
		return (ClassNews) classInfoDao.get(ClassNews.class, id);
	}

	public ClassPhoto getClassPhotoById(long id) {
		return (ClassPhoto) classInfoDao.get(ClassPhoto.class, id);
	}

	public Page<ClassNews> getClassNewsByCondition(Map<String, String> query,
			int pageNo, int pageSize, String queryString) {

		if (pageNo == 0)
			pageNo = 1;
		if (pageSize == 0)
			pageSize = 10;

		if (query != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Criterion> criterionList = new ArrayList<Criterion>();
			String param;

			param = "startTime";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				try {
					criterionList.add(Restrictions.gt("create_time",
							dateFormat.parse((String) query.get(param))));
				} catch (ParseException e) {

				}
			}

			param = "endTime";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				try {
					criterionList.add(Restrictions.lt("create_time",
							dateFormat.parse((String) query.get(param))));
				} catch (ParseException e) {

				}
			}

			param = "title";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like(param,
						(String) query.get(param)));
			}

			param = "class_id";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like(param,
						Long.parseLong(query.get(param))));
			}
			param = "xx_id";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like(param,
						(String) query.get(param)));
			}
			param = "type";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like(param,
						Long.parseLong(query.get(param))));
			}
			param = "status";
			if (StringUtils.isNotEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like(param,
						Long.parseLong(query.get(param))));
			}

			Criterion[] critions = new Criterion[criterionList.size()];
			criterionList.toArray(critions);

			return classInfoDao.pagedQuery(ClassNews.class, pageNo, pageSize,
					"id", false, queryString, critions);
		} else {
			return classInfoDao.pagedQuery(ClassNews.class, pageNo, pageSize,
					"id", false, queryString);
		}
	}

	public Page<VClassNews> getVClassNewByCondition(Map<String, String> query,
			int pageNo, int pageSize, String queryString) {
		Page<ClassNews> list = getClassNewsByCondition(query, pageNo, pageSize,
				queryString);
		Page<VClassNews> result = new Page<VClassNews>();
		result.setPageString(list.getPageString());
		List<VClassNews> tmplist = new ArrayList<VClassNews>();
		// low performance,should think about db cache，this code must enable
		// hibernate cache
		for (ClassNews item : list.getList()) {
			try {
				VClassNews vClassNews = new VClassNews();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Clasz clasz = (Clasz) this.classInfoDao.get(Clasz.class,
						item.getClass_id());
				if (clasz != null) {
					vClassNews.setClassName(clasz.getBj_mcheng());
				}
				vClassNews.setContent(item.getContent());
				vClassNews.setCreateTime(dateFormat.format(item
						.getCreate_time()));
				vClassNews.setId(item.getId());
				List<School> schoolList = this.schoolDao.findBy(School.class,
						"XxID", item.getXx_id());
				if (schoolList != null && schoolList.size() > 0
						&& schoolList.get(0) != null) {
					vClassNews.setSchoolName(schoolList.get(0).getSch_name());
				}
				NewsStatus newsStatus = NewsStatus.parse(item.getStatus());
				if (newsStatus != null) {
					vClassNews.setStatus(newsStatus.getDisplayName());
				}
				vClassNews.setTitle(item.getTitle());
				NewsType newsType = NewsType.parse(item.getType());
				if (newsType != null) {
					vClassNews.setType(newsType.getDisplayName());
				}
				tmplist.add(vClassNews);
			} catch (Exception e) {
				LogTool.getLog().error(e);
			}
		}
		result.setList(tmplist);
		return result;
	}

	public void batchAuditPass(String[] ids) {
		for (String id : ids) {
			id = id.trim();
			ClassNews classNews = (ClassNews) classInfoDao.get(ClassNews.class,
					Long.parseLong(id));
			if (classNews == null)
				throw new RuntimeException("target object not found!");
			classNews.setStatus(NewsStatus.AUDIT_OK.getCode());
			classInfoDao.updateObj(classNews);
		}
	}

	public void auditFail(long classNewsId) {
		ClassNews classNews = (ClassNews) classInfoDao.get(ClassNews.class,
				classNewsId);
		if (classNews == null)
			throw new RuntimeException("target object not found!");
		classNews.setStatus(NewsStatus.AUDIT_FAILED.getCode());
		classInfoDao.updateObj(classNews);
	}

	public List<ClassPhoto> getClassPhotoByClassId(long class_id) {
		String hql = "from ClassPhoto where class_id = ? and type = 0 and status = 2 and isRecommend = 0";
		return classInfoDao.find(hql, new Object[] { class_id });
	}

	public void saveOrUpdateClassPhoto(ClassPhoto classPhotoItem) {
		this.classPhotoDao.saveOrUpdate(classPhotoItem);
	}

	public ClassPhoto getClassSinglePhotoByClassId(long class_id) {
		return this.classPhotoDao.get(ClassPhoto.class, class_id);
	}

	public Page<ClassPhoto> getClassPhoto(Map query, int pageNo, int pageSize,
			String queryString) {
		if (pageNo == 0)
			pageNo = 1;
		if (pageSize == 0)
			pageSize = 10;

		if (query != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Criterion> criterionList = new ArrayList<Criterion>();
			String param;

			param = "class_id";
			if (query.get(param) != null
					&& !StringUtils.isEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.eq("class_id",
						Long.parseLong((String) query.get(param))));
			}
			param = "xx_id";
			if (query.get(param) != null
					&& !StringUtils.isEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.eq("xx_id",
						(String) query.get(param)));
			}
			param = "type";
			if (query.get(param) != null
					&& !StringUtils.isEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.eq("type",
						Long.parseLong((String) query.get(param))));
			}
			param = "title";
			if (query.get(param) != null
					&& !StringUtils.isEmpty((String) query.get(param))) {
				criterionList.add(Restrictions.like("title",
						(String) query.get(param)));
			}

			Criterion[] critions = new Criterion[criterionList.size()];
			criterionList.toArray(critions);

			return classPhotoDao.pagedQuery(ClassPhoto.class, pageNo, pageSize,
					"id", false, queryString, critions);
		} else {
			return classPhotoDao.pagedQuery(ClassPhoto.class, pageNo, pageSize,
					"id", false, queryString);
		}
	}

	public void deleteClassPhotoById(Long id) {
		ClassPhoto classPhoto = classPhotoDao.get(ClassPhoto.class, id);
		if (classPhoto == null) {
			throw new RuntimeException(
					"[ClassPhoto] not found when get(ClassPhoto.class," + id
							+ ")");
		}
		String absoluteFilePath = null;
		try {
			String rootDir = ConfigUtils.getProperty("static.savedPath");// /data/upload
			absoluteFilePath = rootDir.substring(0, rootDir.lastIndexOf('/') + 1)
					+ classPhoto.getUrl();
			File file = new File(absoluteFilePath);
			file.delete();
		} catch (Exception e) {
			LogTool.getLog().error(
					"Cannot delete file[" + absoluteFilePath + "]", e);
		}
		classPhotoDao.remove(classPhoto);
	}

}
