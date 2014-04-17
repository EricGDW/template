package com.itucity.dsmp.identity.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.dao.entity.UserPO;


/**
 * 安全资源（URL）和角色映射关系处理器
 * 
 * @author Eric
 * @since 0.0.1 <p>2014-3-15 下午2:25:09</p>
 */
@Service
public class SecurityUserDetailsService implements UserDetailsService{
	private static Logger logger = LoggerFactory
			.getLogger(SecurityUserDetailsService.class);
	
	protected MessageSourceAccessor messages
			= SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private UserDao userDao;

	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		UserPO user = userDao.findUserByUserName(username);
		
		if (null == user) {
			logger.info("User.notFound", new Object[] { username },
						"Username {0} not found");
			throw new UsernameNotFoundException(
				messages.getMessage("User.notFound", new Object[] { username },
						"Username {0} not found"));
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<GroupPO> userRoles = user.getGroups();
		for (GroupPO userRole : userRoles) {
			//这里的 role 参数为自己定义的，要和 SecurityMetadataSource 中的 SecurityConfig 参数对应
			SimpleGrantedAuthority authority 
					= new SimpleGrantedAuthority(userRole.getName());
			authorities.add(authority);
		}
		
		//创建 UserDetails 对象
		SecurityUserDetails userDetails = new SecurityUserDetails(
				user.getUid() ,username, user.getPassword(), user.getIsValid(),
				authorities);
		return userDetails;
	}

	
}
