package com.itucity.dsmp.identity.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.UserPO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Transactional
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public UserPO findUserByUserName(String userName) {
		return (UserPO) getSession().createCriteria(UserPO.class)
				.add(Restrictions.eq("username", userName)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPO> findAll() {
		return (List<UserPO>) getSession().createCriteria(UserPO.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPO> findAllAvailable() {
		return (List<UserPO>) getSession().createCriteria(UserPO.class)
				.add(Restrictions.eq("isValid", true)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPO> findByLike(String like, Integer firstResult, 
			Integer maxResult) {
		Criteria criteria = getSession().createCriteria(UserPO.class)
				.add(Restrictions.like("username", like, MatchMode.ANYWHERE));
		
		if(firstResult != null){
			criteria.setFirstResult(firstResult > 0 ? firstResult : 0);
		}
		
		if(maxResult != null){
			criteria.setMaxResults(maxResult > 0 ? maxResult : 10);
		}
		
		return (List<UserPO>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPO> findUserByPage(Integer firstResult, Integer maxResult) {
		return (List<UserPO>) getSession().createCriteria(UserPO.class)
				.setFirstResult(firstResult).setMaxResults(maxResult).list();
	}

}
