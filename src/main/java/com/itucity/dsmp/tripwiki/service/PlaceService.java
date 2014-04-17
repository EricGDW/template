package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.service.model.PlaceVO;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;


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
	PlaceVO getDestinationById(Integer id);
	
	/**
	 * 获取地点相关信息
	 * @param name
	 * @return
	 */
	PlaceVO getDestinationByName(String name);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	List<PlaceVO> getDestinationByTag(String tag);
	
	/**
	 * 按地点标签查询地点,同时满足所有的标签条件
	 * @param tag
	 * @return
	 */
	List<PlaceVO> getDestinationByTags(List<String> tags);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Integer addDestination(PlaceVO destination);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Boolean updateDestination(PlaceVO destination);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean deleteDestination(Integer id);

	

	/**
	 * 景点点赞
	 * @param uid
	 * @param placeId
	 * @return
	 */
	Integer favouriteDestination(Integer uid, Integer placeId);

	
	/**
	 * 景点分享
	 * @param uid
	 * @param placeId
	 * @return
	 */
	Integer shareDestination(Integer uid, Integer placeId);

	/**
	 * 景点照片列表
	 * @param id
	 * @return
	 */
	List<ImageVO> destinationImages(Integer id);
}
