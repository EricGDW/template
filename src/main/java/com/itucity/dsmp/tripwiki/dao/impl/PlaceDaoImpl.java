package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.tripweek.dto.PlaceCondition;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceTagPO;

/**
 * 行程的地点Dao层实现
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
@Transactional
@Repository("placeDao")
public class PlaceDaoImpl extends BaseDao implements PlaceDao {

	@Override
	public PlacePO findByName(String name) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM PlacePO t WHERE 1 = 1 ");
		hql.append("AND t.name =:name ");
	
		param.put("name", name);
		
		return (PlacePO) hqlQuery(hql.toString(), param);
		
//		 return (PlacePO) getSession().createCriteria(PlacePO.class)
//			.add(Restrictions.eq("name", name)).uniqueResult();
	}

	@Override
	public List<PlacePO> findByTag(String tag) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM PlacePO t WHERE 1 = 1 ");
		hql.append("AND t.relatedTags.name =:name ");
	
		param.put("name", tag);
		
		List<PlacePO> list =  hqlQuery(hql.toString(), param);
		
		return list;
		
//		return (List<PlacePO>) getSession().createCriteria(PlacePO.class)
//				.createCriteria("relatedTags")
//		        .add(Restrictions.eq("name", tag))
//				.list();
	}

	@Override
	public List<PlacePO> findByCondition(PlaceCondition condition) {
		
		
		return null;
	}

	@Override
	public Integer addPlaceTag(Integer placeId, Integer tagId) {
		PlaceTagPO po = new PlaceTagPO(placeId, tagId);
		
		super.save(po);
		
		return po.getId();
	}

	@Override
	public Integer addPlaceImage(Integer placeId, Integer imageId) {
		PlaceImagePO po = new PlaceImagePO(placeId, imageId);
		
		super.save(po);
		
		return po.getId();
		
	}

}
