package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.SearchInfoDao;
import com.itucity.dsmp.tripwiki.dao.entity.SearchInfoPO;


@Transactional
@Repository("searchDao")
public class SearchInfoDaoImpl extends BaseDao implements SearchInfoDao {

	@Override
	public List<SearchInfoPO> findByUser(Integer uid, String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM SearchInfoPO t WHRER 1 = 1 ");
		
		hql.append("AND t.uid =:uid ");
		hql.append("AND t.type =:type ");
		
		param.put("type", type);
		param.put("uid", uid);
		
		List<SearchInfoPO> list = hqlQuery(hql.toString(), param);
		
		return list;
		
//		return (List<SearchInfoPO>) getSession().createCriteria(SearchInfoPO.class)
//				.add(Restrictions.eq("type", type))
//				.add(Restrictions.eq("user.id", uid))
//				.addOrder(Order.desc("time")).list();
	}

	@Override
	public List<SearchInfoPO> findByPage(Integer uid, String type,
			Integer first, Integer max) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM SearchInfoPO t WHERE 1 = 1 ");
		
		hql.append("AND t.uid =:uid ");
		hql.append("AND t.type =:type ");
		
		param.put("type", type);
		param.put("uid", uid);
		
		List<SearchInfoPO> list = hqlQuery(hql.toString(), param, first, max);
		
		return list;
		
//		return (List<SearchInfoPO>) getSession().createCriteria(SearchInfoPO.class)
//				.add(Restrictions.eq("type", type))
//				.add(Restrictions.eq("user.id", uid))
//				.setFirstResult(first)
//				.setMaxResults(max)
//				.addOrder(Order.desc("time")).list();
	}

	@Override
	public List<SearchInfoPO> findTopSearchConditon(String type, Integer topNum) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM SearchInfoPO t WHERE 1 = 1 ");
		
		hql.append("AND t.type =:type ");
		hql.append("ORDER BY t.ctime");
		
		param.put("type", type);
		
		List<SearchInfoPO> list = hqlQuery(hql.toString(), param, 0, topNum);
		
		return list;
//		Criteria criteria = getSession().createCriteria(SearchInfoPO.class);
		
//		return criteria.setProjection( Projections.projectionList()
//				        .add( Projections.count("conditions").as("countByConditions"))
//				        .add( Projections.groupProperty("conditions"), "conditions" )
//				    )
//				.add(Restrictions.eq("type", type))
//				.setMaxResults(topNum)
//				.addOrder(Order.desc("countByConditions")).list();
	}


}
