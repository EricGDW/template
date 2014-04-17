package com.itucity.dsmp.identity.dao;

import java.util.List;

import com.itucity.dsmp.common.base.IDao;
import com.itucity.dsmp.identity.dao.entity.GroupPO;


/**
 * @author Eric
 * @version 0.1
 * 
 * 3/13/2014
 */
public interface GroupDao extends IDao {

	/**
	 * 根据组名查询
	 * @param groupName
	 * @return
	 */
	GroupPO findByName(String groupName);
	
	/**
	 * 查询所有组
	 * @return
	 */
	List<GroupPO> findAll();
	
	/**
	 * 根据组类型查询
	 * @param type
	 * @return
	 */
	List<GroupPO> findByType(String type);
	
	/**
	 * 对组名进行like查询
	 * @param like
	 * @param first 开始记录
	 * @param max   结果数
	 * 
	 * @return
	 */
	List<GroupPO> findByLike(String like, Integer first, Integer max);
}
