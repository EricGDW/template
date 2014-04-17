package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

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
 *          3/30/2014
 */
@Transactional
@Repository("tagDao")
public class TagDaoImpl extends BaseDao implements TagDao {

	@Override
	public TagPO findById(Integer tagId) {
		return super.find(TagPO.class, tagId);
	}

	@Override
	public Integer save(TagPO tag) {
		super.save(tag);
		return tag.getTagId();
	}

	@Override
	public Boolean update(TagPO tag) {
		super.update(tag);
		return true;
	}

	@Override
	public Boolean delete(TagPO tag) {
		super.delete(tag);
		return true;
	}

	@Override
	public Boolean deleteById(Integer tagId) {
		TagPO tag = findById(tagId);
		return delete(tag);
	}
	
	@Override
	public TagPO findByName(String name) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM TagPO t WHERE 1 = 1 ");
		hql.append("AND t.name =:name ");
		param.put("name", name);
		
		List<TagPO> tagPOs = super.hqlQuery(hql.toString(), param);

		if (tagPOs.size() > 0)
			return tagPOs.get(0);
		return null;

	}

}
