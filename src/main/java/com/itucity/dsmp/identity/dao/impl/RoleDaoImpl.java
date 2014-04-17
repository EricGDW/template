package com.itucity.dsmp.identity.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.RoleDao;
import com.itucity.dsmp.identity.dao.entity.RolePO;

@Transactional
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao implements RoleDao{

	@Override
	public List<RolePO> findAll() {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM RolePO t WHERE 1 = 1 ");
		List<RolePO> roles = super.hqlQuery(hql.toString());
		return roles;
	}

	@Override
	public List<RolePO> findByType(String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM RolePO t WHERE 1 = 1 ");
		hql.append("AND t.type =:type ");
		param.put("type", type);
		List<RolePO> roles = hqlQuery(hql.toString(), param);
		return roles;
	}

	@Override
	public List<RolePO> findByNameLike(String roleName) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM RolePO t WHERE 1 = 1 ");
		hql.append("AND t.roleName LIKE(:roleName) ");
		param.put("roleName", roleName);
		List<RolePO> roles = super.hqlQuery(hql.toString(), param);
		return roles;
	}

	@Override
	public PagesInfo<RolePO> findByPage(PagesInfo<RolePO> pagesInfo) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM RolePO t WHERE 1 = 1 ");
		PagesInfo<RolePO> roles = super.hqlPageQuery(hql.toString(), pagesInfo);
		return roles;
	}

	@Override
	public RolePO findById(Integer id) {
		return super.find(RolePO.class, id);
	}

	@Override
	public Integer save(RolePO role) {
		super.save(role);
		return role.getRoleId();
	}

	@Override
	public Boolean update(RolePO role) {
		super.update(role);
		return true;
	}

	@Override
	public Boolean delete(RolePO role) {
		super.delete(role);
		return true;
	}

	@Override
	public Boolean deleteById(Integer roleId) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("DELETE FROM RolePO t WHERE 1 = 1 ");
		hql.append("AND t.roleId =:roleId ");
		param.put("roleId", roleId);
		hqlUpdate(hql.toString(), param);
		return true;
	}

}
