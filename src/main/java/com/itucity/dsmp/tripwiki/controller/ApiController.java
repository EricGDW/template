package com.itucity.dsmp.tripwiki.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.tripwiki.dto.DataReturnDTO;
import com.itucity.dsmp.tripwiki.dto.LineVO;
import com.itucity.dsmp.tripwiki.service.LineService;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.util.ReturnPackaging;

/**
 * 接口支持类，提供所有内容数据接口，包括个人主页（my_home），应用主页（home）,</br>
 * 景点（place），路线（line），图片接口（image），评论（comment），标签接口（tag）
 * 
 * @author Kevin
 * @mail zhyacn@gmail.com
 * @version v1.0
 * @create 2014-04-01
 * 
 */

@Controller
@RequestMapping("api/v1")
public class ApiController {

	@Resource
	private LineService lineService;

	@Resource
	private PlaceService placeService;
	
	@Resource
	private ReturnPackaging erroMessage;

	/************************************** 应用主页（home）数据接口 ***************************************/

	/**
	 * 根据用户ID获取用户感兴趣的旅游攻略列表
	 * 
	 * @param uids
	 *            用户ID，多个用户ID用","分隔，如id1,id2,id3
	 * @param dateTime
	 *            详细日期，格式为yyyy-MM-dd HH:mm:ss
	 * @param weather
	 *            天气情况
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为10
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @return 攻略列表
	 */
	@RequestMapping(value = "home/get_user_interest_lines", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserInterestLines(
			@RequestParam(value = "uids", required = true) List<Integer> uids,
			@RequestParam(value = "date_time", required = true) String dateTime,
			@RequestParam(value = "weather", required = true) String weather,
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page) {

		return null;
	}

	/**
	 * 根据多个用户ID获取用系统推荐给用户的旅游攻略列表
	 * 
	 * @param uids
	 *            用户ID，多个用户ID用","分隔，如id1,id2,id3
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为10
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @return 攻略列表
	 */
	@RequestMapping(value = "home/get_user_recommand_lines", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserRecommandLines(
			@RequestParam(value = "uids", required = true) List<Integer> uids,
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page) {

		return null;
	}

	/**
	 * 根据不同排序条件获取TopN的攻略列表
	 * 
	 * @param sort
	 *            排序条件,1:默认，2:分享数，3:点击数
	 * @return 攻略列表
	 */
	@RequestMapping(value = "home/get_top_lines", method = RequestMethod.GET)
	@ResponseBody
	public Object getTopLines(
			@RequestParam(value = "limit", required = true, defaultValue = "5") Integer limit,
			@RequestParam(value = "sort", required = true) Integer sort) {
		List<LineVO> lines = new ArrayList<LineVO>();
		
//		lines = lineService.topLine(limit, sort);
		
		DataReturnDTO dto = new DataReturnDTO();
		
		dto.setData(lines);
		
		return dto;
	}

	/**
	 * 获取全局TopN查询条件列表（查询次数最多的查询条件列表）
	 * 
	 * @param limit
	 *            限制查询条数，默认为5条
	 * @return
	 */
	@RequestMapping(value = "home/get_top_query_conditions", method = RequestMethod.GET)
	@ResponseBody
	public Object getTopQueryConditions(
			@RequestParam(value = "limit", required = true, defaultValue = "5") Integer limit) {
	
//		List<SearchInfoVO> result = searchService.topSearchInfo(limit);
		
		DataReturnDTO dto = new DataReturnDTO();
		
//		dto.setData(result);
		
		return dto;
	}

	/************************************** 路线（line）数据接口 ***************************************/


	/**
	 * 生成路线地图
	 * 
	 * @param lineId
	 *            路线ID
	 * @return	该线路所有景点的坐标排序
	 */
	@RequestMapping(value = "line/get_line_map", method = RequestMethod.GET)
	@ResponseBody
	public Object getLineMap(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "sort",required = true, defaultValue="1") Integer sort) {
//		LineVO line = lineService.getTravelLineById(lineId);
		
//		List<Integer> placesId = line.getDestinationList();
		
		List<Map<String , Object>> places = new ArrayList<Map<String , Object>>();
		
//		for(Integer id : placesId){
//			PlaceVO vo = destinationService.getDestinationById(id);
//			
//			Map<String , Object> map = new HashMap<String , Object>(2);
//			
//			map.put("latitude", vo.getLatitude());
//			map.put("longitude", vo.getLongitude());
//			
//			places.add(map);
//		}
//		
		//根据经纬度排序
		Collections.sort(places, new Comparator<Map<String , Object>>(){
			@Override
			public int compare(Map<String , Object> o1, Map<String , Object> o2) {
				Map<String , Object> p1=(Map<String , Object>)o1;
				Map<String , Object> p2=(Map<String , Object>)o2;
				int flag = ((Float)p1.get("longitude")).compareTo((Float)p2.get("longitude"));
				if(flag == 0){
					return ((Float)p1.get("latitude")).compareTo((Float)p2.get("latitude"));
				}else{
					return flag;
				}
			}
		});
 		
		if(sort == 2){
			Collections.reverse(places);
		}
		
		DataReturnDTO dto = new DataReturnDTO();
		
		dto.setData(places);
		
		return dto;
	}

	/**
	 * 用户对某个路线攻略点赞操作
	 * 
	 * @param line_id
	 *            路线ID
	 * @param uid
	 *            用户ID
	 * @return 路线的点赞数
	 */
	@RequestMapping(value = "line/set_line_recommend_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setLineRecommendCount(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "uid") Integer uid) {
		
//		Integer agree = lineService.favouriteTravelLine(uid, lineId);
		
		DataReturnDTO dto = new DataReturnDTO();
		
//		dto.setData(agree);
		
		return dto;
	}

	/**
	 * 用户对某个路线攻略分享操作
	 * 
	 * @param line_id
	 *            路线ID
	 * @param uid
	 *            用户ID
	 * @return 路线的分享数
	 */
	@RequestMapping(value = "line/set_line_share_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setLineShareCount(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "uid") Integer uid) {
//		Integer share = lineService.shareTravelLine(uid, lineId);
		
		DataReturnDTO dto = new DataReturnDTO();
		
//		dto.setData(share);
		
		return dto;
	}

	/**
	 * 用户对查询结果的分享操作
	 * 
	 * @param line_id
	 *            路线ID
	 * @param uid
	 *            用户ID
	 * @return
	
	@RequestMapping(value = "line/set_query_condition_share_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setQueryConditionShareCount(
			@RequestParam(value = "query_condition", required = true) String queryCondition,
			@RequestParam(value = "uid") Integer uid) {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("t", "1sd");
		return map1;
	}
 */
	
	/**
	 * 用户收藏线路操作
	 * 
	 * @param lineId
	 *            路线ID
	 * @param uid
	 *            用户ID
	 * @return 收藏的统计数据
	 */
	@RequestMapping(value = "line/set_line_favorite_count", method = RequestMethod.GET)
	@ResponseBody
	public Object setLineFavoriteCount(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "uid") Integer uid) {
//		Integer share = lineService.collectTravelLine(uid, lineId);
		
		DataReturnDTO dto = new DataReturnDTO();
		
//		dto.setData(share);
		
		return dto;
	}

