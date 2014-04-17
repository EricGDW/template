package com.itucity.dsmp.identity.security;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.itucity.dsmp.common.Constants;
import com.itucity.dsmp.identity.service.AccessClientService;
import com.itucity.dsmp.identity.service.model.AccessClientVO;
import com.itucity.dsmp.identity.util.CryptUtil;

public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource 
	private AccessClientService accessClientService;
	
	private List<String> allowUrls;
	
	public void setAllowUrls(List<String> allowUrls){
		this.allowUrls = allowUrls;
	}
	
	public AuthenticationHandlerInterceptor() {
	}
	
	public boolean preHandle(  
            HttpServletRequest request, HttpServletResponse response,   
            Object handler)   
            throws Exception{
		
		//资源白名单
		String  url = request.getServletPath();
		for(int i = 0; i < allowUrls.size(); i++ ){
			if(url.startsWith(allowUrls.get(i))){
				return true;
			}
		}
		
		String appKey = (String) request.getParameter(Constants.APP_KEY);
		String sign = (String) request.getParameter(Constants.SIGN_KEY);
		String timestamp = (String) request.getParameter(Constants.TIMESTAMP_KEY);
		
		
		if(appKey == null || appKey == "" || sign == null || sign ==""){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return false;
		}
		
		AccessClientVO appInfo = accessClientService.getByAppKey(appKey);
		
		//验证key是否有效
		if(!appInfo.getIsValid()){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return false;
		}
		
		//获取keySecret
		String appSecret = appInfo.getAppSecret();
		
		//签名的方式
		//可以对请求的参数进行签名,后面可以更具需要进行修改
		String beforeSign = appSecret + appKey + timestamp + appSecret;
		
		//重新生成签名
		byte[] signs = CryptUtil.decryptBASE64(
				CryptUtil.HmacSHA1Encrypt(beforeSign, appSecret).toString());		
		
		//确认用户签名
		if(!signs.equals(sign.getBytes())){
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return false;
		}
		
		return true;
	}

}
