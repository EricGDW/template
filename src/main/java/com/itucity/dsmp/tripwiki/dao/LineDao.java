package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripweek.dto.LineCondition;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
public interface LineDao extends IDao {

	/**
	 * 按线路名称查询线路信息
	 * @param name
	 * @return
	 */
	LinePO findByName(String name);
	
	/**
	 * 按线路类型查询线路信息
	 * @param type
	 * @return
	 */
	List<LinePO> findByType(String type);
	
	/**
	 * 按线路标签查询线路信息
	 * @param tag
	 * @return
	 */
	List<LinePO> findByTag(String tag);
	
	/**
	 * 按sort类型获取topNum攻略
	 * @param topNum
	 * @param sort
	 * @return
	 */
	List<LinePO> findTop(Integer topNum, Integer sort);
	
	/**
	 * 通用线路查询接口
	 * @param condition
	 * @return
	 */
	PagesInfo<LinePO> findByCondition(LineCondition condition,PagesInfo pagesInfo);
	
	/**
	 * 线路添加标签
	 * @param lineId
	 * @param tagId
	 * @return
	 */
	Integer addLineTag(Integer lineId, Integer tagId);
}
