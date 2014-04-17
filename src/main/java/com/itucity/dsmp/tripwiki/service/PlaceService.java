package com.itucity.dsmp.tripwiki.service;

import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;
import com.itucity.dsmp.tripwiki.dto.ImageVO;
import com.itucity.dsmp.tripwiki.dto.PlaceVO;


/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
public interface PlaceService {
	
	/**
	 * 获取地点相关信息
	 * @param id
	 * @return
	 */
	PlaceVO getPlaceById(Integer id);
	
	/**
	 * 
	 * @param Place
	 * @return
	 */
	Integer addPlace(PlaceVO Place);
	
	/**
	 * 
	 * @param Place
	 * @return
	 */
	Boolean updatePlace(PlaceVO Place);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean deletePlace(Integer id);

	/**
	 * 分页获取景点照片列表
	 * @param id 景点ID
	 * @param pageNO    获取第几页
	 * @param pageSize  每页大小
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	PagesInfo<ImageVO> getPlaceImages(PagesInfo pagesInfo, ImageCondition condition);
	
	/**
	 * 添加景点标签
	 * @param placeId
	 * @param tagId
	 * @return
	 */
	Boolean addPlaceTag(Integer placeId, Integer tagId);
	
	/**
	 * 添加景点图片
	 * @param placeId
	 * @param imageId
	 * @return
	 */
	Boolean addPlaceImage(Integer placeId, Integer imageId);
}
