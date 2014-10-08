package com.zigui.service.impl;

import com.zigui.dao.SchoolDao;
import com.zigui.dao.SchoolInfoDao;
import com.zigui.domain.School;
import com.zigui.domain.SchoolInfo;
import com.zigui.exception.UserNotExistException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SchoolServiceImpl {
	
	@Autowired
	private SchoolDao schoolDao;
	
	@Autowired
	private SchoolInfoDao schoolInfoDao;
	
	public long saveOrUpdate(School school){
		schoolDao.saveOrUpdate(school);
		return school.getXx_ID();
	}
	
	public School getById(long id){
		try{
            return schoolDao.get(School.class, id);
        }catch (Exception e){
            throw new UserNotExistException("无此学校或未绑定该学校！");
        }
		
	}
	
	public long saveOrUpdateInfo(SchoolInfo info){
		SchoolInfo existedInfo = getInfoBySchoolId(info.getSchoolId());
		if(existedInfo == null){
			schoolInfoDao.saveObj(info);
		}else{
			schoolInfoDao.updateObj(info);
		}
		return info.getSchoolId();
	}
	
	public SchoolInfo getInfoBySchoolId(long schoolId){
		List<SchoolInfo> schoolInfos = schoolInfoDao.findBy(SchoolInfo.class, "schoolId", schoolId);
		
		if(CollectionUtils.isNotEmpty(schoolInfos)){
			return schoolInfos.get(0);
		}else{
			return null;
		}
	}

    public List<School> getSchoolList() {
        return schoolDao.getAll(School.class);
    }

    public School getInfoByXxID(String tmpId) {
        List list = schoolDao.findBy(School.class,"XxID", tmpId);
        if(list.size()>0)
        {
            return (School)list.get(0);
        }
        else
            return  null;
    }
}
