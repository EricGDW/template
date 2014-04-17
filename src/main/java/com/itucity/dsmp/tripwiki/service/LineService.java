package com.itucity.dsmp.tripwiki.service;

import java.util.List;

import com.itucity.dsmp.tripwiki.service.model.LineVO;


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
	LineVO getTravelLineById(Integer id);
	
	/**
	 * 按类型获取线路相关信息
	 * @param type
	 * @return
	 */
	List<LineVO> getTravelLineByType(String type);
	
	
	/**
	 * 按路线标签获取线路相关信息
	 * @param tags
	 * @return
	 */
	List<LineVO> getTravelLineByTags(List<String> tags);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Integer addTravelLine(LineVO travelLine);
	
	/**
	 * 
	 * @param destination
	 * @return
	 */
	Boolean updateTravelLine(LineVO travelLine);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Boolean deleteTravelLine(Integer id);

	/**
	 * 点赞攻略
	 * @param uid
	 * @param lineId
	 * @return
	 */
	Integer favouriteTravelLine(Integer uid, Integer lineId);

	/**
	 * 分享攻略
	 * @param uid
	 * @param lineId
	 * @return
	 */
	Integer shareTravelLine(Integer uid, Integer lineId);

	/**
	 * 用户推荐攻略
	 * @param uid
	 * @return
	 */
	List<LineVO> recommendationTravelLine(Integer uid);

	/**
	 * 全局TopN攻略,分享数/访问数最高的路线列表
	 * @param no
	 * 			top的数量
	 * @param sort	排序条件,1:默认，2:分享数，3:访问数
	 * 
	 * @return
	 */
	List<LineVO> topTravelLine(Integer num, Integer sort);
	
	/**
	 * 收藏攻略
	 * @param uid
	 * @param lineId
	 * @return
	 */
	Integer collectTravelLine(Integer uid, Integer lineId);
	
}
