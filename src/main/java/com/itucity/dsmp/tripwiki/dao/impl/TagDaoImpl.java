package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.TagDao;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;


/**
 * 标签
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/30/2014
 */
@Transactional
@Repository("tagDao")
public class TagDaoImpl extends BaseDao implements TagDao{
	
	@Override
	public TagPO findByName(String name) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM TagPO t WHERE 1 = 1 ");
		hql.append("AND t.name =:name ");
	
		param.put("name", name);
		
		return (TagPO) hqlQuery(hql.toString(), param);
		
//		return (TagPO) getSession().createCriteria(TagPO.class)
//				.add(Restrictions.eq("name", name)).uniqueResult();
	}

}
