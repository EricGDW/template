package com.itucity.dsmp.identity.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomizedRequestMatcher implements RequestMatcher{

	private String filterProcessesUrl;
	
	public void init(){
		this.setFilterProcessesUrl(filterProcessesUrl);
	}
	
	
	@Override
	public boolean matches(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		if(servletPath.equals(filterProcessesUrl)){
			return true;
		}
		return false;
	}

	public String getFilterProcessesUrl() {
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
}
