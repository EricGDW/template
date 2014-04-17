package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.tripweek.dto.PlaceCondition;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
public interface PlaceDao extends IDao {
	
	/**
	 * 按地点名称查询
	 * @param name
	 * @return
	 */
	PlacePO findByName(String name);
	
	/**
	 * 按地点标签查询地点
	 * @param tag
	 * @return
	 */
	List<PlacePO> findByTag(String tag);
	

	/**
	 * 通用查询接口
	 * @param condition
	 * @return
	 */
	List<PlacePO> findByCondition(PlaceCondition condition);
	
	/**
	 * 景点添加标签
	 * @param placeId
	 * @param tagId
	 * @return
	 */
	Integer addPlaceTag(Integer placeId, Integer tagId);
	
	
	/**
	 * 景点添加图片
	 * @param placeId
	 * @param imageId
	 * @return
	 */
	Integer addPlaceImage(Integer placeId, Integer imageId);
}
