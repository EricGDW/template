package com.itucity.dsmp.identity.dao.impl;

import java.util.Hashtable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itucity.dsmp.common.base.impl.BaseDao;
import com.itucity.dsmp.common.page.PagesInfo;
import com.itucity.dsmp.identity.dao.UserDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;
import com.itucity.dsmp.identity.dao.entity.RolePO;
import com.itucity.dsmp.identity.dao.entity.UserDetailPO;
import com.itucity.dsmp.identity.dao.entity.UserRolePO;
import com.itucity.dsmp.identity.dao.entity.UserRolePOId;
import com.itucity.dsmp.identity.dao.entity.UserPO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
@Transactional
@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	private static Logger log = LoggerFactory
			.getLogger(UserDaoImpl.class);
	
	@Override
	public UserDetailPO findUserDetail(Integer uid) {
		return find(UserDetailPO.class, uid);
	}
	
	@Override
	public UserPO findUserByUserName(String username) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM UserPO t WHERE 1 = 1 ");
		hql.append("AND t.username =:username");
		param.put("username", username);
		List<UserPO> users = hqlQuery(hql.toString(), param);
		if(users != null && users.size() != 0){
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<UserPO> findAll() {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM UserPO t WHERE 1 = 1 ");
		List<UserPO> users = hqlQuery(hql.toString());
		return users;
	}

	@Override
	public List<UserPO> findAllAvailable() {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("FROM UserPO t WHERE 1 = 1 ");
		hql.append("AND t.isActive =:isActive");
		param.put("isActive", true);
		List<UserPO> users = hqlQuery(hql.toString(), param);
		return users;
	}

	@Override
	public PagesInfo<UserPO> findByNameLike(String name, PagesInfo<UserPO> pagesInfo) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("FROM UserPO t WHERE 1 = 1 ");
		hql.append("AND t.username LIKE:name ");
		
		param.put("name", name);
		
		if(pagesInfo == null){
			List<UserPO> users = hqlQuery(hql.toString(), param);
			
			PagesInfo<UserPO> list = new PagesInfo<UserPO>();
			
			list.setCountPerPage(users.size());
			list.setPageNumber(1);
			list.setResultsList(users);
			list.setTotalRecord(users.size());
			list.setTotalPage(1);
			
			return list;
		}
		
		PagesInfo<UserPO> list = hqlPageQuery(hql.toString(), param, pagesInfo);
	
		return list;
	}

	@Override
	public PagesInfo<UserPO> findUserByPage(PagesInfo<UserPO> pagesInfo) {
		StringBuffer hql = new StringBuffer();
		
		hql.append("FROM UserPO t WHERE 1 = 1 ");
		
		PagesInfo<UserPO> list = hqlPageQuery(hql.toString(), pagesInfo);
	
		return list;
		
	}

	@Override
	public Boolean addUserToGroup(Integer uid, Integer groupId) {
		UserRolePOId id = new UserRolePOId(uid, groupId);
		
		UserRolePO userGroup = new UserRolePO(id);
		
		save(userGroup);
		
		return true;
	}

	@Override
	public List<GroupPO> findUserGroup(Integer uid) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("SELECT t FROM GroupPO t, UserGroupPO r WHERE 1 = 1 ");
		hql.append("AND r.id.groupId = t.groupId ");
		hql.append("AND r.id.uid =:uid ");
		
		param.put("uid", uid);
		
		List<GroupPO> list = hqlQuery(hql.toString(), param);
		
		return list;
	}

	@Override
	public UserPO findById(Integer uid) {
		UserPO user = super.find(UserPO.class, uid);
		if(user == null){
			log.info(String.format("User [id : %d] not found", uid));
			return null;
		}
		return user;
	}

	@Override
	public Integer save(UserPO user) {
		super.save(user);
		return user.getUid();
	}

	@Override
	public Boolean update(UserPO user) {
		super.update(user);
		return true;
	}

	@Override
	public Boolean delete(UserPO user) {
		super.delete(user);
		return true;
	}

	@Override
	public Boolean deleteById(Integer uid) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		hql.append("DELETE FROM UserPO t WHERE 1 = 1 ");
		hql.append("AND t.uid =:uid ");
		param.put("uid", uid);
		hqlUpdate(hql.toString(), param);
		return true;
	}

	@Override
	public Boolean addRoleToUser(Integer roleId, Integer uid) {
		UserRolePOId id = new UserRolePOId(uid, roleId);
		UserRolePO userRole = new UserRolePO(id);
		save(userRole);
		return true;
	}

	@Override
	public List<RolePO> findUserRole(Integer uid) {
		StringBuffer hql = new StringBuffer();
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		
		hql.append("SELECT t FROM RolePO t, UserRolePO r WHERE 1 = 1 ");
		hql.append("AND r.id.roleId = t.roleId ");
		hql.append("AND r.id.uid =:uid ");
		
		param.put("uid", uid);
		
		List<RolePO> list = hqlQuery(hql.toString(), param);
		
		return list;
	}

}
