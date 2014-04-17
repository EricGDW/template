package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dto.LineCondition;
import com.itucity.dsmp.tripwiki.dto.LineVO;
import com.itucity.dsmp.tripwiki.dto.PlaceVO;
import com.itucity.dsmp.tripwiki.dto.TagVO;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
public interface LineService {

	/**
	 * 获取线路相关信息
	 * @param id
	 * @return
	 */
	LineVO getLineById(Integer id);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Integer addLine(LineVO Line);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Boolean updateLine(LineVO Line);
	
	/**
	 * 删除一条线路
	 * @param id
	 * @return
	 */
	Boolean deleteLine(Integer id);
	
	/**
	 * 根据路线ID获取指定路线的详细信息，景点排序根据景点地理位置排序
	 * @param lineId	线路ID
	 * @param sort	景点排序，1:按地理位置降序排序，2:按地理位置升序排序
	 * @return
	 */
	List<PlaceVO> getLinePlaces(Integer lineId, Integer sort);
	
	
	/**
	 * 根据条件搜索路线
	 * @param condition 条件
	 * @return
	 */
	PagesInfo<Object> getLineByCondition(LineCondition condition);
	
	/**
	 * 获取线路的所有标签
	 * @param lineId 线路ID
	 * @return
	 */
	List<TagVO> getLineTag(Integer lineId);
}
