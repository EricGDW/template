package com.itucity.dsmp.identity.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.common.util.JsonUtil;
import com.itucity.dsmp.identity.service.AccessClientService;
import com.itucity.dsmp.identity.service.model.AccessClientVO;
import com.itucity.dsmp.identity.util.HttpClientUtil;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/18/2014
 */
@Controller
@RequestMapping("wx_oauth")
public class WXOauthController {

	@Resource
	private AccessClientService accessClientService;
	
	public WXOauthController() {
	}
	
	/**
	 * 向微信发出OAUTH授权请求
	 * redirect_uri参数应该设定为"/wx_oauth/get_code"
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/request_code",method=RequestMethod.POST)
	public void requestAuthenticationCode(HttpServletRequest request,
			HttpServletResponse response){
		String weixinOauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
		
		String  appkey = (String) request.getAttribute(Constants.APP_KEY);
		
		AccessClientVO vo = accessClientService.getByAppKey(appkey);
		
		String redirect_uri = vo.getRedirectUri();
		String scope = vo.getScope();
		
		//生成state
		String state = vo.getState();
		
		vo.setState(state);
		//存储到数据库
		accessClientService.editAccessClient(vo);
		
		//发送请求
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("appid", appkey);
		params.put("redirect_uri", redirect_uri);
		params.put("scope", scope);
		params.put("state", state);
		params.put("response_type", "code#wechat_redirect");
		
		HttpClientUtil.invokeGet(weixinOauthUrl, params, "UTF-8", 5000, 0);
		
	}
	
	/**
	 * 获取授权码，存储到数据库
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/get_code",method=RequestMethod.GET)
	public void getAuthenticationCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String code = (String) request.getAttribute("code");
		String state = (String) request.getAttribute("state");
		
		AccessClientVO vo = accessClientService.getByState(state);
		
		//验证state
		if(vo == null || code == null){//验证失败,返回错误码
			
		}
		//将code存储到数据库
		vo.setAuthorityCode(code);
		accessClientService.editAccessClient(vo);
		
		Map<String , String> map = new HashMap<String , String>();
		
		map.put("code", code);
		map.put("state", state);
		
		response.getWriter().print(JsonUtil.obj2JsonArr(code));
	}
	
	/**
	 * 获取网页授权access_token
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/access_token",method=RequestMethod.GET)
	public void getAccessToken(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String weixinOauthUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
		
		String  appkey = (String) request.getAttribute(Constants.APP_KEY);
		
		AccessClientVO vo = accessClientService.getByAppKey(appkey);
		
		String appSecret = vo.getAppSecret();
		String code = vo.getAuthorityCode();
		
		//发送请求
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("appid", appkey);
		params.put("secret", appSecret);
		params.put("code", code);
		params.put("grant_type", "authorization_code");
		
		String res = HttpClientUtil.invokeGet(weixinOauthUrl, params, "UTF-8", 5000, 0);
		
		response.getWriter().print(res);
	}
	
	/**
	 * 获取网页授权refresh_token
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/refresh_token",method=RequestMethod.GET)
	public void refreshToken(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String weixinOauthUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
		
		String  appkey = (String) request.getAttribute(Constants.APP_KEY);
		
		AccessClientVO vo = accessClientService.getByAppKey(appkey);
		
		
		//发送请求
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("appid", appkey);
		params.put("refresh_token", "");
		params.put("grant_type", "refresh_token");
		
		String res = HttpClientUtil.invokeGet(weixinOauthUrl, params, "UTF-8", 5000, 0);
		
		response.getWriter().print(res);
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String url = "http://localhost:8080/dsmp/group/find/id?id=1";
		
		//发送请求
		Map<String, String> params = new HashMap<String, String>();
		
		//发送请求
		String res = HttpClientUtil.invokeGet(url, params, "UTF-8", 5000, 0);
		
		response.getWriter().print(res);
	}

}
