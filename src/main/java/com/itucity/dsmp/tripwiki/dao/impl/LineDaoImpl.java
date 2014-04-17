package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripwiki.dao.LineDao;
import com.itucity.dsmp.tripwiki.dao.entity.LineImagePO;
import com.itucity.dsmp.tripwiki.dao.entity.LineImagePOId;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.LinePlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.LinePlacePOId;
import com.itucity.dsmp.tripwiki.dao.entity.LineTagPO;
import com.itucity.dsmp.tripwiki.dao.entity.LineTagPOId;
import com.itucity.dsmp.tripwiki.dao.entity.PlacePO;
import com.itucity.dsmp.tripwiki.dao.entity.TagPO;
import com.itucity.dsmp.tripwiki.dto.LineCondition;

/**
 * 行程的线路Dao层实现
 * 
 * @author Eric
 * @version 0.1
 * 
 * 3/22/2014
 */
@Transactional
@Repository("lineDao")
public class LineDaoImpl extends BaseDao implements LineDao {

	@Override
	public LinePO findById(Integer lineId) {
		return super.find(LinePO.class, lineId);
	}

	@Override
	public Integer save(LinePO line) {
		super.save(line);
		return line.getLineId();
	}

	@Override
	public Boolean update(LinePO line) {
		super.update(line);
		return true;
	}

	@Override
	public Boolean delete(LinePO line) {
		super.delete(line);
		return true;
	}

	@Override
	public Boolean deleteById(Integer lineId) {
		LinePO line = findById(lineId);
		return delete(line);
	}

	@Override
	public PagesInfo<LinePO> findByCondition(LineCondition condition,
			PagesInfo<LinePO> pagesInfo) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		List<Integer> tagIds = new ArrayList<Integer>();
		
		hql.append("SELECT DISTINCT t FROM LinePO t, LineTagPO r WHERE 1 = 1 ");
		hql.append("AND t.lineId = r.id.lineId ");
		hql.append("AND r.id.tagId IN (:tagIds) ");
		
		if(condition.getWhen() != null){
			tagIds.add(condition.getWhen());
		}
		if(condition.getWho() != null){
			tagIds.add(condition.getWho());
		}
		if(condition.getType() != null){
			tagIds.add(condition.getType());
		}
		param.put("tagIds", tagIds);
		return super.hqlPageQuery(hql.toString(), param, pagesInfo);
	}

	@Override
	public Boolean addLineTag(Integer lineId, Integer tagId) {
		LineTagPOId id = new LineTagPOId(lineId, tagId);
		
		LineTagPO lineTag = new LineTagPO(id);
		super.save(lineTag);
		
		return true;
	}

	@Override
	public Boolean addLineImage(Integer lineId, Integer imageId) {
		LineImagePOId id = new LineImagePOId(lineId, imageId);
		LineImagePO lineImage = new LineImagePO(id);
		save(lineImage);
		return true;
	}

	@Override
	public Boolean addLinePlace(Integer lineId, Integer placeId, Integer sort) {
		LinePlacePOId id = new LinePlacePOId(placeId, lineId);
		LinePlacePO linePlace = new LinePlacePO(id, sort);
		save(linePlace);
		return true;
	}

	@Override
	public List<PlacePO> findLinePlace(Integer lineId) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("SELECT t FROM PlacePO t, LinePlacePO r WHERE 1 = 1 ");
		hql.append("AND r.id.placeId = t.placeId ");
		hql.append("AND r.id.lineId =:lineId ");
		hql.append("ORDER BY r.sort ASC");
		param.put("lineId", lineId);
		
		List<PlacePO> list = hqlQuery(hql.toString(), param);
		return list;
	}

	@Override
	public List<TagPO> findLineTag(Integer lineId) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("SELECT t FROM TagPO t, LineTagPO r WHERE 1 = 1 ");
		hql.append("AND r.id.tagId = t.tagId ");
		hql.append("AND r.id.lineId =:lineId ");
		param.put("lineId", lineId);
		
		List<TagPO> list = hqlQuery(hql.toString(), param);
		return list;
	}

}
