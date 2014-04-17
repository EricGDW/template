package com.itucity.dsmp.tripwiki.dao;

import java.util.List;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;
import com.itucity.dsmp.tripwiki.dto.PlaceCondition;


/**
 * 行程的所有地点
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
public interface PlaceDao {
	
	/**
	 * 添加一个景点
	 * @param po
	 * @return
	 */
	Boolean save(PlacePO placePO);
	
	/**
	 * 按ID删除景点
	 * @param placeId
	 * @return
	 */
	Boolean delete(PlacePO placePO);
	
	/**
	 * 按ID删除景点
	 * @param placeId
	 * @return
	 */
	Boolean deleteById(Integer placeId);
	

	/**
	 * 按地点ID
	 * @param placeId
	 * @return
	 */
	PlacePO findById(Integer placeId);
	
	
	/**
	 * 更新景点
	 * @param placePO
	 * @return
	 */
	Boolean update(PlacePO placePO);
	

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
	Boolean addPlaceTag(Integer placeId, Integer tagId);
	
	
	/**
	 * 景点添加图片
	 * @param placeId
	 * @param imageId
	 * @return
	 */
	Boolean addPlaceImage(Integer placeId, Integer imageId);
	

	/**
	 * 分页获取景点图片
	 * @param placeId
	 * @param page
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	PagesInfo<ImagePO> findPlaceImages(PagesInfo pagesInfo,ImageCondition condition);
}
