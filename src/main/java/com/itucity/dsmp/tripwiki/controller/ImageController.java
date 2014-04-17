package com.itucity.dsmp.tripwiki.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.tripwiki.service.ImageService;
import com.itucity.dsmp.tripwiki.service.model.ImageVO;


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
	@RequestMapping(value="/find/id",method=RequestMethod.GET)
	public void getImageById(HttpServletRequest request, 
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
		
		ImageVO vo = imageService.getImageById(dId);
		
		if (vo == null) {
			response.getWriter().print(
					JsonUtil.obj2JsonArr(new ArrayList<ImageVO>()));
			return ;
		} 
		
		response.getWriter().print(JsonUtil.obj2JsonObj(vo));
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
