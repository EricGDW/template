package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dao.PlaceDao;
import com.itucity.dsmp.tripwiki.dao.entity.ImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceImagePOId;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceTagPO;
import com.itucity.dsmp.tripwiki.dao.entity.PlaceTagPOId;
import com.itucity.dsmp.tripwiki.dto.ImageCondition;
import com.itucity.dsmp.tripwiki.dto.PlaceCondition;

/**
 * 行程的地点Dao层实现
 * 
 * @author Eric
 * @version 0.1
 * 
 *          3/22/2014
 */
@Transactional
@Repository("placeDao")
public class PlaceDaoImpl extends BaseDao implements PlaceDao {

	@Override
	public Boolean save(PlacePO po) {
		super.save(po);
		return true;
	}

	@Override
	public Boolean delete(PlacePO po) {
		super.delete(po);
		return true;
	}

	@Override
	public PlacePO findById(Integer placeId) {
		PlacePO po = super.find(PlacePO.class, placeId);
		return po;
	
	}

	@Override
	public Boolean deleteById(Integer placeId) {
		PlacePO place = findById(placeId);
		return delete(place);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PagesInfo<ImagePO> findPlaceImages(PagesInfo pagesInfo,ImageCondition condition) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("SELECT t FROM ImagePO t, PlaceImagePO r WHERE 1 = 1 ");
		hql.append("AND r.id.imageId = t.imageId ");
		hql.append("AND r.id.placeId =:placeId ");
		
		param.put("placeId", condition.getPlaceId());
		
		PagesInfo<ImagePO> list = hqlPageQuery(hql.toString(), param, pagesInfo);
		
		return list;
	}

	@Override
	public List<PlacePO> findByCondition(PlaceCondition condition) {

		return null;
	}

	@Override
	public Boolean addPlaceTag(Integer placeId, Integer tagId) {
		PlaceTagPOId id = new PlaceTagPOId(placeId, tagId);

		PlaceTagPO placeTag = new PlaceTagPO(id);
		super.save(placeTag);

		return true;
	}

	@Override
	public Boolean addPlaceImage(Integer placeId, Integer imageId) {
		PlaceImagePOId id = new PlaceImagePOId(imageId,placeId);

		PlaceImagePO placeImage = new PlaceImagePO(id);
		super.save(placeImage);

		return true;
	}

	@Override
	public Boolean update(PlacePO placePO) {
		super.save(placePO);
		return true;
	}
}
