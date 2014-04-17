package com.itucity.dsmp.identity.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RegexRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.service.GroupService;
import com.itucity.dsmp.identity.service.ResourceService;
import com.itucity.dsmp.identity.service.model.GroupVO;
import com.itucity.dsmp.identity.service.model.ResourceVO;


/**
 * 安全资源（URL）和角色映射关系处理器
 * 
 * @author Eric
 * @since 0.0.1 <p>2014-3-15 下午1:25:09</p>
 */

public class CustomizedInvocationSecurityMetadataSource 
				implements FilterInvocationSecurityMetadataSource {
	private static Logger logger = LoggerFactory
			.getLogger(CustomizedInvocationSecurityMetadataSource.class);
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap
						= new HashMap<String, Collection<ConfigAttribute>>();
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private ResourceService resourceService;
	
	private boolean expire = false; // 过期标识
	
	private RequestMatcher requestMatcher; // 匹配规则  
	  
    private String matcher = "ant"; // 规则标识 
	
	/**
	 * 初始化资源配置
	 * 
	 * spring 调用该方法的方式有2种
	 * 方式1，方法上加注解：
	 * @PostConstruct
	 * 
	 * 方式2，配置文件中 init-method 属性指定：
	 * <bean id="securityMetadataSource" init-method="initResource"
	 *  class="com.*.SecurityMetadataSource"/>
	 */
	@PostConstruct
	public void initResource(){
		resourceMap.clear();
		List<ResourceVO> resources = this.resourceService.loadForAll();  
        for (ResourceVO resource : resources) {  
            List<GroupPO> owner = resource.getOwner(); 
            
            List<GroupVO> roles = new ArrayList<GroupVO>();		
    		for(GroupPO po : owner){
    			GroupVO vo = new GroupVO();
    			BeanUtils.copyProperties(po, vo);
    			roles.add(vo);
    		}
    		
            resourceMap.put(resource.getContent(), list2Collection(roles));  
        }
	}
		
	/** 
     * 将List<GroupVO>集合转换为框架需要的Collection<ConfigAttribute>集合 
     *  
     * @param roles 
     * @return Collection<ConfigAttribute> 
     */  
    private Collection<ConfigAttribute> list2Collection(List<GroupVO> roles) {  
        List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();  
        for (GroupVO role : roles)  
            list.add(new SecurityConfig(role.getName()));  
        return list;  
    }  
	
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		
		HttpServletRequest request = ((FilterInvocation) object).getRequest();  
  
        // 检测是否刷新了资源  
        if (isExpire()) {  
            // 清空原本资源  
        	resourceMap.clear();  
            expire = false;  
        }  
  
        // 如果资源Map为空的时候则重新加载一次  
        if (null == resourceMap || resourceMap.isEmpty())  
        	reloadResource();  
  
        // 检测请求与当前资源匹配的正确性  
        Iterator<String> iterator = resourceMap.keySet().iterator();  
        while (iterator.hasNext()) {  
            String uri = iterator.next();  
            if (matcher.toLowerCase().equals("ant")) {  
                requestMatcher = new AntPathRequestMatcher(uri);  
            }  
            if (matcher.toLowerCase().equals("regex")) {  
                requestMatcher = new RegexRequestMatcher(uri, request  
                        .getMethod(), true);  
            }  
            if (requestMatcher.matches(request))  
                return resourceMap.get(uri);  
        }  
        return null;
	}

	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	public void reloadResource(){
		this.initResource();
	}
	
	public void setMatcher(String matcher) {  
        this.matcher = matcher;  
    }  
  
    public boolean isExpire() {  
        return expire;  
    }  
  
    public void expireNow() {  
        this.expire = true;  
    }  
}
