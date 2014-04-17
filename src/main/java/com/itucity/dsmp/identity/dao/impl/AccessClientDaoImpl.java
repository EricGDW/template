package com.itucity.dsmp.identity.dao.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.identity.dao.AccessClientDao;
import com.itucity.dsmp.identity.dao.entity.AccessClientPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/16/2014
 */
@Transactional
@Repository("accessClientDao")
public class AccessClientDaoImpl extends BaseDao implements AccessClientDao{

	@Override
	public AccessClientPO findByAppName(String appName) {
		return (AccessClientPO) getSession().createCriteria(AccessClientPO.class)
				.add(Restrictions.eq("appName", appName)).uniqueResult();
	}

	@Override
	public AccessClientPO findByAppKey(String appKey) {
		return (AccessClientPO) getSession().createCriteria(AccessClientPO.class)
				.add(Restrictions.eq("appKey", appKey)).uniqueResult();
	}

}