	/**
	 * 用户对某个线路走起操作
	 * 
	 * @param line_id
	 *            路线ID
	 * @param uid
	 *            用户ID
	 * @return 走起的统计数据
	
	@RequestMapping(value = "line/set_line_go_up", method = RequestMethod.GET)
	@ResponseBody
	public Object setLineGoUp(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "uid") Integer uid) {
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("t", "1sd");
		return map1;
	} 
	*/

	/************************************ 地点（place）数据接口 ****************************************/

	

	
	
	/************************************* 图片（image）数据接口 ***************************************/

	/**
	 * 根据多个景点ID批量获取指定景点的相册详细信息
	 * 
	 * @param placeIds
	 * @return
	 */
	@RequestMapping(value = "image/get_albums_with_place", method = RequestMethod.GET)
	@ResponseBody
	public Object getAlbumsWithPlace(
			@RequestParam(value = "place_ids", required = true, defaultValue = "0") List<Integer> placeIds) {
		Map<Integer, Object> result = new HashMap<Integer, Object>();
		
//		for(Integer id : placeIds){
//			List<ImageVO> images = destinationService.destinationImages(id);
//			result.put(id, images);
//		}
		
		DataReturnDTO dto = new DataReturnDTO();
		
		dto.setData(result);
		
		return dto;
	}

	/**
	 * 根据多个路线ID批量获取指定路线的相册详细信息
	 * 
	 * @param lineIds
	 * @return
	 
	@RequestMapping(value = "image/get_albums_with_line", method = RequestMethod.GET)
	@ResponseBody
	public Object getAlbumWithLine(
			@RequestParam(value = "line_ids", required = true, defaultValue = "0") List<Integer> lineIds) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);

		return map;
	}
	*/

	/************************************ 评论（comment）数据接口 ******************************************/

	/**
	 * 根据景点路线攻略ID获取路线评论
	 * 
	 * @param lineId
	 *            攻略线路ID
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为20
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @param sort
	 *            排序，1:默认，2:按查看数降序排序，3:按查看数升序排序
	 * @return
	 */
	@RequestMapping(value = "comment/get_comments_with_line", method = RequestMethod.GET)
	@ResponseBody
	public Object getCommentWithLine(
			@RequestParam(value = "line_id", required = true) Integer lineId,
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "sort", required = true, defaultValue = "1") Integer sort,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page) {
		// to do

		return null;
	}

	/**
	 * 根据景点ID获取景点评论
	 * 
	 * @param lineId
	 *            攻略线路ID
	 * @param limit
	 *            每页返回的结果条目数上限，最小值1，最大值40，如不传入默认为20
	 * @param page
	 *            页码，如不传入默认为1，即第一页
	 * @param sort
	 *            排序，1:默认，2:按查看数降序排序，3:按查看数升序排序
	 * @return
	 */
	@RequestMapping(value = "comment/get_comments_with_place", method = RequestMethod.GET)
	@ResponseBody
	public Object getCommentWithPlace(
			@RequestParam(value = "place_id", required = true) Integer placeId,
			@RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit,
			@RequestParam(value = "sort", required = true, defaultValue = "1") Integer sort,
			@RequestParam(value = "page", required = true, defaultValue = "1") Integer page) {
		// to do

		return null;
	}

	/**
	 * 用户创建景点评论
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "comment/create_place_comment", method = RequestMethod.GET)
	@ResponseBody
	public Object createPlaceComment(
			@RequestParam(value = "comment", required = true) Object comment) {
		// to do

		return null;
	}

	/**
	 * 用户创建攻略路线评论
	 * 
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "comment/create_line_comment", method = RequestMethod.GET)
	@ResponseBody
	public Object createLineComment(
			@RequestParam(value = "comment", required = true) Object comment) {
		// to do

		return null;
	}

	/************************************** 标签（tag）数据接口 ********************************************/
	
}
