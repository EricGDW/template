package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.tripwiki.dto.ImageVO;
import com.itucity.dsmp.tripwiki.service.ImageService;


/**
 * 图片接口
 * @author Eric
 * @version 0.1
 * 
 * 3/26/2014
 */
@Controller
@RequestMapping("api/image")
public class ImageController {

	@Resource
	private ImageService imageService;
	
	/**
	 * 根据图片ID查询图片详细
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Object getImageById(@PathVariable Integer id) {
		ImageVO image = imageService.getImageById(id);
		
		if (image == null) {
			Map<String , Object> result = new HashMap<String , Object>(3);
			
			result.put(Constants.STATUS_KEY, Constants.STATUS_FAILED);
			result.put("errcode", Constants.ERROR_NOT_FOUND);
			result.put("errmsg", "cannot find group [id : " + id + "]");
			
			return result;
		} 
		return image;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addImage(@RequestBody ImageVO image) 
			throws IOException {
		
		Integer id = imageService.addImage(image);
		
		Map<String, String> map = new HashMap<String, String>(1);
	    map.put("status", "1");
	    map.put("id", id.toString());
	    
	    return map;
	}

}
