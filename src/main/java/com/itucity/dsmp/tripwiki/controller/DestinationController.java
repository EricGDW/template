package com.itucity.dsmp.tripwiki.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.model.PlaceVO;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;
import com.itucity.dsmp.tripwiki.util.ReturnPackaging;

/**
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Controller
@RequestMapping("api/place")
public class DestinationController {
	
	@Resource
	private PlaceService destinationService;
	
	@Resource
	private ReturnPackaging errorMessage;
	
	/**
	 * 根据景点ID查询景点详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object getDestinationById(@PathVariable Integer id) {
	
		PlaceVO place = destinationService.getDestinationById(id);
		
		if (place == null) {
			Map<String , Object> errorInfo = new HashMap<String , Object>(3);
			
			errorInfo.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			errorInfo.put("errcode", Constants.ERROR_INVALID_PARAMETER);
			errorInfo.put("errmsg", "cannot find destination[id : "+ id + "]");
			
			return errorInfo;
		} 
		
		return place;
	}
	
	@RequestMapping(value="/find/tags",method=RequestMethod.GET)
	@ResponseBody
	public Object getDestinationByTags(
			@RequestParam(value="tags") List<String> tags) throws IOException {
		
		List<String> t = new ArrayList<String>();
		
		for(String tag : tags){
			String s = new String(tag.getBytes("ISO8859-1"), "UTF-8");
			t.add(s);
		}
		
		List<PlaceVO> vos = destinationService.getDestinationByTags(t);
		
		return vos;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object addDestination(@RequestBody PlaceVO destination) 
			throws IOException {
		
		Integer id = destinationService.addDestination(destination);
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);    
		result.put("id", id);
	    
	    return result;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object updateDestination(@RequestBody PlaceVO destination) 
			throws IOException {
		
		destinationService.updateDestination(destination);
		
		Map<String, Object> result = new HashMap<String, Object>(1);
		result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
	    
	    return result;
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteDestination(@PathVariable Integer id) 
			throws IOException {
		
		destinationService.deleteDestination(id);
		
		Map<String, Object> result = new HashMap<String, Object>(1);
		result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
	    
	    return result;
	}
	
	/**
	 * 2、对单一景点点赞
	 * @param id
	 * 			景点ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{placeId}/agree",method=RequestMethod.POST)
	@ResponseBody
	public Object agreeDestination(@PathVariable Integer placeId,
			@RequestParam(value = "uid", required = true) Integer uid) {
		
		destinationService.favouriteDestination(uid, placeId);
		
		Map<String, Object> result = new HashMap<String, Object>(1);
		result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
	    
	    return result;
	}
	
	/**
	 * 2、对单一景点分享
	 * @param id
	 * 			景点ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{placeId}/share",method=RequestMethod.POST)
	@ResponseBody
	public Object shareDestination(@PathVariable Integer placeId,
			@RequestParam(value = "uid", required = true) Integer uid) {
		
		destinationService.shareDestination(uid, placeId);
		
		Map<String, Object> result = new HashMap<String, Object>(1);
		result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
	    
	    return result;
	}
	
	/**
	 * 景点照片列表
	 * @param id
	 * 			景点ID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}/images",method=RequestMethod.POST)
	@ResponseBody
	public Object destinationImages(@PathVariable Integer id) 
			throws IOException {
		List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
		
		List<ImageVO> vos = destinationService.destinationImages(id);
		
		for(ImageVO image : vos){
			Map<String, Object> map = new HashMap<String, Object>();
		    
			map.put("id", image.getImageId());
			map.put("content", image.getRaw());
			map.put("description", image.getDescription());
			
			images.add(map);
		}
		
	    return images;
	}
}
