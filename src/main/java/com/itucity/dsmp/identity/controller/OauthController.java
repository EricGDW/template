package com.itucity.dsmp.identity.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.identity.service.AccessClientService;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/20/2014
 */
@Controller
@RequestMapping("oauth")
public class OauthController {

	@Resource
	private AccessClientService accessClientService;
	
	public OauthController() {
	}
	
	/**
	 * 注册应用
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/regist_app",method=RequestMethod.POST)
	public void registApp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String code = (String) request.getAttribute("app_name");
		String redirect_url = (String) request.getAttribute("url");
		String timestramp = (String) request.getAttribute("timestramp");
		
		
		response.getWriter().print(JsonUtil.obj2JsonArr(code));
	}

}
