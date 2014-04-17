package com.itucity.dsmp.identity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.identity.dao.GroupDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Transactional
@Repository("groupDao")
public class GroupDaoImpl extends BaseDao implements GroupDao{

	@Override
	public GroupPO findByName(String groupName) {
		return (GroupPO) getSession().createCriteria(GroupPO.class)
				.add(Restrictions.eq("name", groupName)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupPO> findAll() {
		return (List<GroupPO>) getSession().createCriteria(GroupPO.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupPO> findByType(String type) {
		return (List<GroupPO>) getSession().createCriteria(GroupPO.class)
				.add(Restrictions.eq("type", type)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GroupPO> findByLike(String like, Integer first, Integer max) {
		Criteria criteria = getSession().createCriteria(GroupPO.class)
							.add(Restrictions.like("name", like, MatchMode.ANYWHERE));	
		if(first != null){
			criteria.setFirstResult(first >= 0 ? first : 0);
		}
		if(max != null ){
			criteria.setMaxResults(max > 0 ? max : 10);
		}
		
		return (List<GroupPO>) criteria.list();
	}

}
