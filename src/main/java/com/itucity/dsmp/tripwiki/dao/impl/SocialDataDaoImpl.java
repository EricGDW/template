package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.SocialDataDao;
import com.itucity.dsmp.tripwiki.dao.entity.SocialDataPO;


@Transactional
@Repository("socialDataDao")
public class SocialDataDaoImpl extends BaseDao implements SocialDataDao{

	@Override
	public List<SocialDataPO> findByUser(Integer uid, String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM CollectionPO t WHERE 1 = 1 ");
		
		hql.append("AND t.sourceType =:type ");
		hql.append("AND t.user.uid =:uid ");
		hql.append("ORDER BY t.ctime DESC");
		
		param.put("uid", uid);
		param.put("type", type);
		
		List<SocialDataPO> list = hqlQuery(hql.toString(), param);
		
		return list;
		
//		return (List<CollectionPO>) getSession().createCriteria(CollectionPO.class)
//				.add(Restrictions.eq("sourceType", type))
//				.add(Restrictions.eq("user.uid", uid))
//				.addOrder(Order.desc("ctime")).list();
	}

	@Override
	public List<SocialDataPO> findByPage(Integer uid, String type,
			Integer first, Integer max) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM CollectionPO t WHERE 1 = 1 ");
		
		hql.append("ADN　t.type =:type ");
		hql.append("AND t.user.uid =:uid ");
		hql.append("ORDER BY t.ctime DESC");
		
		param.put("uid", uid);
		param.put("type", type);
		
		List<SocialDataPO> list =  hqlQuery(hql.toString(), param, first, max);
		
		return list;
		
//		return (List<CollectionPO>) getSession().createCriteria(CollectionPO.class)
//				.add(Restrictions.eq("sourceType", type))
//				.add(Restrictions.eq("user.uid", uid))
//				.setFirstResult(first)
//				.setMaxResults(max)
//				.addOrder(Order.desc("ctime")).list();
	}


}
