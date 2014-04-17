package com.itucity.dsmp.identity.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.identity.dao.AccessClientDao;
import com.itucity.dsmp.identity.dao.entity.AccessClientPO;
import com.itucity.dsmp.identity.service.AccessClientService;
import com.itucity.dsmp.identity.service.model.AccessClientVO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/16/2014
 */
@Service("accessClientService")
@Transactional
public class AccessClientServiceImpl implements AccessClientService{

	private static Logger logger = LoggerFactory
			.getLogger(AccessClientServiceImpl.class);
	
	@Resource
	private AccessClientDao accessClientDao;
	
	public AccessClientServiceImpl() {
	}

	@Override
	public AccessClientVO getByAppName(String appName) {
		AccessClientPO  accessToken = accessClientDao.findByAppName(appName);
		AccessClientVO accessTokenVO = new AccessClientVO();
		
		if(accessToken != null){
			accessTokenVO.setAppName(accessToken.getAppName());
			accessTokenVO.setAppKey(accessToken.getAppKey());
			accessTokenVO.setAppSecret(accessToken.getAppSecret());
			accessTokenVO.setIsValid(accessToken.getIsValid());
			return accessTokenVO;
		}
		logger.info("App name {0} not found",
				new Object[] { appName } );
		return null;
	}

	@Override
	public AccessClientVO getByAppKey(String appKey) {
		AccessClientPO  accessToken = accessClientDao.findByAppKey(appKey);
		AccessClientVO accessTokenVO = new AccessClientVO();
		
		if(accessToken != null){
			accessTokenVO.setAppName(accessToken.getAppName());
			accessTokenVO.setAppKey(accessToken.getAppKey());
			accessTokenVO.setAppSecret(accessToken.getAppSecret());
			accessTokenVO.setIsValid(accessToken.getIsValid());
			return accessTokenVO;
		}
		
		logger.info("App key {0} not found",
				new Object[] { appKey } );
		return null;
	}

	@Override
	public AccessClientVO getByState(String state) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editAccessClient(AccessClientVO vo) {
		// TODO Auto-generated method stub
		
	}

}
