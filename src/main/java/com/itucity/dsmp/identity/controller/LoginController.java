package com.itucity.dsmp.identity.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Eric
 * @version 0.1
 * 
 * 6/13/2014
 */
@Controller
public class LoginController {

	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request,HttpServletResponse response) {
		return "login";
	}
	
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="test",method=RequestMethod.GET)
	public String test(HttpServletRequest request,HttpServletResponse response) {
		return "test";
	}
	
	/**
	 * 登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="test2",method=RequestMethod.GET)
	public String test2(HttpServletRequest request,HttpServletResponse response) {
		return "test2";
	}
	
//	/**
//	 * 登出
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="logout",method=RequestMethod.GET)
//	public String logout(HttpServletRequest request,HttpServletResponse response) {
//		request.getSession().invalidate();
//		return "login";
//	}
	
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request,HttpServletResponse response) {
		return "register";
	}
	
}
