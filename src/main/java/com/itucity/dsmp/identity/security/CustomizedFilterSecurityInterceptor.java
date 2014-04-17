package com.itucity.dsmp.identity.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;

/**
 * 自定义的filter，必须包含authenticationManager,accessDecisionManager,
 * securityMetadataSource三个属性 
 * 
 * @author Eric
 * @since 0.0.1 <p>2014-3-15 下午3:25:09</p>
 */
public class CustomizedFilterSecurityInterceptor 
				extends AbstractSecurityInterceptor implements Filter {
	private static Logger logger = LoggerFactory
			.getLogger(CustomizedFilterSecurityInterceptor.class);

	@Autowired
	private CustomizedInvocationSecurityMetadataSource 
				customizedInvocationSecurityMetadataSource;
	
	@Autowired
	private CustomizedAccessDecisionManager customizedAccessDecisionManager;
	
	@Autowired
	@Qualifier("customizedAuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	@PostConstruct
	public void init(){
		super.setAuthenticationManager(authenticationManager);
		super.setAccessDecisionManager(customizedAccessDecisionManager);
	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		FilterInvocation fi = new FilterInvocation(request, response, chain);

		logger.debug("requestURL:" + fi.getRequestUrl());
		
		//在执行doFilter之前，进行权限的检查		
        InterceptorStatusToken statusToken = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(statusToken, null);
        }
	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	
	public void destroy() {
	}

	
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.customizedInvocationSecurityMetadataSource;
	}

	public CustomizedInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return customizedInvocationSecurityMetadataSource;
	}

	public void setSecurityMetadataSource(
			CustomizedInvocationSecurityMetadataSource securityMetadataSource) {
		this.customizedInvocationSecurityMetadataSource = securityMetadataSource;
	}
}
