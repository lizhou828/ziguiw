package com.zigui.service;

import com.zigui.domain.CcczgInfoSouce;
import com.zigui.domain.CourseXml;
import com.zigui.utils.BusinessException;

import java.util.List;

public interface SourceService extends IBaseService<CcczgInfoSouce, String> {
	public List<CcczgInfoSouce> getListBySubjectIdAndLeve(String subjectId,
			Integer leve, Integer len) throws BusinessException;

	/**
	 * 根据资源类型获得数据
	 */
	public List<CcczgInfoSouce> getListByTypeName(String typeName, Integer len)
			throws BusinessException;

	/**
	 * 查询在本月时间内推荐数最高的资源
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CcczgInfoSouce> getListByCreateDateOrderRecommend(Integer len)
			throws BusinessException;

	/**
	 * 查询在本月时间内下载数最高的资源
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<CcczgInfoSouce> getListByCreateDateOrderResourceDownnum(
			Integer len) throws BusinessException;

	/**
	 * 根据1:小学2:初中3:高中 返回所有 并根据创建时间次数进行排序
	 * 
	 * @param leve
	 * @return
	 * @throws BusinessException
	 */
	public List<CcczgInfoSouce> getListByLeve(Integer leve, Integer len)
			throws BusinessException;

	/**
	 * 根据1:小学2:初中3:高中 返回所有 并根据下载次数进行排序
	 * 
	 * @param leve
	 * @return
	 * @throws BusinessException
	 */
	public List<CcczgInfoSouce> getListOrderByResourceDownnum(Integer leve,
			Integer len) throws BusinessException;

	
	public List<CcczgInfoSouce> getListByFlag()
	throws BusinessException ;

    public List<CourseXml> parseXml(String xmlPath) throws Exception;

}
