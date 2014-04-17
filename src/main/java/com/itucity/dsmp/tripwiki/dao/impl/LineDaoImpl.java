package com.itucity.dsmp.tripwiki.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.tripweek.dto.LineCondition;
import com.itucity.dsmp.tripwiki.dao.LineDao;
import com.itucity.dsmp.tripwiki.dao.entity.LinePO;
import com.itucity.dsmp.tripwiki.dao.entity.LineTagPO;

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
public class LineDaoImpl extends BaseDao 
				implements LineDao{

	@Override
	public LinePO findByName(String name) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM LinePO t WHERE 1 = 1 ");
		hql.append("AND t.name =:name ");
	
		param.put("name", name);
		
		return (LinePO) hqlQuery(hql.toString(), param);
		
//		return (LinePO) getSession().createCriteria(LinePO.class)
//				.add(Restrictions.eq("name", name)).uniqueResult();
	}

	@Override
	public List<LinePO> findByType(String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM LinePO t WHERE 1 = 1 ");
		hql.append("AND t.type :=type ");
	
		param.put("type", type);
		
		List<LinePO> list = hqlQuery(hql.toString(), param);
		
		return list;
		
//		return (List<LinePO>) getSession().createCriteria(LinePO.class)
//				.add(Restrictions.eq("type", type))
//				.addOrder( Order.desc("score") ).list();
	}

	@Override
	public List<LinePO> findByTag(String tag) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM LinePO t WHERE 1 = 1 ");
		hql.append("AND t.relatedTags.name :=tag ");
		hql.append("ORDER BY t.score DESC ");
		
		param.put("tag", tag);
		
		List<LinePO> list = hqlQuery(hql.toString(), param);
		
		return list;
		
//		return (List<LinePO>) getSession().createCriteria(LinePO.class)
//				.createCriteria("relatedTags")
//		        .add(Restrictions.eq("name", tag))
//				.list();
	}

	@Override
	public List<LinePO> findTop(Integer topNum, Integer sort) {
		String sortField = "";
		switch(sort){
		case 1:
			sortField = "t.score";
		case 2:
			sortField = "t.shareCount";
		case 3:
			sortField = "t.favouriteCount";
		}
		
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM LinePO t WHERE 1 = 1 ");
		hql.append("ORDER BY :sortField DESC ");
		hql.append("ORDER BY t.hateCount ASC ");
	
		param.put("sortField", sortField);
		
		List<LinePO> list = hqlQuery(hql.toString(), param, 0, topNum);
		
		return list;
		
//		return (List<LinePO>) getSession().createCriteria(LinePO.class)
//				.setMaxResults(topNum)
//				.addOrder( Order.desc(sortField) )
//				.addOrder( Order.asc("disagree") ).list();
	}

	@Override
	public PagesInfo<LinePO> findByCondition(LineCondition condition,
			PagesInfo pagesInfo) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM LinePO t WHERE 1 = 1 ");
		
		if(StringUtils.hasText(condition.getCity())){
			hql.append("AND t.city =:city ");
			param.put("city", condition.getCity());
		}
		if(StringUtils.hasText(condition.getType())){
			hql.append("AND t.type =:type ");
			param.put("type", condition.getType());
		}
		if(StringUtils.hasText(condition.getWho())){
			hql.append("AND t.relatedTags.name IN(:tag) ");
			param.put("tag", condition.getWho());
		}
		
		return super.hqlPageQuery(hql.toString(), pagesInfo);
	}

	@Override
	public Integer addLineTag(Integer lineId, Integer tagId) {
		LineTagPO po = new LineTagPO(lineId, tagId);
		
		super.save(po);
		
		return po.getId();
	}



}
