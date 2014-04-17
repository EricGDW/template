package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.LineService;
import com.itucity.dsmp.tripwiki.service.model.LineVO;
import com.itucity.dsmp.tripwiki.service.model.PlaceVO;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Controller
@RequestMapping("api/line")
public class TravelLineController {

	@Resource
	private LineService travelLineService;
	
	@Resource
	private PlaceService destinationService;
	
	
	public TravelLineController() {
	}
	
	@RequestMapping(value="/find/id",method=RequestMethod.GET)
	public void getTravelLineById(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		
		Map<String , String> errorInfo = new HashMap<String , String>();
		if( id == null){
			errorInfo.put("status", Constants.STATUS_FAILED);
			errorInfo.put("errcode", Constants.ERROR_INVALID_PARAMETER.toString());
			errorInfo.put("errmsg", "no 'id' parameter");
			
			response.getWriter().print(JsonUtil.obj2JsonArr(errorInfo));
			return ;
		}
		
		Integer dId = -1;
		
		try{
			dId = Integer.parseInt(id);
		}catch(NumberFormatException e){
			errorInfo.put("status", Constants.STATUS_FAILED);
			errorInfo.put("errcode", Constants.ERROR_INVALID_PARAMETER.toString());
			errorInfo.put("errmsg", "illegal 'id' parameter");
			
			response.getWriter().print(JsonUtil.obj2JsonArr(errorInfo));
			return ;
		}
		
		LineVO vo = travelLineService.getTravelLineById(dId);
		
		if (vo == null) {
			response.getWriter().print(
					JsonUtil.obj2JsonArr(new ArrayList<LineVO>()));
			return ;
		} 
		
		response.getWriter().print(JsonUtil.obj2JsonObj(vo));
	}
	
	@RequestMapping(value="/find/type",method=RequestMethod.GET)
	public void getTravelLineByType(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
	
		Map<String , String> errorInfo = new HashMap<String , String>();
		if( type == null){
			errorInfo.put("status", Constants.STATUS_FAILED);
			errorInfo.put("errcode", Constants.ERROR_INVALID_PARAMETER.toString());
			errorInfo.put("errmsg", "no 'type' parameter");
			
			response.getWriter().print(JsonUtil.obj2JsonArr(errorInfo));
			return ;
		}
		
		List<LineVO> vos = travelLineService.getTravelLineByType(type);
		
		response.getWriter().print(JsonUtil.obj2JsonArr(vos));
	}
	
	
	/**
	 * 2、攻略列表页/查询结果页
	 * @param tags
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/find/tags",method=RequestMethod.POST)
	@ResponseBody
	public Object getTravelLineByTags(
			@RequestParam(value="tags") List<String> tags) throws IOException {
		
		List<LineVO> vos = travelLineService.getTravelLineByTags(tags);
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		for(LineVO vo : vos){
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("id", vo.getLineId());
			map.put("name", vo.getName());
			map.put("bestTime", vo.getBestTime());
			map.put("description", vo.getDescription());
			map.put("tags", vo.getTagList());
			map.put("share", vo.getShare());
			map.put("agree", vo.getAgree());
			map.put("destinations", vo.getDestinationsName());
			
			result.add(map);
		}
		
		return result;
	}
	
	/**
	 * 3、攻略详细页（路线）
	 * @param tags
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}/details",method=RequestMethod.POST)
	@ResponseBody
	public Object getTravelLineDetails(@PathVariable Integer id) 
			throws IOException {
		
		LineVO vo = travelLineService.getTravelLineById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", vo.getLineId());
		map.put("name", vo.getName());
		map.put("bestTime", vo.getBestTime());
		map.put("description", vo.getDescription());
		map.put("tags", vo.getTagList());
		
		
		List<String> destinations = vo.getDestinationsName();
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for(String destination : destinations){
			PlaceVO dvo = destinationService.getDestinationByName(destination);
			
			Map<String, Object> dmap = new HashMap<String, Object>();
			
			dmap.put("id", dvo.getPlaceId());
			dmap.put("name", dvo.getName());
			dmap.put("content", dvo.getContent());
			dmap.put("share", dvo.getShareCount());
			dmap.put("agree", dvo.getFavouriteCount());
			
			result.add(map);
		}
		map.put("destinations", result);
			
		return map;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addTravelLine(@RequestBody LineVO travelLine) 
			throws IOException {
		
		Integer id = travelLineService.addTravelLine(travelLine);
		
		Map<String, String> map = new HashMap<String, String>(1);
		 map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
	    map.put("id", id.toString());
	    
	    return map;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object updateTravelLine(@RequestBody LineVO travelLine) 
			throws IOException {
		
		travelLineService.updateTravelLine(travelLine);
		
		Map<String, Object> map = new HashMap<String, Object>(1);
		 map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
	    
	    return map;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteTravelLine(@PathVariable Integer id) 
			throws IOException {
		
		travelLineService.deleteTravelLine(id);
		
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
	    
	    return map;
	}
	
	
	/**
	 * 2、对单一攻略点赞
	 * @param id
	 * 			攻略ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{lineId}/agree",method=RequestMethod.POST)
	@ResponseBody
	public Object agreeTravelLine(@PathVariable Integer lineId,
			@RequestParam(value = "uid", required = true) Integer uid) {
		
		travelLineService.favouriteTravelLine(uid, lineId);
		
		Map<String, Object> map = new HashMap<String, Object>(1);
	    map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
	    
	    return map;
	}
	
	/**
	 * 2、对单一攻略分享
	 * @param id
	 * 			攻略ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{lineId}/share",method=RequestMethod.POST)
	@ResponseBody
	public Object shareTravelLine(@PathVariable Integer lineId,
	@RequestParam(value = "uid", required = true) Integer uid) {
		
		travelLineService.shareTravelLine(uid, lineId);
		
		Map<String, String> map = new HashMap<String, String>(1);
		 map.put(Constants.STATUS_KEY, Constants.STATUS_SUCCESS);
	    
	    return map;
	}
	
	
	/**
	 * 用户推荐攻略列表
	 * @param id
	 * 			攻略ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/user/{uid}/recommendations",method=RequestMethod.POST)
	@ResponseBody
	public Object recommendationTravelLine(@PathVariable Integer uid) 
			throws IOException {
		List<Map<String, Object>> recommendations = new ArrayList<Map<String, Object>>();
		
		List<LineVO> vos = travelLineService.recommendationTravelLine(uid);
		
		for(LineVO vo : vos){
			Map<String, Object> map = new HashMap<String, Object>(1);
		   
			map.put("id", vo.getLineId().toString());
			map.put("name", vo.getName());
			map.put("description", vo.getDescription());
			
		    recommendations.add(map);
		}
		
	    return recommendations;
	}
	
	/**
	 * 全局TopN攻略
	 * @param id
	 * 			攻略ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/top/{num}",method=RequestMethod.POST)
	@ResponseBody
	public Object topTravelLine(@PathVariable Integer num,
			@RequestParam(value = "sort", required = true, defaultValue = "1") 
					Integer sort) {
		List<Map<String, Object>> tops = new ArrayList<Map<String, Object>>();
		
		List<LineVO> vos = travelLineService.topTravelLine(num, sort);
		
		for(LineVO vo : vos){
			Map<String, Object> map = new HashMap<String, Object>();
		   
			map.put("id", vo.getLineId());
			map.put("name", vo.getName());
			map.put("description", vo.getDescription());
			
			tops.add(map);
		}
		
	    return tops;
	}
	
}
