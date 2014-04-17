package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.MetaDataService;
import com.itucity.dsmp.tripwiki.service.PlaceService;
import com.itucity.dsmp.tripwiki.service.TagService;

@Controller
public class TestController {
	
	@Resource
	private PlaceService placeService;
	
	@Resource
	private ImageService imageService;
	
	@Resource
	private TagService tagService;
	
	@Resource 
	private MetaDataService metaDataService;
	
	
	@RequestMapping(value="/importdata",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> importData() 
			throws IOException {
		
		Map<String, String> map = new HashMap<String, String>(1);
	    map.put("status", "1");
	    
	    return map;
	}
}
