package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripwiki.dao.ImageDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;

/**
 * 图片的Dao层实现
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/24/2014
 */
@Transactional
@Repository("imageDao")
public class ImageDaoImpl extends BaseDao implements ImageDao {

	@Override
	public List<ImagePO> findByType(String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM ImagePO t WHERE 1 = 1 ");
		
		hql.append("AND t.type =:type ");
		
		param.put("type", type);
		
		List<ImagePO> list = hqlQuery(hql.toString(), param);
		
		return list;
	}

	@Override
	public List<ImagePO> findByDescription(String description) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM ImagePO t WHERE 1 = 1 ");
		
		hql.append("AND t.description LIKE:description ");
		
		param.put("description", description);
		
		List<ImagePO> list = hqlQuery(hql.toString(), param);
		
		return list;
	}

	

}
