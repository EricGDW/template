package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.dto.LineCondition;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
public interface LineDao {
	
	/**
	 * 按线路ID查询线路
	 * @param lineId
	 * @return
	 */
	LinePO findById(Integer lineId);
	
	/**
	 * 增加一条线路
	 * @param line
	 * @return
	 */
	Integer save(LinePO line);
	
	/**
	 * 更新一条线路
	 * @param line
	 * @return
	 */
	Boolean update(LinePO line);
	
	/**
	 * 删除一条线路
	 * @param line
	 * @return
	 */
	Boolean delete(LinePO line);
	
	/**
	 * 按ID删除一条线路
	 * @param lineId
	 * @return
	 */
	Boolean deleteById(Integer lineId);
	
	/**
	 * 通用线路查询接口
	 * @param condition
	 * @return
	 */
	PagesInfo<LinePO> findByCondition(LineCondition condition,PagesInfo<LinePO> pagesInfo);
	
	/**
	 * 线路添加标签
	 * @param lineId
	 * @param tagId
	 * @return
	 */
	Boolean addLineTag(Integer lineId, Integer tagId);
	
	/**
	 * 线路添加图片
	 * @param lineId
	 * @param imageId
	 * @return
	 */
	Boolean addLineImage(Integer lineId, Integer imageId);
	
	/**
	 * 线路添加景点
	 * @param lineId 线路ID
	 * @param placeId 景点ID
	 * @param sort  景点在线路中的顺序
	 * @return
	 */
	Boolean addLinePlace(Integer lineId, Integer placeId, Integer sort);
	
	
	/**
	 * 获取线路景点列表
	 * @param lineId 线路ID
	 * @return
	 */
	List<PlacePO> findLinePlace(Integer lineId); 
	
	/**
	 * 获取线路标签列表
	 * @param lineId 线路ID
	 * @return
	 */
	List<TagPO> findLineTag(Integer lineId); 
	
}
