package com.itucity.dsmp.identity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.identity.dao.ResourceDao;
import com.itucity.dsmp.identity.dao.entity.UrlPO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Transactional
@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDao implements ResourceDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlPO> getAllUrl() {
		return (List<UrlPO>) getSession().createCriteria(UrlPO.class).list();
	}

	@Override
	public UrlPO getUrlById(Integer id) {
		return find(UrlPO.class, id);
	}

}
