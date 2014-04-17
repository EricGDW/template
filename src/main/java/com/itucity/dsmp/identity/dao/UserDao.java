package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.identity.dao.entity.UserPO;

/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface UserDao extends IDao {
	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return
	 */
	UserPO findUserByUserName(String userName);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	List<UserPO> findAll();
	
	/**
	 * 获取所有有效用户
	 * @return
	 */
	List<UserPO> findAllAvailable();
	
	/**
	 * 对用户名进行like查询
	 * @param username
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	List<UserPO> findByLike(String like, Integer firstResult, Integer maxResult);
	
	/**
	 * 获取第firstResult记录到maxResult记录之间的用户
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	List<UserPO> findUserByPage(Integer firstResult, Integer maxResult);
}
