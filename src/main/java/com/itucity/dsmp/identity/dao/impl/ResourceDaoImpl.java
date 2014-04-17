package com.itucity.dsmp.identity.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.identity.dao.ResourceDao;
import com.itucity.dsmp.identity.dao.ResourceTypeEnum;
import com.itucity.dsmp.identity.dao.entity.RolePO;
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

	@Override
	public List<UrlPO> getAllUrl() {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("SELECT t FROM UrlPO t, ResourcePO t2 WHERE 1 = 1 ");
		hql.append("AND t.id = t2.resourceId ");
		hql.append("AND t2.type =:type ");
		param.put("type", ResourceTypeEnum.URL.toString());
		List<UrlPO> urls = super.hqlQuery(hql.toString(), param);
		return urls;
	}

	@Override
	public UrlPO getUrlById(Integer id) {
		return super.find(UrlPO.class, id);
	}

	@Override
	public List<RolePO> getResourceRoleByType(Integer resourceId, String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("SELECT t FROM RolePO t, RoleResourcePO r, ResourcePO t2 WHERE 1 = 1 ");
		hql.append("AND t.roleId = r.id.roleId ");
		hql.append("AND t2.resourceId =:resourceId ");
		hql.append("AND t2.type =:type ");
		hql.append("AND r.id.resourceId = t2.id ");
		param.put("type", type);
		param.put("resourceId", resourceId);
		List<RolePO> roles = super.hqlQuery(hql.toString(), param);
		return roles;
	}

}
