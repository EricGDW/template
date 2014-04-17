package com.itucity.dsmp.identity.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.GroupDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Transactional
@Repository("groupDao")
public class GroupDaoImpl extends BaseDao implements GroupDao{

	private static Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);
	
	@Override
	public List<GroupPO> findAll() {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM GroupPO t WHERE 1 = 1 ");
		List<GroupPO> groups = hqlQuery(hql.toString());
		return groups;
	}

	@Override
	public List<GroupPO> findByType(String type) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM GroupPO t WHERE 1 = 1 ");
		hql.append("AND t.type =:type");
		param.put("type", type);
		List<GroupPO> groups = hqlQuery(hql.toString(), param);
		return groups;
	}

	@Override
	public List<GroupPO> findByNameLike(String name) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM GroupPO t WHERE 1 = 1 ");
		hql.append("AND t.groupName LIKE:name");
		param.put("name", name);
		List<GroupPO> groups = hqlQuery(hql.toString(), param);
		return groups;
	}

	@Override
	public PagesInfo<GroupPO> findByPage(PagesInfo<GroupPO> pagesInfo) {
		StringBuffer hql = new StringBuffer();
		
		hql.append("FROM GroupPO t WHERE 1 = 1 ");
		
		PagesInfo<GroupPO> list = hqlPageQuery(hql.toString(), pagesInfo);
	
		return list;
	}

	@Override
	public GroupPO findById(Integer id) {
		GroupPO group = super.find(GroupPO.class, id);
		if(group == null){
			log.info(String.format("Group [id : %d] not found", id));
			return null;
		}
		return group;
	}

	@Override
	public Integer save(GroupPO group) {
		super.save(group);
		return group.getGroupId();
	}

	@Override
	public Boolean update(GroupPO group) {
		super.update(group);
		return true;
	}

	@Override
	public Boolean delete(GroupPO group) {
		super.delete(group);
		return true;
	}

	@Override
	public Boolean deleteById(Integer id) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("DELETE FROM GroupPO t WHERE 1 = 1 ");
		hql.append("AND t.GroupId =:id ");
		param.put("id", id);
		hqlUpdate(hql.toString(), param);
		return true;
	}

}
